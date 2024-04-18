package com.lime.leetcode.set2200;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/9/28.
 */
public class FullBloomFlowers2251 {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int n = flowers.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < flowers.length; i++){
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int[] ans = new int[people.length];
        for(int i = 0; i < people.length; i++){
            ans[i] = startFlower(start, people[i]) - endFlower(end, people[i]);
        }
        return ans;
    }

    private int startFlower(int[] start, int time) {
        int l = 0, r = start.length - 1;
        int res = start.length;
        while (l <= r){
            int middle = (l + r) >> 1;
            if(start[middle] <= time){
                //l==r时 且end[middle] == time。此时取 l = middle + 1，不满足条件，所以 l - 1为目标值
                res = middle;
                l = middle + 1;
            }else {
                r = middle - 1;
            }
        }
        // (l - 1) + 1 = l
        return res;
    }

    private int endFlower(int[] end, int time) {
        int l = 0, r = end.length - 1;
        while (l <= r){
            int middle = (l + r) >> 1;
            if(end[middle] >= time){
                //l==r时 且end[middle] == time。此时取 r = middle - 1，不满足条件，所以 r + 1为目标值
                r = middle - 1;
            }else {
                l = middle + 1;
            }
        }
        // n - ((r + 1) + 1) = l
        return l;
    }

    public static void main(String[] args) {
        int[][] flowers = new int[][]{{1,6},{3,7},{9,12},{4,13}};
        int[] people = {2,3,7,11};
        FullBloomFlowers2251 fullBloomFlowers2251 = new FullBloomFlowers2251();
        System.out.println(fullBloomFlowers2251.fullBloomFlowers(flowers, people));


    }
}
