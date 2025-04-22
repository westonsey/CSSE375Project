package board;

import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;
import util.CountCollection;

public interface BuildingType {
    int determineResourceGain(ResourceGainContext context);

    BuildingCode getBuildingCode();

    Building createBuilding(VertexLocation loc, Player owner);

    CountCollection<Resource> getRequiredResources();
}
