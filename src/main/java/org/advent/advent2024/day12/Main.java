package org.advent.advent2024.day12;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static LinkedList<Node> nodeQueue = new LinkedList<>();

    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day12/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<List<Node>> map = new ArrayList<>();
            Map<Character, List<Region>> regions = new HashMap<>();
            int x = 0;
            int y = 0;

            List<Node> currentList = new ArrayList<>();
            while (br.ready()) {
                for (char c : br.readLine().toCharArray()) {
                    currentList.add(new Node(c, x, y));
                    x++;
                }
                map.add(currentList);
                currentList = new ArrayList<>();
                y++;
                x = 0;
            }
            for (int i = 0; i < map.size(); i++) {
                for (int j = 0; j < map.get(i).size(); j++) {
                    if (map.get(i).get(j).value < 97) {
                        Region region = new Region(map.get(i).get(j).value);
                        traverse(map.get(i).get(j), map, region);
                        if (regions.containsKey(region.indicator)) {
                            regions.get(region.indicator).add(region);
                        } else {
                            List<Region> list = new ArrayList<>();
                            list.add(region);
                            regions.put(region.indicator, list);
                        }

                    }
                }
            }


            int result = 0;
            int result2 = 0;
            for (Character key : regions.keySet()) {
                for (Region region : regions.get(key)) {
                    result += region.areas * region.border;
                    result2 += region.sides * region.areas;
                }
            }
            System.out.println(result);
            System.out.println(result2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Set<Integer> countSides(int x, int y, List<List<Node>> map) {
        char current = map.get(y).get(x).value >= 97 ? (char) (map.get(y).get(x).value - 32) : map.get(y).get(x).value;
        char evaluatedValue = (char) (current + 32);
        Set<Integer> currentSides = new HashSet<>();
        if (x + 1 == map.get(y).size() || (map.get(y).get(x + 1).value != current && map.get(y)
                .get(x + 1).value != evaluatedValue)) {
            currentSides.add(2);
        }
        if (x - 1 < 0 || (map.get(y).get(x - 1).value != current && map.get(y).get(x - 1).value != evaluatedValue)) {
            currentSides.add(4);

        }
        if (y + 1 >= map.size() || (map.get(y + 1).get(x).value != current && map.get(y + 1)
                .get(x).value != evaluatedValue)) {
            currentSides.add(3);

        }
        if (y - 1 < 0 || (map.get(y - 1).get(x).value != current && map.get(y - 1).get(x).value != evaluatedValue)) {
            currentSides.add(1);
        }
        return currentSides;
    }

    public static void traverse(Node node, List<List<Node>> map, Region region) {
        if (node.value >= 97) {
            return;
        }
        int x = node.x;
        int y = node.y;
        Set<Integer> currentSides = countSides(x, y, map);
        char evaluatedValue = (char) (region.indicator + 32);
        map.get(y).get(x).value = evaluatedValue;
        region.areas++;

        for (int side : currentSides) {
            if (currentSides.contains(side) && !map.get(y).get(x).map.get(side)) {
                map.get(y).get(x).map.put(side, true);
                region.sides++;
                if (side % 2 == 0) {
                    int counter = 1;
                    while (y + counter < map.size() && (map.get(y + counter)
                            .get(x).value == region.indicator || map.get(y + counter)
                            .get(x).value == evaluatedValue) && countSides(x, y + counter, map).contains(side)) {
                        map.get(y + counter).get(x).map.put(side, true);
                        counter++;
                    }
                    counter = 1;
                    while (y - counter >= 0 && (map.get(y - counter)
                            .get(x).value == region.indicator || map.get(y - counter)
                            .get(x).value == evaluatedValue) && countSides(x, y - counter, map).contains(side)) {
                        map.get(y - counter).get(x).map.put(side, true);
                        counter++;
                    }
                }
                if (side % 2 == 1) {
                    int counter = 1;
                    while (x + counter < map.get(y).size() && (map.get(y)
                            .get(x + counter).value == region.indicator || map.get(y)
                            .get(x + counter).value == evaluatedValue) && countSides(x + counter, y, map).contains(side)) {
                        map.get(y).get(x + counter).map.put(side, true);
                        counter++;
                    }
                    counter = 1;
                    while (x - counter >= 0 && (map.get(y).get(x - counter).value == region.indicator || map.get(y)
                            .get(x - counter).value == evaluatedValue) && countSides(x - counter, y, map).contains(side)) {
                        map.get(y).get(x - counter).map.put(side, true);
                        counter++;
                    }
                }
            }
        }

        if (x + 1 < map.get(y).size()) {
            if (map.get(y).get(x + 1).value == region.indicator) {
                nodeQueue.add(map.get(y).get(x + 1));
            } else if (map.get(y).get(x + 1).value != evaluatedValue) {
                region.border++;
            }
        } else {
            region.border++;

        }
        if (x - 1 >= 0) {
            if (map.get(y).get(x - 1).value == region.indicator) {
                nodeQueue.add(map.get(y).get(x - 1));
            } else if (map.get(y).get(x - 1).value != evaluatedValue) {
                region.border++;
            }
        } else {
            region.border++;

        }
        if (y + 1 < map.size()) {
            if (map.get(y + 1).get(x).value == region.indicator) {
                nodeQueue.add(map.get(y + 1).get(x));
            } else if (map.get(y + 1).get(x).value != evaluatedValue) {
                region.border++;
            }
        } else {
            region.border++;

        }
        if (y - 1 >= 0) {
            if (map.get(y - 1).get(x).value == region.indicator) {
                nodeQueue.add(map.get(y - 1).get(x));
            } else if (map.get(y - 1).get(x).value != evaluatedValue) {
                region.border++;
            }
        } else {
            region.border++;
        }
        while (!nodeQueue.isEmpty()) {
            traverse(nodeQueue.poll(), map, region);
        }
    }


    public static class Node {
        int x;
        int y;
        Map<Integer, Boolean> map = new HashMap<>(Map.of(1, false, 2, false, 3, false, 4, false));
        char value;

        public Node(char value, int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }
    }

    public static class Region {
        List<Coordinate> coordinates;
        int border;
        int areas;
        char indicator;
        int sides = 0;

        public Region(char indicator) {
            this.indicator = indicator;
            areas = 0;
            border = 0;
            coordinates = new ArrayList<>();
        }
    }

    public static class Coordinate {

    }
}