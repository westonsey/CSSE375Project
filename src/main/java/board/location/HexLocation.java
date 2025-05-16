package board.location;

import java.util.ArrayList;
import java.util.List;

public class HexLocation {

    private int row;
    private int col;

    public static final int MIN_ROW = 0;
    public static final int MAX_ROW = 4;
    public static final int MIN_COL = 0;
    public static final int MAX_COL = 4;

    public HexLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isValid(){
        if(row < MIN_ROW || row > MAX_ROW || col < MIN_COL || col > MAX_COL) {
            return false;
        }
        return true;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public List<VertexLocation> getVertices() {
        if (!isValid()) {
            throw new IllegalStateException("Invalid hex location: (" + row + "," + col + ")");
        }

        List<VertexLocation> vertices = new ArrayList<>();
        if (row % 2 == 0) {
            // Handle even case
            vertices.add(new VertexLocation(row, 2 * col));
            vertices.add(new VertexLocation(row, 2 * col + 1));
            vertices.add(new VertexLocation(row, 2 * col + 2));
            vertices.add(new VertexLocation(row + 1, 2 * col));
            vertices.add(new VertexLocation(row + 1, 2 * col + 1));
            vertices.add(new VertexLocation(row + 1, 2 * col + 2));
        } else {
            // Handle odd case
            vertices.add(new VertexLocation(row, 2 * col + 1));
            vertices.add(new VertexLocation(row, 2 * col + 2));
            vertices.add(new VertexLocation(row, 2 * col + 3));
            vertices.add(new VertexLocation(row + 1, 2 * col + 1));
            vertices.add(new VertexLocation(row + 1, 2 * col + 2));
            vertices.add(new VertexLocation(row + 1, 2 * col + 3));
        }
        return vertices;
    }

    public List<BorderLocation> getBorders() {
        if (!isValid()) {
            throw new IllegalStateException("Invalid hex location: (" + row + "," + col + ")");
        }

        List<BorderLocation> borders = new ArrayList<>();
        if (row % 2 == 0) {
            // Handle even case
            borders.add(new BorderLocation(row, 3 * col));
            borders.add(new BorderLocation(row, 3 * col + 1));
            borders.add(new BorderLocation(row, 3 * col + 2));
            borders.add(new BorderLocation(row, 3 * col + 3));
            borders.add(new BorderLocation(row + 1, 3 * col - 1));
            borders.add(new BorderLocation(row + 1, 3 * col + 1));
        } else {
            // Handle odd case
            borders.add(new BorderLocation(row, 3 * col + 1));
            borders.add(new BorderLocation(row, 3 * col + 2));
            borders.add(new BorderLocation(row, 3 * col + 3));
            borders.add(new BorderLocation(row, 3 * col + 4));
            borders.add(new BorderLocation(row + 1, 3 * col + 2));
            borders.add(new BorderLocation(row + 1, 3 * col + 4));
        }
        return borders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        HexLocation h = (HexLocation) obj;
        return h.row == row && h.col == col;
    }

    @Override
    public int hashCode() {
        return ("(" + row + "," + col + ")").hashCode();
    }
}
