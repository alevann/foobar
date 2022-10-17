package com.avnnn.foobar.two.b;

import com.avnnn.foobar.Problem;

/**
 * Created on: 17/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public class ProblemTwoB extends Problem<Integer, String> {
    @Override
    protected String prompt() {
        return "Every time the Commander's employees pass each other in the hall, each of them must stop and salute each other -- one at a time -- before resuming their path. A salute is five seconds long, so each exchange of salutes takes a full ten seconds (Commander Lambda's salute is a bit, er, involved). You think that by removing the salute requirement, you could save several collective hours of employee time per day. But first, you need to show the Commander how bad the problem really is.\n\nWrite a program that counts how many salutes are exchanged during a typical walk along a hallway. The hall is represented by a string. For example:\n\"--->-><-><-->-\"\n\nEach hallway string will contain three different types of characters: '>', an employee walking to the right; '<', an employee walking to the left; and '-', an empty space. Every employee walks at the same speed either to right or to the left, according to their direction. Whenever two employees cross, each of them salutes the other. They then continue walking until they reach the end, finally leaving the hallway. In the above example, they salute 10 times.\n\nWrite a function solution(s) which takes a string representing employees walking along a hallway and returns the number of times the employees will salute. s will contain at least 1 and at most 100 characters, each one of -, >, or <.";
    }

    @Override
    protected String[] args() {
        return new String[] { ">----<", "<<>><", "-", "<---<" };
    }

    @Override
    protected Integer[] expected() {
        return new Integer[] { 2, 4, 0, 0 };
    }

    // Given how easy it was to come to this solution I am certain
    // I'm either missing something here, or problem 2a had a super
    // simple solution that I missed...

    @Override
    protected Integer solution(String s) {
        int salutes = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if  (c != '>') {
                continue;
            }

            String r = s.substring(i);

            for (int j = 0; j < r.length(); j++) {
                char n = r.charAt(j);

                if (n != '<') {
                    continue;
                }

                salutes = salutes + 2;
            }
        }

        return salutes;
    }
}
