package com.hutchison.calendar.days.y2019.day2.intcode.operation;

import com.hutchison.calendar.days.y2019.day2.intcode.Codes;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.function.UnaryOperator;

import static com.hutchison.calendar.days.y2019.day2.intcode.operation.ParamMode.POSTIONAL;
import static com.hutchison.calendar.days.y2019.day2.intcode.operation.ParamMode.fromChar;


public interface Operation extends UnaryOperator<Codes> {

    static String getOpCodeString(int opCode, int size) {
        String s = StringUtils.leftPad(String.valueOf(opCode), size, "0");
        return new StringBuilder().append(s, 0, s.length() - 2).reverse().toString();
    }

    static Integer getValueFromCodes(List<Integer> codes, ParamMode pm, int codeIndex) {
        return pm == POSTIONAL ? codes.get() : codes.get(codeIndex);
    }

    static Integer getValueFromCodes(List<Integer> codes, char opChar, int codeIndex) {
        return getValueFromCodes(codes, fromChar(opChar), codeIndex);
    }
}
