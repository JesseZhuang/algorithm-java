package hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeBasedKeyValueStoreTest {

    @Test
    void testBasicSetGet() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        tm1.set("foo", "bar", 1);
        tm2.set("foo", "bar", 1);

        assertEquals("bar", tm1.get("foo", 1));
        assertEquals("bar", tm2.get("foo", 1));
    }

    @Test
    void testGetWithLargerTimestamp() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        tm1.set("foo", "bar", 1);
        tm2.set("foo", "bar", 1);

        // timestamp 3 > 1, should return "bar"
        assertEquals("bar", tm1.get("foo", 3));
        assertEquals("bar", tm2.get("foo", 3));
    }

    @Test
    void testTimestampBeforeAnySet() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        tm1.set("foo", "bar", 5);
        tm2.set("foo", "bar", 5);

        // timestamp 3 < 5, no valid value
        assertEquals("", tm1.get("foo", 3));
        assertEquals("", tm2.get("foo", 3));
    }

    @Test
    void testMissingKey() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        assertEquals("", tm1.get("nonexistent", 1));
        assertEquals("", tm2.get("nonexistent", 1));
    }

    @Test
    void testMultipleKeys() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        tm1.set("a", "val1", 1);
        tm1.set("b", "val2", 2);
        tm2.set("a", "val1", 1);
        tm2.set("b", "val2", 2);

        assertEquals("val1", tm1.get("a", 5));
        assertEquals("val2", tm1.get("b", 5));
        assertEquals("val1", tm2.get("a", 5));
        assertEquals("val2", tm2.get("b", 5));
    }

    @Test
    void testMultipleTimestampsSameKey() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        tm1.set("foo", "bar", 1);
        tm1.set("foo", "bar2", 4);
        tm2.set("foo", "bar", 1);
        tm2.set("foo", "bar2", 4);

        assertEquals("bar", tm1.get("foo", 1));
        assertEquals("bar", tm1.get("foo", 3));
        assertEquals("bar2", tm1.get("foo", 4));
        assertEquals("bar2", tm1.get("foo", 5));

        assertEquals("bar", tm2.get("foo", 1));
        assertEquals("bar", tm2.get("foo", 3));
        assertEquals("bar2", tm2.get("foo", 4));
        assertEquals("bar2", tm2.get("foo", 5));
    }

    @Test
    void testLargeGapInTimestamps() {
        TimeBasedKeyValueStore.TimeMap1 tm1 = new TimeBasedKeyValueStore.TimeMap1();
        TimeBasedKeyValueStore.TimeMap2 tm2 = new TimeBasedKeyValueStore.TimeMap2();

        tm1.set("key", "early", 1);
        tm1.set("key", "late", 10000000);
        tm2.set("key", "early", 1);
        tm2.set("key", "late", 10000000);

        assertEquals("early", tm1.get("key", 5000000));
        assertEquals("late", tm1.get("key", 10000000));
        assertEquals("early", tm2.get("key", 5000000));
        assertEquals("late", tm2.get("key", 10000000));
    }
}
