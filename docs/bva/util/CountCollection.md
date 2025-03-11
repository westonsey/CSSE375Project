# method: add(T item, int amount): void

## BVA Step 1
Input: an element, an amount, and a count collection

Output: a count collection

## BVA Step 2
Input: any type (element), interval \[1, MAX], collection

Output: collection or exception

## BVA Step 3
Input: 
* any element (try different types)
* negative numbers, 0, 1, 10, Integer.MAX_VALUE
* an empty collection, a collection with 1 element (string "e" x1), a collection with 2 elements (string "e" x1, string "a" x10), a collection with 100 elements (integers 0-99 x1 each)

Output:
* a collection with 1 element, a collection with 2 elements, a collection with 101 elements
* an exception for inserting an invalid count

## BVA Step 4
### Test value 1
Collection {}, Element "a", Count -1

Expected Output: IllegalArgumentException

### Test value 2
Collection {}, Element "a", Count 0

Expected Output: IllegalArgumentException

### Test value 3
Collection {}, Element "a", Count 1

Expected Output: {"a":1}

### Test value 4
Collection {"e":1}, Element "a", Count 1

Expected Output: {"e":1,"a":1}

### Test value 5
Collection {"e":1,"a":10}, Element "u", Count Integer.MAX_VALUE

Expected Output: {"e":1,"a":10,"u":Integer.MAX_VALUE}

### Test value 6
Collection {"e":1,"a":10,"u":1}, Element "u", Count Integer.MAX_VALUE

Expected Output: IllegalArgumentException

### Test value 7
Collection {0..99:1}, Element 100, Count 1

Expected Output: {0..100:1}


# method: getCount(T item):int

## BVA Step 1
Input: a count collection, an element

Output: a count

## BVA Step 2
Input: a collection, any type (element)

Output: count

## BVA Step 3
Input:
* an empty collection, a collection with 1 element (string "a" x1), a collection with 2 elements (string "a" x1, string "e" x10), a collection with 100 elements (integers 0-99 x1 each)
* any element (try different types)

Output:
* 0, 1, Integer.MAX_VALUE

## BVA Step 4
### Test value 1

Collection {}, Element "a"

Output: 0

### Test value 2

Collection {"a":1}, Element "a"

Output: 1

### Test value 3

Collection {"a":Integer.MAX_VALUE}, Element "a"

Output: Integer.MAX_VALUE

### Test value 4

Collection {"a":1,"e":10}, Element "e"

Output: 10

### Test value 5

Collection {0..99:1}, Element 1

Output: 1

### Test value 6

Collection {0..99:1}, Element 100

Output: 0

# method: remove(T item, int amount): void

## BVA Step 1
Input: an element, an amount, and a count collection

Output: a count collection

## BVA Step 2
Input: any type (element), interval \[1, MAX], collection

Output: collection or exception

## BVA Step 3
Input:
* any element (try different types)
* negative numbers, 0, 1, 10, Integer.MAX_VALUE
* an empty collection, a collection with 1 element (string "e" x1), a collection with 2 elements (string "e" x1, string "a" x10), a collection with 100 elements (integers 0-99 xINT.MAX each)

Output:
* a collection with no elements, a collection with 1 element, a collection with 99 elements (0-98 xINT.MAX each)
* an exception for removing an invalid count (negative numbers or removing more than exist)

## BVA Step 4
### Test value 1
Collection {}, Element "a", Count -1

Expected Output: IllegalArgumentException

### Test value 2
Collection {}, Element "a", Count 0

Expected Output: IllegalArgumentException

### Test value 3
Collection {}, Element "a", Count 1

Expected Output: IllegalArgumentException

### Test value 4
Collection {"e":1}, Element "e", Count 1

Expected Output: {}

### Test value 5
Collection {"e":1,"a":10}, Element "a", Count 10

Expected Output: {"e":1}

### Test value 6
Collection {"e":1,"a":10}, Element "a", Count 3

Expected Output: {"e":1,"a":7}

### Test value 7
Collection {"e":1,"a":10,"u":1}, Element "u", Count Integer.MAX_VALUE

Expected Output: IllegalArgumentException

### Test value 8
Collection {0..99:Integer.MAX_VALUE}, Element 99, Count Integer.MAX_VALUE

Expected Output: {0..98:Integer.MAX_VALUE}

### Test value 9
Collection {"e":1}, Element "a", Count 1

Expected Output: IllegalArgumentException