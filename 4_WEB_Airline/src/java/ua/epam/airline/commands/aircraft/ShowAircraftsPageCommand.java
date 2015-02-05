package ua.epam.airline.commands.aircraft;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.AircraftDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.model.entities.Aircraft;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowAircraftsPageCommand implements Command {

    static final Logger logger = LogManager.getLogger(ShowAircraftsPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<Aircraft> aircraftList = null;
        AircraftDAO aircraftDAO;

        try {
            aircraftDAO = DAOFactory.getAircraftDAO();
            aircraftList = aircraftDAO.getAllAircrafts();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowAircraftsPageCommand::execute", ex);
        }

        request.setAttribute("aircrafts", aircraftList);

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/aircrafts.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowAircraftsPageCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowAircraftsPageCommand::execute", ex);
        }
    }

}
