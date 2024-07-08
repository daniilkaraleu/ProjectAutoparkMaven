package Project.servlets;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.dto.entity.MapperForDB;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.entity.OrderDTO;
import Project.Classes.Infrastructure.dto.impl.ConnectionFactoryImpl;
import Project.Classes.Infrastructure.dto.impl.EntityManagerImpl;
import Project.Classes.Infrastructure.dto.service.OrdersService;
import Project.Classes.Infrastructure.dto.service.RentsService;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import Project.Classes.MechanicService;
import Project.Classes.interfaces.Fixer;
import Project.Classes.interfaces.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/viewPlannedDiagnostic")
public class ViewPlannedDiagnosticServlet extends HttpServlet {
    private OrdersService ordersService;
    private MechanicService mechanicService;
    private VehiclesService vehiclesService;
    private RentsService rentsService;
    private HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        LocalTime time = (LocalTime) session.getAttribute("time");

        if (time == null){
            time = LocalTime.now();
            session.setAttribute("time", time);
            setNewBreaking();
        }

        if (300 <= LocalTime.now().toSecondOfDay() - time.toSecondOfDay()){
            setNewBreaking();

            time = LocalTime.now();
        }
        long timer = LocalTime.now().toSecondOfDay();

        long timeToShow = timer - time.toSecondOfDay();

        List<OrderDTO> list = ordersService.getAll();

        req.setAttribute("timeToShow", timeToShow);
        req.setAttribute("orders", list);
        req.setAttribute("vehicles", vehiclesService);
        req.setAttribute("mechanics", mechanicService);
        req.setAttribute("rents", rentsService);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewPlannedDiagnosticJSP.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();

        interfaceToImplementation.put(Manager.class, CollectionManager.class);
        interfaceToImplementation.put(EntityManager.class, EntityManagerImpl.class);
        interfaceToImplementation.put(ConnectionFactory.class, ConnectionFactoryImpl.class);
        interfaceToImplementation.put(Context.class, ApplicationContext.class);
        interfaceToImplementation.put(Fixer.class, MechanicService.class);

        try {ApplicationContext context = new ApplicationContext("Project", interfaceToImplementation);
            ordersService = context.getObject(OrdersService.class);
            mechanicService = context.getObject(MechanicService.class);
            rentsService = context.getObject(RentsService.class);
            vehiclesService = context.getObject(VehiclesService.class);

        } catch (Exception e){
            throw new RuntimeException(e);
        }

        super.init();
    }

    private void setNewBreaking(){
        vehiclesService
                .getAll()
                .stream()
                .map(vehicles -> MapperForDB.createVehicle(vehicles, rentsService.getAll()))
                .forEach(mechanicService::repair);

        vehiclesService
                .getAll()
                .stream()
                .map(vehicles -> MapperForDB.createVehicle(vehicles, rentsService.getAll()))
                .forEach(mechanicService::detectBreaking);
    }
}
