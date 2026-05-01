package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxFreqStackTest {

    @Test
    void testLeetCodeExample() {
        // push [5,7,5,7,4,5], pop order: 5,7,5,4
        MaxFreqStack.FreqStack fs = new MaxFreqStack.FreqStack();
        fs.push(5);
        fs.push(7);
        fs.push(5);
        fs.push(7);
        fs.push(4);
        fs.push(5);
        assertEquals(5, fs.pop());
        assertEquals(7, fs.pop());
        assertEquals(5, fs.pop());
        assertEquals(4, fs.pop());
    }

    @Test
    void testSingleElement() {
        MaxFreqStack.FreqStack fs = new MaxFreqStack.FreqStack();
        fs.push(42);
        assertEquals(42, fs.pop());
    }

    @Test
    void testAllSameElement() {
        MaxFreqStack.FreqStack fs = new MaxFreqStack.FreqStack();
        fs.push(3);
        fs.push(3);
        fs.push(3);
        assertEquals(3, fs.pop());
        assertEquals(3, fs.pop());
        assertEquals(3, fs.pop());
    }

    @Test
    void testInterleavedPushPop() {
        MaxFreqStack.FreqStack fs = new MaxFreqStack.FreqStack();
        fs.push(1);
        fs.push(2);
        fs.push(1);
        // freq: 1->2, 2->1. maxFreq=2
        assertEquals(1, fs.pop()); // most frequent, freq(1) becomes 1, maxFreq=1
        fs.push(3);
        // freq: 1->1, 2->1, 3->1. maxFreq=1. group[1]=[1,2,3]
        assertEquals(3, fs.pop()); // tie at freq 1, most recent pushed is 3
        assertEquals(2, fs.pop()); // tie at freq 1, next most recent is 2
        assertEquals(1, fs.pop()); // only 1 left
    }
}
