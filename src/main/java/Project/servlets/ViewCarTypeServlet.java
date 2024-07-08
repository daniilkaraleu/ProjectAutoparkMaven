package Project.servlets;

import Project.Classes.CollectionManager;
import Project.Classes.Infrastructure.core.Context;
import Project.Classes.Infrastructure.core.annotations.Autowired;
import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.ConnectionFactory;
import Project.Classes.Infrastructure.dto.EntityManager;
import Project.Classes.Infrastructure.dto.impl.ConnectionFactoryImpl;
import Project.Classes.Infrastructure.dto.impl.EntityManagerImpl;
import Project.Classes.Infrastructure.dto.service.TypesService;
import Project.Classes.MechanicService;
import Project.Classes.interfaces.Fixer;
import Project.Classes.interfaces.Manager;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/jsp/viewTypes")
public class ViewCarTypeServlet extends HttpServlet {
    @Autowired
    private TypesService typesService;


    private Context context;
    @SneakyThrows
    @Override
    public void init() throws ServletException {
        Map<Class<?>, Class<?>> interfaceToImplementation = new HashMap<>();

        interfaceToImplementation.put(Manager.class, CollectionManager.class);
        interfaceToImplementation.put(EntityManager.class, EntityManagerImpl.class);
        interfaceToImplementation.put(ConnectionFactory.class, ConnectionFactoryImpl.class);
        interfaceToImplementation.put(Context.class, ApplicationContext.class);
        interfaceToImplementation.put(Fixer.class, MechanicService.class);

        ApplicationContext context = new ApplicationContext("Project", interfaceToImplementation);
        typesService = context.getObject(TypesService.class);

        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("types", typesService.getAll());
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewTypesJSP.jsp");
        dispatcher.forward(req,resp);
    }
}
