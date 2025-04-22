package board;

import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;
import util.CountCollection;

public class ObservatoryBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        if (context.hasRobber()) {
            return 0;
        }
        if (context.getTotalRoll() != 2 && context.getTotalRoll() != 12) {
            return 0;
        }
        return 1;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.OBSERVATORY;
    }

    @Override
    public Building createBuilding(VertexLocation loc, Player owner) {
        return new Observatory(loc, owner);
    }

    @Override
    public CountCollection<Resource> getRequiredResources() {
        CountCollection<Resource> result = new CountCollection<>();
        result.add(Resource.BRICK, 1);
        result.add(Resource.WOOD, 1);
        result.add(Resource.ORE, 1);
        return result;
    }

    @Override
    public String getName() {
        return "observatory";
    }

    @Override
    public int getVictoryPoints() {
        return 2;
    }
}
