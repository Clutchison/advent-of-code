package com.hutchison.calendar.intcode.operation;

import com.hutchison.calendar.intcode.operation.arithmetic.AddOperation;
import com.hutchison.calendar.intcode.operation.arithmetic.MultiplyOperation;
import com.hutchison.calendar.intcode.operation.base.AddToBaseOperation;
import com.hutchison.calendar.intcode.operation.bool.EqualsOperation;
import com.hutchison.calendar.intcode.operation.bool.LessThanOperation;
import com.hutchison.calendar.intcode.operation.io.InputOperation;
import com.hutchison.calendar.intcode.operation.io.OutputOperation;
import com.hutchison.calendar.intcode.operation.jump.JumpIfFalseOperation;
import com.hutchison.calendar.intcode.operation.jump.JumpIfTrueOperation;
import com.hutchison.calendar.intcode.operation.stop.StopOperation;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum OpType {
    ADD(1, new AddOperation()),
    MULTIPLY(2, new MultiplyOperation()),
    INPUT(3, new InputOperation()),
    OUTPUT(4, new OutputOperation()),
    JUMP_IF_TRUE(5, new JumpIfTrueOperation()),
    JUMP_IF_FALSE(6, new JumpIfFalseOperation()),
    LESS_THAN(7, new LessThanOperation()),
    EQUALS(8, new EqualsOperation()),
    ADD_TO_BASE(9, new AddToBaseOperation()),
    STOP(99, new StopOperation());

    @Getter
    private final Operation op;
    private final int code;
    private static final Map<Integer, OpType> map = new HashMap<>();

    OpType(int code, Operation op) {
        this.op = op;
        this.code = code;
    }

    static {
        Stream.of(OpType.values()).forEach(op -> map.put(op.code, op));
    }

    public static OpType fromCode(double code) {
        return map.get((int) (code % 100));
    }
}
