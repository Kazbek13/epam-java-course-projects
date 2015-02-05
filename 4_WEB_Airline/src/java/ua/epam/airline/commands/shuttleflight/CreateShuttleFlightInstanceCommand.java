package ua.epam.airline.commands.shuttleflight;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.ShuttleFlightAlreadyExistsException;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.dao.ShuttleFlightDAO;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class CreateShuttleFlightInstanceCommand implements Command {

    static final Logger logger = LogManager.getLogger(CreateShuttleFlightInstanceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ShuttleFlightDAO shuttleFlightDAO = null;
        FlightDAO flightDAO;
        Flight flight = null;
        Date departureDate = Date.valueOf(request.getParameter("date"));

        try {
            shuttleFlightDAO = DAOFactory.getShuttleFlightDAO();
            flightDAO = DAOFactory.getFlightDAO();
            flight = flightDAO.getFlight(request.getParameter("flight"));      
        } catch (SQLException ex) {
            logger.error("SQLException in CreateShuttleFlightInstanceCommand::execute", ex);
        }

        // manipulating time and data for finding free employees
        Calendar calendarDep = new GregorianCalendar();
        Calendar calendarDepTime = new GregorianCalendar();
        Calendar calendarFlightTime = new GregorianCalendar();

        calendarDepTime.setTime(flight.getDepartureTime());
        calendarDep.setTime(departureDate);
        calendarDep.set(Calendar.HOUR_OF_DAY, calendarDepTime.get(Calendar.HOUR_OF_DAY));
        calendarDep.set(Calendar.MINUTE, calendarDepTime.get(Calendar.MINUTE));

        calendarFlightTime.setTime(flight.getArrivalTime());
        calendarFlightTime.add(Calendar.HOUR_OF_DAY, -calendarDepTime.get(Calendar.HOUR_OF_DAY));
        calendarFlightTime.add(Calendar.MINUTE, -calendarDepTime.get(Calendar.MINUTE));

        calendarDep.add(Calendar.HOUR_OF_DAY, calendarFlightTime.get(Calendar.HOUR_OF_DAY));
        calendarDep.add(Calendar.MINUTE, calendarFlightTime.get(Calendar.MINUTE));

        Time arrivalTime = new Time(calendarDep.getTime().getTime());
        Date arrivalDate = new Date(calendarDep.getTime().getTime());

        boolean isFreeAircraft = false;
        try {
            isFreeAircraft = shuttleFlightDAO.isFreeAircraft(departureDate, flight.getDepartureTime(), arrivalDate, arrivalTime, request.getParameter("aircraft"));
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CreateShuttleFlightInstanceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isFreeAircraft == true) {

            try {
                shuttleFlightDAO.createShuttleFlight(Date.valueOf(request.getParameter("date")), request.getParameter("flight"), request.getParameter("aircraft"));
            } catch (ShuttleFlightAlreadyExistsException ex) {
                logger.warn(ex.getMessage(), ex.getCause());

                request.setAttribute("err", "crud_error.flight_instance_already_exists");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/crudError.jsp");

                try {
                    rd.forward(request, response);
                } catch (ServletException ex1) {
                    logger.error("ServletException in CreateShuttleFlightInstanceCommand::execute", ex1);
                } catch (IOException ex1) {
                    logger.error("IOException in CreateShuttleFlightInstanceCommand::execute", ex1);
                }

            } catch (SQLException ex) {
                logger.error("SQLException in CreateShuttleFlightInstanceCommand::execute", ex);
            }

            try {
                response.sendRedirect("shuttleFlights");
            } catch (IOException ex) {
                logger.error("IOException in CreateShuttleFlightInstanceCommand::execute", ex);
            }
        } else {
            request.setAttribute("err", "crud_error.aircraft_is_busy");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/crudError.jsp");
            
            try {
                rd.forward(request, response);
            } catch (ServletException ex1) {
                logger.error("ServletException in CreateShuttleFlightInstanceCommand::execute", ex1);
            } catch (IOException ex1) {
                logger.error("IOException in CreateShuttleFlightInstanceCommand::execute", ex1);
            }
        }
    }

}
