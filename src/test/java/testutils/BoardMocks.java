package testutils;

import board.Board;
import org.easymock.EasyMock;

import java.util.Random;

public class BoardMocks {

    public static Board generateDefaultBoard() {
        // 19 hexagons
        // We'll ask for a number 19 times, with the number 0 signifying desert
        // We'll ask for a resource 18 times, since we'll not do it for desert
        // So 37 times we'll request a random number
        Random random = EasyMock.mock(Random.class);
        // Mock it to match the default board
        // https://myriadsgifts.com/cdn/shop/products/4e99c8a7-443f-5190-ac6f-4d704c2edeb8_1024x1024@2x.jpg?v=1586024528
        // See docs/design/board.png for the board
        // The board will call for the NUMBER FIRST, then ask for the RESOURCE
        EasyMock.expect(random.nextInt(19)).andReturn(13); // Ore 10
        EasyMock.expect(random.nextInt(18)).andReturn(15);

        EasyMock.expect(random.nextInt(18)).andReturn(0); // Sheep 2
        EasyMock.expect(random.nextInt(17)).andReturn(11);

        EasyMock.expect(random.nextInt(17)).andReturn(10); // Wood 9
        EasyMock.expect(random.nextInt(16)).andReturn(0);

        EasyMock.expect(random.nextInt(16)).andReturn(14); // Wheat 12
        EasyMock.expect(random.nextInt(15)).andReturn(6);

        EasyMock.expect(random.nextInt(15)).andReturn(6); // Brick 6
        EasyMock.expect(random.nextInt(14)).andReturn(3);

        EasyMock.expect(random.nextInt(14)).andReturn(2); // Sheep 4
        EasyMock.expect(random.nextInt(13)).andReturn(8);

        EasyMock.expect(random.nextInt(13)).andReturn(9); // Brick 10
        EasyMock.expect(random.nextInt(12)).andReturn(3);

        EasyMock.expect(random.nextInt(12)).andReturn(8); // Wheat 9
        EasyMock.expect(random.nextInt(11)).andReturn(4);

        EasyMock.expect(random.nextInt(11)).andReturn(8); // Wood 11
        EasyMock.expect(random.nextInt(10)).andReturn(0);

        EasyMock.expect(random.nextInt(10)).andReturn(9); // Desert

        EasyMock.expect(random.nextInt(9)).andReturn(0); // Wood 3
        EasyMock.expect(random.nextInt(9)).andReturn(0);

        EasyMock.expect(random.nextInt(8)).andReturn(5); // Ore 8
        EasyMock.expect(random.nextInt(8)).andReturn(6);

        EasyMock.expect(random.nextInt(7)).andReturn(5); // Wood 8
        EasyMock.expect(random.nextInt(7)).andReturn(0);

        EasyMock.expect(random.nextInt(6)).andReturn(0); // Ore 3
        EasyMock.expect(random.nextInt(6)).andReturn(5);

        EasyMock.expect(random.nextInt(5)).andReturn(0); // Wheat 4
        EasyMock.expect(random.nextInt(5)).andReturn(1);

        EasyMock.expect(random.nextInt(4)).andReturn(0); // Sheep 5
        EasyMock.expect(random.nextInt(4)).andReturn(2);

        EasyMock.expect(random.nextInt(3)).andReturn(0); // Brick 5
        EasyMock.expect(random.nextInt(3)).andReturn(0);

        EasyMock.expect(random.nextInt(2)).andReturn(0); // Wheat 6
        EasyMock.expect(random.nextInt(2)).andReturn(0);

        EasyMock.expect(random.nextInt(1)).andReturn(0); // Sheep 11
        EasyMock.expect(random.nextInt(1)).andReturn(0);

        EasyMock.replay(random);
        Board board = new Board();
        board.generate(random);
        EasyMock.verify(random);

        return board;
    }

}
