import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Main application for the Data Analysis Mini‑Project.
 *
 * TODO:
 *  - Update the path to your dataset file
 *  - Read the CSV file using Scanner
 *  - Parse each row and extract the correct columns
 *  - Construct Data objects from each row
 *  - Store them in an array
 *  - Write methods to analyze the dataset (min, max, average, filters, etc.)
 *  - Print insights and answer your guiding question
 *  - Add Javadoc comments for any methods you create
 */
public class App {

    static Data[] dataList = new Data[2705];
    static String filename = "src/worldData.csv";
    public static void main(String[] args) throws FileNotFoundException {

        // TODO: Update this with your CSV file path
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        
        // TODO: Create an array of Data objects to store data
            
            int i = 0;
             
            while (scan.hasNext() && i < dataList.length) {

                // skip first line
                if (i == 0) {
                    scan.nextLine();
                }

                String line = scan.nextLine();
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                /* if (values.length <= 25) {
                    continue;
                } */

                String country = values[0].trim();
                String region = values[24].trim();
                String mobilePhoneUsage = values[17].trim();
                dataList[i] = new Data(country, region, mobilePhoneUsage);
                i++;

            }
            
        scan.close();
    
        runSampling(dataList, i);

       
       // TODO: Read file using Scanner
        // - Skip header if needed
        // - Loop through rows
        // - Split each line by commas
        // - Convert text to numbers when needed
        // - Create new Data objects
        // - Add to your array
        

        // TODO: Call your analysis methods
        // Example:
        // double maxValue = findMaxValue(dataList);
        // double average = computeAverageValue(dataList);


        // TODO: Print insights
        // - Number of rows loaded
        // - Min, max, average, or any other findings
        // - Final answer to your guiding question


        // OPTIONAL TODO:
        // Add user interaction:
        // Ask the user what kind of analysis they want to see
    }
    /*
    * Method to run the sampling process and compare mobile phone usage between the Western Hemisphere and Eastern Hemisphere.
     * It prompts the user for the number of random samples to take, then calculates the total mobile phone usage for both hemispheres based on the random samples.
     * Finally, it compares the totals and prints out which hemisphere has higher mobile phone usage, along with the total values for each hemisphere.
     * @param dataList An array of Data objects representing the dataset.
     * @param i The number of valid Data objects in the dataList array.
     * returns void. The method prints the results directly to the console.
     */
    public static void runSampling (Data[] dataList, int i) {
        Scanner user = new Scanner(System.in);
        System.out.println("How many random samples would you like to see? (Max 50)");
        int numberofRandomSamples = user.nextInt();
        double totalWesternHemisphereValue = 0;
        double totalEasternHemisphereValue = 0;
        for (int j = 0; j < numberofRandomSamples; j++) {
            double[] hemisphereValues = MobilePhoneUsageHemisphere(dataList, i);
            totalWesternHemisphereValue += hemisphereValues[0];
            totalEasternHemisphereValue += hemisphereValues[1];
        }
        if (totalWesternHemisphereValue > totalEasternHemisphereValue) {
            System.out.println("Out of " + numberofRandomSamples + " random samples, the Western Hemisphere has higher mobile phone usage.");
        } else if (totalWesternHemisphereValue == totalEasternHemisphereValue) {
            System.out.println("Out of " + numberofRandomSamples + " random samples, the Eastern Hemisphere has higher mobile phone usage.");
        } else {
            System.out.println("Out of " + numberofRandomSamples + " random samples, the Western Hemisphere and Eastern Hemisphere have the same mobile phone usage.");
        }

        System.out.println("Total Western Hemisphere Mobile Phone Usage: " + totalWesternHemisphereValue);
        System.out.println("Total Eastern Hemisphere Mobile Phone Usage: " + totalEasternHemisphereValue);

        user.close();
    }

    /*
     * Method to calculate mobile phone usage for each hemisphere.
     * @param dataList An array of Data objects representing the dataset.
     * @param i The number of valid Data objects in the dataList array.
     * @return An array containing the total mobile phone usage for the Western and Eastern Hemispheres.
     */
    public static double[] MobilePhoneUsageHemisphere(Data[] dataList, int i) {
        Data[] sampledList = randomSample(dataList, i);
        double westernHemisphereValue = 0;
        double easternHemisphereValue = 0;
        for (int j = 0; j < sampledList.length; j++) {
            if (sampledList[j].isWesternHemisphere()) {
                if (!sampledList[j].getMobilePhoneUsage().isEmpty()) {
                    westernHemisphereValue += Double.parseDouble(sampledList[j].getMobilePhoneUsage());
                }
            } else {
                if (!sampledList[j].getMobilePhoneUsage().isEmpty()) {
                    easternHemisphereValue += Double.parseDouble(sampledList[j].getMobilePhoneUsage());
                }
            }
        }
        return new double[]{westernHemisphereValue, easternHemisphereValue};
    
    }

    /*
     * Method to create a random sample of the dataset.
     * @param dataList An array of Data objects representing the dataset.
     * @param i The number of valid Data objects in the dataList array.
     * @return An array of randomly selected Data objects.
     */
    public static Data[] randomSample(Data[] dataList, int i) {
            Random rand = new Random();
            Data[] sampledList = new Data[50];
            for (int data = 0; data < 50; data++) {
                int randomIndex = rand.nextInt(i);
                sampledList[data] = dataList[randomIndex];
            }
            return sampledList;
    }

    /*
     * Method to print all Data objects in the dataList array.
     * @param dataList An array of Data objects representing the dataset.
     * returns void. The method prints the Data objects directly to the console.
     */
    public static void printData() {
            for (Data data : dataList) {
                if (data != null) {
                    System.out.println(data);
                }
            }
        }


}