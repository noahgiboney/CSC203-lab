import java.util.List;
import java.util.Map;

public class Sector implements GreenhouseGasEmitter {
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

    public static Sector sectorWithBiggestChangeInEmissions(List<Sector> sectors, int startYear, int endYear){
        Sector maxSector = null;
        double maxChange = 0;
        double startEmissions = 0;
        double endEmissions = 0;
        double changeInEmissions = 0;
        for (Sector sectorIndex : sectors){
            startEmissions = sectorIndex.getEmissions().get(startYear);
            endEmissions = sectorIndex.getEmissions().get(endYear);
            changeInEmissions = Math.abs(startEmissions - endEmissions);
            if (changeInEmissions > maxChange){
                maxChange = changeInEmissions;
                maxSector = sectorIndex;
            }
        }
        return maxSector;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmissions(Map<Integer, Double> emissions) {
        this.emissions = emissions;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getTotalEmissionsInYear(int year) {
        return this.getEmissions().get(year);
    }

    public Map<Integer, Double> getEmissions() {
        return emissions;
    }

}
