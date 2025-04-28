package board;

import board.location.VertexLocation;
import game.Player;

public class Observatory extends Building {
    public Observatory(VertexLocation loc, Player owner) {
        super(loc, owner, new ObservatoryBuildingType());
    }
}
