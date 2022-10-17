package com.avnnn.foobar;

import com.avnnn.foobar.two.a.ProblemTwoA;
import com.avnnn.foobar.one.ProblemOne;
import com.avnnn.foobar.two.b.ProblemTwoB;

/**
 * Created on: 11/10/2022
 *
 * @author alessandrovnnn@gmail.com
 * @since 1.0.0
 */
public enum Number {
    One,
    TwoA,
    TwoB;

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
            case TwoA:
                return new ProblemTwoA();
            case TwoB:
                return new ProblemTwoB();
            default:
                throw new IllegalStateException("You forgot to assign a problem here, man.");
        }
    }
}
