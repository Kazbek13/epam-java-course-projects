package ua.epam.airline.commands.aircraft;

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
public class ShowCreateAircraftCommand implements Command {
    
    static final Logger logger = LogManager.getLogger(ShowCreateAircraftCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/aircraftInstance.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowCreateAircraftCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowCreateAircraftCommand::execute", ex);
        }
    }
    
}
