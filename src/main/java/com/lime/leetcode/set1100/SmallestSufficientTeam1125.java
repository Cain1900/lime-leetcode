package com.lime.leetcode.set1100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YuHang on 2023/4/8.
 */
public class SmallestSufficientTeam1125 {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int m = people.size(), n = req_skills.length;
        Map<String, Integer> skillIndexMap = new HashMap<>();
        for(int i = 0; i < n; i++){
            //技能名和二进制位置编码
            skillIndexMap.put(req_skills[i], i);
        }
        //遍历包含到该二进制位置编码的结果集
        List<Integer>[] dp = new List[1 << n];
        //不包含任务技能，所需人数为空, 遍历结果的起始值
        dp[0] = new ArrayList<>();
        for (int i = 0; i < m; i++){
            //遍历每个people
            int cur_skill = 0;
            for (String s : people.get(i)) {
                //遍历people会的技能数组
                //根据技能名取技能的二进制位置编码， 生成people会的技能二进制编码
                cur_skill |= 1 << skillIndexMap.get(s);
            }
            for (int prev = 0; prev < dp.length; ++prev) {
                if (dp[prev] == null) {
                    //dp[prev]表示之前到达的二进制编码
                    //若dp[prev] == null 表示之前还从未到底这个二进制编码，无须进行后续操作
                    continue;
                }
                //之前遍历能到达的二进制编码prev， 加上新一个people能到达的二进制编码combo
                int comb = prev | cur_skill;
                if (dp[comb] == null || dp[prev].size() + 1 < dp[comb].size()) {
                    //dp[comb] == null : 还从未到达过， 第一次到达
                    /* dp[comb] != null : 不是第一次到达， 比较再次到达人数dp[prev].size() + 1 与之前到达dp[comb].size()人数
                    * 1、若 dp[prev].size() + 1 < dp[comb].size()， 新到达人数更少， 重新建立对象
                    * 2、若 dp[prev].size() + 1 >= dp[comb].size()  不需要重新建立对象
                    * * */
                    dp[comb] = new ArrayList<>(dp[prev]);
                    dp[comb].add(i);
                }
            }
        }
        return dp[(1 << n) - 1].stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        SmallestSufficientTeam1125 smallestSufficientTeam1125 = new SmallestSufficientTeam1125();
        String[] req_skills = new String[]{"java","nodejs","reactjs"};
        List<List<String>> people = new ArrayList<List<String>>(){{
            add(new ArrayList<String>(){{
                add("java");
            }});
            add(new ArrayList<String>(){{
                add("nodejs");
            }});
            add(new ArrayList<String>(){{
                add("nodejs");
                add("reactjs");
            }});
        }};
        System.out.println(smallestSufficientTeam1125.smallestSufficientTeam(req_skills, people));
    }
}
