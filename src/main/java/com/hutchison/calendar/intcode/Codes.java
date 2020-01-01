package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.OpType;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class Codes {

    final List<Integer> codes;
    Integer cursor;
    boolean stopped;
    List<Integer> inputs;
    List<Integer> outputs;

    @Builder(toBuilder = true)
    private Codes(List<Integer> codes, Integer cursor, boolean stopped, List<Integer> inputs, List<Integer> outputs) {
        this.codes = codes;
        this.cursor = cursor;
        this.stopped = stopped;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<Integer> getCodes() {
        return new ArrayList<>(codes);
    }

    public int getCode(int position) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        return codes.get(position);
    }

    public OpType getOpType() {
        return OpType.fromCode(codes.get(cursor));
    }

    void setCode(int position, int code) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        codes.set(position, code);
    }

    public int getLastOutput() {
        return outputs.size() > 0 ?
                outputs.get(outputs.size() - 1) :
                -1;
    }

    public static class CodesBuilder {
        public Codes build() {
            if (this.codes == null) throw new RuntimeException("Codes required.");
            if (this.cursor < 0) throw new RuntimeException("Cursor can't be negative: " + cursor);
            return new Codes(this.codes,
                    this.cursor,
                    this.stopped && this.cursor < this.codes.size(),
                    this.inputs == null ? new ArrayList<>() : new ArrayList<>(this.inputs),
                    this.outputs == null ? new ArrayList<>() : new ArrayList<>(this.outputs));
        }
    }
}
