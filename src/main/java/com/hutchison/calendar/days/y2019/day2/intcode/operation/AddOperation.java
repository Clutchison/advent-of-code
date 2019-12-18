package com.hutchison.calendar.days.y2019.day2.intcode.operation;

public class AddOperation {







    private void add() {
        int newSum = intcodeComputer.codes.get(position1) + intcodeComputer.codes.get(position2);
        if (intcodeComputer.debug) System.out.println(String.format("%d[%d] + %d[%d] = %d[%d]",
                intcodeComputer.codes.get(position1),
                position1,
                intcodeComputer.codes.get(position2),
                position2,
                newSum,
                endPosition));
        intcodeComputer.codes.set(endPosition, newSum);
    }
}
