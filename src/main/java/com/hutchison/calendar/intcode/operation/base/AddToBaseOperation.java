package com.hutchison.calendar.intcode.operation.base;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

public class AddToBaseOperation implements Operation {
    @Override
    public Codes apply(Codes codes) {
        int cursor = codes.getCursor();
        int position = cursor + 1;
        double baseDelta = codes.getParameterizedValues(1).get(0);
        return codes.toBuilder()
                .cursor(cursor + 2)
                .relativeBase(codes.getRelativeBase() + baseDelta)
                .build();
    }
}
