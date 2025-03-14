package org.advent.advent2016.day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    static Queue<Integer> candidates = new LinkedList<>();
    static Map<Integer, String> cache = new HashMap<>();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day14/input.txt"));
        String input = "";
        while (br.ready()) {
            input = br.readLine();
        }
        stage1(input);
        cache = new HashMap<>();
        candidates = new LinkedList<>();
        stage2(input);
    }

    public static void stage2(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        int counter = 1;
        int resultCounter = 0;
        while (true) {
            String result = toHex(input + counter, md);
            if (cache.containsKey(counter)) {
                result = cache.get(counter);
            } else {
                for (int i = 0; i < 2016; i++) {
                    result = toHex(result, md);
                }
                cache.put(counter, result);
            }
            Character charResult = firstCheck(result);
            if (charResult != null && checkFirst1000(input, counter, charResult, true)) {
                resultCounter++;
                if (resultCounter == 64) {
                    break;
                }
            }
            if (!candidates.isEmpty()) {
                int newCandidate = candidates.poll();
                if (newCandidate > counter + 1) {
                    counter = newCandidate;
                } else {
                    counter++;
                }
            } else {
                counter++;
            }
        }
        System.out.println(counter);
    }

    public static void stage1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        int counter = 1;
        int resultCounter = 0;
        while (true) {
            String result = cache.getOrDefault(counter, toHex(input + counter, md));
            cache.put(counter, result);
            Character charResult = firstCheck(result);
            if (charResult != null && checkFirst1000(input, counter, charResult, false)) {
                resultCounter++;
                if (resultCounter == 64) {
                    System.out.println(counter);
                    break;
                }
            }
            if (!candidates.isEmpty()) {
                int newCandidate = candidates.poll();
                if (newCandidate > counter + 1) {
                    counter = newCandidate;
                } else {
                    counter++;
                }
            } else {
                counter++;
            }
        }
        System.out.println(counter);
    }

    public static boolean checkFirst1000(String input, int index, char desired, boolean stage2) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (int i = index + 1; i <= index + 1000; i++) {
            String result = toHex(input + i, md);
            if (cache.containsKey(i)) {
                result = cache.get(i);
            } else {
                if (stage2) {
                    for (int k = 0; k < 2016; k++) {
                        result = toHex(result, md);
                    }
                }
                cache.put(i, result);
            }
            if (secondCheck(result, desired, i)) {
                return true;
            }
        }
        return false;
    }

    public static String toHex(String input, MessageDigest md) {
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }

    public static Character firstCheck(String input) {
        char current = input.charAt(0);
        int counter = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == current) {
                counter++;
                if (counter == 3) {
                    return input.charAt(i);
                }
            } else {
                counter = 1;
                current = input.charAt(i);
            }
        }
        return null;
    }

    public static boolean secondCheck(String input, char desired, int currentPasswordIndex) {
        char current = input.charAt(0);
        int counter = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == current) {
                counter++;
                if (counter >= 3) {
                    candidates.add(currentPasswordIndex);
                }
                if (counter == 5 && current == desired) {
                    return true;
                }
            } else {
                counter = 1;
                current = input.charAt(i);
            }
        }
        return false;
    }
}
