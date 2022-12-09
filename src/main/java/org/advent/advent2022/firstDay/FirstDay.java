package org.advent.advent2022.firstDay;

import java.io.*;

public class FirstDay {
    public static void main(String[] args) {
        int sum = 0;
        int maxSum = 0;
        try {

            InputStream is = FirstDay.class.getClassLoader().getResourceAsStream("org/advent/advent2022/firstDay/input.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.ready()) {
                String line = br.readLine();
                if (line.equals("")) {
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                    sum = 0;
                    continue;
                }
                sum += Integer.parseInt(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(maxSum);
    }


}
