package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/10/1.
 */
public class CountVisitedNodes100075 {

    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        List<Integer>[] grid = new List[n];
        Arrays.setAll(grid, e -> new ArrayList<>());
        int[] degree = new int[n];
        for (int i = 0; i < n; i++){
            int j = edges.get(i);
            grid[j].add(i);
            //只有入度
            degree[j]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if(degree[i] == 0){
                //叶子节点
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            //叶子节点减掉一条边，度数为0
            Integer next = edges.get(poll);
            degree[next]--;
            if(degree[next] == 0){
                queue.offer(next);
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++){
            if(degree[i] <= 0){
                continue;
            }
            List<Integer> ring = new ArrayList<>();
            for (int x = i;; x = edges.get(x)){
                degree[x] = -1;
                ring.add(x);
                if(edges.get(x) == i){
                    break;
                }
            }
            for (int r : ring) {
                rdfs(r, ring.size(), grid, degree, ans); // 为方便计算，以 ring.size() 作为初始深度
            }
        }
        return ans;
    }

    private void rdfs(int r, int size, List<Integer>[] grid, int[] degree, int[] ans) {
        ans[r] = size;
        for (int b : grid[r]){
            if (degree[b] == 0) { //树枝上的点在拓扑排序后，入度均为 0
                rdfs(b, size + 1, grid, degree, ans);
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> edges = Arrays.asList(1,2,0,0);
        CountVisitedNodes100075 countVisitedNodes100075 = new CountVisitedNodes100075();
        System.out.println(countVisitedNodes100075.countVisitedNodes(edges));
    }
}
