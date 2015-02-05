package ua.epam.airline.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.FlightAlreadyExistsException;
import ua.epam.airline.model.entities.Airport;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.utilites.ConnectionManager;

/**
 *
 * @author Andrew
 */
public class FlightDAO {

    static final Logger logger = LogManager.getLogger(FlightDAO.class);
    private Properties preparedSqlStatements;

    public FlightDAO() {
        preparedSqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try {
            preparedSqlStatements.load(inputStream);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException in FlightDAO::FlightDAO - can't open properties file", ex);
        } catch (IOException ex) {
            logger.error("IOException in FlightDAO::FlightDAO - can't read properties file", ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            logger.error("IOException in FlightDAO::FlightDAO - can't close properties file", ex);
        }
    }

    public List<Flight> getAllFlights() throws SQLException {
        List<Flight> flights = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_FLIGHT_ALL"));
            rs = ps.executeQuery();

            while (rs.next()) {
                Flight flight = new Flight();
                Airport departureAirport = new Airport();
                Airport arrivalAirport = new Airport();

                departureAirport.setIdIATACode(rs.getString("dep_airport"));
                departureAirport.setTitle(rs.getString("dep_title"));
                departureAirport.setCity(rs.getString("dep_city"));
                departureAirport.setCountry(rs.getString("dep_country"));

                arrivalAirport.setIdIATACode(rs.getString("arr_airport"));
                arrivalAirport.setTitle(rs.getString("arr_title"));
                arrivalAirport.setCity(rs.getString("arr_city"));
                arrivalAirport.setCountry(rs.getString("arr_country"));

                flight.setFlightNumber(rs.getString("id_flight_num"));
                flight.setDepartureTime(rs.getTime("dep_time"));
                flight.setArrivalTime(rs.getTime("arr_time"));
                flight.setDepartureAirport(departureAirport);
                flight.setArrivalAirport(arrivalAirport);

                flights.add(flight);
            }
        } catch (SQLException ex) {
            logger.error("SQLException in FlightDAO::getAllFlights", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return flights;
    }

    public Flight getFlight(String flightNumber) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;
        Flight flight = null;
        Airport departureAirport = new Airport();
        Airport arrivalAirport = new Airport();

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_FLIGHT_BY_FNUMBER"));
            ps.setString(1, flightNumber);
            rs = ps.executeQuery();

            if (rs.next()) {

                flight = new Flight();
                departureAirport.setIdIATACode(rs.getString("dep_airport"));
                departureAirport.setTitle(rs.getString("dep_title"));
                departureAirport.setCity(rs.getString("dep_city"));
                departureAirport.setCountry(rs.getString("dep_country"));

                arrivalAirport.setIdIATACode(rs.getString("arr_airport"));
                arrivalAirport.setTitle(rs.getString("arr_title"));
                arrivalAirport.setCity(rs.getString("arr_city"));
                arrivalAirport.setCountry(rs.getString("arr_country"));

                flight.setFlightNumber(rs.getString("id_flight_num"));
                flight.setDepartureTime(rs.getTime("dep_time"));
                flight.setArrivalTime(rs.getTime("arr_time"));
                flight.setDepartureAirport(departureAirport);
                flight.setArrivalAirport(arrivalAirport);
            }
        } catch (SQLException ex) {
            logger.error("SQLException in FlightDAO::getFlight", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return flight;
    }

    public void createFlight(Flight flight) throws SQLException, FlightAlreadyExistsException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("INSERT_FLIGHT"));

            ps.setString(1, flight.getFlightNumber());
            ps.setTime(2, flight.getDepartureTime());
            ps.setTime(3, flight.getArrivalTime());
            ps.setString(4, flight.getDepartureAirport().getIdIATACode());
            ps.setString(5, flight.getArrivalAirport().getIdIATACode());

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new FlightAlreadyExistsException("SQLIntegrityConstraintViolationException in FlightDAO::createFlight (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            logger.error("SQLException in FlightDAO::createFlight", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateFlight(Flight flight, String oldFlightNumber) throws SQLException, FlightAlreadyExistsException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("UPDATE_FLIGHT_BY_FNUMBER"));

            ps.setString(1, flight.getFlightNumber());
            ps.setTime(2, flight.getDepartureTime());
            ps.setTime(3, flight.getArrivalTime());
            ps.setString(4, flight.getDepartureAirport().getIdIATACode());
            ps.setString(5, flight.getArrivalAirport().getIdIATACode());
            ps.setString(6, oldFlightNumber);

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new FlightAlreadyExistsException("SQLIntegrityConstraintViolationException in FlightDAO::updateFlight (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            logger.error("SQLException in FlightDAO::updateFlight", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteFlight(String flightNumber) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("DELETE_FLIGHT_BY_FNUMBER"));

            ps.setString(1, flightNumber);

            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("SQLException in FlightDAO::deleteFlight", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
