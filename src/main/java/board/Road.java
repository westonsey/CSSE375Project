package board;

import board.location.BorderLocation;
import game.Player;

public class Road {

    private BorderLocation loc;
    private Player owner;

    public Road(BorderLocation loc, Player owner) {
        this.loc = loc;
        this.owner = owner;
    }

    public BorderLocation getLocation(){
        return loc;
    }

    public Player getOwner() {
        return owner;
    }
}
