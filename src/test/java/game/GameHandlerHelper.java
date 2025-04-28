package game;

import board.location.HexLocation;

public class GameHandlerHelper {

	public static GameHandler robberMove(TurnPhase turn) {
		GameHandler game = new GameHandler(GameState.NORMALPLAY, turn, TurnMovementDirection.FORWARD);
		 game.moveRobberWithoutChecks(new HexLocation(3, 3));
		return game;
	}
}
