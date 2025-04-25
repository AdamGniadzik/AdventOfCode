package org.advent.advent2017.day7;

import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2017/day7/input.txt"));
        Map<String, Node> nodes = new HashMap<>();

        while (br.ready()) {
            String[] line = br.readLine().replaceAll(",", "").replaceAll("\\(", "").replaceAll("\\)", "").split(" ");
            if (line.length == 2) {
                if (nodes.containsKey(line[0])) {
                    nodes.get(line[0]).weight = Integer.parseInt(line[1]);
                    nodes.get(line[0]).aloneWeight = Integer.parseInt(line[1]);
                } else {
                    nodes.put(line[0], new Node(line[0], Integer.parseInt(line[1])));
                }
            } else {
                Node node = nodes.getOrDefault(line[0], new Node(line[0], Integer.parseInt(line[1])));
                for (int i = 3; i < line.length; i++) {
                    Node childNode = nodes.getOrDefault(line[i], new Node(line[i]));
                    nodes.put(line[i], childNode);
                    node.nodes.add(childNode);
                }
                node.weight = Integer.parseInt(line[1]);
                node.aloneWeight = Integer.parseInt(line[1]);
                nodes.put(line[0], node);
            }
        }
        Set<String> nodeNames = nodes.keySet();
        for (String keyToRemove : nodes.values().stream().flatMap(n -> n.nodes.stream()).map(n -> n.name).toList()) {
            nodeNames.remove(keyToRemove);
        }
        Node root = nodes.get(nodeNames.stream().findFirst().get());
        System.out.println(root.name);
        calculateWeight(root);
        System.out.println(root.weight);
        for (Node node : root.nodes) {
            System.out.println(node.weight);
        }
    }

    public static void calculateWeight(Node node) {
        int sum = 0;
        if (node.nodes.isEmpty()) {
            return;
        }
        for (int i = 0; i < node.nodes.size(); i++) {
            calculateWeight(node.nodes.get(i));
            sum += node.nodes.get(i).weight;
        }
        node.weight += sum;
        int desired = node.nodes.get(0).weight;
        for(Node childNode : node.nodes){
            if(childNode.weight != desired){
                System.out.println(childNode.aloneWeight + (desired - childNode.weight)) ;
                System.exit(0);
            }
        }
    }

    public static class Node {
        List<Node> nodes;
        String name;
        int weight;
        int aloneWeight;

        public Node(String name, int weight) {
            this.nodes = new ArrayList<>();
            this.name = name;
            this.weight = weight;
            this.aloneWeight = weight;
        }

        public Node(String name) {
            this.name = name;
            this.nodes = new ArrayList<>();
        }
    }


}
