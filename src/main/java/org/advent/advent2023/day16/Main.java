package org.advent.advent2023.day16;


import java.io.InputStream;
import java.util.*;

public class Main {

    public static Set<Path> paths = new HashSet<>();
    public static Set<Path> alreadyProcessed = new HashSet<>();
    public static Set<Integer> results = new HashSet<>();

    public static void main(String[] args) {
        InputStream is = org.advent.advent2022.day1.Main.class.getClassLoader()
                .getResourceAsStream("org/advent/advent2023/day16/input.txt");
        Scanner scanner = new Scanner(is);
        List<String> map = new ArrayList<>();
        List<List<Character>> energizedMap = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            map.add(line);
            energizedMap.add(new ArrayList<>(Collections.nCopies(line.length(), '*')));
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        paths.add(new Path(0,0, Direction.RIGHT));
        startCalculation(map, energizedMap);
        System.out.println(results.stream().findAny().get());

        for(int d =0;d<map.size();d++) {
            paths.add(new Path(d, 0, Direction.RIGHT));
            startCalculation(map, energizedMap);
        }
        for(int d =0;d<map.size();d++) {
            paths.add(new Path(d, map.get(d).length()-1, Direction.LEFT));
            startCalculation(map, energizedMap);
        }
        for(int d =0;d<map.get(0).length();d++) {
            paths.add(new Path(0, d, Direction.DOWN));
            startCalculation(map, energizedMap);

        }
        for(int d =0;d<map.get(0).length();d++) {
            paths.add(new Path(map.size()-1, d, Direction.TOP));
            startCalculation(map, energizedMap);

        }
        System.out.println(results.stream().max(Integer::compareTo).get());
    }

    public static void clearEnergizedMap (List<String> map, List<List<Character>> energizedMap){
        energizedMap.clear();
            for(String line: map){
                energizedMap.add(new ArrayList<>(Collections.nCopies(line.length(), '*')));
            }
    }
    public static void startCalculation(List<String> map, List<List<Character>> energizedMap){
        while (!paths.isEmpty()) {
            goThroughPath(map, energizedMap);
        }
        int sum = 0;
        for (int i = 0; i < energizedMap.size(); i++) {
            sum += (int) energizedMap.get(i).stream().filter(c -> c == '#').count();
        }
        results.add(sum);
        alreadyProcessed.clear();
        clearEnergizedMap(map, energizedMap);
    }


    public static void goThroughPath(List<String> map, List<List<Character>> energizedMap) {
        Path path = paths.iterator().next();
        alreadyProcessed.add(path);
        int y = path.y;
        int x = path.x;
        Direction direction = path.direction;
        while (y < map.size() && y >= 0 && x >= 0 && x < map.get(0).length() && map.get(y).charAt(x) == '.') {
            energizedMap.get(y).set(x, '#');
            y += direction.y;
            x += direction.x;
        }
        if (!(y < map.size() && y >= 0 && x >= 0 && x < map.get(0).length())) {
            paths.remove(path);
            return;
        }
        energizedMap.get(y).set(x, '#');
        if (map.get(y).charAt(x) == '/') {
            Direction newDirection;
            newDirection = switch (direction) {
                case TOP -> Direction.RIGHT;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.DOWN;
                case RIGHT -> Direction.TOP;
            };
            Path newPath = new Path(y + newDirection.y, x + newDirection.x, newDirection);
            if(!alreadyProcessed.contains(newPath)){
                paths.add(newPath);
            }


        } else if (map.get(y).charAt(x) == '\\') {
            Direction newDirection;
            newDirection = switch (direction) {
                case TOP -> Direction.LEFT;
                case DOWN -> Direction.RIGHT;
                case LEFT -> Direction.TOP;
                case RIGHT -> Direction.DOWN;
            };
            Path newPath = new Path(y + newDirection.y, x + newDirection.x, newDirection);
            if(!alreadyProcessed.contains(newPath)){
                paths.add(newPath);
            }


        } else if (map.get(y).charAt(x) == '|') {

            if(direction == Direction.TOP || direction == Direction.DOWN){
                Path newPath = new Path(y + direction.y, x, direction);
                if(!alreadyProcessed.contains(newPath)){
                    paths.add(newPath);
                }

            }else{

                Path newPath = new Path(y + 1, x, Direction.DOWN);
                if(!alreadyProcessed.contains(newPath)){
                    paths.add(newPath);
                }
                newPath = new Path(y - 1, x, Direction.TOP);
                if(!alreadyProcessed.contains(newPath)){
                    paths.add(newPath);
                }
            }
        } else if (map.get(y).charAt(x) == '-') {
            if(direction == Direction.RIGHT || direction == Direction.LEFT){
                Path newPath = new Path(y, x + direction.x, direction);
                if(!alreadyProcessed.contains(newPath)){
                    paths.add(newPath);
                }

            }else{
                Path newPath = new Path(y, x + 1, Direction.RIGHT);
                if(!alreadyProcessed.contains(newPath)){
                    paths.add(newPath);
                }
                newPath = new Path(y, x - 1, Direction.LEFT);
                if(!alreadyProcessed.contains(newPath)){
                    paths.add(newPath);
                }
            }
        }


        paths.remove(path);
    }


    public static void goThroughPath(List<String> map, List<List<Character>> energizedMap, int y, int x, Direction direction) {
        boolean anyChange = false;
        int manySteps =0;
        while (y < map.size() && y >= 0 && x >= 0 && x < map.get(0).length() && map.get(y).charAt(x) == '.') {

            if(!(energizedMap.get(y).get(x) == '#')){
                anyChange = true;
            }
            energizedMap.get(y).set(x, '#');
            y += direction.y;
            x += direction.x;
            manySteps ++;
        }
        if(!anyChange && manySteps > 0){
            return;
        }
        if (!(y < map.size() && y >= 0 && x >= 0 && x < map.get(0).length())) {
            return;
        }
        energizedMap.get(y).set(x, '#');
        if (map.get(y).charAt(x) == '/') {
            Direction newDirection;
            newDirection = switch (direction) {
                case TOP -> Direction.RIGHT;
                case DOWN -> Direction.LEFT;
                case LEFT -> Direction.DOWN;
                case RIGHT -> Direction.TOP;
            };

            goThroughPath(map, energizedMap, y + newDirection.y, x + newDirection.x, newDirection);

        } else if (map.get(y).charAt(x) == '\\') {
            Direction newDirection;
            newDirection = switch (direction) {
                case TOP -> Direction.LEFT;
                case DOWN -> Direction.RIGHT;
                case LEFT -> Direction.TOP;
                case RIGHT -> Direction.DOWN;
            };

            goThroughPath(map, energizedMap, y + newDirection.y, x + newDirection.x, newDirection);

        } else if (map.get(y).charAt(x) == '|') {

            if(direction == Direction.TOP || direction == Direction.DOWN){

                goThroughPath(map, energizedMap, y + direction.y, x, direction);
            }else{

               goThroughPath(map, energizedMap, y+1, x, Direction.DOWN);
                goThroughPath(map, energizedMap, y-1, x, Direction.TOP);
            }
        } else if (map.get(y).charAt(x) == '-') {
            if(direction == Direction.RIGHT || direction == Direction.LEFT){
                goThroughPath(map, energizedMap, y, x+ direction.x, direction);
            }else{
                goThroughPath(map, energizedMap, y, x + 1, Direction.RIGHT);
                goThroughPath(map, energizedMap, y, x - 1, Direction.LEFT);
            }
        }

    }



}
enum Direction {
    RIGHT(0, 1), LEFT(0, -1),
    TOP(-1, 0),
    DOWN(1, 0);

    int y;
    int x;

    Direction(int y, int x) {
        this.x = x;
        this.y = y;
    }

}
class Path {
    int y;
    int x;
    Direction direction;

    public Path(int y, int x, Direction direction) {
        this.y = y;
        this.x = x;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return y == path.y && x == path.x && direction == path.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x, direction);
    }


    public int bombinskiMethod(){
        System.out.println("PIES");
        return 0;
    }
}
