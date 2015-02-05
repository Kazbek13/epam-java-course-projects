package ua.epam.airline.commands;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Andrew
 */
public class SetLanguageCommand implements Command {

    static final Logger logger = LogManager.getLogger(SetLanguageCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        
        if (request.getParameter("lang").equals("en") || request.getParameter("lang").equals("ru")) {
            Config.set(session, Config.FMT_LOCALE, new java.util.Locale(request.getParameter("lang")));
        }
            
        String referer = request.getHeader("Referer");
        try {
            response.sendRedirect(referer);
        } catch (IOException ex) {
            logger.error("IOException in SetLanguageCommand::execute", ex);
        }
    }
    
}
