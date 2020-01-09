package com.hutchison.calendar.intcode.operation.base;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.UnaryOperation;

public class AddToBaseOperation implements UnaryOperation {
    @Override
    public Codes apply(Codes codes) {
        int cursor = codes.getCursor();
        int baseDelta = codes.getParameterizedValues(1).get(0).intValue();
        return codes.toBuilder()
                .cursor(cursor + 2)
                .relativeBase(codes.getRelativeBase() + baseDelta)
                .build();
    }
}
