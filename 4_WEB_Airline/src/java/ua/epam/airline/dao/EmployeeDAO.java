package ua.epam.airline.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.model.entities.Employee;
import ua.epam.airline.utilites.ConnectionManager;

/**
 *
 * @author Andrew
 */
public class EmployeeDAO {
    static final Logger logger = LogManager.getLogger(EmployeeDAO.class);
    private Properties preparedSqlStatements;

    public EmployeeDAO() {
        preparedSqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try {
            preparedSqlStatements.load(inputStream);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException in EmployeeDAO::EmployeeDAO - can't open properties file", ex);
        } catch (IOException ex) {
            logger.error("IOException in EmployeeDAO::EmployeeDAO - can't read properties file", ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            logger.error("IOException in EmployeeDAO::EmployeeDAO - can't close properties file", ex);
        }
    }


    // flight employees - pilots, attendants, navigators, engineers, radiomen
    public List<Employee> getAllFlightEmployees() {
        List<Employee> flightEmployees = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_FLIGHT_EMP_ALL"));
            rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setId(rs.getInt("id_employee"));
                employee.setLogin(rs.getString("login"));
                employee.setName(rs.getString("name"));
                employee.setSurname(rs.getString("surname"));
                employee.setPosition(rs.getString("position"));

                flightEmployees.add(employee);
            }
        } catch (SQLException ex) {
            logger.error("SQLException in EmployeeDAO::getAllFlightEmployees", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in EmployeeDAO::getAllFlightEmployees", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in EmployeeDAO::getAllFlightEmployees", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in EmployeeDAO::getAllFlightEmployees", ex);
                }
            }
        }
        return flightEmployees;
    }
    
    public void createFlightEmployee(Employee employee) {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("INSERT_FLIGHT_EMP"));

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getSurname());
            ps.setString(3, employee.getPosition());

            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("SQLException in EmployeeDAO::createFlightEmployee", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in EmployeeDAO::createFlightEmployee", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in EmployeeDAO::createFlightEmployee", ex);
                }
            }
        }
    }
    
    public void deleteEmployee(int id) {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("DELETE_EMP"));

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("SQLException in EmployeeDAO::deleteEmployee", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in EmployeeDAO::deleteEmployee", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in EmployeeDAO::deleteEmployee", ex);
                }
            }
        }
    }
    
    public boolean checkEmployeeLoginPassword(Employee emp) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_EMP_BY_LOGIN_PWD"));

            ps.setString(1, emp.getLogin());
            ps.setString(2, emp.getPassword());
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            logger.error("SQLException in EmployeeDAO::checkEmployeeLoginPassword", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in EmployeeDAO::checkEmployeeLoginPassword", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in EmployeeDAO::checkEmployeeLoginPassword", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in EmployeeDAO::checkEmployeeLoginPassword", ex);
                }
            }
        }
        return false;
    }

    public Employee getEmployeeInfo(Employee emp) {
        Employee toReturn = new Employee();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_EMP_INFO_BY_LOGIN"));

            ps.setString(1, emp.getLogin());
            rs = ps.executeQuery();

            rs.next();
            toReturn.setId(rs.getInt("id_employee"));
            toReturn.setLogin(rs.getString("login"));
            toReturn.setName(rs.getString("name"));
            toReturn.setSurname(rs.getString("surname"));
            toReturn.setPosition(rs.getString("position"));
        } catch (SQLException ex) {
            logger.error("SQLException in EmployeeDAO::getEmployeeInfo", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in EmployeeDAO::getEmployeeInfo", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in EmployeeDAO::getEmployeeInfo", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in EmployeeDAO::getEmployeeInfo", ex);
                }
            }
        }
        return toReturn;
    }

}
