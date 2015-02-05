package ua.epam.airline.model.entities;

/**
 *
 * @author Andrew
 */
public class Airport {
    private String idIATACode;
    private String title;
    private String city;
    private String country;

    public String getIdIATACode() {
        return idIATACode;
    }

    public void setIdIATACode(String idIATACode) {
        this.idIATACode = idIATACode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
}
