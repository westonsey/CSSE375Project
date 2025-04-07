package board;
import board.location.VertexLocation;
import game.Player;

public abstract class Building {

    private VertexLocation loc;
    private Player owner;
    private BuildingType type;

    public Building(VertexLocation loc, Player owner, BuildingType type) {
        this.loc = loc;
        this.owner = owner;
        this.type = type;
    }

    public VertexLocation getLocation() {
        return loc;
    }

    public Player getOwner() {
        return owner;
    }

    public BuildingType getType() {
        return type;
    }

    public BuildingCode getCode() {
        return type.getBuildingCode();
    }
}






