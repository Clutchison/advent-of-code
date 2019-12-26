package com.hutchison.calendar.intcode.operation;

public enum ParamMode {
   POSITIONAL, IMMEDIATE;

   public static ParamMode fromChar(char c) {
      switch (c) {
         case '0':
            return POSITIONAL;
         case '1':
            return IMMEDIATE;
         default:
            throw new RuntimeException(c + " does not map to a ParamMode");
      }
   }
}