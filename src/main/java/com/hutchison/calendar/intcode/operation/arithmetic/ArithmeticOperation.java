package com.hutchison.calendar.intcode.operation.arithmetic;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.hutchison.calendar.intcode.operation.Operation.*;

abstract class ArithmeticOperation implements Operation {

    static int times = 0;

    final Codes performArithmetic(Codes incomingCodes, BiFunction<Double, Double, Double> function) {
        ArithmeticOperation.times++;
        List<Double> codes = incomingCodes.getCodes();
        Integer cursor = incomingCodes.getCursor();
        validateCursorPosition(cursor + 3, codes.size());
        String opCodeString = getOpCodeString(codes.get(cursor));

        double val1 = getValueFromCodes(codes, opCodeString.charAt(0), cursor + 1);
        double val2 = getValueFromCodes(codes, opCodeString.charAt(1), cursor + 2);
        int sumIndex = codes.get(cursor + 3).intValue();

        if (sumIndex > 973) {
            System.out.println("Ya done fucked up A-A-Ron!");
        }

        if (sumIndex > codes.size()) {
            codes.addAll(
                    IntStream.range(0, sumIndex + 1 - codes.size())
                            .mapToObj(i -> 0.0)
                            .collect(Collectors.toList()));
        }
        codes.set(sumIndex, function.apply(val1, val2));

        return incomingCodes.toBuilder()
                .codes(codes)
                .cursor(cursor + 4)
                .build();
    }
}
