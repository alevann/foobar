package com.avnnn.foobar;

import java.time.Duration;
import java.time.Instant;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public abstract class Problem<S, A> {

    protected abstract String prompt();
    protected abstract A[] args();
    protected abstract S[] expected();
    protected abstract S solution(A a);

    public static Result[] solve(Number number) {
        Problem<Object, Object> problem = number.problem();
        Object[] args = problem.args();
        Object[] expected = problem.expected();

        if (args.length != expected.length) {
            throw new IllegalStateException("Array length mismatch");
        }

        Result[] results = new Result[args.length];

        for (int i = 0; i < args.length; i++) {
            boolean passed = false;
            Object output = null;
            Exception err = null;

            Instant start = Instant.now();
            try {
                output = problem.solution(args[i]);
                passed = output.equals(expected[i]);
            } catch (Exception e) {
                err = e;
            }

            Duration elapsed = Duration.between(start, Instant.now());

            results[i] = new Result(i, passed, output, expected[i], elapsed, err);
        }

        return results;
    }
}
