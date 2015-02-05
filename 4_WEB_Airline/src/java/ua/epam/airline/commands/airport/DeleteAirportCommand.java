package ua.epam.airline.commands.airport;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.AirportDAO;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class DeleteAirportCommand implements Command {

    static final Logger logger = LogManager.getLogger(DeleteAirportCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AirportDAO airportDAO;
        
        try {
            airportDAO = DAOFactory.getAirportDAO();
            airportDAO.deleteAirport(request.getParameter("airport"));
        } catch (SQLException ex) {
            logger.error("SQLException in DeleteAirportCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("destinations");
        } catch (IOException ex) {
            logger.error("IOException in DeleteAirportCommand::execute", ex);
        }
    }
    
}
