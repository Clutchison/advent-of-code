package com.hutchison.calendar.intcode.operation.jump;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.UnaryOperation;

import java.util.List;
import java.util.function.Predicate;

public abstract class JumpOperation implements UnaryOperation {

    protected final Codes jumpIfTrue(Codes codes, Predicate<Double> predicate) {
        int cursor = codes.getCursor();
        List<Double> values = codes.getParameterizedValues(2);
        boolean shouldJump = predicate.test(values.get(0));
        Integer newCursor = shouldJump ?
                values.get(1).intValue() :
                cursor + 3;
        return codes.toBuilder()
                .cursor(newCursor)
                .build();
    }
}
