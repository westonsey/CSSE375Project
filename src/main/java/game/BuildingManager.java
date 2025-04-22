package game;

import board.Board;
import board.Road;
import board.Settlement;
import board.location.BorderLocation;
import board.location.VertexLocation;
import util.Tuple;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BuildingManager {
    private final Board board;
    private final ActionHandler actionHandler;
    private final PlayerTurnManager playerTurnManager;

    public BuildingManager(Board board, ActionHandler actionHandler, PlayerTurnManager playerTurnManager) {
        this.board = board;
        this.actionHandler = actionHandler;
        this.playerTurnManager = playerTurnManager;
    }

    public boolean canPlaceSettlement(Player p, VertexLocation loc) {
        boolean force = playerTurnManager.getGameState() == GameState.SETUP;
        boolean requiresResources = playerTurnManager.getTurnPhase() == TurnPhase.PLAYING_TURN;
        return actionHandler.canPlaceSettlementConditional(p, loc, force, requiresResources, playerTurnManager.getTurnPhase());
    }

    public boolean canPlaceRoad(Player p, BorderLocation loc) {
        boolean requiresResources = playerTurnManager.getTurnPhase() == TurnPhase.PLAYING_TURN;
        TurnPhase currentPhase = playerTurnManager.getTurnPhase();
        if (currentPhase == TurnPhase.PLACING_ROAD || currentPhase == TurnPhase.PLAYING_TURN) {
            return actionHandler.canPlaceRoadRequiresResources(p, loc, requiresResources);
        }
        return false;
    }

    public boolean canUpgradeSettlement(Settlement s) {
        Player owner = s.getOwner();
        return board.canUpgradeSettlement(s) && playerTurnManager.getTurnPhase() == TurnPhase.PLAYING_TURN &&
                owner.getResourceCount(Resource.ORE) >= 3 && owner.getResourceCount(Resource.WHEAT) >= 2;
    }

    public void placeSettlement(Player p, VertexLocation loc) {
        if (canPlaceSettlement(p, loc)) {
            board.placeSettlement(p, loc, playerTurnManager.getGameState() == GameState.SETUP);
            actionHandler.placeSettlementAllowed(p, loc, playerTurnManager.getTurnPhase(), playerTurnManager.getTurnMovementDirection());
            if (playerTurnManager.getTurnPhase() == TurnPhase.PLACING_BUILDING) {
                playerTurnManager.setTurnPhase(TurnPhase.PLACING_ROAD);
            }
        } else {
            actionHandler.placeSettlementThrowException(loc);
        }
    }

    public void placeRoad(Player p, BorderLocation loc) {
        if (canPlaceRoad(p, loc)) {
            actionHandler.placeRoadAllowed(p, loc, playerTurnManager.getTurnPhase());
            if (playerTurnManager.getTurnPhase() == TurnPhase.PLACING_ROAD) {
                playerTurnManager.setTurnPhase(TurnPhase.END_TURN);
            }
            p.setLongestRoad(findLongestRoad(p));
        } else {
            actionHandler.placeRoadThrowException(loc);
        }
    }

    public void upgradeSettlement(Settlement s) {
        if (canUpgradeSettlement(s)) {
            actionHandler.upgradeSettlementAllowed(s);
        } else {
            throw new IllegalArgumentException("Cannot upgrade settlement!");
        }
    }

    public int findLongestRoad(Player player) {
        Set<Road> visitedRoads = new HashSet<>();
        List<Road> playerRoads = board.getRoadsForPlayer(player);
        return actionHandler.findLongestRoadLoop(visitedRoads, player, playerRoads);
    }
}
