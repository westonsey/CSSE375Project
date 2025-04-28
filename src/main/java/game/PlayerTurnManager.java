package game;

import board.Board;
import board.Hexagon;
import util.Tuple;

import java.util.List;
import java.util.Random;

public class PlayerTurnManager {
    private GameState gameState;
    private TurnPhase turnPhase;
    private TurnMovementDirection turnMovementDirection;
    private List<Player> players;
    private Board board;
    private ActionHandler actionHandler;
    private VictoryPointManager victoryPointManager;
    private Random randForDice;
    private RobberManager robberManager;
    
    public int currentPlayerTurnIndex;
    private int currentDiscardPlayerIndex;
    
    private static final int ROBBER_ROLL = 7;
    
    public PlayerTurnManager(GameState gameState, TurnPhase turnPhase, 
                            TurnMovementDirection turnMovementDirection, 
                            List<Player> players, Board board, 
                            ActionHandler actionHandler, Random randForDice,
                            RobberManager robberManager) {
        this.gameState = gameState;
        this.turnPhase = turnPhase;
        this.turnMovementDirection = turnMovementDirection;
        this.players = players;
        this.board = board;
        this.actionHandler = actionHandler;
        this.randForDice = randForDice;
        this.robberManager = robberManager;
        this.currentPlayerTurnIndex = 0;
        this.currentDiscardPlayerIndex = 0;
    }
    
    public void setVictoryPointManager(VictoryPointManager victoryPointManager) {
        this.victoryPointManager = victoryPointManager;
    }
    
    public int rollDice() {
        return randForDice.nextInt(6) + 1;
    }
    
    public void checkTurnPhaseValidDoDiceRoll() {
        if (turnPhase != TurnPhase.ROLLING_DICE) {
            throw new IllegalStateException("Cannot roll dice in this phase!");
        }
    }

    public Tuple<Integer, Integer> doDiceRoll(int turnNumber, boolean weather) {
        checkTurnPhaseValidDoDiceRoll();
        int roll1 = rollDice();
        int roll2 = rollDice();
        handleRoll(roll1, roll2, turnNumber, weather);
        return new Tuple<>(roll1, roll2);
    }
    
    private void handleRoll(int roll1, int roll2, int turnNumber, boolean weather) {
        if (roll1 + roll2 == ROBBER_ROLL) {
            handleRobberRoll();
        } else {
            handleNormalRoll(roll1, roll2, turnNumber, weather);
        }
    }

    private void handleNormalRoll(int roll1, int roll2, int turnNumber, boolean weather) {
        List<Hexagon> hexes = board.getHexList();
        actionHandler.handleNormalRollLoop(hexes, turnNumber, weather, roll1, roll2);
        turnPhase = TurnPhase.PLAYING_TURN;
    }

    private void handleRobberRoll() {
        turnPhase = TurnPhase.DISCARDING_RESOURCES;
        currentDiscardPlayerIndex = 0;
    }
    
    public void checkTurnPhaseHandleSwitchPlayerTurn() {
        if (turnPhase != TurnPhase.END_TURN && turnPhase != TurnPhase.PLAYING_TURN) {
            throw new IllegalStateException("Cannot change turn in this phase!");
        }
    }

    public int handleSwitchPlayerTurn() {
        checkTurnPhaseHandleSwitchPlayerTurn();
        return handleSwitchPlayerTurnCheckGameState();
    }
    
    private int handleSwitchPlayerTurnCheckGameState() {
        if (gameState.equals(GameState.SETUP)) {
            handleSetUpHandleSwitchPlayerTurn();
        } else {
            handleSwitchPlayerTurnCheckGameStateNormalPlay();
        }
        return this.currentPlayerTurnIndex;
    }
    
    private void handleSwitchPlayerTurnCheckGameStateNormalPlay() {
        if (gameState.equals(GameState.NORMALPLAY)) {
            handleVictoryPoints();
            if (gameState.equals(GameState.END)) {
                return;
            }
            handleNormalPlayHandleSwitchPlayerTurn();
        }
    }
    
    public void handleVictoryPoints() {
        this.victoryPointManager.handleVictoryPoints(currentPlayerTurnIndex, this.players);
        this.gameState = victoryPointManager.getGameState();
    }
    
    private void handleSetUpHandleSwitchPlayerTurn() {
        if (getTurnMovementDirection().equals(TurnMovementDirection.FORWARD)) {
            handleSetUpHandleSwitchPlayerTurnForward();
        } else if (getTurnMovementDirection().equals(TurnMovementDirection.REVERSE)) {
            handleSetUpHandleSwitchPlayerTurnReverse();
        }
    }

    private void handleSetUpHandleSwitchPlayerTurnForward() {
        if (this.currentPlayerTurnIndex < (players.size() - 1)) {
            handleSetUpHandleSwitchPlayerTurnForwardNotEnd();
        } else {
            handleSetUpHandleSwitchPlayerTurnForwardEnd();
        }
    }

    private void handleSetUpHandleSwitchPlayerTurnForwardNotEnd() {
        this.currentPlayerTurnIndex++;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        this.gameState = GameState.SETUP;
        this.turnPhase = TurnPhase.PLACING_BUILDING;
    }

    private void handleSetUpHandleSwitchPlayerTurnForwardEnd() {
        this.currentPlayerTurnIndex = players.size() - 1;
        this.turnMovementDirection = TurnMovementDirection.REVERSE;
        this.gameState = GameState.SETUP;
        this.turnPhase = TurnPhase.PLACING_BUILDING;
    }

    private void handleSetUpHandleSwitchPlayerTurnReverse() {
        if (this.currentPlayerTurnIndex > 0) {
            handleSetUpHandleSwitchPlayerTurnReverseNotEnd();
        } else {
            handleSetUpHandleSwitchPlayerTurnReverseEnd();
        }
    }

    private void handleSetUpHandleSwitchPlayerTurnReverseNotEnd() {
        this.currentPlayerTurnIndex--;
        this.turnMovementDirection = TurnMovementDirection.REVERSE;
        this.gameState = GameState.SETUP;
        this.turnPhase = TurnPhase.PLACING_BUILDING;
    }

    private void handleSetUpHandleSwitchPlayerTurnReverseEnd() {
        this.currentPlayerTurnIndex = 0;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        this.gameState = GameState.NORMALPLAY;
        this.turnPhase = TurnPhase.ROLLING_DICE;
    }

    private void handleNormalPlayHandleSwitchPlayerTurn() {
        if (this.currentPlayerTurnIndex < (players.size() - 1)) {
            handleNormalPlayHandleSwitchPlayerTurnNotEnd();
        } else {
            handleNormalPlayHandleSwitchPlayerTurnEnd();
        }
    }

    private void handleNormalPlayHandleSwitchPlayerTurnNotEnd() {
        this.currentPlayerTurnIndex++;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        handleNormalPlayHandleSwitchPlayerTurnUpdateStates();
    }

    private void handleNormalPlayHandleSwitchPlayerTurnEnd() {
        this.currentPlayerTurnIndex = 0;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        handleNormalPlayHandleSwitchPlayerTurnUpdateStates();
    }

    private void handleNormalPlayHandleSwitchPlayerTurnUpdateStates() {
        this.gameState = GameState.NORMALPLAY;
        this.turnPhase = TurnPhase.ROLLING_DICE;
    }
    
    public void switchTurnMovementDirection() {
        this.turnMovementDirection = (this.turnMovementDirection == TurnMovementDirection.FORWARD)
                ? TurnMovementDirection.REVERSE
                : TurnMovementDirection.FORWARD;
    }
    
    public Player getCurrentPlayer(List<Player> players) {
        this.players = players;
        return players.get(currentPlayerTurnIndex);
    }
    
    public Player getCurrentDiscardingPlayer(List<Player> players) {
        this.players = players;
        return players.get(currentDiscardPlayerIndex);
    }
    
    public void incrementDiscardPlayerIndex(List<Player> players) {
        currentDiscardPlayerIndex++;
        if (currentDiscardPlayerIndex >= players.size()) {
            currentDiscardPlayerIndex = 0;
            turnPhase = TurnPhase.MOVING_ROBBER;
        }
    }
    
    // Getters and setters
    public GameState getGameState() {
        return gameState;
    }
    
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
    
    public TurnPhase getTurnPhase() {
        return turnPhase;
    }
    
    public void setTurnPhase(TurnPhase turnPhase) {
        this.turnPhase = turnPhase;
    }
    
    public TurnMovementDirection getTurnMovementDirection() {
        return turnMovementDirection;
    }
    
    public void setTurnMovementDirection(TurnMovementDirection turnMovementDirection) {
        this.turnMovementDirection = turnMovementDirection;
    }
    
    public int getCurrentPlayerTurnIndex() {
        return currentPlayerTurnIndex;
    }
    
    public void setCurrentPlayerTurnIndex(int currentPlayerTurnIndex) {
        this.currentPlayerTurnIndex = currentPlayerTurnIndex;
    }
    
    public int getCurrentDiscardPlayerIndex() {
        return currentDiscardPlayerIndex;
    }
    
    public void setCurrentDiscardPlayerIndex(int currentDiscardPlayerIndex) {
        this.currentDiscardPlayerIndex = currentDiscardPlayerIndex;
    }
}
