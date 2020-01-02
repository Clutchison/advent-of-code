package com.hutchison.calendar.intcode.operation.bool;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.BiPredicate;

import static com.hutchison.calendar.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.intcode.operation.Operation.getValueFromCodes;
import static com.hutchison.calendar.intcode.operation.Operation.validateCursorPosition;
import static com.hutchison.calendar.intcode.operation.ParamMode.fromChar;

public abstract class BooleanOperation implements Operation {

    protected final Codes storeBoolean(Codes incomingCodes, BiPredicate<Double, Double> predicate) {
        List<Double> codes = incomingCodes.getCodes();
        int cursor = incomingCodes.getCursor();
        validateCursorPosition(cursor + 3, codes.size());
        String opString = getOpCodeString(codes.get(cursor));
        double val1 = getValueFromCodes(codes, fromChar(opString.charAt(0)), cursor + 1);
        double val2 = getValueFromCodes(codes, fromChar(opString.charAt(1)), cursor + 2);
        double valToStore = predicate.test(val1, val2) ? 1 : 0;
        codes.set(codes.get(cursor + 3).intValue(), valToStore);

        return incomingCodes.toBuilder()
                .codes(codes)
                .cursor(cursor + 4)
                .build();
    }
}
