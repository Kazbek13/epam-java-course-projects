package ua.epam.airline.commands.shuttleflight;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.AircraftDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.model.entities.Aircraft;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowCreateShuttleFlightCommand implements Command {

    static final Logger logger = LogManager.getLogger(ShowCreateShuttleFlightCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        
        FlightDAO flightDAO;
        AircraftDAO aircraftDAO;
        List<Flight> flights = null;
        List<Aircraft> aircrafts = null;
        
        try {
            flightDAO = DAOFactory.getFlightDAO();
            aircraftDAO = DAOFactory.getAircraftDAO();
            flights = flightDAO.getAllFlights();
            aircrafts = aircraftDAO.getAllAircrafts();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowCreateShuttleFlightCommand::execute", ex);
        }
       
        request.setAttribute("flights", flights);
        request.setAttribute("aircrafts", aircrafts);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/shuttleFlightInstance.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowCreateShuttleFlightCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowCreateShuttleFlightCommand::execute", ex);
        }
    }
    
}
