package org.advent.advent2023.day10;


import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static List<Character> connectingLeft = List.of('F', '-', 'L');
    public static List<Character> connectingRight = List.of('J', '-', '7');
    public static List<Character> connectingUp = List.of('F', '|', '7');
    public static List<Character> connectingDown = List.of('L', '|', 'J');
    public static void main(String[] args) {
        InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2023/day10/input.txt");
        Scanner scanner = new Scanner(is);
        List<List<Character>> map = new ArrayList<>();
        int i =-1;
        int j =-1;
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                map.add(line.chars().mapToObj(c->(char) c ).collect(Collectors.toList()));
                if(j == -1){
                    i++;
                    j = line.indexOf("S");
                }
            }
            int y = i;
            int x = j;
            map.get(y).set(x, identifyPipe(map, y,x));
            int steps = 0;
            Direction oldMove = null;
            while(steps == 0 || ( y != i || x != j)){
                if(map.get(y).get(x) == '-'){
                    if(oldMove == Direction.LEFT){
                        x--;
                        oldMove = Direction.LEFT;
                    }else{
                        x++;
                        oldMove = Direction.RIGHT;
                    }
                }
                else if(map.get(y).get(x) == '|'){
                    if(oldMove == Direction.UP){
                        y--;
                        oldMove = Direction.UP;
                    }else{
                        y++;
                        oldMove = Direction.DOWN;
                    }
                }
                else if(map.get(y).get(x) == 'F'){
                    if(oldMove == Direction.LEFT){
                        y++;
                        oldMove = Direction.DOWN;
                    }else{
                        x++;
                        oldMove = Direction.RIGHT;
                    }
                }
                else if(map.get(y).get(x) == 'J'){
                    if(oldMove == Direction.RIGHT){
                        y--;
                        oldMove = Direction.UP;
                    }else{
                        x--;
                        oldMove = Direction.LEFT;
                    }
                }
                else if(map.get(y).get(x) == 'L'){
                    if(oldMove == Direction.LEFT){
                        y--;
                        oldMove = Direction.UP;
                    }else{
                        x++;
                        oldMove = Direction.RIGHT;
                    }
                }
                else if(map.get(y).get(x) == '7'){
                    if(oldMove == Direction.RIGHT){
                        y++;
                        oldMove = Direction.DOWN;
                    }else{
                        x--;
                        oldMove = Direction.LEFT;
                    }
                }
                else if(map.get(y).get(x) == 'F'){
                    if(oldMove == Direction.LEFT){
                        y++;
                        oldMove = Direction.DOWN;
                    }else{
                        x++;
                        oldMove = Direction.RIGHT;
                    }
                }

                steps++;
            }
            System.out.println(steps / 2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static char identifyPipe(List<List<Character>> map, int y, int x){
        if(x + 1 < map.get(y).size() && x - 1 > 0  && connectingLeft.contains(map.get(y).get(x-1)) && connectingRight.contains(map.get(y).get(x+1))){
            return '-';
        }
        if(x + 1 < map.get(y).size() && y - 1 > 0  && connectingUp.contains(map.get(y-1).get(x)) && connectingRight.contains(map.get(y).get(x+1))){
            return 'L';
        }
        if(x + 1 < map.get(y).size() && y + 1 < map.size()  && connectingDown.contains(map.get(y+1).get(x)) && connectingRight.contains(map.get(y).get(x+1))){
            return 'F';
        }
        if(x - 1 < map.get(y).size() && y - 1 > 0  && connectingLeft.contains(map.get(y).get(x-1)) && connectingUp.contains(map.get(y-1).get(x))){
            return 'J';
        }
        if(x - 1 < map.get(y).size() && y + 1 <map.size()  && connectingLeft.contains(map.get(y).get(x-1)) && connectingDown.contains(map.get(y+1).get(x))){
            return '7';
        }
        return 'S';
    }

    enum Direction{
        RIGHT, UP, DOWN, LEFT;
    }
}