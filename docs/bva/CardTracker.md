# method: TotalPlayedDevCardsWithThree

## BVA Step 1
Input: All players in the game

Output: Number

## BVA Step 2
Input: Collection of Players

Output: Interval[0,25]

## BVA Step 3
Input: Collection of Players in a 3 player game
Output: Integer
Input: Collection of Players with some players having played development cards
Output: Integer
Input: Collection of Players with all players having played development cards
Output: Integer
Input: Collection of Players with total played development card count of 25
Output: Integer


## BVA Step 4
### Test value 1
Input: Array of 3 Players without any played Development Cards
Expected: 0
### Test value 2
Input: Array of 3 Players with 2 players having played 1 development card
Expected: 2
### Test value 3
Input: Array of 3 Players with all having played 1 development card
Expected: 3
### Test value 4
Input: Array of 3 players with max count development card played
Expected: 25

# method: TotalPlayedDevCardsWithFour

## BVA Step 1
Input: All players in the game

Output: Number

## BVA Step 2
Input: Collection of Players

Output: Interval[0,25]

## BVA Step 3
Input: Collection of Players in a 4 player game
Output: Integer
Input: Collection of 4 Players with some players having played development cards
Output: Integer
Input: Collection of 4 Players with all players having played development cards
Output: Integer
Input: Collection of 4 Players with total played development card count of 25
Output: Integer

## BVA Step 4
### Test value 1
Input: Array of 4 Players without any played Development Cards
Expected: 0
### Test value 2
Input: Array of 4 Players with 2 players having played 1 development card
Expected: 2
### Test value 3
Input: Array of 4 Players with all having played 1 development card
Expected: 4
### Test value 4
Input: Array of 4 players with max count development card played
Expected: 25


# method: TotalUnPlayedDevCardsWithThree

## BVA Step 1
Input: All players in the game

Output: Number

## BVA Step 2
Input: Collection of Players

Output: Interval[0,25]

## BVA Step 3
Input: Collection of Players in a 3 player game
Output: Integer
Input: Collection of Players with some players having played development cards
Output: Integer
Input: Collection of Players with all players having played development cards
Output: Integer
Input: Collection of Players with total unplayed development card count of 0
Output: Integer


## BVA Step 4
### Test value 1
Input: Array of 3 Players without any purhcase of Development Cards
Expected: 0
### Test value 2
Input: Array of 3 Players with 2 players having played 1 development card after purchasing 2 cards in total
Expected: 2
### Test value 3
Input: Array of 3 Players with all having played 1 development card after purhcasing 3 cards in total
Expected: 3
### Test value 4
Input: Array of 3 players with max count development card played in total
Expected: 0


# method: TotalUnPlayedDevCardsWithFour

## BVA Step 1
Input: All players in the game

Output: Number

## BVA Step 2
Input: Collection of Players

Output: Interval[0,25]

## BVA Step 3
Input: Collection of Players in a 4 player game
Output: Integer
Input: Collection of Players with some players having played development cards
Output: Integer
Input: Collection of Players with all players having played development cards
Output: Integer
Input: Collection of Players with total unplayed development card count of 0
Output: Integer


## BVA Step 4
### Test value 1
Input: Array of 4 Players without purchase of Development Cards
Expected: 0
### Test value 2
Input: Array of 4 Players with 2 players having played 1 development card after purchasing 2 cards in total
Expected: 2
### Test value 3
Input: Array of 4 Players with all having played 1 development card after purchasing 4 cards in total
Expected: 4
### Test value 4
Input: Array of 4 players with max count development card played in total
Expected: 0


# method: TotalUnPlayedDevCardsInGame

## BVA Step 1
Input: All players in the game and Unplayed Pile

Output: Number

## BVA Step 2
Input: Collection of Players and a Collection of DevCard

Output: Interval[0,25]

## BVA Step 3
Input: Collection of Players in a 3 player game and Unplayed Pile before any purhcase of development cards.
Output: Integer
Input: Collection of Players in a 3 player game and Unplayed Pile without playing any cards
Output: Integer
Input: Collection of Players with some players having played development cards and Unplayed Pile
Output: Integer
Input: Collection of Players with all players having played development cards and UnPlayed Pile
Output: Integer
Input: Collection of Players with total unplayed development card count of 0
Output: Integer


## BVA Step 4
### Test value 1
Input: Array of 3 Players, Unplayed pile
Expected: 25
### Test value 2
Input: Array of 3 players with purchased but not played development cards, Unplayed pile
Expected: 25
### Test value 3
Input: Array of 3 Players with 2 players having played 1 development card, Unplayed pile
Expected: 23
### Test value 4
Input: Array of 3 Players with all having played 2 development card, Unplayed pile
Expected: 19
### Test value 5
Input: Array of 3 Players with all having purchased 5 development card playing 2 in between, and purchasing 2 more, | Unplayed pile
Expected: 23
### Test value 6
Input: Array of 3 players with max count development card played in total, Unplayed pile
Expected: 0

# Method: TotalNumberResourceHeld

## BVA Step 1
Input: Array of Players
Output: Integer

## BVA Step 2
Input: Collection of Players
Output: Interval[0, 95]

## BVA Step 3
Input: All players with no resource
Output: Integer
Input: Some players with some resources
Output: Integer
Input: All players with some resources
Output: Integer
Input: All players with some resources after a few cosumption
Output: Integer

## BVA Step 4
### Test Value 1
Input: Collection of players with all yet to gain a resources
Expected: 0
### Test Value 2
Input: Collection of players with 2 players having 3 resources each
Expected: 6
### Test value 3
Input: Collection of players with 4 players having 3 resources each
Expected: 12
### Test value 4
Input: Collection of players starting with 3 Resources and 2 players spending 2 each.
Expected: 8

# Method: TradeResourcesWithinPlayers

## BVA Step 1
Input: Player, other Player, Resource pile, Resource pile
Output: true or false

## BVA Step 2
Input: 2 Player and 2 Collection of Resources
Output: Boolean (0,1)

## BVA Step 3
Input: 2 players, trading 1 : 1
Output: true
Input: 2 players, trading 2 : 2
Output: true
Input: 2 players, trading 3 : 3
Output: true

## BVA Step 4
### Test Value 1
Input: 2 players trading one wood and one ore
Expected: true
### Test Value 2
Input: 2 players trading one sheep and one brick
Expected: true
### Test value 3
Input: 2 players trading two wheat and two ores
Expected: true
### Test value 4
Input: 2 players trading three sheeps and (2 bricks, 1 ORE);
Expected: true
### Test value 5
Input: 2 players trading three wheat and (2 WOOD, 1 ORE);
Expected: true


# Method: TradeResourcesWithBank

## BVA Step 1
Input: Player, Resource pile, Number of resources to trade, Resource pile, A list of PortTypes a player owns
Output: An updated player collection

## BVA Step 2
Input: A Player and 2 Collection of Resources, an integer, Collection of PortTypes
Output: An updated player collection of resources

## BVA Step 3
Input: 
* (for all) a player
* 4 SHEEP, 1 WHEAT, No ports
* 4 WOOD, 1 ORE, No ports
* 4 SHEEP, 1 ORE, No ports
* 4 WHEAT, 1 BRICK, No ports
* 4 ORE, 1 SHEEP, No ports
* 4 BRICK, 1 WOOD, No ports
* 2 WHEAT, 1 Wood, No ports
* 3 ORE, 1 SHEEP, 3to1 port
* 3 WOOD, 1 BRICK, 3to1 port
* 2 ORE, 1 WOOD, Ore port
* 2 WOOD, 1 ORE, Wood port
* 2 BRICK, 1 WOOD, Brick port
* 2 WHEAT, 1 BRICK, Wheat port
* 2 SHEEP, 1 WHEAT, Sheep port
* 2 SHEEP, 1 WHEAT, Wheat port, Sheep port

Output:
    An updated game state where the player has the resources
    An IllegalArgumentException


## BVA Step 4
### Test Value 1
Input: player and bank trading one wood and one ore, no ports
Expected: valid player resource state
### Test Value 2
Input: player and bank trading one sheep and one brick, no ports
Expected: valid player resource state
### Test value 3
Input: player and bank trading two wheat and two ores, no ports
Expected: valid player resource state
### Test value 4
Input: player and bank three sheeps and (2 bricks, 1 ORE), no ports
Expected: valid player resource state
### Test value 5
Input: player and bank three wheat and (2 WOOD, 1 ORE), no ports
Expected: valid player resource state
### Test value 6
Input: player and bank 3 wheat and 1 ore, 3to1 port;
Expected: valid player resource state
### Test value 7
Input: player and bank 3 wood and 1 brick, 3to1 port;
Expected: valid player resource state
### Test value 8
Input: player and bank 2 ore and 1 wood, Ore port;
Expected: valid player resource state
### Test value 9
Input: player and bank 2 wood and 1 ore, wood port;
Expected: valid player resource state
### Test value 10
Input: player and bank 2 wheat and 1 brick, wheat port;
Expected: valid player resource state
### Test value 11
Input: player and bank 2 sheep and 1 wheat, sheep port;
Expected: valid player resource state
### Test value 12
Input: player and bank 2 wood and 1 brick, Wheat port, Sheep port;
Expected: valid player resource state
