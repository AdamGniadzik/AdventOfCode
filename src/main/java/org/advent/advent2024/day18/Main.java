package org.advent.advent2024.day18;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    static char[][] map = new char[71][71];

    public static void main(String[] args) {
        try {
            initMap();
            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day18/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int counter = 0;
            while (counter < 1024) {
                String[] input = br.readLine().split(",");
                map[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = '#';
                counter++;
            }
            printMap();

            Graph graph = new Graph();
            String fallingByte = "";
            String previousByte = "";
            int fallToPrevent = 0;
            do {
                graph = new Graph();
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map.length; j++) {
                        Node node = new Node(i, j);
                        graph.addNode(node);
                    }
                }
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map.length; j++) {
                        Node node = graph.getNodeMap().get(i + ":" + j);
                        if (j + 1 < map.length && map[i][j + 1] != '#') {
                            node.addDestination(graph.getNodeMap().get(i + ":" + (j + 1)), 1);
                        }
                        if (i + 1 < map.length && map[i + 1][j] != '#') {
                            node.addDestination(graph.getNodeMap().get((i + 1) + ":" + j), 1);
                        }
                        if (j - 1 >= 0 && map[i][j - 1] != '#') {
                            node.addDestination(graph.getNodeMap().get(i + ":" + (j - 1)), 1);
                        }
                        if (i - 1 >= 0 && map[i - 1][j] != '#') {
                            node.addDestination(graph.getNodeMap().get((i - 1) + ":" + j), 1);
                        }
                    }
                }
                graph.getNodeMap().get("0:0").setDistance(0);
                Graph.calculateShortestPathFromSource(graph, graph.getNodeMap().get("0:0"));
//                System.out.println(graph.getNodeMap().get("70:70").getDistance());
                previousByte = fallingByte;
                fallingByte = br.readLine();
                String[] input = fallingByte.split(",");
                map[Integer.parseInt(input[0])][Integer.parseInt(input[1])] = '#';
                fallToPrevent ++;
            }while(br.ready() && graph.getNodeMap().get("70:70").getDistance() != Integer.MAX_VALUE);
            System.out.println(fallToPrevent);
            System.out.println(previousByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initMap() {
        for (int i = 0; i < 71; i++) {
            for (int j = 0; j < 71; j++) {
                map[i][j] = '.';
            }
        }
    }

    private static void printMap() {
        for (char[] line : map) {
            System.out.println(Arrays.toString(line));
        }
    }
}