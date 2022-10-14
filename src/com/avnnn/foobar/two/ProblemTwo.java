package com.avnnn.foobar.two;

import com.avnnn.foobar.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public class ProblemTwo extends Problem<Integer, Integer[]> {

    @Override
    protected String prompt() {
        return "You have L, a list containing some digits (0 to 9). Write a function solution(L) which finds the largest number that can be made from some or all of these digits and is divisible by 3. If it is not possible to make such a number, return 0 as the solution. L will contain anywhere from 1 to 9 digits. The same digit may appear multiple times in the list, but each element in the list may only be used once.";
    }

    @Override
    protected Integer[][] args() {
        return new Integer[][] { { 7, 6, 7, 1, 4, 1 }, { 3, 4, 1, 1 }, { 7, 6, 7, 1, 2, 2, 1 }, { 3, 1, 4, 2, 6 }, { 5, 5, 5, 5, 5 }, { 8 }, { 3 }, { 5, 5, 5, 5, 5 }, { 0, 9 } };
    }

    @Override
    protected Integer[] expected() {
        return new Integer[] { 7764, 4311, 776211, 6432, 555, 0, 3, 555, 90 };
    }

    @Override
    protected Integer solution(Integer[] args) {
        return solution(Arrays.stream(args).mapToInt(n -> n).toArray());
    }

    private static int solution (int[] array) {
        int sum = IntStream.of(array).sum();
        int rem = sum % 3;

        if (rem == 0) {
            return arrayToInt(array);
        }

        int closestMultiple = sum - rem;
        int addendsCount = 1;

        while (addendsCount <= array.length) {
            int diff = sum - closestMultiple;
            if (diff >= 9 * addendsCount) {
                addendsCount++;
                closestMultiple = sum - rem;
                diff = sum - closestMultiple;
            }

            int[] match = findAddends(array, diff, addendsCount);
            if (match.length > 0) {
                List<Integer> result = IntStream.of(array).boxed().collect(Collectors.toList());
                for (int n : match) {
                    result.remove((Integer) n);
                }
                return arrayToInt(result.stream().mapToInt(n->n).toArray());
            }
            closestMultiple -= 3;
        }

        return 0;
    }

    private static int[] findAddends(int[] array, int diff, int addendsCount) {
        if (addendsCount == 1) {
            return IntStream.of(array)
                    .filter(n -> n == diff)
                    .limit(1)
                    .toArray();
        }

        /*if (addendsCount == 2) {
            Integer[] combinations = new Integer[18];
            for (int n : array) {
                if (combinations[n] != null) {
                    return new int[] { n, combinations[n] };
                } else if (n < diff) {
                    combinations[diff - n] = n;
                }
            }
        }*/

        return findAddends(diff, addendsCount, Arrays.stream(array).boxed().collect(Collectors.toList())).stream().sorted().mapToInt(n->n).toArray();
    }

    private static int arrayToInt (int[] array) {
        AtomicInteger i = new AtomicInteger();
        return IntStream.of(array)
                .sorted()
                .map(n -> n * (int) Math.pow(10, i.getAndIncrement()))
                .sum();
    }

    // S = 2, 2, 4
    // S = 6, 1, 1
    // S = 3, 3, 2

    /**
     * <pre>
     * define routine `Find Addends` (
     *     let T be the target sum to reach
     *     let D be the number of addends that must sum up to T
     *     let L be the list of digits to pick from
     * ) {
     *     if D is equal to 1 {
     *         let R be a list of integers
     *         let K be T if T exists in L
     *         if K is equal to T {
     *             add R1 to R
     *         }
     *         return R
     *     }
     *
     *     let R be a list of lists to contain the results
     *
     *     for each (Index, Digit) in L {
     *         let L1 be a copy of L without Digit
     *         let T1 be T - Digit
     *         let D1 be D - 1
     *
     *         let R1 be the result of `Find Addends` ( T1, D1, L1 )
     *         if R1 is of size D1 and sums to T1 {
     *             add R1 to R
     *         }
     *     }
     *
     *     let I be an integer
     *     let M be an integer initialized at 0
     *     for each (Index, List) in R {
     *         let Max be the highest value in List
     *         if  Max is bigger than M {
     *             Max = M1
     *             I = Index
     *         }
     *     }
     *
     *     return R[I]
     * }
     * </pre>
     *
     *
     */

    // target = 8
    // depth = 3
    // digits = 2, 3, 2, 4, 8, 6, 1, 1, 3

        // 2 -> target -2 -> depth -1 -> togli 2 da digits

        // target = 6
        // depth = 2
        // digits = 3, 2, 4, 8, 6, 1, 1, 3

            // 3 -> target -3 -> depth -1 -> toglie 3 da digits

            // target = 3
            // depth = 1
            // digits = 2, 4, 8, 6, 1, 1, 3

            // return 3 -> return 3 + 3 -> return 2 + 3 + 3

        // 3 -> target -3 -> depth -1 -> togli 3 da digits

        // target = 5
        // depth = 2
        // digits = 2, 2, 4, 8, 6, 1, 1, 3

            // 2 -> target -2 -> depth -1 -> togli 2 da digits

            // target = 3
            // depth = 1
            // digits = 2, 2, 4, 8, 6, 1, 1, 3

            // return 3 -> return 3 + 2 -> return 3 + 2 + 3

        // 2 -> target -> -2 -> depth -1 -> togli 2 da digits

        // target = 6
        // depth = 2
        // digits = 2, 3, 4, 8, 6, 1, 1, 3

            // 2

    private static List<Integer> findAddends (int target, int depth, List<Integer> digits) {
        if (depth == 1) {
            List<Integer> result = new ArrayList<>();
            digits.stream()
                    .filter(n -> n == target)
                    .findFirst()
                    .ifPresent(result::add);

            return result;
        }

        List<List<Integer>> results = new ArrayList<>();

        for (Integer digit : digits) {
            if (digit >= target) {
                continue;
            }

            List<Integer> digitsCopy = new ArrayList<>(digits);
            digitsCopy.remove(digit);

            int newTarget = target - digit;
            int newDepth = depth - 1;

            List<Integer> result = findAddends(newTarget, newDepth, digitsCopy);
            if (result.size() == newDepth && result.stream().reduce(0, Integer::sum) == newTarget) {
                result.add(digit);
                results.add(result);
            }
        }

        if (results.size() == 0) {
            return new ArrayList<>();
        }

        int i = 0;
        int m = 0;
        for (int j = 0; j < results.size(); j++) {
            int m1 = results.get(j).stream().max(Integer::compare).get();
            if (m1 > m) {
                m = m1;
                i = j;
            }
        }

        return results.get(i);
    }
}
