package org.advent.advent2024.day15;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Part1 {
    public static LinkedList<Character> moves = new LinkedList<>();
    public static int currentRow = 0;
    public static int currentColumn = 0;
    public static void main(String[] args) {
        try {
            long result = 0;
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day15/input.txt");
            List<List<Character>> map = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int atRow = 0;
            int atColumn = 0;
            boolean atFound = false;
            boolean movesTurn = false;
            while (br.ready()) {
                String input = br.readLine();
                if (!atFound && input.contains("@")) {
                    atColumn = input.indexOf("@");
                    atFound = true;
                } else if (!atFound) {
                    atRow++;
                }
                if (input.isEmpty()) {
                    movesTurn = true;
                }
                if (movesTurn) {
                    moves.addAll(new ArrayList<>(input.chars().mapToObj(i -> (char) i).toList()));
                } else {
                    map.add(new ArrayList<>(input.chars().mapToObj(i -> (char) i).toList()));
                }

            }

            currentColumn = atColumn;
            currentRow = atRow;
            while(!moves.isEmpty()){
                    move(map, currentRow, currentColumn, moves.pollFirst());

            }
            for(int i =0;i<map.size();i++){
                for(int j =0;j<map.get(i).size();j++){
                    if(map.get(i).get(j) == 'O'){
                        result += i * 100 + j;
                    }
                }
            }
            System.out.println(result);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void move(List<List<Character>> map, int row, int column, char command) {

        int newRow = row;
        int newColumn = column;
        if (command == '<') {
            if (map.get(row).get(column - 1) == '.') {
                map.get(row).set(column - 1, '@');
                map.get(row).set(column, '.');
                newColumn--;
            } else if (map.get(row).get(column - 1) == 'O') {
                int counter = 1;
                while (map.get(row).size() - counter >= 0) {
                    if (map.get(row).get(column - counter) == '0') {
                        counter++;
                        continue;
                    }
                    if (map.get(row).get(column - counter) == '.') {
                        for (int i = counter; i > 0; i--) {
                            map.get(row).set(column - i, map.get(row).get(column - i + 1));
                        }
                        map.get(row).set(column, '.');
                        newColumn--;
                        break;
                    }
                    if (map.get(row).get(column - counter) == '#') {
                        break;
                    }
                    counter++;
                }
            }
        } else if (command == '^') {
            if (map.get(row - 1).get(column) == '.') {
                map.get(row - 1).set(column, '@');
                map.get(row).set(column, '.');
                newRow--;
            } else if (map.get(row - 1).get(column) == 'O') {
                int counter = 1;
                while (map.get(row).size() - counter >= 0) {
                    if (map.get(row - counter).get(column) == '0') {
                        counter++;
                        continue;
                    }
                    if (map.get(row - counter).get(column) == '.') {
                        for (int i = counter; i > 0; i--) {
                            map.get(row - i).set(column, map.get(row - i + 1).get(column));
                        }
                        map.get(row).set(column, '.');
                        newRow--;
                        break;
                    }
                    if (map.get(row - counter).get(column) == '#') {
                        break;
                    }
                    counter++;
                }
            }
        } else if (command == '>') {
            if (map.get(row).get(column + 1) == '.') {
                map.get(row).set(column + 1, '@');
                map.get(row).set(column, '.');
                newColumn++;
            } else if (map.get(row).get(column + 1) == 'O') {
                int counter = 1;
                while (map.get(row).size() + counter >= 0) {
                    if (map.get(row).get(column + counter) == '0') {
                        counter++;
                        continue;
                    }
                    if (map.get(row).get(column + counter) == '.') {
                        for (int i = counter; i > 0; i--) {
                            map.get(row).set(column + i, map.get(row).get(column + i - 1));
                        }
                        map.get(row).set(column, '.');
                        newColumn++;
                        break;
                    }
                    if (map.get(row).get(column + counter) == '#') {
                        break;
                    }
                    counter++;
                }
            }
        } else if (command == 'v') {
            if (map.get(row + 1).get(column) == '.') {
                map.get(row + 1).set(column, '@');
                map.get(row).set(column, '.');
                newRow++;
            } else if (map.get(row + 1).get(column) == 'O') {
                int counter = 1;
                while (map.size() + counter >= 0) {
                    if (map.get(row + counter).get(column) == '0') {
                        counter++;
                        continue;
                    }
                    if (map.get(row + counter).get(column) == '.') {
                        for (int i = counter; i > 0; i--) {
                            map.get(row + i).set(column, map.get(row + i - 1).get(column));
                        }
                        map.get(row).set(column, '.');
                        newRow++;
                        break;
                    }
                    if (map.get(row + counter).get(column) == '#') {
                        break;
                    }
                    counter++;
                }
            }
        }
        currentRow = newRow;
        currentColumn = newColumn;

    }
}