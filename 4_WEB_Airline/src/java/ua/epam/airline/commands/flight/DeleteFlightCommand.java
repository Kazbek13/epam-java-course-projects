package ua.epam.airline.commands.flight;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class DeleteFlightCommand implements Command {

    static final Logger logger = LogManager.getLogger(DeleteFlightCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        FlightDAO flightDAO;
        
        try {
            flightDAO = DAOFactory.getFlightDAO();
            flightDAO.deleteFlight(request.getParameter("flight"));
        } catch (SQLException ex) {
            logger.error("SQLException in DeleteFlightCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("flights");
        } catch (IOException ex) {
            logger.error("IOException in DeleteFlightCommand::execute", ex);
        }
    }
    
}
