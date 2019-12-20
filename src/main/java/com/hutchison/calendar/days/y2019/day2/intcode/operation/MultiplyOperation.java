package com.hutchison.calendar.days.y2019.day2.intcode.operation;

import com.hutchison.calendar.days.y2019.day2.intcode.Codes;

import java.util.List;

import static com.hutchison.calendar.days.y2019.day2.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.days.y2019.day2.intcode.operation.Operation.getValueFromCodes;

public class MultiplyOperation implements Operation{

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
        if (cursor + 3 < codes.size())
            throw new RuntimeException("Codes do not have enough remaining values to perform add operation.");
        String opCodeString = getOpCodeString(codes.get(cursor), 5);

        int val1 = getValueFromCodes(codes, opCodeString.charAt(0), cursor + 1);
        int val2 = getValueFromCodes(codes, opCodeString.charAt(1), cursor + 2);
        int sumIndex = getValueFromCodes(codes, opCodeString.charAt(2), cursor + 3);

        codes.set(sumIndex, val1 * val2);

        return Codes.builder()
                .codes(codes)
                .cursor(incomingCodes.getCursor() + 4)
                .stopped(false)
                .build();
    }
}
