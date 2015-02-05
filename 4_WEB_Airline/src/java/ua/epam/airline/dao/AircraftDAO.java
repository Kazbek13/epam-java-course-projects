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
import ua.epam.airline.exceptions.AircraftAlreadyExistsException;
import ua.epam.airline.model.entities.Aircraft;
import ua.epam.airline.utilites.ConnectionManager;

/**
 *
 * @author Andrew
 */
public class AircraftDAO {

    static final Logger logger = LogManager.getLogger(FlightDAO.class);
    private Properties preparedSqlStatements;

    public AircraftDAO() {
        preparedSqlStatements = new Properties();
        InputStream inputStream = this.getClass().getResourceAsStream("/sql.properties");
        try {
            preparedSqlStatements.load(inputStream);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException in AircraftDAO::AircraftDAO - can't open properties file", ex);
        } catch (IOException ex) {
            logger.error("IOException in AircraftDAO::AircraftDAO - can't read properties file", ex);
        }
        try {
            inputStream.close();
        } catch (IOException ex) {
            logger.error("IOException in AircraftDAO::AircraftDAO - can't close properties file", ex);
        }
    }

    public List<Aircraft> getAllAircrafts() throws SQLException {
        List<Aircraft> aircrafts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("SELECT_AIRCRAFT_ALL"));
            rs = ps.executeQuery();

            while (rs.next()) {
                Aircraft aircraft = new Aircraft();

                aircraft.setIdReg(rs.getString("id_aircraft_reg"));
                aircraft.setManufacturer(rs.getString("manufacturer"));
                aircraft.setModelType(rs.getString("model_type"));
                aircraft.setNumSeats(rs.getInt("num_seats"));
                aircraft.setNumPilots(rs.getInt("num_pilots"));
                aircraft.setNumNavigators(rs.getInt("num_navigators"));
                aircraft.setNumRadiomen(rs.getInt("num_radiomen"));
                aircraft.setNumEngineers(rs.getInt("num_engineers"));
                aircraft.setNumFlightAttendants(rs.getInt("num_attendants"));

                aircrafts.add(aircraft);
            }
        } catch (SQLException ex) {
            logger.error("SQLException in AircraftDAO::getAllAircrafts", ex);
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
        return aircrafts;
    }

    public void createAircraft(Aircraft aircraft) throws SQLException, AircraftAlreadyExistsException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("INSERT_AIRCRAFT"));

            ps.setString(1, aircraft.getIdReg());
            ps.setString(2, aircraft.getManufacturer());
            ps.setString(3, aircraft.getModelType());
            ps.setInt(4, aircraft.getNumSeats());
            ps.setInt(5, aircraft.getNumPilots());
            ps.setInt(6, aircraft.getNumNavigators());
            ps.setInt(7, aircraft.getNumRadiomen());
            ps.setInt(8, aircraft.getNumEngineers());
            ps.setInt(9, aircraft.getNumFlightAttendants());

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new AircraftAlreadyExistsException("SQLIntegrityConstraintViolationException in AircraftDAO::createAircraft (inserting duplicate key)", ex);
        } catch (SQLException ex) {
            logger.error("SQLException in AircraftDAO::createAircraft", ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
    public void deleteAircraft(String regNum) throws SQLException {
        PreparedStatement ps = null;
        Connection connection = null;

        try {
            connection = ConnectionManager.getConnection();
            ps = connection.prepareStatement(preparedSqlStatements.getProperty("DELETE_AIRCRAFT_BY_REG"));

            ps.setString(1, regNum);

            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.error("SQLException in AircraftDAO::deleteAircraft", ex);
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
