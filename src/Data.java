/**
 * Represents one row from the dataset.
 */
public class Data {

    private String country;
    private String region;
    private String mobilePhoneUsage;


    /*
     * Constructor for the Data class.
     * @param country The country of the data.
     * @param region The region of the data.
     * @param mobilePhoneUsage The mobile phone usage of the data.
     */
    public Data(String country, String region, String mobilePhoneUsage) {
        this.country = country;
        this.region = region;
        this.mobilePhoneUsage = mobilePhoneUsage;
    }

    


    /*
     * Method to get the country of the data.
     * @return The country of the data.
     */
    public String getCountry() {
        return country;
    }
    /*
     * Method to get the mobile phone usage of the data.
     * @return The mobile phone usage of the data.
     */
    public String getMobilePhoneUsage() {
        return mobilePhoneUsage;
    }

    /*
     * Method to get the region of the data.
     * @return The region of the data.
     */
    public String getRegion() {
        return region;
    }

    /*
     * Method to determine if the data belongs to the Western Hemisphere.
     * @return true if the data belongs to the Western Hemisphere, false otherwise.
     */
    public boolean isWesternHemisphere() {
       if (this.region.equalsIgnoreCase("The Americas") || this.region.equalsIgnoreCase("Europe")) {
           return true;
       } else {
           return false;
       }
    }

    /*
     * Method to convert the Data object to a string representation.
     * @return A string representing the Data object.
     */
    @Override
    public String toString() {
        return "Data{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", mobilePhoneUsage=" + mobilePhoneUsage +
                ", isWesternHemisphere=" + isWesternHemisphere() +
                '}';
    }

}
