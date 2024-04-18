package com.lime.leetcode.week;

/**
 * Created by YuHang on 2023/11/19.
 */
public class FindMinimumOperations100131 {
    public int findMinimumOperations(String s1, String s2, String s3) {
        if(s1.charAt(0) != s2.charAt(0) || s2.charAt(0) != s3.charAt(0)){
            return -1;
        }
        return 1;

    }

    public long minimumSteps(String s) {
        long count = 0;
        int left = 0, right = s.length() - 1;
        while (left < right && left < s.length() && right >= 0){
            if(s.charAt(left) == '0'){
                while (left < s.length() && s.charAt(left) == '0' ){
                    left++;
                }
                continue;
            }
            if(s.charAt(right) == '1'){
                while (right >= 0 && s.charAt(right) == '1'){
                    right--;
                }
                continue;
            }
            count += (right - left);
            left++;
            right--;
        }
        return count;
    }


    public int maximumXorProduct(long a, long b, int n) {
        long a1 = a, b1 = b;
        long x = 0;
        
        for (int i = 0; i < n; i++){
            if(a1 % 2 == 0 &&  b1 % 2 == 0){
                x += Math.pow(2, i);
            }else if(a1 % 2 == 0 ||  b1 % 2 == 0) {

            }
            a1 /= 2;
            b1 /= 2;
        }
        return (int) ((((a ^ x) % 1000000007) * ((b ^ x) % 1000000007)) % 1000000007);
    }

    public static void main(String[] args) {
        testMaximumXorProduct();
    }

    private static void testMaximumXorProduct() {
        FindMinimumOperations100131 findMinimumOperations100131 = new FindMinimumOperations100131();
        findMinimumOperations100131.maximumXorProduct(12, 5, 4);
    }

    private static void testMinimumSteps() {
        String s = "111111111100100010";
        FindMinimumOperations100131 findMinimumOperations100131 = new FindMinimumOperations100131();
        findMinimumOperations100131.minimumSteps(s);
    }
}
