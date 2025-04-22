package board;

import board.location.VertexLocation;
import game.Player;

public class Temple extends Building {
    public Temple(VertexLocation loc, Player owner) {
        super(loc, owner, new TempleBuildingType());
    }
}
