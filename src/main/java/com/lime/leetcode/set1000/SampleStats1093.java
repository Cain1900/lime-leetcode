package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/5/27.
 */
public class SampleStats1093 {

    public double[] sampleStats(int[] count) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        int mode = 0;
        int modeCount = 0;
        long sum = 0;
        int num = 0;
        for(int i = 0; i < count.length; i++){
            if(count[i] != 0){
                if(min == Integer.MAX_VALUE){
                    min = i;
                }
                max = i;
                //累计总数
                sum += (long) count[i] * i;
                //累计次数
                num += count[i];
                if(count[i] > modeCount){
                    //整数 i 在样本中出现的次数较大
                    mode = i;
                    modeCount = count[i];
                }
            }
        }
        double mean = (double) sum / num;
        int left = (num + 1)/2, right = 0;
        if(num % 2 == 0){
            right = left + 1;
        }else {
            right = (num + 1)/2;
        }
        int medianCount = 0;
        double median = 0;
        for (int i = 0; i < count.length; i++){
            if(count[i] != 0){
                if (medianCount < right && medianCount + count[i] >= right) {
                    median += i;
                }
                if (medianCount < left && medianCount + count[i] >= left) {
                    median += i;
                }
                medianCount += count[i];
            }
        }
        return new double[]{min, max, mean, median / 2, mode};
    }
}
