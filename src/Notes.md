## Recursive and Iterative

Recursion might be more readable in some cases but requires stack space. Iterative solution in practice can often be
faster.

References

1. Fibonacci O(n) stack space https://stackoverflow.com/questions/43298938/space-complexity-of-recursive-function
2. Performance compare https://stackoverflow.com/questions/72209/recursion-or-iteration
3. Java TreeMap uses while loops, not recursion.

Not all recursions can be turned into tail recursions, .e.g, quick sort.

1. https://stackoverflow.com/questions/1888702/are-there-problems-that-cannot-be-written-using-tail-recursion
2. http://www.owlnet.rice.edu/~comp210/96spring/Labs/lab09.html#:~:text=Not%20every%20recursive%20function%20can,make%20the%20function%20tail%2Drecursive
3. https://stackoverflow.com/questions/11715224/can-all-recursive-functions-be-re-written-as-tail-recursions

## LeetCode Practice Notes

### Java

1. `map.containsKey()` not `map.contains()`

### LeetCode

1. OJ (online judge) does not allow changing method signature. So when discussing throwing an exception, maybe just
   choose to log/print. For example, question 1, two sum, question mentioned can assume exactly one solution but code
   need to return empty result or null in the case no solution is found.

## String and Unicode

- Unicode is a character set, others like ISO-8859, windows 1252 (cp-1252).
- Unicode has 1,114,112 code points, but only characters are assigned to more than 96,000 of them.
- The Unicode code space is divided into 17 planes, each with 65,536 code points.
- Different encodings, like UTF-8 or UTF-16, can encode the same code point as different byte sequences.
- UTF-8: Variable-width encoding, backwards compatible with ASCII. ASCII characters (U+0000 to U+007F) take 1 byte, code
  points U+0080 to U+07FF take 2 bytes, code points U+0800 to U+FFFF take 3 bytes, code points U+10000 to U+10FFFF take
  4 bytes. Good for English text, not so good for Asian text.
- UTF-16: Variable-width encoding. Code points U+0000 to U+FFFF take 2 bytes, code points U+10000 to U+10FFFF take 4
  bytes. Bad for English text, good for Asian text.
- UTF-32: Fixed-width encoding. All code points take four bytes. An enormous memory hog, but fast to operate on. Rarely
  used.

### Programming Languages

- Internally, Java uses UTF-16.
- Python strings are Unicode by default in Python 3, so they don't have an inherent encoding. Starting from Python 3.15,
  the default encoding for files, stdio, and pipes is UTF-8. This means you don't need to specify the encoding
  explicitly in most cases. `chr()` valid range for the argument is 0 through 1,114,111 (0x10FFFF).

- https://www.cogsci.ed.ac.uk/~richard/utf-8.cgi?input=1000&mode=hex
- https://onlinetools.com/unicode

Examples:

| Character | Unicode | UTF-8(hex) | UTF-16(hex) |
|-----------|---------|------------|-------------|
| a         | U+0061  | 61         | 0061        |
| √         | U+221A  | e2 88 9a   | 221a        |
