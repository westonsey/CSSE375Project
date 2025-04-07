package board;

import game.ResourceGainContext;

public class SettlementBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        if (context.hasRobber()) {
            return 0;
        }
        if (context.getTotalRoll() != context.getHexValue()) {
            return 0;
        }
        return 1;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.SETTLEMENT;
    }
}
