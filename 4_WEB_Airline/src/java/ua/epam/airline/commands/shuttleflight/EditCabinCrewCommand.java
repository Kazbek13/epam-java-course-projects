package ua.epam.airline.commands.shuttleflight;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.FlightDAO;
import ua.epam.airline.dao.ShuttleFlightDAO;
import ua.epam.airline.model.entities.Aircraft;
import ua.epam.airline.model.entities.Employee;
import ua.epam.airline.model.entities.Flight;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class EditCabinCrewCommand implements Command {

    static final Logger logger = LogManager.getLogger(EditCabinCrewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Aircraft aircraft = null;
        List<Employee> freePilots = null;
        List<Employee> freeEngineers = null;
        List<Employee> freeRadiomen = null;
        List<Employee> freeNavigators = null;
        List<Employee> freeAttendants = null;
        Flight flight = null;
        ShuttleFlightDAO shuttleFlightDAO = null;
        FlightDAO flightDAO;
        Date departureDate = Date.valueOf(request.getParameter("date"));

        try {
            shuttleFlightDAO = DAOFactory.getShuttleFlightDAO();
            flightDAO = DAOFactory.getFlightDAO();
            flight = flightDAO.getFlight(request.getParameter("flight"));
            aircraft = shuttleFlightDAO.getAssignedAircraft(departureDate, request.getParameter("flight"));
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(EditCabinCrewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

        // manipulating time and data for finding free employees
        Calendar calendarDep = new GregorianCalendar();
        Calendar calendarDepTime = new GregorianCalendar();
        Calendar calendarFlightTime = new GregorianCalendar();

        calendarDepTime.setTime(flight.getDepartureTime());
        calendarDep.setTime(departureDate);
        calendarDep.set(Calendar.HOUR_OF_DAY, calendarDepTime.get(Calendar.HOUR_OF_DAY));
        calendarDep.set(Calendar.MINUTE, calendarDepTime.get(Calendar.MINUTE));

        calendarFlightTime.setTime(flight.getArrivalTime());
        calendarFlightTime.add(Calendar.HOUR_OF_DAY, -calendarDepTime.get(Calendar.HOUR_OF_DAY));
        calendarFlightTime.add(Calendar.MINUTE, -calendarDepTime.get(Calendar.MINUTE));

        calendarDep.add(Calendar.HOUR_OF_DAY, calendarFlightTime.get(Calendar.HOUR_OF_DAY));
        calendarDep.add(Calendar.MINUTE, calendarFlightTime.get(Calendar.MINUTE));

        Time arrivalTime = new Time(calendarDep.getTime().getTime());
        Date arrivalDate = new Date(calendarDep.getTime().getTime());

        // assigning lists of free employees
        try {
            if (aircraft.getNumPilots() > 0) {
                freePilots = shuttleFlightDAO.getFreeCabinCrewByPosition(departureDate, flight.getDepartureTime(), arrivalDate, arrivalTime, "pilot");
            }

            if (aircraft.getNumEngineers() > 0) {
                freeEngineers = shuttleFlightDAO.getFreeCabinCrewByPosition(departureDate, flight.getDepartureTime(), arrivalDate, arrivalTime, "engineer");
            }

            if (aircraft.getNumNavigators() > 0) {
                freeNavigators = shuttleFlightDAO.getFreeCabinCrewByPosition(departureDate, flight.getDepartureTime(), arrivalDate, arrivalTime, "navigator");
            }

            if (aircraft.getNumRadiomen() > 0) {
                freeRadiomen = shuttleFlightDAO.getFreeCabinCrewByPosition(departureDate, flight.getDepartureTime(), arrivalDate, arrivalTime, "radioman");
            }

            if (aircraft.getNumFlightAttendants() > 0) {
                freeAttendants = shuttleFlightDAO.getFreeCabinCrewByPosition(departureDate, flight.getDepartureTime(), arrivalDate, arrivalTime, "attendant");
            }

        } catch (SQLException ex) {
            logger.error("SQLException in EditCabinCrewCommand::execute", ex);
        }

        // adding already assigned crew
        List<Employee> assignedPilots = null;
        List<Employee> assignedEngineers = null;
        List<Employee> assignedRadiomen = null;
        List<Employee> assignedNavigators = null;
        List<Employee> assignedAttendants = null;

        try {
            assignedPilots = shuttleFlightDAO.getAssignedCrewByPosition(departureDate, request.getParameter("flight"), "pilot");
            assignedEngineers = shuttleFlightDAO.getAssignedCrewByPosition(departureDate, request.getParameter("flight"), "engineer");
            assignedNavigators = shuttleFlightDAO.getAssignedCrewByPosition(departureDate, request.getParameter("flight"), "navigator");
            assignedRadiomen = shuttleFlightDAO.getAssignedCrewByPosition(departureDate, request.getParameter("flight"), "radioman");
            assignedAttendants = shuttleFlightDAO.getAssignedCrewByPosition(departureDate, request.getParameter("flight"), "attendant");
        } catch (SQLException ex) {
            logger.error("SQLException in EditCabinCrewCommand::execute", ex);
        }

        // creating combined lists
        List<Employee> pilots = new ArrayList(assignedPilots);
        if (freePilots != null) {
            pilots.addAll(freePilots);
        }
        List<Employee> engineers = new ArrayList(assignedEngineers);
        if (freeEngineers != null) {
            engineers.addAll(freeEngineers);
        }
        List<Employee> navigators = new ArrayList(assignedNavigators);
        if (freeNavigators != null) {
            navigators.addAll(freeNavigators);
        }
        List<Employee> radiomen = new ArrayList(assignedRadiomen);
        if (freeRadiomen != null) {
            radiomen.addAll(freeRadiomen);
        }
        List<Employee> attendants = new ArrayList(assignedAttendants);
        if (freeAttendants != null) {
            attendants.addAll(freeAttendants);
        }
        
        request.setAttribute("assignedPilots", assignedPilots);
        request.setAttribute("assignedEngineers", assignedEngineers);
        request.setAttribute("assignedNavigators", assignedNavigators);
        request.setAttribute("assignedRadiomen", assignedRadiomen);
        request.setAttribute("assignedAttendants", assignedAttendants);

        request.setAttribute("aircraft", aircraft);
        request.setAttribute("pilots", pilots);
        request.setAttribute("engineers", engineers);
        request.setAttribute("navigators", navigators);
        request.setAttribute("radiomen", radiomen);
        request.setAttribute("attendants", attendants);

        request.setAttribute("needed_pilots", aircraft.getNumPilots());
        request.setAttribute("needed_engineers", aircraft.getNumEngineers());
        request.setAttribute("needed_navigators", aircraft.getNumNavigators());
        request.setAttribute("needed_radiomen", aircraft.getNumRadiomen());
        request.setAttribute("needed_attendants", aircraft.getNumFlightAttendants());

        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/cabinCrew.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            logger.error("ServletException in EditCabinCrewCommand::execute", ex);
        } catch (IOException ex) {
            logger.error("IOException in EditCabinCrewCommand::execute", ex);
        }
    }

}
