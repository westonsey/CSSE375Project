package board.location;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HexLocationTests {

    @Test
    public void getVertices_With0_0_Success() {
        HexLocation hex = new HexLocation(0, 0);
        List<VertexLocation> vertices = hex.getVertices();
        assertEquals(6, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(0,0)));
        assertTrue(vertices.contains(new VertexLocation(0,1)));
        assertTrue(vertices.contains(new VertexLocation(0,2)));
        assertTrue(vertices.contains(new VertexLocation(1,0)));
        assertTrue(vertices.contains(new VertexLocation(1,1)));
        assertTrue(vertices.contains(new VertexLocation(1,2)));
    }

    @Test
    public void getVertices_WithNegative_Fail() {
        HexLocation hex = new HexLocation(-1, -1);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getVertices());
        assertEquals("Invalid hex location: (-1,-1)", ex.getMessage());
    }

    @Test
    public void getVertices_With4_4_Success() {
        HexLocation hex = new HexLocation(4, 4);
        List<VertexLocation> vertices = hex.getVertices();
        assertEquals(6, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(4,8)));
        assertTrue(vertices.contains(new VertexLocation(4,9)));
        assertTrue(vertices.contains(new VertexLocation(4,10)));
        assertTrue(vertices.contains(new VertexLocation(5,8)));
        assertTrue(vertices.contains(new VertexLocation(5,9)));
        assertTrue(vertices.contains(new VertexLocation(5,10)));
    }

    @Test
    public void getVertices_With5_5_Fail() {
        HexLocation hex = new HexLocation(5, 5);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getVertices());
        assertEquals("Invalid hex location: (5,5)", ex.getMessage());
    }

    @Test
    public void getVertices_WithMax_Fail() {
        HexLocation hex = new HexLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getVertices());
        assertEquals("Invalid hex location: (2147483647,2147483647)", ex.getMessage());
    }

    @Test
    public void getVertices_WithMin_Fail() {
        HexLocation hex = new HexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getVertices());
        assertEquals("Invalid hex location: (-2147483648,-2147483648)", ex.getMessage());
    }

    @Test
    public void getVertices_With3_2_Success() {
        HexLocation hex = new HexLocation(3, 2);
        List<VertexLocation> vertices = hex.getVertices();
        assertEquals(6, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(3,5)));
        assertTrue(vertices.contains(new VertexLocation(3,6)));
        assertTrue(vertices.contains(new VertexLocation(3,7)));
        assertTrue(vertices.contains(new VertexLocation(4,5)));
        assertTrue(vertices.contains(new VertexLocation(4,6)));
        assertTrue(vertices.contains(new VertexLocation(4,7)));
    }

    @Test
    public void getBorders_With0_0_Success() {
        HexLocation hex = new HexLocation(0, 0);
        List<BorderLocation> borders = hex.getBorders();
        assertEquals(6, borders.size());
        assertTrue(borders.contains(new BorderLocation(0,0)));
        assertTrue(borders.contains(new BorderLocation(0,1)));
        assertTrue(borders.contains(new BorderLocation(0,2)));
        assertTrue(borders.contains(new BorderLocation(1,-1)));
        assertTrue(borders.contains(new BorderLocation(1,1)));
        assertTrue(borders.contains(new BorderLocation(0,3)));
    }

    @Test
    public void getBorders_WithNegative_Fail() {
        HexLocation hex = new HexLocation(-1, -1);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getBorders());
        assertEquals("Invalid hex location: (-1,-1)", ex.getMessage());
    }

    @Test
    public void getBorders_With4_4_Success() {
        HexLocation hex = new HexLocation(4, 4);
        List<BorderLocation> borders = hex.getBorders();
        assertEquals(6, borders.size());
        assertTrue(borders.contains(new BorderLocation(4,12)));
        assertTrue(borders.contains(new BorderLocation(4,13)));
        assertTrue(borders.contains(new BorderLocation(4,14)));
        assertTrue(borders.contains(new BorderLocation(5,11)));
        assertTrue(borders.contains(new BorderLocation(5,13)));
        assertTrue(borders.contains(new BorderLocation(4,15)));
    }

    @Test
    public void getBorders_With5_5_Fail() {
        HexLocation hex = new HexLocation(5,5);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getBorders());
        assertEquals("Invalid hex location: (5,5)", ex.getMessage());
    }

    @Test
    public void getBorders_WithMax_Fail() {
        HexLocation hex = new HexLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getBorders());
        assertEquals("Invalid hex location: (2147483647,2147483647)", ex.getMessage());
    }

    @Test
    public void getBorders_WithMin_Fail() {
        HexLocation hex = new HexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> hex.getBorders());
        assertEquals("Invalid hex location: (-2147483648,-2147483648)", ex.getMessage());
    }

    @Test
    public void getBorders_With3_2_Success() {
        HexLocation hex = new HexLocation(3, 2);
        List<BorderLocation> borders = hex.getBorders();
        assertEquals(6, borders.size());
        assertTrue(borders.contains(new BorderLocation(3,7)));
        assertTrue(borders.contains(new BorderLocation(3,8)));
        assertTrue(borders.contains(new BorderLocation(3,9)));
        assertTrue(borders.contains(new BorderLocation(4,8)));
        assertTrue(borders.contains(new BorderLocation(4,10)));
        assertTrue(borders.contains(new BorderLocation(3,10)));
    }

    @Test
    public void equals_With1_2_And1_3_ReturnFalse() {
        HexLocation loc1 = new HexLocation(1, 2);
        HexLocation loc2 = new HexLocation(1, 3);
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

    @Test
    public void equals_With1_2_And2_2_ReturnFalse() {
        HexLocation loc1 = new HexLocation(1, 2);
        HexLocation loc2 = new HexLocation(2, 2);
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

    @Test
    public void equals_With1_2_And1_2_ReturnTrue() {
        HexLocation loc1 = new HexLocation(1, 2);
        HexLocation loc2 = new HexLocation(1, 2);
        assertTrue(loc1.equals(loc2));
        assertTrue(loc2.equals(loc1));
    }

    @Test
    public void isValid_With0_0_ReturnTrue() {
        HexLocation loc = new HexLocation(0, 0);
        assertTrue(loc.isValid());
    }

    @Test
    public void isValid_WithNegative1_0_ReturnFalse() {
        HexLocation loc = new HexLocation(-1, 0);
        assertFalse(loc.isValid());
    }

    @Test
    public void isValid_With0_Negative1_ReturnFalse() {
        HexLocation loc = new HexLocation(0, -1);
        assertFalse(loc.isValid());
    }

    @Test
    public void isValid_With5_0_ReturnFalse() {
        HexLocation loc = new HexLocation(5, 0);
        assertFalse(loc.isValid());
    }

    @Test
    public void isValid_With0_5_ReturnFalse() {
        HexLocation loc = new HexLocation(0, 5);
        assertFalse(loc.isValid());
    }

    @Test
    public void hashCode_With3_3_Success() {
        int expected = 38504087;
        HexLocation loc = new HexLocation(3, 3);
        assertEquals(expected, loc.hashCode());
    }

}
