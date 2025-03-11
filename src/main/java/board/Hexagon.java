package board;

import board.location.HexLocation;
import board.location.VertexLocation;
import game.Resource;

import java.util.List;

public class Hexagon {

    public HexLocation location;
    public Resource resource;
    public int number;
    public boolean isDesert;

    public Hexagon(HexLocation location, Resource resource, int number, boolean isDesert) {
        this.location = location;
        this.resource = resource;
        this.number = number;
        this.isDesert = isDesert;
    }

    public HexLocation getLocation() {
        return location;
    }

    public List<VertexLocation> getVertices() {
        return location.getVertices();
    }

}
