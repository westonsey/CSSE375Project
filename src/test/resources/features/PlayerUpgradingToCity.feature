Feature: Player Upgrading Settlement to City
  In order to play the game,
  As a player,
  I want to upgrade settlements to cities with resources

  Scenario Outline: Upgrade Settlements to City Valid
    Given the default game
    And Player has <wood> wood, <sheep> sheep, <ore> ore, <wheat> wheat, <brick> brick
    And Player has settlement at <srow> <scol>
    And Player starts with <origVP> victory points
    When Player purchases a city at <srow> <scol>
    Then Player is left with <newWood> wood, <newSheep> sheep, <newOre> ore, <newWheat> wheat, <newBrick> brick
    And Settlement is removed at location <srow> <scol>
    And City is placed at location <srow> <scol>
    And Player has <numVP> victory points
    And GameState at end of turn is <gameState>
    Examples:
      | wood | sheep | ore | wheat | brick | srow | scol | newWood | newSheep | newOre | newWheat | newBrick | numVP| origVP| gameState|
      |1     |1      |3    |2      |1      |5     |8    | 1       | 1        | 0      | 0        | 1        | 2     | 1     | "NORMALPLAY"|
      |1     |1      |3    |2      |1      |5     |8    | 1       | 1        | 0      | 0        | 1        | 10     | 9     | "END"|


