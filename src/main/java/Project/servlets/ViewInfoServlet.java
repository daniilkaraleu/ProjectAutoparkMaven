package Project.servlets;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.impl.ConnectionFactoryImpl;
import Project.Classes.Infrastructure.dto.impl.EntityManagerImpl;
import Project.Classes.Infrastructure.dto.service.RentsService;
import Project.Classes.Infrastructure.dto.service.TypesService;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/info")
public class ViewInfoServlet extends HttpServlet {
    private VehiclesService vehiclesService;
    private TypesService typesService;
    private RentsService rentsService;
    @Override
    public void init() throws ServletException {
        Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();

        interfaceToImplementation.put(Manager.class, CollectionManager.class);
        interfaceToImplementation.put(EntityManager.class, EntityManagerImpl.class);
        interfaceToImplementation.put(ConnectionFactory.class, ConnectionFactoryImpl.class);
        interfaceToImplementation.put(Context.class, ApplicationContext.class);
        interfaceToImplementation.put(Fixer.class, MechanicService.class);

        ApplicationContext context = new ApplicationContext("Project", interfaceToImplementation);
        try {
            vehiclesService = context.getObject(VehiclesService.class);
            typesService = context.getObject(TypesService.class);
            rentsService = context.getObject(RentsService.class);
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

        long id = Long.parseLong(req.getParameter("id"));

        req.setAttribute("car", vehiclesService.get(id));
        req.setAttribute("type", typesService.get(vehiclesService.get(id).getType()));
        req.setAttribute("rents", rentsService.getAll().stream().filter(rents -> rents.getVehicleId().equals(id)).toList());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/viewInfoJSP.jsp");
        dispatcher.forward(req, resp);
    }
}
