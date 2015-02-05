package ua.epam.airline.commands.airport;

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
import ua.epam.airline.model.entities.Airport;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowDestinationsPageCommand implements Command {
    
    static Logger logger = LogManager.getLogger(ShowDestinationsPageCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        
        List<Airport> airportList = null;
        AirportDAO airportDAO;
        
        try {
            airportDAO = DAOFactory.getAirportDAO();
            airportList = airportDAO.getAllAirports();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowDestinationsPageCommand::execute", ex);
        }
        
        request.setAttribute("airports", airportList);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/destinations.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowDestinationsPageCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowDestinationsPageCommand::execute", ex);
        }
    }
}
