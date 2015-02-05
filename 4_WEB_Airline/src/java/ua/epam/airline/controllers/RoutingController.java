package ua.epam.airline.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.epam.airline.commands.CommandFactory;

/**
 *
 * @author Andrew
 */
public class RoutingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        HttpSession session = request.getSession();

        if (request.getRequestURI().contains("setlang")) {
            commandFactory.createCommand("setLanguage").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/index")) {
            commandFactory.createCommand("showIndexPage").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/logout")) {
            commandFactory.createCommand("logout").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/flights")) {
            commandFactory.createCommand("showFlightsPage").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/shuttleFlights")) {
            commandFactory.createCommand("showShuttleFlightsPage").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/destinations")) {
            commandFactory.createCommand("showDestinationsPage").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/aircrafts")) {
            commandFactory.createCommand("showAircraftsPage").execute(request, response);
        }
        
        if (request.getRequestURI().endsWith("/flightPersonnel")) {
            commandFactory.createCommand("showEmployeesPage").execute(request, response);
        }

        if (request.getRequestURI().endsWith("/login")) {
            if (session.getAttribute("employee") != null) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/index.jsp");
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/view/login.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();

        if (request.getRequestURI().endsWith("/login")) {
            commandFactory.createCommand("login").execute(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
