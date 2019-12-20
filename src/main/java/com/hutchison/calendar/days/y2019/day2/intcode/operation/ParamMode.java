package com.hutchison.calendar.days.y2019.day2.intcode.operation;

public enum ParamMode {
   POSTIONAL, IMMEDIATE;

   public static ParamMode fromChar(char c) {
      switch (c) {
         case '0':
            return POSTIONAL;
         case '1':
            return IMMEDIATE;
         default:
            throw new RuntimeException(c + " does not map to a ParamMode");
      }
   }
}
