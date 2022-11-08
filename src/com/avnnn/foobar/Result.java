package com.avnnn.foobar;

import java.time.Duration;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public class Result {
    public final int id;
    public final boolean passed;
    public final Object output;
    public final Object expected;
    public final Duration elapsed;
    public final Exception error;

    public Result(int id, boolean passed, Object result, Object expected, Duration elapsed, Exception error) {
        this.id = id;
        this.passed = passed;
        this.output = result;
        this.expected = expected;
        this.elapsed = elapsed;
        this.error = error;
    }
}
