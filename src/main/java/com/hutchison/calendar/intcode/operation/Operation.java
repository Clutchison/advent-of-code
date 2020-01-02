package com.hutchison.calendar.intcode.operation;

import com.hutchison.calendar.intcode.Codes;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.UnaryOperator;

import static com.hutchison.calendar.intcode.operation.ParamMode.POSITIONAL;
import static com.hutchison.calendar.intcode.operation.ParamMode.fromChar;


public interface Operation extends UnaryOperator<Codes> {

    static String getOpCodeString(double opCode) {
        return getOpCodeString(opCode, 5);
    }

    static String getOpCodeString(double opCode, int size) {
        String sub = String.valueOf(opCode).substring(0, String.valueOf(opCode).indexOf("."));
        String s = StringUtils.leftPad(sub, size, "0");
        return new StringBuilder().append(s, 0, s.length() - 2).reverse().toString();
    }

    static Double getValueFromCodes(List<Double> codes, ParamMode pm, int codeIndex) {
        return pm == POSITIONAL ? codes.get(codes.get(codeIndex).intValue()) : codes.get(codeIndex);
    }

    static Double getValueFromCodes(List<Double> codes, char opChar, int codeIndex) {
        return getValueFromCodes(codes, fromChar(opChar), codeIndex);
    }

    static void validateCursorPosition(int maxIndex, int codesSize) {
        if (maxIndex >= codesSize)
            throw new RuntimeException("Codes do not have enough remaining values to perform operation.");
    }
}
