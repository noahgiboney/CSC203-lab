package Part1;

import java.util.Map;

public class Sector {
    private String name;
    private Map<Integer, Double> emissions;

    public Sector(String name, Map<Integer, Double> emissions){
        this.name = name;
        this.emissions = emissions;
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
