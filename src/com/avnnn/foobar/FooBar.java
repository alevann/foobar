package com.avnnn.foobar;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public class FooBar {

    public static void main (String[] args) {
        Result[] results = Problem.solve(Number.One);

        Printer.prompt(Number.One);
        Printer.results(results);
    }
}
