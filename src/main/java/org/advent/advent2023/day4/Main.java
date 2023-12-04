package org.advent.advent2023.day4;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static Map<Integer, Integer> scratchCards = new HashMap<>();
    public static int sumOfProcessedScratchCards = 0;

    public static void main(String[] args) {
        int sum = 0;

        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day4/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int counter = 1;
            while (br.ready()) {
                String line = br.readLine();
                String parts[] = line.substring(line.indexOf(": ") + ": ".length()).split(" \\| ");
                Set<Integer> winning = Arrays.stream(parts[0].replaceAll("^\\s+", "").split("\\s+"))
                        .map(Integer::parseInt).collect(Collectors.toSet());
                long myNumbers = Arrays.stream(parts[1].replaceAll("^\\s+", "").split("\\s+")).map(Integer::parseInt)
                        .filter(winning::contains).count();
                sum += Math.pow(2, myNumbers - 1);
                scratchCards.put(counter, (int) myNumbers);
                counter++;
            }

            for (Integer key : scratchCards.keySet()) {
                processScratchCard(key);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println(sumOfProcessedScratchCards);
    }

    public static void processScratchCard(int id) {
        sumOfProcessedScratchCards++;
        int cardToTake = scratchCards.get(id);
        for (int i = 1; i <= cardToTake; i++) {
            processScratchCard(id + i);
        }
    }

}
