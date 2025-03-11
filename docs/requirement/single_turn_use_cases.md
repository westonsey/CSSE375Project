# UC2. Single Turn of Game

### Actor: Current Player

### Description
The Player rolls the dice and collects resources according to controlled settlements. The Player then can build buildings, trade, and play development cards.

### Basic Flow
1. The Player rolls the dice
2. The Player gains resources according to their buildings on the hexagon borders of hexagons whose numbers were rolled
3. The Player plays/buys development cards, trades cards with other players/the bank/ports, and builds buildings
4. The Player ends their turn with a button click

### Alternate Flow
Step 1: The Player rolls a '7' with the dice
  1.  All Players discard half of their resource cards if they have 7 or more
  2.  The Player chooses a spot for the robber to move to, blocking production from the hexagon
  3.  The Player steals a random card from one of the players with settlements on the hexagon (if applicable)
  4.  The Player then takes the rest of their turn as norma

### Exceptions
Step 2: The Player doesn't get resources from a hexagon that has the robber on it

Step 3: The Player cannot play development cards bought that turn
  1.  The Player is unable to select/gets a prompt when they try to select the card


### Preconditions
It is the chosen player's turn

### Postconditions
none

### System or subsystem
Game board

### Other Stakeholders
Other Players

### Special Requirements:
none


