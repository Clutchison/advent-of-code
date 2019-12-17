package com.hutchison.calendar;


import com.hutchison.util.AocIO;
import com.hutchison.util.Console;

import java.io.File;
import java.util.Arrays;
import java.util.List;

abstract public class Day {

    private static final String PREFIX = "src\\main\\java\\com\\hutchison\\calendar\\days\\y2019";
    protected Console console = Console.getInstance();
    protected final List<String> input = getInput();

    abstract public void part1();

    abstract public void part2();

    protected final List<String> getInput() {
        File inputFile = new File(PREFIX + "/" + this.getClass().getSimpleName().toLowerCase() + "/input.txt");
        String input = AocIO.readFile(inputFile);
        return Arrays.asList(input.split("\n"));
    }
}
