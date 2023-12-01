package org.advent.advent2022.day8;

public class Tree {
    public int value;
    public boolean visible = false;
    public int countOfVisibleTrees = 1;

    public Tree(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + value + ","+visible+","+countOfVisibleTrees+"}";
    }
}