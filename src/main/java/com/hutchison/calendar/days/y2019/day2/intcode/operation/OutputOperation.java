package com.hutchison.calendar.days.y2019.day2.intcode.operation;

import com.hutchison.calendar.days.y2019.day2.intcode.Codes;

import java.util.List;

import static com.hutchison.calendar.days.y2019.day2.intcode.operation.Operation.getOpCodeString;

public class OutputOperation implements Operation {
    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        int cursor = incomingCodes.getCursor();
        List<Integer> codes = incomingCodes.getCodes();
        Operation.validateCursorPosition(cursor + 1, codes.size());
        String opCodeString = getOpCodeString(codes.get(cursor));
        int position = cursor + 1;
        int outputVal = Operation.getValueFromCodes(codes, ParamMode.fromChar(opCodeString.charAt(0)), position);
        System.out.println(String.format("Cursor at %d, outputting value at position %d: %d", cursor, position, outputVal));
        return Codes.builder()
                .codes(codes)
                .cursor(cursor + 2)
                .stopped(false)
                .build();
    }
}
