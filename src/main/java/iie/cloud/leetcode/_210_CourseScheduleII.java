package iie.cloud.leetcode;

import javax.swing.text.html.parser.Entity;
import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class _210_CourseScheduleII {

    public int[] canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(numCourses);
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int[] prer: prerequisites) {
            graph.get(prer[1]).add(prer[0]);
            indegree.put(prer[0], indegree.getOrDefault(prer[0], 0) + 1);
        }
        boolean flag = true;
        List<ArrayList<Integer>> res = new ArrayList<>();
        while(indegree.size() > 0) {
            int min_count = find0IndegreeNode(indegree);
            if(min_count == -1) {
                return new int[] {};
            }
        }
        return new int[]{};
    }

//    public int dfs(ArrayList<ArrayList<Integer>> graph, Map<Integer, Integer> indegree, )
    
    public int find0IndegreeNode(Map<Integer, Integer> indegree) {
        int min = Integer.MAX_VALUE;
        int index = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> e: indegree.entrySet()) {
            if(e.getValue() < min) {
                min = e.getValue();
                index = e.getKey();
            }
        }
        if(min > 0) {
            index = -1;
        }
        return index;
    }

    public static void main(String[] args) {
        _210_CourseScheduleII cs = new _210_CourseScheduleII();
        int num = 2;
        int[][] prer = {{0, 1}, {1, 0}};
        System.out.println(cs.canFinish(num, prer));
    }
}
