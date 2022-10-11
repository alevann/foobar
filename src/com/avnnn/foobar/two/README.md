# Problem Two

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
The basic idea is there, but I was missing a crucial step that I had not yet considered at the time: I needed to be able to break up a sum into addends, I'll touch on this later.

Anyways, after a bunch of failed attempts and lots of thinking, I eventually broke the problem into these steps:

1. Given a list, remove the least amount of numbers to make the sum of the list be divisible by 3
2. Given a list, a sum, and the number of addends, find the best possible candidates to reach the sum
3. Given a list, build the biggest possible number out of it

Let's take it from the bottom, problem number 3 was quite easy to solve as I had done something like this many times before, here's the pseudo-code for it:

```
def ArrayToInt ( int array Array ):
    let Result be 0
    
    sort Array such that Array[0] < Array[1]
    
    for (Index, Number) in Array:
        Result += Number * 10^Index
    
    return Result
```

The real tricky part starts now

```
let FindAddends be a function that takes an array, a sum, and an amount of addends
    and outputs the addends that can be used to obtain the sum, if any


let L be an array of digits
let S be the sum of L

let R be S % 3
let M be S - R

if R is 0:
    return ArrayToInt(L)

let A be 1
while A is smaller than the length of L:
    let D be S - M
    if D >= 9 * A:
        increment A
        M = S - R
        D = S - M
    
    let K be FindAddends(L, D, A)
    if K is not empty:
        return ArrayToInt(L - K)
    
    decrement M by 3

return 0
```
