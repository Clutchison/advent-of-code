package com.hutchison.calendar.intcode.operation.base;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;
import com.hutchison.calendar.intcode.operation.ParamMode;

import java.util.List;

import static com.hutchison.calendar.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.intcode.operation.Operation.validateCursorPosition;

public class AddToBaseOperation implements Operation {
    @Override
    public Codes apply(Codes incomingCodes) {
        int cursor = incomingCodes.getCursor();
        List<Double> codes = incomingCodes.getCodes();
        validateCursorPosition(cursor + 1, codes.size());
        String opCodeString = getOpCodeString(codes.get(cursor));
        int position = cursor + 1;
        double baseDelta = Operation.getValueFromCodes(codes, ParamMode.fromChar(opCodeString.charAt(0)), position);
        return incomingCodes.toBuilder()
                .cursor(cursor + 2)
                .relativeBase(incomingCodes.getRelativeBase() + baseDelta)
                .build();
    }
}
