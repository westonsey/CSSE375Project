package game;

import board.Board;
import board.location.HexLocation;

import java.util.List;
import java.util.Random;

public class RobberManager {
    private Robber robber;
    private Board board;
    private TurnPhase turnPhase;

    public RobberManager(Robber robber, Board board) {
        this.robber = robber;
        this.board = board;
    }

    public void moveRobber(HexLocation loc, TurnPhase turnPhase) {
        this.turnPhase = turnPhase;
        checkTurnPhaseValidMovingRobber();
        checkRobberMoveLocValid(loc);
        robber.moveLocation(loc);
    }

    private void checkTurnPhaseValidMovingRobber(){
        if (turnPhase != TurnPhase.MOVING_ROBBER) {
            throw new IllegalArgumentException("Cannot move robber (invalid state)");
        }
    }


	private void checkRobberMoveLocValid(HexLocation loc){
		if (!loc.isValid() || loc.equals(robber.getLoc())) {
			throw new IllegalArgumentException("Cannot move robber to (" + loc.getRow() + "," + loc.getCol() + ")");
		}
	}

    void getPlayersToStealFromThrowException(TurnPhase turnPhase) {
        if (turnPhase != TurnPhase.STEALING_RESOURCE) {
            throw new IllegalStateException("Cannot steal in this phase");
        }
    }

    List<Player> getPlayersToStealFromLoop(Player currentTurn, List<Player> adjacent){
        for (int i = 0; i < adjacent.size(); i++) {
            if (adjacent.get(i).equals(currentTurn) || adjacent.get(i).getTotalNumberOfResources() <= 0) {
                adjacent.remove(i);
                i--;
            }
        }
        return adjacent;
    }

    void stealResourceLoop(Player thief, Player victim, List<Resource> resources, Random rand, TurnPhase turnPhase) {
        this.turnPhase = turnPhase;
        while (this.turnPhase == TurnPhase.STEALING_RESOURCE) {
            int i = rand.nextInt(resources.size());
            Resource resource = resources.get(i);
            stealResourceLoopConditional(thief, victim, resource);
        }
    }

    private void stealResourceLoopConditional(Player thief, Player victim, Resource resource){
        if (victim.hasResource(resource)) {
            victim.removeResource(resource, 1);
            thief.addResource(resource, 1);
            turnPhase = TurnPhase.PLAYING_TURN;
        }
    }
}
