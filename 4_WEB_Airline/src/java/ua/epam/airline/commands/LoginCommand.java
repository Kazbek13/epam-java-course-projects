package ua.epam.airline.commands;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.EmployeeDAO;
import ua.epam.airline.model.entities.Employee;

/**
 *
 * @author Andrew
 */
public class LoginCommand implements Command {

    static final Logger logger = LogManager.getLogger(EmployeeDAO.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String empLogin = request.getParameter("login");
        String empPassword = request.getParameter("password");
        EmployeeDAO empDAO = null;

        if (empLogin != null && empPassword != null) {
            try {
                try {
                    empDAO = DAOFactory.getEmployeeDAO();
                } catch (SQLException ex) {
                    logger.error("SQLException in LoginCommand::execute", ex);
                }
                Employee emp = new Employee();
                emp.setLogin(empLogin);
                emp.setPassword(empPassword);
                
                boolean isFound = empDAO.checkEmployeeLoginPassword(emp);
                
                if (isFound == true) {
                    Employee empInfo = empDAO.getEmployeeInfo(emp);
                    
                    HttpSession session = request.getSession(true);
                    session.setAttribute("employee", empInfo);
                    
                    String referer = request.getHeader("Referer");
                    try {
                        response.sendRedirect(referer);
                    } catch (IOException ex) {
                        logger.error("IOException in LoginCommand::execute", ex);
                    }
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/authentificationError.jsp");
                    try {
                        rd.forward(request, response);
                    } catch (ServletException ex) {
                        logger.error("ServletException in LoginCommand::execute", ex);
                    } catch (IOException ex) {
                        logger.error("IOException in LoginCommand::execute", ex);
                    }
                }
            } catch (SQLException ex) {
                logger.error("SQLException in LoginCommand::execute", ex);
            }
        }
    }

}
