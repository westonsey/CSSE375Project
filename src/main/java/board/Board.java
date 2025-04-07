package board;

import board.location.BorderLocation;
import board.location.HexLocation;
import board.location.VertexLocation;
import game.Player;
import game.Resource;
import game.ResourceGainContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {

    private List<Hexagon> hexagons;

    private List<Road> roads;
    public List<Building> buildings;
    private List<Port> ports;

    private static final int MIN_HEX_ROW = 0;
    private static final int MAX_HEX_ROW = 4;
    private static final int[] HEX_ROW_MIN_COLS = new int[]{
            1, 0, 0, 0, 1
    };
    private static final int[] HEX_ROW_MAX_COLS = new int[]{
            3, 3, 4, 3, 3
    };

    private static final int MIN_VERTEX_ROW = 0;
    private static final int MAX_VERTEX_ROW = 5;
    private static final int[] VERTEX_ROW_MIN_COLS = new int[]{
            2, 1, 0, 0, 1, 2
    };
    private static final int[] VERTEX_ROW_MAX_COLS = new int[]{
            8, 9, 10, 10, 9, 8
    };

    private static final int MIN_BORDER_ROW = 0;
    private static final int MAX_BORDER_ROW = 5;
    private static final int[] BORDER_ROW_MIN_COLS = new int[]{
            3, 0, 0, -1, 2, 2
    };
    private static final int[] BORDER_ROW_MAX_COLS = new int[]{
            12, 12, 15, 13, 13, 10
    };

    private static final int DESERT_NUMBER = 0;

    public Board() {
        hexagons = new ArrayList<>();
        buildings = new ArrayList<>();
        roads = new ArrayList<>();
        ports = new ArrayList<>();
        ports.add(new Port(new VertexLocation(0, 2), new VertexLocation(0, 3), PortType.THREE_FOR_ONE));
        ports.add(new Port(new VertexLocation(1, 1), new VertexLocation(2, 1), PortType.WOOD));
        ports.add(new Port(new VertexLocation(3, 1), new VertexLocation(4, 1), PortType.BRICK));
        ports.add(new Port(new VertexLocation(5, 2), new VertexLocation(5, 3), PortType.THREE_FOR_ONE));
        ports.add(new Port(new VertexLocation(5, 5), new VertexLocation(5, 6), PortType.THREE_FOR_ONE));
        ports.add(new Port(new VertexLocation(4, 8), new VertexLocation(4,9), PortType.SHEEP));
        ports.add(new Port(new VertexLocation(2, 10), new VertexLocation(3, 10), PortType.THREE_FOR_ONE));
        ports.add(new Port(new VertexLocation(1, 8), new VertexLocation(1, 9), PortType.ORE));
        ports.add(new Port(new VertexLocation(0, 5), new VertexLocation(0, 6), PortType.WHEAT));
    }

    public void generate(Random random) {
        List<Integer> numbers = new ArrayList<>(
                List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12, DESERT_NUMBER)
        );
        List<Resource> resources =
                new ArrayList<>(
                        List.of(Resource.WOOD, Resource.WOOD, Resource.WOOD, Resource.WOOD,
                                Resource.BRICK, Resource.BRICK, Resource.BRICK,
                                Resource.WHEAT, Resource.WHEAT, Resource.WHEAT, Resource.WHEAT,
                                Resource.SHEEP, Resource.SHEEP, Resource.SHEEP, Resource.SHEEP,
                                Resource.ORE, Resource.ORE, Resource.ORE));
        int row = MIN_HEX_ROW;
        int col = getMinHexCol(row);
        Hexagon newHex;
        while (true) {
            int index = random.nextInt(numbers.size());
            int number = numbers.get(index);
            numbers.remove(index);
            if (number == DESERT_NUMBER) {
                newHex = new Hexagon(new HexLocation(row, col),
                        null, DESERT_NUMBER, true);
            } else {
                index = random.nextInt(resources.size());
                Resource resource = resources.get(index);
                resources.remove(index);
                newHex = new Hexagon(new HexLocation(row, col),
                        resource, number, false);
            }
            hexagons.add(newHex);
            col++;
            if (col > getMaxHexCol(row)) {
                row++;
                if (row > MAX_HEX_ROW) {
                    break;
                }
                col = getMinHexCol(row);
            }
        }
    }

    public Hexagon getHexAt(HexLocation location) {
        if (hexagons.isEmpty()) {
            throw new IllegalStateException("Cannot get hex from an ungenerated board");
        }

        for (Hexagon hexagon : hexagons) {
            if (hexagon.getLocation().equals(location)) {
                return hexagon;
            }
        }

        throw new IllegalArgumentException("Invalid board hex location: (" +
                location.getRow() + ", " + location.getCol() + ")");
    }

    public boolean canPlaceRoad(Player p, BorderLocation loc, boolean force) {
        if (!isBorderValid(loc) || isBorderOccupied(loc)) {
            return false;
        }

        if (force) {
            return true;
        }

        boolean valid = false;
        List<Building> buildings = getBuildingsForPlayer(p);
        List<VertexLocation> validVertices = loc.getVertices();
        if (buildings.stream().anyMatch(b -> validVertices.stream().anyMatch(v ->
                v.equals(b.getLocation())))) {
            valid = true;
        }

        if (!valid) {
            List<Road> roads = getRoadsForPlayer(p);
            List<BorderLocation> validBorders = loc.getBorders();
            if (roads.stream().anyMatch(b -> validBorders.stream().anyMatch(v ->
                    v.equals(b.getLocation())))) {
                valid = true;
            }
        }

        return valid;
    }

    public boolean canPlaceSettlement(Player p, VertexLocation loc, boolean force) {

        if (!isVertexValid(loc) || isVertexOccupied(loc) || isAnyAdjacentVertexOccupied(loc)) {
            return false;
        }

        if (force) {
            return true;
        }

        boolean valid = false;
        List<Road> roads = getRoadsForPlayer(p);
        List<BorderLocation> validBorders = loc.getBorders();
        if (roads.stream().anyMatch(r -> validBorders.stream().anyMatch(b ->
                b.equals(r.getLocation())))) {
            valid = true;
        }

        return valid;
    }

    public boolean canUpgradeSettlement(Settlement s) {
        int index = getIndexOfBuilding(s);
        return index >= 0;
    }

    public void placeRoad(Player p, BorderLocation loc, boolean force) {
        if (canPlaceRoad(p, loc, force)) {
            Road road = new Road(loc, p);
            roads.add(road);
        } else {
            throw new IllegalArgumentException("Cannot place road at (" + loc.getRow() +
                    ", " + loc.getCol() + ")");
        }
    }

    public void placeSettlement(Player p, VertexLocation loc, boolean force) {
        if (canPlaceSettlement(p, loc, force)) {
            Settlement settlement = new Settlement(loc, p);
            buildings.add(settlement);
        } else {
            throw new IllegalArgumentException("Cannot place settlement at (" + loc.getRow() +
                    ", " + loc.getCol() + ")");
        }
    }

    public void addResourceForGameSetup(Player p, VertexLocation loc){
        List<HexLocation> hexLocs = loc.getHexes();
        hexLocs.removeIf(h -> !isHexValid(h));
        for (HexLocation hl : hexLocs) {
            Hexagon hex = getHexAt(hl);
            p.addResource(hex.resource, 1);
        }
    }

    public void upgradeSettlement(Settlement s) {
        if (canUpgradeSettlement(s)) {
            Player p = s.getOwner();
            int index = getIndexOfBuilding(s);
            Building existing = buildings.get(index);
            buildings.remove(index);
            City city = new City(new VertexLocation(existing.getLocation().getRow(), existing.getLocation().getCol()),
                    existing.getOwner());
            buildings.add(city);
        } else {
            throw new IllegalArgumentException("No settlement found at (" + s.getLocation().getRow() +
                    ", " + s.getLocation().getCol() + ") to upgrade");
        }
    }

    public int getIndexOfBuilding(Building b) {
        for (int i = 0; i < buildings.size(); i++) {
            Building bu = buildings.get(i);
            if (b.getOwner() == bu.getOwner() && b.getLocation().equals(bu.getLocation()) &&
                    b.getCode() == bu.getCode()) {
                return i;
            }
        }
        return -1;
    }

    public boolean isBorderValid(BorderLocation loc) {
        int row = loc.getRow();
        int col = loc.getCol();
        if (row < MIN_BORDER_ROW || row > MAX_BORDER_ROW) {
            return false;
        }
        int minCol = getMinBorderCol(row);
        int maxCol = getMaxBorderCol(row);
        if (col < minCol || col > maxCol) {
            return false;
        }
        return row != MAX_BORDER_ROW || col % 3 != 0;
    }

    public boolean isBorderOccupied(BorderLocation loc) {
        for (Road r : this.roads) {
            if (r.getLocation().equals(loc)) {
                return true;
            }
        }
        return false;
    }

    public boolean isHexValid(HexLocation hex) {
        int row = hex.getRow();
        int col = hex.getCol();
        if (row < MIN_HEX_ROW || row > MAX_HEX_ROW) {
            return false;
        }
        int minCol = getMinHexCol(row);
        int maxCol = getMaxHexCol(row);
        return col >= minCol && col <= maxCol;
    }

    public boolean isVertexValid(VertexLocation loc) {
        int row = loc.getRow();
        int col = loc.getCol();
        if (row < MIN_VERTEX_ROW || row > MAX_VERTEX_ROW) {
            return false;
        }
        int minCol = getMinVertexCol(row);
        int maxCol = getMaxVertexCol(row);
        return col >= minCol && col <= maxCol;
    }

    private boolean isVertexOccupied(VertexLocation loc) {
        for (Building b : this.buildings) {
            if (b.getLocation().equals(loc)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAnyAdjacentVertexOccupied(VertexLocation loc) {
        List<VertexLocation> otherVertices = isAnyAdjacentVertexOccupiedLoop(loc);
        return otherVertices.stream().anyMatch(this::isVertexOccupied);
    }

    private List<VertexLocation> isAnyAdjacentVertexOccupiedLoop(VertexLocation loc){
        List<BorderLocation> validBorders = loc.getBorders();
        List<VertexLocation> otherVertices = new ArrayList<>();
        for (BorderLocation b : validBorders) {
            if (!isBorderValid(b)) {
                continue;
            }
            List<VertexLocation> vertices = b.getVertices();
            for (VertexLocation v : vertices) {
                if (!v.equals(loc)) {
                    otherVertices.add(v);
                }
            }
        }
        return otherVertices;
    }

    public List<Building> getBuildingsForPlayer(Player p) {
        List<Building> playerBuildings = new ArrayList<>();
        for (Building b : buildings) {
            if (b.getOwner() == p) {
                playerBuildings.add(b);
            }
        }
        return playerBuildings;
    }

    public Road getRoadAt(BorderLocation loc) {
        for (Road r : roads) {
            if (r.getLocation().equals(loc)) {
                return r;
            }
        }
        return new Road(loc, null);
    }

    public List<Road> getRoadsForPlayer(Player p) {
        List<Road> playerRoads = new ArrayList<>();
        for (Road r : roads) {
            if (r.getOwner() == p) {
                playerRoads.add(r);
            }
        }
        return playerRoads;
    }

    public List<Road> getAdjacentPlayerRoads(Player p, Road road){
        return getAdjacentPlayerRoadsAddRoad(p, road);
    }

    private List<Road> getAdjacentPlayerRoadsAddRoad(Player p, Road road){
        List<Road> adjacentRoads = new ArrayList<>();
        for(BorderLocation loc : road.getLocation().getBorders()){
            if(getRoadAt(loc).getOwner() == p){
                adjacentRoads.add(getRoadAt(loc));
            }
        }
        return adjacentRoads;
    }

    private int getMinHexCol(int row) {
        return HEX_ROW_MIN_COLS[row];
    }

    private int getMaxHexCol(int row) {
        return HEX_ROW_MAX_COLS[row];
    }

    public int getMinVertexCol(int row) {
        return VERTEX_ROW_MIN_COLS[row];
    }

    private int getMaxVertexCol(int row) {
        return VERTEX_ROW_MAX_COLS[row];
    }

    public int getMinBorderCol(int row) {
        return BORDER_ROW_MIN_COLS[row];
    }

    private int getMaxBorderCol(int row) {
        return BORDER_ROW_MAX_COLS[row];
    }

    public Port getPort(Building settlement) {
        VertexLocation location = settlement.getLocation();
        for(Port port : ports){
            if(port.checkVertexes(location))
                return port;
        }
        return new Port(null, null, null);
    }

    public List<Hexagon> getHexList(){
        return this.hexagons;
    }

    public void addPlayerResourcesFromHex(Hexagon hex, ResourceGainContext context) {
        List<VertexLocation> vertListForHex= hex.getVertices();
        List<Building> buildingLst = addPlayerResourcesFromHexGetBuildings(vertListForHex);
        for(Building b : buildingLst) {
            int resourceCountToGive = b.getType().determineResourceGain(context);
            if (resourceCountToGive > 0) {
                b.getOwner().addResource(hex.resource, resourceCountToGive);
            }
        }
    }

    private List<Building> addPlayerResourcesFromHexGetBuildings( List<VertexLocation> vertListForHex){
        List<Building> buildingLst = new ArrayList<Building>();
        for(Building b : buildings){
            if(vertListForHex.contains(b.getLocation())){
                buildingLst.add(b);
            }
        }
        return buildingLst;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public List<Player> getAdjacentPlayers(HexLocation loc) {
        getAdjacentPlayersThrowException(loc);
        List<Player> players = new ArrayList<>();
        getAdjacentPlayersAddPlayer(players, loc);
        return players;
    }

    private void getAdjacentPlayersThrowException(HexLocation loc){
        if (!isHexValid(loc)) {
            throw new IllegalArgumentException("Invalid hex location (" + loc.getRow() + "," +
                    loc.getCol() + ")");
        }
    }

    private void getAdjacentPlayersAddPlayer(List<Player> players, HexLocation loc){
        List<VertexLocation> vertices = loc.getVertices();
        for (Building building : buildings) {
            if (vertices.contains(building.getLocation()) &&
                    !players.contains(building.getOwner())) {
                players.add(building.getOwner());
            }
        }
    }

}
