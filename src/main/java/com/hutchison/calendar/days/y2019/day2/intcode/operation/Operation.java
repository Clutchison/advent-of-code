package com.hutchison.calendar.days.y2019.day2.intcode.operation;

import com.hutchison.calendar.days.y2019.day2.intcode.IntcodeComputer;
import com.hutchison.calendar.days.y2019.day2.intcode.OpType;

import java.util.List;

import static com.hutchison.calendar.days.y2019.day2.intcode.OpType.STOP;

public interface Operation {

    final List<Integer> codes;
    boolean performed;

    public void perform();


    static Operation make(List<Integer> codes, boolean performed) {
       return new  
    }

//    private void multiply() {
//        int newProduct = intcodeComputer.codes.get(position1) * intcodeComputer.codes.get(position2);
//        if (intcodeComputer.debug) System.out.println(String.format("%d[%d] * %d[%d] = %d[%d]",
//                intcodeComputer.codes.get(position1),
//                position1,
//                intcodeComputer.codes.get(position2),
//                position2,
//                newProduct,
//                endPosition));
//        intcodeComputer.codes.set(endPosition, newProduct);
//    }

}
