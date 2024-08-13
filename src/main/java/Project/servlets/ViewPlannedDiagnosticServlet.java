package Project.servlets;

import Project.Classes.Infrastructure.dto.entity.VehicleDTO;
import Project.Classes.Infrastructure.dto.entity.mappers.MapperForDB;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.entity.mappers.MapperForServlet;
import Project.Classes.Infrastructure.dto.service.OrdersService;
import Project.Classes.Infrastructure.dto.service.RentsService;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import Project.Classes.MechanicService;
import Project.servlets.utils.InterfaceToImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/viewPlannedDiagnostic")
public class ViewPlannedDiagnosticServlet extends HttpServlet {
    private static final int SECONDS_TO_WAIT = 300;

    private OrdersService ordersService;
    private MechanicService mechanicService;
    private VehiclesService vehiclesService;
    private RentsService rentsService;
    private HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        LocalTime time = (LocalTime) session.getAttribute("time");

        if (time == null){
            time = LocalTime.now();
            session.setAttribute("time", time);

            setNewBreaking();
        }

        if (SECONDS_TO_WAIT <= LocalTime.now().toSecondOfDay() - time.toSecondOfDay()){
            setNewBreaking();
            time = LocalTime.now();
        }

        long timeToShow = LocalTime.now().toSecondOfDay() - time.toSecondOfDay();

        List<OrderDTO> list = ordersService.getAll();

        req.setAttribute("timeToShow", timeToShow);
        req.setAttribute("orders", list);
        List<VehicleDTO> list1 = MapperForServlet.getVehiclesDTOForDiagnostics();
        req.setAttribute("vehicles", list1);

        this.getServletContext().getRequestDispatcher("/jsp/viewPlannedDiagnosticJSP.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        try {ApplicationContext context = new ApplicationContext("Project", InterfaceToImplementation.interfaceToImplementation);
            context.getObject(MapperForServlet.class);

            ordersService = context.getObject(OrdersService.class);
            mechanicService = context.getObject(MechanicService.class);
//            rentsService = context.getObject(RentsService.class);
            vehiclesService = context.getObject(VehiclesService.class);

        } catch (Exception e){
            throw new RuntimeException(e);
        }

        super.init();
    }

    private void setNewBreaking(){
        ordersService.getAll()
                .forEach(orderDTO -> mechanicService.repair(orderDTO.getId()));

        vehiclesService
                .getAll()
                .stream()
                .map(MapperForDB::createVehicle)
                .forEach(mechanicService::detectBreaking);
    }
}
