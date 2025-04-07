package board;

import game.Resource;

public class ResourceGainContext {
    private Resource resource;

    public ResourceGainContext(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }
}
