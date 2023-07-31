package com.lime.leetcode.set800;

/**
 * Created by YuHang on 2023/4/1.
 */
public class MaskPII831 {

    public String maskPII(String s) {
        char c = s.charAt(0);
        if((c >= 'a' & c <= 'z') || (c >= 'A' & c <= 'Z')){
            return email(s);
        }else {
            return telephone(s);
        }
    }

    public String email(String s) {
        StringBuilder ans = new StringBuilder();
        ans.append(upToDown(s.charAt(0))).append("*****");
        int i = 1;
        for(; i < s.length(); i++){
            if(s.charAt(i) == '@'){
                ans.append(upToDown(s.charAt(i - 1))).append('@');
                i++;
                break;
            }
        }
        for (; i < s.length(); i++){
            ans.append(upToDown(s.charAt(i)));
        }
        return ans.toString();
    }

    public char upToDown(char c){
        if((c >= 'A' & c <= 'Z')){
            return (char) (c + 'a' - 'A');
        }else {
            return c;
        }
    }

    public String telephone(String s) {
        int[] nums = new int[13];
        int index = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                nums[++index] = s.charAt(i) - '0';
            }
        }
        StringBuilder ans = new StringBuilder();
        if(index == 10){
            ans.append("***-***-");
        }else if(index == 11){
            ans.append("+*-***-***-");
        }else if(index == 12){
            ans.append("+**-***-***-");
        }else if(index == 13){
            ans.append("+***-***-***-");
        }
        ans.append(nums[index -3]).append(nums[index -2]).append(nums[index -1]).append(nums[index]);
        return ans.toString();
    }

    public static void main(String[] args) {
        MaskPII831 maskPII831 = new MaskPII831();
        System.out.println(maskPII831.maskPII("1(234)567-890"));
    }
}
