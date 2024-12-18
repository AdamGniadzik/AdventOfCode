package org.advent.advent2024.day15;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Part2 {
    public static LinkedList<Character> moves = new LinkedList<>();
    public static int currentRow = 0;
    public static int currentColumn = 0;
    public static final int UP = -1;
    public static final int DOWN = 1;

    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day15/input.txt");
            List<List<Character>> map = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<Character> list = new ArrayList<>();
            int column = 0;
            int row = 0;
            int c = 0;
            while (br.ready()) {
                c = br.read();
                if (c == '\r') {
                    br.read();
                    if (!list.isEmpty()) {
                        map.add(list);
                    }
                    list = new ArrayList<>();
                    row++;
                    column = 0;
                    continue;
                } else if (c == '#') {
                    list.add('#');
                    list.add('#');
                    column = column + 2;
                } else if (c == '@') {
                    list.add('@');
                    list.add('.');
                    currentColumn = column;
                    currentRow = row;
                    column = column + 2;
                } else if (c == 'O') {
                    list.add('[');
                    list.add(']');
                    column = column + 2;
                } else if (c == '.') {
                    list.add('.');
                    list.add('.');
                    column = column + 2;
                }
                if (List.of('v', '>', '<', '^').contains((char) c)) {
                    break;
                }
            }
            moves.add((char) c);
            while (br.ready()) {
                moves.add((char) br.read());
            }

            while (!moves.isEmpty()) {
                move(map, currentRow, currentColumn, moves.pollFirst());
            }
            long result = 0;
            for (int i = 0; i < map.size(); i++) {
                for (int j = 0; j < map.get(i).size(); j++) {
                    if (map.get(i).get(j) == '[') {
                        result += i * 100L + j;
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
            } else if (map.get(row).get(column - 1) == ']') {
                int counter = 1;
                while (map.get(row).size() - counter >= 0) {
                    if (map.get(row).get(column - counter) == ']' || map.get(row).get(column - counter) == '[') {
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
        }  else if (command == '>') {
            if (map.get(row).get(column + 1) == '.') {
                map.get(row).set(column + 1, '@');
                map.get(row).set(column, '.');
                newColumn++;
            } else if (map.get(row).get(column + 1) == '[') {
                int counter = 1;
                while (map.get(row).size() + counter >= 0) {
                    if (map.get(row).get(column + counter) == '[' || map.get(row).get(column + counter) == ']') {
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
            if(map.get(row).get(column) == '@' && map.get(row + 1).get(column) == '.'){
                map.get(row + 1).set(column, '@');
                map.get(row).set(column, '.');
                newRow++;
            }
            else if(check(map, row + 1, column, DOWN)){
                if(map.get(row+1).get(column) == ']'){
                    move(map, row+1, column - 1, DOWN);
                }else{
                    move(map, row+1, column, DOWN);
                }
                map.get(row + 1).set(column, '@');
                map.get(row).set(column, '.');
                newRow++;
            }
        }
        else if (command == '^') {
            if(map.get(row).get(column) == '@' && map.get(row - 1).get(column) == '.'){
                map.get(row - 1).set(column, '@');
                map.get(row).set(column, '.');
                newRow--;
            }
            else if(check(map, row - 1, column, UP)){
                if(map.get(row-1).get(column) == ']'){
                    move(map, row-1, column - 1, UP);
                }else{
                    move(map, row-1, column, UP);
                }
                map.get(row - 1).set(column, '@');
                map.get(row).set(column, '.');
                newRow--;
            }
        }
        currentRow = newRow;
        currentColumn = newColumn;

    }

    public static void move(List<List<Character>> map, int row ,int column, int direction) {
        if(map.get(row + direction).get(column - 1) == '['){
            move(map, row+ direction, column - 1, direction);
        }
        if(map.get(row +direction).get(column + 1) == '['){
            move(map, row+ direction, column+1, direction);
        }
        if(map.get(row +direction).get(column) == '['){
            move(map, row+ direction, column, direction);
        }
        map.get(row+ direction).set(column, map.get(row).get(column));
        map.get(row + direction).set(column+1, map.get(row).get(column+1));
        map.get(row ).set(column, '.');
        map.get(row ).set(column+1, '.');

    }
    public static boolean check(List<List<Character>> map, int row, int column, int direction){
        if(map.get(row).get(column) == '.'){
            return true;
        }
        Set<Character> available = Set.of('[', ']', '.');
        if(map.get(row).get(column) == '['){
           if(available.contains(map.get(row + direction).get(column)) && available.contains(map.get(row + direction).get(column + 1))){
               return check(map, row + direction, column, direction) && check(map, row + direction, column + 1, direction);
           }
        }
        if(map.get(row).get(column) == ']'){
            if(available.contains(map.get(row+direction).get(column)) && available.contains(map.get(row+direction).get(column - 1))){
                return check(map, row + direction, column, direction) && check(map, row + direction, column - 1 , direction);
            }
        }
        return false;

    }
}