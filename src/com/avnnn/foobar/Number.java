package com.avnnn.foobar;

import com.avnnn.foobar.two.ProblemTwo;
import com.avnnn.foobar.one.ProblemOne;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public enum Number {
    One,
    Two;

    private Problem<?, ?> problem;

    public Problem<Object, Object> problem() {
        if (this.problem == null) {
            this.problem = getProblem();
        }
        return (Problem<Object, Object>) this.problem;
    }

    private Problem<?, ?> getProblem () {
        switch (this) {
            case One:
                return new ProblemOne();
            case Two:
                return new ProblemTwo();
            default:
                throw new IllegalStateException("You forgot to assign a problem here, man.");
        }
    }
}
