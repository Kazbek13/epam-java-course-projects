package ua.epam.airline.dao;

import java.sql.SQLException;

/**
 *
 * @author Andrew
 */
public class DAOFactory {

    public static EmployeeDAO getEmployeeDAO() throws SQLException {
        EmployeeDAO empDAO = new EmployeeDAO();

        return empDAO;
    }
    
    public static FlightDAO getFlightDAO() throws SQLException {
        FlightDAO flightDAO = new FlightDAO();

        return flightDAO;
    }
    
    public static AirportDAO getAirportDAO() throws SQLException {
        AirportDAO airportDAO = new AirportDAO();

        return airportDAO;
    }
    
    public static ShuttleFlightDAO getShuttleFlightDAO() throws SQLException {
        ShuttleFlightDAO shuttleFlightDAO = new ShuttleFlightDAO();

        return shuttleFlightDAO;
    }
    
    public static AircraftDAO getAircraftDAO() throws SQLException {
        AircraftDAO aircraftDAO = new AircraftDAO();

        return aircraftDAO;
    }
}
