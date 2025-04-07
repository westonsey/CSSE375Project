package board;

public interface BuildingType {
    int determineResourceGain(ResourceGainContext context);

    BuildingCode getBuildingCode();
}
