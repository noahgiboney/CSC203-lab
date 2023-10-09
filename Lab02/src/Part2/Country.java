package Part2;

import java.util.List;
import java.util.Map;

public class Country {
    private String name;
    private Map<Integer, Emission> emissions;

    public Country(String name, Map<Integer, Emission> emissions){
        this.name = name;
        this.emissions = emissions;
    };
    public int getYearWithHighestEmissions(){
        int maxYear = 0;
        double maxEmissions = 0;
        double currentEmissions;
        for(Integer key: this.emissions.keySet()){
            currentEmissions = this.emissions.get(key).getCO2() + this.emissions.get(key).getN2O() + this.emissions.get(key).getCH4();
            if (currentEmissions > maxEmissions){
                maxEmissions = currentEmissions;
                maxYear = key;
            }
        }
        return maxYear;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEmissions(Map<Integer, Emission> map) {
        this.emissions = map;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Emission> getEmissions() {
        return emissions;
    }

    public static Country countryWithHighestCH4InYear(List<Country> countries, int year){
        Country maxCountry = null;
        double maxCH4 = 0;
        for (Country countryIndex : countries){
            if (countryIndex.getEmissions().get(year).getCH4() > maxCH4){
                maxCountry = countryIndex;
                maxCH4 = countryIndex.getEmissions().get(year).getCH4();
            }

        }
        return maxCountry;
    }

    public static Country countryWithHighestChangeInEmissions(List<Country> countries, int startYear, int endYear){
        Country maxCountry = null;
        double maxIncrease = 0;
        double startEmissions = 0;
        double endEmissions = 0;
        double changeInEmissions = 0;

        for (Country countryIndex: countries){
            startEmissions = countryIndex.getTotalEmissions(startYear);
            endEmissions = countryIndex.getTotalEmissions(endYear);
            changeInEmissions = endEmissions - startEmissions;
            if (changeInEmissions > maxIncrease){
                maxIncrease = changeInEmissions;
                maxCountry = countryIndex;
            }
        }
        return maxCountry;
    }

    public double getTotalEmissions(int year){
        return this.getEmissions().get(year).getCO2() + this.getEmissions().get(year).getN2O() + this.getEmissions().get(year).getCH4();
    }
}
