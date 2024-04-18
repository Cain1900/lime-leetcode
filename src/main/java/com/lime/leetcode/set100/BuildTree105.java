package com.lime.leetcode.set100;

import com.lime.leetcode.TreeNode;

/**
 * Created by YuHang on 2024/2/20.
 */
public class BuildTree105 {

    int[] pre;
    int[] in;
    int mid = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.pre = preorder;
        this.in = inorder;
        return dp(0, inorder.length - 1);
    }

    public TreeNode dp(int start, int end){
        if(start > end){
            return null;
        }
        int cur = mid;
        mid++;
        if(start == end){
            return new TreeNode(in[start]);
        }
        int i = start;
        for(; i < end; i++){
            if(pre[cur] == in[i]){
                break;
            }
        }
        return new TreeNode(pre[cur], dp(start, i - 1), dp(i + 1, end));
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        BuildTree105 buildTree105 = new BuildTree105();
        System.out.println(buildTree105.buildTree(preorder, inorder));
    }
}
