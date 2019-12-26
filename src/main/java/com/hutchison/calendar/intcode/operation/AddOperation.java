package com.hutchison.calendar.intcode.operation;

import com.hutchison.calendar.intcode.Codes;

import java.util.List;

import static com.hutchison.calendar.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.intcode.operation.Operation.getValueFromCodes;
import static com.hutchison.calendar.intcode.operation.Operation.validateCursorPosition;

public class AddOperation implements Operation {

    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        List<Integer> codes = incomingCodes.getCodes();
        Integer cursor = incomingCodes.getCursor();
        validateCursorPosition(cursor + 3, codes.size());
        String opCodeString = getOpCodeString(codes.get(cursor));

        int val1 = getValueFromCodes(codes, opCodeString.charAt(0), cursor + 1);
        int val2 = getValueFromCodes(codes, opCodeString.charAt(1), cursor + 2);
        int sumIndex = codes.get(cursor + 3);

        codes.set(sumIndex, val1 + val2);

        return Codes.builder()
                .codes(codes)
                .cursor(incomingCodes.getCursor() + 4)
                .stopped(false)
                .build();
    }
}
