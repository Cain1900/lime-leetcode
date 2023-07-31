package com.lime.leetcode.set2400;

/**
 * Created by YuHang on 2023/4/17.
 */
public class CountDaysTogether2409 {

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] prefixSum = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 1; i <= 12; i++) {
            prefixSum[i] = prefixSum[i - 1] + prefixSum[i];
        }
        int arriveAliceDay = calculateDayOfYear(arriveAlice, prefixSum);
        int leaveAliceDay = calculateDayOfYear(leaveAlice, prefixSum);
        int arriveBobDay = calculateDayOfYear(arriveBob, prefixSum);
        int leaveBobDay = calculateDayOfYear(leaveBob, prefixSum);
        return Math.max(Math.min(leaveAliceDay, leaveBobDay) - Math.max(arriveAliceDay, arriveBobDay) + 1 , 0);
    }

    public int calculateDayOfYear(String date, int[] prefixSum){
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3));
        return prefixSum[month - 1] + day;
    }
}
