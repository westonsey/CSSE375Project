package game;

import board.location.HexLocation;

public class Robber {
    private HexLocation loc;

    public Robber(HexLocation loc){
        this.loc = loc;
    }

    public void moveLocation(HexLocation hexLocation) {
        if(!hexLocation.isValid()){
            throw new IllegalStateException("Invalid hex location");
        }
        this.loc = hexLocation;
    }

    public HexLocation getLoc() {
        return loc;
    }
}

