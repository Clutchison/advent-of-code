package com.hutchison.calendar.intcode.operation.bool;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.BiPredicate;

public abstract class BooleanOperation implements Operation {

    protected final Codes storeBoolean(Codes codes, BiPredicate<Double, Double> predicate) {
        List<Double> values = codes.getParameterizedValues(3);
        double valToStore = predicate.test(values.get(0), values.get(1)) ? 1 : 0;
        codes.setCode(values.get(2).intValue(), valToStore);
        return codes.toBuilder()
                .cursor(codes.getCursor() + 4)
                .build();
    }
}
