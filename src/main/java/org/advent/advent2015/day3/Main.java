package org.advent.advent2015.day3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        stage1();
        stage2();
    }

    public static void stage1() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day3/input.txt"));
        Set<Coordinates> coordinatesSet = new HashSet<>();
        int currentX = 0;
        int currentY = 0;
        coordinatesSet.add(new Coordinates(0, 0));
        while (br.ready()) {
            char input = (char) br.read();
            Coordinates coordinates = parseInput(input, currentX, currentY);
            coordinatesSet.add(coordinates);
            currentX = coordinates.x();
            currentY = coordinates.y();
        }

        System.out.println(coordinatesSet.size());
        br.close();
    }

    public static void stage2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2015/day3/input.txt"));
        Set<Coordinates> coordinatesSet = new HashSet<>();
        int santaX = 0;
        int robotX = 0;
        int santaY = 0;
        int robotY = 0;
        coordinatesSet.add(new Coordinates(0, 0));
        while (br.ready()) {
            char santaInput = (char) br.read();
            char robotInput = (char) br.read();
            Coordinates santaCoordinates = parseInput(santaInput, santaX, santaY);
            Coordinates robotcoordinates = parseInput(robotInput, robotX, robotY);
            coordinatesSet.add(santaCoordinates);
            coordinatesSet.add(robotcoordinates);
            santaX = santaCoordinates.x();
            santaY = santaCoordinates.y();
            robotX = robotcoordinates.x();
            robotY = robotcoordinates.y();
        }
        System.out.println(coordinatesSet.size());
        br.close();
    }

    public static Coordinates parseInput(char input, int currentX, int currentY) {
        if (input == '>') {
            currentX++;
        } else if (input == '^') {
            currentY--;
        } else if (input == '<') {
            currentX--;
        } else if (input == 'v') {
            currentY++;
        }
        return new Coordinates(currentX, currentY);
    }

    public record Coordinates(int x, int y) {
    }
}
