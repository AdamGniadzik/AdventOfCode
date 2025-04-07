package org.advent.advent2016.day24;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day24/input.txt"));
        List<List<Integer>> original = new ArrayList<>();
        int pointsOfInterest = 0;
        int lineCounter = 0;
        Map<Integer, Coordinate> interestCoordiantes = new HashMap<>();
        while (br.ready()) {
            String line = br.readLine();
            List<Integer> lineArr = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == '#') {
                    lineArr.add(-1);
                } else if (ch == '.') {
                    lineArr.add(0);
                } else {
                    lineArr.add(Integer.MAX_VALUE - (ch - '0'));
                }
                if (ch != '.' && ch != '#') {
                    pointsOfInterest++;
                    interestCoordiantes.put(ch - '0', new Coordinate(i, lineCounter));
                }
            }
            original.add(lineArr);
            lineCounter++;
        }
        int[][] distances = new int[pointsOfInterest][pointsOfInterest];
        for (int i = 0; i < distances.length; i++) {
            for (int j = i + 1; j < distances.length; j++) {
                List<List<Integer>> map = new ArrayList<>();
                for (List<Integer> originalRow : original) {
                    map.add(new ArrayList<>(originalRow));
                }
                if (distances[i][j] != 0 || distances[j][i] != 0) {
                    continue;
                }
                Traveler traveler = new Traveler(interestCoordiantes.get(i).x, interestCoordiantes.get(i).y);
                Queue<Traveler> travelerQueue = new LinkedList<>();
                travelerQueue.add(traveler);
                while (traveler != null) {
                    if (traveler.y + 1 < map.size() && map.get(traveler.y + 1)
                            .get(traveler.x) != -1 && !traveler.visitedCoordinate.contains(new Coordinate(traveler.x, traveler.y + 1))) {
                        if (map.get(traveler.y + 1).get(traveler.x) == Integer.MAX_VALUE - j) {
                            distances[i][j] = traveler.visitedCoordinate.size();
                            distances[j][i] = traveler.visitedCoordinate.size();
                            break;
                        } else {
                            if ((map.get(traveler.y + 1).get(traveler.x) == 0 || map.get(traveler.y + 1)
                                    .get(traveler.x) > traveler.visitedCoordinate.size())) {
                                map.get(traveler.y + 1).set(traveler.x, traveler.visitedCoordinate.size() + 1);
                                travelerQueue.add(new Traveler(traveler.x, traveler.y + 1, traveler.visitedCoordinate));
                            }
                        }
                    }
                    if (traveler.x + 1 < map.get(0).size() && map.get(traveler.y)
                            .get(traveler.x + 1) != -1 && !traveler.visitedCoordinate.contains(new Coordinate(traveler.x + 1, traveler.y))) {
                        if (map.get(traveler.y).get(traveler.x + 1) == Integer.MAX_VALUE - j) {
                            distances[i][j] = traveler.visitedCoordinate.size();
                            distances[j][i] = traveler.visitedCoordinate.size();
                            break;
                        } else {
                            if ((map.get(traveler.y).get(traveler.x + 1) == 0 || map.get(traveler.y)
                                    .get(traveler.x + 1) > traveler.visitedCoordinate.size() + 1)) {
                                map.get(traveler.y).set(traveler.x + 1, traveler.visitedCoordinate.size() + 1);
                                travelerQueue.add(new Traveler(traveler.x + 1, traveler.y, traveler.visitedCoordinate));
                            }
                        }
                    }
                    if (traveler.y - 1 >= 0 && map.get(traveler.y - 1)
                            .get(traveler.x) != -1 && !traveler.visitedCoordinate.contains(new Coordinate(traveler.x, traveler.y - 1))) {
                        if (map.get(traveler.y - 1).get(traveler.x) == Integer.MAX_VALUE - j) {
                            distances[i][j] = traveler.visitedCoordinate.size();
                            distances[j][i] = traveler.visitedCoordinate.size();
                            break;
                        } else {
                            if ((map.get(traveler.y - 1).get(traveler.x) == 0 || map.get(traveler.y - 1)
                                    .get(traveler.x) > traveler.visitedCoordinate.size() + 1)) {
                                map.get(traveler.y - 1).set(traveler.x, traveler.visitedCoordinate.size() + 1);
                                travelerQueue.add(new Traveler(traveler.x, traveler.y - 1, traveler.visitedCoordinate));
                            }
                        }
                    }
                    if (traveler.x - 1 >= 0 && map.get(traveler.y)
                            .get(traveler.x - 1) != -1 && !traveler.visitedCoordinate.contains(new Coordinate(traveler.x - 1, traveler.y))) {
                        if (map.get(traveler.y).get(traveler.x - 1) == Integer.MAX_VALUE - j) {
                            distances[i][j] = traveler.visitedCoordinate.size();
                            distances[j][i] = traveler.visitedCoordinate.size();
                            break;
                        } else {
                            if (map.get(traveler.y).get(traveler.x - 1) == 0 || map.get(traveler.y)
                                    .get(traveler.x - 1) > traveler.visitedCoordinate.size() + 1) {
                                map.get(traveler.y).set(traveler.x - 1, traveler.visitedCoordinate.size() + 1);
                                travelerQueue.add(new Traveler(traveler.x - 1, traveler.y, traveler.visitedCoordinate));
                            }
                        }
                    }
                    traveler = travelerQueue.poll();
                }
            }
        }
        Set<Integer> possibilites = new HashSet<>();
        for(int i =1 ;i<pointsOfInterest; i++){
            possibilites.add(i);
        }
        Path startingPath = new Path(0, 0, possibilites);
        List<Path> newPaths = new ArrayList<>();
        List<Path> currentPaths = new ArrayList<>();
        currentPaths.add(startingPath);
        boolean finished = false;
        while(!finished){
            newPaths.clear();
            for(Path path : currentPaths){
                path.leftPlaces.forEach(newPlace -> {
                    Set<Integer> leftPlaces = new HashSet<>(path.leftPlaces);
                    leftPlaces.remove(newPlace);
                    newPaths.add(new Path(newPlace, path.steps + distances[path.currentPlace][newPlace], leftPlaces));
                });
            }
            currentPaths = new ArrayList<>(newPaths);
            if(newPaths.get(0).leftPlaces.size() == 0){
                finished = true;
            }
        }
        System.out.println(currentPaths.stream().mapToInt(Path::steps).min().getAsInt());

        // Stage 2
        System.out.println(currentPaths.stream().mapToInt(p-> p.steps + distances[p.currentPlace][0]).min().getAsInt());


    }

    public static record Path(int currentPlace, int steps, Set<Integer> leftPlaces){

    }


    public static class Traveler {
        int x;
        int y;
        Set<Coordinate> visitedCoordinate;
        public Traveler(int x, int y) {
            this.x = x;
            this.y = y;
            this.visitedCoordinate = new HashSet<>();
            this.visitedCoordinate.add(new Coordinate(x, y));
        }

        public Traveler(int x, int y, Set<Coordinate> visted) {
            this.x = x;
            this.y = y;
            this.visitedCoordinate = new HashSet<>(visted);
            this.visitedCoordinate.add(new Coordinate(x, y));
        }
    }

    public record Coordinate(int x, int y) {
    }
}
