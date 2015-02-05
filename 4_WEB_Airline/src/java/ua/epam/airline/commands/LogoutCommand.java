package ua.epam.airline.commands;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew
 */
public class LogoutCommand implements Command {

    static final Logger logger = LogManager.getLogger(LogoutCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("employee") != null) {
            session.setAttribute("employee", null);
        }

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/index.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in LogoutCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("SQLException in LogoutCommand::execute", ex);
        }
    }

}
