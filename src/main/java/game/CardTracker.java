package game;

import java.util.List;
import java.util.Random;

import board.PortType;
import util.CountCollection;

public class CardTracker{

    private CountCollection<DevCardType> unPlayedPile;
    private Player[] players;

    public CardTracker(List<Player> players) {
        this(players.toArray(new Player[0]));
    }

    public CardTracker(Player[] players){
        this.players = players;
        unPlayedPile = new CountCollection<>();
        unPlayedPile.add(DevCardType.KNIGHT, 14);
        unPlayedPile.add(DevCardType.ROAD_BUILDING, 2);
        unPlayedPile.add(DevCardType.YEAR_OF_PLENTY, 2);
        unPlayedPile.add(DevCardType.MONOPOLY, 2);
        unPlayedPile.add(DevCardType.VICTORY_POINT, 5);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        Player[] newPlayers = new Player[players.size()];
        for (int i = 0; i < players.size(); i++) {
            newPlayers[i] = players.get(i);
        }
        this.players = newPlayers;
    }

    public CountCollection<DevCardType> getUnPlayedCards(){
        return unPlayedPile;
    }

    public int TotalPlayedDevCards() {
        int totalCount = 0;
        for (Player player : players) {
            totalCount += player.getTotalNumberOfPlayedDevCards();
        }
        return totalCount;
    }

    public int TotalUnplayedDevCards() {
        int totalCount = 0;
        for (Player player : players) {
            totalCount += player.getTotalNumberOfUnplayedDevCards();
        }
        return totalCount;
    }

    public int TotalUnplayedDevCardsInGame() {
        int totalCount = 0;
        for (Player player : players) {
            totalCount += player.getTotalNumberOfUnplayedDevCards();
        }
        totalCount += unPlayedPile.getTotalCount();
        return totalCount;
    }

    public DevCardType getRandomDevCard(List<DevCardType> values, Random random){
        return values.get(random.nextInt(values.size()));
    }

    public void PurchaseDevCard(int playerNumber, Random random){
        Player p = players[playerNumber - 1];
        if (p.hasResource(Resource.ORE) && p.hasResource(Resource.WHEAT) 
            && p.hasResource(Resource.SHEEP)){

                List<DevCardType> values = unPlayedPile.getValuesList();
                if(values.isEmpty()){
                    throw new IllegalArgumentException("No more development cards left to buy");
                }
                DevCardType card = getRandomDevCard(values, random);
                AddDevCard(playerNumber, card);

                p.removeResource(Resource.ORE, 1);
                p.removeResource(Resource.WHEAT, 1);
                p.removeResource(Resource.SHEEP, 1);   
        } else {
            throw new IllegalArgumentException("Could not purchase development card");
        }
    }

    public void AddDevCard(int playerNumber, DevCardType devCard) {
        unPlayedPile.remove(devCard, 1);
        players[playerNumber - 1].addDevCard(devCard);
    }

    public void ConsumeDevCard(int playerNumber, DevCardType devCard) {
        players[playerNumber - 1].playDevCard(devCard);
    }

    public int TotalNumberOfResourcesHeld() {
        int totalCount = 0;
        for (Player player : players) {
            totalCount += player.getTotalNumberOfResources();
        }
        return totalCount;
    }

    public int NumberOfResourceHeldByPlayer(int playerNumber, Resource resource) {
        return players[playerNumber - 1].getResourceCount(resource);
    }

    public void GainResourceFromBank(int playerNumber, Resource resource, int amount) {
        players[playerNumber - 1].addResource(resource, amount);
    }

    public void ConsumeResource(int playerNumber, Resource resource, int amount) {
        players[playerNumber - 1].removeResource(resource, amount);
    }

    public void TradeResourceWithinPlayers(int playerNumber, int otherPlayerNumber,
                                           CountCollection<Resource> resources, CountCollection<Resource> otherResources) {
        TradeResourceWithinPlayers(players[playerNumber - 1], players[otherPlayerNumber - 1],
                resources, otherResources);
    }

    public void TradeResourceWithinPlayers(Player player1, Player player2,
                                           CountCollection<Resource> resources, CountCollection<Resource> otherResources) {
        player1.TradeResource(player2, resources, otherResources);
    }

    public void TradeResourceWithBank(int playerNumber, Resource resource, int numberOfResources,
                                      Resource resourceFromBank, List<PortType> ownedPorts) {
        TradeResourceWithBank(players[playerNumber - 1], resource, numberOfResources, resourceFromBank, ownedPorts);
    }

    public void TradeResourceWithBank(Player player, Resource resource, int numberOfResources,
            Resource resourceFromBank, List<PortType> ownedPorts) {

        if (numberOfResources < 2 || numberOfResources > 4) {
            throw new IllegalArgumentException("Cannot trade with " + numberOfResources + " of Resource " +
                    resource.name());
        }

        if (numberOfResources == 4) {
            player.removeResource(resource, 4);
            player.addResource(resourceFromBank, 1);
        } else {
            if (numberOfResources == 3 && ownedPorts.contains(PortType.THREE_FOR_ONE)) {
                player.removeResource(resource, 3);
                player.addResource(resourceFromBank, 1);
            } else if (resource == Resource.WHEAT && ownedPorts.contains(PortType.WHEAT) ||
                    resource == Resource.ORE && ownedPorts.contains(PortType.ORE) ||
                    resource == Resource.SHEEP && ownedPorts.contains(PortType.SHEEP) ||
                    resource == Resource.WOOD && ownedPorts.contains(PortType.WOOD) ||
                    resource == Resource.BRICK && ownedPorts.contains(PortType.BRICK)) {

                player.removeResource(resource, 2);

                player.addResource(resourceFromBank, 1);
            }else {
                throw new IllegalArgumentException("Cannot trade with " + numberOfResources + " of Resource " +
                        resource.name());
            }

        }
    }
}
