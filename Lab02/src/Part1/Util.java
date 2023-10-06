package Part1;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static int getYearWithHighestEmissions(Country country){
        int maxYear = 0;
        double maxEmissions = 0;
        double currentEmissions;
        for(Integer key: country.getEmissions().keySet()){
            currentEmissions = country.getEmissions().get(key).getCO2() + country.getEmissions().get(key).getN2O() + country.getEmissions().get(key).getCH4();
            if (currentEmissions > maxEmissions){
                maxEmissions = currentEmissions;
                maxYear = key;
            }
        }
        return maxYear;
    }

    public static int getYearWithHighestEmissions(Sector sector) {
        int maxYear = 0;
        double maxEmissions = 0;
        for (Integer key : sector.getEmissions().keySet()) {
            if (sector.getEmissions().get(key) > maxEmissions) {
                maxEmissions = sector.getEmissions().get(key);
                maxYear = key;
            }
        }
        return maxYear;
    }
}
