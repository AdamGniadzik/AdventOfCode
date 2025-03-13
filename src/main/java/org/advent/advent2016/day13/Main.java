package org.advent.advent2016.day13;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        int input = 1350;
        boolean[][] map = new boolean[50][50];
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                int bitValue = ((x * x) + (3 * x) + (2 * y * x) + y + (y * y)) + input;
                map[y][x] = Integer.bitCount(bitValue) % 2 == 0;
            }
        }
        List<Traveler> travelers = new ArrayList<>();
        Traveler starting = new Traveler(1, 1);
        travelers.add(starting);
        stage1(map);
        stage2(map);
    }

    public static void stage1(boolean[][] map){
        List<Traveler> travelers = new ArrayList<>();
        Traveler starting = new Traveler(1, 1);
        travelers.add(starting);
        int shortestPath = Integer.MAX_VALUE;
        while (!travelers.isEmpty()) {
            List<Traveler> newTravelers = new ArrayList<>();
            for (Traveler traveler : travelers) {
                if(traveler.x == 31 && traveler.y == 39){
                    shortestPath = traveler.visitedPlaces.size();
                    newTravelers.clear();
                    break;

                }
                if (traveler.y + 1 < map.length && map[traveler.y + 1][traveler.x] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x, traveler.y + 1))) {
                    newTravelers.add(new Traveler(traveler.x, traveler.y+1, new HashSet<>(traveler.visitedPlaces)));
                }
                if (traveler.y - 1 >= 0 && map[traveler.y - 1][traveler.x] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x, traveler.y - 1))) {
                    newTravelers.add(new Traveler(traveler.x, traveler.y-1, new HashSet<>(traveler.visitedPlaces)));
                }
                if (traveler.x + 1 < map.length && map[traveler.y][traveler.x + 1] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x + 1, traveler.y))) {
                    newTravelers.add(new Traveler(traveler.x + 1, traveler.y, new HashSet<>(traveler.visitedPlaces)));
                }
                if (traveler.x - 1 >= 0 && map[traveler.y][traveler.x - 1] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x - 1, traveler.y))) {
                    newTravelers.add(new Traveler(traveler.x - 1, traveler.y, new HashSet<>(traveler.visitedPlaces)));
                }
            }
            travelers = newTravelers;

        }
        System.out.println(shortestPath - 1);
    }

    public static void stage2(boolean[][] map){
        Set<Coordinates> distinctPlaces = new HashSet<>();
        List<Traveler> travelers = new ArrayList<>();
        Traveler starting = new Traveler(1, 1);
        travelers.add(starting);
        while (!travelers.isEmpty()) {
            List<Traveler> newTravelers = new ArrayList<>();
            for (Traveler traveler : travelers) {
                if(traveler.visitedPlaces.size() == 50){
                    distinctPlaces.addAll(traveler.visitedPlaces);
                    continue;
                }
                if (traveler.y + 1 < map.length && map[traveler.y + 1][traveler.x] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x, traveler.y + 1))) {
                    newTravelers.add(new Traveler(traveler.x, traveler.y+1, new HashSet<>(traveler.visitedPlaces)));
                }
                if (traveler.y - 1 >= 0 && map[traveler.y - 1][traveler.x] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x, traveler.y - 1))) {
                    newTravelers.add(new Traveler(traveler.x, traveler.y-1, new HashSet<>(traveler.visitedPlaces)));
                }
                if (traveler.x + 1 < map.length && map[traveler.y][traveler.x + 1] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x + 1, traveler.y))) {
                    newTravelers.add(new Traveler(traveler.x + 1, traveler.y, new HashSet<>(traveler.visitedPlaces)));
                }
                if (traveler.x - 1 >= 0 && map[traveler.y][traveler.x - 1] && !traveler.visitedPlaces.contains(new Coordinates(traveler.x - 1, traveler.y))) {
                    newTravelers.add(new Traveler(traveler.x - 1, traveler.y, new HashSet<>(traveler.visitedPlaces)));
                }
                distinctPlaces.addAll(traveler.visitedPlaces);
            }
            travelers = newTravelers;

        }
        System.out.println(distinctPlaces.size() + 1);
    }

    public static class Traveler {
        int x;
        int y;
        Set<Coordinates> visitedPlaces = new HashSet<>();

        public Traveler(int x, int y) {
            this.x = x;
            this.y = y;
            this.visitedPlaces.add(new Coordinates(x, y));
        }

        public Traveler(int x, int y, Set<Coordinates> visitedPlaces) {
            this.x = x;
            this.y = y;
            this.visitedPlaces = visitedPlaces;
            this.visitedPlaces.add(new Coordinates(x, y));
        }
    }

    public record Coordinates(int x, int y) {
        @Override
        public String toString() {
            return "("+x+ ","+y+")";
        }
    }
}
