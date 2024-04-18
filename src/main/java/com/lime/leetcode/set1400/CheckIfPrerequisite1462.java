package com.lime.leetcode.set1400;

import java.util.*;

/**
 * Created by YuHang on 2023/9/12.
 */
public class CheckIfPrerequisite1462 {

    int[] visited;
    List<List<Integer>> nextList;
    int[][] preArray;

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        visited = new int[numCourses];
        nextList = new ArrayList<>();
        preArray = new int[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++){
            nextList.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++){
            nextList.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        for(int i = 0; i < numCourses; i++){
            if(visited[i] == 0){
                dfs(i);
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            ans.add(preArray[queries[i][0]][queries[i][1]] == 1? Boolean.TRUE: Boolean.FALSE);
        }
        return ans;
    }

    public void dfs(int i){
        visited[i] = 1;
        for (Integer j: nextList.get(i)){
            if(visited[j] == 0){
                dfs(j);
            }
            preArray[i][j] = 1;
            for (int k = 0; k < preArray.length; k++){
                preArray[i][k] = preArray[i][k] | preArray[j][k];
            }
        }
        visited[i] = 2;
    }


    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        int[] visited = new int[numCourses];
        List<List<Integer>> nextList = new ArrayList<>();
        List<Set<Integer>> preSet = new ArrayList<>();
        for (int i = 0; i < numCourses; i++){
            nextList.add(new ArrayList<>());
            preSet.add(new HashSet<>());
        }
        for (int i = 0; i < prerequisites.length; i++){
            nextList.get(prerequisites[i][1]).add(prerequisites[i][0]);
            visited[prerequisites[i][0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++){
            if(visited[i] == 0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            Integer i = queue.poll();
            for(Integer j: nextList.get(i)){
                preSet.get(j).add(i);
                preSet.get(j).addAll(preSet.get(i));
                visited[j]--;
                if(visited[j] == 0){
                    queue.offer(j);
                }
            }
        }
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < queries.length; i++){
            if(preSet.get(queries[i][1]).contains(queries[i][0])){
                ans.add(Boolean.TRUE);
            }else {
                ans.add(Boolean.FALSE);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        int[][] queries = new int[][]{{1, 0},{0, 1}};
        CheckIfPrerequisite1462 checkIfPrerequisite1462 = new CheckIfPrerequisite1462();
        System.out.println(checkIfPrerequisite1462.checkIfPrerequisite(numCourses, prerequisites, queries));
    }
}
