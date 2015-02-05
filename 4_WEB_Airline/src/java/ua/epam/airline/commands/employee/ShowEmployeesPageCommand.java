package ua.epam.airline.commands.employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.AirportDAO;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.EmployeeDAO;
import ua.epam.airline.model.entities.Employee;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class ShowEmployeesPageCommand implements Command {
    static final Logger logger = LogManager.getLogger(ShowEmployeesPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employeeList = null;
        EmployeeDAO employeeDAO;
        
        try {
            employeeDAO = DAOFactory.getEmployeeDAO();
            employeeList = employeeDAO.getAllFlightEmployees();
        } catch (SQLException ex) {
            logger.error("SQLException in ShowEmployeesPageCommand::execute", ex);
        }
        
        request.setAttribute("employees", employeeList);
        
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/flightPersonnel.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in ShowEmployeesPageCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShowEmployeesPageCommand::execute", ex);
        }
    }
}
