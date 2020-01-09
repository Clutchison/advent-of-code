package com.hutchison.calendar.intcode.operation.io;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.UnaryOperation;

import java.util.List;

public class OutputOperation implements UnaryOperation {
    /**
     * Applies this function to the given argument.
     *
     * @param codes the function argument
     * @return the function result
     */
    @Override
    public Codes apply(Codes codes) {
        int cursor = codes.getCursor();
        int position = cursor + 1;
        double outputVal = codes.getParameterizedValues(1).get(0);
        System.out.println(String.format("Cursor at %d, outputting value at position %d: %.0f", cursor, position, outputVal));
        List<Double> outputs = codes.getOutputs();
        outputs.add(outputVal);
        return codes.toBuilder()
                .cursor(cursor + 2)
                .outputs(outputs)
                .build();
    }
}
