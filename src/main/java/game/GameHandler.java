package game;

import board.Board;
import board.Hexagon;
import board.PortType;
import board.Road;
import board.Settlement;
import board.BuildingTypeFactory;
import board.BuildingType;
import board.BuildingCode;
import board.CityBuildingType;
import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import util.CountCollection;
import util.Tuple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    private static final int ROBBER_ROLL = 7;
    private static final int INVALID_PLAYER_INDEX = -1;

    private int largestArmy = 2;
    private int largestArmyPlayerIndex = INVALID_PLAYER_INDEX;
    private int longestRoad = 4;
    private int longestRoadPlayerIndex = INVALID_PLAYER_INDEX;

    public int currentPlayerTurnIndex;
    private int currentDiscardPlayerIndex;

    private BuildingTypeFactory buildingTypeFactory;
    private BuildingType currentlySelectedUpgrade;

    public GameHandler(Random randInt, Random boardRandom) {
        this(randInt, boardRandom, GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD,
                new Board(), true, new BuildingTypeFactory());
    }

    public GameHandler(GameState inputGameState, TurnPhase turnPhase, TurnMovementDirection inputTurnMovementDirection) {
        this(new Random(), new Random(), inputGameState, turnPhase, inputTurnMovementDirection, new Board(), true, new BuildingTypeFactory());
    }

    public GameHandler(Board board, Random randInt, GameState gameState, TurnPhase turnPhase) {
        this(randInt, new Random(), gameState, turnPhase, TurnMovementDirection.FORWARD, board, false, new BuildingTypeFactory());
    }

    private GameHandler(Random randInt, Random boardRandom, GameState inputGameState, TurnPhase turnPhase,
                        TurnMovementDirection inputTurnMovementDirection, Board board, boolean generate,
                        BuildingTypeFactory buildingTypeFactory) {
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
        this.buildingTypeFactory = buildingTypeFactory;
        this.currentlySelectedUpgrade = new CityBuildingType();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentDiscardingPlayer() {
        return players.get(currentDiscardPlayerIndex);
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
        return randForDice.nextInt(6)+1;
    }

    public HexLocation getRobberLoc() {
        return robber.getLoc();
    }

    public void moveRobberWithoutChecks(HexLocation loc) {
        robber.moveLocation(loc);
    }

    public void purchaseDevelopmentCard(Player player) {
        if (turnPhase != TurnPhase.PLAYING_TURN) {
            throw new IllegalStateException("Cannot purchase development card in this phase!");
        }
        Random random = new Random();
        this.cardTracker.PurchaseDevCard(player, random);
    }

    public void addDevelopmentCard(Player player, DevCardType card){
        this.cardTracker.AddDevCard(player, card);
    }

    public void playMonopolyCard(Player player, Resource resource){
        player.playDevCard(DevCardType.MONOPOLY);
        for(int i = 0; i < players.size(); i++){
            if (players.get(i) != player){
                int currentResourceCount = players.get(i).getResourceCount(resource);
                if(currentResourceCount != 0){
                    players.get(i).removeResource(resource, currentResourceCount);
                    player.addResource(resource, currentResourceCount);
                }
            }
        }
    }

    public void playYearOfPlentyCard(Player player, Resource resource1, Resource resource2){
        player.playDevCard(DevCardType.YEAR_OF_PLENTY);
        player.addResource(resource1, 1);
        player.addResource(resource2, 1);
    }

    public void playRoadBuildingCard(Player player, BorderLocation loc1, BorderLocation loc2){
        player.playDevCard(DevCardType.ROAD_BUILDING);
        board.placeRoad(player, loc1, false);
        board.placeRoad(player, loc2, false);
    }

    public void playKnightCard(Player player) {
        player.playDevCard(DevCardType.KNIGHT);
        this.turnPhase = TurnPhase.MOVING_ROBBER;
    }

    public CardTracker getCardTracker(){
        return this.cardTracker;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

    public void setCardTracker(CardTracker cardTracker){
        this.cardTracker = cardTracker;
    }

    public void moveRobber(HexLocation loc) {
        robberManager.moveRobber(loc, turnPhase);
        turnPhase = TurnPhase.STEALING_RESOURCE;
    }

    private void checkTurnPhaseValidDoDiceRoll(){
        if (turnPhase != TurnPhase.ROLLING_DICE) {
            throw new IllegalStateException("Cannot roll dice in this phase!");
        }
    }

    public Tuple<Integer, Integer> doDiceRoll() {
        checkTurnPhaseValidDoDiceRoll();
        int roll1 = rollDice();
        int roll2 = rollDice();
        handleRoll(roll1, roll2);
        return new Tuple<>(roll1, roll2);
    }

    private void checkTurnPhaseHandleSwitchPlayerTurn(){
        if (turnPhase != TurnPhase.END_TURN && turnPhase != TurnPhase.PLAYING_TURN) {
            throw new IllegalStateException("Cannot change turn in this phase!");
        }
    }

    private void handleSetUpHandleSwitchPlayerTurn(){
        if(getTurnMovementDirection().equals(TurnMovementDirection.FORWARD)){
            handleSetUpHandleSwitchPlayerTurnForward();
        }else if(getTurnMovementDirection().equals(TurnMovementDirection.REVERSE)){
            handleSetUpHandleSwitchPlayerTurnReverse();
        }
    }

    private void handleSetUpHandleSwitchPlayerTurnReverse(){
        if(this.currentPlayerTurnIndex > 0 ) {
            handleSetUpHandleSwitchPlayerTurnReverseNotEnd();
        }else{
            handleSetUpHandleSwitchPlayerTurnReverseEnd();
        }
    }

    private void handleSetUpHandleSwitchPlayerTurnReverseNotEnd(){
        this.currentPlayerTurnIndex--;
        this.turnMovementDirection = TurnMovementDirection.REVERSE;
        this.gameState = GameState.SETUP;
        this.turnPhase = TurnPhase.PLACING_BUILDING;
    }

    private void handleSetUpHandleSwitchPlayerTurnReverseEnd(){
        this.currentPlayerTurnIndex = 0;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        this.gameState = GameState.NORMALPLAY;
        this.turnPhase = TurnPhase.ROLLING_DICE;
    }

    private void handleSetUpHandleSwitchPlayerTurnForward(){
        if(this.currentPlayerTurnIndex < (players.size()-1) ) {
            handleSetUpHandleSwitchPlayerTurnForwardNotEnd();
        }else{
            handleSetUpHandleSwitchPlayerTurnForwardEnd();
        }
    }

    private void handleSetUpHandleSwitchPlayerTurnForwardNotEnd(){
        this.currentPlayerTurnIndex++;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        this.gameState = GameState.SETUP;
        this.turnPhase = TurnPhase.PLACING_BUILDING;
    }

    private void handleSetUpHandleSwitchPlayerTurnForwardEnd(){
        this.currentPlayerTurnIndex = players.size()-1;
        this.turnMovementDirection = TurnMovementDirection.REVERSE;
        this.gameState = GameState.SETUP;
        this.turnPhase = TurnPhase.PLACING_BUILDING;
    }

    private void handleNormalPlayHandleSwitchPlayerTurn(){
        if(this.currentPlayerTurnIndex < (players.size()-1)) {
            handleNormalPlayHandleSwitchPlayerTurnNotEnd();
        }else{
            handleNormalPlayHandleSwitchPlayerTurnEnd();
        }
    }

    private void handleNormalPlayHandleSwitchPlayerTurnUpdateStates(){
        this.gameState = GameState.NORMALPLAY;
        this.turnPhase = TurnPhase.ROLLING_DICE;
    }

    private void handleNormalPlayHandleSwitchPlayerTurnNotEnd(){
        this.currentPlayerTurnIndex++;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        handleNormalPlayHandleSwitchPlayerTurnUpdateStates();
    }

    private void handleNormalPlayHandleSwitchPlayerTurnEnd(){
        this.currentPlayerTurnIndex = 0;
        this.turnMovementDirection = TurnMovementDirection.FORWARD;
        handleNormalPlayHandleSwitchPlayerTurnUpdateStates();
    }

    private int handleSwitchPlayerTurnCheckGameState(){
        if(getCurrentGameState().equals(GameState.SETUP)){
            handleSetUpHandleSwitchPlayerTurn();
        }else {
            handleSwitchPlayerTurnCheckGameStateNormalPlay();
        }
        return this.currentPlayerTurnIndex;
    }

    private void handleSwitchPlayerTurnCheckGameStateNormalPlay(){
        if(getCurrentGameState().equals(GameState.NORMALPLAY)){
            handleVictoryPoints();
            if (getCurrentGameState().equals(GameState.END)) {
                return;
            }
            handleNormalPlayHandleSwitchPlayerTurn();
        }
    }

    public int handleSwitchPlayerTurn() {
        checkTurnPhaseHandleSwitchPlayerTurn();
        return handleSwitchPlayerTurnCheckGameState();
    }

    private void handleVictoryPointsLargestArmy(Player oldPlayer){
        if(largestArmyPlayerIndex != INVALID_PLAYER_INDEX){
            players.get(largestArmyPlayerIndex).changeVictoryPoints(-2);
        }
        largestArmyPlayerIndex = currentPlayerTurnIndex;
        largestArmy = oldPlayer.getNumKnights();
        oldPlayer.changeVictoryPoints(2);
    }

    private void handleVictoryPointsLongestRoad(Player oldPlayer){
        if(longestRoadPlayerIndex != INVALID_PLAYER_INDEX){
            players.get(longestRoadPlayerIndex).changeVictoryPoints(-2);
        }
        longestRoadPlayerIndex = currentPlayerTurnIndex;
        longestRoad = oldPlayer.getLongestRoad();
        oldPlayer.changeVictoryPoints(2);
    }

    private void handleVictoryPointsCheckEnd(Player oldPlayer){
        if(oldPlayer.getVictoryPoints() >= 10){
            this.gameState = GameState.END;
        }
    }

    private void handleVictoryPointsCheckLargestArmy(Player oldPlayer){
        if(oldPlayer.getNumKnights() > largestArmy){
            handleVictoryPointsLargestArmy(oldPlayer);
        }
    }

    private void handleVictoryPointsCheckLongestRoad(Player oldPlayer){
        if(oldPlayer.getLongestRoad() > longestRoad){
            handleVictoryPointsLongestRoad(oldPlayer);
        }
    }

    public void handleVictoryPoints(){
        Player oldPlayer = players.get(currentPlayerTurnIndex);
        handleVictoryPointsCheckLongestRoad(oldPlayer);
        handleVictoryPointsCheckLargestArmy(oldPlayer);
        handleVictoryPointsCheckEnd(oldPlayer);
    }

    public Player playerByTurnIndex() {
        return players.get(currentPlayerTurnIndex);
    }

    public GameState getCurrentGameState() {
        return this.gameState;
    }

    public TurnPhase getTurnPhase() {
        return this.turnPhase;
    }

    public void setTurnPhase(TurnPhase phase) {
        this.turnPhase = phase;
    }

    public Board getBoard() {
        return this.board;
    }

    public TurnMovementDirection getTurnMovementDirection() {
        return this.turnMovementDirection;
    }

    public void switchTurnMovementDirection() {
        this.turnMovementDirection = (this.turnMovementDirection == TurnMovementDirection.FORWARD)
                                        ? TurnMovementDirection.REVERSE : TurnMovementDirection.FORWARD;
    }

    public boolean canPlaceSettlement(Player p, VertexLocation loc) {
        boolean force = gameState == GameState.SETUP;
        boolean requiresResources = turnPhase == TurnPhase.PLAYING_TURN;
        return actionHandler.canPlaceSettlementConditional(p, loc, force, requiresResources, turnPhase);
    }

    public boolean canPlaceRoad(Player p, BorderLocation loc) {
        boolean requiresResources = turnPhase == TurnPhase.PLAYING_TURN;
        if (turnPhase == TurnPhase.PLACING_ROAD || turnPhase == TurnPhase.PLAYING_TURN) {
            return actionHandler.canPlaceRoadRequiresResources(p, loc, requiresResources);
        }
        return false;
    }

    public boolean canUpgradeSettlement(Settlement s) {
        Player owner = s.getOwner();
        boolean hasResources = owner.hasResources(currentlySelectedUpgrade.getRequiredResources());
        return board.canUpgradeSettlement(s) && turnPhase == TurnPhase.PLAYING_TURN && hasResources;
    }

    public void placeSettlement(Player p, VertexLocation loc) {
        if (canPlaceSettlement(p, loc)) {
            board.placeSettlement(p, loc, gameState == GameState.SETUP);
            actionHandler.placeSettlementAllowed(p, loc, turnPhase, turnMovementDirection);
            if (turnPhase == TurnPhase.PLACING_BUILDING) {
                turnPhase = TurnPhase.PLACING_ROAD;
            }
        } else {
            actionHandler.placeSettlementThrowException(loc);
        }
    }

    public void placeRoad(Player p, BorderLocation loc) {
        if (canPlaceRoad(p, loc)) {
            actionHandler.placeRoadAllowed(p, loc, turnPhase);
            if (turnPhase == TurnPhase.PLACING_ROAD) {
                turnPhase = TurnPhase.END_TURN;
            }
            p.setLongestRoad(findLongestRoad(p));
        } else {
            actionHandler.placeRoadThrowException(loc);
        }
    }

    public void upgradeSettlement(Settlement s) {
        if (canUpgradeSettlement(s)) {
            actionHandler.upgradeSettlementAllowed(s, currentlySelectedUpgrade);
        }else {
            throw new IllegalArgumentException("Cannot upgrade settlement!");
        }
    }

    public BuildingType getSelectedUpgrade() {
        return currentlySelectedUpgrade;
    }

    public void stealResource(Player thief, Player victim, Random rand) {
        actionHandler.stealResourceThrowException(victim, turnPhase);
        List<Resource> resources = new ArrayList<>(List.of(Resource.values()));
        robberManager.stealResourceLoop(thief, victim, resources, rand, turnPhase);
        turnPhase = TurnPhase.PLAYING_TURN;
    }

    public void skipSteal() {
        if (turnPhase != TurnPhase.STEALING_RESOURCE) {
            throw new IllegalStateException("Cannot steal in this phase");
        }
        turnPhase = TurnPhase.PLAYING_TURN;
    }

    public List<Player> getPlayersToStealFrom(Player currentTurn) {
        robberManager.getPlayersToStealFromThrowException(turnPhase);
        List<Player> adjacent = board.getAdjacentPlayers(robber.getLoc());
        return robberManager.getPlayersToStealFromLoop(currentTurn, adjacent);
    }

    public int getRequiredDiscardAmount() {
        actionHandler.getRequiredDiscardAmountException(turnPhase);
        Player player = players.get(currentDiscardPlayerIndex);
        return actionHandler.getRequiredDiscardAmountConditional(player);
    }

    public void discardResources(CountCollection<Resource> resources) {
        int required = getRequiredDiscardAmount();
        actionHandler.discardResourcesNotEnoughException(resources, required);
        Player player = players.get(currentDiscardPlayerIndex);
        Iterator<Tuple<Resource, Integer>> resourceIterator = resources.iterator();
        actionHandler.discardResourcesIterator(player, resourceIterator);
        actionHandler.discardResourcesRemoveResource(player, resources, resourceIterator);
        discardResourcesSetTurnPhase();
    }

    private void discardResourcesSetTurnPhase(){
        currentDiscardPlayerIndex++;
        if (currentDiscardPlayerIndex >= players.size()) {
            currentDiscardPlayerIndex = 0;
            turnPhase = TurnPhase.MOVING_ROBBER;
        }
    }

    private void initRobber() {
        HexLocation desert = initRobberLoop();
        robber = new Robber(desert);
        robberManager = new RobberManager(robber, board);
    }

    private HexLocation initRobberLoop(){
        for (Hexagon hex : board.getHexList()) {
            if (hex.isDesert) {
                return hex.location;
            }
        }
        return null;
    }

    public void setCurrentlySelectedUpgrade(BuildingCode code) {
        currentlySelectedUpgrade = buildingTypeFactory.build(code);
    }

    private void handleRoll(int roll1, int roll2) {
        if (roll1 + roll2 == ROBBER_ROLL) {
            handleRobberRoll();
        } else {
            handleNormalRoll(roll1, roll2);
        }
    }

    private void handleNormalRoll(int roll1, int roll2) {
        List<Hexagon> hexes = board.getHexList();
        actionHandler.handleNormalRollLoop(hexes, roll1, roll2);
        turnPhase = TurnPhase.PLAYING_TURN;
    }

    private void handleRobberRoll() {
        turnPhase = TurnPhase.DISCARDING_RESOURCES;
        currentDiscardPlayerIndex = 0;
    }

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

