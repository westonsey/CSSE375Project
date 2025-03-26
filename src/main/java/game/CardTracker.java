package game;

import java.util.List;
import java.util.Random;

import board.PortType;
import util.CountCollection;

public class CardTracker{

    private CountCollection<DevCardType> unPlayedPile;

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

    public DevCardType getRandomDevCard(List<DevCardType> values, Random random){
        return values.get(random.nextInt(values.size()));
    }

    public void PurchaseDevCard(Player p, Random random){
        if (p.hasResource(Resource.ORE) && p.hasResource(Resource.WHEAT) 
            && p.hasResource(Resource.SHEEP)){

                List<DevCardType> values = unPlayedPile.getValuesList();
                if(values.isEmpty()){
                    throw new IllegalArgumentException("No more development cards left to buy");
                }
                DevCardType card = getRandomDevCard(values, random);
                AddDevCard(p, card);

                p.removeResource(Resource.ORE, 1);
                p.removeResource(Resource.WHEAT, 1);
                p.removeResource(Resource.SHEEP, 1);   
        } else {
            throw new IllegalArgumentException("Could not purchase development card");
        }
    }

    public void AddDevCard(Player player, DevCardType devCard) {
        unPlayedPile.remove(devCard, 1);
        player.addDevCard(devCard);
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
