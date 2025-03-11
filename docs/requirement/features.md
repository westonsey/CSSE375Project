### F1: Tracks the total Victory points earned by each player
-Largest Army + 2
-Longest Road + 2
-VP Development Cards +1
-City + 2

### F2: Tracks the total number of roads built by each player

### F3: Tracks the total number of "Knight" cards played by each player

### F4: Tracks the total number of resources each player has used 

### F5: Tracks the total number of resources each player has remaining.
- Each player starts with 5 Settlements, 4 Cities, 15 roads
### F6: Tracks the phase of each turn

### F7: ABC


BOARD GAME CONTEXT:
### F1: Ability to detect when any player has won
condition: Victory Points >= 10
### F2: Ability to randomize the terrain hex on the "board" 

### F3: Ability to flip a single hex upward
condition: when a player builds a road or a building on that specific hex
### F4: Ability to place the tokens on proper hexes excluding deserts.
condition: clockwise?
condition: random?
### F5: Ability to show the available resources for the corresponding token

### F6: Ability to shuffle the development card deck

### F7: Ability to set the turn order
Condition - High-order dice roll

### F10 Ability to separate resource cards into 5 piles
-Hills, Forests, Mountains, Fields, Pastures. 


PLAYER CONTEXT:
### F1 Ability for each player to start the game with the condition
-Condition: 2 settlements && 2 Roads
### F2: Ability to build roads and settlement
Roads: condition: only at the joint of 2 hexes 
Settlement: When there is enough space between settlement
### F3: Ability to Roll the dice:
-Condition: At the beginning of each player's turn. 
### F4: Ability to trade resource cards.
-Condition: At least 1 person involved with trade must be in turn phase
### F5: Ability to propose a trade or offer

### F6: Ability to upgrade settlements to city
-Condition: Requires 2 grain and 3 ore cards

GAMEPLAY CONTEXT:
### F1 Ability to track each player's phase
-Resource Production, Trade, Build Phase
### F2: Ability for players to receive resource cards on each dice roll
-Condition: Any Player with settlement or city touching the corresponding hex number
-Condition: Settlements get 1 resource, Cities get 2
### F3: Ability to receive resources in 2nd Settlement
-Condition: Adjacent hexes determine which resources each player receives. 
### F4: Ability to change trade requirements for each player
-Default rate 4:1
-Harbor 3:1 or 2:1
### F5: Ability to activate the robber
-Condition: 7 is rolled by any player

ROBBER CONTEXT:
### F1: Ability to steal resources -- cut in half
-Player with 7 or more resource cards
### F2: Ability to prevent resource production
-Condition: on the placed hex
### F3: Ability for the player to move a robber
-Condition: a player who activated the robber
### F4: Ability to steal one resource card
-Condition: from a player with a settlement or city next to robber hex









