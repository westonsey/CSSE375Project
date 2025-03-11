Feature: Rolling dice on turn
  In order to play the game
  As a player
  I want to collect resources from my dice roll

  Scenario Outline: Rolling dice
    Given a default game with a mocked random returning <roll1> <roll2>
    And the game state is playing turn
    And player <playerIndex> placed a settlement at <s_row> <s_col>
    And the robber is at <r_row> <r_col>
    When a <roll1> and <roll2> are rolled
    Then player <playerIndex> has <wood> wood
    And the turn state is <state>
  Examples:
    |playerIndex |s_row|s_col|r_row|r_col|roll1|roll2|wood|state|
    |0 |1    |4    |2    |2    |5    |5    |0    |"PLAYING_TURN"|
    |0 |1    |4    |2    |2    |1    |1    |1    |"PLAYING_TURN"|
    |0 |1    |4    |2    |2    |3    |4    |0    |"DISCARDING_RESOURCES"|
    |0 |1    |4    |0    |1    |1    |1    |0    |"PLAYING_TURN"|

