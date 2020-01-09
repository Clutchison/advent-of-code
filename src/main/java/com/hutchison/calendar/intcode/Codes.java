package com.hutchison.calendar.intcode;

import com.hutchison.calendar.intcode.operation.Operation;
import com.hutchison.calendar.intcode.operation.ParamMode;
import lombok.Builder;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class Codes {

    List<Double> codes;
    Integer cursor;
    boolean stopped;
    List<Double> inputs;
    List<Double> outputs;
    Integer relativeBase;

    @Builder(toBuilder = true)
    private Codes(List<Double> codes, Integer cursor, boolean stopped, List<Double> inputs, List<Double> outputs, Integer relativeBase) {
        this.codes = codes;
        this.cursor = cursor;
        this.stopped = stopped;
        this.inputs = inputs;
        this.outputs = outputs;
        this.relativeBase = relativeBase;
    }

    public List<Double> getParameterizedValues(int numberOfValues) {
        if (numberOfValues > 5) throw new RuntimeException("Parameterized values cannot exceed 5.");
        String opCodeString = getOpCodeString(cursor);
        return IntStream.range(0, numberOfValues)
                .mapToObj(i -> {
                    ParamMode paramMode = ParamMode.fromChar(
                            opCodeString.charAt(i));
                    return paramMode.getFunction()
                            .apply(this, cursor + i + 1);
                })
                .collect(Collectors.toList());
    }

    public int getParameterizedIndex(int cursorDelta) {
        ParamMode paramMode = ParamMode.fromChar(getOpCodeString(cursor).charAt(cursorDelta - 1));
        if (paramMode == ParamMode.IMMEDIATE) throw new RuntimeException("Index cannot be in Immediate mode.");
        int i = codes.get(cursor + cursorDelta).intValue() +
                (paramMode == ParamMode.RELATIVE ? relativeBase : 0);
        return i;
    }

    public Double getCode(int position) {
        if (position < 0) throw new RuntimeException("Position out of bounds.");
        fillCodesToPosition(position);
        return codes.get(position);
    }

    public void setCode(int position, double code) {
        fillCodesToPosition(position);
        codes.set(position, code);
    }

    public int size() {
        return codes.size();
    }

    public Operation getOpType() {
        Double code = codes.get(cursor);
        Operation operation = Operation.fromCode(code);
        if (operation == null) throw new RuntimeException("No OpType for code: " + code);
        return operation;
    }

    public double getLastOutput() {
        return outputs.size() > 0 ?
                outputs.get(outputs.size() - 1) :
                -1;
    }

    String getOpCodeString(int position) {
        String codeString = String.valueOf(codes.get(position));
        String sub = codeString.substring(0, codeString.indexOf("."));
        String s = StringUtils.leftPad(sub, 5, "0");
        return new StringBuilder().append(s, 0, s.length() - 2).reverse().toString();
    }

    private void fillCodesToPosition(int position) {
        if (position >= codes.size()) {
            codes.addAll(
                    IntStream.range(0, position + 1 - codes.size())
                            .mapToObj(i -> 0.0)
                            .collect(Collectors.toList()));
        }
    }

    public static class CodesBuilder {
        public Codes build() {
            if (this.codes == null) throw new RuntimeException("Codes required.");
            if (this.cursor < 0) throw new RuntimeException("Cursor can't be negative: " + cursor);
            return new Codes(this.codes,
                    this.cursor,
                    this.stopped && this.cursor < this.codes.size(),
                    this.inputs == null ? new ArrayList<>() : new ArrayList<>(this.inputs),
                    this.outputs == null ? new ArrayList<>() : new ArrayList<>(this.outputs),
                    this.relativeBase == null ? 0 : this.relativeBase);
        }
    }
}
