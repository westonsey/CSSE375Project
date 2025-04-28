package game;

import board.PortType;
import util.CountCollection;

import java.util.List;

public class TradeManager {
    private final ActionHandler actionHandler;
    private final CardTracker cardTracker;

    public TradeManager(ActionHandler actionHandler, CardTracker cardTracker) {
        this.actionHandler = actionHandler;
        this.cardTracker = cardTracker;
    }

    public void tradeBetweenPlayers(Player player1, Player player2, CountCollection<Resource> fromResources,
            CountCollection<Resource> toResources) {
        player1.TradeResource(player2, fromResources, toResources);
    }

    public void tradeWithBank(Player player, Resource toTrade, Resource toReceive) {
        int amount = getTradeAmount(player, toTrade);
        cardTracker.TradeResourceWithBank(player, toTrade, amount, toReceive, actionHandler.getOwnedPorts(player));
    }

    public int getTradeAmount(Player player, Resource resource) {
        List<PortType> ports = actionHandler.getOwnedPorts(player);
        return actionHandler.getTradeAmountHelper(player, resource, ports);
    }
}
