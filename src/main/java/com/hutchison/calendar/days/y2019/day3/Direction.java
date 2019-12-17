package com.hutchison.calendar.days.y2019.day3;

public enum Direction {
    U, D, L, R;

    public static Direction fromChar(char c) {
        switch (c) {
            case 'U':
                return U;
            case 'D':
                return D;
            case 'L':
                return L;
            case 'R':
                return R;
            default:
                throw new RuntimeException("Invalid Direction char: " + c);
        }
    }

    public boolean isVertical() {
        return this == U || this == D;
    }
}
