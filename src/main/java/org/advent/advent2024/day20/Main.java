package org.advent.advent2024.day20;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day20/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Map<Point, Integer> distanceMap = new HashMap<>();
            List<List<Character>> map = new ArrayList<>();
            while (br.ready()) {
                map.add(new ArrayList<>(br.readLine().chars().mapToObj(i -> (char) i).toList()));
            }
            Point startPos = null;
            Point finishPos = null;

            for (int i = 0; i < map.size(); i++) {
                for (int j = 0; j < map.get(i).size(); j++) {
                    if (map.get(i).get(j) == 'S') {
                        startPos = new Point(i, j);
                        map.get(i).set(j, '.');
                    }
                    if (map.get(i).get(j) == 'E') {
                        finishPos = new Point(i, j);
                        map.get(i).set(j, '.');
                    }
                }
            }
            fillDistanceMap(startPos, finishPos, distanceMap, map);
            map.get(finishPos.i).set(finishPos.j, 'O');
            System.out.println(findCheats(map, distanceMap, 2, 100));
            System.out.println(findCheats(map, distanceMap, 20, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return i == point.i && j == point.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }


    public static void fillDistanceMap(Point startPosition, Point endPosition, Map<Point, Integer> distanceMap, List<List<Character>> map){
        int traverseI = startPosition.i;
        int traverseJ = startPosition.j;
        int counter = 0;

        distanceMap.put(new Point(traverseI, traverseJ), 0);
        while (!(traverseI == endPosition.i && traverseJ == endPosition.j)) {
            counter++;
            if (map.get(traverseI + 1).get(traverseJ) == '.') {
                map.get(traverseI).set(traverseJ, 'O');
                traverseI = traverseI + 1;
            } else if (map.get(traverseI - 1).get(traverseJ) == '.') {
                map.get(traverseI).set(traverseJ, 'O');
                traverseI = traverseI - 1;
            } else if (map.get(traverseI).get(traverseJ - 1) == '.') {
                map.get(traverseI).set(traverseJ, 'O');
                traverseJ = traverseJ - 1;
            } else if (map.get(traverseI).get(traverseJ + 1) == '.') {
                map.get(traverseI).set(traverseJ, 'O');
                traverseJ = traverseJ + 1;
            }
            distanceMap.put(new Point(traverseI, traverseJ), counter);
        }
    }

    public static int findCheats(List<List<Character>> map, Map<Point, Integer> distanceMap, int maxCheatRange, int minDistanceApprove){
        Map<Integer, Integer> cheatMap = new HashMap<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                List<Integer> cheats = new ArrayList<>();
                if (map.get(i).get(j) == 'O') {
                    for (int vertical = 0; vertical <= maxCheatRange; vertical++) {
                        if (i + vertical < map.size() && map.get(i + vertical).get(j) == 'O') {
                            cheats.add(distanceMap.get(new Point(i + vertical, j)) - vertical);
                        }
                        if (i - vertical >= 0 && map.get(i - vertical).get(j) == 'O') {
                            cheats.add(distanceMap.get(new Point(i - vertical, j)) - vertical);
                        }
                        for (int horizontal = 1; horizontal <= maxCheatRange - vertical; horizontal++) {
                            if (vertical > 0) {
                                if (i + vertical < map.size()) {
                                    if (j + horizontal < map.get(i + vertical).size() && map.get(i + vertical)
                                            .get(j + horizontal) == 'O') {
                                        cheats.add(distanceMap.get(new Point(i + vertical, j + horizontal)) - vertical - horizontal);
                                    }
                                    if (j - horizontal >= 0 && map.get(i + vertical).get(j - horizontal) == 'O') {
                                        cheats.add(distanceMap.get(new Point(i + vertical, j - horizontal)) - vertical - horizontal);
                                    }
                                }
                                if (i - vertical >= 0) {
                                    if (j + horizontal < map.get(i - vertical).size() && map.get(i - vertical)
                                            .get(j + horizontal) == 'O') {
                                        cheats.add(distanceMap.get(new Point(i - vertical, j + horizontal)) - vertical - horizontal);
                                    }
                                    if (j - horizontal >= 0 && map.get(i - vertical).get(j - horizontal) == 'O') {
                                        cheats.add(distanceMap.get(new Point(i - vertical, j - horizontal)) - vertical - horizontal);
                                    }
                                }
                            }else{
                                if (j + horizontal < map.get(i).size() && map.get(i - vertical)
                                        .get(j + horizontal) == 'O') {
                                    cheats.add(distanceMap.get(new Point(i, j + horizontal))  - horizontal);
                                }
                                if (j - horizontal >= 0 && map.get(i - vertical).get(j - horizontal) == 'O') {
                                    cheats.add(distanceMap.get(new Point(i, j - horizontal))  - horizontal);
                                }
                            }
                        }
                    }

                    for(int cheat : cheats) {
                        int val = (cheat - distanceMap.get(new Point(i, j)));
                        if (val >= minDistanceApprove) {
                            cheatMap.put(val, cheatMap.getOrDefault(val, 0) + 1);
                        }
                    }
                }

            }

        }
        return cheatMap.entrySet().stream().filter(k -> k.getKey() >= minDistanceApprove).map(Map.Entry::getValue)
                .mapToInt(Integer::intValue).sum();
    }
}