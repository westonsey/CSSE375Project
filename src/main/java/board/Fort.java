package board;

import board.location.VertexLocation;
import game.Player;

public class Fort extends Building {
    public Fort(VertexLocation loc, Player owner) {
        super(loc, owner, new FortBuildingType());
    }
}
