package ua.epam.airline.commands.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.EmployeeDAO;
import ua.epam.airline.model.entities.Employee;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class CreateEmployeeInstanceCommand implements Command {
    static final Logger logger = LogManager.getLogger(CreateEmployeeInstanceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDAO employeeDAO;
        Employee employee = new Employee();
        
        employee.setName(request.getParameter("name"));
        employee.setSurname(request.getParameter("surname"));
        employee.setPosition(request.getParameter("position"));
        
        try {
            employeeDAO = DAOFactory.getEmployeeDAO();
            employeeDAO.createFlightEmployee(employee);
        } catch (SQLException ex) {
            logger.error("SQLException in CreateEmployeeInstanceCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("flightPersonnel");
        } catch (IOException ex) {
            logger.error("IOException in CreateEmployeeInstanceCommand::execute", ex);
        }
    }
    
}
