package board;

import game.ResourceGainContext;

public interface BuildingType {
    int determineResourceGain(ResourceGainContext context);

    BuildingCode getBuildingCode();
}
