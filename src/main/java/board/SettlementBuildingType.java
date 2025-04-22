package board;

import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;
import util.CountCollection;

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

    @Override
    public Building createBuilding(VertexLocation loc, Player owner) {
        return new Settlement(loc, owner);
    }

    @Override
    public CountCollection<Resource> getRequiredResources() {
        CountCollection<Resource> result = new CountCollection<>();
        result.add(Resource.WOOD, 1);
        result.add(Resource.WHEAT, 1);
        result.add(Resource.BRICK, 1);
        result.add(Resource.SHEEP, 1);
        return result;
    }

    @Override
    public String getName() {
        return "settlement";
    }
}
