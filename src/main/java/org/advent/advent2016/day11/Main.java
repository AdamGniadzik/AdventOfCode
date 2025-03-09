package org.advent.advent2016.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int moves = 0;
    static int currentElevatorLevel = 0;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        List<Map<String, Integer>> floorsStage1 = new ArrayList<>();
        floorsStage1.add(new HashMap<>(Map.of("Strontium", 1, "Thulium", 2, "Plutionium", 1)));
        floorsStage1.add(new HashMap<>(Map.of("Strontium", 1, "Plutionium", 1)));
        floorsStage1.add(new HashMap<>(Map.of("Promethium", 2, "Ruthenium", 2)));
        floorsStage1.add(new HashMap<>(Map.of()));

        List<Map<String, Integer>> floorsStage2 = new ArrayList<>();
        floorsStage2.add(new HashMap<>(Map.of("Strontium", 1, "Thulium", 2, "Plutionium", 1, "Elerium", 2, "Dilithium", 2)));
        floorsStage2.add(new HashMap<>(Map.of("Strontium", 1, "Plutionium", 1)));
        floorsStage2.add(new HashMap<>(Map.of("Promethium", 2, "Ruthenium", 2)));
        floorsStage2.add(new HashMap<>(Map.of()));
        while (true) {
            try {
                doOptionalMove(floorsStage1);
            } catch (Exception e) {
                System.out.println(moves);
                break;
            }
        }
        moves=0;
        currentElevatorLevel=0;
        while (true) {
            try {
                doOptionalMove(floorsStage2);
            } catch (Exception e) {
                System.out.println(moves);
                break;
            }
        }


    }

    public static void doOptionalMove(List<Map<String, Integer>> floors) {
        Optional<Map.Entry<String, Integer>> optimalPair = floors.get(currentElevatorLevel).entrySet().stream()
                .filter(e -> e.getValue() == 2).findFirst();
        if (optimalPair.isPresent() && (currentElevatorLevel == 0 || floors.get(currentElevatorLevel - 1).isEmpty())) {
            floors.get(currentElevatorLevel).remove(optimalPair.get().getKey());
            floors.get(currentElevatorLevel + 1).put(optimalPair.get().getKey(), 2);
            currentElevatorLevel++;
            moves++;
        } else if (currentElevatorLevel > 0 && floors.get(0).size() > 0 && floors.get(currentElevatorLevel).entrySet()
                .stream().anyMatch(e -> floors.get(currentElevatorLevel - 1).containsKey(e.getKey()))) {
            floors.get(currentElevatorLevel).entrySet().stream()
                    .filter(e -> floors.get(currentElevatorLevel - 1).keySet().contains(e.getKey())).findFirst()
                    .ifPresent(move -> {
                        floors.get(currentElevatorLevel - 1).remove(move.getKey());
                        floors.get(currentElevatorLevel).put(move.getKey(), 2);
                        moves++;
                        moves++;
                    });
        } else if (currentElevatorLevel == 0 && floors.get(0).size() == 0) {
            System.out.println("SOMETHING WENT WRONG");
        } else {
            Optional<String> moveOne = floors.get(currentElevatorLevel).entrySet().stream()
                    .filter(e -> e.getValue() == 1).map(e -> e.getKey()).findFirst();
            Optional<String> moveTwo = floors.get(currentElevatorLevel).entrySet().stream()
                    .filter(e -> e.getValue() == 2).map(e -> e.getKey()).findFirst();
            if (moveOne.isPresent()) {
                floors.get(currentElevatorLevel).remove(moveOne.get());
                floors.get(currentElevatorLevel - 1)
                        .put(moveOne.get(), floors.get(currentElevatorLevel - 1).getOrDefault(moveOne.get(), 0) + 1);
                currentElevatorLevel--;
                moves++;
            } else if (moveTwo.isPresent()) {
                floors.get(currentElevatorLevel).put(moveTwo.get(), 1);
                floors.get(currentElevatorLevel - 1).put(moveTwo.get(), 1);
                currentElevatorLevel--;
                moves++;
            }
        }
    }


}
