package ua.epam.airline.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.epam.airline.commands.CommandFactory;

/**
 *
 * @author Andrew
 */
public class AircraftCRUDController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            commandFactory.createCommand("deleteAircraft").execute(request, response);
        }

        if (action.equalsIgnoreCase("insert")) {
            commandFactory.createCommand("showCreateAircraftPage").execute(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        commandFactory.createCommand("createAircraftInstance").execute(request, response);
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
}
