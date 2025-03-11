Feature: Player Purchasing Road
  In order to play the game,
  As a player,
  I want to purchase roads with resources

  Scenario Outline: Purchasing and Place Road Valid
    Given the default game
    And Player has <wood> wood, <sheep> sheep, <ore> ore, <wheat> wheat, <brick> brick
    And Player has settlement at <srow> <scol>
    When Player purchases a road at <brow> <bcol>
    Then Player is left with <newWood> wood, <newSheep> sheep, <newOre> ore, <newWheat> wheat, <newBrick> brick
    And road is at location <brow> <bcol>

    Examples:
      | wood | sheep | ore | wheat | brick | srow | scol | brow | bcol | newWood | newSheep | newOre | newWheat | newBrick | numVP| origVP| gameState| numRoads|
      |1     |1      |1    |1      |1      |0    |2    | 0      | 3       | 0      | 1        | 1        |1     |0         | 2    |2      | "NORMALPLAY"    | 0|




