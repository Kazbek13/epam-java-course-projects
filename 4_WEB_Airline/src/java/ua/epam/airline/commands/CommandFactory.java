package ua.epam.airline.commands;

import ua.epam.airline.commands.flight.ShowFlightsPageCommand;
import ua.epam.airline.commands.flight.DeleteFlightCommand;
import ua.epam.airline.commands.flight.ShowUpdateFlightCommand;
import ua.epam.airline.commands.flight.ShowCreateFlightCommand;
import ua.epam.airline.commands.flight.CreateFlightInstanceCommand;
import ua.epam.airline.commands.flight.UpdateFlightInstanceCommand;
import java.util.HashMap;
import java.util.Map;
import ua.epam.airline.commands.aircraft.CreateAircraftInstanceCommand;
import ua.epam.airline.commands.aircraft.DeleteAircraftCommand;
import ua.epam.airline.commands.aircraft.ShowAircraftsPageCommand;
import ua.epam.airline.commands.aircraft.ShowCreateAircraftCommand;
import ua.epam.airline.commands.airport.CreateAirportInstanceCommand;
import ua.epam.airline.commands.airport.DeleteAirportCommand;
import ua.epam.airline.commands.airport.ShowCreateAirportCommand;
import ua.epam.airline.commands.airport.ShowDestinationsPageCommand;
import ua.epam.airline.commands.airport.ShowUpdateAirportCommand;
import ua.epam.airline.commands.airport.UpdateAirportInstanceCommand;
import ua.epam.airline.commands.employee.CreateEmployeeInstanceCommand;
import ua.epam.airline.commands.employee.DeleteEmployeeCommand;
import ua.epam.airline.commands.employee.ShowCreateEmployeeCommand;
import ua.epam.airline.commands.employee.ShowEmployeesPageCommand;
import ua.epam.airline.commands.shuttleflight.CreateShuttleFlightInstanceCommand;
import ua.epam.airline.commands.shuttleflight.DeleteShuttleFlightCommand;
import ua.epam.airline.commands.shuttleflight.EditCabinCrewCommand;
import ua.epam.airline.commands.shuttleflight.SaveCabinCrewCommand;
import ua.epam.airline.commands.shuttleflight.ShowCreateShuttleFlightCommand;
import ua.epam.airline.commands.shuttleflight.ShowShuttleFlightsPageCommand;

/**
 *
 * @author Andrew
 */
public class CommandFactory {
    private static Map<String, Command> commands;
    private static CommandFactory factoryInstance;
    
    private CommandFactory() {
        commands = new HashMap<>();
        
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("setLanguage", new SetLanguageCommand());
        commands.put("showIndexPage", new ShowIndexPageCommand());
        
        commands.put("showFlightsPage", new ShowFlightsPageCommand());
        commands.put("deleteFlight", new DeleteFlightCommand());
        commands.put("showUpdateFlightPage", new ShowUpdateFlightCommand());
        commands.put("showCreateFlightPage", new ShowCreateFlightCommand());
        commands.put("updateFlightInstance", new UpdateFlightInstanceCommand());
        commands.put("createFlightInstance", new CreateFlightInstanceCommand());
        
        commands.put("showDestinationsPage", new ShowDestinationsPageCommand());
        commands.put("deleteAirport", new DeleteAirportCommand());
        commands.put("showUpdateAirportPage", new ShowUpdateAirportCommand());
        commands.put("showCreateAirportPage", new ShowCreateAirportCommand());
        commands.put("updateAirportInstance", new UpdateAirportInstanceCommand());
        commands.put("createAirportInstance", new CreateAirportInstanceCommand());
        
        commands.put("showShuttleFlightsPage", new ShowShuttleFlightsPageCommand());
        commands.put("deleteShuttleFlight", new DeleteShuttleFlightCommand());
        commands.put("showCreateShuttleFlightPage", new ShowCreateShuttleFlightCommand());
        commands.put("createShuttleFlightInstance", new CreateShuttleFlightInstanceCommand());
        commands.put("editCabinCrew", new EditCabinCrewCommand());
        commands.put("saveCabinCrew", new SaveCabinCrewCommand());
        
        commands.put("showAircraftsPage", new ShowAircraftsPageCommand());
        commands.put("deleteAircraft", new DeleteAircraftCommand());
        commands.put("showCreateAircraftPage", new ShowCreateAircraftCommand());
        commands.put("createAircraftInstance", new CreateAircraftInstanceCommand());
        
        commands.put("showEmployeesPage", new ShowEmployeesPageCommand());
        commands.put("deleteEmployee", new DeleteEmployeeCommand());
        commands.put("showCreateEmployeePage", new ShowCreateEmployeeCommand());
        commands.put("createEmployeeInstance", new CreateEmployeeInstanceCommand());
    }
    
    public static synchronized CommandFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new CommandFactory();
        }
        return factoryInstance;
    }
    
    public Command createCommand(String command) {
        Command toReturn = commands.get(command);
        if (toReturn == null) {
            toReturn = commands.get("showIndexPage");
        }
        return toReturn;
    }
}
