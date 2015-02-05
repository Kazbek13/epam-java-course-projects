package ua.epam.airline.commands.aircraft;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.AircraftDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class DeleteAircraftCommand implements Command {
    
    static final Logger logger = LogManager.getLogger(DeleteAircraftCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AircraftDAO aircraftDAO;
        
        try {
            aircraftDAO = DAOFactory.getAircraftDAO();
            aircraftDAO.deleteAircraft(request.getParameter("aircraft"));
        } catch (SQLException ex) {
            logger.error("SQLException in DeleteAircraftCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("aircrafts");
        } catch (IOException ex) {
            logger.error("IOException in DeleteAircraftCommand::execute", ex);
        }
    }
    
}
