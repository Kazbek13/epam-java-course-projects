package ua.epam.airline.commands.employee;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.EmployeeDAO;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class DeleteEmployeeCommand implements Command {
    static final Logger logger = LogManager.getLogger(DeleteEmployeeCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDAO employeeDAO;
        
        try {
            employeeDAO = DAOFactory.getEmployeeDAO();
            employeeDAO.deleteEmployee(Integer.parseInt(request.getParameter("id")));
        } catch (SQLException ex) {
            logger.error("SQLException in DeleteEmployeeCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("flightPersonnel");
        } catch (IOException ex) {
            logger.error("IOException in DeleteEmployeeCommand::execute", ex);
        }
    }
    
}
