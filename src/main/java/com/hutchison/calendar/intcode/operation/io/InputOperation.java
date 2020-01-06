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
     * @param codes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes codes) {
        List<Double> inputs = new ArrayList<>(codes.getInputs());
        Integer cursor = codes.getCursor();

        double input;
        if (inputs.size() == 0) {
            input = getInput();
        } else {
            input = inputs.get(0);
            inputs.remove(0);
        }
        int writeIndex = codes.getParameterizedIndex(1);
        codes.setCode(writeIndex, input);

        return codes.toBuilder()
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
