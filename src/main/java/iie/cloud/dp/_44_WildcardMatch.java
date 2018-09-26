package iie.cloud.dp;

/**
 * Created by Administrator on 2018/3/4.
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).
 */
public class _44_WildcardMatch {
    public static void main(String[] args) {
        System.out.println(iterative("aaab", "**?b"));
        System.out.println(isMatchDP("aaab", "**a"));
    }

    private static boolean isMatchDP(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }

    private static boolean iterative(String s, String p) {
        int s_idx = 0, p_idx = 0, lastStarIdx = -1, starLastMatchIdx = 0;
        while(s_idx < s.length()) {
            // 将索引s, p同时后移
            if (p_idx < p.length() && (p.charAt(p_idx) == '?' || s.charAt(s_idx) == p.charAt(p_idx))) {
                s_idx++;
                p_idx++;
                // p_idx索引处为'*'，只将p_idx后移，先让*后面的模式串去匹配，如果遇到无法匹配上的在回退到上一次出现*时的位置
                // （用lastStarIdx记录*的位置，用starLastMatchIdx记录s中匹配的位置），用*去匹配一个字符，然后再用*后的字符继续匹配，直到s匹配完
            } else if (p_idx < p.length() && p.charAt(p_idx) == '*') {
                lastStarIdx = p_idx;
                starLastMatchIdx = s_idx;
                p_idx++;
                // 回退至上次pattern出现*的位置
            } else if (lastStarIdx != -1) {
                p_idx = lastStarIdx + 1;
                s_idx = ++starLastMatchIdx;
            } else {  // pattern串中没有*，而且当前已经无法匹配，则直接返回false
                return false;
            }
        }

        while(p_idx < p.length() && p.charAt(p_idx) == '*') {  // 如果pattern串没有匹配完，而且当前匹配位置后面是*，则将p_idx后移
            p_idx++;
        }

        return p_idx == p.length();  // 如果pattern串的所有刚好匹配str则匹配成功，否则失败。
    }

    /**
     * dp[i][j] denotes whether s[0…i-1] matches p[0…j-1],

     First, we need to initialize dp[i][0], i= [1,m]. All the dp[i][0] should be false because p has nothing in it.

     Then, initialize dp[0][j], j = [1, n]. In this case, s has nothing, to get dp[0][j] = true, p must be ‘*’, ‘**’, ‘***’,etc. Once p.charAt(j-1) != ‘*’, all the dp[0][j] afterwards will be false.

     Then start the typical DP loop.

     Though this solution is clear and easy to understand. It is not good enough in the interview. it takes O(mn) time and O(mn) space.

     Improvement: 1) optimize 2d dp to 1d dp, this will save space, reduce space complexity to O(N). 2) use iterative 2-pointer.
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_2d_method(String s, String p) {
        int m=s.length(), n=p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i=1; i<=m; i++) {
            dp[i][0] = false;
        }

        for(int j=1; j<=n; j++) {
            if(p.charAt(j-1)=='*'){
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if (p.charAt(j-1)!='*') {
                    dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?');
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
