package ua.epam.airline.commands.aircraft;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.AircraftAlreadyExistsException;
import ua.epam.airline.dao.AircraftDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.model.entities.Aircraft;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class CreateAircraftInstanceCommand implements Command {

    static final Logger logger = LogManager.getLogger(CreateAircraftInstanceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AircraftDAO aircraftDAO;
        Aircraft aircraft = new Aircraft();

        aircraft.setIdReg(request.getParameter("idReg"));
        aircraft.setManufacturer(request.getParameter("manufacturer"));
        aircraft.setModelType(request.getParameter("modelType"));
        aircraft.setNumSeats(Integer.parseInt(request.getParameter("numSeats")));
        aircraft.setNumPilots(Integer.parseInt(request.getParameter("numPilots")));
        aircraft.setNumEngineers(Integer.parseInt(request.getParameter("numEngineers")));
        aircraft.setNumNavigators(Integer.parseInt(request.getParameter("numNavigators")));
        aircraft.setNumRadiomen(Integer.parseInt(request.getParameter("numRadiomen")));
        aircraft.setNumFlightAttendants(Integer.parseInt(request.getParameter("numFlightAttendants")));

        try {
            aircraftDAO = DAOFactory.getAircraftDAO();
            try {
                aircraftDAO.createAircraft(aircraft);
            } catch (AircraftAlreadyExistsException ex) {
                logger.warn(ex.getMessage(), ex.getCause());

                request.setAttribute("err", "crud_error.aircraft_already_exists");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/crudError.jsp");

                try {
                    rd.forward(request, response);
                } catch (ServletException ex1) {
                    logger.error("ServletException in CreateAircraftInstanceCommand::execute", ex1);
                } catch (IOException ex1) {
                    logger.error("IOException in CreateAircraftInstanceCommand::execute", ex1);
                }
            }
        } catch (SQLException ex) {
            logger.error("SQLException in CreateAircraftInstanceCommand::execute", ex);
        }

        try {
            response.sendRedirect("aircrafts");
        } catch (IOException ex) {
            logger.error("IOException in CreateAircraftInstanceCommand::execute", ex);
        }
    }

}
