package Part2;

import java.util.Map;

public class Sector {
    private String name;
    private Map<Integer, Double> emissions;

    public Sector(String name, Map<Integer, Double> emissions){
        this.name = name;
        this.emissions = emissions;
    }

    public int getYearWithHighestEmissions() {
        int maxYear = 0;
        double maxEmissions = 0;
        for (Integer key : this.emissions.keySet()) {
            if (this.emissions.get(key) > maxEmissions) {
                maxEmissions = this.emissions.get(key);
                maxYear = key;
            }
        }
        return maxYear;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setEmissions(Map<Integer, Double> emissions) {
        this.emissions = emissions;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Double> getEmissions() {
        return emissions;
    }
}
