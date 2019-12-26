package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.AddOperation;
import com.hutchison.calendar.intcode.operation.InputOperation;
import com.hutchison.calendar.intcode.operation.MultiplyOperation;
import com.hutchison.calendar.intcode.operation.OpType;
import com.hutchison.calendar.intcode.operation.OutputOperation;
import com.hutchison.calendar.intcode.operation.StopOperation;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

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

    Codes performNextOperation() {
        if (stopped) return this;
        switch (getOpType()) {
            case ADD:
                return new AddOperation().apply(this);
            case MULTIPLY:
                return new MultiplyOperation().apply(this);
            case INPUT:
                return new InputOperation().apply(this);
            case OUTPUT:
                return new OutputOperation().apply(this);
            case STOP:
            default:
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
            return new Codes(this.codes, this.cursor, this.stopped || this.cursor >= this.codes.size());
        }
    }
}
