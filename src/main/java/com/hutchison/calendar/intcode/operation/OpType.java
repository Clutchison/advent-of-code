package com.hutchison.calendar.intcode.operation;

import com.hutchison.calendar.intcode.Codes;
import com.hutchison.calendar.intcode.operation.arithmetic.AddOperation;
import com.hutchison.calendar.intcode.operation.arithmetic.MultiplyOperation;
import com.hutchison.calendar.intcode.operation.bool.EqualsOperation;
import com.hutchison.calendar.intcode.operation.bool.LessThanOperation;
import com.hutchison.calendar.intcode.operation.io.InputOperation;
import com.hutchison.calendar.intcode.operation.io.OutputOperation;
import com.hutchison.calendar.intcode.operation.jump.JumpIfFalseOperation;
import com.hutchison.calendar.intcode.operation.jump.JumpIfTrueOperation;
import com.hutchison.calendar.intcode.operation.stop.StopOperation;

public enum OpType {
    ADD(new AddOperation()),
    MULTIPLY(new MultiplyOperation()),
    INPUT(new InputOperation()),
    OUTPUT(new OutputOperation()),
    JUMP_IF_TRUE(new JumpIfTrueOperation()),
    JUMP_IF_FALSE(new JumpIfFalseOperation()),
    LESS_THAN(new LessThanOperation()),
    EQUALS(new EqualsOperation()),
    STOP(new StopOperation());

    private final Operation op;

    OpType(Operation op) {
        this.op = op;
    }

    public void apply(Codes codes) {
        this.op.apply(codes);
    }

    public static OpType fromCode(int code) {
        switch (code % 100) {
            case 1:
                return ADD;
            case 2:
                return MULTIPLY;
            case 3:
                return INPUT;
            case 4:
                return OUTPUT;
            case 5:
                return JUMP_IF_TRUE;
            case 6:
                return JUMP_IF_FALSE;
            case 7:
                return LESS_THAN;
            case 8:
                return EQUALS;
            case 99:
                return STOP;
            default:
                throw new RuntimeException("Invalid opcode: " + code);
        }
    }
}
