package org.advent.advent2015.day7;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day7/input.txt"));
        LinkedList<String> inputList = new LinkedList<>();
        while (br.ready()) {
            inputList.add(br.readLine());
        }
        runProgram(new LinkedList<>(inputList), false);
        runProgram(new LinkedList<>(inputList), true);

    }

    private static void runProgram(LinkedList<String> inputList, boolean isStage2) {
        Map<String, Integer> variables = new HashMap<>();
        while (!inputList.isEmpty()) {
            try {
                String input[] = inputList.getFirst().split(" ");
                if (input.length == 3) {
                    int value = parseStringToInt(input[0]).orElse(variables.get(input[0]));
                    if (isStage2 && input[2].equals("b")) {
                        variables.put("b", 3176);
                    } else {
                        variables.put(input[2], value);
                    }
                } else if ("NOT".equals(input[0])) {
                    int operand = parseStringToInt(input[1]).orElse(variables.get(input[1]));
                    variables.put(input[3], ~operand);
                } else {
                    int firstOperand = parseStringToInt(input[0]).orElse(variables.get(input[0]));
                    int secondOperand = parseStringToInt(input[2]).orElse(variables.get(input[2]));
                    int output = switch (input[1]) {
                        case "AND" -> firstOperand & secondOperand;
                        case "OR" -> firstOperand | secondOperand;
                        case "RSHIFT" -> firstOperand >> secondOperand;
                        case "LSHIFT" -> firstOperand << secondOperand;
                        default -> throw new RuntimeException();
                    };
                    variables.put(input[4], output);
                }
                inputList.poll();
            } catch (Exception e) {
                String postpone = inputList.poll();
                inputList.add(postpone);
            }
        }
        System.out.println(variables.get("a"));
    }

    private static Optional<Integer> parseStringToInt(String input) {
        try {
            return Optional.of(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

}

