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
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowFlightsPageCommand implements Command {
    static Logger logger = LogManager.getLogger(ShowFlightsPageCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        
        List<Flight> flightList = null;
        FlightDAO flightDAO;
        
        try {
            flightDAO = DAOFactory.getFlightDAO();
            flightList = flightDAO.getAllFlights();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowFlightsPageCommand::execute", ex);
        }
        
        
        request.setAttribute("flights", flightList);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/flights.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowFlightsPageCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowFlightsPageCommand::execute", ex);
        }
    }
    
}
