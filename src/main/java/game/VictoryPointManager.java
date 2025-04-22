package game;

import java.util.List;

public class VictoryPointManager {

    private static final int INVALID_PLAYER_INDEX = -1;
    
    private int largestArmy = 2;
    private int largestArmyPlayerIndex = INVALID_PLAYER_INDEX;
    private int longestRoad = 4;
    private int longestRoadPlayerIndex = INVALID_PLAYER_INDEX;
    
    private List<Player> players;
    private GameState gameState;
    
    public VictoryPointManager(List<Player> players) {
        this.players = players;
        this.gameState = GameState.NORMALPLAY;
    }
    
    public void handleVictoryPoints(int currentPlayerTurnIndex, List<Player> players) {
        this.players = players;
        Player currentPlayer = players.get(currentPlayerTurnIndex);
        handleVictoryPointsCheckLongestRoad(currentPlayer, currentPlayerTurnIndex);
        handleVictoryPointsCheckLargestArmy(currentPlayer, currentPlayerTurnIndex);
        handleVictoryPointsCheckEnd(currentPlayer);
    }
    
    private void handleVictoryPointsLargestArmy(Player player, int playerIndex) {
        if(largestArmyPlayerIndex != INVALID_PLAYER_INDEX) {
            players.get(largestArmyPlayerIndex).changeVictoryPoints(-2);
        }
        largestArmyPlayerIndex = playerIndex;
        largestArmy = player.getNumKnights();
        player.changeVictoryPoints(2);
    }

    private void handleVictoryPointsLongestRoad(Player player, int playerIndex) {
        if(longestRoadPlayerIndex != INVALID_PLAYER_INDEX) {
            players.get(longestRoadPlayerIndex).changeVictoryPoints(-2);
        }
        longestRoadPlayerIndex = playerIndex;
        longestRoad = player.getLongestRoad();
        player.changeVictoryPoints(2);
    }

    private void handleVictoryPointsCheckEnd(Player player) {
        if(player.getVictoryPoints() >= 10) {
            this.gameState = GameState.END;
        }
    }

    private void handleVictoryPointsCheckLargestArmy(Player player, int playerIndex) {
        if(player.getNumKnights() > largestArmy) {
            handleVictoryPointsLargestArmy(player, playerIndex);
        }
    }

    private void handleVictoryPointsCheckLongestRoad(Player player, int playerIndex) {
        if(player.getLongestRoad() > longestRoad) {
            handleVictoryPointsLongestRoad(player, playerIndex);
        }
    }
    
    public GameState getGameState() {
        return this.gameState;
    }
}
