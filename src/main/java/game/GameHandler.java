package game;

import board.Board;
import board.Building;
import board.Hexagon;
import board.PortType;
import board.Road;
import board.Settlement;
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
    private List<Player> players;
    private CardTracker cardTracker;

    private static final int ROBBER_ROLL = 7;
    private static final int INVALID_PLAYER_INDEX = -1;

    private int largestArmy = 2;
    private int largestArmyPlayerIndex = INVALID_PLAYER_INDEX;
    private int longestRoad = 4;
    private int longestRoadPlayerIndex = INVALID_PLAYER_INDEX;
  
    private static final int DISCARD_RESOURCE_THRESHOLD = 7;

    public int currentPlayerTurnIndex;
    private int currentDiscardPlayerIndex;

    public GameHandler(Random randInt, Random boardRandom) {
        this(randInt, boardRandom, GameState.SETUP, TurnPhase.PLACING_BUILDING, TurnMovementDirection.FORWARD,
                new Board(), true);
    }

    public GameHandler(GameState inputGameState, TurnPhase turnPhase, TurnMovementDirection inputTurnMovementDirection) {
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
        cardTracker = new CardTracker(players);
        initRobber();
    }

    public void addPlayer(Player player) {
        players.add(player);
        cardTracker.setPlayers(players);
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
        cardTracker.setPlayers(players);
    }

    public int rollDice() {
        return randForDice.nextInt(6)+1;
    }

    public Robber getRobber() {
        return robber;
    }

    public void purchaseDevelopmentCard(int playerNumber) {
        if (turnPhase != TurnPhase.PLAYING_TURN) {
            throw new IllegalStateException("Cannot purchase development card in this phase!");
        }
        Random random = new Random();
        this.cardTracker.PurchaseDevCard(playerNumber, random);
    }

    public void addDevelopmentCard(int playerNumber, DevCardType card){
        this.cardTracker.AddDevCard(playerNumber, card);
    }
    
    // Might need change after integrating with GUI
    public void playMonopolyCard(int playerNumber, Resource resource){
        this.cardTracker.ConsumeDevCard(playerNumber, DevCardType.MONOPOLY);
        for(int i = 0; i < players.size(); i++){
            if(i != playerNumber - 1){
                int currentResourceCount = players.get(i).getResourceCount(resource);
                if(currentResourceCount != 0){
                    players.get(i).removeResource(resource, currentResourceCount);
                    players.get(playerNumber - 1).addResource(resource, currentResourceCount);
                }
            }
        }
    }

    public void playYearOfPlentyCard(int playerNumber, Resource resource1, Resource resource2){
        this.cardTracker.ConsumeDevCard(playerNumber, DevCardType.YEAR_OF_PLENTY);
        this.cardTracker.GainResourceFromBank(playerNumber, resource1, 1);
        this.cardTracker.GainResourceFromBank(playerNumber, resource2, 1);
    }

    public void playRoadBuildingCard(int playerNumber, BorderLocation loc1, BorderLocation loc2){
        this.cardTracker.ConsumeDevCard(playerNumber, DevCardType.ROAD_BUILDING);
        board.placeRoad(this.players.get(playerNumber - 1), loc1, false);
        board.placeRoad(this.players.get(playerNumber - 1), loc2, false);
    }

    public void playKnightCard(int playerNumber) {
        this.cardTracker.ConsumeDevCard(playerNumber, DevCardType.KNIGHT);
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
        checkTurnPhaseValidMovingRobber();
        checkRobberMoveLocValid(loc);
        robber.moveLocation(loc);
        turnPhase = TurnPhase.STEALING_RESOURCE;
    }

    private void checkTurnPhaseValidMovingRobber(){
        if (turnPhase != TurnPhase.MOVING_ROBBER) {
            throw new IllegalArgumentException("Cannot move robber (invalid state)");
        }
    }

    private void checkRobberMoveLocValid(HexLocation loc){
        if (!loc.isValid() || loc.equals(robber.loc)) {
            throw new IllegalArgumentException("Cannot move robber to (" + loc.getRow() + "," + loc.getCol() + ")");
        }
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
        handleRoll(roll1 + roll2);
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
        this.currentPlayerTurnIndex = (players.size()-1);
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

    private boolean canPlaceSettlementRequiresResources(Player p, VertexLocation loc, Boolean force, Boolean requiresResources){
        if (requiresResources && (!p.hasResource(Resource.WOOD) || !p.hasResource(Resource.BRICK) ||
                !p.hasResource(Resource.WHEAT) || !p.hasResource(Resource.SHEEP))) {
            return false;
        }
        return board.canPlaceSettlement(p, loc, force);
    }

    private boolean canPlaceSettlementConditional(Player p, VertexLocation loc, Boolean force, Boolean requiresResources){
        if (turnPhase == TurnPhase.PLACING_BUILDING || turnPhase == TurnPhase.PLAYING_TURN) {
            return canPlaceSettlementRequiresResources(p, loc, force, requiresResources);
        }
        return false;
    }

    public boolean canPlaceSettlement(Player p, VertexLocation loc) {
        boolean force = gameState == GameState.SETUP;
        boolean requiresResources = turnPhase == TurnPhase.PLAYING_TURN;
        return canPlaceSettlementConditional(p, loc, force, requiresResources);
    }

    private boolean canPlaceRoadRequiresResources(Player p, BorderLocation loc, Boolean requiresResources){
        if (requiresResources) {
            if (!p.hasResource(Resource.WOOD) || !p.hasResource(Resource.BRICK)) {
                return false;
            }
        }
        return board.canPlaceRoad(p, loc, false);
    }

    public boolean canPlaceRoad(Player p, BorderLocation loc) {
        boolean requiresResources = turnPhase == TurnPhase.PLAYING_TURN;
        if (turnPhase == TurnPhase.PLACING_ROAD || turnPhase == TurnPhase.PLAYING_TURN) {
            return canPlaceRoadRequiresResources(p, loc, requiresResources);
        }
        return false;
    }

    public boolean canUpgradeSettlement(Settlement s) {
        Player owner = s.getOwner();
        return board.canUpgradeSettlement(s) && turnPhase == TurnPhase.PLAYING_TURN &&
                owner.getResourceCount(Resource.ORE) >= 3 && owner.getResourceCount(Resource.WHEAT) >= 2;
    }

    public void placeSettlement(Player p, VertexLocation loc) {
        if (canPlaceSettlement(p, loc)) {
            placeSettlementAllowed(p, loc);
        } else {
            placeSettlementThrowException(loc);
        }
    }

    private void placeSettlementThrowException(VertexLocation loc){
        throw new IllegalArgumentException("Could not place settlement at (" +
                loc.getRow() + ", " + loc.getCol() + ")");
    }

    private void placeSettlementRemoveResources(Player p){
        p.removeResource(Resource.WOOD, 1);
        p.removeResource(Resource.BRICK, 1);
        p.removeResource(Resource.WHEAT, 1);
        p.removeResource(Resource.SHEEP, 1);
    }

    private void placeSettlementAllowed(Player p, VertexLocation loc){
        board.placeSettlement(p, loc, gameState == GameState.SETUP);
        p.changeVictoryPoints(1);
        placeSettlementAllowedConditional(p, loc);
    }

    private void placeSettlementAllowedConditional(Player p, VertexLocation loc){
        if (turnPhase == TurnPhase.PLACING_BUILDING) {
            placeSettlementAllowedConditionalMovementDirection(p, loc);
        } else {
            placeSettlementRemoveResources(p);
        }
    }

    private void placeSettlementAllowedConditionalMovementDirection(Player p, VertexLocation loc){
        if (turnMovementDirection == TurnMovementDirection.REVERSE) {
            board.addResourceForGameSetup(p, loc);
        }
        turnPhase = TurnPhase.PLACING_ROAD;
    }

    public void placeRoad(Player p, BorderLocation loc) {
        if (canPlaceRoad(p, loc)) {
            placeRoadAllowed(p, loc);
        } else {
            placeRoadThrowException(loc);
        }
    }

    private void placeRoadAllowed(Player p, BorderLocation loc){
        board.placeRoad(p, loc, false);
        p.setLongestRoad(findLongestRoad(p));
        placeRoadAllowedConditional(p, loc);
    }

    private void placeRoadAllowedConditional(Player p, BorderLocation loc){
        if (turnPhase == TurnPhase.PLACING_ROAD) {
            turnPhase = TurnPhase.END_TURN;
        } else {
            placeRoadAllowedRemoveResources(p);
        }
    }

    private void placeRoadAllowedRemoveResources(Player p){
        p.removeResource(Resource.WOOD, 1);
        p.removeResource(Resource.BRICK, 1);
    }

    private void placeRoadThrowException(BorderLocation loc){
        throw new IllegalArgumentException("Could not place road at (" +
                loc.getRow() + ", " + loc.getCol() + ")");
    }

    public void upgradeSettlement(Settlement s) {
        if (canUpgradeSettlement(s)) {
            upgradeSettlementAllowed(s);
        }else {
            throw new IllegalArgumentException("Cannot upgrade settlement!");
        }
    }

    private void upgradeSettlementAllowed(Settlement s){
        Player owner = s.getOwner();
        board.upgradeSettlement(s);
        upgradeSettlementAllowedResource(owner);
    }

    private void upgradeSettlementAllowedResource(Player owner){
        owner.changeVictoryPoints(1);
        owner.removeResource(Resource.ORE, 3);
        owner.removeResource(Resource.WHEAT, 2);
    }

    public void stealResource(Player thief, Player victim, Random rand) {
        stealResourceThrowException(victim);
        List<Resource> resources = new ArrayList<>(List.of(Resource.values()));
        stealResourceLoop(thief, victim, resources, rand);
    }

    private void stealResourceLoop(Player thief, Player victim, List<Resource> resources, Random rand) {
        while (turnPhase == TurnPhase.STEALING_RESOURCE) {
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

    private void stealResourceThrowException(Player victim){
        stealResourceThrowExceptionTurnPhase();
        stealResourceThrowExceptionResource(victim);
    }

    private void stealResourceThrowExceptionTurnPhase(){
        if (turnPhase != TurnPhase.STEALING_RESOURCE) {
            throw new IllegalArgumentException("Cannot steal in this phase");
        }

    }

    private void stealResourceThrowExceptionResource(Player victim){
        if (victim.getTotalNumberOfResources() <= 0) {
            throw new IllegalArgumentException("Cannot steal from player with no resources");
        }
    }

    public void skipSteal() {
        if (turnPhase != TurnPhase.STEALING_RESOURCE) {
            throw new IllegalStateException("Cannot steal in this phase");
        }
        turnPhase = TurnPhase.PLAYING_TURN;
    }

    public List<Player> getPlayersToStealFrom(Player currentTurn) {
        getPlayersToStealFromThrowException();
        List<Player> adjacent = board.getAdjacentPlayers(robber.loc);
        return getPlayersToStealFromLoop(currentTurn, adjacent);
    }

    private void getPlayersToStealFromThrowException(){
        if (turnPhase != TurnPhase.STEALING_RESOURCE) {
            throw new IllegalStateException("Cannot steal in this phase");
        }
    }

    private List<Player> getPlayersToStealFromLoop(Player currentTurn, List<Player> adjacent){
        for (int i = 0; i < adjacent.size(); i++) {
            if (adjacent.get(i).equals(currentTurn) || adjacent.get(i).getTotalNumberOfResources() <= 0) {
                adjacent.remove(i);
                i--;
            }
        }
        return adjacent;
    }

    public int getRequiredDiscardAmount() {
        getRequiredDiscardAmountException();
        Player player = players.get(currentDiscardPlayerIndex);
        return getRequiredDiscardAmountConditional(player);
    }

    private void getRequiredDiscardAmountException(){
        if (turnPhase != TurnPhase.DISCARDING_RESOURCES) {
            throw new IllegalStateException("Cannot discard in this phase!");
        }
    }

    private int getRequiredDiscardAmountConditional(Player player){
        if (player.getTotalNumberOfResources() > DISCARD_RESOURCE_THRESHOLD) {
            return player.getTotalNumberOfResources() / 2;
        }
        return 0;
    }

    public void discardResources(CountCollection<Resource> resources) {
        int required = getRequiredDiscardAmount();
        discardResourcesNotEnoughException(resources, required);
        Player player = players.get(currentDiscardPlayerIndex);
        Iterator<Tuple<Resource, Integer>> resourceIterator = resources.iterator();
        discardResourcesIterator(player, resources, resourceIterator);
        discardResourcesRemoveResource(player, resources, resourceIterator);
        discardResourcesSetTurnPhase();
    }

    private void discardResourcesSetTurnPhase(){
        currentDiscardPlayerIndex++;
        if (currentDiscardPlayerIndex >= players.size()) {
            currentDiscardPlayerIndex = 0;
            turnPhase = TurnPhase.MOVING_ROBBER;
        }
    }

    private void discardResourcesIterator(Player player, CountCollection<Resource> resources, Iterator<Tuple<Resource, Integer>> resourceIterator){
        while (resourceIterator.hasNext()) {
            Tuple<Resource, Integer> entry = resourceIterator.next();
            discardResourcesIteratorThrowException(player, entry);
        }
    }

    private void discardResourcesIteratorThrowException(Player player, Tuple<Resource, Integer> entry){
        if (player.getResourceCount(entry.first) < entry.second) {
            throw new IllegalArgumentException("Player does not have the resource they're discarding!");
        }
    }

    private void discardResourcesRemoveResource(Player player, CountCollection<Resource> resources, Iterator<Tuple<Resource, Integer>> resourceIterator){
        resourceIterator = resources.iterator();
        while (resourceIterator.hasNext()) {
            Tuple<Resource, Integer> entry = resourceIterator.next();
            player.removeResource(entry.first, entry.second);
        }
    }

    private void discardResourcesNotEnoughException(CountCollection<Resource> resources, int required){
        if (resources.getTotalCount() != required) {
            throw new IllegalArgumentException("Not the correct discard amount (should be " + required + ")");
        }
    }

    private void initRobber() {
        HexLocation desert = initRobberLoop();
        robber = new Robber(desert);
    }

    private HexLocation initRobberLoop(){
        for (Hexagon hex : board.getHexList()) {
            if (hex.isDesert) {
                return hex.location;
            }
        }
        return null;
    }

    private void handleRoll(int roll) {
        if (roll == ROBBER_ROLL) {
            handleRobberRoll();
        } else {
            handleNormalRoll(roll);
        }
    }

    private void handleNormalRoll(int roll) {
        List<Hexagon> hexes = board.getHexesAtNumber(roll, new ArrayList<>());
        handleNormalRollLoop(hexes);
        turnPhase = TurnPhase.PLAYING_TURN;
    }

    private void handleNormalRollLoop(List<Hexagon> hexes){
        for (Hexagon h : hexes) {
            if(!h.location.equals(getRobber().loc)) {
                board.addPlayerResourcesFromHex(h);
            }
        }
    }

    private void handleRobberRoll() {
        turnPhase = TurnPhase.DISCARDING_RESOURCES;
        currentDiscardPlayerIndex = 0;
    }

    private void detectRoadSegments(Player player, Road startRoad, Set<Road> visitedRoads, Set<Road> currentSegment) {
        if (visitedRoads.contains(startRoad)) {
            return;
        }
        visitedRoads.add(startRoad);
        currentSegment.add(startRoad);
        detectRoadSegmentsLoop(player, startRoad, visitedRoads, currentSegment);
    }

    private void detectRoadSegmentsLoop(Player player, Road startRoad, Set<Road> visitedRoads, Set<Road> currentSegment){
        for (Road neighborRoad : board.getAdjacentPlayerRoads(player, startRoad)) {
            detectRoadSegments(player, neighborRoad, visitedRoads, currentSegment);
        }
    }

    public int findLongestRoad(Player player) {
        Set<Road> visitedRoads = new HashSet<>();
        List<Road> playerRoads = board.getRoadsForPlayer(player);
        return findLongestRoadLoop(visitedRoads, player, playerRoads);
    }

    private int findLongestRoadLoop(Set<Road> visitedRoads, Player player, List<Road> playerRoads){
        int longestRoad = 0;
        for (Road road : playerRoads) {
            if (!visitedRoads.contains(road)) {
                Set<Road> currentSegment = new HashSet<>();
                detectRoadSegments(player, road, visitedRoads, currentSegment);
                longestRoad = Math.max(currentSegment.size(), longestRoad);
            }
        }
        return longestRoad;
    }

    public void tradeBetweenPlayers(Player player1, Player player2, CountCollection<Resource> fromResources,
                                    CountCollection<Resource> toResources) {
        cardTracker.TradeResourceWithinPlayers(player1, player2, fromResources, toResources);
    }

    public void tradeWithBank(Player player, Resource toTrade, Resource toReceive) {
        int amount = getTradeAmount(player, toTrade);
        cardTracker.TradeResourceWithBank(player, toTrade, amount, toReceive, getOwnedPorts(player));
    }

    public int getTradeAmount(Player player, Resource resource) {
        List<PortType> ports = getOwnedPorts(player);
        return getTradeAmountHelper(player, resource, ports);
    }

    private int getTradeAmountHelper(Player player, Resource resource, List<PortType> ports){
        if (ports.contains(portTypeForResource(resource))) {
            return 2;
        } else if (ports.contains(PortType.THREE_FOR_ONE)) {
            return 3;
        }
        return 4;
    }

    private PortType portTypeForResource(Resource resource) {
        switch (resource) {
            case ORE:
                return PortType.ORE;
            case WOOD:
                return PortType.WOOD;
            case BRICK:
                return PortType.BRICK;
            case WHEAT:
                return PortType.WHEAT;
            default:
                return PortType.SHEEP;
        }
    }

    private List<PortType> getOwnedPorts(Player player) {
        List<PortType> ports = new ArrayList<>();
        List<Building> buildings = board.getBuildingsForPlayer(player);
        getOwnedPortsLoop(player, buildings, ports);
        return ports;
    }

    private void getOwnedPortsLoop(Player player, List<Building> buildings, List<PortType> ports){
        for (Building b : buildings) {
            PortType p = board.getPort(b).getPortType();
            checkValidPortgetOwnedPorts(p, ports);
        }
    }

    private void checkValidPortgetOwnedPorts(PortType p, List<PortType> ports){
        if (p != null && !ports.contains(p)) {
            ports.add(p);
        }
    }

}

