package com.lime.leetcode.set200;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by YuHang on 2023/9/10.
 */
public class CanFinish207 {

    //广度搜索
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
            return true;
        }else {
            return false;
        }
    }

    int[] reach;
    List<List<Integer>> preList;
    boolean valid = true;

    //深度搜索
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
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
        return valid;
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
    }

}
