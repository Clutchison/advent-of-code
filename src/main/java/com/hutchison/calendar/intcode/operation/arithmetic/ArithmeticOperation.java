package com.hutchison.calendar.intcode.operation.arithmetic;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.BiFunction;

import static com.hutchison.calendar.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.intcode.operation.Operation.getValueFromCodes;
import static com.hutchison.calendar.intcode.operation.Operation.validateCursorPosition;

public abstract class ArithmeticOperation implements Operation {

    protected final Codes performArithmetic(Codes incomingCodes, BiFunction<Integer, Integer, Integer> function) {
        List<Integer> codes = incomingCodes.getCodes();
        Integer cursor = incomingCodes.getCursor();
        validateCursorPosition(cursor + 3, codes.size());
        String opCodeString = getOpCodeString(codes.get(cursor));

        int val1 = getValueFromCodes(codes, opCodeString.charAt(0), cursor + 1);
        int val2 = getValueFromCodes(codes, opCodeString.charAt(1), cursor + 2);
        int sumIndex = codes.get(cursor + 3);

        codes.set(sumIndex, function.apply(val1, val2));

        return incomingCodes.toBuilder()
                .codes(codes)
                .cursor(cursor + 4)
                .build();
    }
}
