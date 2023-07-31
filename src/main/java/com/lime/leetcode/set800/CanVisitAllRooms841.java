package com.lime.leetcode.set800;

import java.util.*;

/**
 * Created by YuHang on 2023/5/8.
 */
public class CanVisitAllRooms841 {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int[] visit = new int[rooms.size()];
        int count = dfs(rooms, visit, 0);
        return count == rooms.size();
    }

    private int dfs(List<List<Integer>> rooms, int[] visit, int index) {
        int count = 1;
        //设置为已访问到
        visit[index] = 1;
        List<Integer> keyList = rooms.get(index);
        for (int i = 0; i < keyList.size(); i++){
            if(visit[keyList.get(i)] == 0){
                count += dfs(rooms, visit, keyList.get(i));
            }
        }
        return count;
    }


    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        //搜索队列
        Deque<Integer> deque = new LinkedList<>();
        //已访问集合
        Set<Integer> visitSet = new HashSet<>();
        //拿到钥匙集合
        Set<Integer> querySet = new HashSet<>();
        deque.push(0);
        querySet.add(0);
        while (!deque.isEmpty()){
            int index = deque.pop();
            //检查是否已访问
            if(visitSet.contains(index)){
                //已访问，跳过
                continue;
            }
            //未访问
            visitSet.add(index);
            for (Integer item: rooms.get(index)){
                //拿到钥匙集合
                deque.push(item);
                querySet.add(item);
            }
        }
        return visitSet.size() == rooms.size();
    }

    public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
        //搜索队列
        Queue<Integer> queue = new LinkedList<>();
        //已访问集合
        Set<Integer> visitSet = new HashSet<>();
        //拿到钥匙集合
        Set<Integer> querySet = new HashSet<>();
        queue.offer(0);
        querySet.add(0);
        while (!queue.isEmpty()){
            int index = queue.poll();
            //检查是否已访问
            if(visitSet.contains(index)){
                //已访问，跳过
                continue;
            }
            //未访问
            visitSet.add(index);
            for (Integer item: rooms.get(index)){
                //拿到钥匙集合
                queue.offer(item);
                querySet.add(item);
            }
        }
        return visitSet.size() == rooms.size();
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = Arrays.asList(Arrays.asList(1), Arrays.asList(2),Arrays.asList(3),Arrays.asList());
        CanVisitAllRooms841 canVisitAllRooms841 = new CanVisitAllRooms841();
        System.out.println(canVisitAllRooms841.canVisitAllRooms(rooms));
    }
}
