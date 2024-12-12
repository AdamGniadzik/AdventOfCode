package org.advent.advent2024.day12;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static Map<Character, Integer> counterOfBorder = new HashMap<>();
    static Map<Character, Integer> counterOfAreas = new HashMap<>();
    static int counter = 0;

    public static void main(String[] args) {
        try {
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day12/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<List<Character>> map = new ArrayList<>();
            Map<Character, List<Region>> regions = new HashMap<>();
            while (br.ready()) {
                map.add(new ArrayList<>(br.readLine().chars().mapToObj(c -> (char) c).toList()));
            }
                for (int i = 0; i < map.size(); i++) {
                    for (int j = 0; j < map.get(i).size(); j++) {
                        if (map.get(i).get(j) >= 65) {
                            Region region = new Region(map.get(i).get(j));
                            traverse(j, i, map, region);
                            if(regions.containsKey(region.indicator)){
                                regions.get(region.indicator).add(region);
                            }else{
                                List<Region> list = new ArrayList<>();
                                list.add(region);
                                regions.put(region.indicator, list);
                            }

                        }
                    }
                }

            int result = 0;
            for(Character key : regions.keySet()){
                for(Region region : regions.get(key)){
                    result += region.areas * region.border;
                }
            }
            System.out.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void traverse(int x, int y, List<List<Character>> map, Region region) {
        char evaluatedValue = (char) (region.indicator - 65);
        map.get(y).set(x, evaluatedValue);
        region.areas++;
        if (x + 1 < map.get(y).size()) {
            if (map.get(y).get(x + 1) == region.indicator) {
                traverse(x+1, y, map, region);
            }else if(map.get(y).get(x+1) != evaluatedValue){
                region.border++;
            }
        } else {
            region.border++;

        }
        if (x - 1 >= 0) {
            if (map.get(y).get(x - 1) == region.indicator) {
                traverse(x-1, y, map, region);

            }else if(map.get(y).get(x-1) != evaluatedValue){
                region.border++;
            }
        } else {
            region.border++;

        }
        if (y + 1 < map.size()) {
            if (map.get(y + 1).get(x) == region.indicator) {
                traverse(x, y+1, map, region);
            }else if(map.get(y+1).get(x) != evaluatedValue){
                region.border++;
            }
        } else {
            region.border++;

        }
        if (y - 1 >= 0) {
            if (map.get(y - 1).get(x) == region.indicator) {
                traverse(x, y-1, map, region);
            }else if(map.get(y-1).get(x) != evaluatedValue){
                region.border++;
            }
        } else {
            region.border++;

        }
    }

    public static class Region {
        List<Coordinate> coordinates;
        int border = 0;
        int areas = 0;
        char indicator;

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