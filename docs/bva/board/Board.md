# method: generate(rand: Random): void

## BVA Step 1
Input: a Random (random number generator)

Output: an ordering of hexes and numbers

## BVA Step 2
Input: a random number generator

Output: an ordered collection of Resource, an ordered collection of numbers

## BVA Step 3
Input:
* A Random value such that the output places all the numbers in order, and all the resources in chunks together
* A Random value such that the output builds the default board
    * [https://myriadsgifts.com/cdn/shop/products/4e99c8a7-443f-5190-ac6f-4d704c2edeb8_1024x1024@2x.jpg?v=1586024528]

Output:
* All of the numbers in order + all of the resources chunked together (wood, then brick, then wheat, then sheep, then ore, then desert on its own)
* The default Catan board

## BVA Step 4
### Test value 1
Input: A mocked Random constructed to create the board with all numbers in order from top to bottom, left to right, and the resources grouped (wood, then brick, then wheat, then sheep, then ore, then desert)

Expected Output: The board given above (check each hex)

### Test value 2
Input: A mocked Random constructed to create the default Catan board in this image ([https://myriadsgifts.com/cdn/shop/products/4e99c8a7-443f-5190-ac6f-4d704c2edeb8_1024x1024@2x.jpg?v=1586024528])

Expected Output: The default Catan board given above

# method: getHexAt(loc: HexLocation): Hexagon

## BVA Step 1
Input: a Board, and a HexLocation

Output: a Hexagon

## BVA Step 2
Input: an ordering of numbers, an ordering of resources, and an ordered pair

Output: a resource, a desert boolean, and a number

## BVA Step 3
Input:
* The default Catan board
* An ungenerated board
* The "first" hex (0,1)
* The "last" hex (4,3)
* Just before the first hex (0,0)
* Just after the last hex (4,4)
* An odd-row hex (1,2)
* The middle hex (desert) (2,2)
* The maximum hex (MAX,MAX)
* The minimum hex (MIN,MIN)

Output:
* A hex with ore and number 10
* A hex with sheep and number 11
* A hex with sheep and number 4
* A desert hex
* IllegalArgumentException
* IllegalStateException

## BVA Step 4
### Test value 1
Input: The default Catan board, hex location (0,1)

Expected Output: A hex with ore, number 10, and no desert

### Test value 2
Input: The default Catan board, hex location (4,3)

Expected Output: A hex with sheep, number 11, and no desert

### Test value 3
Input: The default Catan board, hex location (1,2)

Expected Output: A hex with sheep, number 4, and no desert

### Test value 4
Input: The default Catan board, hex location (2,2)

Expected Output: A hex with desert (number/resource don't matter)

### Test value 5
Input: The default Catan board, hex location (0,0)

Expected Output: IllegalArgumentException

### Test value 6
Input: The default Catan board, hex location (4,4)

Expected Output: IllegalArgumentException

### Test value 7
Input: The default Catan board, hex location (MAX,MAX)

Expected Output: IllegalArgumentException

### Test value 8
Input: The default Catan board, hex location (MIN,MIN)

Expected Output: IllegalArgumentException

### Test value 9
Input: An ungenerated Catan board, hex location (2,2)

Expected Output: IllegalStateException

# method: canPlaceSettlement(p: Player, loc: VertexLocation, force: boolean): boolean

## BVA Step 1
Input: a Player, a Board, a VertexLocation, a boolean

Output: a boolean of whether or not they can place there

## BVA Step 2
Input: a Player, stored locations of roads and buildings, a pair of intervals - row from \[0,5] and column from \[0,10], and a boolean

Output: a boolean

## BVA Step 3
Input:
* Any player, force=false, the minimum location (MIN, MIN), an empty board
* Any player, force=false, the maximum location (MAX, MAX), an empty board
* Any player, force=false, the first vertex (0, 2), a board with a road for that player next to the vertex
* Any player, force=false, the last vertex (5, 8), a board with a road for that player next to the vertex
* Any player, force=false, and a vertex that isn't next to one of their roads, an empty board
* Any player, force=false, and a vertex that has an adjacent vertex with a settlement on it, a board with a settlement on it
* Any player, force=false, and a vertex that already has a settlement on it, a board with a settlement on it
* Any player, force=false, the last vertex (5, 8), a board with a road for an opposing player next to the vertex
* Any player, force=true, the minimum location on the board, empty board
* Any player, force=true, a settlement with no neighboring roads (3, 3), empty board
* Any player, force=true, a settlement at (2, 2), a board with a settlement adjacent to (2, 2)
* Any player, force=true, a settlement with no neighboring roads (2, 2), random Road on Board

Output:
* True, False

## BVA Step 4
### Test value 1
Input: Player 1, vertex (MIN, MIN), empty board, force=false

Expected Output: False

### Test value 2
Input: Player 1, vertex (MAX, MAX), empty board, force=false

Expected Output: False

### Test value 3
Input: Player 1, vertex (0, 2), a board with a road for Player 1 at (0, 3), force=false

Expected Output: True

### Test value 4
Input: Player 1, vertex (5, 8), a board with a road for Player 1 at (4, 12), force=false

Expected Output: True

### Test value 5
Input: Player 1, vertex (5, 8), a board with a road for Player 2 at (4, 12), force=false

Expected Output: False

### Test value 6
Input: Player 1, vertex (2, 2), an empty board, force=false

Expected Output: False

### Test value 7
Input: Player 1, vertex (2, 2), a board with a settlement for Player 1 at (2, 1) and a road for Player 1 at (2, 2), force=false

Expected Output: False

### Test value 8
Input: Player 1, vertex (2, 2), a board with a settlement for Player 1 at (2, 2), force=false

Expected Output: False

### Test value 9
Input: Player 1, vertex (MIN,MIN), an empty board, force=true

Expected Output: False

### Test value 10
Input: Player 1, vertex (2, 2), an empty board, force=true

Expected Output: False

### Test value 11
Input: Player 1, vertex (2, 2), a board with a settlement for Player 1 at (2, 1) and a road for Player 1 at (2, 2), force=true

Expected Output: False

### Test value 12
Input: Any player, force=true, a settlement with no neighboring roads (2, 2), random Road on Board

Expected Output: False

# method: canPlaceRoad(p: Player, loc: BorderLocation, force: boolean): boolean

## BVA Step 1
Input: a Player, a Board, a BorderLocation, a boolean to force the placement

Output: a boolean of whether or not they can place there

## BVA Step 2
Input: a Player, stored locations of roads and buildings, a pair of intervals - row from \[0,4] and column from \[-1,15] (only -1 if row is odd), a boolean

Output: a boolean

## BVA Step 3
Input:
* Any player, the minimum location (MIN, MIN), an empty board, force=false
* Any player, the maximum location (MAX, MAX), an empty board, force=false
* Any player, the first border (0, 3), a board with a settlement for that player next to the vertex, force=false
* Any player, the last border (4, 12), a board with a settlement for that player next to the vertex, force=false
* Any player, the last border (4, 12), a board with a settlement for an opposing player next to the vertex, force=false
* Any player, the last border (4, 12), a board with a road for that player next to the vertex, force=false
* Any player, the last border (4, 12), a board with a road for an opposing player next to the vertex, force=false
* Any player, and a border that isn't next to one of their settlements or roads, an empty board, force=false
* Any player, and a border that already has a road on it, a board with a road on it, force=false
* Any player, the minimum location (MIN, MIN), an empty board, force=true
* Any player, the border (3, 3), an empty board, force=true
* Any player, the border (0, 3), an empty board, force=false, case random building on board
* Any player, the border (0, 3), an empty board, force=false case random road on board

Output:
* True, False

## BVA Step 4
### Test value 1
Input: Player 1, border (MIN, MIN), empty board, force=false

Expected Output: False

### Test value 2
Input: Player 1, border (MAX, MAX), empty board, force=false

Expected Output: False

### Test value 3
Input: Player 1, border (0, 3), a board with a settlement for Player 1 at (0, 2), force=false

Expected Output: True

### Test value 4
Input: Player 1, border (4, 12), a board with a settlement for Player 1 at (5,8), force=false

Expected Output: True

### Test value 5
Input: Player 1, border (4, 12), a board with a settlement for Player 2 at (5,8), force=false

Expected Output: False

### Test value 6
Input: Player 1, border (4, 12), a board with a road for Player 1 at (4,11), force=false

Expected Output: True

### Test value 7
Input: Player 1, border (4, 12), a board with a road for Player 2 at (4,11), force=false

Expected Output: False

### Test value 8
Input: Player 1, border (4, 12), an empty board, force=false

Expected Output: False

### Test value 9
Input: Player 1, border (4, 12), a board with a road for Player 1 at (4, 12), force=false

Expected Output: False

### Test value 10
Input: Player 1, border (MIN, MIN), an empty board, force=true

Expected Output: False

### Test value 11
Input: Player 1, border (3, 3), an empty board, force=true

Expected Output: True

### Test value 12
Input: Player 1, the border (0, 3), an empty board, force=false, case random building on board

Expected Output: False

### Test value 13
Input: Player 1, the border (0, 3), an empty board, force=false case random road on board

Expected Output: False

# method: placeSettlement(p: Player, loc: VertexLocation, force: boolean): void

## BVA Step 1
Input: a Player, a VertexLocation, a board, a boolean to force an invalid placement

Output: a new board

## BVA Step 2
Input: a Player, stored locations of roads and buildings, a pair of intervals - row from \[0,4] and column from \[-1,15] (only -1 if row is odd), a boolean

Output: a new set of stored locations of roads and buildings

## BVA Step 3

(NOTE: Since we tested all types of invalid placements in "canPlace" methods, we only need to check one valid and one invalid placement in the actual "place" methods)

Input:
* any Player, the minimum vertex (MIN,MIN), an empty board, false for force
* any Player, the minimum vertex (MIN,MIN), an empty board, true for force (should still fail for invalid vertex)
* any Player, the valid vertex (2, 2), a board with a road for that player at (2, 2), false for force
* any Player, the valid vertex (2, 2), an empty board, true for force

Output:
* IllegalArgumentException
* A board with a new settlement at (2,2)

## BVA Step 4
### Test value 1
Input: Player 1, vertex (MIN, MIN), empty board, force=false

Expected Output: IllegalArgumentException

### Test value 2
Input: Player 1, vertex (MIN, MIN), empty board, force=true

Expected Output: IllegalArgumentException

### Test value 3
Input: Player 1, vertex (2, 2), board with a road for Player 1 at (2,2), force=false

Expected Output: a board with a road for Player 1 at (2, 2) and a settlement for Player 1 at (2, 2)

### Test value 4
Input: Player 1, vertex (2, 2), empty board, force=true

Expected Output: a board with a settlement for Player 1 at (2, 2)

# method: placeRoad(p: Player, loc: BorderLocation, force: boolean): void

## BVA Step 1
Input: a Player, a BorderLocation, a board, a boolean to force an invalid placement

Output: a new board

## BVA Step 2
Input: a Player, stored locations of roads and buildings, a pair of intervals - row from \[0,5] and column from \[0,10], a boolean

Output: a new set of stored locations of roads and buildings

## BVA Step 3

(NOTE: Since we tested all types of invalid placements in "canPlace" methods, we only need to check one valid and one invalid placement in the actual "place" methods)

Input:
* any Player, the minimum border (MIN,MIN), an empty board, false for force
* any Player, the minimum border (MIN,MIN), an empty board, true for force (should still fail for invalid border)
* any Player, the valid border (2, 2), a board with a road for that player at (2, 1), false for force
* any Player, the valid border (2, 2), an empty board, true for force

Output:
* IllegalArgumentException
* A board with a new border at (2,2)

## BVA Step 4
### Test value 1
Input: Player 1, border (MIN, MIN), empty board, force=false

Expected Output: IllegalArgumentException

### Test value 2
Input: Player 1, border (MIN, MIN), empty board, force=true

Expected Output: IllegalArgumentException

### Test value 3
Input: Player 1, border (2, 2), board with a road for Player 1 at (2,1), force=false

Expected Output: a board with roads for Player 1 at (2, 1) and at (2, 2)

### Test value 4
Input: Player 1, border (2, 2), empty board, force=true

Expected Output: a board with a road for Player 1 at (2, 2)

# method: getHexesAtNumber(num: int): List<Hexagon>

## BVA Step 1
Input: integer value from dice roll

Output: List of hexagons with that number
 
## BVA Step 2
Input: interval[2,12]

Output:Collection (the collection items are dependent on the input number and therefore do not need to be extensivly tested in accordance to the BVA Catelog)

## BVA Step 3
//should test all combinations for hexagon values to ensure each resource hexagon is pulled correctly into the array

Input: integer values: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 12, 13


Output: 
Collection (Elements of collection will be dependent on the number input and randomization of the board)



## BVA Step 4
### Test value 1
input: 1

Output: Empty Collection

### Test value 2
input: 2

output: Collection of with Hexagones associted with number 2 of size 1
### Test value 3
input: 2

output: Collection of with Hexagones associted with number 2 of size 2
### Test value 3
input: 3

output: Collection of with Hexagones associted with number 3 of size 2
### Test value 4
input: 4

output: Collection of with Hexagones associted with number 4 of size 2
### Test value 5
input: 5

output: Collection of with Hexagones associted with number 5 of size 2
### Test value 6
input: 6

output: Collection of with Hexagones associted with number 6 of size 2
### Test value 7
input: 7

output: Collection of size 0
### Test value 8
input: 8

output: Collection of with Hexagones associted with number 8 of size 2
### Test value 9
input: 9

output: Collection of with Hexagones associted with number 9 of size 2
### Test value 10
input: 10

output: Collection of with Hexagones associted with number 10 of size 2
### Test value 11
input: 11

output: Collection of with Hexagones associted with number 11 of size 2
### Test value 12
input: 12

output: Collection of with Hexagones associted with number 12 of size 1
### Test value 13
input: 13

output: Empty Collection


//This Method has been turned for integration testing

# method: addPlayerResourcesFromHex(hex: Hexagon): void

## BVA Step 1
Input: Hexagon, List of Currently Placed Buildings

Output: Players get appropriate resources if they have a building on a hex

## BVA Step 2
Input: Cases, Collection

Output: Collection (The output is dependent on the input hex and does not follow a BVA Catalog datatype)

## BVA Step 3

Input: 1: Hexagon with no buildings,  Hexagon with 1 building,  Hexagon with 2 buildings,  Hexagon with 3 buildings (since only a max of 3 buildings can be placed per hex and this covers all cases for both inputs)

Output: Player receives no resources, player recieves 1 resource, player recieves 2 resources, player recieves 3 resources

## BVA Step 4
### Test value 1
Input: Hexagon with no buildings

Expected Output: Player receives no resources
### Test value 2
Input: Hexagon with 1 building

Expected Output:  player recieves 1 resource
### Test value 3
Input:  Hexagon with 2 buildings

Expected Output: player recieves 2 resources
### Test value 4
Input: Hexagon with 3 buildings

Expected Output: player recieves 3 resources

# method: canUpgradeSettlement(settlement: Settlement): boolean

## BVA Step 1
Input: a settlement, a board

Output: a yes or no answer

## BVA Step 2
Input: a settlement (player reference, ordered pair of intervals), and a collection of placed buildings and roads

Output: a boolean

## BVA Step 3
Input:
* an empty board
* a board with one settlement on it
* a board with two settlements on it from different players
* a board with a city on it
* a settlement that exists on the board
* a settlement that does not exist on the board

Output:
* True, false

## BVA Step 4
### Test value 1
Input: An empty board, a settlement that does not exist on the board \[player 1, (1, 1)]

Expected Output: False

### Test value 2
Input: A board with one settlement \[player 1, (1, 1)], a reference to that settlement

Expected Output: True

### Test value 3
Input: A board with one settlement \[player 1, (1, 1)], a reference to another settlement \[player 2, (1, 1)]

Expected Output: False

### Test value 4
Input: A board with two settlements from different players \[player 1, (1, 1)], \[player 2, (3, 1)], a reference to one of those settlements \[player 1, (1, 1)]

Expected Output: True

### Test value 5
Input: A board with a city on it \[player 1, (1, 1)], \[player 2, (3, 1)], a reference to an alleged settlement at that location \[player 1, (1, 1)]

Expected Output: True

# method: upgradeSettlement(settlement: Settlement): void

## BVA Step 1
Input: a settlement, a board

Output: a board

## BVA Step 2
Input: a settlement (player reference, ordered pair of intervals), and a collection of placed buildings and roads

Output: a new collection of placed buildings and roads, or an exception

## BVA Step 3
Input:
* a valid board-settlement combination (1 settlement \[player 1, (1, 1)])
* an invalid board-settlement combination (empty board, alleged settlement at \[player 1, (1, 1)])

Output:
* a board with a city at the location (1,1) and no more settlements
* an IllegalArgumentException

## BVA Step 4
### Test value 1
Input: A board with the settlement \[player 1, (1, 1)] on it, the settlement \[player 1, (1,1)] 

Expected Output: A board with a city at \[player 1, (1, 1)] and no settlements on it

### Test value 2
Input: An empty board, the settlement \[player 1, (1, 1)]

Expected Output: IllegalArgumentException

# method: getPort(settlement: Settlement): PortType

## BVA Step 1
Input: a settlement, a board

Output: a PortType, or false

## BVA Step 2
Input: a settlement (ordered pair of intervals)

Output: an identifier for the type of trading port, or a false value

## BVA Step 3
Input:
* a settlement with a valid location

Output:
* the type of the port at the settlement location [WOOD, ORE, WHEAT, SHEEP, BRICK, 3FOR1]
* a boolean [FALSE, TRUE]

## BVA Step 4
### Test value 1
Input: A board with the settlement \[(0, 2)] on it

Expected Output: A 3-1 PortType

### Test value 2
Input: A board with the settlement \[(0, 3)] on it

Expected Output: A 3-1 PortType

### Test value 3
Input: A board with the settlement \[(0, 6)] on it

Expected Output: A Wheat PortType

### Test value 4
Input: A board with the settlement \[(1, 9)] on it

Expected Output: A Ore PortType

### Test value 5
Input: A board with the settlement \[(3, 1)] on it

Expected Output: A Brick PortType

### Test value 6
Input: A board with the settlement \[(4, 8)] on it

Expected Output: A Sheep PortType

### Test value 7
Input: A board with the settlement \[(2, 9)] on it

Expected Output: A null value

# method: addResourceForGameSetup(p: Player, loc: VertexLocation): void

## BVA Step 1
Input: a Player, a Vertex Location for a settlement

Output: add Resources to player

## BVA Step 2
Input: a Player (player), and an Integer Vertex Location

Output: Player gains 3 resource dependent on the Vertex Location (output not important in scope of BVA)

## BVA Step 3
Input:
* Player 1
* vertex location 2,2
* vertex location 0,2

Output:
* Player gains 3 resources from surrounding hexes of 2, 2
* Player gains 1 resource from surrounding hexes of 0, 2

## BVA Step 4
(Only one of the set is needed to check resources since the VertexLocation is known to be a valid settlement placement and the only system under test if if resoucres were added. Since resources are added we know it works for all valid vertex locations)
### Test value 1
Input: Player 1, (2,2)

Expected Output: Player gains 3 resources from surrounding hexes of 2, 2

### Test value 2
Input: Player 1, (0,2)

Expected Output: Player gains 1 resource from surrounding hex of 0, 2



# method: getAdjacentPlayers(loc: HexLocation): List<Player>

## BVA Step 1
Input: a hex location, a board

Output: list of players

## BVA Step 2
Input: a pair of intervals, a collection of hexes/numbers and placed buildings

Output: collection of players or exception

## BVA Step 3
Input:
* Invalid hex (-1,-1)
* Hex (2,1) - particular hex doesn't matter, so we can just use the same one every time
* An empty board
* A board with 1 player's settlement on the hex at (2,2)
* A board with 2 players' settlements on the hex at (2,2) and (2,4)
* A board with 2 players' settlements at (2,2) and (2,4), and player 1 also has another settlement on the hex at (3,3)
* A board with 1 player's settlement on the hex at (2,2) and another player that isn't on the hex at (3,5)

Output:
* IllegalArgumentException
* Empty player list
* List of one player
* List of two players

## BVA Step 4
### Test Case 1
Input: Empty board, hex (-1,-1)

Expected Output: Illegal Argument Exception

### Test Case 2
Input: Empty board, hex (2, 1)

Expected Output: Empty player list

### Test Case 3
Input: Hex (2, 1), board w/ settlement for player 1 at (2,2)

Expected Output: List w/ player 1

### Test Case 4
Input: Hex (2, 1), board w/ settlement for player 1 at (2,2) and player 2 at (2,4)

Expected Output: List w/ player 1 and player 2

### Test Case 5
Input: Hex (2, 1), board w/ settlement for player 1 at (2,2) and (3,3) and player 2 at (2,4)

Expected Output: List w/ player 1 and player 2

### Test Case 6
Input: Hex (2, 1), board w/ settlement for player 1 at (2,2) and player 2 at (3,5)

Expected Output: List w/ player 1

# method: isBorderValid(loc: BorderLocation): boolean
## BVA Step 1
Input: a border location

Output: a boolean

## BVA Step 2
Input: a pair of intervals \[MIN, MAX]

Output: boolean

## BVA Step 3
Inputs:
* Border with invalid row (MIN, 0)
* Border with invalid column (0, MIN)
* Vertical border in last row (5, 3)
* Valid border (2, 2)
* Valid border Min (0, 3)
* Valid border Max (5, 2)
* * Valid border Min (1, 0)
* Valid border Max (1, 12)

Outputs: True, false

## BVA Step 4
### Test Case 1
Input: (MIN, 0)

Expected Output: false

### Test Case 2
Input: (0, MIN)

Expected Output: false

### Test Case 3
Input: (5, 3)

Expected Output: false

### Test Case 4
Input: (2, 2)

Expected Output: true

### Test Case 5
Input: (0, 3)

Expected Output: true

### Test Case 6
Input: (5, 2)

Expected Output: true

### Test Case 5
Input: (1, 0)

Expected Output: true

### Test Case 6
Input: (1, 12)

Expected Output: true

# method: isHexValid(loc: HexLocation): boolean
## BVA Step 1
Input: hex location

Output: boolean

## BVA Step 2
Input: pair of intervals \[MIN, MAX]

Output: boolean

## BVA Step 3
Inputs:
* Small row (MIN, 0)
* Large row (MAX, 0)
* Small column (0, MIN)
* Large column (0, MAX)
* Valid hex (2, 2)
* Low Boundry (0,3)
* High Boundry (4,2)

Outputs: true, false

## BVA Step 4
### Test Case 1
Input: (MIN, 0)

Expected Output: false

### Test Case 2
Input: (0, MAX)

Expected Output: false

### Test Case 3
Input: (2, 2)

Expected Output: true

### Test Case 4
Input: (0, 3)

Expected Output: true

### Test Case 5
Input: (4, 2)

Expected Output: true


# method: isVertexValid(loc: VertexLocation): boolean
## BVA Step 1
Input: vertex location

Output: boolean

## BVA Step 2
Input: pair of intervals \[MIN, MAX]

Output: boolean

## BVA Step 3
Inputs:
* Small row (MIN, 0)
* Large row (MAX, 0)
* Small column (0, MIN)
* Large column (0, MAX)
* Valid vertex (2, 2)

Outputs: true, false

## BVA Step 4
### Test Case 1
Input: (MIN, 0)

Expected Output: false

### Test Case 2
Input: (MAX, 0)

Expected Output: false

### Test Case 3
Input: (0, MIN)

Expected Output: false

### Test Case 4
Input: (0, MAX)

Expected Output: false

# method: getMinVertexCol(int row): int
## BVA Step 1
Input: row

Output: minimun col for row

## BVA Step 2
Input: interval [0, 5]

Output: integer

## BVA Step 3
Inputs:
* Row 0
* Row 1
* Row 2
* Row 3
* Row 4
* Row 5

Outputs: 2, 1, 0, 0, 1, 2

## BVA Step 4
### Test Case 1
Input: 0

Expected Output: 2

### Test Case 2
Input: 1

Expected Output: 1

### Test Case 3
Input: 2

Expected Output: 0

### Test Case 4
Input: 3

Expected Output: 0

### Test Case 5
Input: 4

Expected Output: 1

### Test Case 5
Input: 5

Expected Output: 2

# method: getMinBorderCol(int row): int
## BVA Step 1
Input: row

Output: minimun border for row

## BVA Step 2
Input: interval [0, 5]

Output: integer

## BVA Step 3
Inputs:
* Row 0
* Row 1
* Row 2
* Row 3
* Row 4
* Row 5

Outputs: 3, 0, 0, -1, 2, 2

## BVA Step 4
### Test Case 1
Input: 0

Expected Output: 3

### Test Case 2
Input: 1

Expected Output: 0

### Test Case 3
Input: 2

Expected Output: 0

### Test Case 4
Input: 3

Expected Output: -1

### Test Case 5
Input: 4

Expected Output: 2

### Test Case 6
Input: 5

Expected Output: 2


# method: isBorderOccupied(BorderLocation loc): boolean
## BVA Step 1
Input: Border Location

Output: boolean

## BVA Step 2
Input: Cases(Occupied, Non Occupied)

Output: boolean

## BVA Step 3
Inputs:
* Occupied Border Location
* Non Occupied Border Location

Outputs: True, False

## BVA Step 4
### Test Case 1
Input: Occupied Border Location

Expected Output: True

### Test Case 2
Input: Non Occupied Border Location

Expected Output: False

# method: getIndexOfBuilding(Building b): int
## BVA Step 1
Input: Building 

Output: Integer index in array

## BVA Step 2
Input: Cases(Valid Building, Invalid Building)

Output: index or -1

## BVA Step 3
Inputs:
* Valid Building
* Invalid Building

Outputs: 0, -1

## BVA Step 4
### Test Case 1
Input: Valid Building

Expected Output: 1

### Test Case 2
Input: InValid Building

Expected Output: -1

