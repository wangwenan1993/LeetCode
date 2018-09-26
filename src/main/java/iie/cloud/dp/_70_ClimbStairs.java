package iie.cloud.dp;

public class _70_ClimbStairs {

    public static void main(String[] args) {
        System.out.println(dp(4));
        System.out.println(dp_O1_space(4));
    }

    private static int dp(int n) {
        if(n == 1) return 1;
        int[] ways = new int[n];
        ways[0] = 1;
        ways[1] = 2;
        for(int i = 2; i < n; i++) {
            ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[n-1];
    }

    private static int dp_O1_space(int n) {
        if(n == 1) return 1;

        int pre = 1;
        int cur = 2;
        for(int i = 2; i < n; i++) {
            int tmp = cur;
            cur = cur + pre;
            pre = tmp;
        }
        return cur;
    }
}
