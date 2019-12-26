package com.hutchison.calendar.intcode.operation.arithmetic;

import com.hutchison.calendar.intcode.Codes;

public class MultiplyOperation extends ArithmeticOperation {

    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        return performArithmetic(incomingCodes, (n1, n2) -> n1 * n2);
    }
}
