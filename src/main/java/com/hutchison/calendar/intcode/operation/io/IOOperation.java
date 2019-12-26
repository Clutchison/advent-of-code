package com.hutchison.calendar.intcode.operation.io;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class IOOperation implements Operation {

    protected Codes perform(Codes incomingCodes, Command command) {
        List<Integer> codes = incomingCodes.getCodes();
        Integer cursor = incomingCodes.getCursor();
        Operation.validateCursorPosition(cursor + 1, codes.size());
        command.execute();
        return Codes.builder()
                .codes(codes)
                .cursor(cursor + 2)
                .stopped(false)
                .build();
    }
}
