package com.lime.leetcode.set200;

import java.util.*;

/**
 * Created by YuHang on 2023/9/10.
 */
public class FindOrder210 {

    int[] reach;
    List<List<Integer>> preList;
    boolean valid = true;
    Stack<Integer> stack = new Stack<>();

    //深度搜索
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        reach = new int[numCourses];
        preList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++){
            preList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++){
            preList.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        for(int i = 0; i < numCourses && valid; i++){
            if(reach[i] == 0){
                dfs(i);
            }
        }
        if(!valid){
            return new int[0];
        }
        int[] ans = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty()){
            ans[i] = stack.pop();
            i++;
        }
        return ans;
    }

    public void dfs(int i){
        reach[i] = 1;
        for (Integer item: preList.get(i)){
            if(reach[item] == 0){
                dfs(item);
                if(!valid){
                    return;
                }
            }else if(reach[item] == 1){
                valid = false;
                return;
            }
        }
        reach[i] = 2;
        stack.push(i);
    }

    //广度搜索
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] preCount = new int[numCourses];
        List<List<Integer>> preList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++){
            preList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++){
            preList.get(prerequisites[i][1]).add(prerequisites[i][0]);
            preCount[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(preCount[i] == 0){
                queue.offer(i);
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            ans.add(poll);
            for (Integer item: preList.get(poll)){
                preCount[item]--;
                if(preCount[item] == 0){
                    queue.offer(item);
                }
            }
        }
        if(ans.size() == numCourses){
            return ans.stream().mapToInt(Integer::intValue).toArray();
        }else {
            return new int[0];
        }
    }
}
