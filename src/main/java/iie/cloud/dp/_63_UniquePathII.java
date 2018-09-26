package iie.cloud.dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?


 Above is a 3 x 7 grid. How many possible unique paths are there?

 Note: m and n will be at most 100.
 */
public class _63_UniquePathII {

    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        System.out.println(dp(obstacleGrid));
    }

    private static int dp(int[][] obstacleGrid) {

        int[][] uniquePath = new int[obstacleGrid.length][obstacleGrid[0].length];
        if (obstacleGrid[0][0] == 1) {
            uniquePath[0][0] = 0;
        } else {
            uniquePath[0][0] = 1;
        }
        for (int i = 1; i < obstacleGrid.length; i++) {
            if (obstacleGrid[i][0] == 1) uniquePath[i][0] = 0;
            else uniquePath[i][0] = uniquePath[i-1][0];
        }
        for (int i = 1; i < obstacleGrid[0].length; i++) {
            if (obstacleGrid[0][i] == 1) uniquePath[0][i] = 0;
            else uniquePath[0][i] = uniquePath[0][i-1];
        }

        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    uniquePath[i][j] = 0;
                } else {
                    uniquePath[i][j] = uniquePath[i-1][j] + uniquePath[i][j-1];
                }
            }
        }

        return uniquePath[uniquePath.length-1][uniquePath[0].length-1];
    }
}
