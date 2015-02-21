package ua.epam.airline.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.NotUniqueCabinCrewMembersException;
import ua.epam.airline.exceptions.ShuttleFlightAlreadyExistsException;
import ua.epam.airline.model.entities.Aircraft;
import ua.epam.airline.model.entities.Employee;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.model.entities.ShuttleFlight;
import ua.epam.airline.utilites.ConnectionManager;

/**
 *
 * @author Andrew
 */
public class ShuttleFlightDAO {

    static final Logger logger = LogManager.getLogger(ShuttleFlightDAO.class);
    private Properties preparedSqlStatements;

    public ShuttleFlightDAO() {
        preparedSqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try {
            preparedSqlStatements.load(inputStream);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException in ShuttleFlightDAO::ShuttleFlightDAO - can't open properties file", ex);
        } catch (IOException ex) {
            logger.error("IOException in ShuttleFlightDAO::ShuttleFlightDAO - can't read properties file", ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            logger.error("IOException in ShuttleFlightDAO::ShuttleFlightDAO - can't close properties file", ex);
        }
    }

    public List<ShuttleFlight> getAllShuttleFlights() {
        List<ShuttleFlight> shuttleFlights = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_SHUTTLE_FLIGHT_ALL"));
            rs = ps.executeQuery();

            while (rs.next()) {
                ShuttleFlight shuttleFlight = new ShuttleFlight();

                Aircraft aircraft = new Aircraft();
                aircraft.setIdReg(rs.getString("aircraft_reg"));

                Flight flight = new Flight();
                flight.setFlightNumber(rs.getString("id_flight_num"));

                shuttleFlight.setAircraft(aircraft);
                shuttleFlight.setIdFlight(flight);
                shuttleFlight.setIdFlightDate(rs.getDate("id_flight_date"));

                ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_CABIN_CREW_BY_FNUMBER_DATE"));
                ps.setDate(1, rs.getDate("id_flight_date"));
                ps.setString(2, rs.getString("id_flight_num"));
                rs1 = ps.executeQuery();

                if (rs1.next()) {
                    shuttleFlight.setHasCabinCrew(true);
                }
                shuttleFlights.add(shuttleFlight);
            }
        } catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::getAllShuttleFlights", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::getAllShuttleFlights", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in ShuttleFlightDAO::getAllShuttleFlights", ex);
                }
            }
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in ShuttleFlightDAO::getAllShuttleFlights", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::getAllShuttleFlights", ex);
                }
            }
        }
        return shuttleFlights;
    }

    public boolean isFreeAircraft(Date departureDate, Time departureTime, Date arrivalDate, Time arrivalTime, String aircraftReg) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        
        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_SHUTTLE_FLIGHT_BY_DATETIME_AIRCRAFT"));

            ps.setDate(1, departureDate);
            ps.setTime(2, departureTime);
            ps.setDate(3, arrivalDate);
            ps.setTime(4, arrivalTime);
            ps.setDate(5, departureDate);
            ps.setTime(6, departureTime);
            ps.setDate(7, arrivalDate);
            ps.setTime(8, arrivalTime);
            ps.setDate(9, departureDate);
            ps.setTime(10, departureTime);
            ps.setDate(11, arrivalDate);
            ps.setTime(12, arrivalTime);
            ps.setString(13, aircraftReg);

            rs = ps.executeQuery();
            
            if (rs.next()) {
                return false;
            }
        }  catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::isFreeAircraft", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::isFreeAircraft", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in ShuttleFlightDAO::isFreeAircraft", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::isFreeAircraft", ex);
                }
            }
        }
              
        return true;
    }
    
    public void createShuttleFlight(Date date, String flightNumber, String aircraftReg) throws ShuttleFlightAlreadyExistsException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("INSERT_SHUTTLE_FLIGHT"));

            ps.setDate(1, date);
            ps.setString(2, aircraftReg);
            ps.setString(3, flightNumber);

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new ShuttleFlightAlreadyExistsException("SQLIntegrityConstraintViolationException in ShuttleFlightDAO::createShuttleFlight (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::createShuttleFlight", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::createShuttleFlight", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::createShuttleFlight", ex);
                }
            }
        }
    }
    
    public List<Employee> getAssignedCrewByPosition(Date date, String flightNumber, String position) {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_ASSIGNED_CABIN_CREW"));
            ps.setDate(1, date);
            ps.setString(2, flightNumber);
            ps.setString(3, position);

            rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setName(rs.getString("name"));
                employee.setSurname(rs.getString("surname"));
                employee.setId(rs.getInt("id_employee"));
                employees.add(employee);

            }
        } catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::getAssignedCrewByPosition", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::getAssignedCrewByPosition", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in ShuttleFlightDAO::getAssignedCrewByPosition", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::getAssignedCrewByPosition", ex);
                }
            }
        }

        return employees;
    }

    public Aircraft getAssignedAircraft(Date date, String flightNumber) {
        Aircraft aircraft = new Aircraft();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_ASSIGNED_AIRCRAFT"));

            ps.setDate(1, date);
            ps.setString(2, flightNumber);
            rs = ps.executeQuery();

            rs.next();
            aircraft.setIdReg(rs.getString("id_aircraft_reg"));
            aircraft.setManufacturer(rs.getString("manufacturer"));
            aircraft.setModelType(rs.getString("model_type"));
            aircraft.setNumSeats(rs.getInt("num_seats"));
            aircraft.setNumPilots(rs.getInt("num_pilots"));
            aircraft.setNumNavigators(rs.getInt("num_navigators"));
            aircraft.setNumRadiomen(rs.getInt("num_radiomen"));
            aircraft.setNumEngineers(rs.getInt("num_engineers"));
            aircraft.setNumFlightAttendants(rs.getInt("num_attendants"));

        } catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::hasCabinCrew", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::hasCabinCrew", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in ShuttleFlightDAO::hasCabinCrew", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::hasCabinCrew", ex);
                }
            }
        }

        return aircraft;
    }

    public List<Employee> getFreeCabinCrewByPosition(Date departureDate, Time departureTime, Date arrivalDate, Time arrivalTime, String position) {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_FREE_CABIN_CREW"));
            ps.setDate(1, departureDate);
            ps.setTime(2, departureTime);
            ps.setDate(3, arrivalDate);
            ps.setTime(4, arrivalTime);
            ps.setDate(5, departureDate);
            ps.setTime(6, departureTime);
            ps.setDate(7, arrivalDate);
            ps.setTime(8, arrivalTime);
            ps.setDate(9, departureDate);
            ps.setTime(10, departureTime);
            ps.setDate(11, arrivalDate);
            ps.setTime(12, arrivalTime);
            ps.setString(13, position);

            rs = ps.executeQuery();

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setName(rs.getString("name"));
                employee.setSurname(rs.getString("surname"));
                employee.setId(rs.getInt("id_employee"));
                employees.add(employee);

            }
        } catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::getFreeCabinCrewByPosition", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::getFreeCabinCrewByPosition", ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    logger.error("Close ResultSet SQLException in ShuttleFlightDAO::getFreeCabinCrewByPosition", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::getFreeCabinCrewByPosition", ex);
                }
            }
        }
        return employees;
    }

    public void assignCabinCrewOnShuttleFlight(List<Integer> employeeId, Date date, String flightNumber) throws NotUniqueCabinCrewMembersException {
        PreparedStatement ps = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
            
            // at first, delete all previously assigned crew
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("DELETE_ASSIGNED_CABIN_CREW"));
            ps.setDate(1, date);
            ps.setString(2, flightNumber);
            ps.executeUpdate();
            
            // now, assign new crew
            for (Integer id: employeeId) {
                ps = connection.prepareStatement(preparedSqlStatements.getProperty("INSERT_CABIN_CREW"));
                
                ps.setInt(1, id);
                ps.setDate(2, date);
                ps.setString(3, flightNumber);
                
                ps.executeUpdate();
            }
            
            // if everything above is successfull, then commit
            connection.commit();
           
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new NotUniqueCabinCrewMembersException("SQLIntegrityConstraintViolationException in ShuttleFlightDAO::assignCabinCrewOnShuttleFlight (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            try {
                connection.rollback();
                logger.error("SQLException in ShuttleFlightDAO::assignCabinCrewOnShuttleFlight", ex);
            } catch (SQLException ex1) {
                logger.error("Rollback SQLException in ShuttleFlightDAO::assignCabinCrewOnShuttleFlight", ex1);
            }
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::assignCabinCrewOnShuttleFlight", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::assignCabinCrewOnShuttleFlight", ex);
                }
            }
        }
    }
    
    public void deleteShuttleFlight(Date date, String flightNumber) {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("DELETE_SHUTTLE_FLIGHT"));

            ps.setDate(1, date);
            ps.setString(2, flightNumber);

            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("SQLException in ShuttleFlightDAO::deleteShuttleFlight", ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    logger.error("Close PreparedStatement SQLException in ShuttleFlightDAO::deleteShuttleFlight", ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    logger.error("Close Connection SQLException in ShuttleFlightDAO::deleteShuttleFlight", ex);
                }
            }
        }
    }
}
