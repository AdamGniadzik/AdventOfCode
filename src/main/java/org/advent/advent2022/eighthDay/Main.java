package org.advent.advent2022.eighthDay;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream is = Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/eighthDay/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            List<List<Tree>> grid = new ArrayList<>();
            grid.add(new ArrayList<>());
            int index = 0;
            while (br.ready()) {
                int value = br.read();
                if(value == 13){
                    br.read();
                    index ++;
                    grid.add(new ArrayList<>());
                    continue;
                }
                grid.get(index).add(new Tree(value - 48));
            }
            traverseLeft(grid);
            traverseRight(grid);
            traverseTop(grid);
            traverseBottom(grid);

            int countVisible = 0;
            int x =0;
            int y =0;
            int biggestScore =0;
            for(int i=0;i<grid.size();i++){
                for(int j=0;j<grid.get(i).size();j++){
                    if(grid.get(i).get(j).visible){
                        countVisible++;
                        countVisibilityScore(grid, j,i);
                        if(grid.get(i).get(j).countOfVisibleTrees > biggestScore){
                            biggestScore = grid.get(i).get(j).countOfVisibleTrees;
                            y=i;
                            x=j;
                        }
                    }
                }
            }

            System.out.println("count of all visible trees: " + countVisible);
            System.out.println("score:"+grid.get(y).get(x).countOfVisibleTrees + " y:" + y  + " x:" + x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void countVisibilityScore (List<List<Tree>> forest, int x, int y){
        int right = 0;
        int left = 0;
        int top = 0;
        int bottom = 0;
        Tree currentTree = forest.get(y).get(x);
        //Right
        if(x < forest.get(y).size() -1) {
            for (int i = x +1; i < forest.get(y).size(); i++) {
                if (forest.get(y).get(i).value < currentTree.value) {

                    right++;
                } else {
                    right++;
                    break;
                }
            }
        }
        //Left
        if(x != 0) {
            for (int i = x - 1; i >= 0; i--) {
                if (forest.get(y).get(i).value < currentTree.value) {
                    left++;
                } else {
                    left++;
                    break;
                }
            }
        }
        if(y != forest.size()-1) {
            //Top
            for (int i = y + 1; i < forest.size(); i++) {
                if (forest.get(i).get(x).value < currentTree.value) {
                    bottom++;
                } else {
                    bottom++;
                    break;
                }
            }
        }
        if( y != 0) {
            for (int i = y -1; i >= 0; i--) {
                if (forest.get(i).get(x).value < currentTree.value) {
                    top++;
                } else {
                    top++;
                    break;
                }
            }
        }
        currentTree.countOfVisibleTrees = left * right * bottom * top;
    }


    public static void traverseTop (List<List<Tree>> forest){
        for(int i=0;i<forest.get(0).size();i++){
            int biggestValue = forest.get(0).get(i).value - 1;
            for(int j=0;j<forest.size(); j++){
                Tree tree = forest.get(j).get(i);
                if(tree.value > biggestValue){
                    tree.visible = true;
                    biggestValue = tree.value;
                }
                if(tree.value == 9){
                    break;
                }
            }
        }
    }
    public static void traverseBottom (List<List<Tree>> forest){
        for(int i=0;i<forest.get(0).size();i++){
            int biggestValue = forest.get(forest.size()-1).get(i).value - 1;
            for(int j=forest.size()-1;j>=0; j--){
                Tree tree = forest.get(j).get(i);
                if(tree.value > biggestValue){
                    tree.visible = true;
                    biggestValue = tree.value;
                }
            }
        }
    }
    public static void traverseLeft (List<List<Tree>> forest){
        for(int i=0;i<forest.size();i++){
            int biggestValue = forest.get(i).get(0).value - 1;
            for(int j=0;j<forest.get(i).size(); j++){
                Tree tree = forest.get(i).get(j);
                if(tree.value > biggestValue){
                    tree.visible = true;
                    biggestValue = tree.value;
                }
                if(tree.value == 9){
                    break;
                }
            }
        }
    }
    public static void traverseRight (List<List<Tree>> forest){
        for(int i=0;i<forest.size();i++){
            int biggestValue = forest.get(i).get(forest.get(i).size()-1).value - 1;
            for(int j=forest.get(i).size()-1;j>=0; j--){
                Tree tree = forest.get(i).get(j);
                if(tree.value > biggestValue){
                    tree.visible = true;
                    biggestValue = tree.value;
                }
                if(tree.value == 9){
                    break;
                }
            }
        }
    }
}
