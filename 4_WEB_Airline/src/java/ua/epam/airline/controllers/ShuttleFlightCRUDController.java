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
public class ShuttleFlightCRUDController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            commandFactory.createCommand("deleteShuttleFlight").execute(request, response);
        }

        if (action.equalsIgnoreCase("insert")) {
            commandFactory.createCommand("showCreateShuttleFlightPage").execute(request, response);
        }

        if (action.equalsIgnoreCase("editCrew")) {
            commandFactory.createCommand("editCabinCrew").execute(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        String page = request.getParameter("page");

        if (page.equals("cabinCrew")) {
            commandFactory.createCommand("saveCabinCrew").execute(request, response);
        }
        else {
            commandFactory.createCommand("createShuttleFlightInstance").execute(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
