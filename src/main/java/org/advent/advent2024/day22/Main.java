package org.advent.advent2024.day22;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2024/day22/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<Long> input = new ArrayList<>();
        Map<String, Integer> monkeyPrices = new HashMap<>();
        while (br.ready()) {
            input.add(Long.parseLong(br.readLine()));
        }
        List<Long> secrets = new ArrayList<>(input);
        Long output = 0L;
        for (Long rawSecret : secrets) {
            Map<String, Integer> monkeyCache = new HashMap<>();
            LinkedList<Integer> changes = new LinkedList<>();
            int currentChange = 0;
            Long secret = rawSecret;
            for (int i = 0; i < 4; i++) {
                int oldChange = (int) (secret % 10);

                secret = calculateNewSecret(secret);
                currentChange = (int) (secret % 10) - oldChange;
                changes.add(currentChange);
            }
            for (int i = 0; i < 1996; i++) {
                String key = getKey(changes);
                monkeyCache.putIfAbsent(key, (int) (secret % 10));

                int oldChange = (int) (secret % 10);
                secret = calculateNewSecret(secret);
                currentChange = (int) (secret % 10) - oldChange;

                changes.poll();
                changes.add(currentChange);
            }
            for (Map.Entry<String, Integer> entry : monkeyCache.entrySet()) {
                monkeyPrices.put(entry.getKey(), monkeyPrices.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
            output += secret;
        }
        System.out.println(monkeyPrices.values().stream().sorted(Comparator.reverseOrder()).findFirst().get());
        System.out.println(output);
    }

    static String getKey(LinkedList<Integer> changes) {
        StringBuilder str = new StringBuilder();
        for (int change : changes) {
            str.append(change);
        }
        return str.toString();
    }

    static Long calculateNewSecret(Long secret) {
        long mix = secret * 64;
        secret = (mix ^ secret) % 16777216;
        mix = secret / 32;
        secret = (mix ^ secret) % 16777216;
        mix = secret * 2048;
        return (mix ^ secret) % 16777216;
    }
}