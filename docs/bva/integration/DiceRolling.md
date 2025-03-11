# method: rollDice, getHexesAtNumber, addPlayerResourcesFromHex

### BVA Step 1:

Input: two dice rolls, the board, robber location

Output: new turn state, the new player resource counts

### BVA Step 2:

Input: two intervals from 1 to 6 inclusive (cases where a sum of 7 is a special case), a collection of resources/numbers and locations of settlements, a pair of intervals (hex location)

Output: turn state (enumeration), collection of resources and counts

### BVA Step 3:

Input: 
* default board with settlement for player 1 at (1, 4) and a 5 and a 6 are rolled and robber is at (2, 2)
* default board with settlement for player 1 at (1, 4) and a 2 and a 4 are rolled and robber is at (2, 2)
* default board with settlement for player 1 at (1, 4) and a 3 and a 4 are rolled and robber is at (2, 2)
* default board with settlement for player 1 at (1, 4) and a 2 and a 4 are rolled and robber is at (1, 1)

Output: 
* no player receives resources
* player 1 receives 1 brick
* turn state changed to "moving robber"

### BVA Step 4:

#### Test Case 1:

Input: Default board with settlement for player 1 at (1, 4) and a 5 and a 6 are rolled and robber is at (2, 2), player 1 has no resources

Output: Player 1 has no resources, turn state is "playing turn"

#### Test Case 2:

Input: default board with settlement for player 1 at (1, 4) and a 2 and a 4 are rolled and robber is at (2, 2), player 1 has no resources

Output: Player 1 has 1 brick, turn state is "playing turn"

#### Test Case 3:

Input: default board with settlement for player 1 at (1, 4) and a 3 and a 4 are rolled and robber is at (2, 2), player 1 has no resources

Output: Player 1 has no resources, turn state is "moving robber"

#### Test Case 4:

Input: default board with settlement for player 1 at (1, 4) and a 2 and a 4 are rolled and robber is at (1, 1), player 1 has no resources 

Output: Player 1 has no resources, turn state is "playing turn"