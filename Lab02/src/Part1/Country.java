package Part1;

import java.util.Map;

public class Country {
    private String name;
    private Map<Integer, Emission> emissions;

    public Country(String name, Map<Integer, Emission> emissions){
        this.name = name;
        this.emissions = emissions;
    };

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
