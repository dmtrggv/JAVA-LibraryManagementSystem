package use;

public class Address {

    private String streetName;
    private String streetNumber;
    private String town;
    private String country;

    public Address(String streetName, String streetNumber, String town, String country) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.town = town;
        this.country = country;
    }

    public Address(String input) {
        String[] parts = input.split(", ");
        String streetInfo = parts[0].replace("str. ", "").trim();
        String[] streetParts = streetInfo.split(" ");
        this.streetName = streetParts[0];
        this.streetNumber = (streetParts.length > 1) ? streetParts[1] : "";
        this.town = (parts.length > 1) ? parts[1] : "";
        this.country = (parts.length > 2) ? parts[2] : "";
    }

    //region Getters

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }

    //endregion

    //region Setters

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //endregion

    @Override
    public String toString() {
        return (
            "ул. " + this.streetName + " " + this.streetNumber + ", " +
            this.town + ", " + this.country
        );
    }
}
