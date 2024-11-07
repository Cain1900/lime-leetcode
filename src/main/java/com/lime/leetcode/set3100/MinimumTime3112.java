package com.lime.leetcode.set3100;

import java.util.*;

public class MinimumTime3112 {

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, List<Integer[]>> map = new HashMap<>();
        for (int[] item: edges){
            if(map.containsKey(item[0])){
                map.get(item[0]).add(new Integer[]{item[1], item[2]});
            }else {
                List<Integer[]> list = new ArrayList<>();
                list.add(new Integer[]{item[1], item[2]});
                map.put(item[0], list);
            }
            if(map.containsKey(item[1])){
                map.get(item[1]).add(new Integer[]{item[0], item[2]});
            }else {
                List<Integer[]> list = new ArrayList<>();
                list.add(new Integer[]{item[0], item[2]});
                map.put(item[1], list);
            }
        }
        for (Integer item: map.keySet()){
            Collections.sort(map.get(item), (a, b) -> a[1] - b[1]);
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        PriorityQueue<Integer[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new Integer[]{0, 0});
        while (!queue.isEmpty()){
            Integer[] poll = queue.poll();
            List<Integer[]> list = map.get(poll[0]);
            if(list != null){
                for (Integer[] item: list){
                    int reach = poll[1] + item[1];
                    if(reach >= disappear[item[0]]){
                        //已经消失
                        continue;
                    }
                    if(ans[item[0]] == -1 || reach < ans[item[0]]){
                        ans[item[0]] = reach;
                        queue.add(new Integer[]{item[0], reach});
                    }
                }
            }
        }
        return ans;
    }

    public int[] minimumTime1(int n, int[][] edges, int[] disappear) {
        Map<Integer, List<Integer[]>> map = new HashMap<>();
        for (int[] item: edges){
            if(map.containsKey(item[0])){
                map.get(item[0]).add(new Integer[]{item[1], item[2]});
            }else {
                List<Integer[]> list = new ArrayList<>();
                list.add(new Integer[]{item[1], item[2]});
                map.put(item[0], list);
            }
            if(map.containsKey(item[1])){
                map.get(item[1]).add(new Integer[]{item[0], item[2]});
            }else {
                List<Integer[]> list = new ArrayList<>();
                list.add(new Integer[]{item[0], item[2]});
                map.put(item[1], list);
            }
        }
        for (Integer item: map.keySet()){
            Collections.sort(map.get(item), (a, b) -> a[1] - b[1]);
        }
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        PriorityQueue<Integer[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new Integer[]{0, 0});
        while (!queue.isEmpty()){
            Integer[] poll = queue.poll();
            List<Integer[]> list = map.get(poll[0]);
            if(list != null){
                for (Integer[] item: list){
                    int reach = poll[1] + item[1];
                    if(reach >= disappear[item[0]]){
                        //已经消失
                        continue;
                    }
                    if(ans[item[0]] == -1 || reach < ans[item[0]]){
                        ans[item[0]] = reach;
                        queue.add(new Integer[]{item[0], reach});
                    }
                }
            }
        }
        return ans;
    }

}
