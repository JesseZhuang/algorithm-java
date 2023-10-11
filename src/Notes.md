## Recursive and Iterative

Recursion might be more readable in some cases but requires stack space. Iterative solution in practice can often be
faster.

References

1. Fibonacci O(n) stack space https://stackoverflow.com/questions/43298938/space-complexity-of-recursive-function
1. Performance compare https://stackoverflow.com/questions/72209/recursion-or-iteration
1. Java TreeMap uses while loops, not recursion.

Not all recursions can be turned into tail recursions, .e.g, quick sort.

1. https://stackoverflow.com/questions/1888702/are-there-problems-that-cannot-be-written-using-tail-recursion
1. http://www.owlnet.rice.edu/~comp210/96spring/Labs/lab09.html#:~:text=Not%20every%20recursive%20function%20can,make%20the%20function%20tail%2Drecursive
1. https://stackoverflow.com/questions/11715224/can-all-recursive-functions-be-re-written-as-tail-recursions

## LeetCode Practice Notes

### Java

1. `map.containsKey()` not `map.contains()`

### LeetCode

1. OJ (online judge) does not allow changing method signature. So when discussing throwing an exception, maybe just
   choose to log/print. For example, question 1, two sum, question mentioned can assume exactly one solution but code
   need to return empty result or null in the case no solution is found.
