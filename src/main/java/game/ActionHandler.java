package game;

import board.Building;
import board.Board;
import board.Settlement;
import board.PortType;
import board.Road;
import board.Hexagon;
import board.BuildingType;
import board.location.BorderLocation;
import board.location.VertexLocation;
import util.CountCollection;
import util.Tuple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ActionHandler {
	private final Board board;
	private final GameHandler gameStateManager;
	private final CardTracker cardTracker;
	private static final int DISCARD_RESOURCE_THRESHOLD = 7;

	public ActionHandler(Board board, GameHandler gameStateManager, CardTracker cardTracker) {
		this.board = board;
		this.gameStateManager = gameStateManager;
		this.cardTracker = cardTracker;
	}

	boolean canPlaceSettlementConditional(Player p, VertexLocation loc, Boolean force, Boolean requiresResources, TurnPhase turnPhase) {
		if (turnPhase == TurnPhase.PLACING_BUILDING || turnPhase == TurnPhase.PLAYING_TURN) {
			return canPlaceSettlementRequiresResources(p, loc, force, requiresResources);
		}
		return false;
	}

	private boolean canPlaceSettlementRequiresResources(Player p, VertexLocation loc, Boolean force, Boolean requiresResources){
		if (requiresResources && (!p.hasResource(Resource.WOOD) || !p.hasResource(Resource.BRICK) ||
				!p.hasResource(Resource.WHEAT) || !p.hasResource(Resource.SHEEP))) {
			return false;
		}
		return board.canPlaceSettlement(p, loc, force);
	}

	boolean canPlaceRoadRequiresResources(Player p, BorderLocation loc, Boolean requiresResources){
		if (requiresResources) {
			if (!p.hasResource(Resource.WOOD) || !p.hasResource(Resource.BRICK)) {
				return false;
			}
		}
		return board.canPlaceRoad(p, loc, false);
	}

	void placeSettlementRemoveResources(Player p){
		p.removeResource(Resource.WOOD, 1);
		p.removeResource(Resource.BRICK, 1);
		p.removeResource(Resource.WHEAT, 1);
		p.removeResource(Resource.SHEEP, 1);
	}

	void placeRoadThrowException(BorderLocation loc){
		throw new IllegalArgumentException("Could not place road at (" +
				loc.getRow() + ", " + loc.getCol() + ")");
	}

	void upgradeSettlementAllowed(Settlement s, BuildingType upgrade){
		Player owner = s.getOwner();
		board.upgradeSettlement(s, upgrade);
		upgradeSettlementAllowedResource(owner);
	}

	private void upgradeSettlementAllowedResource(Player owner){
		owner.changeVictoryPoints(1);
		owner.removeResource(Resource.ORE, 3);
		owner.removeResource(Resource.WHEAT, 2);
	}

	void stealResourceThrowException(Player victim, TurnPhase turnPhase) {
		stealResourceThrowExceptionTurnPhase(turnPhase);
		stealResourceThrowExceptionResource(victim);
	}

	private void stealResourceThrowExceptionResource(Player victim){
		if (victim.getTotalNumberOfResources() <= 0) {
			throw new IllegalArgumentException("Cannot steal from player with no resources");
		}
	}

	private void stealResourceThrowExceptionTurnPhase(TurnPhase turnPhase) {
		if (turnPhase != TurnPhase.STEALING_RESOURCE) {
			throw new IllegalArgumentException("Cannot steal in this phase");
		}

	}

	void getRequiredDiscardAmountException(TurnPhase turnPhase) {
		if (turnPhase != TurnPhase.DISCARDING_RESOURCES) {
			throw new IllegalStateException("Cannot discard in this phase!");
		}
	}

	int getRequiredDiscardAmountConditional(Player player){
		if (player.getTotalNumberOfResources() > DISCARD_RESOURCE_THRESHOLD) {
			return player.getTotalNumberOfResources() / 2;
		}
		return 0;
	}

	void placeSettlementThrowException(VertexLocation loc){
		throw new IllegalArgumentException("Could not place settlement at (" +
				loc.getRow() + ", " + loc.getCol() + ")");
	}

	void placeSettlementAllowed(Player p, VertexLocation loc, TurnPhase turnPhase,
	                                    TurnMovementDirection turnMovementDirection) {
		p.changeVictoryPoints(1);
		placeSettlementAllowedConditional(p, loc, turnPhase, turnMovementDirection);
	}

	private void placeSettlementAllowedConditional(Player p, VertexLocation loc,
	                                               TurnPhase turnPhase, TurnMovementDirection turnMovementDirection) {
		if (turnPhase == TurnPhase.PLACING_BUILDING) {
			placeSettlementAllowedConditionalMovementDirection(p, loc, turnMovementDirection);
		} else {
			placeSettlementRemoveResources(p);
		}
	}

	private void placeSettlementAllowedConditionalMovementDirection(Player p, VertexLocation loc,
	                                                                TurnMovementDirection turnMovementDirection) {
		if (turnMovementDirection == TurnMovementDirection.REVERSE) {
			board.addResourceForGameSetup(p, loc);
		}
	}

	void placeRoadAllowed(Player p, BorderLocation loc, TurnPhase turnPhase){
		board.placeRoad(p, loc, false);
		placeRoadAllowedConditional(p, turnPhase);
	}

	private void placeRoadAllowedConditional(Player p,TurnPhase turnPhase){
		if (turnPhase != TurnPhase.PLACING_ROAD) {
			placeRoadAllowedRemoveResources(p);
		}
	}

	private void placeRoadAllowedRemoveResources(Player p){
		p.removeResource(Resource.WOOD, 1);
		p.removeResource(Resource.BRICK, 1);
	}

	void discardResourcesIterator(Player player, Iterator<Tuple<Resource, Integer>> resourceIterator){
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

	void discardResourcesRemoveResource(Player player, CountCollection<Resource> resources, Iterator<Tuple<Resource, Integer>> resourceIterator){
		resourceIterator = resources.iterator();
		while (resourceIterator.hasNext()) {
			Tuple<Resource, Integer> entry = resourceIterator.next();
			player.removeResource(entry.first, entry.second);
		}
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

	int findLongestRoadLoop(Set<Road> visitedRoads, Player player, List<Road> playerRoads){
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

	int getTradeAmountHelper(Player player, Resource resource, List<PortType> ports){
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

	List<PortType> getOwnedPorts(Player player) {
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

	void handleNormalRollLoop(List<Hexagon> hexes, int roll1, int roll2){
		for (Hexagon h : hexes) {
			boolean hasRobber = h.location.equals(gameStateManager.getRobber().loc);
			ResourceGainContext context = new ResourceGainContext(h.resource, h.number, roll1, roll2, hasRobber);
			board.addPlayerResourcesFromHex(h, context);
		}
	}

	void discardResourcesNotEnoughException(CountCollection<Resource> resources, int required){
		if (resources.getTotalCount() != required) {
			throw new IllegalArgumentException("Not the correct discard amount (should be " + required + ")");
		}
	}
}
