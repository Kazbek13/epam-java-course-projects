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
public class AirportCRUDController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            commandFactory.createCommand("deleteAirport").execute(request, response);
        }

        if (action.equalsIgnoreCase("edit")) {
            commandFactory.createCommand("showUpdateAirportPage").execute(request, response);
        }

        if (action.equalsIgnoreCase("insert")) {
            commandFactory.createCommand("showCreateAirportPage").execute(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean isUpdating = Boolean.valueOf(request.getParameter("isUpdating"));

        if (isUpdating == true) {
            CommandFactory commandFactory = CommandFactory.getInstance();
            commandFactory.createCommand("updateAirportInstance").execute(request, response);
        } else {
            CommandFactory commandFactory = CommandFactory.getInstance();
            commandFactory.createCommand("createAirportInstance").execute(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
