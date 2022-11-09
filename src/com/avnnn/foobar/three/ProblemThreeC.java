package com.avnnn.foobar.three;

import com.avnnn.foobar.Problem;

import java.math.BigInteger;

public class ProblemThreeC extends Problem<String, String[]> {
    @Override
    protected String prompt() {
        return "You're so close to destroying the LAMBCHOP doomsday device you can taste it! But in order to do so, you need to deploy special self-replicating bombs designed for you by the brightest scientists on Bunny Planet. There are two types: Mach bombs (M) and Facula bombs (F). The bombs, once released into the LAMBCHOP's inner workings, will automatically deploy to all the strategic points you've identified and destroy them at the same time.\n\nBut there's a few catches. First, the bombs self-replicate via one of two distinct processes:\nEvery Mach bomb retrieves a sync unit from a Facula bomb; for every Mach bomb, a Facula bomb is created;\nEvery Facula bomb spontaneously creates a Mach bomb.\n\nFor example, if you had 3 Mach bombs and 2 Facula bombs, they could either produce 3 Mach bombs and 5 Facula bombs, or 5 Mach bombs and 2 Facula bombs. The replication process can be changed each cycle.\n\nSecond, you need to ensure that you have exactly the right number of Mach and Facula bombs to destroy the LAMBCHOP device. Too few, and the device might survive. Too many, and you might overload the mass capacitors and create a singularity at the heart of the space station - not good!\n\nAnd finally, you were only able to smuggle one of each type of bomb - one Mach, one Facula - aboard the ship when you arrived, so that's all you have to start with. (Thus it may be impossible to deploy the bombs to destroy the LAMBCHOP, but that's not going to stop you from trying!)\n\nYou need to know how many replication cycles (generations) it will take to generate the correct amount of bombs to destroy the LAMBCHOP. Write a function solution(M, F) where M and F are the number of Mach and Facula bombs needed. Return the fewest number of generations (as a string) that need to pass before you'll have the exact number of bombs necessary to destroy the LAMBCHOP, or the string \"impossible\" if this can't be done! M and F will be string representations of positive integers no larger than 10^50. For example, if M = \"2\" and F = \"1\", one generation would need to pass, so the solution would be \"1\". However, if M = \"2\" and F = \"4\", it would not be possible.";
    }

    @Override
    protected String[][] args() {
        return new String[][] { { "2", "1" }, { "4", "7" }, { "2", "2" }, { "3", "3" }, { "1", "1" }, { "581497373652535413", "47223665165468444454848363684841687416464687681"}, { "100000000000000000000000000000000000000000000000000", "1" }, { "47223665165468444454848363684841687416464687681", "47223665165468444454848363684841687416464687680" }, { "55", "46" }, { "7", "14" }, { "14", "7" } };
    }

    @Override
    protected String[] expected() {
        return new String[] { "1", "4", "impossible", "impossible", "0", "81210453056467630731203915625", "99999999999999999999999999999999999999999999999999", "47223665165468444454848363684841687416464687680", "14", "impossible", "impossible" };
    }

    @Override
    protected String solution(String[] args) {
        return solution("" + args[0], "" + args[1]);
    }

    public static String solution (String x, String y) {
        BigInteger m = new BigInteger(x);
        BigInteger f = new BigInteger(y);
        BigInteger g = BigInteger.ZERO;

        while (!m.equals(BigInteger.ZERO) && !f.equals(BigInteger.ZERO)) {
            BigInteger max = m.max(f);
            BigInteger min = m.min(f);

            BigInteger[] result = max.divideAndRemainder(min);
            BigInteger div = result[0];
            BigInteger rem = result[1];

            m = rem;
            f = min;
            g = g.add(div);
        }

        if (!m.add(f).equals(BigInteger.ONE)) {
            return "impossible";
        }

        return g.subtract(BigInteger.ONE).toString();
    }

    public static BigInteger gcd (BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        }
        return gcd(b, a.remainder(b));
    }
}
