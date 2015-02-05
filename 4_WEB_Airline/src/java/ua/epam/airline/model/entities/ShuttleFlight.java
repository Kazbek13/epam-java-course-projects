package ua.epam.airline.model.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andrew
 */
public class ShuttleFlight {
    private Date idFlightDate;
    private Flight idFlight;
    private Aircraft aircraft;
    private List cabinCrew;
    private boolean hasCabinCrew;

    public boolean isHasCabinCrew() {
        return hasCabinCrew;
    }

    public void setHasCabinCrew(boolean hasCabinCrew) {
        this.hasCabinCrew = hasCabinCrew;
    }

    public Date getIdFlightDate() {
        return idFlightDate;
    }

    public void setIdFlightDate(Date idFlightDate) {
        this.idFlightDate = idFlightDate;
    }

    public Flight getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Flight idFlight) {
        this.idFlight = idFlight;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public List getCabinCrew() {
        return cabinCrew;
    }

    public void setCabinCrew(List cabinCrew) {
        this.cabinCrew = cabinCrew;
    }
    
    
}
