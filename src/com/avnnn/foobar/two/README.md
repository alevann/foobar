# Problem Two

[ ⚠ this readme is a work in progress ⚠ ]

**Assignment**:
You have L, a list containing some digits (0 to 9). Write a function solution(L) which finds the largest number that can be made from some or all of these digits and is divisible by 3. If it
is not possible to make such a number, return 0 as the solution. L will contain anywhere from 1 to 9 digits.  The same digit may appear multiple times in the list, but each element in the
list may only be used once.

## My approach

This is just the second problem, but it already gave me quite the trouble. The objective here is to create the biggest possible number divisible by 3 given a list of digits.

```
7, 4, 7, 6, 1, 1    ->      7764
3, 4, 1, 1          ->      4311
2, 0                ->      0
9, 0, 6             ->      960
5, 5, 5, 5, 5       ->      555
```

At first, I considered building all possible combinations of numbers to then sort them and spit out the biggest that was divisible by 3. That idea died quite fast since it sounded pretty dumb and boring.

Next I tried breaking up the problem a bunch, and I divided it into two smaller problems and that worked, for the most part.
The basic idea was there, but I was missing a crucial step that I had not yet considered at the time.

Anyway, after a bunch of failed attempts and lots of thinking, I eventually broke the problem into these parts:

1. Given a list, remove the least amount of numbers to make the sum of the list be divisible by 3
2. Given a list, a sum, and the number of addends, find the best possible candidates to reach the sum
3. Given a list, build the biggest possible number out of it

With those problems solved the challenge can be completed.
