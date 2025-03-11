# UC1. Game Setup

### Actor: Player(s)

### Description
The Player starts the game, creating the board, assigning turn order, and placing initial settlements and roads.

### Basic Flow
1. User joins or creates a new game
2. After all the players have joined, the lead player starts the game
3. The game creates the gameboard
4. The game assigns a turn order to the players
5. Players go from top to bottom of the order placing their first settlement and road on the game board
6. Players go from bottom to top of the order placing their second settlement and road on the game board
7. Players receive resources according to their second settlement
8. The game starts

### Alternate Flow
N/A

### Exceptions
Step 5: Player attempts to place a settlement next to an existing settlement
1. System informs the Player that the location is invalid
2. Player confirms message
3. System reprompts the user to place the settlement again


### Preconditions
The Player has started a game/created a lobby

### Postconditions
The game has been set up, and all Players have their starting resources/settlements/roads.

### System or subsystem
The main game handler

### Other Stakeholders
Other players in the game

### Special Requirements:
none


