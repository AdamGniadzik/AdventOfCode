package org.advent.advent2016.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day7/input.txt"));
        List<String> input = new ArrayList<>();

        while (br.ready()) {
            input.add(br.readLine());
        }
        stage1(input);
        stage2(input);

    }

    public static void stage1(List<String> inputList) {
        int result = 0;
        for (String address : inputList) {

            boolean supportOutsideFound = false;
            boolean supportInsideFound = false;
            int pointer = 0;
            boolean isInside = false;
            while (pointer < address.length() - 3) {
                if (address.charAt(pointer) == '[') {
                    isInside = true;
                    pointer++;
                    continue;
                }
                if (address.charAt(pointer) == ']') {
                    isInside = false;
                    pointer++;
                    continue;
                }
                if (address.charAt(pointer) == address.charAt(pointer + 3) && address.charAt(pointer + 1) == address.charAt(pointer + 2) && address.charAt(pointer) != address.charAt(pointer + 1)) {
                    if (isInside) {
                        supportInsideFound = true;
                        break;
                    } else {
                        supportOutsideFound = true;
                    }
                }
                pointer++;
            }

            if (supportOutsideFound && !supportInsideFound) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static void stage2(List<String> inputList) {
        int result = 0;
        for (String address : inputList) {
            Set<String> matchesOutside = new HashSet<>();
            Set<String> matchesInside = new HashSet<>();
            int pointer = 0;
            boolean isInside = false;
            while (pointer < address.length() - 2) {
                if (address.charAt(pointer) == '[') {
                    isInside = true;
                    pointer++;
                    continue;
                }
                if (address.charAt(pointer) == ']') {
                    isInside = false;
                    pointer++;
                    continue;
                }
                if (address.charAt(pointer) == address.charAt(pointer + 2) && address.charAt(pointer) != address.charAt(pointer + 1)) {
                    if (isInside) {
                        matchesInside.add(address.substring(pointer, pointer + 3));
                    } else {
                        matchesOutside.add(address.substring(pointer, pointer + 3));
                    }
                }
                pointer++;
            }
            if (matchesOutside.stream()
                    .anyMatch(s -> matchesInside.contains(new String(new char[]{s.charAt(1), s.charAt(0), s.charAt(1)})))) {
                result++;
            }
        }
        System.out.println(result);
    }
}
