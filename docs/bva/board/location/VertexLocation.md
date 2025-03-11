# method: getHexes(): List<HexLocation>

## BVA Step 1
Input: a vertex location

Output: a collection of up to 3 hex locations

## BVA Step 2
Input: pair of intervals - row from \[0,5] and column from \[0,10]

Output: a collection of pairs of intervals - each from \[0, 4]

## BVA Step 3
Input:
* The pair (0, 0) - just within the interval (with 1 output)
* The pair (-1, -1) - just out of the interval
* The pair (0, -1)
* The pair (-1, 0)
* The pair (5, 10) - just within the interval (with 1 output)
* The pair (6, 11) - just out of the interval
* The pair (6, 10)
* The pair (5, 11)
* The pair (MAX, MAX) - well out of the interval
* The pair (MIN, MIN) - well out of the interval
* The pair (3, 3) - to test with odd row + odd column
* The pair (5, 4) - to test with 2 output hexes
* The pair (3, 4) - to test with odd row + even column
* The pair (2, 4) - to test with even row + even column
* The pair (2, 3) - to test with even row + odd column

Output:
* The set {(0,0)}
* The set {(4,4)}
* The set {(2,0),(3,0),(2,1)}
* The set {(4,1),(4,2)}
* The set {(2,1),(2,2),(3,1)}
* The set {(1,1),(2,1),(2,2)}
* The set {(1,0),(1,1),(2,1)}
* An IllegalStateException

## BVA Step 4
### Test value 1
Input: Vertex (0,0)

Expected Output: {(0,0)}

### Test value 2
Input: Vertex (-1,-1)

Expected Output: IllegalStateException

### Test value 3
Input: Vertex (5,10)

Expected Output: {(4,4)}

### Test value 4
Input: Vertex (6,11)

Expected Output: IllegalStateException

### Test value 5
Input: Vertex (MAX,MAX)

Expected Output: IllegalStateException

### Test value 6
Input: Vertex (MIN,MIN)

Expected Output: IllegalStateException

### Test value 7
Input: Vertex (3,3)

Expected Output: {(2,1),(3,0),(3,1)}

### Test value 8
Input: Vertex (5,4)

Expected Output: {(4,1),(4,2)}

### Test value 9
Input: Vertex (2,4)

Expected Output: {(1,1),(2,1),(2,2)}

### Test value 10
Input: Vertex (2,3)

Expected Output: {(1,0),(1,1),(2,1)}

### Test value 11
Input: Vertex (3,4)

Expected Output: {(2,1),(2,2),(3,1)}

### Test value 12
Input: Vertex (0,-1)

Expected Output: IllegalStateException

### Test value 13
Input: Vertex (-1,0)

Expected Output: IllegalStateException

### Test value 14
Input: Vertex (6,10)

Expected Output: IllegalStateException

### Test value 15
Input: Vertex (5,11)

Expected Output: IllegalStateException

# method: getBorders(): List<BorderLocation>

## BVA Step 1
Input: a vertex location

Output: a collection of 2 or 3 border locations

## BVA Step 2
Input: pair of intervals - row from \[0,5] and column from \[0,10]

Output: a collection of pairs of intervals - each from \[-1, 15]

## BVA Step 3
Input:
* The pair (0, 0) - just within the interval (with 2 outputs)
* The pair (-1, -1) - just out of the interval
* The pair (5, 10) - just within the interval (with 2 outputs)
* The pair (6, 11) - just out of the interval
* The pair (MAX, MAX) - well out of the interval
* The pair (MIN, MIN) - well out of the interval
* The pair (3, 3) - to test a generic case in the middle (also since odd rows are treated differently from even rows) - with 3 outputs
* The pair (2, 3) - to test even row + odd column
* The pair (2, 4) - to test an even row + even column

Output:
* The set {(0,0),(0,1)}
* The set {(4,15),(5,13),(5,14)}
* The set {(3,3),(3,4),(3,5)}
* The set {(1,3),(2,4),(2,5)}
* The set {(2,5),(2,6),(2,7)}
* An IllegalStateException

## BVA Step 4
### Test value 1
Input: Vertex (0,0)

Expected Output: {(0,0),(0,1)}

### Test value 2
Input: Vertex (-1,-1)

Expected Output: IllegalStateException

### Test value 3
Input: Vertex (5,10)

Expected Output: {(4,15),(5,13),(5,14)}

### Test value 4
Input: Vertex (6,11)

Expected Output: IllegalStateException

### Test value 5
Input: Vertex (MAX,MAX)

Expected Output: IllegalStateException

### Test value 6
Input: Vertex (MIN,MIN)

Expected Output: IllegalStateException

### Test value 7
Input: Vertex (3,3)

Expected Output: {(3,2),(3,3),(3,4)}

### Test value 8
Input: Vertex (2,3)

Expected Output: {(1,3),(2,4),(2,5)}

### Test value 9
Input: Vertex (2,4)

Expected Output: {(2,5),(2,6),(2,7)}

# method: equals(other: Object): boolean
## BVA Step 1
Input: two vertex locations

Output: boolean

## BVA Step 2
Input: 2 pairs of intervals

Output: boolean

## BVA Step 3
* Instance where both are equal (1, 2) and (1, 2)
* Instance where row not equal (1, 2) and (2, 2)
* Instance where column not equal (1, 2) and (1, 3)

## BVA Step 4
### Test Case 1
Input: (1, 2) and (1, 2)

Expected Output: True

### Test Case 2
Input: (1, 2) and (2, 2)

Expected Output: False

### Test Case 3
Input: (1, 2) and (1, 3)

Expected Output: False

# method: hashCode(): int
## BVA Step 1
Input: vertex location

Output: number

## BVA Step 2
Input: two intervals \[MIN, MAX]

Output: number

## BVA Step 3
Input: Any hex (3, 3)

## BVA Step 4
Input: (3,3)

Expected Output: 38504087