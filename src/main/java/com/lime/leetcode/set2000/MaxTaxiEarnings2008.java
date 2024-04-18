package com.lime.leetcode.set2000;

import java.util.Arrays;

/**
 * Created by YuHang on 2023/12/8.
 */
public class MaxTaxiEarnings2008 {

    public long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);
        long[] earnings = new long[n + 1];
        int rideIndex = 0;
        for(int i = 1; i <= n; ){
            if( rideIndex == rides.length || i < rides[rideIndex][1]){
                earnings[i] = Math.max(earnings[i], earnings[i - 1]);
                i++;
            }else if(i == rides[rideIndex][1]){
                earnings[i] = Math.max(earnings[i], earnings[rides[rideIndex][0]] + rides[rideIndex][1] - rides[rideIndex][0] + rides[rideIndex][2]);
                rideIndex++;
            }
        }
        return earnings[n];
    }

    public static void main(String[] args) {
        int n = 20;
        int[][] rides = {{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}};
        MaxTaxiEarnings2008 maxTaxiEarnings2008 = new MaxTaxiEarnings2008();
        System.out.println(maxTaxiEarnings2008.maxTaxiEarnings(n, rides));
    }
}
