package integration;

import board.Board;
import board.Road;
import board.location.BorderLocation;
import game.GameHandler;
import game.Player;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class PlayerPurchasingRoadTests {

    public PlayerPurchasingSettlementTests pps;

    public Board board;

    public GameHandler game;

    public Player player;

    public Exception exception;

    public PlayerPurchasingRoadTests(PlayerPurchasingSettlementTests pps){
        this.pps = pps;
        this.board = pps.board;
        this.game = pps.game;
        this.player = pps.player;
    }

    @And("Player starts with {int} Roads")
    public void player_starts_with_numRoads_Roads(Integer numRoads) {
        // Write code here that turns the phrase above into concrete actions
        for(int i = 3; i < numRoads+3; i++){
            board.placeRoad(player, new BorderLocation(0, i), true);
        }
    }

    @When("Player purchases a road at {int} {int}")
    public void playerPurchasesARoadAtBrowBcol(Integer row, Integer col) {
        BorderLocation bl = new BorderLocation(row, col);
        this.game.placeRoad(this.player, bl);
    }

    @And("road is at location {int} {int}")
    public void roadIsAtLocationBrowBcol(Integer row, Integer col) {
        BorderLocation bl = new BorderLocation(row, col);
        for(Road r : this.board.getRoadsForPlayer(this.player)){
            assertEquals(bl, this.board.getRoadsForPlayer(this.player).get(0).getLocation());
        }
    }

}
