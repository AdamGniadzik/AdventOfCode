package org.advent.advent2024.day18;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    int x;
    int y;
    Map<Node, Integer> adjacenedNodes = new HashMap<>();
    private String name;
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    public Node(String name) {
        this.name = name;
    }
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = x + ":" + y;
    }

    public void addDestination(Node node, int distance) {
        adjacenedNodes.put(node, distance);
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacenedNodes() {
        return adjacenedNodes;
    }
}
