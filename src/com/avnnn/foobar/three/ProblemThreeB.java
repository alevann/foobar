package com.avnnn.foobar.three;

import com.avnnn.foobar.Problem;

public class ProblemThreeB extends Problem<Integer, Integer> {
    @Override
    protected String prompt() {
        return "With the LAMBCHOP doomsday device finished, Commander Lambda is preparing to debut on the galactic stage -- but in order to make a grand entrance, Lambda needs a grand staircase! As the Commander's personal assistant, you've been tasked with figuring out how to build the best staircase EVER.\n\nLambda has given you an overview of the types of bricks available, plus a budget. You can buy different amounts of the different types of bricks (for example, 3 little pink bricks, or 5 blue lace bricks). Commander Lambda wants to know how many different types of staircases can be built with each amount of bricks, so they can pick the one with the most options.\n\nEach type of staircase should consist of 2 or more steps.  No two steps are allowed to be at the same height - each step must be lower than the previous one. All steps must contain at least one brick. A step's height is classified as the total amount of bricks that make up that step.\nFor example, when N = 3, you have only 1 choice of how to build the staircase, with the first step having a height of 2 and the second step having a height of 1: (# indicates a brick)\n\n#\n##\n21\n\nWhen N = 4, you still only have 1 staircase choice:\n\n#\n#\n##\n31\n\nBut when N = 5, there are two ways you can build a staircase from the given bricks. The two staircases can have heights (4, 1) or (3, 2), as shown below:\n\n#\n#\n#\n##\n41\n\n#\n##\n##\n32\n\nWrite a function called solution(n) that takes a positive integer n and returns the number of different staircases that can be built from exactly n bricks. n will always be at least 3 (so you can have a staircase at all), but no more than 200, because Commander Lambda's not made of money!";
    }

    @Override
    protected Integer[] args() {
        return new Integer[] { 4, 5, 200, 3 };
    }

    @Override
    protected Integer[] expected() {
        return new Integer[] { 1, 2, 487067745, 1 };
    }

    @Override
    protected Integer solution(Integer arg) {
        return solution((int) arg);
    }


    private static int solution (int n) {
        int t = m(n);
        int s = 0;
        Integer[][] memo = new Integer[n + 1][t + 1];

        for (int i = 0; i <= t; i++) {
            s += q(n, i, memo);
        }

        return s - 1;
    }

    private static int m (int n) {
        return Math.floorDiv((int) Math.pow(8 * n + 1, 0.5) - 1, 2);
    }

    private static int q (int n, int k, Integer[][] memo) {
        if (n == k && k == 1) {
            return 1;
        }
        if (k == 0 && n > 0) {
            return 0;
        }
        if (n < k || k < 1) {
            return 0;
        }

        if (memo[n][k] == null) {
            memo[n][k] = q(n - k, k, memo) + q(n - k, k - 1, memo);
        }
        return memo[n][k];
    }
}
