package Project.servlets;

import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.entity.mappers.MapperForServlet;
import Project.Classes.Infrastructure.dto.service.TypesService;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import Project.servlets.utils.InterfaceToImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewCars")
public class ViewCarsServlet extends HttpServlet {
    private VehiclesService vehiclesService;
    private TypesService typesService;
    @Override
    public void init() throws ServletException {

        ApplicationContext context = new ApplicationContext("Project", InterfaceToImplementation.interfaceToImplementation);
        try {
            context.getObject(MapperForServlet.class);
            typesService = context.getObject(TypesService.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        req.setAttribute("cars", MapperForServlet.getVehiclesDTOForCars());
        req.setAttribute("types", typesService.getAll());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewCarsJSP.jsp");
        dispatcher.forward(req, resp);
    }
}
