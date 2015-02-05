package ua.epam.airline.commands.airport;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.AirportAlreadyExistsException;
import ua.epam.airline.dao.AirportDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.model.entities.Airport;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class UpdateAirportInstanceCommand implements Command {

    static final Logger logger = LogManager.getLogger(UpdateAirportInstanceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AirportDAO airportDAO;
        Airport airport = new Airport();

        airport.setIdIATACode(request.getParameter("idIATACode"));
        airport.setTitle(request.getParameter("title"));
        airport.setCity(request.getParameter("city"));
        airport.setCountry(request.getParameter("country"));

        try {
            airportDAO = DAOFactory.getAirportDAO();
            try {
                airportDAO.updateAirport(airport, request.getParameter("oldIATACode"));
            } catch (AirportAlreadyExistsException ex) {
                logger.warn(ex.getMessage(), ex.getCause());

                request.setAttribute("err", "crud_error.airport_already_exists");
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/crudError.jsp");

                try {
                    rd.forward(request, response);
                } catch (ServletException ex1) {
                    logger.error("ServletException in UpdateAirportInstanceCommand::execute", ex1);
                } catch (IOException ex1) {
                    logger.error("IOException in UpdateAirportInstanceCommand::execute", ex1);
                }
            }
        } catch (SQLException ex) {
            logger.error("SQLException in UpdateAirportInstanceCommand::execute", ex);
        }

        try {
            response.sendRedirect("destinations");
        } catch (IOException ex) {
            logger.error("IOException in UpdateAirportInstanceCommand::execute", ex);
        }
    }

}
