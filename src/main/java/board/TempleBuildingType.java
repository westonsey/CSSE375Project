package board;

import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;
import util.CountCollection;

public class TempleBuildingType implements BuildingType {
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
        return BuildingCode.TEMPLE;
    }

    @Override
    public Building createBuilding(VertexLocation loc, Player owner) {
        return new Temple(loc, owner);
    }

    @Override
    public CountCollection<Resource> getRequiredResources() {
        CountCollection<Resource> result = new CountCollection<>();
        result.add(Resource.ORE, 2);
        result.add(Resource.WHEAT, 2);
        result.add(Resource.SHEEP, 1);
        return result;
    }

    @Override
    public String getName() {
        return "temple";
    }

    @Override
    public int getVictoryPoints() {
        return 3;
    }
}
