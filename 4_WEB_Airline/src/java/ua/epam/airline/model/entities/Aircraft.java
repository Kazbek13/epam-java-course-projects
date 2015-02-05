package ua.epam.airline.model.entities;

/**
 *
 * @author Andrew
 */
public class Aircraft {
    private String idReg;
    private String manufacturer;
    private String modelType;
    private Integer numSeats;
    private Integer numPilots;
    private Integer numNavigators;
    private Integer numRadiomen;
    private Integer numEngineers;
    private Integer numFlightAttendants;

    public String getIdReg() {
        return idReg;
    }

    public void setIdReg(String idReg) {
        this.idReg = idReg;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Integer getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(Integer numSeats) {
        this.numSeats = numSeats;
    }

    public Integer getNumPilots() {
        return numPilots;
    }

    public void setNumPilots(Integer numPilots) {
        this.numPilots = numPilots;
    }

    public Integer getNumNavigators() {
        return numNavigators;
    }

    public void setNumNavigators(Integer numNavigators) {
        this.numNavigators = numNavigators;
    }

    public Integer getNumRadiomen() {
        return numRadiomen;
    }

    public void setNumRadiomen(Integer numRadiomen) {
        this.numRadiomen = numRadiomen;
    }

    public Integer getNumEngineers() {
        return numEngineers;
    }

    public void setNumEngineers(Integer numEngineers) {
        this.numEngineers = numEngineers;
    }

    public Integer getNumFlightAttendants() {
        return numFlightAttendants;
    }

    public void setNumFlightAttendants(Integer numFlightAttendants) {
        this.numFlightAttendants = numFlightAttendants;
    }
    
    
}
