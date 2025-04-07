package board;

import game.ResourceGainContext;

public class CityBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        if (context.hasRobber()) {
            return 0;
        }
        return 2;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.CITY;
    }
}
