package ua.epam.airline.commands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew
 */
public class ShowIndexPageCommand implements Command {

    static final Logger logger = LogManager.getLogger(ShowIndexPageCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowIndexPageCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowIndexPageCommand::execute", ex);
        }
    }
    
}
