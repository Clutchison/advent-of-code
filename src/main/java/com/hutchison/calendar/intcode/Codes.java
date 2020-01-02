package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.OpType;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class Codes {

    List<Double> codes;
    Integer cursor;
    boolean stopped;
    List<Double> inputs;
    List<Double> outputs;

    @Builder(toBuilder = true)
    private Codes(List<Double> codes, Integer cursor, boolean stopped, List<Double> inputs, List<Double> outputs) {
        this.codes = codes;
        this.cursor = cursor;
        this.stopped = stopped;
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public List<Double> getCodes() {
        return new ArrayList<>(codes);
    }

    public double getCode(int position) {
        if (position < 0 || position >= codes.size()) throw new RuntimeException("Position out of bounds.");
        return codes.get(position);
    }

    public OpType getOpType() {
        Double code = codes.get(cursor);
        OpType opType = OpType.fromCode(code);
        if (opType == null) throw new RuntimeException("No OpType for code: " + code);
        return opType;
    }

    public double getLastOutput() {
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
