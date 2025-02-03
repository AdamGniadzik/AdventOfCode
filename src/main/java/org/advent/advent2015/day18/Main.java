package org.advent.advent2015.day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

//154 too low
// 640 too low
public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day18/input.txt"));
        List<List<Point>> mapStage1 = new ArrayList<>();
        List<List<Point>> mapStage2 = new ArrayList<>();
        int steps = 100;
        while (br.ready()) {
            List<Point> list = new ArrayList<>();
            List<Point> list2 = new ArrayList<>();
            for (char ch : br.readLine().toCharArray()) {
                list.add(new Point(ch == '#'));
                list2.add(new Point(ch == '#'));
            }
            mapStage1.add(list);
            mapStage2.add(list2);
        }
       stage1(mapStage1, steps);
       stage2(mapStage2, steps);
    }

    public static void stage1(List<List<Point>> map, int steps){
        for (int i = 0; i < steps; i++) {
            markSwitches(map);
            doSwitches(map);
        }
        int counter = 0;
        for (List<Point> list : map) {
            for (Point p : list) {
                if (p.state) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
    public static void stage2(List<List<Point>> map, int steps){
        map.get(map.size() - 1).get(map.get(0).size() - 1).switchable = false;
        map.get(map.size() - 1).get(0).switchable = false;
        map.get(0).get(map.get(0).size() - 1).switchable = false;
        map.get(0).get(0).switchable = false;
        stage1(map, steps);
    }


    public static void doSwitches(List<List<Point>> map) {
        for (List<Point> list : map) {
            for (Point p : list) {
                if (p.switchable && p.shouldSwitch) {
                    p.state = !p.state;
                }
                p.shouldSwitch = false;
            }
        }
    }

    public static void markSwitches(List<List<Point>> map) {
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                int neighbours = 0;

                if (j + 1 < map.get(i).size() && map.get(i).get(j + 1).state) {
                    neighbours++;
                }
                if (j - 1 >= 0 && map.get(i).get(j - 1).state) {
                    neighbours++;
                }

                if (i + 1 < map.size()) {
                    if (map.get(i + 1).get(j).state) {
                        neighbours++;
                    }
                    if (j + 1 < map.get(i).size() && map.get(i + 1).get(j + 1).state) {
                        neighbours++;
                    }
                    if (j - 1 >= 0 && map.get(i + 1).get(j - 1).state) {
                        neighbours++;
                    }
                }

                if (i - 1 >= 0) {
                    if (map.get(i - 1).get(j).state) {
                        neighbours++;
                    }
                    if (j + 1 < map.get(i).size() && map.get(i - 1).get(j + 1).state) {
                        neighbours++;
                    }
                    if (j - 1 >= 0 && map.get(i - 1).get(j - 1).state) {
                        neighbours++;
                    }
                }
                if (map.get(i).get(j).state) {
                    if (neighbours == 2 || neighbours == 3) {
                        map.get(i).get(j).shouldSwitch = false;
                    } else {
                        map.get(i).get(j).shouldSwitch = true;
                    }
                }
                if (!map.get(i).get(j).state && neighbours == 3) {
                    map.get(i).get(j).shouldSwitch = true;
                }
            }
        }
    }

    static class Point {
        boolean state;
        boolean shouldSwitch = false;
        boolean switchable = true;

        public Point(boolean state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return state ? "#" : ".";
        }
    }
}