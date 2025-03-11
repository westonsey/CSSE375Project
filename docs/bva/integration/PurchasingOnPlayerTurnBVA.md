# Integration Feature: Purchasing and Place Settlement 
# method: removeResource, placeSettlement

### BVA Step 1:

Input: Player (collection of resources), the board, vertex location to place settlement 

Output: settlement is placed on board, player gains 1 victory point, player loses the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep

### BVA Step 2:

Input: player (collection of players resources), a collection of resources/numbers and locations of settlements (the board), a pair of intervals (vertex location)

Output: integer value (victory points), collection of a player's settlements updated, collection of player's resources updated, the datatype for the board is not important to the scope of the BVA since the Board State is dependent on the input board state
### BVA Step 3:

Input: 
(testing has already been written for the valid palcement of settlements so these tests will assume the settlement placement is valid and will only test the removal of resources on purchase
* player 1, collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep, default board, vertex location of (1, 4), player has 2 victory points
* player 2, collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep, board with settlement at vertex location (1,4), vertex location of (1, 4) to palce new settlement, player has 2 victory points
* player 3, collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 0 sheep, default board, vertex location of (1, 4), player has 2 victory points


Output: 
* Player has 3 victory points, building is added to board at (1,4), player loses the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep (sucessful placement)
* Player has 2 victory points, board remains the same, player keeps collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep (invalid placement tests resources are not taken)
* Player has 2 victory points, board remains the same, player keeps collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 0 sheep (invalid number of resources)

### BVA Step 4:

#### Test Case 1:

Input: player 1, collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep, default board, vertex location of (1, 4), player has 2 victory points

Output: Player 1 has 3 victory points, building is added to board at (1,4), player loses the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep (sucessful placement)

#### Test Case 2:

Input: player 2, collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep, board with settlement at vertex location (1,4), vertex location of (1, 4) to palce new settlement, player has 2 victory points

Output: Player 2 has 2 victory points, board remains the same, player keeps collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 1 sheep (invalid placement tests resources are not taken)

#### Test Case 3:

Input: player 3, collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 0 sheep, default board, vertex location of (1, 4), player has 2 victory points

Output: Player 3 has 2 victory points, board remains the same, player keeps collection of resources with the following resources: 1 wood, 1 wheat, 1 brick, 0 sheep (invalid number of resources)



# Integration Feature: Purchasing and Place Road 
# method: removeResource, placeSettlement

### BVA Step 1:

Input: Player (collection of resources), the board, border location to place road 

Output: road is placed on board, player loses the following resources: 1 wood, 1 brick

### BVA Step 2:

Input: player (collection of players resources), a collection of resources/numbers and locations of settlements (the board), a pair of intervals (border location)

Output: collection of a player's settlements updated, collection of player's resources updated, the datatype for the board is not important to the scope of the BVA since the Board State is dependent on the input board state
### BVA Step 3:

Input: 
(testing has already been written for the valid palcement of roads so these tests will assume the road placement is valid and will only test the removal of resources on purchase
* player 1, collection of resources with the following resources: 1 wood, 1 brick, default board, border location of (4, 15)
* player 2, collection of resources with the following resources: 1 wood, 1 brick, board with road at border location (4,15), border location of (4, 15) to place new settlement
* player 3, collection of resources with the following resources: 1 wood, 0 brick, default board, border location of (4, 15)


Output: 
* road is added to board at (4,15), player loses the following resources: 1 wood, 1 brick, (sucessful placement)
* board remains the same, player keeps collection of resources with the following resources: 1 wood, 1 brick, (invalid placement tests resources are not taken)
* board remains the same, player keeps collection of resources with the following resources: 1 wood, 0 brick, (invalid number of resources)

### BVA Step 4:

#### Test Case 1:

Input: player 1, collection of resources with the following resources: 1 wood, 1 brick, default board, border location of (4, 15)

Output: road is added to board at (4,15), player 1 loses the following resources: 1 wood, 1 brick, (sucessful placement)
#### Test Case 2:

Input: player 2, collection of resources with the following resources: 1 wood, 1 brick, board with road at border location (4,15), border location of (4, 15) to place new settlement

Output: board remains the same, player 2 keeps collection of resources with the following resources: 1 wood, 1 brick, (invalid placement tests resources are not taken)

#### Test Case 3:

Input: player 3, collection of resources with the following resources: 1 wood, 0 brick, default board, border location of (4, 15)
Output: board remains the same, player 3 keeps collection of resources with the following resources: 1 wood, 0 brick, (invalid number of resources)




# Integration Feature: Purchasing and Receiving Dev Card
# method: removeResource, addDevCard, getRandomDevCardType

### BVA Step 1:

Input: Player (collection of resources)

Output: Players collection of unplayed dev cards increases, Players resource collection is upadated

### BVA Step 2:

Input: player (collection of players resources)

Output: collection of player's resources, the players collection of unplayed dev cards
### BVA Step 3:

Input: 
(testing has already been written for the valid palcement of roads so these tests will assume the road placement is valid and will only test the removal of resources on purchase
* player 1, collection of resources with the following resources: 1 wheat, 1 sheep, 1 ore
* player 2, collection of resources with the following resources: 1 wheat, 0 sheep, 1 ore


Output: 
* development card is added to players collection of unplayed dev cards, player loses the following resources: 1 wheat, 1 sheep, 1 ore, (sucessful placement)
* player loses no resources and recources array contains the following resources: 1 wheat, 0 sheep, 1 ore, player's number of development cards in the unplayed development card collection remains the same (invalid number of resources)

### BVA Step 4:

#### Test Case 1:

Input: player 1, collection of resources with the following resources: 1 wheat, 1 sheep, 1 ore

Output: development card is added to player 1 collection of unplayed dev cards, player 1 loses the following resources: 1 wheat, 1 sheep, 1 ore, (sucessful placement)
#### Test Case 2:

Input: player 2, collection of resources with the following resources: 1 wheat, 0 sheep, 1 ore

Output: player 2 loses no resources and recources array contains the following resources: 1 wheat, 0 sheep, 1 ore, player's number of development cards in the unplayed development card collection remains the same (invalid number of resources)






# Integration Feature: Purchasing City and Upgrading Settlement
# method: removeResource, upgradeSettlement

### BVA Step 1:

Input: Player (collection of resources), Settlement belonging to player, board

Output: Players collection of resources is updated, Settlement is removed from the board, city is added at location of settlement, player gains 1 victory point

### BVA Step 2:

Input: player (collection of players resources), a collection of resources/numbers and locations of settlements (the board), a settlement (the datatype settlement is not important to the scope of BVA since it is not under test)

Output: collection of a player's buildings updated, collection of player's resources updated, the datatype for the board is not important to the scope of the BVA since the Board State is dependent on the input board state, integer victory point
### BVA Step 3:

Input: 
(testing has already been written for the valid palcement of settlements so these tests will assume the settlement input is valid and will only test the removal of resources on purchase and the update of victory points
* player 1, collection of resources with the following resources: 3 ore, 2 wheat, default board, settlement at vertex location of (1, 4), player 1 has 2 victory points
* player 2, collection of resources with the following resources: 3 ore, 1 wheat, default board, settlement at vertex location of (1, 4), player 1 has 2 victory points
  


Output: 
* city is added to board at (1,4), settlement at vertex location (1,4) is removed from the board, player loses the following resources: 3 ore, 2 wheat, (sucessful placement), player has 3 victory points
* board remains the same, player keeps collection of resources with the following resources: 3 ore, 1 wheat, (invalid number of resources)

### BVA Step 4:

#### Test Case 1:

Input: player 1, collection of resources with the following resources: 3 ore, 2 wheat, default board, settlement at vertex location of (1, 4), player 1 has 2 victory points

Output: city is added to board at (1,4), settlement at vertex location (1,4) is removed from the board, player loses the following resources: 3 ore, 2 wheat, (sucessful placement), player has 3 victory points
#### Test Case 2:

Input: player 2, collection of resources with the following resources: 3 ore, 1 wheat, default board, settlement at vertex location of (1, 4), player 1 has 2 victory points

Output: board remains the same, player keeps collection of resources with the following resources: 3 ore, 1 wheat, (invalid number of resources)

