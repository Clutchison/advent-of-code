package com.hutchison.calendar.days.y2019.day2.intcode.operation;

import com.hutchison.calendar.days.y2019.day2.intcode.Codes;

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
        return Codes.builder()
                .codes(incomingCodes.getCodes())
                .cursor(incomingCodes.getCursor())
                .stopped(true)
                .build();
    }
}
