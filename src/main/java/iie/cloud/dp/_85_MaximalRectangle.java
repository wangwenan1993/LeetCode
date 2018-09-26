package iie.cloud.dp;

import iie.cloud.util.PrintUtil;

public class _85_MaximalRectangle {

    public static void main(String[] args) {
        char[][] obstacleGrid = {
                {'0','1','0'},
                {'0','1','0'},
                {'1','1','1'}
        };
//        System.out.println(dp(obstacleGrid));

        char[][] matrix = {
                {'0', '0', '0', '1', '0', '0', '0'},
                {'0', '0', '1', '1', '1', '0', '0'},
                {'0', '1', '1', '1', '1', '1', '0'},
        };
        char[][] matrix1 = {
                {'1','0','1','0','0'},
                {'1','0','0','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(dp_boundary(matrix));
        System.out.println(dp_boundary(matrix1));
    }

    private static int dp(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] rect = new int[m][n];
        int[][] rect_row_index = new int[m][n];
        int result = 0;
        for(int i = 0; i < rect[0].length; i++) {
            if(matrix[0][i] == '0') {
                rect[0][i] = 0;
            } else if (i > 0) {
                rect[0][i] = rect[0][i-1]+1;
            } else {
                rect[0][i] = 1;
            }
            rect_row_index[0][i] = 0;
            if(rect[0][i] > result) result = rect[0][i];
            System.out.println(rect[0][i] + " " + rect_row_index[0][i]);
        }

        for(int i = 0; i < rect.length; i++) {
            if(matrix[i][0] == '0') {
                rect[i][0] = 0;
                rect_row_index[i][0] = i;
            } else if (i > 0) {
                rect[i][0] = rect[i-1][0]+1;
                rect_row_index[i][0] = rect[i-1][0] == 0 ? i : rect_row_index[i-1][0];
            } else {
                rect[i][0] = 1;
                rect_row_index[i][0] = i;
            }
            if(rect[i][0] > result) result = rect[i][0];
            System.out.println(rect[i][0] + " " + rect_row_index[i][0]);
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                System.out.print("x:" + String.valueOf(i) + " y:" + String.valueOf(j) + ' ');
                if(matrix[i][j] == '0') {
                    rect[i][j] = 0;
                    rect_row_index[i][j] = i;
                    System.out.print("元素为0 " + String.valueOf(rect[i][j]) + " " + String.valueOf(rect_row_index[i][j]));
                } else {
                    if(matrix[i-1][j-1] == '0') {
                        if(rect[i-1][j] >= rect[i][j-1]) {
                            rect[i][j] = rect[i-1][j] +1;
                            rect_row_index[i][j] = rect[i-1][j] == 0 ? i : rect_row_index[i-1][j];
                        } else {
                            rect[i][j] = rect[i][j-1]+1;
                            rect_row_index[i][j] = i;
                        }
                        System.out.print("元素为1, 左上方元素为0 " + String.valueOf(rect[i][j]) + " " + String.valueOf(rect_row_index[i][j]));
                    } else if(matrix[i-1][j] == '0' || matrix[i][j-1] == '0') {
                        if(matrix[i-1][j] == '0' && matrix[i][j-1] == '0') {
                            rect[i][j] = 1;
                            rect_row_index[i][j] = i;
                        } else if(matrix[i][j-1] == '0') {
                            rect[i][j] = i-1 - rect_row_index[i-1][j] + 1 + 1;
                            rect_row_index[i][j] = rect_row_index[i-1][j];
                        } else {
                            rect[i][j] = rect[i][j-1] / (i + 1 - rect_row_index[i][j-1]) + 1;
                            rect_row_index[i][j] = i;
                        }
                        System.out.print("元素为1, 左上方元素为1 左方或者上方元素为0 " + String.valueOf(rect[i][j]) + " " + String.valueOf(rect_row_index[i][j]));
                    } else {
                        int row_index = Math.max(rect_row_index[i-1][j-1], rect_row_index[i-1][j]);
                        int column_index = Math.max(j-1 + 1 - (rect[i-1][j-1]/(i-1 + 1 - rect_row_index[i-1][j-1])), j-1 + 1 - (rect[i][j-1]/(i + 1 - rect_row_index[i][j-1])));
                        System.out.print(String.valueOf(row_index) + " " + String.valueOf(column_index) + " ");
                        rect[i][j] = (i - row_index + 1) * (j - column_index + 1);
                        rect_row_index[i][j] = row_index;
                        System.out.print("元素为1, 左上方元素为1 左方和上方元素都为1 " + String.valueOf(rect[i][j]) + " " + String.valueOf(rect_row_index[i][j]));
                    }
                }
                if(rect[i][j] > result) result = rect[i][j];
                System.out.println();
            }
        }

        return result;
    }

    private static int dp_boundary(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n], right = new int[n], height = new int[n];
        for(int i = 0; i < n; i++) {
            left[i] = 0;
        }
        for(int i = 0; i < n; i++) {
            right[i] = n;
        }
        for(int i = 0; i < n; i++) {
            height[i] = 0;
        }
        int maxA = 0;

        for(int i = 0; i < m; i++) {
            int cur_left=0, cur_right=n;
            for(int j=0; j<n; j++) { // compute height (can do this from either side)
                if(matrix[i][j]=='1') height[j]++;
                else height[j]=0;
            }
            for(int j=0; j<n; j++) { // compute left (from left to right)
                if(matrix[i][j]=='1') left[j]=Math.max(left[j],cur_left);
                else {left[j]=0; cur_left=j+1;}
            }
            // compute right (from right to left)
            for(int j=n-1; j>=0; j--) {
                if(matrix[i][j]=='1') right[j]=Math.min(right[j],cur_right);
                else {right[j]=n; cur_right=j;}
            }

            PrintUtil.printIntArray(left);
            PrintUtil.printIntArray(right);
            PrintUtil.printIntArray(height);
            System.out.println();
            // compute the area of rectangle (can do this from either side)
            for(int j=0; j<n; j++)
                maxA = Math.max(maxA,(right[j]-left[j])*height[j]);
        }
        return maxA;
    }
}
