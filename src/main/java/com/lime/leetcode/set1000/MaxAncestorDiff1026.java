package com.lime.leetcode.set1000;

/**
 * Created by YuHang on 2023/4/18.
 */
public class MaxAncestorDiff1026 {

    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }


    public int dfs(TreeNode root, int min, int max){
        if(root == null){
            return 0;
        }
        int ans = Math.max(Math.abs(min - root.val), Math.abs(max - root.val));
        ans = Math.max(ans, dfs(root.left, Math.min(min, root.val), Math.max(max, root.val)));
        ans = Math.max(ans, dfs(root.right, Math.min(min, root.val), Math.max(max, root.val)));
        return ans;
    }

    public int maxAncestorDiff1(TreeNode root) {
        int[] count = count(root);
        return count[0];
    }

    public int[] count(TreeNode root){
        int[][] count = new int[2][3];
        if(root.left != null){
            count[0] = count(root.left);
        }else {
            count[0] = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        if(root.right != null){
            count[1] = count(root.right);
        }else {
            count[1] = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int ans = Math.max(count[0][0], count[1][0]);
        int min = Math.min(count[0][1], count[1][1]);
        int max = Math.max(count[0][2], count[1][2]);
        if(min <= max){
            if(root.val <= min){
                ans = Math.max(ans, max - root.val);
            }else if( root.val >= max) {
                ans = Math.max(ans, root.val - min);
            }else {
                ans = Math.max(ans, Math.max(root.val - min, max - root.val));
            }
        }
        return new int[]{ans, Math.min(root.val, Math.min(count[0][1], count[1][1])),  Math.max(root.val, Math.max(count[0][2], count[1][2]))};
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
