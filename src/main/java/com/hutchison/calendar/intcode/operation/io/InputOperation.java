package com.hutchison.calendar.intcode.operation.io;

import com.hutchison.calendar.intcode.Codes;

import java.util.Scanner;

public class InputOperation extends IOOperation {

    private final Scanner in = new Scanner(System.in);

    /**
     * Applies this function to the given argument.
     *
     * @param incomingCodes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes incomingCodes) {
        return perform(incomingCodes,
                () -> incomingCodes.getCodes()
                        .set(incomingCodes.getCodes().get(incomingCodes.getCursor() + 1), getInput()));
    }

    private int getInput() {
        System.out.println("Enter an input: ");
        String input = in.nextLine();
        return Integer.parseInt(input);
    }
}
