package com.lime.leetcode.week;

import java.util.*;

/**
 * Created by YuHang on 2023/11/5.
 */
public class FindChampion100116 {

    public int findChampion(int n, int[][] edges) {
        int[] degree = new int[n];
        for(int i = 0; i < edges.length; i++){
            degree[edges[i][1]]++;
        }
        int owner = -1;
        for (int i = 0; i < n; i++){
            if(degree[i] == 0){
                if(owner == -1){
                    owner = i;
                }else {
                    return -1;
                }
            }
        }
        return owner;
    }

    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = values.length;
        int[] degree = new int[n];
        long[] sum = new long[n];
        long[] remove = new long[n];
        Map<Integer, Set<Integer>> nextMap = new HashMap<>();
        for(int i = 0; i < edges.length; i++){
            //度
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
            //记录下一条边
            if(nextMap.containsKey(edges[i][0])){
                nextMap.get(edges[i][0]).add(edges[i][1]);
            }else {
                Set<Integer> set = new HashSet<>();
                set.add(edges[i][1]);
                nextMap.put(edges[i][0], set);
            }
            if(nextMap.containsKey(edges[i][1])){
                nextMap.get(edges[i][1]).add(edges[i][0]);
            }else {
                Set<Integer> set = new HashSet<>();
                set.add(edges[i][0]);
                nextMap.put(edges[i][1], set);
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++){
            if(degree[i] == 1 && i != 0){
                queue.add(i);
                //移除的值
                remove[i] = values[i];
            }
        }
        Integer root = -1;
        while (!queue.isEmpty()){
            Integer poll = queue.poll();
            for (Integer next: nextMap.get(poll)){
                sum[next] += sum[poll];
                remove[next] += remove[poll];
                degree[next]--;
                if((next != 0 && degree[next] == 1) || (next == 0 && degree[next] == 0)){
                    if(remove[next] <= values[next]){
                        sum[next] += values[next];
                    }else {
                        sum[next] += remove[next];
                        remove[next] = values[next];
                    }
                    if(next != 0){
                        queue.add(next);
                    }
                }
            }

        }
        return sum[0];
    }

    public static void main(String[] args) {
        //testMaximumScoreAfterOperations();
        //testMaxBalancedSubsequenceSum();
        testDistributeCandies();
    }

    private static void testMaximumScoreAfterOperations() {
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};
        int[] values = {20,10,9,7,4,3,5};
        FindChampion100116 findChampion100116 = new FindChampion100116();
        findChampion100116.maximumScoreAfterOperations(edges, values);
    }

    public long maxBalancedSubsequenceSum(int[] nums) {
        Map<Integer, Long> map = new HashMap();
        for(int i = 0; i < nums.length; i++){
            map.put(i, Long.valueOf(nums[i]));
            Iterator<Map.Entry<Integer, Long>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Integer item = iterator.next().getKey();
                if(i != item && nums[i] - nums[item] >= i - item){
                    map.put(i, Math.max(map.get(i), map.get(item) + nums[i]));
                    iterator.remove();
                }
            }
        }
        long max = nums[0];
        for (Integer item: map.keySet()){
            max = Math.max(max, map.get(item));
        }
        return max;
    }

    private static void testMaxBalancedSubsequenceSum() {
        int[] nums = {-1,2,-5,5,-2,6,-5,2};
        FindChampion100116 findChampion100116 = new FindChampion100116();
        findChampion100116.maxBalancedSubsequenceSum(nums);
    }


    public long distributeCandies(int n, int limit) {
        long ans = 0;
        for(int i = 0; i <= limit; i++){
            ans += Math.max((Math.min(n - i, limit) - Math.max(n - i - limit, 0)) + 1, 0);
        }
        return ans;
    }

    private static void testDistributeCandies() {
        FindChampion100116 findChampion100116 = new FindChampion100116();
        findChampion100116.distributeCandies(5, 2);
    }

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for(List<String> list: access_times){
            if(map.containsKey(list.get(0))){
                map.get(list.get(0)).add(Integer.valueOf(list.get(1)));
            }else {
                List<Integer> time = new ArrayList<>();
                time.add(Integer.valueOf(list.get(1)));
                map.put(list.get(0), time);
            }
        }
        for(String name: map.keySet()){
            List<Integer> list = map.get(name);
            Collections.sort(list);
            if(list.size() < 3){
                continue;
            }
            for(int i = 2; i < list.size(); i++){
                if(list.get(i) - list.get(i - 2) < 100){
                    ans.add(name);
                    break;
                }
            }
        }
        return ans;
    }

    public int maximumStrongPairXor(int[] nums) {
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j])){
                    max = Math.max(max, nums[i] ^ nums[j]);
                }

            }
        }
        return max;
    }
}
