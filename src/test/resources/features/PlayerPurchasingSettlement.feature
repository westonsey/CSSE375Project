Feature: Player Purchasing Settlement
  In order to play the game,
  As a player,
  I want to purchase settlements with resources

  Scenario Outline: Purchasing and Place Settlement Valid
    Given the default game
    And Player has <wood> wood, <sheep> sheep, <ore> ore, <wheat> wheat, <brick> brick
    And Player starts with <origVP> victory points
    When Player purchases a settlement at <srow> <scol>
    Then Player is left with <newWood> wood, <newSheep> sheep, <newOre> ore, <newWheat> wheat, <newBrick> brick
    And Settlement is at location <srow> <scol>
    And Player has <numVP> victory points
    And GameState at end of turn is <gameState>

    Examples:
      | wood | sheep | ore | wheat | brick | srow | scol | newWood | newSheep | newOre | newWheat | newBrick | numVP| origVP| gameState|
      |1     |1      |1    |1      |1      |2     |2    | 0       | 0        | 1      | 0        | 0        | 1    | 0      | "NORMALPLAY"  |
      |1     |1      |1    |1      |1      |2     |2    | 0       | 0        | 1      | 0        | 0        | 10    | 9      | "END"        |



