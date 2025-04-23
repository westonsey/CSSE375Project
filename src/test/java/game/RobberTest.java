package game;

import board.location.HexLocation;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RobberTest {

    @Test
    public void testMoveRobber_NewLoc_2_2_Update(){
        Robber r = new Robber(new HexLocation(1,0));
        r.moveLocation(new HexLocation(2,2));
        assertEquals(r.getLoc(), new HexLocation(2,2));
    }

    @Test
    public void testMoveRobber_NewLoc_10_10_IllegalStateException(){
        HexLocation oldHex = EasyMock.createMock(HexLocation.class);
        EasyMock.expect(oldHex.getRow()).andReturn(0);
        EasyMock.expect(oldHex.getCol()).andReturn(1);
        Robber r = new Robber(oldHex);

        HexLocation newHex = EasyMock.createMock(HexLocation.class);
        EasyMock.expect(newHex.getRow()).andReturn(10);
        EasyMock.expect(newHex.getCol()).andReturn(10);

        assertThrows(IllegalStateException.class, () -> r.moveLocation(newHex));

    }

}
