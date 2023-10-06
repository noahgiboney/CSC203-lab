package Part2;

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
}
