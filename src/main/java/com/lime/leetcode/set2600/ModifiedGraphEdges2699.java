package com.lime.leetcode.set2600;

import java.util.*;

/**
 * Created by YuHang on 2023/6/9.
 */
public class ModifiedGraphEdges2699 {
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        Map<Integer, List<int[]>> nodeMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++){
            if(nodeMap.containsKey(edges[i][0])){
                nodeMap.get(edges[i][0]).add(edges[i]);
            }else {
                List<int[]> destList = new ArrayList<>();
                destList.add(edges[i]);
                nodeMap.put(edges[i][0], destList);
            }
        }
        //记录是否访问
        int[] reach = new int[n];
        for (int i = 0; i < reach.length; i++){
            reach[i] = -1;
        }
        //标记起始节点
        reach[source] = 0;
        Stack<int[]> stack = new Stack<>();
        //起始节点能到达的列表
        List<int[]> sourceList = nodeMap.get(source);
        for (int i = 0; i < sourceList.size(); i++){
            stack.push(sourceList.get(i));
        }
        //广度搜索
        while (!stack.isEmpty()){
            int[] pop = stack.pop();
            if(pop[1] == destination){
                //下一步是目的节点
                if(pop[2] == -1){
                    //待修改值,差多少就补多少，

                }else {
                    //固定值
                    if(reach[pop[0]] + pop[2] > target){
                        //超过target，不符合要求，移除
                    }else if(reach[pop[0]] + pop[2] == target){
                        //符合要求

                    }else {
                        //如果中间有修改值，可以增大修改值，满足条件
                        //若中间没有修改值，其实不符合要求，可以舍弃
                    }

                }
            }else {
                //下一步不是目的节点
                if(pop[2] == -1){
                    //待修改值,差多少就补多少，
                    if(reach[pop[1]] == -1 || reach[pop[0]] + 1 < reach[pop[1]]) {
                        //未到达或者距离更短
                        List<int[]> temp = nodeMap.get(pop[1]);
                        for (int i = 0; i < temp.size(); i++){
                            stack.push(temp.get(i));
                        }
                        reach[pop[1]] = reach[pop[0]] + 1;
                    }
                }else {
                    //固定值
                    if(reach[pop[1]] == -1 || reach[pop[0]] + pop[2] < reach[pop[1]]) {
                        //未到达或者距离更短
                        List<int[]> temp = nodeMap.get(pop[1]);
                        for (int i = 0; i < temp.size(); i++){
                            stack.push(temp.get(i));
                        }
                        reach[pop[1]] = reach[pop[0]] + pop[2];
                    }
                }
            }
        }

        return new int[1][1];
    }





}
