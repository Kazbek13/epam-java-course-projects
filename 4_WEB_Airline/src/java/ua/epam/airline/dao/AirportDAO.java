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
import ua.epam.airline.exceptions.AirportAlreadyExistsException;
import ua.epam.airline.model.entities.Airport;
import ua.epam.airline.utilites.ConnectionManager;

/**
 *
 * @author Andrew
 */
public class AirportDAO {

    static final Logger logger = LogManager.getLogger(FlightDAO.class);
    private Properties preparedSqlStatements;

    public AirportDAO() {
        preparedSqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try {
            preparedSqlStatements.load(inputStream);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException in AirportDAO::AirportDAO - can't open properties file", ex);
        } catch (IOException ex) {
            logger.error("IOException in AirportDAO::AirportDAO - can't read properties file", ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            logger.error("IOException in AirportDAO::AirportDAO - can't close properties file", ex);
        }
    }

    public List<Airport> getAllAirports() throws SQLException {
        List<Airport> airports = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_AIRPORT_ALL"));
            rs = ps.executeQuery();

            while (rs.next()) {
                Airport airport = new Airport();

                airport.setIdIATACode(rs.getString("id_airport"));
                airport.setTitle(rs.getString("title"));
                airport.setCity(rs.getString("city"));
                airport.setCountry(rs.getString("country"));

                airports.add(airport);
            }
        } catch (SQLException ex) {
            logger.error("SQLException in AirportDAO::getAllAirports", ex);
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
        return airports;
    }

    public Airport getAirport(String iataCode) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;
        ResultSet rs = null;
        Airport airport = new Airport();

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_AIRPORT_BY_IATA"));
            ps.setString(1, iataCode);
            rs = ps.executeQuery();

            rs.next();

            airport.setIdIATACode(rs.getString("id_airport"));
            airport.setTitle(rs.getString("title"));
            airport.setCity(rs.getString("city"));
            airport.setCountry(rs.getString("country"));

        } catch (SQLException ex) {
            logger.error("SQLException in AirportDAO::getAirport", ex);
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

        return airport;
    }

    public void createAirport(Airport airport) throws SQLException, AirportAlreadyExistsException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("INSERT_AIRPORT"));

            ps.setString(1, airport.getIdIATACode());
            ps.setString(2, airport.getTitle());
            ps.setString(3, airport.getCity());
            ps.setString(4, airport.getCountry());

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new AirportAlreadyExistsException("SQLIntegrityConstraintViolationException in AirportDAO::createAirport (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            logger.error("SQLException in AirportDAO::createAirport", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void updateAirport(Airport airport, String oldIataCode) throws SQLException, AirportAlreadyExistsException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("UPDATE_AIRPORT_BY_IATA"));

            ps.setString(1, airport.getIdIATACode());
            ps.setString(2, airport.getTitle());
            ps.setString(3, airport.getCity());
            ps.setString(4, airport.getCountry());
            ps.setString(5, oldIataCode);

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new AirportAlreadyExistsException("SQLIntegrityConstraintViolationException in AirportDAO::createAirport (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            logger.error("SQLException in AirportDAO::updateAirport", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void deleteAirport(String iataCode) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("DELETE_AIRPORT_BY_IATA"));

            ps.setString(1, iataCode);

            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("SQLException in AirportDAO::deleteAirport", ex);
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
