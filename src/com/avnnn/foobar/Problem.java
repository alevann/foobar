package com.avnnn.foobar;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public abstract class Problem<S, A> {

    protected abstract String prompt();
    protected abstract S solution(A a);
    protected abstract A[] args();
    protected abstract S[] expected();

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
            try {
                output = problem.solution(args[i]);
                passed = output.equals(expected[i]);
            } catch (Exception e) {
                err = e;
            }

            results[i] = new Result(i, passed, output, expected[i], err);
        }

        return results;
    }
}
