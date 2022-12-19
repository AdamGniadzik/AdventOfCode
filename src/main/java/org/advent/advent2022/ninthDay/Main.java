package org.advent.advent2022.ninthDay;

import org.advent.advent2022.eighthDay.Tree;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        List<Pointer> tails = new ArrayList<>();
        for(int i=0;i<9;i++){
            tails.add(new Pointer());
        }
        Set<String> positions = new HashSet<>();
        positions.add(0 + " " + 0);
        Pointer head = new Pointer();
        Pointer tail = new Pointer();
        InputStream is = org.advent.advent2022.eighthDay.Main.class.getClassLoader().getResourceAsStream("org/advent/advent2022/ninthDay/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<List<Tree>> grid = new ArrayList<>();
        grid.add(new ArrayList<>());
        int index = 0;
        try {
            while (br.ready()) {
                char direction = (char) br.read();
                br.read();
                int numberOfSteps = Integer.parseInt(br.readLine());
                for(int i=0;i<numberOfSteps; i++){
                    move(head, direction);
                    /* Part 1
                    follow(head, tail);
                    positions.add(tail.x + " " + tail.y);
                     */
                    //Part 2
                    follow(head, tails.get(0));
                    for(int j=0;j<=tails.size()-2;j++){
                        follow(tails.get(j), tails.get(j+1));
                    }
                    positions.add(tails.get(tails.size()-1).x + " " + tails.get(tails.size()-1).y);
                    // End of Part 2
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(positions.size());


    }

    public static void move(Pointer head, char direction){
        if(direction == 'R'){
            head.x ++;
        }else if(direction =='L'){
            head.x --;
        }else if(direction =='U'){
            head.y ++;
        }else if(direction =='D'){
            head.y --;
        }
    }
    public static void follow(Pointer head, Pointer tail){
        if(head.x == tail.x && head.y - tail.y == 2){
            tail.y ++;
        } else if (head.y == tail.y && head.x - tail.x == 2) {
            tail.x++;
        }
        if(head.x == tail.x && head.y - tail.y == -2){
            tail.y --;
        } else if (head.y == tail.y && head.x - tail.x == -2) {
            tail.x--;
        }
        else if(head.x - tail.x == 2){
            if(head.y > tail.y){
                tail.x++;
                tail.y++;
            }else{
                tail.x++;
                tail.y--;
            }
        }
        else if(head.x - tail.x == -2){
            if(head.y > tail.y){
                tail.x--;
                tail.y++;
            }else{
                tail.x--;
                tail.y--;
            }
        }
        else if(head.y - tail.y == 2){
            if(head.x > tail.x){
                tail.y++;
                tail.x++;
            }else{
                tail.y++;
                tail.x--;
            }
        }
        else if(head.y - tail.y == -2){
            if(head.x > tail.x){
                tail.y--;
                tail.x++;
            }else{
                tail.y--;
                tail.x--;
            }
        }
    }
}
