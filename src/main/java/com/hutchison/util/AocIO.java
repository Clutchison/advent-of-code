package com.hutchison.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AocIO {
    public static String readFile(File file) {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (InputStream is = new FileInputStream(file);
             BufferedReader br
                     = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
            return resultStringBuilder.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not find input file: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + e);
        }
    }
}
