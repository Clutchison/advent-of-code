package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.arithmetic.AddOperation;
import com.hutchison.calendar.intcode.operation.bool.EqualsOperation;
import com.hutchison.calendar.intcode.operation.io.InputOperation;
import com.hutchison.calendar.intcode.operation.jump.JumpIfFalseOperation;
import com.hutchison.calendar.intcode.operation.jump.JumpIfTrueOperation;
import com.hutchison.calendar.intcode.operation.bool.LessThanOperation;
import com.hutchison.calendar.intcode.operation.arithmetic.MultiplyOperation;
import com.hutchison.calendar.intcode.operation.OpType;
import com.hutchison.calendar.intcode.operation.io.OutputOperation;
import com.hutchison.calendar.intcode.operation.StopOperation;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

import static com.hutchison.calendar.intcode.operation.OpType.ADD;
import static com.hutchison.calendar.intcode.operation.OpType.EQUALS;
import static com.hutchison.calendar.intcode.operation.OpType.INPUT;
import static com.hutchison.calendar.intcode.operation.OpType.JUMP_IF_FALSE;
import static com.hutchison.calendar.intcode.operation.OpType.JUMP_IF_TRUE;
import static com.hutchison.calendar.intcode.operation.OpType.LESS_THAN;
import static com.hutchison.calendar.intcode.operation.OpType.MULTIPLY;
import static com.hutchison.calendar.intcode.operation.OpType.OUTPUT;
import static com.hutchison.calendar.intcode.operation.OpType.STOP;

@Value
public class Codes {

    private final List<Integer> codes;
    private Integer cursor;
    private boolean stopped;

    @Builder
    private Codes(List<Integer> codes, Integer cursor, boolean stopped) {
        this.codes = codes;
        this.cursor = cursor;
        this.stopped = stopped;
    }

    public List<Integer> getCodes() {
        return new ArrayList<>(codes);
    }

    public int getCode(int position) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        return codes.get(position);
    }

    void setCode(int position, int code) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        codes.set(position, code);
    }

    private void printOperation(OpType type, int cursor) {
        System.out.println(String.format("Performing %s at cursor: %d", type, cursor));
    }


    Codes performNextOperation() {
        if (stopped) return this;
        switch (getOpType()) {
            case ADD:
                printOperation(ADD, cursor);
                return new AddOperation().apply(this);
            case MULTIPLY:
                printOperation(MULTIPLY, cursor);
                return new MultiplyOperation().apply(this);
            case INPUT:
                printOperation(INPUT, cursor);
                return new InputOperation().apply(this);
            case OUTPUT:
                printOperation(OUTPUT, cursor);
                return new OutputOperation().apply(this);
            case JUMP_IF_TRUE:
                printOperation(JUMP_IF_TRUE, cursor);
                return new JumpIfTrueOperation().apply(this);
            case JUMP_IF_FALSE:
                printOperation(JUMP_IF_FALSE, cursor);
                return new JumpIfFalseOperation().apply(this);
            case LESS_THAN:
                printOperation(LESS_THAN, cursor);
                return new LessThanOperation().apply(this);
            case EQUALS:
                printOperation(EQUALS, cursor);
                return new EqualsOperation().apply(this);
            case STOP:
            default:
                printOperation(STOP, cursor);
                return new StopOperation().apply(this);
        }
    }

    private OpType getOpType() {
        return OpType.fromCode(codes.get(cursor));
    }

    public static class CodesBuilder {
        public Codes build() {
            if (this.codes == null) throw new RuntimeException("Codes required.");
            if (this.cursor < 0) throw new RuntimeException("Cursor can't be negative: " + cursor);
            return new Codes(this.codes, this.cursor, this.stopped && this.cursor < this.codes.size());
        }
    }
}
