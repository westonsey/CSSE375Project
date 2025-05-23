package board;

import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;
import util.CountCollection;

public class FortBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        if (context.getTotalRoll() != context.getHexValue()) {
            return 0;
        }
        return 1;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.FORT;
    }

    @Override
    public Building createBuilding(VertexLocation loc, Player owner) {
        return new Fort(loc, owner);
    }

    @Override
    public CountCollection<Resource> getRequiredResources() {
        CountCollection<Resource> result = new CountCollection<>();
        result.add(Resource.BRICK, 1);
        result.add(Resource.ORE, 1);
        return result;
    }

    @Override
    public String getName() {
        return "fort";
    }

    @Override
    public int getVictoryPoints() {
        return 2;
    }
}
