package com.hutchison.calendar.intcode.operation.jump;

import com.hutchison.calendar.intcode.Codes;

public class JumpIfTrueOperation extends JumpOperation {
    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        return jumpIfTrue(incomingCodes, n -> n != 0);
    }


}
