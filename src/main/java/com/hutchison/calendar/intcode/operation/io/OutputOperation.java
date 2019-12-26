package com.hutchison.calendar.intcode.operation.io;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import static com.hutchison.calendar.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.intcode.operation.ParamMode.fromChar;

public class OutputOperation extends IOOperation {
    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        return perform(incomingCodes, () -> System.out.println(String.format("Output: %d",
                Operation.getValueFromCodes(incomingCodes.getCodes(),
                        fromChar(getOpCodeString(incomingCodes.getCodes().get(incomingCodes.getCursor())).charAt(0)),
                        incomingCodes.getCursor() + 1))));
    }
}
