//package Part3;
//
//import Part2.Country;
//import Part2.Emission;
//import Part2.Sector;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.*;
//
///**
// * NOTE THAT THIS CLASS WILL NOT COMPILE UNTIL YOU HAVE COMPLETED PART 2 OF THIS LAB
// */
//public class Main {
//
//    public static void main(String[] args) throws FileNotFoundException {
//        List<Country> countries = getCountries();
//        List<Sector> sectors = getSectors();
//
//        //5950000
//        //8346000
//        //9737 or 6704
//
//        String nameHighestCH4 = Country.countryWithHighestCH4InYear(countries, 2000).getName();
//        double highestCh4 = Country.countryWithHighestCH4InYear(countries,2000).getEmissions().get(2000).getCH4();
//
//        String nameHighestIncrease = Country.countryWithHighestChangeInEmissions(countries, 1988, 2012).getName();
//        double highestIncrease = Country.countryWithHighestChangeInEmissions(countries, 1988, 2012).getTotalEmissions(2012) -
//                                Country.countryWithHighestChangeInEmissions(countries, 1988, 2012).getTotalEmissions(1988);
//
//        String sectorNameIncrease = Sector.sectorWithBiggestChangeInEmissions(sectors,1988,2012).getName();
//        double sectorIncrease = Sector.sectorWithBiggestChangeInEmissions(sectors,1988,2012).getEmissions().get(2012) -
//                                Sector.sectorWithBiggestChangeInEmissions(sectors,1988,2012).getEmissions().get(1988);
//
//
//        System.out.println();
//        System.out.println("Country with highest methane gas emission in 2000: " + nameHighestCH4 + " with " + highestCh4 );
//        System.out.println("Country with highest increase in greenhouse gas emissions between 1988 and 2012: " + nameHighestIncrease  + " with " + highestIncrease);
//        System.out.println("Sector with the highest change in greenhouse gas emissions between 1988 and 2012: " + sectorNameIncrease  + " with " +sectorIncrease);
//    }
//
//	/**
//     * Reads country emissions data from the countries.csv file. Do not modify this
//     * method. Note that this method won't compile until you have implemented the
//     * Country class.
//     *
//     * @return A List of Country objects.
//     * @throws FileNotFoundException If the countries.csv file does not exist
//     */
//    private static List<Country> getCountries() throws FileNotFoundException {
//        File dataFile = new File("Lab02//src//Part3//countries.csv");
//        Map<String, Map<Integer, Emission>> emissions = new HashMap<>();
//
//        Scanner scan = new Scanner(dataFile);
//        scan.nextLine(); // Skip the header line
//        while (scan.hasNextLine()) {
//            String[] data = scan.nextLine().split(",");
//
//            // Each line contains Country, Year, CO2, N20, CH4 --- in that order
//            String name = data[0];
//            int year = Integer.parseInt(data[1]);
//            double co2emissions = Double.parseDouble(data[2]);
//            double n2oemissions = Double.parseDouble(data[3]);
//            double ch4emissions = Double.parseDouble(data[4]);
//
//            Emission emission = new Emission(co2emissions, n2oemissions, ch4emissions);
//
//            if (!emissions.containsKey(name)) {
//                emissions.put(name, new HashMap<>());
//            }
//            emissions.get(name).put(year, emission);
//        }
//        scan.close();
//
//        // Process emissions into a List of Countries
//        List<Country> result = new LinkedList<>();
//        for (Map.Entry<String, Map<Integer, Emission>> entry : emissions.entrySet()) {
//            Country country = new Country(entry.getKey(), entry.getValue());
//            result.add(country);
//        }
//        return result;
//    }
//
//    /**
//     * Reads sector emissions data from the sectors.csv file. Do not modify this
//     * method. Note that this method won't compile until you have implemented the
//     * Country class.
//     *
//     * @return A List of Sector objects
//     * @throws FileNotFoundException If the sectors.csv file does not exist
//     */
//    private static List<Sector> getSectors() throws FileNotFoundException {
//        File dataFile = new File("Lab02//src//Part3//sectors.csv");
//        Map<String, Map<Integer, Double>> tempMap = new HashMap<>();
//        Scanner scan = new Scanner(dataFile);
//        scan.nextLine(); // Skip the header line
//        while (scan.hasNextLine()) {
//            String[] data = scan.nextLine().split(",");
//
//            // Each line contains Sector, Year, Emissions --- in that order
//            String name = data[0].split("\\.")[2]; // Sector names are "Emissions.Sector.X" — we only want "X"
//            int year = Integer.parseInt(data[1]);
//            double greenhouseGasEmissions = Double.parseDouble(data[2]);
//
//            if (!tempMap.containsKey(name)) {
//                tempMap.put(name, new HashMap<>());
//            }
//            tempMap.get(name).put(year, greenhouseGasEmissions);
//        }
//        scan.close();
//
//        // Process tempMap into a List of Countries
//        List<Sector> result = new LinkedList<>();
//        for (Map.Entry<String, Map<Integer, Double>> entry : tempMap.entrySet()) {
//            Sector sector = new Sector(entry.getKey(), entry.getValue());
//            result.add(sector);
//        }
//        return result;
//    }
//}
