package ua.epam.airline.commands.airport;

import java.io.IOException;
import java.sql.SQLException;
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
public class ShowUpdateAirportCommand implements Command {

    static Logger logger = LogManager.getLogger(ShowUpdateAirportCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AirportDAO airportDAO;
        Airport airport = null;
        
        try {
            airportDAO = DAOFactory.getAirportDAO();
            airport = airportDAO.getAirport(request.getParameter("airport"));
        } catch (SQLException ex) {
            logger.error("SQLException in ShowUpdateAirportCommand::execute", ex);
        }
        
        // tells render page if it is new flight instance creation or modification
        Boolean isUpdating = true;
        request.setAttribute("airportInstance", airport);
        request.setAttribute("isUpdating", isUpdating);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/airportInstance.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowUpdateAirportCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowUpdateAirportCommand::execute", ex);
        }
    }
    
}
