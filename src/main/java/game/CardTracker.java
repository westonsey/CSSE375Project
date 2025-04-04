package game;

import java.util.List;
import java.util.Random;

import board.PortType;
import util.CountCollection;

public class CardTracker{

    private CountCollection<DevCardType> unPlayedPile;
    private static final int REQUIRED_ORE = 1;
    private static final int REQUIRED_WHEAT = 1;
    private static final int REQUIRED_SHEEP = 1;

    public CardTracker() {
        unPlayedPile = new CountCollection<>();
        unPlayedPile.add(DevCardType.KNIGHT, 14);
        unPlayedPile.add(DevCardType.ROAD_BUILDING, 2);
        unPlayedPile.add(DevCardType.YEAR_OF_PLENTY, 2);
        unPlayedPile.add(DevCardType.MONOPOLY, 2);
        unPlayedPile.add(DevCardType.VICTORY_POINT, 5);
    }

    public CountCollection<DevCardType> getUnPlayedCards(){
        return unPlayedPile;
    }

    private DevCardType getRandomDevCard(List<DevCardType> values, Random random){
        return values.get(random.nextInt(values.size()));
    }

    private DevCardType drawDevCard(Random random) {
        List<DevCardType> availableCards = unPlayedPile.getValuesList();

        if (availableCards.isEmpty()) {
            throw new IllegalArgumentException("No more development cards left to buy");
        }

        return getRandomDevCard(availableCards, random);
    }

    private void removeRequiredResources(Player player) {
        player.removeResource(Resource.ORE, REQUIRED_ORE);
        player.removeResource(Resource.WHEAT, REQUIRED_WHEAT);
        player.removeResource(Resource.SHEEP, REQUIRED_SHEEP);
    }

    public void PurchaseDevCard(Player player, Random random){
        validatePlayerResources(player);

        DevCardType card = drawDevCard(random);

        processDevCardPurchase(player, card);
    }

    private void validatePlayerResources(Player player) {
        if (!player.hasResource(Resource.ORE) ||
                !player.hasResource(Resource.WHEAT) ||
                !player.hasResource(Resource.SHEEP)) {

            throw new IllegalArgumentException("Could not purchase development card");
        }
    }

    public void AddDevCard(Player player, DevCardType devCard) {
        unPlayedPile.remove(devCard, 1);
        player.addDevCard(devCard);
    }

    private void processDevCardPurchase(Player player, DevCardType card) {
        removeRequiredResources(player);
        AddDevCard(player, card);
    }

    private void validateTradeAmount(int numberOfResources, Resource resource) {
        if (numberOfResources < 2 || numberOfResources > 4) {
            throw new IllegalArgumentException("Cannot trade with " + numberOfResources + " of Resource " +
                    resource.name());
        }
    }

    private int getExchangeRate(Resource resource, int numberOfResources, List<PortType> ownedPorts) {
        if (numberOfResources == 4) {
            return 4; // Default exchange rate
        }
        if (numberOfResources == 3 && ownedPorts.contains(PortType.THREE_FOR_ONE)) {
            return 3;
        }
        if (hasMatchingPort(resource, ownedPorts)) {
            return 2; // Specialized port allows 2:1 trade
        }
        throw new IllegalArgumentException("Cannot trade with " + numberOfResources + " of Resource " + resource.name());
    }

    private boolean hasMatchingPort(Resource resource, List<PortType> ownedPorts) {
        return (resource == Resource.WHEAT && ownedPorts.contains(PortType.WHEAT)) ||
                (resource == Resource.ORE && ownedPorts.contains(PortType.ORE)) ||
                (resource == Resource.SHEEP && ownedPorts.contains(PortType.SHEEP)) ||
                (resource == Resource.WOOD && ownedPorts.contains(PortType.WOOD)) ||
                (resource == Resource.BRICK && ownedPorts.contains(PortType.BRICK));
    }

    private void processTrade(Player player, Resource resource, int numberOfResources,
                              Resource resourceFromBank, int exchangeRate) {

        player.removeResource(resource, exchangeRate);
        player.addResource(resourceFromBank, 1);
    }

    public void TradeResourceWithBank(Player player, Resource resource, int numberOfResources,
            Resource resourceFromBank, List<PortType> ownedPorts) {
        validateTradeAmount(numberOfResources, resource);

        int exchangeRate = getExchangeRate(resource, numberOfResources, ownedPorts);

        processTrade(player, resource, numberOfResources, resourceFromBank, exchangeRate);
    }
}
