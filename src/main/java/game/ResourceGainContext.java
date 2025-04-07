package game;

public class ResourceGainContext {
    private Resource resource;
    private int roll1;
    private int roll2;
    private int hexValue;
    private boolean hasRobber;

    public ResourceGainContext(Resource resource, int hexValue, int roll1, int roll2, boolean hasRobber) {
        this.resource = resource;
        this.hexValue = hexValue;
        this.roll1 = roll1;
        this.roll2 = roll2;
        this.hasRobber = hasRobber;
    }

    public Resource getResource() {
        return resource;
    }

    public int getRoll1() {
        return roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public int getTotalRoll() {
        return roll1 + roll2;
    }

    public int getHexValue() {
        return hexValue;
    }

    public boolean hasRobber() {
        return hasRobber;
    }
}
