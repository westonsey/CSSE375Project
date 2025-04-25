package org.openjfx.ui;

import game.GameHandler;
import game.Player;
import game.Resource;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;
import org.openjfx.ui.data.ResourceInfo;
import org.openjfx.ui.dialog.BankTradeDialog;
import org.openjfx.ui.dialog.PlayerSelectDialog;
import org.openjfx.ui.dialog.ResourceSelectDialog;
import org.openjfx.ui.dialog.TradeOfferDialog;
import util.CountCollection;
import util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Trade {
    private GameHandler game;
    private ResourceBundle bundle;
    private Window window;
    private ResourceInfo resourceInfo;
    private UiUpdates uiUpdates;

    public Trade(GameHandler game, ResourceBundle bundle, Window window, ResourceInfo resourceInfo, UiUpdates uiUpdates) {
        this.game = game;
        this.bundle = bundle;
        this.window = window;
        this.resourceInfo = resourceInfo;
        this.uiUpdates = uiUpdates;
    }

    public void doBankTrade() {
        CountCollection<Resource> tradeReqs = new CountCollection<>();
        for (Resource r : Resource.values()) {
            int amt = game.getTradeAmount(game.playerByTurnIndex(), r);
            tradeReqs.add(r, amt);
        }
        BankTradeDialog dialog = new BankTradeDialog(window,
                resourceInfo, bundle.getString("bankTrade"), game.playerByTurnIndex().availableResources, tradeReqs,
                bundle);
        Tuple<Resource, Resource> trade = dialog.showDialog();
        if (dialog.isCanceled()) {
            return;
        }
        game.tradeWithBank(game.playerByTurnIndex(), trade.first, trade.second);
        uiUpdates.updateDisplay();
    }

    public void doPlayerTrade() {
        Player tradePlayer = getPlayerToTradeWith();
        if (tradePlayer == null) {
            return;
        }
        Tuple<CountCollection<Resource>, CountCollection<Resource>> tradeOffer = getTradeOffer();
        if (tradeOffer == null) {
            return;
        }
        boolean accepted = getTradeResult(tradePlayer, tradeOffer);
        if (accepted) {
            game.tradeBetweenPlayers(game.playerByTurnIndex(), tradePlayer,
                    tradeOffer.first, tradeOffer.second);
            Alert alert = new Alert(Alert.AlertType.NONE, bundle.getString("tradeAccepted"), ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, bundle.getString("tradeRejected"), ButtonType.OK);
            alert.showAndWait();
        }
        uiUpdates.updateDisplay();
    }

    private Player getPlayerToTradeWith() {
        List<Player> tradePlayers = new ArrayList<>(game.getPlayers());
        tradePlayers.remove(game.playerByTurnIndex());
        PlayerSelectDialog playerSelect = new PlayerSelectDialog(window, tradePlayers,
                bundle.getString("selectTradePlayer"), true, bundle);
        Optional<Player> p = playerSelect.showDialog();
        if (playerSelect.isCanceled()) {
            return null;
        }
        return p.get();
    }

    private Tuple<CountCollection<Resource>, CountCollection<Resource>> getTradeOffer() {
        Player currentPlayer = game.playerByTurnIndex();
        ResourceSelectDialog resourceSelect = ResourceSelectDialog.tradeSelect(window,
                bundle.getString("createTradeOffer"), resourceInfo, currentPlayer.availableResources,
                new Tuple<>(bundle.getString("resourcesToGive"), bundle.getString("resourcesToRequest")), bundle);
        var offer = resourceSelect.showDoubleDialog();
        if (resourceSelect.isCanceled()) {
            return null;
        }
        return offer;
    }

    private boolean getTradeResult(Player tradePlayer,
                                   Tuple<CountCollection<Resource>, CountCollection<Resource>> offer) {
        Player currentPlayer = game.playerByTurnIndex();
        TradeOfferDialog dialog = new TradeOfferDialog(window, resourceInfo,
                bundle.getString("tradeFrom" + PlayerColorUtils.PLAYER_NAMES.get(currentPlayer.getColor())),
                offer.first, offer.second, tradePlayer.availableResources, bundle);
        return dialog.showDialog();
    }
}
