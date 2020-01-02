package com.hutchison.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    private static Console console = null;

    private BufferedReader reader;

    private Console() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static Console getInstance() {
        if (console == null) console = new Console();
        return console;
    }

    public String request(String question) {
        System.out.println(question);
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void print(String s) {
        System.out.println(s);
    }

    public void print(long n) {
        print(String.valueOf(n));
    }

    public void print(double d) {
        print(String.valueOf(d));
    }
}
