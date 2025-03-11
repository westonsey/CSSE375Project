package board.location;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BorderLocationTests {

    @Test
    public void getVertices_With0_0_Success() {
        BorderLocation border = new BorderLocation(0,0);
        List<VertexLocation> vertices = border.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(0,0)));
        assertTrue(vertices.contains(new VertexLocation(1,0)));
    }

    @Test
    public void getVertices_With1_Negative1_Success() {
        BorderLocation border = new BorderLocation(1,-1);
        List<VertexLocation> vertices = border.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(1,0)));
        assertTrue(vertices.contains(new VertexLocation(1,1)));
    }

    @Test
    public void getVertices_WithNegative_Fail() {
        BorderLocation border = new BorderLocation(-1, -1);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getVertices());
        assertEquals("Invalid border location: (-1,-1)", ex.getMessage());
    }

    @Test
    public void getVertices_With4_15_Success() {
        BorderLocation border = new BorderLocation(4,15);
        List<VertexLocation> vertices = border.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(4,10)));
        assertTrue(vertices.contains(new VertexLocation(5,10)));
    }

    @Test
    public void getVertices_With5_15_Fail() {
        BorderLocation border = new BorderLocation(5,16);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getVertices());
        assertEquals("Invalid border location: (5,16)", ex.getMessage());
    }

    @Test
    public void getVertices_WithMax_Fail() {
        BorderLocation border = new BorderLocation(Integer.MAX_VALUE,Integer.MAX_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getVertices());
        assertEquals("Invalid border location: (2147483647,2147483647)", ex.getMessage());
    }

    @Test
    public void getVertices_WithMin_Fail() {
        BorderLocation border = new BorderLocation(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getVertices());
        assertEquals("Invalid border location: (-2147483648,-2147483648)", ex.getMessage());
    }

    @Test
    public void getVertices_With1_12_Success() {
        BorderLocation border = new BorderLocation(1,12);
        List<VertexLocation> vertices = border.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(1,9)));
        assertTrue(vertices.contains(new VertexLocation(2,9)));
    }

    @Test
    public void getVertices_With1_11_Success() {
        BorderLocation border = new BorderLocation(1,11);
        List<VertexLocation> vertices = border.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(1,8)));
        assertTrue(vertices.contains(new VertexLocation(1,9)));
    }

    @Test
    public void getVertices_With1_10_Success() {
        BorderLocation border = new BorderLocation(1,10);
        List<VertexLocation> vertices = border.getVertices();
        assertEquals(2, vertices.size());
        assertTrue(vertices.contains(new VertexLocation(1,7)));
        assertTrue(vertices.contains(new VertexLocation(1,8)));
    }

    @Test
    public void getVertices_With5_3_Fail() {
        BorderLocation border = new BorderLocation(5,3);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getVertices());
        assertEquals("Invalid border location: (5,3)", ex.getMessage());
    }

    // ===================================================================

    @Test
    public void getBorders_With0_0_Success() {
        BorderLocation border = new BorderLocation(0,0);
        List<BorderLocation> borders = border.getBorders();
        assertEquals(2, borders.size());
        assertTrue(borders.contains(new BorderLocation(0,1)));
        assertTrue(borders.contains(new BorderLocation(1,-1)));
    }

    @Test
    public void getBorders_With1_Negative1_Success() {
        BorderLocation border = new BorderLocation(1,-1);
        List<BorderLocation> borders = border.getBorders();
        assertEquals(3, borders.size());
        assertTrue(borders.contains(new BorderLocation(0,0)));
        assertTrue(borders.contains(new BorderLocation(1,0)));
        assertTrue(borders.contains(new BorderLocation(1,1)));
    }

    @Test
    public void getBorders_WithNegative_Fail() {
        BorderLocation border = new BorderLocation(-1, -1);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getBorders());
        assertEquals("Invalid border location: (-1,-1)", ex.getMessage());
    }

    @Test
    public void getBorders_With4_15_Success() {
        BorderLocation border = new BorderLocation(4,15);
        List<BorderLocation> borders = border.getBorders();
        assertEquals(3, borders.size());
        assertTrue(borders.contains(new BorderLocation(4,14)));
        assertTrue(borders.contains(new BorderLocation(5,13)));
        assertTrue(borders.contains(new BorderLocation(5,14)));
    }

    @Test
    public void getBorders_WithLarge_Fail() {
        BorderLocation border = new BorderLocation(5,16);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getBorders());
        assertEquals("Invalid border location: (5,16)", ex.getMessage());
    }

    @Test
    public void getBorders_WithMax_Fail() {
        BorderLocation border = new BorderLocation(Integer.MAX_VALUE,Integer.MAX_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getBorders());
        assertEquals("Invalid border location: (2147483647,2147483647)", ex.getMessage());
    }

    @Test
    public void getBorders_WithMin_Fail() {
        BorderLocation border = new BorderLocation(Integer.MIN_VALUE,Integer.MIN_VALUE);
        Exception ex = assertThrows(IllegalStateException.class, () -> border.getBorders());
        assertEquals("Invalid border location: (-2147483648,-2147483648)", ex.getMessage());
    }

    @Test
    public void getBorders_With1_12_Success() {
        BorderLocation border = new BorderLocation(1,12);
        List<BorderLocation> borders = border.getBorders();
        assertEquals(4, borders.size());
        assertTrue(borders.contains(new BorderLocation(1,11)));
        assertTrue(borders.contains(new BorderLocation(1,13)));
        assertTrue(borders.contains(new BorderLocation(2,13)));
        assertTrue(borders.contains(new BorderLocation(2,14)));
    }

    @Test
    public void getBorders_With1_11_Success() {
        BorderLocation border = new BorderLocation(1,11);
        List<BorderLocation> borders = border.getBorders();
        assertEquals(4, borders.size());
        assertTrue(borders.contains(new BorderLocation(1,12)));
        assertTrue(borders.contains(new BorderLocation(1,10)));
        assertTrue(borders.contains(new BorderLocation(0,12)));
        assertTrue(borders.contains(new BorderLocation(1,13)));
    }

    @Test
    public void getBorders_With1_10_Success() {
        BorderLocation border = new BorderLocation(1,10);
        List<BorderLocation> borders = border.getBorders();
        assertEquals(4, borders.size());
        assertTrue(borders.contains(new BorderLocation(0,12)));
        assertTrue(borders.contains(new BorderLocation(1,11)));
        assertTrue(borders.contains(new BorderLocation(1,8)));
        assertTrue(borders.contains(new BorderLocation(1,9)));
    }

    @Test
    public void equals_With1_2_And1_3_ReturnFalse() {
        BorderLocation loc1 = new BorderLocation(1, 2);
        BorderLocation loc2 = new BorderLocation(1, 3);
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

    @Test
    public void equals_With1_2_And2_2_ReturnFalse() {
        BorderLocation loc1 = new BorderLocation(1, 2);
        BorderLocation loc2 = new BorderLocation(2, 2);
        assertFalse(loc1.equals(loc2));
        assertFalse(loc2.equals(loc1));
    }

    @Test
    public void equals_With1_2_And1_2_ReturnTrue() {
        BorderLocation loc1 = new BorderLocation(1, 2);
        BorderLocation loc2 = new BorderLocation(1, 2);
        assertTrue(loc1.equals(loc2));
        assertTrue(loc2.equals(loc1));
    }

    @Test
    public void hashCode_With3_3_Success() {
        int expected = 38504087;
        BorderLocation loc = new BorderLocation(3, 3);
        assertEquals(expected, loc.hashCode());
    }

    @Test
    public void isValid_WithBigRow_False() {
        boolean value = new BorderLocation(6, 0).isValid();
        assertFalse(value);
    }

    @Test
    public void isValid_WithSmallRow_False() {
        boolean value = new BorderLocation(-1, 0).isValid();
        assertFalse(value);
    }

    @Test
    public void isValid_WithBigCol_False() {
        boolean value = new BorderLocation(0, 16).isValid();
        assertFalse(value);
    }

    @Test
    public void isValid_WithSmallCol_False() {
        boolean value = new BorderLocation(1, -2).isValid();
        assertFalse(value);
    }

    @Test
    public void isValid_WithIllegalVertical_False() {
        boolean value = new BorderLocation(5, 3).isValid();
        assertFalse(value);
    }

    @Test
    public void isValid_WithIllegalNegative_False() {
        boolean value = new BorderLocation(2, -1).isValid();
        assertFalse(value);
    }

    @Test
    public void isValid_WithValidBorder_True() {
        boolean value = new BorderLocation(1, 1).isValid();
        assertTrue(value);
    }

}
