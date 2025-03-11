# method: getVertices(): List<VertexLocation>

## BVA Step 1
Input: a border location

Output: a collection of 2 vertex locations

## BVA Step 2
Input: pair of intervals - row from \[0,4] and column from \[-1,15] (only -1 if row is odd)

Output: a collection of pairs of intervals - each from \[0, 10]

## BVA Step 3
Input:
* The pair (0, 0) - just within the interval
* The pair (1,-1) - also just within the interval
* The pair (-1, -1) - just out of the interval
* The pair (4, 15) - just within the interval
* The pair (5, 16) - just out of the interval
* The pair (MAX, MAX) - well out of the interval
* The pair (MIN, MIN) - well out of the interval
* The pair (1,12) - to test a vertical border 
* The pair (1,11) - to test a "diagonal-down" border
* The pair (1,10) - to test a "diagonal-up" border
* The pair (5,3) - to test an invalid vertical border at the bottom

Output:
* The set {(0,0),(1,0)}
* The set {(1,0),(1,1)}
* The set {(4,10),(5,10)}
* The set {(1,9),(2,9)}
* The set {(1,8),(1,9)}
* The set {(1,7),(1,8)}
* An IllegalStateException

## BVA Step 4
### Test value 1
Input: Border (0,0)

Expected Output: {(0,0),(1,0)}

### Test value 2
Input: Border (1,-1)

Expected Output: {(1,0),(1,1)}

### Test value 3
Input: Border (-1,-1)

Expected Output: IllegalStateException

### Test value 4
Input: Border (4,15)

Expected Output: {(4,10),(5,10)}

### Test value 5
Input: Border (5,16)

Expected Output: IllegalStateException

### Test value 6
Input: Border (MAX,MAX)

Expected Output: IllegalStateException

### Test value 7
Input: Border (MIN,MIN)

Expected Output: IllegalStateException

### Test value 8
Input: Border (1,12)

Expected Output: {(1,9),(2,9)}

### Test value 9
Input: Border (1,11)

Expected Output: {(1,8),(1,9)}

### Test value 10
Input: Border (1,10)

Expected Output: {(1,7),(1,8)}

### Test value 11
Input: Border (5,3)

Expected Output: IllegalStateException

# method: getBorders(): List<BorderLocation>

## BVA Step 1
Input: a border location

Output: a collection of up to 4 border locations

## BVA Step 2
Input: pair of intervals - row from \[0,4] and column from \[-1,15] (only -1 if row is odd)

Output: a collection of pairs of intervals - each with row from \[0,4] and column from \[-1,15] (only -1 if row is odd)

## BVA Step 3
Input:
* The pair (0, 0) - just within the interval
* The pair (1,-1) - also just within the interval
* The pair (-1, -1) - just out of the interval
* The pair (4, 15) - just within the interval
* The pair (5, 16) - just out of the interval
* The pair (MAX, MAX) - well out of the interval
* The pair (MIN, MIN) - well out of the interval
* The pair (1,12) - to test a vertical border
* The pair (1,11) - to test a "diagonal-down" border
* The pair (1,10) - to test a "diagonal-up" border

Output:
* The set of pairs {(0,1), (1,-1)}
* The set of pairs {(0,0), (1,0), (1,1)}
* The set of pairs {(4,14), (5,13), (5,14)}
* The set of pairs {(1,11), (1,13), (2,13), (2,14)}
* The set of pairs {(1,12), (1,10), (0,12), (1,13)}
* The set of pairs {(0,12), (1,11), (1,8), (1,9)}
* An IllegalStateException

## BVA Step 4
### Test value 1
Input: Border (0,0)

Expected Output: {(0,1), (1,-1)}

### Test value 2
Input: Border (1,-1)

Expected Output: {(0,0), (1,0), (1,1)}

### Test value 3
Input: Border (-1,-1)

Expected Output: IllegalStateException

### Test value 4
Input: Border (4,15)

Expected Output: {(4,14), (5,13), (5,14)}

### Test value 5
Input: Border (5,16)

Expected Output: IllegalStateException

### Test value 6
Input: Border (MAX,MAX)

Expected Output: IllegalStateException

### Test value 7
Input: Border (MIN,MIN)

Expected Output: IllegalStateException

### Test value 8
Input: Border (1,12)

Expected Output: {(1,11), (1,13), (2,13), (2,14)}

### Test value 9
Input: Border (1,11)

Expected Output: {(1,12), (1,10), (0,12), (1,13)}

### Test value 10
Input: Border (1,10)

Expected Output: {(0,12), (1,11), (1,8), (1,9)}

# method: equals(other: Object): boolean
## BVA Step 1
Input: two border locations

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
Input: border location

Output: number

## BVA Step 2
Input: two intervals \[MIN, MAX]

Output: number

## BVA Step 3
Input: Any hex (3, 3)

## BVA Step 4
Input: (3,3)

Expected Output: 38504087

# method: isValid(): boolean
## BVA Step 1
Input: border location

Output: boolean

## BVA Step 2
Input: two intervals \[MIN, MAX]

Output: boolean

## BVA Step 3
Input:
* Big row (6, 0)
* Small row (-1, 0)
* Big column (0, 16)
* Small column (1, -2)
* Illegal vertical (5, 3)
* Illegal negative (2, -1)
* Valid border (1, 1)

Output: true/false

## BVA Step 4
### Test Case 1
Input: (6, 0)

Expected output: false

### Test Case 2
Input: (-1, 0)

Expected output: false

### Test Case 3
Input: (0, 16)

Expected output: false

### Test Case 4
Input: (1, -2)

Expected output: false

### Test Case 5
Input: (5, 3)

Expected output: false

### Test Case 6
Input: (2, -1)

Expected output: false

### Test Case 7
Input: (1, 1)

Expected output: true