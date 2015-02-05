package ua.epam.airline.utilites;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Andrew
 */
public class ConnectionManager {
    private static DataSource dataSource;

    static {
        InitialContext initialContext;
        try {
            initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/airline");
        } catch (NamingException ex) {
            //Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    public static synchronized Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
