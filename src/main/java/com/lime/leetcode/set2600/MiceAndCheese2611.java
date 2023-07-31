package com.lime.leetcode.set2600;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by YuHang on 2023/6/7.
 */
public class MiceAndCheese2611 {

    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sum = 0;
        int[] more = new int[reward1.length];
        for (int i = 0; i < reward1.length; i++){
            sum += reward2[i];
            more[i] = reward1[i] - reward2[i];
        }
        Arrays.sort(more);
        for (int i = more.length - 1; i > more.length - 1 - k ; i--){
            sum += more[i];
        }
        return sum;
    }
}
