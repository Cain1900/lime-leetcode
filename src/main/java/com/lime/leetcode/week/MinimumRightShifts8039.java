package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/9/16.
 */
public class MinimumRightShifts8039 {

    public int minimumRightShifts(List<Integer> nums) {
        int count = 0;
        boolean increase = true;
        for(int i = 1; i < nums.size(); i++){
            if(nums.get(i) < nums.get(i - 1)){
                if(increase){
                    //第一次递减
                    increase = false;
                    count++;
                }else {
                    //不是第一次递减
                    return -1;
                }
            }else {
                //递增
                if(!increase){
                    count++;
                }
            }
        }
        if(!increase && nums.get(0) < nums.get(nums.size()) ){
            return -1;
        }
        return count;
    }

    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.size(); i++){
            map.put(nums.get(i), map.getOrDefault(nums.get(i), 0) + 1);
        }
        for (Integer item: map.keySet()){
            if(map.get(item) > nums.size() / 2){
                return map.get(item) - (nums.size() - map.get(item));
            }
        }
        if(nums.size() % 2 == 0){
            return 0;
        }else {
            return 1;
        }
    }

    public int countPairs(List<List<Integer>> coordinates, int k) {
        // x ^ y = a1
        // x ^ x ^ y = x ^ a1
        // y = x ^ a1
        // (x1 ^ x2) + (y1 ^ y2) = k1 + k2 = k
        // x1 ^ x2 = k1; y1 ^ y2 = k2
        // x2 = x1 ^ k1; y2 = y1 ^ k2
        Map<Integer, Map<Integer, Integer>> map = new HashMap();
        int count = 0;
        for(int i = 0; i < coordinates.size(); i++){
            int x = coordinates.get(i).get(0);
            int y = coordinates.get(i).get(1);
            for (int j = 0; j <= k; j++){
                Map<Integer, Integer> temp = map.get(x ^ j);
                if(temp != null){
                    count += temp.getOrDefault(y ^ (k - j), 0);
                }
            }
            Map<Integer, Integer> temp = map.computeIfAbsent(x, key -> new HashMap<>());
            temp.put(y, temp.getOrDefault(y, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        testCountPairs();
    }

    private static void testCountPairs() {
        List<List<Integer>> coordinates = Arrays.asList(Arrays.asList(1,3),Arrays.asList(1,3),Arrays.asList(1,3),Arrays.asList(1,3),Arrays.asList(1,3));
        int k = 0;
        MinimumRightShifts8039 minimumRightShifts8039 = new MinimumRightShifts8039();
        System.out.println(minimumRightShifts8039.countPairs(coordinates, k));
    }

    List<int[]>[]g;
    int[] dp1;
    int[] dp2;

    //从0出发，进行深度搜索
    void dfs1(int u, int fa) {
        int res = 0;
        //遍历每一个从u出发的边
        for (int[] e: g[u]) {
            //从u出发到达t
            int t = e[0];
            //d表示方向
            int d = e[1];
            if (t == fa){
                //后面使用dfs1(t, u),此处避免从u出发到达t，再回到从t出发到达u,跳出死循环
                continue;
            }
            dfs1(t, u);
            res += dp1[t];
            //反向时，加1
            res += (d == -1) ? 1 : 0;
        }
        //
        dp1[u] = res;
    }

    void dfs2(int u, int fa, int w) {
        dp2[u] = dp1[u] + w;
        for (int[] e: g[u]) {
            int t = e[0], d = e[1];
            if (t == fa) continue;
            int x = dp1[u];
            x -= dp1[t];
            x -= (d == -1) ? 1 : 0;
            dfs2(t, u, x + w + (d == 1 ? 1 : 0));
        }
    }

    public int[] minEdgeReversals(int n, int[][] edges) {
        //g[i]记录从i出发能到达的点列表，列表元素是2维数组 [目的点，正向|反向]
        g = new List[n];
        Arrays.setAll(g, x -> new ArrayList<>());
        for (int[] e: edges) {
            int u = e[0], v = e[1];
            //从u出发到达v，正向
            g[u].add(new int[] {v, 1});
            //从v出发到达u，反向
            g[v].add(new int[] {u, -1});
        }
        dp1 = new int[n];
        dfs1(0, -1);
        dp2 = new int[n];
        dfs2(0, -1, 0);
        return dp2;
    }

}
