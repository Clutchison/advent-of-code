package com.hutchison.calendar.days.y2019.day2.intcode.operation;

import com.hutchison.calendar.days.y2019.day2.intcode.Codes;

import java.util.List;
import java.util.Scanner;

import static com.hutchison.calendar.days.y2019.day2.intcode.operation.Operation.getOpCodeString;
import static com.hutchison.calendar.days.y2019.day2.intcode.operation.Operation.getValueFromCodes;

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
        List<Integer> codes = incomingCodes.getCodes();
        Integer cursor = incomingCodes.getCursor();
        String opCodeString = getOpCodeString(codes.get(cursor), 3);

        int input = getInput();
        Integer insertIndex = getValueFromCodes(codes, opCodeString.charAt(0), cursor + 1);
        codes.set(insertIndex, input);

        return Codes.builder()
                .codes(codes)
                .cursor(cursor + 2)
                .stopped(false)
                .build();
    }

    private int getInput() {
        System.out.println("Enter an input: ");
        String input = in.nextLine();
        return Integer.parseInt(input);
    }
}
