# method: getBorders(): List<BorderLocation>

## BVA Step 1
Input: a hex location

Output: a collection of 6 border locations

## BVA Step 2
Input: pair of intervals - each from \[0, 4] 

Output: a collection of pairs of intervals - each from \[-1, 15]

## BVA Step 3
Input:
* The pair (0, 0) - just within the interval
* The pair (-1, -1) - just out of the interval
* The pair (4, 4) - just within the interval
* The pair (5, 5) - just out of the interval
* The pair (MAX, MAX) - well out of the interval
* The pair (MIN, MIN) - well out of the interval
* The pair (3, 2) - to test a generic case where the row and column aren't equal (also since odd rows are treated differently from even rows)

Output:
* The set {(0,0),(0,1),(0,2),(1,-1),(1,1),(0,3)}
* The set {(4,12),(4,13),(4,14),(5,11),(5,13),(4,15)}
* The set {(3,7),(3,8),(3,9),(4,8),(4,10),(3,10)}
* An IllegalStateException

## BVA Step 4
### Test value 1
Input: Hex (0,0)

Expected Output: {(0,0),(0,1),(0,2),(1,-1),(1,1),(0,3)}

### Test value 2
Input: Hex (-1,-1)

Expected Output: IllegalStateException

### Test value 3
Input: Hex (4,4)

Expected Output: {(4,12),(4,13),(4,14),(5,11),(5,13),(4,15)}

### Test value 4
Input: Hex (5,5)

Expected Output: IllegalStateException

### Test value 5
Input: Hex (MAX,MAX)

Expected Output: IllegalStateException

### Test value 6
Input: Hex (MIN,MIN)

Expected Output: IllegalStateException

### Test value 7
Input: Hex (3,2)

Expected Output: {(3,7),(3,8),(3,9),(4,8),(4,10),(3,10)}

# method: getVertices(): List<VertexLocation>

## BVA Step 1
Input: a hex location

Output: a collection of 6 vertex locations

## BVA Step 2
Input: pair of intervals - each from \[0, 4]

Output: a collection of pairs of intervals - each from \[0, 10]

## BVA Step 3
Input:
* The pair (0, 0) - just within the interval
* The pair (-1, -1) - just out of the interval
* The pair (4, 4) - just within the interval
* The pair (5, 5) - just out of the interval
* The pair (MAX, MAX) - well out of the interval
* The pair (MIN, MIN) - well out of the interval
* The pair (3, 2) - to test a generic case where the row and column aren't equal (also since odd rows are treated differently from even rows)

Output:
* The set {(0,0),(0,1),(0,2),(1,0),(1,1),(1,2)}
* The set {(4,8),(4,9),(4,10),(5,8),(5,9),(5,10)}
* The set {(3,5),(3,6),(3,7),(4,5),(4,6),(4,7)}
* An IllegalStateException

## BVA Step 4
### Test value 1
Input: Hex (0,0)

Expected Output: {(0,0),(0,1),(0,2),(1,0),(1,1),(1,2)}

### Test value 2
Input: Hex (-1,-1)

Expected Output: IllegalStateException

### Test value 3
Input: Hex (4,4)

Expected Output: {(4,8),(4,9),(4,10),(5,8),(5,9),(5,10)}

### Test value 4
Input: Hex (5,5)

Expected Output: IllegalStateException

### Test value 5
Input: Hex (MAX,MAX)

Expected Output: IllegalStateException

### Test value 6
Input: Hex (MIN,MIN)

Expected Output: IllegalStateException

### Test value 7
Input: Hex (3,2)

Expected Output: {(3,5),(3,6),(3,7),(4,5),(4,6),(4,7)}

# method: equals(other: Object): boolean
## BVA Step 1
Input: two hex locations

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

# method: isValid(): boolean
## BVA Step 1
Input: hex location

Output: boolean

## BVA Step 2
Input: two intervals \[MIN, MAX]

Output: boolean

## BVA Step 3
Inputs:
* Normal point (0, 0) 
* Small row (-1, 0)
* Small column (0, -1)
* Large row (5, 0)
* Large column (0, 5)

Outputs: True, False

## BVA Step 4
### Test Case 1
Input: (0,0)

Expected Output: True

### Test Case 2
Input: (-1,0)

Expected Output: False

### Test Case 3
Input: (0,-1)

Expected Output: False

### Test Case 4
Input: (5,0)

Expected Output: False

### Test Case 5
Input: (0,5)

Expected Output: False

# method: hashCode(): int
## BVA Step 1
Input: hex location

Output: number

## BVA Step 2
Input: two intervals \[MIN, MAX]

Output: number

## BVA Step 3
Input: Any hex (3, 3)

## BVA Step 4
Input: (3,3)

Expected Output: 38504087
