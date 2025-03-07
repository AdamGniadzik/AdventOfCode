package org.advent.advent2016.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/advent/advent2016/day10/input.txt"));
        int[] output = new int[1000];
        Map<Integer, Bot> bots = new HashMap<>();
        LinkedList<String[]> queue = new LinkedList<>();
        while(br.ready()){
            queue.add(br.readLine().split(" "));
        }
        while(!queue.isEmpty()){
            String[] input = queue.pollFirst();
            if(input[0].equals("value")){
                int botNumber = Integer.parseInt(input[5]);
                int value = Integer.parseInt(input[1]);
                Bot bot = bots.getOrDefault(botNumber, new Bot(botNumber));
                bot.putValue(value);
                bots.put(botNumber, bot);
            }else{
                int botNumber = Integer.parseInt(input[1]);
                Bot bot = bots.getOrDefault(botNumber, new Bot(botNumber));
                bot.lowDestination = Integer.parseInt(input[6]);
                bot.highDestination = Integer.parseInt(input[11]);
                if(bot.value1 != null && bot.value2 != null) {
                    if (input[5].equals("output")) {
                        output[bot.lowDestination] = bot.getLow();
                    } else {
                        Bot destinationBot = bots.getOrDefault(bot.lowDestination, new Bot(botNumber));
                        destinationBot.putValue(bot.getLow());
                        bots.put(bot.lowDestination, destinationBot);
                    }
                    if (input[10].equals("output")) {
                        output[bot.highDestination] = Math.max(bot.value1, bot.value2);
                    } else {
                        Bot destinationBot = bots.getOrDefault(bot.highDestination, new Bot(botNumber));
                        destinationBot.putValue(bot.getHigh());
                        bots.put(bot.highDestination, destinationBot);
                    }
                }else{
                    queue.add(input);
                }
            }
        }
        bots.entrySet().stream().filter(e->(e.getValue().value1 == 17 && e.getValue().value2 == 61) || (e.getValue().value1 == 61 && e.getValue().value2 == 17)).forEach(e-> System.out.println(e.getKey()));
        System.out.println(output[0] * output[1] * output[2]);

    }


    public static class Bot{
        Integer botNumber;
        Integer value1 = null;
        Integer value2 = null;
        Integer lowDestination;
        Integer highDestination;

        public void putValue(int value){
            if(this.value1== null){
                this.value1 = value;
            }else{
                this.value2 = value;
            }
        }
        public int getLow(){
            return Math.min(value1, value2);
        }

        public int getHigh(){
            return Math.max(value1, value2);
        }

        public Bot(Integer botNumber) {
            this.botNumber = botNumber;
        }
    }
}
