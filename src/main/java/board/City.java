package board;

import board.location.VertexLocation;
import game.Player;

public class City extends Building {
    public City(VertexLocation loc, Player owner) {
        super(loc, owner, new CityBuildingType());
    }
}
