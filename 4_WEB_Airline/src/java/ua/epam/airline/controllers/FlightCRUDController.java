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
public class FlightCRUDController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("delete")) {
            commandFactory.createCommand("deleteFlight").execute(request, response);
        }
        
        if (action.equalsIgnoreCase("edit")) {
            commandFactory.createCommand("showUpdateFlightPage").execute(request, response);
        }
        
        if (action.equalsIgnoreCase("insert")) {
            commandFactory.createCommand("showCreateFlightPage").execute(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isUpdating = Boolean.valueOf(request.getParameter("isUpdating"));
        
        if (isUpdating == true) {
            CommandFactory commandFactory = CommandFactory.getInstance();
            commandFactory.createCommand("updateFlightInstance").execute(request, response);
        }
        else {
            CommandFactory commandFactory = CommandFactory.getInstance();
            commandFactory.createCommand("createFlightInstance").execute(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
