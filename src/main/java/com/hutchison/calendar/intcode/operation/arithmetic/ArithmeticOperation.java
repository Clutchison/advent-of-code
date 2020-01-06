package com.hutchison.calendar.intcode.operation.arithmetic;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.BiFunction;

abstract class ArithmeticOperation implements Operation {


    final Codes performArithmetic(Codes codes, BiFunction<Double, Double, Double> function) {
        Integer cursor = codes.getCursor();
        List<Double> values = codes.getParameterizedValues(2);
        int parameterizedIndex = codes.getParameterizedIndex(3);
        codes.setCode(parameterizedIndex, function.apply(values.get(0), values.get(1)));
        return codes.toBuilder()
                .cursor(cursor + 4)
                .build();
    }
}
