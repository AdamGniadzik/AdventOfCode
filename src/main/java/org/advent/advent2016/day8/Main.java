package org.advent.advent2016.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day8/input.txt"));
        char [][] map = new char[6][50];
        List<String> commands = new ArrayList<>();
        for(int i =0;i<map.length;i++){
            Arrays.fill(map[i], '.');
        }
        while (br.ready()) {
            commands.add(br.readLine());
        }
        stage1(commands, map);


    }


    private static void stage1 (List<String> commands, char[][] map){
        String ROTATE_COLUMN = "rotate column x=";
        String ROTATE_ROW = "rotate row y=";
        for(String command : commands){
            if(command.startsWith("rect")){
                String[] coordinates = command.substring(5).split("x");
                rect(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), map);
            }else if(command.startsWith(ROTATE_ROW)){
                int y = Integer.parseInt(command.substring(ROTATE_ROW.length(), command.indexOf(" ", ROTATE_ROW.length())));
                int by = Integer.parseInt(command.substring(command.lastIndexOf(" ")  + 1));
                rotateRow(y, by, map);
            }
            else if(command.startsWith(ROTATE_COLUMN)){
                int x = Integer.parseInt(command.substring(ROTATE_COLUMN.length(), command.indexOf(" ", ROTATE_COLUMN.length())));
                int by = Integer.parseInt(command.substring(command.lastIndexOf(" ") + 1));
                rotateColumn(x, by, map);
            }


        }
        int counter =0;
        for(char[] row : map){
            for(char sign : row){
                if(sign == '#'){
                    counter++;
                }
            }
        }
        System.out.println(counter);
        for(int i =0;i<map.length;i++){
            System.out.println(Arrays.toString(map[i]));
        }
    }
    private static void rect(int x, int y, char[][] map){
        for(int i =0;i<x;i++){
            for(int j=0;j<y;j++){
               map[j][i] = '#';
            }
        }

    }

    private static void rotateColumn(int column, int by, char[][] map){
        char[] oldValues = new char[map.length];
        for(int i =0;i<map.length;i++){
            oldValues[i] = map[i][column];
        }
        for(int i =0;i<map.length;i++){
            map[(i + by) % map.length][column] = oldValues[i];
        }
    }

    private static void rotateRow(int row, int by, char[][] map){
        char[] oldValues = new char[map[0].length];
        for(int i =0;i<map[0].length;i++){
            oldValues[i] = map[row][i];
        }
        for(int i =0;i<map[0].length;i++){
            map[row][(i + by) % map[0].length] = oldValues[i];
        }
    }
}
