package board;

import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import org.easymock.EasyMock;
import game.Player;
import game.Resource;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static testutils.BoardMocks.generateDefaultBoard;

public class BoardTests {

    @Test
    public void generate_StructuredBoard() {
        // 19 hexagons
        // We'll ask for a number 19 times, with the number 0 signifying desert
        // We'll ask for a resource 18 times, since we'll not do it for desert
        // So 37 times we'll request a random number
        Random random = EasyMock.mock(Random.class);
        for (int i = 0; i < 19; i++) {
            // The upper bound will be the length of the list
            EasyMock.expect(random.nextInt(19 - i)).andReturn(0);
            if (18 - i > 0) {
                EasyMock.expect(random.nextInt(18 - i)).andReturn(0);
            }
        }
        EasyMock.replay(random);
        Board board = new Board();
        board.generate(random);
        EasyMock.verify(random);
        // Check that the board generated properly
        Hexagon hex;

        hex = board.getHexAt(new HexLocation(0,1));
        assertEquals(2, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(0,2));
        assertEquals(3, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(0,3));
        assertEquals(3, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(1,0));
        assertEquals(4, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(1,1));
        assertEquals(4, hex.number);
        assertEquals(Resource.BRICK, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(1,2));
        assertEquals(5, hex.number);
        assertEquals(Resource.BRICK, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(1,3));
        assertEquals(5, hex.number);
        assertEquals(Resource.BRICK, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(2,0));
        assertEquals(6, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,1));
        assertEquals(6, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,2));
        assertEquals(8, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,3));
        assertEquals(8, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(2,4));
        assertEquals(9, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(3,0));
        assertEquals(9, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(3,1));
        assertEquals(10, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(3,2));
        assertEquals(10, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(3,3));
        assertEquals(11, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(4,1));
        assertEquals(11, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(4,2));
        assertEquals(12, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(4,3));
        assertEquals(true, hex.isDesert);
    }

    @Test
    public void generate_DefaultBoard() {
        Board board = generateDefaultBoard();
        Hexagon hex;

        hex = board.getHexAt(new HexLocation(0,1));
        assertEquals(10, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(0,2));
        assertEquals(2, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(0,3));
        assertEquals(9, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(1,0));
        assertEquals(12, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(1,1));
        assertEquals(6, hex.number);
        assertEquals(Resource.BRICK, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(1,2));
        assertEquals(4, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(1,3));
        assertEquals(10, hex.number);
        assertEquals(Resource.BRICK, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(2,0));
        assertEquals(9, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,1));
        assertEquals(11, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,2));
        assertEquals(true, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,3));
        assertEquals(3, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(2,4));
        assertEquals(8, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(3,0));
        assertEquals(8, hex.number);
        assertEquals(Resource.WOOD, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(3,1));
        assertEquals(3, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(3,2));
        assertEquals(4, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(3,3));
        assertEquals(5, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);

        hex = board.getHexAt(new HexLocation(4,1));
        assertEquals(5, hex.number);
        assertEquals(Resource.BRICK, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(4,2));
        assertEquals(6, hex.number);
        assertEquals(Resource.WHEAT, hex.resource);
        assertEquals(false, hex.isDesert);
        hex = board.getHexAt(new HexLocation(4,3));
        assertEquals(11, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
    }

    @Test
    public void getHex_0_1_Success() {
        Board board = generateDefaultBoard();
        Hexagon hex = board.getHexAt(new HexLocation(0, 1));
        assertEquals(10, hex.number);
        assertEquals(Resource.ORE, hex.resource);
        assertEquals(false, hex.isDesert);
    }

    @Test
    public void getHex_4_3_Success() {
        Board board = generateDefaultBoard();
        Hexagon hex = board.getHexAt(new HexLocation(4, 3));
        assertEquals(11, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
    }

    @Test
    public void getHex_1_2_Success() {
        Board board = generateDefaultBoard();
        Hexagon hex = board.getHexAt(new HexLocation(1, 2));
        assertEquals(4, hex.number);
        assertEquals(Resource.SHEEP, hex.resource);
        assertEquals(false, hex.isDesert);
    }

    @Test
    public void getHex_2_2_Success() {
        Board board = generateDefaultBoard();
        Hexagon hex = board.getHexAt(new HexLocation(2, 2));
        assertEquals(true, hex.isDesert);
    }

    @Test
    public void getHex_0_0_Fail() {
        Board board = generateDefaultBoard();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> board.getHexAt(new HexLocation(0, 0)));
        String expected = "Invalid board hex location: (0, 0)";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void getHex_4_4_Fail() {
        Board board = generateDefaultBoard();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> board.getHexAt(new HexLocation(4, 4)));
        String expected = "Invalid board hex location: (4, 4)";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void getHex_Max_Fail() {
        Board board = generateDefaultBoard();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.getHexAt(new HexLocation(Integer.MAX_VALUE, Integer.MAX_VALUE)));
        String expected = "Invalid board hex location: (2147483647, 2147483647)";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void getHex_Min_Fail() {
        Board board = generateDefaultBoard();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.getHexAt(new HexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE)));
        String expected = "Invalid board hex location: (-2147483648, -2147483648)";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void getHex_UngeneratedBoard_Fail() {
        Board board = new Board();
        Exception ex = assertThrows(IllegalStateException.class,
                () -> board.getHexAt(new HexLocation(2, 2)));
        String expected = "Cannot get hex from an ungenerated board";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void canPlaceSettlement_withMin_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_withMin_withRandomRoad_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(1,8), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(2, 2), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_withMax_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(Integer.MAX_VALUE, Integer.MAX_VALUE), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_with0_2_AndRoad_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(0, 3), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(0, 2), false);
        assertTrue(result);
    }

    @Test
    public void canPlaceSettlement_with5_8_AndRoad_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(4, 12), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(5, 8), false);
        assertTrue(result);
    }

    @Test
    public void canPlaceSettlement_with5_8_AndOtherPlayerRoad_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        board.placeRoad(player2, new BorderLocation(4, 12), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(5, 8), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_with2_2_AndNoRoad_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(2, 2), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_with2_2_AndCloseSettlement_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(2, 2), true);
        board.placeSettlement(player1, new VertexLocation(2, 1), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(2, 2), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_with2_2_AndAlreadyASettlement_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(2, 2), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_with2_2_AndCloseSettlementAndForce_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(2, 2), true);
        board.placeSettlement(player1, new VertexLocation(2, 1), true);
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(2, 2), true);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_withMinAndForce_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), true);
        assertFalse(result);
    }

    @Test
    public void canPlaceSettlement_with2_2_AndNoRoadAndForce_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceSettlement(player1, new VertexLocation(2, 2), true);
        assertTrue(result);
    }

    @Test
    public void placeSettlement_withMin_withEmptyBoard_ForceFalse_ThrowIllegalArgumentException() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.placeSettlement(player1, new VertexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), false));
        String expected = "Cannot place settlement at (-2147483648, -2147483648)";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void placeSettlement_withMin_withEmptyBoard_ForceTrue_ThrowIllegalArgumentException() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.placeSettlement(player1, new VertexLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), true));
        String expected = "Cannot place settlement at (-2147483648, -2147483648)";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void placeSettlement_with2_2_withRoad2_2_ForceFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(2, 2), true);
        board.placeSettlement(player1, new VertexLocation(2, 2), false);
        List<Building> buildings = board.getBuildingsForPlayer(player1);
        assertEquals(1, buildings.size());
        assertTrue(buildings.stream()
                .anyMatch(b -> b.getLocation().getRow() == 2 && b.getLocation().getCol() == 2));
    }

    @Test
    public void placeSettlement_with2_2_withEmptyBoard_ForceTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        List<Building> buildings = board.getBuildingsForPlayer(player1);
        assertEquals(1, buildings.size());
        assertTrue(buildings.stream()
                .anyMatch(b -> b.getLocation().getRow() == 2 && b.getLocation().getCol() == 2));

    }

    @Test
    public void canPlaceRoad_withMin_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceRoad(player1, new BorderLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_with_Random_Building_False() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(2,2), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(0, 3), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_with_Random_Road_False() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(1,8), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(0, 3), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_withMax_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceRoad(player1, new BorderLocation(Integer.MAX_VALUE, Integer.MAX_VALUE), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_with0_3_HasSettlement_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(0, 2), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(0, 3), false);
        assertTrue(result);
    }

    @Test
    public void canPlaceRoad_with4_12_HasSettlement_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(5, 8), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(4, 12), false);
        assertTrue(result);
    }

    @Test
    public void canPlaceRoad_with4_12_HasOpponentSettlement_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        board.placeSettlement(player2, new VertexLocation(5, 8), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(4, 12), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_with4_12_HasRoad_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(4, 11), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(4, 12), false);
        assertTrue(result);
    }

    @Test
    public void canPlaceRoad_with4_12_HasOpponentRoad_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        board.placeRoad(player2, new BorderLocation(4, 11), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(4, 12), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_with4_12_OnEmpty_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceRoad(player1, new BorderLocation(4, 12), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_with4_12_AndAlreadyHasRoad_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(4, 12), true);
        boolean result = board.canPlaceRoad(player1, new BorderLocation(4, 12), false);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_withMinAndForce_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceRoad(player1, new BorderLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), true);
        assertFalse(result);
    }

    @Test
    public void canPlaceRoad_withEmptyBoardAndForce_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        boolean result = board.canPlaceRoad(player1, new BorderLocation(3, 3), true);
        assertTrue(result);
    }

    @Test
    public void placeRoad_WithMin_Fail() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.placeRoad(player1, new BorderLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), false));
        assertEquals("Cannot place road at (-2147483648, -2147483648)", ex.getMessage());
    }

    @Test
    public void placeRoad_WithMinForce_Fail() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.placeRoad(player1, new BorderLocation(Integer.MIN_VALUE, Integer.MIN_VALUE), true));
        assertEquals("Cannot place road at (-2147483648, -2147483648)", ex.getMessage());
    }

    @Test
    public void placeRoad_WithExistingRoad_Success() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(2, 1), true);
        board.placeRoad(player1, new BorderLocation(2, 2), false);
        // Check that the desired roads exist
        List<Road> roads = board.getRoadsForPlayer(player1);
        assertEquals(2, roads.size());
        assertTrue(roads.stream()
                .anyMatch(r -> r.getLocation().getRow() == 2 && r.getLocation().getCol() == 1));
        assertTrue(roads.stream()
                .anyMatch(r -> r.getLocation().getRow() == 2 && r.getLocation().getCol() == 2));
    }

    @Test
    public void placeRoad_WithValidForce_Success() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeRoad(player1, new BorderLocation(2, 2), true);
        // Check that the desired road exists
        List<Road> roads = board.getRoadsForPlayer(player1);
        assertEquals(1, roads.size());
        assertTrue(roads.stream()
                .anyMatch(r -> r.getLocation().getRow() == 2 && r.getLocation().getCol() == 2));
    }

    @Test
    public void getHexesAtNumber_WithDiceRoll1_ReturnEmptyList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = EasyMock.createMock(List.class);
        EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(1, hexList);

        EasyMock.replay(mockHexList);

        //assert size equals 0
        assertEquals(mockHexList.size(), actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll2_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(2, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(1, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll3_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(3, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll4_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(4, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll5_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(5, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll6_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(6, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll7_ReturnEmptyHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(7, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(0, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll8_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        //EasyMock.expect(mockHexList.size()).andReturn(0).anyTimes();
        //EasyMock.expect(mockHexList.isEmpty()).andReturn(true).anyTimes();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(8, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll9_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(9, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll10_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(10, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll11_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(11, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(2, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll12_ReturnHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(12, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(1, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void getHexesAtNumber_WithDiceRoll13_ReturnEmptyHexList() {
        Board board = generateDefaultBoard();
        List<Hexagon> mockHexList = EasyMock.createMock(List.class);
        List<Hexagon> hexList = new ArrayList<>();
        List<Hexagon> actualHexesAtVal = board.getHexesAtNumber(13, hexList);

        EasyMock.replay(mockHexList);

        assertEquals(0, actualHexesAtVal.size());

        EasyMock.verify(mockHexList);

    }

    @Test
    public void addPlayerResourcesFromHex_With0BuildingsHex_AddNoResourceToPlayer() {
        Board board = generateDefaultBoard();
        List<Hexagon> hexLst = board.getHexList();
        Player mockPlayer = EasyMock.createMock(Player.class);
        EasyMock.expect(mockPlayer.getResourceCount(hexLst.get(0).resource)).andReturn(0);
        board.addPlayerResourcesFromHex(hexLst.get(0), 5, 2);
        assertEquals(0, mockPlayer.getResourceCount(hexLst.get(0).resource));
    }

    @Test
    public void addPlayerResourcesFromHex_With1BuildingsHex_Add1ResourceToPlayer() {
        Board board = generateDefaultBoard();
        List<Hexagon> hexLst = board.getHexList();
        Player player1 = new Player();
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(0), true);
        board.addPlayerResourcesFromHex(hexLst.get(0), 5, 2);
        assertEquals(1, player1.getResourceCount(hexLst.get(0).resource));
    }

    @Test
    public void addPlayerResourcesFromHex_With1CityHex_Add1ResourceToPlayer() {
        Board board = generateDefaultBoard();
        List<Hexagon> hexLst = board.getHexList();
        Player player1 = new Player();
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(0), true);
        board.upgradeSettlement((Settlement)board.buildings.get(0));
        board.addPlayerResourcesFromHex(hexLst.get(0), 5, 2);
        assertEquals(2, player1.getResourceCount(hexLst.get(0).resource));
    }

    @Test
    public void addPlayerResourcesFromHex_With2BuildingsHex_Add2ResourceToPlayer() {
        Board board = generateDefaultBoard();
        List<Hexagon> hexLst = board.getHexList();
        Player player1 = new Player();
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(0), true);
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(2), true);
        board.addPlayerResourcesFromHex(hexLst.get(0), 5, 2);

        assertEquals(2, player1.getResourceCount(hexLst.get(0).resource));
    }

    @Test
    public void addPlayerResourcesFromHex_With3BuildingsHex_Add3ResourceToPlayer() {
        Board board = generateDefaultBoard();
        List<Hexagon> hexLst = board.getHexList();
        Player player1 = new Player();
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(0), true);
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(2), true);
        board.placeSettlement(player1, hexLst.get(0).getVertices().get(4), true);
        board.addPlayerResourcesFromHex(hexLst.get(0), 5, 2);

        assertEquals(3, player1.getResourceCount(hexLst.get(0).resource));
    }

    @Test
    public void canUpgradeSettlement_WithEmptyBoard_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player1);
        boolean result = board.canUpgradeSettlement(s);
        assertFalse(result);
    }

    @Test
    public void canUpgradeSettlement_WithOneSettlement_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player1);
        board.placeSettlement(player1, new VertexLocation(1, 1), true);
        boolean result = board.canUpgradeSettlement(s);
        assertTrue(result);
    }

    @Test
    public void canUpgradeSettlement_WithRefToOtherPlayer_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player2);
        board.placeSettlement(player1, new VertexLocation(1, 1), true);
        boolean result = board.canUpgradeSettlement(s);
        assertFalse(result);
    }

    @Test
    public void canUpgradeSettlement_WithTwoSettlements_ReturnTrue() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player1);
        board.placeSettlement(player1, new VertexLocation(1, 1), true);
        board.placeSettlement(player2, new VertexLocation(3, 3), true);
        boolean result = board.canUpgradeSettlement(s);
        assertTrue(result);
    }

    @Test
    public void canUpgradeSettlement_WithCity_ReturnFalse() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player1);
        board.placeSettlement(player1, new VertexLocation(1, 1), true);
        board.upgradeSettlement(s); // Upgrade once to get it to a city
        boolean result = board.canUpgradeSettlement(s);
        assertFalse(result);
    }

    @Test
    public void upgradeSettlement_WithOneSettlement_Success() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player1);
        board.placeSettlement(player1, new VertexLocation(1, 1), true);
        board.upgradeSettlement(s);

        List<Building> buildings = board.getBuildingsForPlayer(player1);
        assertEquals(1, buildings.size());
        assertTrue(buildings.stream().anyMatch(b -> b instanceof City && b.getOwner() == player1 &&
                b.getLocation().equals(new VertexLocation(1, 1))));
    }

    @Test
    public void upgradeSettlement_WithEmptyBoard_Fail() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement s = new Settlement(new VertexLocation(1, 1), player1);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> board.upgradeSettlement(s));
        assertEquals("No settlement found at (1, 1) to upgrade", ex.getMessage());
    }

    @Test
    public void getPort_3for1() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(0, 2), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.THREE_FOR_ONE, portType);
    }

    @Test
    public void getPort_3for1_2() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(0, 3), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.THREE_FOR_ONE, portType);
    }

    @Test
    public void getPort_Wood() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(1, 1), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.WOOD, portType);
    }

    @Test
    public void getPort_Brick() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(3, 1), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.BRICK, portType);
    }

    @Test
    public void getPort_Sheep() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(4, 8), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.SHEEP, portType);
    }

    @Test
    public void getPort_Ore() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(1, 8), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.ORE, portType);
    }

    @Test
    public void getPort_Wheat() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(0, 5), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(PortType.WHEAT, portType);
    }

    @Test
    public void getPort_ReturnNull() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Settlement settlement = new Settlement(new VertexLocation(2, 9), player1);
        PortType portType = board.getPort(settlement).getPortType();
        assertEquals(null, portType);
    }

    @Test
    public void addResourceForGameSetup_Player1_Location2_2_AddResources() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        board.addResourceForGameSetup(player1, new VertexLocation(2, 2));
        assertEquals(3, player1.getTotalNumberOfResources());
    }

    @Test
    public void addResourceForGameSetup_Player1_Location0_2_AddResource() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(0, 2), true);
        board.addResourceForGameSetup(player1, new VertexLocation(0, 2));
        assertEquals(1, player1.getTotalNumberOfResources());
    }

    @Test
    public void getAdjacentPlayers_WithNegativeHex_ThrowsException() {
        Board board = generateDefaultBoard();
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> board.getAdjacentPlayers(new HexLocation(-1,-1)));
        assertEquals("Invalid hex location (-1,-1)", ex.getMessage());
    }

    @Test
    public void getAdjacentPlayers_WithNoSettlements_ReturnEmpty() {
        Board board = generateDefaultBoard();
        List<Player> players = board.getAdjacentPlayers(new HexLocation(2, 1));
        assertEquals(0, players.size());
    }

    @Test
    public void getAdjacentPlayers_WithOneSettlement_ReturnPlayer1() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        List<Player> players = board.getAdjacentPlayers(new HexLocation(2, 1));
        assertEquals(1, players.size());
        assertTrue(players.contains(player1));
    }

    @Test
    public void getAdjacentPlayers_WithTwoSettlements_ReturnTwoPlayers() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        board.placeSettlement(player2, new VertexLocation(2, 4), true);
        List<Player> players = board.getAdjacentPlayers(new HexLocation(2, 1));
        assertEquals(2, players.size());
        assertTrue(players.contains(player1));
        assertTrue(players.contains(player2));
    }

    @Test
    public void getAdjacentPlayers_WithThreeSettlements_ReturnTwoPlayers() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        board.placeSettlement(player1, new VertexLocation(3, 3), true);
        board.placeSettlement(player2, new VertexLocation(2, 4), true);
        List<Player> players = board.getAdjacentPlayers(new HexLocation(2, 1));
        assertEquals(2, players.size());
        assertTrue(players.contains(player1));
        assertTrue(players.contains(player2));
    }

    @Test
    public void getAdjacentPlayers_WithPlayerNotNear_ReturnPlayer1() {
        Board board = generateDefaultBoard();
        Player player1 = new Player();
        Player player2 = new Player();
        board.placeSettlement(player1, new VertexLocation(2, 2), true);
        board.placeSettlement(player2, new VertexLocation(3, 5), true);
        List<Player> players = board.getAdjacentPlayers(new HexLocation(2, 1));
        assertEquals(1, players.size());
        assertTrue(players.contains(player1));
    }

    @Test
    public void isBorderValid_WithSmallRow_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(Integer.MIN_VALUE, 0));
        assertFalse(result);
    }

    @Test
    public void isBorderValid_WithSmallCol_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(0, Integer.MIN_VALUE));
        assertFalse(result);
    }

    @Test
    public void isBorderValid_WithVerticalBottomRow_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(5, 3));
        assertFalse(result);
    }

    @Test
    public void isBorderValid_WithValidBorder_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(0, 3));
        assertTrue(result);
    }

    @Test
    public void isBorderValid_WithValidBorder_5_2_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(5, 2));
        assertTrue(result);
    }

    @Test
    public void isBorderValid_WithValidBorder_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(2, 2));
        assertTrue(result);
    }

    @Test
    public void isBorderValid_WithLowRow_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(0, 3));
        assertTrue(result);
    }

    @Test
    public void isBorderValid_WithHighRow_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(4, 2));
        assertTrue(result);
    }

    @Test
    public void isBorderValid_WithHighCol_1_0_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(1, 0));
        assertTrue(result);
    }

    @Test
    public void isBorderValid_WithLowCol_1_12_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isBorderValid(new BorderLocation(1, 12));
        assertTrue(result);
    }

    @Test
    public void isHexValid_WithSmallRow_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(Integer.MIN_VALUE, 0));
        assertFalse(result);
    }

    @Test
    public void isHexValid_WithLargeRow_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(Integer.MAX_VALUE, 0));
        assertFalse(result);
    }

    @Test
    public void isHexValid_WithSmallCol_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(0, Integer.MIN_VALUE));
        assertFalse(result);
    }

    @Test
    public void isHexValid_WithLargeCol_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(0, Integer.MAX_VALUE));
        assertFalse(result);
    }

    @Test
    public void isHexValid_WithLowRow_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(0, 3));
        assertTrue(result);
    }

    @Test
    public void isHexValid_WithHighRow_ReturnTrue() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(4, 2));
        assertTrue(result);
    }

    @Test
    public void isHexValid_WithValidHex_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isHexValid(new HexLocation(2, 2));
        assertTrue(result);
    }

    @Test
    public void isVertexValid_WithSmallRow_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isVertexValid(new VertexLocation(Integer.MIN_VALUE, 0));
        assertFalse(result);
    }

    @Test
    public void isVertexValid_WithLargeRow_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isVertexValid(new VertexLocation(Integer.MAX_VALUE, 0));
        assertFalse(result);
    }

    @Test
    public void isVertexValid_WithSmallCol_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isVertexValid(new VertexLocation(0, Integer.MIN_VALUE));
        assertFalse(result);
    }

    @Test
    public void isVertexValid_WithLargeCol_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isVertexValid(new VertexLocation(0, Integer.MAX_VALUE));
        assertFalse(result);
    }

    @Test
    public void isVertexValid_WithValidVertex_ReturnFalse() {
        Board board = new Board();
        boolean result = board.isVertexValid(new VertexLocation(2, 2));
        assertTrue(result);
    }

    @Test
    public void getPortsIsNotEmpty() {
        Board board = new Board();
        assertFalse(board.getPorts().isEmpty());
    }

    @Test void getMinVertexCol_Row0_Ret2(){
        Board board = new Board();
        assertEquals(2, board.getMinVertexCol(0));
    }

    @Test void getMinVertexCol_Row1_Ret1(){
        Board board = new Board();
        assertEquals(1, board.getMinVertexCol(1));
    }

    @Test void getMinVertexCol_Row2_Ret0(){
        Board board = new Board();
        assertEquals(0, board.getMinVertexCol(2));
    }

    @Test void getMinVertexCol_Row3__Ret0(){
        Board board = new Board();
        assertEquals(0, board.getMinVertexCol(3));
    }

    @Test void getMinVertexCol_Row4_Ret1(){
        Board board = new Board();
        assertEquals(1, board.getMinVertexCol(4));
    }

    @Test void getMinVertexCol_Row5_Ret2(){
        Board board = new Board();
        assertEquals(2, board.getMinVertexCol(5));
    }

    @Test void getMinBorderCol_Row0_Ret3(){
        Board board = new Board();
        assertEquals(3, board.getMinBorderCol(0));
    }

    @Test void getMinBorderCol_Row1_Ret0(){
        Board board = new Board();
        assertEquals(0, board.getMinBorderCol(1));
    }

    @Test void getMinBorderCol_Row2_Ret0(){
        Board board = new Board();
        assertEquals(0, board.getMinBorderCol(2));
    }

    @Test void getMinBorderCol_Row3__Retneg1(){
        Board board = new Board();
        assertEquals(-1, board.getMinBorderCol(3));
    }

    @Test void getMinBorderCol_Row4_Ret2(){
        Board board = new Board();
        assertEquals(2, board.getMinBorderCol(4));
    }

    @Test void getMinBorderCol_Row5_Ret2(){
        Board board = new Board();
        assertEquals(2, board.getMinBorderCol(5));
    }

    @Test void isBorderOccupied_Occupied_True(){
        Board board = new Board();
        Player p = new Player();
        board.placeRoad(p, new BorderLocation(2,2), true);
        assertTrue(board.isBorderOccupied(new BorderLocation(2,2)));
    }

    @Test void isBorderOccupied_Occupied_False(){
        Board board = new Board();
        assertFalse(board.isBorderOccupied(new BorderLocation(2,2)));
    }

    @Test void getIndexOfBuild_return_index(){
        Board board = new Board();
        Building b = new Settlement(new VertexLocation(2, 2), new Player());
        Building b1 = new Settlement(new VertexLocation(2, 3), new Player());
        board.buildings.add(b);
        board.buildings.add(b1);
        assertEquals(1, board.getIndexOfBuilding(b1));
    }

    @Test void getIndexOfBuild_return_negative_1(){
        Board board = new Board();
        Building b = new Settlement(new VertexLocation(2, 2), new Player());
        assertEquals(-1, board.getIndexOfBuilding(b));
    }
}
