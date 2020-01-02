package com.hutchison.calendar.intcode.operation.jump;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.Predicate;

import static com.hutchison.calendar.intcode.operation.Operation.getValueFromCodes;
import static com.hutchison.calendar.intcode.operation.Operation.validateCursorPosition;
import static com.hutchison.calendar.intcode.operation.ParamMode.fromChar;

public abstract class JumpOperation implements Operation {

    protected final Codes jumpIfTrue(Codes incomingCodes, Predicate<Double> predicate) {
        List<Double> codes = incomingCodes.getCodes();
        int cursor = incomingCodes.getCursor();
        validateCursorPosition(cursor + 2, codes.size());
        String opString = Operation.getOpCodeString(codes.get(cursor));
        boolean shouldJump = predicate.test(getValueFromCodes(codes, fromChar(opString.charAt(0)), cursor + 1));
        Integer newCursor = shouldJump ?
                getValueFromCodes(codes, fromChar(opString.charAt(1)), cursor + 2).intValue() :
                cursor + 3;
        return incomingCodes.toBuilder()
                .cursor(newCursor)
                .build();
    }
}
