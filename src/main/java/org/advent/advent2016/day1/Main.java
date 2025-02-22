package org.advent.advent2016.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day1/input.txt"));
        int x = 0;
        int y = 0;
        int currentDirection = 1;
        Map<Integer, List<Integer>> moves = Map.of(1, List.of(0, 1), 2, List.of(1, 0), 3, List.of(0, -1), 4, List.of(-1, 0));
        Set<String> positions = new HashSet<>();
        boolean found = false;

        while (br.ready()) {
            for (String move : br.readLine().split(", ")) {
                int steps = Integer.parseInt(move.substring(1));
                if (move.charAt(0) == 'R') {
                    currentDirection =  (currentDirection + 1) % 4;
                } else if (move.charAt(0) == 'L') {
                    currentDirection = (currentDirection) - 1 % 4;
                }
                currentDirection = currentDirection == 0 ? 4 : currentDirection;
                currentDirection = currentDirection == 5 ? 1 : currentDirection;
                for(int i =0;i<steps; i++){
                    x +=  moves.get(currentDirection).get(0);
                    y += moves.get(currentDirection).get(1);
                    if(!found && !positions.add(x+":"+y)){
                        System.out.println("Stage2: " +  (Math.abs(x) + Math.abs(y)));
                        found = true;
                    };
                }
            }
        }
        System.out.println(Math.abs(x) + Math.abs(y));

    }
}
