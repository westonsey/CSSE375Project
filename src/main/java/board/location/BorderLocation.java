package board.location;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class BorderLocation {

    private int row;
    private int col;

    public static final int MIN_ROW = 0;
    public static final int MAX_ROW = 5;
    public static final int MIN_COL = -1;
    public static final int MAX_COL = 15;

    public BorderLocation(int row, int col) {
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
        return !(row < MIN_ROW || col < MIN_COL || row > MAX_ROW || col > MAX_COL ||
                row >= MAX_ROW && col % 3 == 0 || col < 0 && row % 2 == 0);
    }

    @SuppressWarnings("checkstyle:UnnecessaryParentheses")
    public List<VertexLocation> getVertices() {
        if (!isValid()) {
            // Invalid border location
            throw new IllegalStateException("Invalid border location: (" + row + "," + col + ")");
        }

        List<VertexLocation> vertices = new ArrayList<>();
        if (col % 3 == 0) {
            // Handle vertical border
            vertices.add(new VertexLocation(row, col / 3 * 2 + 1));
            vertices.add(new VertexLocation(row + 1, col / 3 * 2 + 1));
        } else if (col % 3 == 1) {
            // Handle "diagonal-up" border
            vertices.add(new VertexLocation(row, col / 3 * 2 + 1));
            vertices.add(new VertexLocation(row, col / 3 * 2 + 2));
        } else {
            // Handle "diagonal-down" border
            vertices.add(new VertexLocation(row, (col - 2) / 3 * 2 + 2));
            vertices.add(new VertexLocation(row, (col - 2) / 3 * 2 + 3));
        }
        if (row % 2 == 0) {
            // The numbers above only work for odd rows
            // For even rows, subtract 1 from each column
            for (int i = 0; i < vertices.size(); i++) {
                VertexLocation v = vertices.get(i);
                vertices.set(i, new VertexLocation(v.getRow(), v.getCol() - 1));
            }
        }

        return vertices;
    }

    public List<BorderLocation> getBorders() {
        // For ease, calculate the vertices on this border, grab the borders on those vertices,
        // and remove this border from that set
        Set<BorderLocation> locs = new HashSet<>();
        List<VertexLocation> vertices = getVertices();
        for (VertexLocation v : vertices) {
            List<BorderLocation> borders = v.getBorders();
            locs.addAll(borders);
        }
        locs.remove(this);
        return new ArrayList<>(locs);
    }

    @Override
    public boolean equals(Object obj) {
        BorderLocation b = (BorderLocation) obj;
        return b.row == row && b.col == col;
    }

    @Override
    public int hashCode() {
        return ("(" + row + "," + col + ")").hashCode();
    }
}
