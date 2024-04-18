package com.lime.leetcode.week;

/**
 * Created by YuHang on 2024/1/7.
 */
public class Week20240107 {

    public int areaOfMaxDiagonal(int[][] dimensions) {
        long maxLength = 0;
        int maxArea = 0;
        for(int i = 0; i < dimensions.length; i++){
            long s = dimensions[i][0] * dimensions[i][0] + dimensions[i][1] * dimensions[i][1];
            if(s > maxLength){
                maxLength = s;
                maxArea = dimensions[i][0] * dimensions[i][1];
            }else if(s == maxLength){
                maxArea = Math.max(maxArea, dimensions[i][0] * dimensions[i][1]);
            }
        }
        return maxArea;
    }

    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if( a == e ){
            if(a == c && ((b < d && d < f) || (b > d && d > f))){
                return 2;
            }else {
                return 1;
            }
        }else if( b == f ){
            if( b == d && ((a < c && c < e) || (a > c && c > e))){
                return 2;
            }else {
                return 1;
            }
        }else if(Math.abs(c - e) == Math.abs(d - f)){
            //
            if(Math.abs(c - a) == Math.abs(d - b)){

                if((c > a && a > e && d < b && b < e) || (e > a && a > c && f < b && b < d)){
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        return 2;
    }


}
