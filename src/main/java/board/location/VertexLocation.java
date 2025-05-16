package board.location;

import java.util.ArrayList;
import java.util.List;

public class VertexLocation {

    private int row;
    private int col;

    public static final int MIN_ROW = 0;
    public static final int MAX_ROW = 5;
    public static final int MIN_COL = 0;
    public static final int MAX_COL = 10;

    public VertexLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isValid() {
        return !(row < MIN_ROW || row > MAX_ROW || col < MIN_COL || col > MAX_COL);
    }

    public List<HexLocation> getHexes() {
        // Check for valid row/col
        if (!isValid()) {
            throw new IllegalStateException("Invalid vertex location: (" + row + "," + col + ")");
        }

        List<HexLocation> hexes = new ArrayList<>();
        if (col % 2 == 0) {
            // Handle even-column case
            if (row % 2 == 0) {
                // Handle even-row case
                hexes.add(new HexLocation(row - 1, col / 2 - 1));
                hexes.add(new HexLocation(row, col / 2 - 1));
                hexes.add(new HexLocation(row, col / 2));
            } else {
                // Handle odd-row case
                hexes.add(new HexLocation(row - 1, col / 2 - 1));
                hexes.add(new HexLocation(row - 1, col / 2));
                hexes.add(new HexLocation(row, col / 2 - 1));
            }
        } else {
            // Handle odd-column case
            if (row % 2 == 0) {
                // Handle even-row case
                hexes.add(new HexLocation(row - 1, (col - 1) / 2 - 1));
                hexes.add(new HexLocation(row - 1, (col - 1) / 2));
                hexes.add(new HexLocation(row, (col - 1) / 2));
            } else {
                // Handle odd-row case
                hexes.add(new HexLocation(row - 1, (col - 1) / 2));
                hexes.add(new HexLocation(row, (col - 1) / 2 - 1));
                hexes.add(new HexLocation(row, (col - 1) / 2));
            }
        }

        // Prune invalid locations
        for (int i = 0; i < hexes.size(); i++) {
            if (!hexes.get(i).isValid()) {
                // Remove this hex
                hexes.remove(i);
                i--;
            }
        }
        return hexes;
    }

    public List<BorderLocation> getBorders() {
        // Check for valid row/col
        if (!isValid()) {
            throw new IllegalStateException("Invalid vertex location: (" + row + "," + col + ")");
        }

        List<BorderLocation> borders = new ArrayList<>();
        if (col % 2 == 0) {
            if (row % 2 == 0) {
                // Even col, even row
                borders.add(new BorderLocation(row, col + col / 2 - 1));
                borders.add(new BorderLocation(row, col + col / 2));
                borders.add(new BorderLocation(row, col + col / 2 + 1));
            } else {
                // Even col, odd row
                borders.add(new BorderLocation(row - 1, col / 2 * 3));
                borders.add(new BorderLocation(row, col / 2 * 3 - 2));
                borders.add(new BorderLocation(row, col / 2 * 3 - 1));
            }
        } else {
            if (row % 2 == 0) {
                // Odd col, even row
                borders.add(new BorderLocation(row - 1, (col - 1) / 2 * 3));
                borders.add(new BorderLocation(row, (col - 1) / 2 * 3 + 1));
                borders.add(new BorderLocation(row, (col - 1) / 2 * 3 + 2));
            } else {
                // Odd col, odd row
                borders.add(new BorderLocation(row, (col - 1) / 2 * 3 - 1));
                borders.add(new BorderLocation(row, (col - 1) / 2 * 3));
                borders.add(new BorderLocation(row, (col - 1) / 2 * 3 + 1));
            }
        }

        // Prune borders
        for (int i = 0; i < borders.size(); i++) {
            if (!borders.get(i).isValid()) {
                borders.remove(i);
                break; // Vertex always has at most one invalid border
            }
        }

        return borders;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        VertexLocation v = (VertexLocation) obj;
        return v.row == row && v.col == col;
    }

    @Override
    public int hashCode() {
        return ("(" + row + "," + col + ")").hashCode();
    }
}
