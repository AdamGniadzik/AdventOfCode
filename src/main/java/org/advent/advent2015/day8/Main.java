package org.advent.advent2015.day8;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day8/input.txt"));
        int stage1 = 0;
        int stage2 = 0;
        while (br.ready()) {
            String input = br.readLine();
            stage1 += input.length() - deleteEscape(input).length();
            stage2 += addEscape(input).length() - input.length();
        }
        System.out.println(stage1);
        System.out.println(stage2);


    }

    static String addEscape(String input) {
        return  "\"" + input.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"") + "\"";
    }

    static String deleteEscape(String input) {
        StringBuilder str = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] == '\\') {
                if (chars[i + 1] == '"') {
                    str.append('"');
                    i++;
                } else if (chars[i + 1] == 'x') {
                    str.append((char) Integer.parseInt(new String(new char[]{chars[i + 2], chars[i + 3]}), 16));
                    i += 3;
                } else if (chars[i + 1] == '\\') {
                    str.append('\\');
                    i++;
                }
            } else {
                str.append(chars[i]);
            }
        }
        return str.toString();
    }
}

