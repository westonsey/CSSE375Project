package board;

public class SettlementBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        return 1;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.SETTLEMENT;
    }
}
