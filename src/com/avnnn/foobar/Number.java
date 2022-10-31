package com.avnnn.foobar;

import com.avnnn.foobar.three.ProblemThreeA;
import com.avnnn.foobar.three.ProblemThreeB;
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
    TwoB,
    ThreeA,
    ThreeB;

    private Problem<?, ?> problem;

    public Problem<Object, Object> problem() {
        if (this.problem == null) {
            this.problem = getProblem();
        }
        return (Problem<Object, Object>) this.problem;
    }

    private Problem<?, ?> getProblem () {
        return switch (this) {
            case One -> new ProblemOne();
            case TwoA -> new ProblemTwoA();
            case TwoB -> new ProblemTwoB();
            case ThreeA -> new ProblemThreeA();
            case ThreeB -> new ProblemThreeB();
            default -> throw new IllegalStateException("You forgot to assign a problem here, man.");
        };
    }
}
