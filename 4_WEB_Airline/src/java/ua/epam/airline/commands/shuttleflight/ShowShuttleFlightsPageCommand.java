package ua.epam.airline.commands.shuttleflight;

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
import ua.epam.airline.dao.ShuttleFlightDAO;
import ua.epam.airline.model.entities.ShuttleFlight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowShuttleFlightsPageCommand implements Command {

    static final Logger logger = LogManager.getLogger(ShowShuttleFlightsPageCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<ShuttleFlight> shuttleFlightList = null;
        ShuttleFlightDAO shuttleFlightDAO;
        
        try {
            shuttleFlightDAO = DAOFactory.getShuttleFlightDAO();
            shuttleFlightList = shuttleFlightDAO.getAllShuttleFlights();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowShuttleFlightsPageCommand::execute", ex);
        }
        
        request.setAttribute("shuttleFlights", shuttleFlightList);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/shuttleFlights.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowShuttleFlightsPageCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowShuttleFlightsPageCommand::execute", ex);
        }
    }
    
}
