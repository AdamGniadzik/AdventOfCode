package org.advent.advent2024.day14;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import static java.awt.Color.BLACK;
import static java.awt.Color.WHITE;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;


public class Main {
    public static void main(String[] args) {
        try {

            InputStream is = org.advent.advent2024.day2.Main.class.getClassLoader()
                    .getResourceAsStream("org/advent/advent2024/day14/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int xSize = 101;
            int ySize = 103;
            List<Robot> robots = new ArrayList<>();
            while (br.ready()) {
                String input = br.readLine();
                int firstComma = input.indexOf(",");
                int secondComma = input.indexOf(",", firstComma + 1);
                int x = Integer.parseInt(input.substring(2, firstComma));
                int y = Integer.parseInt(input.substring(firstComma + 1, input.indexOf(" ")));
                int vX = Integer.parseInt(input.substring(input.indexOf("v=") + 2, secondComma));
                int vY = Integer.parseInt(input.substring(secondComma + 1));
                Robot robot = new Robot(x, y, vX, vY);
                robots.add(robot);
            }
            int[][] map = new int[ySize][xSize];
            for (Robot robot : robots) {
                map[robot.y][robot.x]++;
            }
            int[] quadrants = new int[4];
            for(int i =0;i<101*103;i++) {
                int seconds = 1;
                for (Robot robot : robots) {
                    int newX = (robot.x + (robot.vX * seconds)) % xSize;
                    int newY = (robot.y + (robot.vY * seconds)) % ySize;
                    if (newY < 0) {
                        newY = ySize + newY;
                    }
                    if (newX < 0) {
                        newX = xSize + newX;
                    }
                    map[robot.y][robot.x]--;
                    map[newY][newX]++;
                    robot.x = newX;
                    robot.y = newY;

//                    if (robot.x > xSize / 2) {
//                        if (robot.y > ySize / 2) {
//                            quadrants[2]++;
//                        } else if (robot.y < ySize / 2) {
//                            quadrants[1]++;
//                        }
//                    } else if (robot.x < xSize / 2) {
//                        if (robot.y > ySize / 2) {
//                            quadrants[3]++;
//                        } else if (robot.y < ySize / 2) {
//                            quadrants[0]++;
//                        }
//                    }
                }

//                final String ANSI_GREEN = "\u001B[32m";
//               final String ANSI_RESET = "\u001B[0m";
//                System.out.println(i);
//                for(int[] tab : map){
//                    for(int n : tab){
//                        if(n == 1){
//                            System.out.print(ANSI_GREEN + n +ANSI_RESET );
//                        }else{
//                            System.out.print(n);
//                        }
//                    }
//                    System.out.println();
//                }
                BufferedImage img = new BufferedImage(xSize, ySize, TYPE_INT_RGB);
                for(int k =0;k<map.length;k++){
                    for(int j =0;j<map[k].length;j++){
                        img.setRGB(j, k, map[k][j] > 0 ? BLACK.getRGB() : WHITE.getRGB());
                    }
                }
                ImageIO.write(img, "BMP", new File(i + ".bmp"));
            }

            int result = 1;
            for(int r : quadrants){
                result *= r;
            }
            System.out.println(result);
            System.out.println(Arrays.toString(quadrants));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double countDistance(Robot robot1, Robot robot2){
        return Math.sqrt((robot2.y - robot1.y) * (robot2.y - robot1.y) + (robot2.x - robot1.x) * (robot2.x - robot1.x));
    }
    public static class Robot {

        int x;
        int y;
        int vX;
        int vY;

        public Robot(int x, int y, int vX, int vY) {
            this.x = x;
            this.y = y;
            this.vX = vX;
            this.vY = vY;
        }
    }
}