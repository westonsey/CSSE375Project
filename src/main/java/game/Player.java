package game;

import java.util.Iterator;

import util.CountCollection;
import util.Tuple;

public class Player {

    public CountCollection<DevCardType> unplayedDevCards;
    private CountCollection<DevCardType> playedDevCards;
    public CountCollection<Resource> availableResources;
    private int victoryPoints;
    private int numKnights;
    private int longestRoad;
    private static final CountCollection<DevCardType> MAX_DEV_CARDS = new CountCollection<>();

    static {
        MAX_DEV_CARDS.add(DevCardType.KNIGHT, 14);
        MAX_DEV_CARDS.add(DevCardType.ROAD_BUILDING, 2);
        MAX_DEV_CARDS.add(DevCardType.YEAR_OF_PLENTY, 2);
        MAX_DEV_CARDS.add(DevCardType.MONOPOLY, 2);
        MAX_DEV_CARDS.add(DevCardType.VICTORY_POINT, 5);
    }

    private PlayerColors color;

    public Player() {
        this(PlayerColors.RED);
    }
    public Player(PlayerColors color) {
        this.color = color;
        availableResources = new CountCollection<Resource>();
        unplayedDevCards = new CountCollection<DevCardType>();
        playedDevCards = new CountCollection<DevCardType>();
        victoryPoints = 0;
    }

    public int getLongestRoad(){
        return longestRoad;
    }

    public void setLongestRoad(int length){
        longestRoad = length;
    }

    public int getNumKnights(){
        return numKnights;
    }

    public void addNumKnights(int addAmount){
        numKnights += addAmount;
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }
    
    public void changeVictoryPoints(int addAmount){
        victoryPoints += addAmount;
    }

    public PlayerColors getColor() {
        return color;
    }

    public int getTotalNumberOfResources() {
        return availableResources.getTotalCount();
    }

    public void addResource(Resource resource, int amount) {
        availableResources.add(resource, amount);
    }

    public void removeResource(Resource resource, int amount) {
        availableResources.remove(resource, amount);
    }

    public int getResourceCount(Resource resource){
        return availableResources.getCount(resource);
    }

    public boolean hasResource(Resource resource) {
        return availableResources.getCount(resource) != 0;
    }
   
    public void playDevCard(DevCardType card) {
        if(unplayedDevCards.getCount(card) == 0){
            throw new IllegalArgumentException("Cannot play DevCard " + card + " without owning them");
        }
        if(card == DevCardType.KNIGHT){
            addNumKnights(1);
        }
        unplayedDevCards.remove(card, 1);
        playedDevCards.add(card, 1);
    }

    public void addDevCard(DevCardType card) {
        if(unplayedDevCards.getCount(card) == MAX_DEV_CARDS.getCount(card)){
            throw new IllegalArgumentException("Reached max number of development card " + card);
        }
        unplayedDevCards.add(card, 1);
        if (card == DevCardType.VICTORY_POINT) {
            changeVictoryPoints(1);
        }
    }

    public int getTotalNumberOfUnplayedDevCards(){
        return unplayedDevCards.getTotalCount();
    }

    public int getTotalNumberOfPlayedDevCards(){
        return playedDevCards.getTotalCount();
    }

    public int getNumberOfUnplayedDevCards(DevCardType card){
        return unplayedDevCards.getCount(card);
    }
    
    public int getNumberOfPlayedDevCards(DevCardType card) {
        return playedDevCards.getCount(card);
    }

    public void TradeResource(Player otherPlayer, CountCollection<Resource> resources,
                              CountCollection<Resource> otherResources){
        Iterator<Tuple<Resource, Integer>> resourceIterator1 = resources.iterator();
        while (resourceIterator1.hasNext()) {
            Tuple<Resource, Integer> entry = resourceIterator1.next();
            removeResource(entry.first, entry.second);
            otherPlayer.addResource(entry.first, entry.second);
        }
        Iterator<Tuple<Resource, Integer>> resourceIterator2 = otherResources.iterator();
        while (resourceIterator2.hasNext()) {
            Tuple<Resource, Integer> entry = resourceIterator2.next();
            otherPlayer.removeResource(entry.first, entry.second);
            addResource(entry.first, entry.second);
        }
    }

}
