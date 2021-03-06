package com.hutchison.calendar.intcode.operation.stop;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

public class StopOperation implements Operation {
    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        System.out.println("Stop command received.");
        return incomingCodes.toBuilder()
                .stopped(true)
                .build();
    }
}
