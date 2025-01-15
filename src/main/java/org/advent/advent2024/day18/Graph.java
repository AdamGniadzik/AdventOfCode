package org.advent.advent2024.day18;

import java.util.*;

public class Graph {

    private Set<Node> nodes = new HashSet<>();

    public Map<String, Node> getNodeMap() {
        return nodeMap;
    }

    private Map<String, Node> nodeMap = new HashMap<>();

    public void addNode(Node node){
        nodes.add(node);
        nodeMap.put(node.getName(), node);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source){
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);
        while(unsettledNodes.size() != 0){
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for(Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacenedNodes().entrySet()){
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if(!settledNodes.contains(adjacentNode)){
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    public static Node getLowestDistanceNode(Set<Node> unsettledNodes){
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for(Node node: unsettledNodes){
            int nodeDistance = node.getDistance();
            if(nodeDistance < lowestDistance){
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public static void calculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode){
        Integer sourceDistance = sourceNode.getDistance();
        if(sourceDistance + edgeWeigh < evaluationNode.getDistance()){
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }

    }
}
