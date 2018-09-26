package iie.cloud.dp;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.

 Example 1:
 [[1,3,1],
 [1,5,1],
 [4,2,1]]
 Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the sum.

 */
public class _70_MinimumPathSum {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {1,2},
                {5,6},
                {1,1}
        };
        System.out.println(dp(obstacleGrid));
    }

    private static int dp(int[][] grid) {
        int[] pre_row_min = new int[grid[0].length];    // 保存上一行的 从起始元素到第j个元素的最小路劲和
        pre_row_min[0] = grid[0][0];
        for(int i = 1; i < pre_row_min.length; i++) {
            pre_row_min[i] = pre_row_min[i-1] + grid[0][i];
        }

        for(int i = 1; i < grid.length; i++) {
            pre_row_min[0] = grid[i][0] + pre_row_min[0];
//            System.out.println(pre_row_min[0]);
            for (int j = 1; j < pre_row_min.length; j++) {
                pre_row_min[j] = grid[i][j] + Math.min(pre_row_min[j], pre_row_min[j-1]);
            }
        }

        return pre_row_min[pre_row_min.length-1];
    }
}
