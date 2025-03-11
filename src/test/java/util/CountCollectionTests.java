package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CountCollectionTests {

    // add tests
    @Test
    public void testAdd_WithEmptyAndNegative_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.add("a", -1));
        assertEquals("Count must be greater than 0", ex.getMessage());
    }

    @Test
    public void testAdd_WithEmptyAndZero_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.add("a", 0));
        assertEquals("Count must be greater than 0", ex.getMessage());
    }

    @Test
    public void testAdd_WithEmptyAndOne_Success() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("a", 1);
        int count = cc.getCount("a");
        assertEquals(1, count);
    }

    @Test
    public void testAdd_WithNonEmptyAndOne_Success() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 10);

        cc.add("a", 1);
        int count = cc.getCount("a");
        assertEquals(1, count);
        count = cc.getCount("e");
        assertEquals(10, count);
    }
    
    @Test
    public void testAdd_WithNonEmptyAndMaxInt_Success() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);
        cc.add("a", 10);

        cc.add("u", Integer.MAX_VALUE);
        int count = cc.getCount("a");
        assertEquals(10, count);
        count = cc.getCount("e");
        assertEquals(1, count);
        count = cc.getCount("u");
        assertEquals(Integer.MAX_VALUE, count);
    }

    @Test
    public void testAdd_WithNonEmptyAndTooLarge_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);
        cc.add("a", 10);
        cc.add("u", 1);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.add("u", Integer.MAX_VALUE));
        assertEquals("Count cannot go over Integer.MAX_VALUE", ex.getMessage());
    }

    @Test
    public void testAdd_WithLargeAndOne_Success() {
        CountCollection<Integer> cc = new CountCollection<>();
        for (int i = 0; i < 100; i++) {
            cc.add(i, 1);
        }
        cc.add(100, 1);

        int count = 0;
        for (int i = 0; i < 100; i++) {
            count = cc.getCount(i);
            assertEquals(1, count);
        }
        count = cc.getCount(100);
        assertEquals(1, count);
    }

    // getCount tests
    @Test
    public void testGetCount_WithEmpty_ReturnZero() {
        CountCollection<String> cc = new CountCollection<>();
        int count = cc.getCount("a");
        assertEquals(0, count);
    }

    @Test
    public void testGetCount_WithOne_ReturnOne() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("a", 1);
        int count = cc.getCount("a");
        assertEquals(1, count);
    }

    @Test
    public void testGetCount_WithMax_ReturnMax() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("a", Integer.MAX_VALUE);
        int count = cc.getCount("a");
        assertEquals(Integer.MAX_VALUE, count);
    }

    @Test
    public void testGetCount_WithMultiEntry_Return10() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("a", 1);
        cc.add("e", 10);
        int count = cc.getCount("e");
        assertEquals(10, count);
    }

    @Test
    public void testGetCount_WithLarge_ReturnOne() {
        CountCollection<Integer> cc = new CountCollection<>();
        for (int i = 0; i < 100; i++) {
            cc.add(i, 1);
        }

        int count = cc.getCount(1);
        assertEquals(1, count);
    }

    @Test
    public void testGetCount_WithLarge_ReturnZero() {
        CountCollection<Integer> cc = new CountCollection<>();
        for (int i = 0; i < 100; i++) {
            cc.add(i, 1);
        }

        int count = cc.getCount(100);
        assertEquals(0, count);
    }

    @Test
    public void testRemove_WithNegative_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.remove("a", -1));
        String expected = "Count must be greater than 0";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testRemove_WithZero_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.remove("a", 0));
        String expected = "Count must be greater than 0";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testRemove_WithEmpty_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.remove("a", 1));
        String expected = "Cannot remove \"a\" from empty collection";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void testRemove_WithOne_ReturnsEmpty() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);

        cc.remove("e", 1);
        int count = cc.getCount("e");
        assertEquals(0, count);
    }

    @Test
    public void testRemove_WithTwo_ReturnsOne() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);
        cc.add("a", 10);

        cc.remove("e", 1);
        int count = cc.getCount("e");
        assertEquals(0, count);
        count = cc.getCount("a");
        assertEquals(10, count);
    }

    @Test
    public void testRemove_WithPartial_ReturnsTwo() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);
        cc.add("a", 10);

        cc.remove("a", 3);
        int count = cc.getCount("e");
        assertEquals(1, count);
        count = cc.getCount("a");
        assertEquals(7, count);
    }

    @Test
    public void testRemove_WithMax_Exception() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);
        cc.add("a", 10);
        cc.add("u", 1);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.remove("u", Integer.MAX_VALUE));
        String expected = "Cannot remove \"u\" because there is less than "+Integer.MAX_VALUE;
        assertEquals(expected, ex.getMessage());
    }
    
    @Test
    public void testRemove_WithLarge_Success() {
        CountCollection<Integer> cc = new CountCollection<>();
        for (int i = 0; i < 100; i++) {
            cc.add(i, Integer.MAX_VALUE);
        }

        cc.remove(99, Integer.MAX_VALUE);

        int count = 0;
        for (int i = 0; i < 99; i++) {
            count = cc.getCount(i);
            assertEquals(Integer.MAX_VALUE, count);
        }
        count = cc.getCount(99);
        assertEquals(0, count);
    }

    @Test
    public void testRemove_WithZeroCount_ThrowsException() {
        CountCollection<String> cc = new CountCollection<>();
        cc.add("e", 1);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> cc.remove("a", 1));
        String expected = "Cannot remove \"a\" because there is less than 1";
        assertEquals(expected, ex.getMessage());
    }
}
