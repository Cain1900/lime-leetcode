package com.lime.leetcode.set800;

import java.util.*;

/**
 * Created by YuHang on 2023/7/19.
 */
public class RobotSim874 {

    public int robotSim(int[] commands, int[][] obstacles) {
        int max = 0;
        Map<Integer, List<Integer>> xObstacles = new HashMap<>();
        Map<Integer, List<Integer>> yObstacles = new HashMap<>();
        for (int i = 0; i < obstacles.length; i++){
            if(xObstacles.containsKey(obstacles[i][0])){
                xObstacles.get(obstacles[i][0]).add(obstacles[i][1]);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(obstacles[i][1]);
                xObstacles.put(obstacles[i][0], list);
            }
            if(yObstacles.containsKey(obstacles[i][1])){
                yObstacles.get(obstacles[i][1]).add(obstacles[i][0]);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(obstacles[i][0]);
                yObstacles.put(obstacles[i][1], list);
            }
        }
        int[][] direList = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[] post = new int[]{0, 0, 0};
        for (int command: commands){
            if(command == -1){
                post[2] = (post[2] + 1) % 4;
            }else if(command == -2){
                post[2] = (post[2] + 3) % 4;
            }else {
                if(post[2] == 0 || post[2] == 2){
                    //x不变，y上下移动
                    if(xObstacles.containsKey(post[0])){
                        //可能有障碍物
                        if(post[2] == 0){
                            int yMax = post[1] + command;
                            for (Integer yObstacle: xObstacles.get(post[0])){
                                if(yObstacle > post[1]){
                                    yMax = Math.min(yMax, yObstacle - 1);
                                }
                            }
                            post[1] = yMax;
                        }else {
                            int yMin = post[1] - command;
                            for (Integer yObstacle: xObstacles.get(post[0])){
                                if(yObstacle < post[1]){
                                    yMin = Math.max(yMin, yObstacle + 1);
                                }
                            }
                            post[1] = yMin;
                        }
                    }else {
                        //没有障碍物
                        post[1] = post[1] + command * direList[post[2]][1];
                    }
                }else {
                    //y不变，x左右移动
                    if(yObstacles.containsKey(post[1])){
                        //可能有障碍物
                        if(post[2] == 1){
                            int xMax = post[0] + command;
                            for (Integer xObstacle: yObstacles.get(post[1])){
                                if(xObstacle > post[0]){
                                    xMax = Math.min(xMax, xObstacle - 1);
                                }
                            }
                            post[0] = xMax;
                        }else {
                            int xMin = post[0] - command;
                            for (Integer xObstacle: yObstacles.get(post[1])){
                                if(xObstacle < post[0]){
                                    xMin = Math.max(xMin, xObstacle + 1);
                                }
                            }
                            post[0] = xMin;
                        }
                    }else {
                        //没有障碍物
                        post[0] = post[0] + command * direList[post[2]][0];
                    }
                }
                max = Math.max(max, post[0] * post[0] + post[1] * post[1]);
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[] commands = new int[]{-2,8,3,7,-1};
        int[][] obstacles = new int[][]{{-4,-1}, {1,-1}, {1,4}, {5,0}, {4,5}, {-2,-1}, {2,-5}, {5,1}, {-3,-1}, {5,-3}};
        RobotSim874 robotSim874 = new RobotSim874();
        System.out.println(robotSim874.robotSim(commands, obstacles));
    }

}
