package com.hutchison.calendar.intcode.operation.bool;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.bool.BooleanOperation;

public class LessThanOperation extends BooleanOperation {
    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        return storeBoolean(incomingCodes, (n1, n2) -> n1 < n2);
    }
}
