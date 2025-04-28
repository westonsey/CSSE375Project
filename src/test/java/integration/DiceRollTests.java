package integration;

import board.Board;
import board.location.HexLocation;
import board.location.VertexLocation;
import game.GameHandler;
import game.Player;
import game.GameState;
import game.Robber;
import game.TurnPhase;
import game.Resource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.easymock.EasyMock;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class DiceRollTests {

    public Board board;
    public Robber robber;

    public GameHandler game;

    public Player player;

    @Given("a default game with a mocked random returning {int} {int}")
    public void the_default_board(Integer roll1, Integer roll2) {
        Random random = EasyMock.mock(Random.class);
        for (int i = 0; i < 19; i++) {
            // The upper bound will be the length of the list
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
        EasyMock.expect(mockRandom.nextInt(6)).andReturn(roll1-1);
        EasyMock.expect(mockRandom.nextInt(6)).andReturn(roll2-1);
        EasyMock.replay(mockRandom);
        this.game = new GameHandler(this.board, mockRandom, GameState.NORMALPLAY, TurnPhase.PLAYING_TURN);
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.addPlayer(new Player());
        game.currentPlayerTurnIndex = 0;
    }

    @Given("player {int} placed a settlement at {int} {int}")
    public void player_placed_a_settlement_at(Integer playerIndex, Integer x, Integer y) {
        VertexLocation vl = new VertexLocation(x,y);
        game.getBoard().placeSettlement(game.playerByTurnIndex(), vl, true);
    }

    @And("the game state is playing turn")
    public void theGameStateIsPlayingTurn() {
        game.setTurnPhase(TurnPhase.PLAYING_TURN);
    }

    @Given("the robber is at {int} {int}")
    public void the_robber_is_at(Integer x, Integer y) {
        HexLocation hl = new HexLocation(x,y);
        game.moveRobberWithoutChecks(hl);
    }

    @When("a {int} and {int} are rolled")
    public void a_and_are_rolled(Integer int1, Integer int2) {
        game.setTurnPhase(TurnPhase.ROLLING_DICE);
        game.doDiceRoll();
    }

    @Then("player {int} has {int} wood")
    public void player_has_wood(Integer playerIndex, Integer numWood) {
        int actual = game.playerByTurnIndex().getResourceCount(Resource.WOOD);
        int expected = numWood;
        assertEquals(expected, actual);
    }

    @Then("the turn state is {string}")
    public void the_turn_state_is(String expectedState) {
        TurnPhase actualState = game.getTurnPhase();
        assertEquals(expectedState, actualState.toString());
    }

}
