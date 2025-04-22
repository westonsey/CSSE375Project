package game;

import board.Board;
import board.Hexagon;
import board.PortType;
import board.Road;
import board.Settlement;
import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import util.CountCollection;
import util.Tuple;

import java.util.*;

public class GameHandler {

    private Random randForDice;
    private GameState gameState;
    private TurnPhase turnPhase;
    private TurnMovementDirection turnMovementDirection;
    private Board board;
    private Robber robber;
    private RobberManager robberManager;
    private ActionHandler actionHandler;
    private List<Player> players;
    private CardTracker cardTracker;
    private VictoryPointManager victoryPointManager;
    private PlayerTurnManager playerTurnManager;

    private static final int ROBBER_ROLL = 7;
    private static final int INVALID_PLAYER_INDEX = -1;

    public int currentPlayerTurnIndex;
    private int currentDiscardPlayerIndex;

    public GameHandler(Random randInt, Random boardRandom) {
        this(randInt, boardRandom, GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD,
                new Board(), true);
    }

    public GameHandler(GameState inputGameState, TurnPhase turnPhase,
            TurnMovementDirection inputTurnMovementDirection) {
        this(new Random(), new Random(), inputGameState, turnPhase, inputTurnMovementDirection, new Board(), true);
    }

    public GameHandler(Board board, Random randInt, GameState gameState, TurnPhase turnPhase) {
        this(randInt, new Random(), gameState, turnPhase, TurnMovementDirection.FORWARD, board, false);
    }

    private GameHandler(Random randInt, Random boardRandom, GameState inputGameState, TurnPhase turnPhase,
            TurnMovementDirection inputTurnMovementDirection, Board board, boolean generate) {
        this.board = board;
        this.currentPlayerTurnIndex = 0;
        this.gameState = inputGameState;
        this.turnPhase = turnPhase;
        this.randForDice = randInt;
        this.turnMovementDirection = inputTurnMovementDirection;
        if (generate) {
            board.generate(boardRandom);
        }
        players = new ArrayList<>();
        cardTracker = new CardTracker();
        initRobber();
        actionHandler = new ActionHandler(board, this, cardTracker);
        playerTurnManager = new PlayerTurnManager(inputGameState, turnPhase, inputTurnMovementDirection, 
                                                players, board, actionHandler, randInt, robberManager);
    }

    public void setVictoryPointManager() {
        this.victoryPointManager = new VictoryPointManager(players);
        playerTurnManager.setVictoryPointManager(victoryPointManager);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentDiscardingPlayer() {
        return playerTurnManager.getCurrentDiscardingPlayer(players);
    }

    public void shufflePlayers(Random random) {
        List<Player> newPlayers = new ArrayList<>();
        while (!players.isEmpty()) {
            Player p = players.remove(random.nextInt(players.size()));
            newPlayers.add(p);
        }
        players = newPlayers;
    }

    public int rollDice() {
        return playerTurnManager.rollDice();
    }

    public Robber getRobber() {
        return robber;
    }

    public void purchaseDevelopmentCard(Player player) {
        if (playerTurnManager.getTurnPhase() != TurnPhase.PLAYING_TURN) {
            throw new IllegalStateException("Cannot purchase development card in this phase!");
        }
        Random random = new Random();
        this.cardTracker.PurchaseDevCard(player, random);
    }

    public void addDevelopmentCard(Player player, DevCardType card) {
        this.cardTracker.AddDevCard(player, card);
    }

    public void playMonopolyCard(Player player, Resource resource) {
        player.playDevCard(DevCardType.MONOPOLY);
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) != player) {
                int currentResourceCount = players.get(i).getResourceCount(resource);
                if (currentResourceCount != 0) {
                    players.get(i).removeResource(resource, currentResourceCount);
                    player.addResource(resource, currentResourceCount);
                }
            }
        }
    }

    public void playYearOfPlentyCard(Player player, Resource resource1, Resource resource2) {
        player.playDevCard(DevCardType.YEAR_OF_PLENTY);
        player.addResource(resource1, 1);
        player.addResource(resource2, 1);
    }

    public void playRoadBuildingCard(Player player, BorderLocation loc1, BorderLocation loc2) {
        player.playDevCard(DevCardType.ROAD_BUILDING);
        board.placeRoad(player, loc1, false);
        board.placeRoad(player, loc2, false);
    }

    public void playKnightCard(Player player) {
        player.playDevCard(DevCardType.KNIGHT);
        playerTurnManager.setTurnPhase(TurnPhase.MOVING_ROBBER);
        this.turnPhase = TurnPhase.MOVING_ROBBER; // Keep local copy in sync
    }

    public CardTracker getCardTracker() {
        return this.cardTracker;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setCardTracker(CardTracker cardTracker) {
        this.cardTracker = cardTracker;
    }

    public void moveRobber(HexLocation loc) {
        robberManager.moveRobber(loc, playerTurnManager.getTurnPhase());
        playerTurnManager.setTurnPhase(TurnPhase.STEALING_RESOURCE);
        this.turnPhase = TurnPhase.STEALING_RESOURCE; // Keep local copy in sync
    }

    public Tuple<Integer, Integer> doDiceRoll() {
        return playerTurnManager.doDiceRoll();
    }

    // These methods have been moved to PlayerTurnManager

    public int handleSwitchPlayerTurn() {
        int newIndex = playerTurnManager.handleSwitchPlayerTurn();
        // Update local state to match PlayerTurnManager
        this.gameState = playerTurnManager.getGameState();
        this.turnPhase = playerTurnManager.getTurnPhase();
        this.turnMovementDirection = playerTurnManager.getTurnMovementDirection();
        this.currentPlayerTurnIndex = newIndex;
        return newIndex;
    }

    public void handleVictoryPoints() {
        this.victoryPointManager.handleVictoryPoints(playerTurnManager.getCurrentPlayerTurnIndex(), this.players);
        GameState newState = victoryPointManager.getGameState();
        playerTurnManager.setGameState(newState);
        this.gameState = newState; // Keep local copy in sync
    }

    public Player playerByTurnIndex() {
        return playerTurnManager.getCurrentPlayer(players);
    }

    public GameState getCurrentGameState() {
        return playerTurnManager.getGameState();
    }

    public TurnPhase getTurnPhase() {
        return playerTurnManager.getTurnPhase();
    }

    public void setTurnPhase(TurnPhase phase) {
        playerTurnManager.setTurnPhase(phase);
        this.turnPhase = phase; // Keep local copy in sync
    }

    public Board getBoard() {
        return this.board;
    }

    public TurnMovementDirection getTurnMovementDirection() {
        return playerTurnManager.getTurnMovementDirection();
    }

    public void switchTurnMovementDirection() {
        playerTurnManager.switchTurnMovementDirection();
        this.turnMovementDirection = playerTurnManager.getTurnMovementDirection(); // Keep local copy in sync
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
                this.turnPhase = TurnPhase.PLACING_ROAD; // Keep local copy in sync
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
                this.turnPhase = TurnPhase.END_TURN; // Keep local copy in sync
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

    public void stealResource(Player thief, Player victim, Random rand) {
        actionHandler.stealResourceThrowException(victim, playerTurnManager.getTurnPhase());
        List<Resource> resources = new ArrayList<>(List.of(Resource.values()));
        robberManager.stealResourceLoop(thief, victim, resources, rand, playerTurnManager.getTurnPhase());
        playerTurnManager.setTurnPhase(TurnPhase.PLAYING_TURN);
        this.turnPhase = TurnPhase.PLAYING_TURN; // Keep local copy in sync
    }

    public void skipSteal() {
        if (playerTurnManager.getTurnPhase() != TurnPhase.STEALING_RESOURCE) {
            throw new IllegalStateException("Cannot steal in this phase");
        }
        playerTurnManager.setTurnPhase(TurnPhase.PLAYING_TURN);
        this.turnPhase = TurnPhase.PLAYING_TURN; // Keep local copy in sync
    }

    public List<Player> getPlayersToStealFrom(Player currentTurn) {
        robberManager.getPlayersToStealFromThrowException(playerTurnManager.getTurnPhase());
        List<Player> adjacent = board.getAdjacentPlayers(robber.loc);
        return robberManager.getPlayersToStealFromLoop(currentTurn, adjacent);
    }

    public int getRequiredDiscardAmount() {
        actionHandler.getRequiredDiscardAmountException(playerTurnManager.getTurnPhase());
        Player player = playerTurnManager.getCurrentDiscardingPlayer(players);
        return actionHandler.getRequiredDiscardAmountConditional(player);
    }

    public void discardResources(CountCollection<Resource> resources) {
        int required = getRequiredDiscardAmount();
        actionHandler.discardResourcesNotEnoughException(resources, required);
        Player player = playerTurnManager.getCurrentDiscardingPlayer(players);
        Iterator<Tuple<Resource, Integer>> resourceIterator = resources.iterator();
        actionHandler.discardResourcesIterator(player, resourceIterator);
        actionHandler.discardResourcesRemoveResource(player, resources, resourceIterator);
        playerTurnManager.incrementDiscardPlayerIndex(players);
        // Local state will be updated through the PlayerTurnManager
        this.turnPhase = playerTurnManager.getTurnPhase();
    }

    private void initRobber() {
        HexLocation desert = initRobberLoop();
        robber = new Robber(desert);
        robberManager = new RobberManager(robber, board);
    }

    private HexLocation initRobberLoop() {
        for (Hexagon hex : board.getHexList()) {
            if (hex.isDesert) {
                return hex.location;
            }
        }
        return null;
    }

    // These methods are now handled by PlayerTurnManager

    public int findLongestRoad(Player player) {
        Set<Road> visitedRoads = new HashSet<>();
        List<Road> playerRoads = board.getRoadsForPlayer(player);
        return actionHandler.findLongestRoadLoop(visitedRoads, player, playerRoads);
    }

    public void tradeBetweenPlayers(Player player1, Player player2, CountCollection<Resource> fromResources,
            CountCollection<Resource> toResources) {
        player1.TradeResource(player2, fromResources, toResources);
    }

    public void tradeWithBank(Player player, Resource toTrade, Resource toReceive) {
        int amount = getTradeAmount(player, toTrade);
        cardTracker.TradeResourceWithBank(player, toTrade, amount, toReceive, actionHandler.getOwnedPorts(player));
    }

    public int getTradeAmount(Player player, Resource resource) {
        List<PortType> ports = actionHandler.getOwnedPorts(player);
        return actionHandler.getTradeAmountHelper(player, resource, ports);
    }

}
