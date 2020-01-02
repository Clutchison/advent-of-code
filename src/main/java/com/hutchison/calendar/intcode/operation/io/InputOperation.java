package com.hutchison.calendar.intcode.operation.io;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOperation implements Operation {

    private final Scanner in = new Scanner(System.in);

    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        List<Double> inputs = new ArrayList<>(incomingCodes.getInputs());
        List<Double> codes = incomingCodes.getCodes();
        Integer cursor = incomingCodes.getCursor();
        Operation.validateCursorPosition(cursor + 1, codes.size());

        double input;
        if (inputs.size() == 0) {
            input = getInput();
        } else {
            input = inputs.get(0);
            inputs.remove(0);
        }
        codes.set(codes.get(cursor + 1).intValue(), input);

        return incomingCodes.toBuilder()
                .codes(codes)
                .cursor(cursor + 2)
                .inputs(inputs)
                .build();
    }

    private int getInput() {
        System.out.println("Enter an input: ");
        String input = in.nextLine();
        return Integer.parseInt(input);
    }
}
