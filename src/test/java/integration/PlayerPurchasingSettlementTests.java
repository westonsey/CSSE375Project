package integration;

import board.Board;
import board.Building;
import board.location.BorderLocation;
import board.location.VertexLocation;
import game.Player;
import game.GameHandler;
import game.GameState;
import game.Resource;
import game.TurnPhase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.easymock.EasyMock;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class PlayerPurchasingSettlementTests {

    public Board board;

    public GameHandler game;

    public Player player;

    public Exception exception;

    @Given("the default game")
    public void theDefautGame() {
        Random random = EasyMock.mock(Random.class);
        for (int i = 0; i < 19; i++) {
            EasyMock.expect(random.nextInt(19 - i)).andReturn(0);
            if (18 - i > 0) {
                EasyMock.expect(random.nextInt(18 - i)).andReturn(0);
            }
        }
        EasyMock.replay(random);
        Board generatedBoard = new Board();
        generatedBoard.generate(random);
        EasyMock.verify(random);
        this.board = generatedBoard;
        Random mockRandom = EasyMock.createMock(Random.class);
        EasyMock.replay(mockRandom);
        this.game = new GameHandler(this.board, mockRandom, GameState.NORMALPLAY,TurnPhase.PLAYING_TURN);
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.currentPlayerTurnIndex = 0;
        this.player = game.playerByTurnIndex();


    }

    @And("Player starts with {int} victory points")
    public void player_starts_with_victory_points(Integer origVP) {
        player.changeVictoryPoints(origVP);
    }

    @And("Player has {int} wood, {int} sheep, {int} ore, {int} wheat, {int} brick")
    public void playerIndexHasResources( Integer numWood,Integer numSheep, Integer numOre, Integer numWheat, Integer numBrick) {
        if(numBrick>0) {
            this.player.addResource(Resource.BRICK, numBrick);
        }
        if(numWood >0) {
            this.player.addResource(Resource.WOOD, numWood);
        }
        if(numWheat >0) {
            this.player.addResource(Resource.WHEAT, numWheat);
        }
        if(numSheep>0) {
            this.player.addResource(Resource.SHEEP, numSheep);
        }
        if(numOre>0) {
            this.player.addResource(Resource.ORE, numOre);
        }
    }

    @When("Player purchases a settlement at {int} {int}")
    public void playerIndexPurchasesASettlementAtSrowScol(Integer row, Integer col) {
        this.board.placeRoad(this.player, new BorderLocation(row, col), true);
        game.placeSettlement(this.player, new VertexLocation(row, col));
    }

    @And("Settlement is at location {int} {int}")
    public void settlementIsAtLocationSrowScol(Integer row, Integer col) {
        VertexLocation vl = new VertexLocation(row, col);
        for(Building b : this.board.getBuildingsForPlayer(this.player)){
            assertEquals(vl, this.board.getBuildingsForPlayer(this.player).get(0).getLocation());
        }
    }

    @Then("Player is left with {int} wood, {int} sheep, {int} ore, {int} wheat, {int} brick")
    public void playerIsLeftWithResources(Integer numWood,Integer numSheep, Integer numOre, Integer numWheat, Integer numBrick) {
        int woodCount = numWood;
        int sheepCount = numSheep;
        int oreCount = numOre;
        int wheatCount = numWheat;
        int brickCount = numBrick;
        assertEquals(woodCount, this.player.getResourceCount(Resource.WOOD));
        assertEquals(sheepCount, this.player.getResourceCount(Resource.SHEEP));
        assertEquals(oreCount, this.player.getResourceCount(Resource.ORE));
        assertEquals(wheatCount, this.player.getResourceCount(Resource.WHEAT));
        assertEquals(brickCount, this.player.getResourceCount(Resource.BRICK));
    }

    @And("Player has {int} victory points")
    public void playerIndexHasVictoryPoints(int numVP) {
        assertEquals(numVP, this.player.getVictoryPoints());
    }

    @And("GameState at end of turn is {string}")
    public void gamestate_at_end_of_turn_is(String gameState) {
        game.handleSwitchPlayerTurn();
        assertEquals(gameState, game.getCurrentGameState().toString());
    }

}
