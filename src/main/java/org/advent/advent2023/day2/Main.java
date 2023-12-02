package org.advent.advent2023.day2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int sumOfIds = 0;
        int sumOfPowers = 0;
        try {
            InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2023/day2/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Map<String, Integer> gameColours = Map.of("red", 12, "green", 13, "blue", 14);
            while (br.ready()) {
                Map<String, Integer> minimumRequired = new HashMap<>();
                String line = br.readLine();
                int idOfGame = Integer.parseInt(line.substring(5, line.indexOf(":")));
                boolean isCorrectGame = true;

                String[] rounds = line.substring(line.indexOf(": ") + 2).replace(",", "").split("; ");

                for (String round : rounds) {
                    String[] words = round.split(" ");
                    for (int i = 0; i < words.length; i = i + 2) {
                        int count = Integer.parseInt(words[i]);
                        String colour = words[i + 1];
                        if (minimumRequired.getOrDefault(colour, Integer.MIN_VALUE) < count) {
                            minimumRequired.put(colour, count);
                        }
                        if (gameColours.get(colour) < count) {
                            isCorrectGame = false;
                            // break (can be used in stage 1, but have to comment it in stage 2)
                        }
                    }
                }
                if (isCorrectGame) {
                    sumOfIds += idOfGame;
                }

                int power = 1;
                for (Integer value : minimumRequired.values()) {
                    power *= value;
                }
                sumOfPowers += power;
            }
            System.out.println(sumOfIds);
            System.out.println(sumOfPowers);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
