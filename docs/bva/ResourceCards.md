# method: GetNumberOfResources();

## BVA Step 1
Input: CountCollection<Resource>
Output: Integer

## BVA Step 2
Input: Collection
Output: Interval[0, 15];

## BVA Step 3
Input : CountCollection with no resources (All 0 on Hashmap)
Output : 0
Input: CountCollection with some resource (2 wood only)
Output : 2
Input: CountCollection with some resource (3 Sheep only)
Output : 1
Input: CountCollection with some resources (2 wood, 1 ore)
Output : 3

## BVA Step 4
### Test value 1
CountCollection containing (WOOD, 2)
### Test value 2
CountCollection containing nothing (Empty)
### Test value 3
CountCollection containing (SHEEP, 3)
### Test Value 4
CountCollection containing (WOOD, 1)(ORE, 1)

# method: RemoveResource();

## BVA Step 1
Input: CountCollection<Resource>, Resource Type, Number to Remove
Output: None

## BVA Step 2
Input: Collection, Enum, Interval[0, 15]
Output: NONE

## BVA Step 3
Input: CountCollection with Zero Resources , Resource Type, Number to Remove
Output: Exception
Input: CountCollection with few Resources , Resource Type, Number to Remove
Output: None
Input: CountCollection with Many Resources, Resource Type, Number to Remove
Output: None
Input : CountCollection with no specific Resource to Remove
Output: None
Input : CountCollection with few Resources, Resource Type, Negative Number;
Output: Exception

## BVA Step 4
### Test value 1
CountCollection.cardCollection = Empty , Resource.WOOD, 2
### Test value 2
CountCollection.cardCollection = (WOOD, 2)(ORE, 3),(Resource.ORE) , 1
### Test value 3
CountCollection.cardCollection = (WOOD, 6)(ORE, 7)(SHEEP 3),(Resource.ORE), 3
### Test value 4
CountCollection.cardCollection = (WOOD, 2)(ORE, 4)(SHEEP 3),(Resource.BRICK), 4
### Test value 5
CountCollection.cardCollection = (WOOD, 2)(ORE, 3),(Resource.WOOD), -2

# method: HasResource(Resource);

## BVA Step 1
Input: CountCollection<Resource>, Resource
Output: True or False

## BVA Step 2
Input: Collection, enum
Output: Boolean 

## BVA Step 3
Input : CountCollection with no resources (All 0 on Hashmap), resource
Output : False
Input: CountCollection, with few resources, resource
Output : True
Input: CountCollection with many resource, resource
Output : True

## BVA Step 4
### Test value 1
CountCollection containing nothing (Empty)
### Test value 2
CountCollection containing (WOOD, 2)
### Test value 3
CountCollection containing (SHEEP, 4)(BRICK, 6)(ORE, 3)


# method: AddResource(Resource, amount);

## BVA Step 1
Input: CountCollection<Resource>, Resource, number
Output: none

## BVA Step 2
Input: Collection, enum , Interval[0, 15]
Output: void

## BVA Step 3
Input: CountCollection, Resource, 0
Output: error
Input: CountCollection, Resource, negative number
Output: error
Input: CountCollection, Resource, positive number
Output: none

## BVA Step 4
### Test value 1
CountCollection, (WHEAT, 2)(ORE, 2)(WOOD, 2), 0
### Test value 2
CountCollection (WHEAT, 2)(ORE, 2)(WOOD, 2), -2
### Test value 3
CountCollection (WHEAT, 2)(ORE, 2)(WOOD, 2), -10
### Test Value 4
CountCollection (WHEAT, 2)(ORE, 2)(WOOD, 2), MIN_INT
### Test Value 5
CountCollection (WHEAT, 2)(ORE, 2)(WOOD, 2), 2


# method: AddDevCard;

## BVA Step 1
Input: CountCollection<Resource>, DevCard
Output: none

## BVA Step 2
Input: Collection, enum
Output: void

## BVA Step 3
Input: A Single DevCard
Output: None
Input: A Single DevCard when full capacity
Output: Error
Input: A Single DevCard when not empty
Output: None

## BVA Step 4
### Test value 1
CountCollection (Empty), Great Hall
### Test value 2
CountCollection (Empty), Knight
### Test value 3
CountCollection (Knight, 1)(GreatHall, 1)(Chapel, 1), University
### Test value 4
CountCollection (Knight, 14)(Year of Plenty, 1)(Chapel, 1), Knight
### Test value 5
CountCollection (Knight, 2)(Road_Building, 1)(Library, 1), Library
### Test value 6
CountCollection (Library, 1)(University, 1), University


# method: PlayDevCard;

## BVA Step 1
Input: CountCollection<Resource>, DevCard
Output: none

## BVA Step 2
Input: Collection, enum
Output: void

## BVA Step 3
Input: Playing a DevCard with Empty DevCard pile
Output: error
Input: Playing a DevCard with no DevCard
Output: error
Input: Playing a DevCard with a DevCard
Output: none

## BVA Step 4
### Test value 1
CountCollection (Empty), Great Hall
### Test value 2
CountCollection (Empty), Knight
### Test value 3
CountCollection (Knight, 1)(Year of Plenty, 1)(Chapel, 1), University
### Test value 4
CountCollection (Knight, 1)(Year of Plenty, 1)(Chapel, 1), Knight
### Test value 5
CountCollection (Knight, 1)(Year of Plenty, 1)(Chapel, 1), Chapel


# method: GetNumberOfPlayedDevCards(DevCard);

## BVA Step 1
Input: CountCollection<Resource>, DevCard
Output: integer

## BVA Step 2
Input: Collection, enum
Output: Interval[0, 25]

## BVA Step 3
Input: Development Card
Output: #of Played Development Cards

## BVA Step 4
### Test value 1
CountCollection (Knight, 1)(Year of Plenty, 1)(Chapel, 1), University
### Test value 2
CountCollection (Knight, 1)(Year of Plenty, 1)(Chapel, 1), Knight
### Test value 3
CountCollection (Knight, 1)(Year of Plenty, 1)(Chapel, 1), Chapel



