package com.hutchison.day;

import com.hutchison.util.AocIO;
import com.hutchison.util.Console;

import java.io.File;
import java.util.Arrays;
import java.util.List;

abstract public class Day {

    private static final String PREFIX = "C:\\Users\\sean.hutchison\\Projects\\IntelliJ\\personal\\advent-of-code\\src\\main\\java\\com\\hutchison\\day";
    protected Console console = Console.getInstance();

    abstract public void part1();

    abstract public void part2();

    public final List<String> getInput(String suffix) {
        String input = AocIO.readFile(new File(PREFIX + "/" + suffix));
        return Arrays.asList(input.split("\n"));
    }
}
