package board;

import board.location.VertexLocation;

public class Port {

    public VertexLocation loc1;
    public VertexLocation loc2;
    public PortType type;

    public Port(VertexLocation loc1, VertexLocation loc2, PortType type){
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.type = type;
    }

    public PortType getPortType(){
        return this.type;
    }

    public boolean checkVertexes(VertexLocation inputLocation){
        int inputRow = inputLocation.getRow();
        int inputCol = inputLocation.getCol();
        return inputRow == loc1.getRow() && inputCol == loc1.getCol() ||
                inputRow == loc2.getRow() && inputCol == loc2.getCol();
    }
    
}
