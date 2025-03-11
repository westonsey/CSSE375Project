# Instructor Meeting Week 7

Attendees: Yiji, Daniel Gaull, Spencer Halsey, Henry Jung, Weston Seybold

## Progress Evaluation
1. One Single Turn of Game? Setup phase all works with GUI; business logic is mostly done except a few corner cases/cards; 
2. GUI? Need to finish one single turn of game; can roll dice, can get resources, can place resources, but can't get resources yet; can move the robber
3. Win condition is partly done except for the longest road and largest army
4. i18n? In progress
5. Linters:
    - Checkstyle? Found in the build script.
    - Spotbugs? Found in the build script.
    - Jacoco? Found in the build script.
    - Pitest? Found in the build script.

## Teamwork Challenges
N/A

## Recommendations and Other Notes
1. Remove commented out code. For instance, util.CountCollection.remove() method, line 43, 44.
2. There are methods that violate Martin's coding standard. For instance,
   - game.CardTracker.TradeResourceWothinPlayers method, 1) parameter list is too long, and 2) method body is too long.
   - game.GameHandler.doDiceRoll() method is long
   - game.GameHandler.handleSwitchPlayerTurn() method is too long
   - game.GameHandler.handleSwitchPlayerTurn() method: the if conditions are too long. Should be encapsulated into their own methods. For instance, if(getCurrentGameState().equals(GameState.SETUP)) should be if(isSetupPhse()), where isSetupPhse() is a private helper method that returns getCurrentGameState().equals(GameState.SETUP)
4. There are unecessary comment. For instance, game.GameHandler, "    //constructor for basic game initialization"
