package ua.epam.airline.commands.shuttleflight;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.ShuttleFlightDAO;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class DeleteShuttleFlightCommand implements Command {

    static final Logger logger = LogManager.getLogger(DeleteShuttleFlightCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ShuttleFlightDAO shuttleFlightDAO;
        
        try {
            shuttleFlightDAO = DAOFactory.getShuttleFlightDAO();
            shuttleFlightDAO.deleteShuttleFlight(Date.valueOf(request.getParameter("date")), request.getParameter("flight"));
        } catch (SQLException ex) {
            logger.error("SQLException in DeleteShuttleFlightCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("shuttleFlights");
        } catch (IOException ex) {
            logger.error("IOException in DeleteShuttleFlightCommand::execute", ex);
        }
    }
    
}
