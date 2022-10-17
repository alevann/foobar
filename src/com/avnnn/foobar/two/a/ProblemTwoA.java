package com.avnnn.foobar.two.a;

import com.avnnn.foobar.Problem;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public class ProblemTwoA extends Problem<Integer, Integer[]> {

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

            int[] match = findAddends(diff, addendsCount, array);
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

    private static int arrayToInt (int[] array) {
        AtomicInteger i = new AtomicInteger();
        return IntStream.of(array)
                .sorted()
                .map(n -> n * (int) Math.pow(10, i.getAndIncrement()))
                .sum();
    }

    private static int[] findAddends (int target, int depth, int[] digits) {
        if (depth == 1) {
            return IntStream.of(digits).filter(n -> n == target).limit(1).toArray();
        }

        if (depth == 2) {
            int[] combinations = new int[9 * 2];
            for (int digit : digits) {
                if (combinations[digit] != 0) {
                    return new int[] { digit, combinations[digit] };
                } else if (digit < target) {
                    combinations[target - digit] = digit;
                }
            }
            return new int[0];
        }

        int[][] addends = new int[digits.length][depth];
        boolean match = false;

        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i];
            if (digit >= target) {
                continue;
            }

            int[] updated = IntStream.of(digit).toArray();
            int t = target - digit;
            int d = depth - 1;

            int[] result = findAddends(t, d, updated);
            if (result.length == d) {
                match = true;
                addends[i] = IntStream.concat(IntStream.of(result), IntStream.of(digit)).toArray();
            }
        }

        if (!match) {
            return new int[0];
        }

        int i = 0;
        int m = 0;
        for (int j = 0; j < digits.length; j++) {
            int m1 = IntStream.of(addends[j]).reduce(0, Math::max);

            // Find the sum with the lowest highest digit
            if (m1 < m || m == 0) {
                m = m1;
                i = j;
            }
        }

        return addends[i];
    }
}
