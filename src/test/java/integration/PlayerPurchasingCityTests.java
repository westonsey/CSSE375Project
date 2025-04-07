package integration;

import board.Board;
import board.Building;
import board.BuildingCode;
import board.Settlement;
import board.location.VertexLocation;
import game.GameHandler;
import game.Player;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class PlayerPurchasingCityTests {

    public PlayerPurchasingSettlementTests pps;

    public Board board;

    public GameHandler game;

    public Player player;

    public Exception exception;

    public PlayerPurchasingCityTests(PlayerPurchasingSettlementTests pps){
        this.pps = pps;
        this.board = pps.board;
        this.game = pps.game;
        this.player = pps.player;
    }

    @And("Player has settlement at {int} {int}")
    public void playerHasSettlementAtSrowScol(Integer row, Integer col) {
        VertexLocation vl = new VertexLocation(row, col);
        game.getBoard().placeSettlement(this.player, vl, true);
    }

    @When("Player purchases a city at {int} {int}")
    public void playerPurchasesACityAtSrowScol(Integer row, Integer col) {
        Settlement s = (Settlement) this.board.getBuildingsForPlayer(this.player).get(0);
        this.game.upgradeSettlement(s);
    }

    @And("Settlement is removed at location {int} {int}")
    public void settlementIsRemovedAtLocationSrowScol(Integer row, Integer col) {
        VertexLocation vl = new VertexLocation(row, col);
        for(Building b : this.board.getBuildingsForPlayer(this.player)){
            assertEquals(BuildingCode.CITY, this.board.getBuildingsForPlayer(this.player).get(0).getCode());
        }
    }

    @And("City is placed at location {int} {int}")
    public void cityIsPlacedAtLocationSrowScol(Integer row, Integer col) {
        VertexLocation vl = new VertexLocation(row, col);
        for(Building b : this.board.getBuildingsForPlayer(this.player)){
            assertEquals(BuildingCode.CITY, this.board.getBuildingsForPlayer(this.player).get(0).getCode());
        }
    }

}
