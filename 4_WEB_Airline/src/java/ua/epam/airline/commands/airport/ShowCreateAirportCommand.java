package ua.epam.airline.commands.airport;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowCreateAirportCommand implements Command {

    static final Logger logger = LogManager.getLogger(ShowCreateAirportCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/airportInstance.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowCreateAirportCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowCreateAirportCommand::execute", ex);
        }
    }

}
