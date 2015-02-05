package ua.epam.airline.commands.shuttleflight;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.epam.airline.exceptions.NotUniqueCabinCrewMembersException;
import ua.epam.airline.dao.DAOFactory;
import ua.epam.airline.dao.ShuttleFlightDAO;
import ua.epam.airline.commands.Command;

/**
 *
 * @author Andrew
 */
public class SaveCabinCrewCommand implements Command {

    static final Logger logger = LogManager.getLogger(SaveCabinCrewCommand.class);
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ShuttleFlightDAO shuttleFlightDAO = null;

        int neededPilots = Integer.parseInt(request.getParameter("needed_pilots"));
        int neededEngineers = Integer.parseInt(request.getParameter("needed_engineers"));
        int neededNavigators = Integer.parseInt(request.getParameter("needed_navigators"));
        int neededRadiomen = Integer.parseInt(request.getParameter("needed_radiomen"));
        int neededAttendants = Integer.parseInt(request.getParameter("needed_attendants"));

        // Cabin crew IDs to be assigned on given flight
        List<Integer> crewIds = new ArrayList<>();
        for (int i = 0; i < neededPilots; ++i) {
            crewIds.add(Integer.parseInt(request.getParameter("pilot" + i)));
        }
        for (int i = 0; i < neededEngineers; ++i) {
            crewIds.add(Integer.parseInt(request.getParameter("engineer" + i)));
        }
        for (int i = 0; i < neededNavigators; ++i) {
            crewIds.add(Integer.parseInt(request.getParameter("navigator" + i)));
        }
        for (int i = 0; i < neededRadiomen; ++i) {
            crewIds.add(Integer.parseInt(request.getParameter("radioman" + i)));
        }
        for (int i = 0; i < neededAttendants; ++i) {
            crewIds.add(Integer.parseInt(request.getParameter("attendant" + i)));
        }
        //System.out.println(crewIds);
        try {
            shuttleFlightDAO = DAOFactory.getShuttleFlightDAO();
        } catch (SQLException ex) {
            logger.error("SQLException in SaveCabinCrewCommand::execute", ex);
        }

        try {
            shuttleFlightDAO.assignCabinCrewOnShuttleFlight(crewIds, Date.valueOf(request.getParameter("date")), request.getParameter("flight"));
        } catch (NotUniqueCabinCrewMembersException ex) {
            logger.warn(ex.getMessage(), ex.getCause());

            request.setAttribute("err", "crud_error.cabin_crew_not_unique");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/crudError.jsp");

            try {
                rd.forward(request, response);
            } catch (ServletException ex1) {
                logger.error("ServletException in SaveCabinCrewCommand::execute", ex1);
            } catch (IOException ex1) {
                logger.error("IOException in SaveCabinCrewCommand::execute", ex1);
            }

        } catch (SQLException ex) {
            logger.error("SQLException in SaveCabinCrewCommand::execute", ex);
        }
        
        try {
            response.sendRedirect("shuttleFlights");
        } catch (IOException ex) {
            logger.error("IOException in SaveCabinCrewCommand::execute", ex);
        }

    }

}
