# BVA For Game Handler Class

## method: rollDice():int

### BVA Step 1: describe the input and output

Input: Random Class that roduces values on the interval [1,6]

Output: Integer on the interval [1, 6]

### BVA Step 2: determine the data type

Input: Interval [1,6]

Output: Cases 

### BVA Step 3: select values along the edges

Input: Integer values (1, 6)

Output: Cases: Integer Values 1, 2, 3, 4, 5, 6

### BVA Step 4: determine the test cases

#### Test value 1:

Input: Generate Integer Value 1 using Random Mock

Output: Integer Value 1

#### Test value 2:

Input: Generate Integer Value 2 using Random Mock

Output: Integer Value 2

#### Test value 3:
Input: Generate Integer Value 3 using Random Mock

Output: Integer Value 3

#### Test value 4:

Input: Generate Integer Value 4 using Random Mock

Output: Integer Value 4

#### Test value 5:

Input: Generate Integer Value 5 using Random Mock

Output: Integer Value 5

#### Test value 6:

Input: Generate Integer Value 6 using Random Mock

Output: Integer Value 6

## method: doDiceRoll(): Tuple<Integer, Integer>

### BVA Step 1
Input: a random object, a board, turn state

Output: two integer dice rolls, player resource counts, a turn state

### BVA Step 2
Input: a mocked random producing two intervals from \[1, 6], a collection of resource/number pairs and building/road locations, turn state (cases)

Output: two intervals from \[1, 6], collection of resources and counts, a turn state (cases: PLAYING_TURN, MOVING_ROBBER)

### BVA Step 3
Input:
* Mocked random to roll a 5 and 1
* Mocked random to roll at 3 and 4
* Turn state ROLLING_DICE
* Turn state PLAYING_TURN
* The default board with a settlement for player 1 at (1, 3) (so player gets 1 brick when 6 is rolled)

Output:
* The rolled intervals (5, 1) or (3, 4)
* No resources
* Player now having 1 brick
* PLAYING_TURN
* DISCARDING_RESOURCES
* IllegalStateException

### BVA Step 4
#### Test Case 1
Input: roll of 5 and 1, default board with a settlement for player 1 at (1, 3), state is ROLLING_DICE

Expected Output: 5 and 1, player 1 has 1 brick, state is PLAYING_TURN

#### Test Case 2
Input: roll of 3 and 4, default board with a settlement for player 1 at (1, 3), state is ROLLING_DICE

Expected Output: 3 and 4, player 1 has no resources, state is DISCARDING_RESOURCES

#### Test Case 3
Input: roll of 5 and 1, default board with a settlement for player 1 at (1, 3), state is PLAYING_TURN 

Expected Output: IllegalStateException

## method: handleSwitchPlayerTurn():int

### BVA Step 1: describe the input and output

Input: Variable set to Integer on the interval [0, 3] to indicate which players turn it is, current game state/phase, movement direction for snake, number of victory points

Output: Variable set to Integer on the interval [0, 3] to indicate which players turn it is, Current Game State/phase, movement direction for snake

### BVA Step 2: determine the data type

Input: Cases, Cases, Cases

Output: Cases, Cases, Cases

### BVA Step 3: select values along the edges

Input:  Cases: Integer Values 0, 1, 2, 3 - Cases: Game State: Setup, NormalPlay - Cases: Turn Phase: Placing Settlement, Placing Road, End Turn, Moving Robber, Rolling Dice, Playing Turn - Cases: Movement Direction: Forward, Reverse. Number: Integer Values 0-10.

Output: IllegalStateException OR Cases: Integer Values 0, 1, 2, 3 - Cases: Game State: Setup, NormalPlay, End - Cases: Movement Direction: Forward, Reverse - Cases: Turn Phase: Placing Settlement, Rolling Dice

### BVA Step 4: determine the test cases

#### Test value 1:

Input: Integer Value: 0 - Game State: Setup - Movement Direction: Forward - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 1 - Game State: Setup - Movement Direction: Forward - Turn Phase: Placing Settlement

#### Test value 2:

Input: Integer Value: 0 - Game State: Setup - Movement Direction: Reverse - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 0 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Rolling Dice

#### Test value 3:

Input: Integer Value: 0 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Playing Turn, Integer Value: 0

Output: Integer Value: 1 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Rolling Dice

#### Test value 4:

Input: Integer Value: 1 - Game State: Setup - Movement Direction: Forward - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 2 - Game State: Setup - Movement Direction: Forward - Turn Phase: Placing Settlement

#### Test value 5:

Input: Integer Value: 1 - Game State: Setup - Movement Direction: Reverse - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 0 - Game State: Setup - Movement Direction: Reverse - Turn Phase: Placing Settlement

#### Test value 6:

Input: Integer Value: 1 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Playing Turn, Integer Value: 0

Output: Integer Value: 2 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Rolling Dice

#### Test value 7:

Input: Integer Value: 2 - Game State: Setup - Movement Direction: Forward - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 3 - Game State: Setup - Movement Direction: Forward - Turn Phase: Placing Settlement

#### Test value 8:

Input: Integer Value: 2 - Game State: Setup - Movement Direction: Reverse - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 1 - Game State: Setup - Movement Direction: Reverse - Turn Phase: Placing Settlement

#### Test value 9:

Input: Integer Value: 2 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Playing Turn, Integer Value: 0

Output: Integer Value: 3 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Rolling Dice

#### Test value 10:

Input: Integer Value: 3 - Game State: Setup - Movement Direction: Forward - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 3 - Game State: Setup - Movement Direction: Reverse - Turn Phase: Placing Settlement

#### Test value 11:

Input: Integer Value: 3 - Game State: Setup - Movement Direction: Reverse - Turn Phase: End Turn, Integer Value: 0

Output: Integer Value: 2 - Game State: Setup - Movement Direction: Reverse - Turn Phase: Placing Settlement

#### Test value 12:

Input: Integer Value: 3 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Playing Turn, Integer Value: 0

Output: Integer Value: 0 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Rolling Dice

#### Test value 13:

Input: Integer Value: 3 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Playing Turn, Integer Value: 10

Output: Integer Value: 3 - Game State: End

#### Test Value 14:
Input: Integer Value: 0 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Rolling Dice

Output: IllegalStateException

#### Test Value 15:
Input: Integer Value: 0 - Game State: NormalPlay - Movement Direction: Forward - Turn Phase: Moving Robber

Output: IllegalStateException

#### Test Value 16:
Input: Integer Value: 0 - Game State: Setup - Movement Direction: Forward - Turn Phase: Placing Settlement

Output: IllegalStateException

#### Test Value 17:
Input: Integer Value: 0 - Game State: Setup - Movement Direction: Forward - Turn Phase: Placing Road

Output: IllegalStateException

## method: getPlayerByTurnIndex():int

### BVA Step 1: describe the input and output

Input: Player Collection containing 4 players, Integer Index on the Interval [0,3]

Output: Player 

### BVA Step 2: determine the data type

Input: Cases: get Player at Index 0, get Player at Index 1, get Player at Index 2, get Player at Index 3

Output: Player 1, Player 2, Player 3, Player 4

### BVA Step 3: select values along the edges

Input:  Cases: Integer Values 0, 1, 2, 3

Output: Cases: Player 1, Player 2, Player 3, Player 4

### BVA Step 4: determine the test cases

#### Test value 1:

Input: Player Collection containing 4 players, Integer Value 0

Output: Player 1

#### Test value 2:

Input: Player Collection containing 4 players, Integer Value 1

Output: Player 2

#### Test value 3:
Input: Player Collection containing 4 players, Integer Value 2

Output: Player 3

#### Test value 4:

Input: Player Collection containing 4 players, Integer Value  3

Output: Player 4

## method: placeSettlement(p: Player, loc: VertexLocation): void

This method pushes most of the logic onto the Board class. So, we'll ONLY be checking that the phase gets changed properly and resources get consumed

### BVA Step 1

Input: a player, player's resources, a board, a vertex location, the current game state/turn phase/movement direction

Output: a new turn phase, player's resources

### BVA Step 2

Input: a player, a collection of resources and associated counts, a collection of buildings/roads, a pair of intervals, an enum (cases) value (either PLACING_BUILDING or PLAYING_TURN), an enum value (either SETUP or NORMALPLAY), an enum value (either FORWARD or REVERSE)

Output: an enum (cases) value (either PLACING_ROAD or PLAYING_TURN), a collection of resources and associated counts

### BVA Step 3

Input: 
* * player 1, a board with a road for player 1 at (0, 3), the location (0, 2), PLAYING_TURN, one of each resource needed to buy a settlement
* player 1, an empty board, the location (0, 2), PLACING_BUILDING, no resources

Output:
* PLAYING_TURN
* PLACING_ROAD
* No resources
* Total of 2 or 3 resources (of any type; board is randomly generated and desert may play a factor)
* IllegalArgumentException

### BVA Step 4
#### Test Case 1
Input: player 1, a board with a road for player 1 at (0, 3), player 1 has one wood, brick, wheat, and sheep, the location (0, 2), PLAYING_TURN, NORMALPLAY, FORWARD

Expected Output: PLAYING_TURN, no resources

#### Test Case 2
Input: player 1, an empty board, the location (1, 3), player 1 has no resources, PLACING_BUILDING, SETUP, FORWARD

Expected Output: PLACING_ROAD, no resources

#### Test Case 3
Input: player 1, an empty board, the location (1, 3), player 1 has no resources, PLACING_BUILDING, SETUP, REVERSE

Expected Output: PLACING_ROAD, player 1 has 2 or 3 total resources (board may have the desert nearby)

#### Test Case 3
Input: player 1, an empty board, the location (0, 2), player 1 has one wood, brick, wheat, and sheep, PLAYING_TURN, NORMALPLAY, FORWARD

Expected Output: IllegalArgumentException

## method: placeRoad(p: Player, loc: BorderLocation): void

### BVA Step 1
Input: a player, a board, a border location, the current turn phase, player's resources

Output: a new turn phase, player's resources

### BVA Step 2

Input: a player, a collection of buildings/roads, a pair of intervals, and an enum (cases) value (either PLACING_ROAD or PLAYING_TURN), a collection of resources and counts

Output: an enum (cases) value (either TURN_OVER or PLAYING_TURN), a collection of resources and counts

### BVA Step 3

Input:
* player 1, a board with a road for player 1 at (2, 1), the location (2, 2), PLAYING_TURN, one wood+brick
* player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), PLACING_ROAD

Output:
* PLAYING_TURN
* TURN_OVER
* IllegalArgumentException

### BVA Step 4
#### Test Case 1
Input: player 1, a board with a road for player 1 at (2, 1), the location (2, 2), PLAYING_TURN, NORMALPLAY, FORWARD, one wood+brick

Expected Output: PLAYING_TURN, no resources

#### Test Case 2
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), PLACING_ROAD, SETUP, FORWARD, no resources

Expected Output: END_TURN, no resources

#### Test Case 3
Input: player 1, an empty board, the location (0, 3), PLACING_ROAD, SETUP, FORWARD, no resources

Expected Output: IllegalArgumentException

## method: upgradeSettlement(s: Settlement): void
### BVA Step 1
Input: settlement, board, player resources, turn phase

Output: turn phase, board, player resources

### BVA Step 2
Input: a pair of intervals and player, a collection of buildings and locations, a collection of resources and counts, cases

Output: cases, a collection of buildings and locations, a collection of resources and counts, an exception

### BVA Step 3
Input:
* An invalid time to upgrade (turn phase ROLLING_DICE) w/ all else valid
* Board w/ settlement for player 1 at (2, 3)
* Player 1 has 3 ore and 2 wheat
* A valid time to upgrade (turn phase PLAYING_TURN) w/ all else valid

Output:
* Settlement at (2, 3) is now a city
* Turn phase remains PLAYING_TURN
* Player has no resources remaining
* IllegalArgumentException

### BVA Step 4
#### Test Case 1
Input: ROLLING_DICE, board w/ settlement for player 1 at (2, 3), pass in that settlement, player has 3 ore and 2 wheat

Output: IllegalArgumentException

#### Test Case 2
Input: PLAYING_TURN, board w/ settlement for player 1 at (2, 3), pass in that settlement, player has 3 ore and 2 wheat

Output: now has a city at (2, 3), player 1 has no resources

## method: canPlaceRoad(p: Player, loc: BorderLocation): boolean

### BVA Step 1
Input: a player, a board, a border location, the current game state, the current turn phase, player's resources

Output: a boolean

### BVA Step 2
Input: a player, a collection of buildings and roads, a pair of intervals, cases (SETUP or NORMALPLAY), cases (PLACING_ROAD, PLACING_BUILDING, END_TURN, ROLLING_DICE, PLAYING_TURN), a collection of Resources and counts

Output: a boolean

### BVA Step 3
Input:
* player 1
* a board with a settlement for player 1 at (0, 2) OR an empty board 
* the location (0, 3)
* SETUP, PLACING_ROAD
* SETUP, PLACING_BUILDING
* SETUP, END_TURN
* NORMALPLAY, ROLLDING_DICE
* NORMALPLAY, PLAYING_TURN
* one wood
* one brick
* one wood+brick

Output: True, false

### BVA Step 4
#### Test Case 1
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), SETUP, PLACING_ROAD, no resources

Output: true

#### Test Case 2
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), SETUP, PLACING_BUILDING, no resources

Output: false

#### Test Case 3
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), SETUP, END_TURN, no resources

Output: false

#### Test Case 4
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), NORMALPLAY, ROLLING_DICE, one wood+brick

Output: false

#### Test Case 5
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), NORMALPLAY, PLAYING_TURN, one wood+brick

Output: true

#### Test Case 6
Input: player 1, an empty board, the location (0, 3), NORMALPLAY, PLAYING_TURN, one wood+brick

Output: false

#### Test Case 7
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), NORMALPLAY, PLAYING_TURN, one wood but no brick

Output: false

#### Test Case 8
Input: player 1, a board with a settlement for player 1 at (0, 2), the location (0, 3), NORMALPLAY, PLAYING_TURN, one brick but no wood

Output: false

## method: canPlaceSettlement(p: Player, loc: VertexLocation): boolean

### BVA Step 1
Input: a player, a board, a vertex location, a game state/turn phase, player's resources

Output: a boolean

### BVA Step 2
Input: a player, a collection of buildings/roads, a pair of intervals, cases (SETUP, NORMALPLAY), cases (PLACING_ROAD, PLACING_BUILDING, END_TURN, ROLLING_DICE, PLAYING_TURN), a collection of resources and counts

Output: boolean

### BVA Step 3
Input:
* player 1
* an empty board
* a board with a road for player 1 at (0, 3)
* the location (0, 2)
* NORMALPLAY, ROLLING_DICE
* NORMALPLAY, PLAYING_TURN
* one wood+brick+sheep
* one wood+brick+wheat
* one wood+sheep+wheat
* one brick+sheep+wheat
* one wood+brick+sheep+wheat

Output:
* True, false

### BVA Step 4
#### Test Case 1
Input: player 1, an empty board, the location (0, 2), SETUP, PLACING_SETTLEMENT, no resources

Expected Output: True

#### Test Case 2
Input: player 1, a board with a road for player 1 at (0, 3), the location (0, 2), NORMALPLAY, ROLLING_DICE, one wood+brick+sheep+wheat

Expected Output: False

#### Test Case 3
Input: player 1, a board with a road for player 1 at (0, 3), the location (0, 2), NORMALPLAY, PLAYING_TURN, one wood+brick+sheep+wheat

Expected Output: True

#### Test Case 4
Input: player 1, an empty board, the location (0, 2), NORMALPLAY, PLAYING_TURN, one wood+brick+sheep+wheat

Expected Output: False

#### Test Case 5
Input: player 1, a board with a road for player 1 at (0, 3), the location (0, 2), NORMALPLAY, PLAYING_TURN, one wood+brick+sheep

Expected Output: False

#### Test Case 6
Input: player 1, a board with a road for player 1 at (0, 3), the location (0, 2), NORMALPLAY, PLAYING_TURN, one wood+brick+wheat

Expected Output: False

#### Test Case 7
Input: player 1, a board with a road for player 1 at (0, 3), the location (0, 2), NORMALPLAY, PLAYING_TURN, one wood+sheep+wheat

Expected Output: False

#### Test Case 8
Input: player 1, a board with a road for player 1 at (0, 3), the location (0, 2), NORMALPLAY, PLAYING_TURN, one brick+sheep+wheat

Expected Output: False

## canUpgradeSettlement(settlement: Settlement)
### BVA Step 1
Input: a settlement, a board, a game state/turn phase, player's resources

Output: a boolean

### BVA Step 2:
Input: a settlement (player + pair of intervals), a collection of buildings/roads, cases (SETUP, NORMALPLAY), cases (either PLAYING_TURN or not), a collection of resources and counts

Output: boolean

### BVA Step 3:
Input: 
* player 1
* PLAYING_TURN
* PLACING_ROAD
* insufficient ore (two ore and two wheat)
* insufficient wheat (three ore and one wheat)
* three ore and two wheat

Output: True/False

### BVA Step 4:
#### Test Case 1
Input: settlement at (2,4), player 1 has three ore and two wheat, PLACING_ROAD

Output: false

#### Test Case 2
Input: settlement at (2,4), player 1 has two ore and two wheat, PLAYING_TURN

Output: false

#### Test Case 3
Input: settlement at (2,4), player 1 has three ore and one wheat, PLAYING_TURN

Output: false

#### Test Case 4
Input: settlement at (2,4) that is not on the board, player 1 has three ore and two wheat, PLAYING_TURN

Output: false

#### Test Case 5
Input: settlement at (2,4), player 1 has three ore and two wheat, PLAYING_TURN

Output: true

## method: moveRobber(loc: HexLocation): void

### BVA Step 1
Input: game state/turn phase, robber location, new robber location

Output: game state/turn phase, IllegalArgumentException

### BVA Step 2
Input: cases/cases, pair of intervals/pair of intervals

Output: cases/cases or exception

### BVA Step 3
Input:
* SETUP/END_TURN (invalid state to move robber)
* NORMALPLAY/MOVING_ROBBER (valid to move robber)
* Robber location at (2, 2)
* New robber location (2, 3)
* Same robber location (2, 2)
* Invalid hex location (-1, 0)

Output:
* Moving robber to (2, 3)
* IllegalArgumentException
* State to NORMALPLAY/STEALING_RESOURCE

### BVA Step 4
#### Test Case 1
Input: SETUP/END_TURN, robber @(2,2), location (2,3)

Expected Output: IllegalArgumentException

#### Test Case 2
Input: NORMALPLAY/MOVING_ROBBER, robber @(2,2), location (2,3)

Expected Output: Robber @(2,3), state is NORMALPLAY/STEALING_RESOURCE

#### Test Case 3
Input: NORMALPLAY/MOVING_ROBBER, robber @(2,2), location (2,2)

Expected Output: IllegalArgumentException

#### Test Case 4
Input: NORMALPLAY/MOVING_ROBBER, robber @(2,2), location (-1,0)

Expected Output: IllegalArgumentException

## method: stealResource(thief: Player, victim: Player): void

### BVA Step 1
Input: two players' resources, game state/turn phase

Output: two players' resources, game state/turn phase

### BVA Step 2
Input: two collections of resources and associated counts, cases for both game state/turn phase

Output: (same datatypes as the input) or IllegalArgumentException

### BVA Step 3

Input:
* NORMALPLAY/STEALING_RESOURCE, thief has no resources, victim has 1 wood
* NORMALPLAY/STEALING_RESOURCE, thief has no resources, victim has 4 wood
* NORMALPLAY/STEALING_RESOURCE, thief has 1 brick, victim has 4 wood
* NORMALPLAY/PLAYING_TURN
* victim has no resources

Output:
* State NORMALPLAY/PLAYING_TURN
* Thief now having 1 wood
* Thief now having 1 wood and 1 brick
* Victim having no resources
* Victim having 3 wood
* IllegalArgumentException

### BVA Step 4
#### Test Case 1
Input: NORMALPLAY/STEALING_RESOURCE, thief has no resources, victim has 1 wood

Expected Output: NORMALPLAY/PLAYING_TURN, thief has 1 wood, victim has no resources

#### Test Case 2
Input: NORMALPLAY/STEALING_RESOURCE, thief has no resources, victim has 4 wood

Expected Output: NORMALPLAY/PLAYING_TURN, thief has 1 wood, victim has 3 wood

#### Test Case 3
Input: NORMALPLAY/STEALING_RESOURCE, thief has 1 brick, victim has 1 wood

Expected Output: NORMALPLAY/PLAYING_TURN, thief has 1 wood and 1 brick, victim has no resources

#### Test Case 4
Input: NORMALPLAY/PLAYING_TURN, thief has no resources, victim has 1 wood

Expected Output: IllegalArgumentException

#### Test Case 5
Input: NORMALPLAY/STEALING_RESOURCE, thief has no resources, victim has no resources

Expected Output: IllegalArgumentException

## method: skipSteal(): void

### BVA Step 1:
Input: game state/phase

Output: game state/phase, exception

### BVA Step 2:
Input: cases

Output: cases, IllegalStateException

### BVA Step 3:
Input:
* NORMALPLAY/STEALING_RESOURCE
* NORMALPLAY/MOVING_ROBBER

Output:
* NORMALPLAY/PLAYING_TURN
* IllegalStateException

### BVA Step 4
#### Test Case 1
Input: NORMALPLAY/STEALING_RESOURCE

Expected Output: NORMALPLAY/PLAYING_TURN

#### Test Case 2
Input: NORMALPLAY/MOVING_ROBBER

Expected Output: IllegalStateException

## method: getPlayersToStealFrom(currentTurn: Player): List<Player>
### BVA Step 1
Input: turn phase, robber location, board, current player's turn

Output: list of players

### BVA Step 2
Input: cases, pair of intervals, collection of buildings, index

Output: collection of players

### BVA Step 3
Input:
* Invalid turn phase (PLAYING_TURN)
* Valid turn phase (STEALING_RESOURCE)
* Use robber location (3, 3)
* Use player 1's turn
* An empty board
* A board with 1 player settlement at the robber location
* A board with the current turn player and 1 other player at the robber location
* A board w/ 1 adjacent player but they have no resources
* Board with 3 adjacent players all with resources

Outputs:
* IllegalStateException
* Empty list
* List with only 1 player

### BVA Step 4
#### Test Case 1
Input: PLAYING_TURN, empty board, robber (3,3), player 1's turn

Expected Output: IllegalStateException

#### Test Case 2
Input: STEALING_RESOURCE, empty board, robber (3,3), player 1's turn

Output: Empty list

#### Test Case 3
Input: STEALING_RESOURCE, board w/ settlement for player 2 at (3,7), robber (3,3), player 1's turn, player 2 has 1 wood

Output: list w/ player 2

#### Test Case 4
Input: STEALING_RESOURCE, board w/ settlement for player 2 at (3,7) and player 1 at (3,9), robber (3,3), player 1's turn, player 2 has 1 wood

Output: list w/ player 2

#### Test Case 5
Input: STEALING_RESOURCE, board w/ settlement for player 2 at (3,7) and player 1 at (3,9), robber (3,3), player 1's turn, player 2 has no resources

Output: empty list

#### Test Case 5
Input: STEALING_RESOURCE, board w/ settlement for player 2 at (3,7) and player 3 at (3,9) and player 4 at (4, 8), robber (3,3), player 1's turn, player 2/3/4 have no resources

Output: empty list


## method: findLongestRoad(player: Player): int
### BVA Step 1
Input: A Player, A collection of roads

Output: The count of the longest road for the player

### BVA Step 2
Input: A Player, Collection of Roads

Output: An Interval

### BVA Step 3
Input:
* A Player with no roads
* A Player with 1 road
* A Player with 2 connected roads
* A Player with 2 connected roads + 3 connected roads
* A Player with 5 connected roads

Outputs:
* The values 1-5

### BVA Step 4
#### Test Case 1
Input: A Player with no roads

Expected Output: 0

#### Test Case 2
Input: A Player with 1 road

Expected Output: 1

#### Test Case 2
Input: A Player with 2 connected roads

Expected Output: 2

#### Test Case 3
Input: A Player with 2 connected roads + 3 connected roads

Expected Output: 3

#### Test Case 4
Input: A Player with 5 connected roads

Expected Output: 5

## method: getRequiredDiscardAmount(): int
### BVA Step 1
Input: turn phase, current discarding player, players' resources

Output: number of resources required to discard

### BVA Step 2
Input: cases, index, collection of resources and counts 

Output: count or exception

### BVA Step 3
Input:
* Valid turn phase (DISCARDING_RESOURCES)
* Invalid turn phase (PLAYING_TURN)
* Current player has no resources
* Current player has seven resources
* Current player has eight resources
* Current player has nine resources

Output:
* IllegalStateException
* 0
* 4

### BVA Step 4
#### Test Case 1
Input: Index 0, player 1 has 4 wood and 4 brick, PLAYING_TURN

Expected Output: IllegalStateException

#### Test Case 2
Input: Index 0, player 1 has no resources, DISCARDING_RESOURCES

Expected Output: 0

#### Test Case 3
Input: Index 0, player 1 has 3 wood and 4 brick, DISCARDING_RESOURCES

Expected Output: 0

#### Test Case 4
Input: Index 0, player 1 has 3 wood, 4 brick and 1 sheep, DISCARDING_RESOURCES

Expected Output: 4

#### Test Case 5
Input: Index 0, player 1 has 3 wood, 4 brick, 1 sheep and 1 wheat, DISCARDING_RESOURCES

Expected Output: 4

## method: discardResources(resources: CountCollection<Resource>): void
### BVA Step 1
Input: current player to discard, count collection of resources

Output: modified resources, new player to discard, new turn phase; or an exception

### BVA Step 2
Input: index, collection of resources and counts

Output: collection of resources and counts, index, cases, exception

### BVA Step 3
Input:
* Index 0
* Index 3
* Player w/ 8 total resources (8 wood)
* Discarding 4 resources (4 wood)
* Discarding 3 resources (3 wood)
* Discarding 4 other resources (4 brick, when we have 8 wood)

Output:
* Player w/ 4 wood left
* IllegalArgumentException
* Turn phase remains DISCARDING_RESOURCES, index = 1
* Turn phase flips to MOVING_ROBBER

### BVA Step 4
#### Test Case 1
Input: Index 0, player has 8 wood, discarding 4 wood

Expected Output: DISCARDING_RESOURCES, index = 1, player now has 4 wood

#### Test Case 2
Input: Index 0, player has 8 wood, discarding 3 wood

Expected Output: IllegalArgumentException

#### Test Case 3
Input: Index 3, player has 8 wood, discarding 4 wood

Expected Output: MOVING_ROBBER, player now has 4 wood

#### Test Case 3
Input: Index 0, player has 8 wood, discarding 4 brick

Expected Output: IllegalArgumentException

#### Test Case 4
Input: Index 0, player has 4 wood and 4 brick, discarding 4 wood

Expected Output: MOVING_ROBBER, player now has 4 brick

## method: shufflePlayers(random: Random): void
### BVA Step 1
Input: list of players, a random

Output: list of players

### BVA Step 2
Input: a collection of Player, a random

Output: a new ordering of the collection of Player

### BVA Step 3
Input:
* Really only need to consider the list of 4 default players (since we always have 4 players)
* Mocked random

Output:
* Mocked to produce the ordering Yellow, Blue, White, Red

### BVA Step 4
#### Test Case 1
Input: Added players in order: Red, Blue, Yellow, White

Expected Output: Players in order: Yellow, Blue, White, Red

## method: getTradeAmount(player: Player, resource: Resource): int
(Returns the amount of the provided resource you need to trade to get 1 of any other resource)

### BVA Step 1
Input: Resource, the board, a player

Output: number

### BVA Step 2
Input: Resource (cases), collection of settlements and ports, player

Output: cases (2, 3, or 4)

### BVA Step 3
Input:
* Board with nothing on it
* Board with a port for player 1 at the 3:1 port
* Board with a port for player 1 at the 2:1 wood port
* Board with a port for player 1 at the 2:1 brick port
* Board with a port for player 1 at the 2:1 wheat port
* Board with a port for player 1 at the 2:1 sheep port
* Board with a port for player 1 at the 2:1 ore port
* Wood
* Brick
* Sheep
* Ore
* Wheat

Output:
* 2
* 3
* 4

### BVA Step 4
#### Test Case 1
Input: default board (empty), player 1, Brick

Expected Output: 4

#### Test Case 2
Input: default board, player 1, settlement for player 1 at (0,3), Sheep

Expected Output: 3

#### Test Case 3
Input: default board (empty), player 1, settlement for player 1 at (1,1), Wood

Expected Output: 2

#### Test Case 4
Input: default board (empty), player 1, settlement for player 1 at (3,1), Brick

Expected Output: 2

#### Test Case 5
Input: default board (empty), player 1, settlement for player 1 at (0,5), Wheat

Expected Output: 2

#### Test Case 6
Input: default board (empty), player 1, settlement for player 1 at (4,8), Sheep

Expected Output: 2

#### Test Case 7
Input: default board (empty), player 1, settlement for player 1 at (1,9), Ore

Expected Output: 2

## method: purchaseDevelopmentCard(playerNumber: int): void
### BVA Step 1
Input: player number, player resources, turn phase

Output: player resources, player dev card count

### BVA Step 2
Input: interval \[0,3], collection of resource + count, cases

Output: collection of resource + count, number (count)

### BVA Step 3
Input:
* Player 1
* Valid turn phase (PLAYING_TURN)
* Invalid turn phase (MOVING_ROBBER)
* 1 sheep, 1 wheat, 1 ore
* Resource checks already handled by CardTracker tests

Output:
* Empty collection of resources
* 1 dev card
* IllegalStateException

### BVA Step 4
#### Test Case 1
Input: player 1, PLAYING_TURN, 1 wheat/sheep/ore

Expected Output: player 1 has 1 dev card and 0 resources

#### Test Case 2
Input: player 1, MOVING_ROBBER, 1 wheat/sheep/ore

Expected Output: IllegalStateException