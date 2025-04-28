package board;

import board.location.VertexLocation;
import game.Player;

public class Settlement extends Building {

    public Settlement(VertexLocation loc, Player owner) {
        super(loc, owner, new SettlementBuildingType());
    }
}
