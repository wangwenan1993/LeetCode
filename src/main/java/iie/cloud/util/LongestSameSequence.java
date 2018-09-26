package iie.cloud.util;

import java.util.Scanner;

public class LongestSameSequence {
    // 求解字符串中的最长重复子串
    public static String maxRepat(String input) {
        // 参数检查
        if (input == null || input.length() == 0) {
            return null;
        }
        // 重复子串的最长长度
        int max = 0;
        // 最长重复子串的起始位置
        int first = 0;
        int k = 0;
        for (int i = 1; i < input.length(); i++) {
            for (int j = 0; j < input.length() - i; j++) {
                if (input.charAt(j) == input.charAt(i + j)) {
                    k++;
                } else {
                    k = 0;
                }
                if (k > max) {
                    max = k;
                    first = j - k + 1;
                }
            }
        }
        if (max > 0) {
            System.out.println(max);
            return input.substring(first, first + max);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String o_str = in.nextLine();
//        String result = maxRepat(o_str);
//        System.out.println(result);
        int result = maxRep(o_str);
        System.out.println(result);
    }

    public static int maxRep(String str) {
        int len = str.length();

        int array[][] = new int[len + 1][len + 1];
        for (int m = 0; m < len; m++) {

            for (int n = 0; n < len; n++) {
                if (str.charAt(m) == str.charAt(n))
                    array[m + 1][n + 1] = array[m][n] + 1;

            }

        }

        int max = 0;
        for (int m = 0; m < len + 1; m++) {

            for (int n = 0; n < len + 1; n++) {
                if ((m != n) && (array[m][n] > max))
                    max = array[m][n];

            }

        }
        return max;
    }
}