package com.lime.leetcode.set2700;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by YuHang on 2024/1/9.
 */
public class MinExtraChar2707 {

    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String str : dictionary) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        d[0] = 0;
        for(int i = 0; i < n; i++){

        }
        return d[n];
    }

    public int minLength(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'B' && !stack.isEmpty() && stack.peek() == 'A'){
                stack.pop();
            }else if(c == 'D' && !stack.isEmpty() && stack.peek() == 'C'){
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.size();
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        int[] count = new int[26];
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder ans = new StringBuilder();
        int cur = 25;
        while(cur >= 0){
            if(count[cur] == 0){
                cur--;
                continue;
            }
            int i = 1;
            while(i <= repeatLimit && count[cur] > 0){
                ans.append((char)('a' + cur));
                i++;
                count[cur]--;
            }
            if(i > repeatLimit && count[cur] > 0){
                int pre = cur - 1;
                boolean isFind = false;
                while(pre >= 0){
                    if(count[pre] > 0){
                        ans.append((char)('a' + pre));
                        count[pre]--;
                        isFind = true;
                        break;
                    }else {
                        pre--;
                    }
                }
                if(!isFind){
                    break;
                }
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        //缓存线程池: 核心线程数为0，最大线程数为最大整数，无上限，同步移交队列
        Executors.newCachedThreadPool();
        //固定线程池: 核心线程数等于最大线程数，链表队列，无界
        Executors.newFixedThreadPool(10);
        //单一线程池: 核心线程数等于最大线程数,且等于1，链表队列，无界
        Executors.newSingleThreadExecutor();
        //调度线程数；指定核心线程数，最大线程数为最大整数，无上限，延迟阻塞队列，无界, 按时间调度执行任务的功能，分为延迟执行任务和周期性执行任务。
        Executors.newScheduledThreadPool(10);//ScheduledThreadPoolExecutor不是静态类
        //new DelayedWorkQueue(): 二叉最小堆的优先队列，队列满时会自动扩容
        /**PriorityBlockingQueue: 优先级队列, 有界。
         * Executors.defaultThreadFactory: 默认线程工厂, 相同的NORM_PRIORITY优先级, 非守护进程状态。
         * ThreadPoolExecutor.AbortPolicy: 中止策略, 抛出运行时异常（RejectedExecutionException）。
         * * */
        new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>(11, new Comparator<Runnable>() {
                    @Override
                    public int compare(Runnable o1, Runnable o2) {
                        return 0;
                    }
                }),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        /**ArrayBlockingQueue: 列表队列, 有界。
         * Executors.privilegedThreadFactory: 授权的线程工厂, 在默认线程工厂基础上增加与创建线程池线程拥有相同的AccessControlContext和ContextClassLoader权限。
         * ThreadPoolExecutor.CallerRunsPolicy: 调用者运行策略, 将某些任务回退到调用者。
         * * */
        new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.privilegedThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        /**LinkedBlockingQueue: 链表队列，无界。
         * new ThreadFactory: 自定义线程工厂, 可指定线程名称。
         * ThreadPoolExecutor.DiscardPolicy: 抛弃策略, 偷偷抛弃提交的任务，不会抛出异常。
         * * */
        new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return null;
                    }
                },
                new ThreadPoolExecutor.DiscardPolicy());

        /**SynchronousQueue: 同步移交队列，不是一个真正的队列，而是一种线程之间移交的机制。
         * Executors.defaultThreadFactory:
         * ThreadPoolExecutor.DiscardOldestPolicy: 抛弃最旧策略, 偷偷抛弃最早提交的任务，不会抛出异常。
         * * */
        new ThreadPoolExecutor(1, 1, 100, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
