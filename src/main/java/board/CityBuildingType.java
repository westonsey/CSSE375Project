package board;

public class CityBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        return 2;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.CITY;
    }
}
