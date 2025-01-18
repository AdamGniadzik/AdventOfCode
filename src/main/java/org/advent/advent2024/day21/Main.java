package org.advent.advent2024.day21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2024/day21/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        Map<Character, Position> numericKeypad = new HashMap<>();
        Map<Character, Position> directionalKeypad = new HashMap<>();
        HashMap<Key, Long> directionalKeypadCache = new HashMap<>();
        Position numericBlank = new Position(0, 3);
        Position directionalBlank = new Position(0, 0);
        initNumericKeypad(numericKeypad);
        initDirectionalKeypad(directionalKeypad);
        long stage1 = 0;
        long stage2 = 0;
        while (br.ready()) {
            String code = br.readLine();
            List<String> numericKeypadMoves = movesOnPad(numericKeypad, numericBlank, code, numericKeypad.get('A'));
            List<String> moves = numericKeypadMoves;
            int directionalKeypadSteps = 2;
            for (int i = 0; i < directionalKeypadSteps; i++) {
                List<String> newMoves = new ArrayList<>();
                for (String move : moves) {
                    newMoves.addAll(movesOnPad(directionalKeypad, directionalBlank, move, directionalKeypad.get('A')));
                }
                moves = newMoves;
            }
            List<String> all = new ArrayList<>(moves);
            all.sort(Comparator.comparingInt(String::length));
            long codeNum = Long.parseLong(code.substring(0, 3));
            long complexity = codeNum * all.get(0).length();
            stage1 += complexity;

            long len = Long.MAX_VALUE;
            for (String numPadMove : numericKeypadMoves) {
                char previous = 'A';
                long candidateLength = 0;
                for (char c : numPadMove.toCharArray()) {
                    candidateLength += calcBestOnDirectionalPad(directionalKeypadCache, directionalKeypad, directionalBlank, 25, c, previous);
                    previous = c;
                }
                len = Math.min(len, candidateLength);
            }
            complexity = codeNum * len;
            stage2 += complexity;
        }
        System.out.println(stage1);
        System.out.println(stage2);
    }

    private static long calcBestOnDirectionalPad(HashMap<Key, Long> directionalPadCache, Map<Character, Position> directionalKeypad, Position directionalBlank, int padCount, char move, char start) {
        Key key = new Key(padCount, move, start);
        if (directionalPadCache.containsKey(key)) {
            return directionalPadCache.get(key);
        }
        long result = Long.MAX_VALUE;
        for (String moveCandidate : movesOnPad(directionalKeypad, directionalBlank, String.valueOf(move), directionalKeypad.get(start))) {
            long candidateLength = 0;
            if (padCount == 1) {
                candidateLength = moveCandidate.length();
            } else {
                char prev = 'A';
                for (int i = 0; i < moveCandidate.length(); i++) {
                    char c = moveCandidate.charAt(i);
                    candidateLength += calcBestOnDirectionalPad(directionalPadCache, directionalKeypad, directionalBlank, padCount - 1, c, prev);
                    prev = c;
                }
            }
            result = Math.min(result, candidateLength);
        }
        directionalPadCache.put(key, result);
        return result;
    }

    static List<String> movesOnPad(Map<Character, Position> keypad, Position crash, String code, Position start) {
        Position current = start;
        List<StringBuilder> output = new ArrayList<>();
        output.add(new StringBuilder());
        for (char inputChar : code.toCharArray()) {
            Position nextPosition = keypad.get(inputChar);
            if (nextPosition == null) {
                System.out.println("*****");
            }
            List<List<Direction>> possibilities = new ArrayList<>(2);
            for (Direction direction : Direction.values()) {
                List<Direction> moves = direction.calculateMoves(current, nextPosition);
                if (!moves.isEmpty()) {
                    possibilities.add(moves);
                }
            }
            if (possibilities.size() == 2) {
                boolean first = true;
                int x = current.x;
                int y = current.y;
                for (Direction d : possibilities.get(0)) {
                    x += d.x;
                    y += d.y;
                    if (crash.x == x && crash.y == y) {
                        first = false;
                        break;
                    }
                }
                boolean second = true;
                x = current.x;
                y = current.y;
                for (Direction d : possibilities.get(1)) {
                    x += d.x;
                    y += d.y;
                    if (crash.x == x && crash.y == y) {
                        second = false;
                        break;
                    }
                }
                if (first && !second) {
                    for (StringBuilder sb : output) {
                        possibilities.get(0).forEach(d -> sb.append(d.c));
                        possibilities.get(1).forEach(d -> sb.append(d.c));
                        sb.append('A');
                    }
                } else if (!first && second) {
                    for (StringBuilder sb : output) {
                        possibilities.get(1).forEach(d -> sb.append(d.c));
                        possibilities.get(0).forEach(d -> sb.append(d.c));
                        sb.append('A');
                    }
                } else {
                    List<StringBuilder> add = new ArrayList<>();
                    for (StringBuilder sb : output) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(sb.toString());
                        possibilities.get(0).forEach(d -> sb.append(d.c));
                        possibilities.get(1).forEach(d -> sb.append(d.c));
                        sb.append('A');
                        possibilities.get(1).forEach(d -> sb2.append(d.c));
                        possibilities.get(0).forEach(d -> sb2.append(d.c));
                        sb2.append('A');
                        add.add(sb2);
                    }
                    output.addAll(add);
                }
            } else {
                for (StringBuilder sb : output) {
                    if (!possibilities.isEmpty()) {
                        possibilities.get(0).forEach(d -> sb.append(d.c));
                    }
                    sb.append('A');
                }
            }
            current = nextPosition;
        }
        return output.stream().map(StringBuilder::toString).toList();
    }

    static void initNumericKeypad(Map<Character, Position> numericKeypad) {
        numericKeypad.put('A', new Position(2, 3));
        numericKeypad.put('0', new Position(1, 3));
        numericKeypad.put('1', new Position(0, 2));
        numericKeypad.put('2', new Position(1, 2));
        numericKeypad.put('3', new Position(2, 2));
        numericKeypad.put('4', new Position(0, 1));
        numericKeypad.put('5', new Position(1, 1));
        numericKeypad.put('6', new Position(2, 1));
        numericKeypad.put('7', new Position(0, 0));
        numericKeypad.put('8', new Position(1, 0));
        numericKeypad.put('9', new Position(2, 0));
    }

    static void initDirectionalKeypad(Map<Character, Position> directionalKeypad) {
        directionalKeypad.put('A', new Position(2, 0));
        directionalKeypad.put('^', new Position(1, 0));
        directionalKeypad.put('<', new Position(0, 1));
        directionalKeypad.put('v', new Position(1, 1));
        directionalKeypad.put('>', new Position(2, 1));

    }

    enum Direction {
        UP(0, -1, '^'),
        DOWN(0, 1, 'v'),
        LEFT(-1, 0, '<'),
        RIGHT(1, 0, '>');
        int x;
        int y;
        char c;

        Direction(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        List<Direction> calculateMoves(Position start, Position end) {
            List<Direction> moves = new ArrayList<>();
            if (this == UP) {
                for (int i = start.y; i > end.y; i--) {
                    moves.add(UP);
                }
            } else if (this == DOWN) {
                for (int i = start.y; i < end.y; i++) {
                    moves.add(DOWN);
                }
            } else if (this == LEFT) {
                for (int i = start.x; i > end.x; i--) {
                    moves.add(LEFT);
                }
            } else if (this == RIGHT) {
                for (int i = start.x; i < end.x; i++) {
                    moves.add(RIGHT);
                }
            }
            return moves;
        }
    }

    record Position(int x, int y) {
    }

    record Key(int count, char move, char start) {
    }
}
