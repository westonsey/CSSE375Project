package board;

import game.Resource;

public class ResourceGainContext {
    private Resource resource;
    private int roll1;
    private int roll2;

    public ResourceGainContext(Resource resource, int roll1, int roll2) {
        this.resource = resource;
        this.roll1 = roll1;
        this.roll2 = roll2;
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
}
