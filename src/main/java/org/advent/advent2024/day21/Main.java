package org.advent.advent2024.day21;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day21/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Map<Transition, Map<Character, Integer>> numericalKeypad = new HashMap<>();
            Map<Transition, Map<Character, Integer>> directionalKeypad = new HashMap<>();
            initNumericalKeypad(numericalKeypad);
            initDirectionalKeypad(directionalKeypad);
            int result = 0;
            while (br.ready()) {
                String input = br.readLine();
                Set<String> firstOutput = getNumericalKeypadMove(numericalKeypad, input);
                Set<String> secondOutput = firstOutput.stream().map(f -> getDirectionalKeypadMove(directionalKeypad, f))
                        .flatMap(Collection::stream).collect(Collectors.toSet());
                Set<String> thirdOutput = secondOutput.stream().map(f -> getDirectionalKeypadMove(directionalKeypad, f))
                        .flatMap(Collection::stream).collect(Collectors.toSet());
                int count = thirdOutput.stream().mapToInt(String::length).min().getAsInt();
                int parsedNumber = Integer.parseInt(input.substring(0, input.length() - 1));
                System.out.println(input + " " + count + "  " + parsedNumber + "   " + (count * parsedNumber) + "  " + firstOutput.size() + "  " + secondOutput.size() + "  " + thirdOutput.size());
                result += count * parsedNumber;
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getDirectionalKeypadMove(Map<Transition, Map<Character, Integer>> directionalKeypad, String input) {
        char lastMove = ' ';
        char currentCharacter = 'A';
        Set<String> outputList = new HashSet<>();
        for (char inputChar : input.toCharArray()) {
            boolean allowToShuffle = true;
            StringBuilder output = new StringBuilder();
            Transition currentTransition = new Transition(currentCharacter, inputChar);
            Map<Character, Integer> movesToBeDone = new HashMap<>(directionalKeypad.get(currentTransition));
            if (currentCharacter == '<' && (inputChar == '^' || inputChar == 'A')) {
                allowToShuffle = false;
                output.append(">");
                lastMove = '>';
                if (movesToBeDone.get('>') > 1) {
                    movesToBeDone.put('>', movesToBeDone.get('>') - 1);
                } else {
                    movesToBeDone.remove('>');
                }
            }
            if ((currentCharacter == '^' || currentCharacter == 'A') && inputChar == '<') {
                allowToShuffle = false;
                output.append("v");
                lastMove = 'v';
                if (movesToBeDone.get('v') > 1) {
                    movesToBeDone.put('v', movesToBeDone.get('v') - 1);
                } else {
                    movesToBeDone.remove('v');
                }
            }
            if (movesToBeDone.containsKey(lastMove)) {
                output.append(lastMove);
                if (movesToBeDone.get(lastMove) > 1) {
                    movesToBeDone.put(lastMove, movesToBeDone.get(lastMove) - 1);
                } else {
                    movesToBeDone.remove(lastMove);
                }
            }
            for (char move : movesToBeDone.keySet()) {
                for (int i = 0; i < movesToBeDone.get(move); i++) {
                    output.append(move);
                }
                lastMove = move;
            }
            Set<String> incomingOutput = new HashSet<>();
            if (outputList.isEmpty()) {
                if (allowToShuffle) {
                    outputList.add(output.toString() + 'A');
                    outputList.add(output.reverse().toString() + 'A');
                } else {
                    outputList.add(output.toString() + 'A');
                }
            } else {
                for (String s : outputList) {
                    if (allowToShuffle) {
                        incomingOutput.add(s + output + 'A');
                        incomingOutput.add(s + output.reverse() + 'A');
                    } else {
                        incomingOutput.add(s + output + 'A');
                    }
                }
            }
            outputList = incomingOutput.isEmpty() ? outputList : incomingOutput;
            currentCharacter = inputChar;
        }
        return outputList;
    }

    public static Set<String> permutation(String s) {
        char[] tmp = s.toCharArray();
        Arrays.sort(tmp);
        StringBuilder str = new StringBuilder();
        str.append(tmp);
        return new HashSet<>(List.of(s, str.toString(), str.reverse().toString()));
    }

    public static Set<String> getNumericalKeypadMove(Map<Transition, Map<Character, Integer>> numericalKeypad, String input) {
        char currentCharacter = 'A';
        char lastMove = ' ';
        Set<String> outputList = new HashSet<>();
        for (char inputChar : input.toCharArray()) {
            StringBuilder output = new StringBuilder();
            boolean allowToShuffle = true;
            Transition currentTransition = new Transition(currentCharacter, inputChar);
            Map<Character, Integer> movesToBeDone = new HashMap<>(numericalKeypad.get(currentTransition));
            if ((currentCharacter == 'A' || currentCharacter == '0') && movesToBeDone.containsKey('^')) {
                allowToShuffle = false;
                output.append("^");
                lastMove = '^';
                if (movesToBeDone.get('^') > 1) {
                    movesToBeDone.put('^', movesToBeDone.get('^') - 1);
                } else {
                    movesToBeDone.remove('^');
                }
            }
            if (inputChar == '0' && currentCharacter != 'A' && ((int) (currentCharacter - '0') % 3 == 1 && movesToBeDone.containsKey('>'))) {
                allowToShuffle = false;
                output.append(">");
                lastMove = '>';
                if (movesToBeDone.get('>') > 1) {
                    movesToBeDone.put('>', movesToBeDone.get('>') - 1);
                } else {
                    movesToBeDone.remove('>');
                }
            }
            if (movesToBeDone.containsKey(lastMove)) {
                while ((movesToBeDone.get(lastMove) > 0)) {
                    movesToBeDone.put(lastMove, movesToBeDone.get(lastMove) - 1);
                    output.append(lastMove);
                }
                movesToBeDone.remove(lastMove);
            }
            for (char move : movesToBeDone.keySet()) {
                for (int i = 0; i < movesToBeDone.get(move); i++) {
                    output.append(move);
                }
                lastMove = move;
            }
            Set<String> incomingOutput = new HashSet<>();
            if (outputList.isEmpty()) {
                if (allowToShuffle) {
                    outputList.addAll(permutation(output.toString()).stream().map(i -> i + 'A')
                            .collect(Collectors.toSet()));
                } else {
                    outputList.add(output.toString() + 'A');
                }
            } else {
                for (String s : outputList) {
                    if (allowToShuffle) {
                        incomingOutput.addAll(permutation(output.toString()).stream().map(i -> s + i + 'A')
                                .collect(Collectors.toSet()));
                    } else {
                        incomingOutput.add(s + output + 'A');
                    }
                }
            }
            outputList = incomingOutput.isEmpty() ? outputList : incomingOutput;
            currentCharacter = inputChar;
        }
        return outputList;
    }

    public static void initNumericalKeypad(Map<Transition, Map<Character, Integer>> numericalOutputKeypad) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (i == 3 && j == 7) {
                    System.out.println("x");
                }
                if (i == j) {
                    numericalOutputKeypad.put(new Transition(i, j), Map.of());
                }
                int tmpI = i;
                Transition transition = new Transition(i, j);
                Map<Character, Integer> occurenceMap = new HashMap<>();
                numericalOutputKeypad.put(transition, new HashMap<>());
                while (Math.abs(((tmpI - 1) / 3) - ((j - 1) / 3)) > 0) {
                    if (tmpI > j) {
                        occurenceMap.put('v', occurenceMap.getOrDefault('v', 0) + 1);
                        tmpI = tmpI - 3;

                    } else {
                        occurenceMap.put('^', occurenceMap.getOrDefault('^', 0) + 1);
                        tmpI = tmpI + 3;
                    }
                }
                while (Math.abs(tmpI - j) > 0) {
                    if (tmpI > j) {
                        occurenceMap.put('<', occurenceMap.getOrDefault('<', 0) + 1);
                        tmpI = tmpI - 1;

                    } else {
                        occurenceMap.put('>', occurenceMap.getOrDefault('>', 0) + 1);
                        tmpI = tmpI + 1;

                    }
                }
                numericalOutputKeypad.put(transition, occurenceMap);
            }
        }

        for (int i = 1; i <= 9; i++) {
            Transition transitionFromZero = new Transition('0', i);
            Transition transitionFromA = new Transition('A', i);
            Transition transitionToZero = new Transition(i, '0');
            Transition transitionToA = new Transition(i, 'A');
            Map<Character, Integer> occurenceMap;

            occurenceMap = new HashMap<>(numericalOutputKeypad.get(new Transition(2, i)));
            occurenceMap.put('^', occurenceMap.getOrDefault('^', 0) + 1);
            numericalOutputKeypad.put(transitionFromZero, occurenceMap);

            occurenceMap = new HashMap<>(numericalOutputKeypad.get(new Transition(i, 2)));
            occurenceMap.put('v', occurenceMap.getOrDefault('v', 0) + 1);
            numericalOutputKeypad.put(transitionToZero, occurenceMap);

            occurenceMap = new HashMap<>(numericalOutputKeypad.get(new Transition(3, i)));
            occurenceMap.put('^', occurenceMap.getOrDefault('^', 0) + 1);
            numericalOutputKeypad.put(transitionFromA, occurenceMap);

            occurenceMap = new HashMap<>(numericalOutputKeypad.get(new Transition(i, 3)));
            occurenceMap.put('v', occurenceMap.getOrDefault('v', 0) + 1);
            numericalOutputKeypad.put(transitionToA, occurenceMap);
        }

        numericalOutputKeypad.put(new Transition('A', '0'), Map.of('<', 1));
        numericalOutputKeypad.put(new Transition('0', 'A'), Map.of('>', 1));
    }

    public static void initDirectionalKeypad(Map<Transition, Map<Character, Integer>> directionalKeypad) {
        directionalKeypad.put(new Transition('^', 'A'), Map.of('>', 1));
        directionalKeypad.put(new Transition('^', 'v'), Map.of('v', 1));
        directionalKeypad.put(new Transition('^', '>'), Map.of('>', 1, 'v', 1));
        directionalKeypad.put(new Transition('^', '<'), Map.of('v', 1, '<', 1));
        directionalKeypad.put(new Transition('^', '^'), Map.of());

        directionalKeypad.put(new Transition('>', 'A'), Map.of('^', 1));
        directionalKeypad.put(new Transition('>', '<'), Map.of('<', 2));
        directionalKeypad.put(new Transition('>', '^'), Map.of('<', 1, '^', 1));
        directionalKeypad.put(new Transition('>', 'v'), Map.of('<', 1));
        directionalKeypad.put(new Transition('>', '>'), Map.of());

        directionalKeypad.put(new Transition('<', 'A'), Map.of('>', 2, '^', 1));
        directionalKeypad.put(new Transition('<', '^'), Map.of('>', 1, '^', 1));
        directionalKeypad.put(new Transition('<', 'v'), Map.of('>', 1));
        directionalKeypad.put(new Transition('<', '>'), Map.of('>', 2));
        directionalKeypad.put(new Transition('<', '<'), Map.of());

        directionalKeypad.put(new Transition('v', 'A'), Map.of('^', 1, '>', 1));
        directionalKeypad.put(new Transition('v', '>'), Map.of('>', 1));
        directionalKeypad.put(new Transition('v', '<'), Map.of('<', 1));
        directionalKeypad.put(new Transition('v', '^'), Map.of('^', 1));
        directionalKeypad.put(new Transition('v', 'v'), Map.of());

        directionalKeypad.put(new Transition('A', 'v'), Map.of('<', 1, 'v', 1));
        directionalKeypad.put(new Transition('A', '>'), Map.of('v', 1));
        directionalKeypad.put(new Transition('A', '^'), Map.of('<', 1));
        directionalKeypad.put(new Transition('A', '<'), Map.of('v', 1, '<', 2));
        directionalKeypad.put(new Transition('A', 'A'), Map.of());
    }

}