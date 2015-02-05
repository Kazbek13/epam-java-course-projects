package ua.epam.airline.commands.flight;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.FlightAlreadyExistsException;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.model.entities.Airport;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class UpdateFlightInstanceCommand implements Command {

    static final Logger logger = LogManager.getLogger(UpdateFlightInstanceCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        FlightDAO flightDAO;
        Flight flight = new Flight();
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Airport();

        departureAirport.setIdIATACode(request.getParameter("departureAirport"));
        arrivalAirport.setIdIATACode(request.getParameter("arrivalAirport"));

        flight.setFlightNumber(request.getParameter("flightNumber"));
        flight.setDepartureTime(Time.valueOf(request.getParameter("departureTime")));
        flight.setArrivalTime(Time.valueOf(request.getParameter("arrivalTime")));
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);

        try {
            flightDAO = DAOFactory.getFlightDAO();

            try {
                flightDAO.updateFlight(flight, request.getParameter("oldFlightNumber"));
            } catch (FlightAlreadyExistsException ex) {
                logger.warn(ex.getMessage(), ex.getCause());

                request.setAttribute("err", "crud_error.flight_already_exists");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/crudError.jsp");
                
                try {
                    rd.forward(request, response);
                } catch (ServletException ex1) {
                    logger.error("ServletException in UpdateFlightInstanceCommand::execute", ex1);
                } catch (IOException ex1) {
                    logger.error("IOException in UpdateFlightInstanceCommand::execute", ex1);
                }
            }

        } catch (SQLException ex) {
            logger.error("SQLException in UpdateFlightInstanceCommand::execute", ex);
        }

        try {
            response.sendRedirect("flights");
        } catch (IOException ex) {
            logger.error("IOException in UpdateFlightInstanceCommand::execute", ex);
        }
    }

}
