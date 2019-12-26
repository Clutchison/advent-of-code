package com.hutchison.calendar.intcode.operation.jump;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.Predicate;

import static com.hutchison.calendar.intcode.operation.ParamMode.fromChar;
import static com.hutchison.calendar.intcode.operation.Operation.getValueFromCodes;
import static com.hutchison.calendar.intcode.operation.Operation.validateCursorPosition;

public abstract class JumpOperation implements Operation {

    protected final Codes jumpIfTrue(Codes incomingCodes, Predicate<Integer> predicate) {
        List<Integer> codes = incomingCodes.getCodes();
        int cursor = incomingCodes.getCursor();
        validateCursorPosition(cursor + 2, codes.size());
        String opString = Operation.getOpCodeString(codes.get(cursor));
        boolean shouldJump = predicate.test(getValueFromCodes(codes, fromChar(opString.charAt(0)), cursor + 1));
        Integer newCursor = shouldJump ?
                getValueFromCodes(codes, fromChar(opString.charAt(1)), cursor + 2) :
                cursor + 3;
        return Codes.builder()
                .codes(incomingCodes.getCodes())
                .cursor(newCursor)
                .stopped(false)
                .build();
    }

    static class JumpIfZeroPredicate implements Predicate<Integer> {

        /**
         * Evaluates this predicate on the given argument.
         *
         * @param integer the input argument
         * @return {@code true} if the input argument matches the predicate,
         * otherwise {@code false}
         */
        @Override
        public boolean test(Integer integer) {
            return integer == 0;
        }
    }
}
