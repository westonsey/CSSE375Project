package game;

public class ResourceGainContext {
    private Resource resource;
    private int roll1;
    private int roll2;
    private boolean hasRobber;

    public ResourceGainContext(Resource resource, int roll1, int roll2, boolean hasRobber) {
        this.resource = resource;
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

    public boolean hasRobber() {
        return hasRobber;
    }
}
