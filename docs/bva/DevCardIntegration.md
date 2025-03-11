# Method: Purchase Development Card

## BVA Step 1
Input: 
Player number, 
A Development Card, 
Player's Dev Card pile, 
Dev Card Bank, 
Player's Resource pile, 
Resource Bank

Output: 
Player's development Card pile, 
Developoment Card Bank

## BVA Step 2
Input: Interval[1, 4], enum
Output: Collecton of Enum, Collection of Enum

## BVA Step 3
Input: Player with no resource, a Development Card
Output: Error
Input: Player with sufficient resource, a Development Card
Output: Player's development card pile, Development Card Bank
Input: Player with sufficient resource, a Development Card that does not exist.
Output: Error

## BVA Step 4
### Test Value 1
Input: Player 1 with empty resource pile, Knight Card
Expected: Illegal Argument Exception
### Test Value 2
Input: Player 1 with empty resource pile, Progress Card (Year of Plenty)
Expected: Illegal Argument Exception
### Test value 3
Input: Player 1 with 1 sheep, 1 grain, 1 ore. Knight Card
Expected: Player 1's DevCard Pile, Dev Card Bank
### Test value 4
Input: Player 1 with 1 sheep, 1 grain, 1 ore. Progress Card (Road Building)
Expected: Player 1's DevCard Pile, Dev Card Bank
### Test value 5
Input: Player 1 with 1 sheep, 1 grain, 1 ore. Progress Card (Monopoly), with no remaining progress card on bank
Expected: Illegal Argument Exception
### Test value 6
Input: Player 1 with 1 sheep, 1 grain, 1 ore. Progress Card (Monopoly), with no remaining progress card on bank
Expected: Illegal Argument Exception

# Method: Consume Development Card

## BVA Step 1
Input: 
Player number
Developent Card
Player's Development Card pile
Consumed Development Card bank

Output: 
Player's development Card pile, 
Developoment Card Bank
Consumed Development Card Bank

## BVA Step 2
Input: Interval[1, 4], enum, Collection of Enum, Collection of Enum. 
Output: Collecton of Enum, Collection of Enum, Collection of enum

## BVA Step 3
Input: Player with Empty Dev Card pile, A development Card
Output: Error
Input: Player with sufficient development Cards, a Development Card
Output: Player's development card pile, Development Card Bank
Input: Player with sufficient development Cards, a Development Card that player does not have
Output: Error

## BVA Step 4
### Test Value 1
Input: Player 1 with empty Dev Card pile, Knight Card
Expected: Illegal Argument Exception
### Test Value 2
Input: Player 1 with empty Dev Card pile, Progress Card
Expected: Illegal Argument Exception
### Test value 3
Input: Player 1 with a Knight Card and a Progress Card (Year of Plenty),  Knight Card
Expected: Player 1's DevCard Pile, Consumed Dev Card Bank
### Test value 4
Input: Player 1 with a Knight Card and a Progress Card (Road Building), Progress Card
Expected: Player 1's DevCard Pile, Consumed Dev Card Bank
### Test value 5
Input: Player 1 with a Victory Card and a Progress Card (Monopoly), Victory Card
Expected: Player 1's DevCard Pile, Consumed Dev Card Bank
### Test value 6
Input: Player 1 with a Knight Card and a Progress Card (Monopoly), Victory Card 
Expected: Illegal Argument Exception
### Test value 7
Input: Player 1 with Victory Card and a Progress Card (Year of Plenty), Knight Card
Expected: Illegal Argument Exception


# Method: PlayVictoryPointCard

## BVA Step 1
Input: 
Player number
VictoryPoint Card

Output: 
Player with increased victory points

## BVA Step 2
Input: Interval[1, 4]
Output: Interval[0, 10]

## BVA Step 3
Input: Player with sufficient victory point cards
Output: Increased Victory Point

## BVA Step 4
### Test Value 1
Input: Player 1 with a Victory Point Card
Expected: Increased Victory Point by 1

## Method PlayMonopolyCard

## BVA Step 1
Input: 
Player number, Resource

Output: 
Player's Resource pile

## BVA Step 2
Input: Interval[1, 4], Enum
Output: Collection

## BVA Step 3
Input: Player with a Monopoly card, and Resource to announce
Output: When you play this card, announce one type of resource. All other players must give you all of their resources of that type.

## BVA Step 4
### Test Value 1
Input: 
Player 1 with a Monopoly Card, 
Announcing Wood, 
All other players having 2 woods

Expected: Player 1 with 6 more woods

### Test Value 2
Input: 
Player 1 with a Monopoly Card.
Announcing Wood, 
player 2 having 3, 
player 3 having 2, 
player 4 having 1

Expected: Player 1 with 6 more woods

### Test Value 3
Input: 
Player 1 with a Monopoly Card.
Announcing Brick, 
player 2 having 2, 
player 3 having 2, 
player 4 having 2,

Expected: Player 1 with 6 more bricks

### Test Value 4
Input: 
Player 1 with a Monopoly Card.
Announcing Brick, 
player 2 having 3, 
player 3 having 2, 
player 4 having 0,

Expected: Player 1 with 5 more bricks

### Test Value 5
Input: 
Player 1 with a Monopoly Card.
Announcing Wheat, 
player 2 having 0, 
player 3 having 2, 
player 4 having 0,

Expected: Player 1 with 2 more wheats


# Method: playYearOfPlentyCard

## BVA Step 1
Input: player number, resource, resource
Output: player's resource pile

## BVA Step 2
Input: Interval[1, 4], enum, enum
Output: Collection

## BVA Step 3
Input: Player 1 playing Year of Plenty Development Card, taking the 2 of same resource from bank
Output: player's resource pile with 2 more of chosen resource

Input: Player 1 playing Year of Plenty Development Card, taking the 2 of different resource from bank
Output: player's resource pile with 1 more of each chosen resource

## BVA Step 4
Input: Player 1 playing Year of Plenty Development Card, taking wood and wood
Expected: Player's resource pile with 2 more wood

Input: Player 1 playing Year of Plenty Development Card, taking ore and ore
Expected: Player's resource pile with 2 more ore

Input: Player 1 playing Year of Plenty Development Card, taking wheat and brick
Expected: Player's resource pile with 1 more wheat and 1 more brick

Input: Player 1 playing Year of Plenty Development Card, taking sheep and ore
Expected: Player's resource pile with 1 more sheep and 1 more ore


# Method: playRoadBuildingCard

## BVA Step 1
Input: player number, BorderLocation, BorderLocation
Output: player's resource pile

## BVA Step 2
Input: Interval[1, 4], 
Output: Collection

## BVA Step 3
Input: Player 1 playing Year of Plenty Development Card, taking the 2 of same resource from bank
Output: player's resource pile with 2 more of chosen resource

Input: Player 1 playing Year of Plenty Development Card, taking the 2 of different resource from bank
Output: player's resource pile with 1 more of each chosen resource

## BVA Step 4
Input: Player 1 playing Year of Plenty Development Card, taking wood and wood
Expected: Player's resource pile with 2 more wood

Input: Player 1 playing Year of Plenty Development Card, taking ore and ore
Expected: Player's resource pile with 2 more ore

Input: Player 1 playing Year of Plenty Development Card, taking wheat and brick
Expected: Player's resource pile with 1 more wheat and 1 more brick

Input: Player 1 playing Year of Plenty Development Card, taking sheep and ore
Expected: Player's resource pile with 1 more sheep and 1 more ore


