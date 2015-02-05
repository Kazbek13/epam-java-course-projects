package ua.epam.airline.commands.flight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.AirportDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.model.entities.Airport;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowUpdateFlightCommand implements Command {
    static final Logger logger = LogManager.getLogger(ShowUpdateFlightCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        FlightDAO flightDAO;
        Flight flight = null;
        AirportDAO airportDAO;
        List<Airport> airports = null;
        
        try {
            flightDAO = DAOFactory.getFlightDAO();
            flight = flightDAO.getFlight(request.getParameter("flight"));
            airportDAO = DAOFactory.getAirportDAO();
            airports = airportDAO.getAllAirports();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowUpdateFlightCommand::execute", ex);
        }
        
        // tells render page if it is new flight instance creation or modification
        Boolean isUpdating = true;
        request.setAttribute("flightInstance", flight);
        request.setAttribute("isUpdating", isUpdating);
        request.setAttribute("airports", airports);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/flightInstance.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowUpdateFlightCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowUpdateFlightCommand::execute", ex);
        }
    }
    
}
