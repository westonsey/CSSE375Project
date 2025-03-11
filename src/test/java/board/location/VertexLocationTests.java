package board.location;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VertexLocationTests {

    @Test
    public void getHexes_With0_0_Success() {
        VertexLocation vertex = new VertexLocation(0,0);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(1, hexes.size());
        assertTrue(hexes.contains(new HexLocation(0,0)));
    }

    @Test
    public void getHexes_WithNegative_Fail() {
        VertexLocation vertex = new VertexLocation(-1,-1);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (-1,-1)", ex.getMessage());
    }

    @Test
    public void getHexes_With5_10_Success() {
        VertexLocation vertex = new VertexLocation(5,10);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(1, hexes.size());
        assertTrue(hexes.contains(new HexLocation(4,4)));
    }

    @Test
    public void getHexes_With6_11_Fail() {
        VertexLocation vertex = new VertexLocation(6,11);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (6,11)", ex.getMessage());
    }

    @Test
    public void getHexes_WithMax_Fail() {
        VertexLocation vertex = new VertexLocation(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (2147483647,2147483647)", ex.getMessage());
    }

    @Test
    public void getHexes_WithMin_Fail() {
        VertexLocation vertex = new VertexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (-2147483648,-2147483648)", ex.getMessage());
    }

    @Test
    public void getHexes_With3_3_Success() {
        VertexLocation vertex = new VertexLocation(3,3);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(3, hexes.size());
        assertTrue(hexes.contains(new HexLocation(2,1)));
        assertTrue(hexes.contains(new HexLocation(3,0)));
        assertTrue(hexes.contains(new HexLocation(3,1)));
    }

    @Test
    public void getHexes_With5_4_Success() {
        VertexLocation vertex = new VertexLocation(5,4);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(2, hexes.size());
        assertTrue(hexes.contains(new HexLocation(4,1)));
        assertTrue(hexes.contains(new HexLocation(4,2)));
    }

    @Test
    public void getHexes_With2_4_Success() {
        VertexLocation vertex = new VertexLocation(2,4);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(3, hexes.size());
        assertTrue(hexes.contains(new HexLocation(1,1)));
        assertTrue(hexes.contains(new HexLocation(2,1)));
        assertTrue(hexes.contains(new HexLocation(2,2)));
    }

    @Test
    public void getHexes_With2_3_Success() {
        VertexLocation vertex = new VertexLocation(2,3);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(3, hexes.size());
        assertTrue(hexes.contains(new HexLocation(1,0)));
        assertTrue(hexes.contains(new HexLocation(1,1)));
        assertTrue(hexes.contains(new HexLocation(2,1)));
    }

    @Test
    public void getHexes_With3_4_Success() {
        VertexLocation vertex = new VertexLocation(3,4);
        List<HexLocation> hexes = vertex.getHexes();
        assertEquals(3, hexes.size());
        assertTrue(hexes.contains(new HexLocation(2,1)));
        assertTrue(hexes.contains(new HexLocation(2,2)));
        assertTrue(hexes.contains(new HexLocation(3,1)));
    }

    @Test
    public void getHexes_With0_Negative_Fail() {
        VertexLocation vertex = new VertexLocation(0,-1);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (0,-1)", ex.getMessage());
    }

    @Test
    public void getHexes_WithNegative_0_Fail() {
        VertexLocation vertex = new VertexLocation(-1,0);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (-1,0)", ex.getMessage());
    }

    @Test
    public void getHexes_With6_10_Fail() {
        VertexLocation vertex = new VertexLocation(6,10);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (6,10)", ex.getMessage());
    }

    @Test
    public void getHexes_With5_11_Fail() {
        VertexLocation vertex = new VertexLocation(5,11);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getHexes());
        assertEquals("Invalid vertex location: (5,11)", ex.getMessage());
    }

    @Test
    public void getBorders_With0_0_Success() {
        VertexLocation vertex = new VertexLocation(0,0);
        List<BorderLocation> borders = vertex.getBorders();
        assertEquals(2, borders.size());
        assertTrue(borders.contains(new BorderLocation(0,0)));
        assertTrue(borders.contains(new BorderLocation(0,1)));
    }

    @Test
    public void getBorders_WithNegative_Fail() {
        VertexLocation vertex = new VertexLocation(-1,-1);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getBorders());
        assertEquals("Invalid vertex location: (-1,-1)", ex.getMessage());
    }

    @Test
    public void getBorders_With5_10_Success() {
        VertexLocation vertex = new VertexLocation(5,10);
        List<BorderLocation> borders = vertex.getBorders();
        assertEquals(3, borders.size());
        assertTrue(borders.contains(new BorderLocation(4,15)));
        assertTrue(borders.contains(new BorderLocation(5,13)));
        assertTrue(borders.contains(new BorderLocation(5,14)));
    }

    @Test
    public void getBorders_With6_11_Fail() {
        VertexLocation vertex = new VertexLocation(6,11);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getBorders());
        assertEquals("Invalid vertex location: (6,11)", ex.getMessage());
    }

    @Test
    public void getBorders_WithMax_Fail() {
        VertexLocation vertex = new VertexLocation(Integer.MAX_VALUE,Integer.MAX_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getBorders());
        assertEquals("Invalid vertex location: (2147483647,2147483647)", ex.getMessage());
    }

    @Test
    public void getBorders_WithMin_Fail() {
        VertexLocation vertex = new VertexLocation(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> vertex.getBorders());
        assertEquals("Invalid vertex location: (-2147483648,-2147483648)", ex.getMessage());
    }

    @Test
    public void getBorders_With3_3_Success() {
        VertexLocation vertex = new VertexLocation(3,3);
        List<BorderLocation> borders = vertex.getBorders();
        assertEquals(3, borders.size());
        assertTrue(borders.contains(new BorderLocation(3,2)));
        assertTrue(borders.contains(new BorderLocation(3,3)));
        assertTrue(borders.contains(new BorderLocation(3,4)));
    }

    @Test
    public void getBorders_With2_3_Success() {
        VertexLocation vertex = new VertexLocation(2,3);
        List<BorderLocation> borders = vertex.getBorders();
        assertEquals(3, borders.size());
        assertTrue(borders.contains(new BorderLocation(1,3)));
        assertTrue(borders.contains(new BorderLocation(2,4)));
        assertTrue(borders.contains(new BorderLocation(2,5)));
    }

    @Test
    public void getBorders_With2_4_Success() {
        VertexLocation vertex = new VertexLocation(2,4);
        List<BorderLocation> borders = vertex.getBorders();
        assertEquals(3, borders.size());
        assertTrue(borders.contains(new BorderLocation(2,5)));
        assertTrue(borders.contains(new BorderLocation(2,6)));
        assertTrue(borders.contains(new BorderLocation(2,7)));
    }

    @Test
    public void equals_With1_2_And1_3_ReturnFalse() {
        VertexLocation loc1 = new VertexLocation(1, 2);
        VertexLocation loc2 = new VertexLocation(1, 3);
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

    @Test
    public void equals_With1_2_And2_2_ReturnFalse() {
        VertexLocation loc1 = new VertexLocation(1, 2);
        VertexLocation loc2 = new VertexLocation(2, 2);
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

    @Test
    public void equals_With1_2_And1_2_ReturnTrue() {
        VertexLocation loc1 = new VertexLocation(1, 2);
        VertexLocation loc2 = new VertexLocation(1, 2);
        assertTrue(loc1.equals(loc2));
        assertTrue(loc2.equals(loc1));
    }

    @Test
    public void hashCode_With3_3_Success() {
        int expected = 38504087;
        VertexLocation loc = new VertexLocation(3, 3);
        assertEquals(expected, loc.hashCode());
    }
}
