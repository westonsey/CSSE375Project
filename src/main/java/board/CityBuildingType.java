package board;

import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;
import util.CountCollection;

public class CityBuildingType implements BuildingType {
    @Override
    public int determineResourceGain(ResourceGainContext context) {
        if (context.hasRobber()) {
            return 0;
        }
        if (context.getTotalRoll() != context.getHexValue()) {
            return 0;
        }
        return 2;
    }

    @Override
    public BuildingCode getBuildingCode() {
        return BuildingCode.CITY;
    }

    @Override
    public Building createBuilding(VertexLocation loc, Player owner) {
        return new City(loc, owner);
    }

    @Override
    public CountCollection<Resource> getRequiredResources() {
        CountCollection<Resource> result = new CountCollection<>();
        result.add(Resource.ORE, 3);
        result.add(Resource.WHEAT, 2);
        return result;
    }

    @Override
    public String getName() {
        return "city";
    }
}
