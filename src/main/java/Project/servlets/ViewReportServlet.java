package Project.servlets;

import Project.Classes.Infrastructure.core.impl.ApplicationContext;
import Project.Classes.Infrastructure.dto.entity.mappers.MapperForServlet;
import Project.Classes.Infrastructure.dto.service.VehiclesService;
import Project.servlets.utils.InterfaceToImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewReport")
public class ViewReportServlet extends HttpServlet {
    private VehiclesService vehiclesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", MapperForServlet.getVehiclesDTOForReport());

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/viewReportJSP.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ApplicationContext context = new ApplicationContext("Project", InterfaceToImplementation.interfaceToImplementation);

        try {
            context.getObject(MapperForServlet.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.init();
    }
}
