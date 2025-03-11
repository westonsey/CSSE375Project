package game;

import board.location.HexLocation;

public class Robber {
    public HexLocation loc;

    public Robber(HexLocation loc){
        this.loc = loc;
    }

    public void moveLocation(HexLocation hexLocation) {
        if(!hexLocation.isValid()){
            throw new IllegalStateException("Invalid hex location");
        }
        this.loc = hexLocation;
    }
}

