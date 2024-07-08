package Project.servlets;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.entity.MapperForServlet;
import Project.Classes.Infrastructure.dto.impl.ConnectionFactoryImpl;
import Project.Classes.Infrastructure.dto.impl.EntityManagerImpl;
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

@WebServlet("/viewDiagnostic")
public class ViewDiagnosticServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("cars", MapperForServlet.getVehiclesDTOForDiagnostics());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewDiagnosticsJSP.jsp");
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

        ApplicationContext context = new ApplicationContext("Project", interfaceToImplementation);

        try {
            context.getObject(MapperForServlet.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }
}
