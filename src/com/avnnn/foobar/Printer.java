package com.avnnn.foobar;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public class Printer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final PrintWriter out = new PrintWriter(System.out, true);

    public static void prompt(Number number) {
        String[] paragraphs = number.problem().prompt().split("\n");

        List<String> lines = new ArrayList<>();
        for (String line : paragraphs) {
            StringBuilder builder = new StringBuilder();
            String[] words = line.split(" ");

            for (String word : words) {
                if (builder.length() + word.length() >= 80) {
                    lines.add(builder.toString());
                    builder = new StringBuilder();
                }
                builder.append(word);
                builder.append(" ");
            }
            lines.add(builder.toString());
        }

        out.println("Prompt:");
        for (String line : lines) {
            out.printf("\t%s%n", line);
        }
        out.println();
    }

    public static void results(Result[] results) {
        int passed = 0;
        int failed = 0;

        for (Result result : results) {
            if (result.passed) {
                passed++;
                passed(result);
            } else {
                failed++;
                failed(result);
            }
        }

        out.printf("%nTotal tests ran: %d%n", results.length);
        if (failed == results.length) {
            out.printf("%sAll tests failed%s%n", ANSI_RED, ANSI_RESET);
        } else if (passed == results.length) {
            out.printf("%sAll tests passed%s%n", ANSI_GREEN, ANSI_RESET);
        } else {
            out.printf("%sPassed%s: %d%n", ANSI_GREEN, ANSI_RESET, passed);
            out.printf("%sFailed%s: %d%n", ANSI_RED, ANSI_RESET, failed);
        }
    }

    private static void passed(Result result) {
        out.printf("%s ✔️ \tPassed%s test case %d%n", ANSI_GREEN, ANSI_RESET, result.id);
    }

    private static void failed(Result result) {
        out.printf(
                "%s ✗ \tFailed%s test case %d: expected %s got %s%n",
                ANSI_RED,
                ANSI_RESET,
                result.id,
                result.expected.toString(),
                result.output
        );

        // Kind of a hack, but it works in IntelliJ
        // and that's what I'm using rn so...
        if (result.error != null) {
            out.write(ANSI_RED);
            result.error.printStackTrace(out);
            out.write(ANSI_RESET);
        }
    }
}
