package org.advent.advent2024.day24;


import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2024/day24/input.txt"));
        Map<String, Integer> wireInputs = new HashMap<>();
        Set<String> knownWires = new HashSet<>();
        Set<String> unknownWires = new HashSet<>();
        while (br.ready()) {
            String input = br.readLine();
            if (input.isEmpty()) {
                break;
            }
            String[] splitInput = input.split(": ");
            wireInputs.put(splitInput[0], Integer.parseInt(splitInput[1]));
            knownWires.add(splitInput[0]);
        }
        List<Connection> connections = new ArrayList<>();
        while (br.ready()) {
            String input[] = br.readLine().split(" ");
            connections.add(new Connection(input[0], input[1], input[2], input[4]));
            unknownWires.add(input[0]);
            unknownWires.add(input[2]);
            unknownWires.add(input[4]);
        }
        unknownWires.removeAll(knownWires);
        while(!unknownWires.isEmpty()) {
            connections.stream().filter(c -> c.result == null && knownWires.containsAll(Set.of(c.wireA, c.wireB)))
                    .forEach(c -> {
                        c.result = doOperation(wireInputs.get(c.wireA), wireInputs.get(c.wireB), c.operation);
                        wireInputs.put(c.destination, c.result);
                        unknownWires.remove(c.destination);
                        knownWires.add(c.destination);
                    });
        }
        StringBuilder zWires = new StringBuilder();
        wireInputs.keySet().stream().filter(k->k.startsWith("z")).sorted().toList().forEach(key->{
            zWires.append(wireInputs.get(key));
        });
        String binaryZ = zWires.reverse().toString();
        System.out.println(Long.parseLong(binaryZ, 2));
    }

    public static int doOperation(int input1, int input2, String operation) {
        return switch (operation) {
            case "OR" -> input1 | input2;
            case "AND" -> input1 & input2;
            case "XOR" -> input1 ^ input2;
            default -> throw new RuntimeException();
        };
    }

    public static class Connection {
        String wireA;
        String operation;
        String wireB;
        String destination;
        Integer result = null;
        public Connection(String wireA, String operation, String wireB, String destination) {
            this.wireA = wireA;
            this.operation = operation;
            this.wireB = wireB;
            this.destination = destination;
        }

        public String getWireA() {
            return wireA;
        }

        public String getWireB() {
            return wireB;
        }
    }


}