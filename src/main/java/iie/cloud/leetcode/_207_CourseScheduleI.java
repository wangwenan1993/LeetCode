package iie.cloud.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class _207_CourseScheduleI {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(numCourses);
        for(int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int[] prer: prerequisites) {
            graph.get(prer[1]).add(prer[0]);
        }
        boolean flag = true;
        for(int i = 0; i < numCourses; i++) {
            boolean visited[] = new boolean[numCourses];
            boolean tmp = dfs(graph, i, visited);
            System.out.println(tmp);
            flag &= tmp;
            if(!flag) break;
        }
        return flag;
    }

    public boolean dfs(ArrayList<ArrayList<Integer>> graph, int curr, boolean[] visited) {
        boolean flag = true;
        if(visited[curr]) {
            return false;
        }

        visited[curr] = true;
        ArrayList<Integer> curr_list = graph.get(curr);
        for(int i = 0; i < curr_list.size(); i++) {
            boolean[] copy = Arrays.copyOf(visited, visited.length);
            flag &= dfs(graph, curr_list.get(i), copy);
            if(!flag) break;
        }
        return flag;
    }

    public static void main(String[] args) {
        _207_CourseScheduleI cs = new _207_CourseScheduleI();
        int num = 2;
        int[][] prer = {{0, 1}, {1, 0}};
        System.out.println(cs.canFinish(num, prer));
    }
}
