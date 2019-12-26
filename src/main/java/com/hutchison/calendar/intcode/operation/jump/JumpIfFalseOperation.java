package com.hutchison.calendar.intcode.operation.jump;

import com.hutchison.calendar.intcode.Codes;

public class JumpIfFalseOperation extends JumpOperation {
    /**
     * Applies this function to the given argument.
     *
     * @param codes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes codes) {
        return jumpIfTrue(codes, n -> n == 0);
    }
}
