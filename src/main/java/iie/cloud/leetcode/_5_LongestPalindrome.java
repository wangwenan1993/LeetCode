package iie.cloud.leetcode;


import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

public class _5_LongestPalindrome {

	static SimpleDateFormat sdf = new SimpleDateFormat("HH-MM-ss sss");

	/**
	 * 解法一：动态规划->先找出状态和状态转移方程
	 *
	 * @param s
	 * @return define P[ i, j ] ← true iff the subString si ... sj is Palidrome,
	 *         otherwise false; P[ i, j ] ← ( P[ i+1, j-1 ] and Si = Sj )
	 *         ，显然，如果一个子串是回文串，并且如果从它的左右两侧分别向外扩展的一位也相等，那么这个子串就可以从左右两侧分别向外扩展一位。
	 *         其中的base case是 P[ i, i ] ← true P[ i, i+1 ] ← ( Si = Si+1 )
	 */
	@Test
	public static String longestPalindrome(String s) {
		int len = s.length(), begin_pIndex = 0, max_pLen = 1;
		boolean p[][] = new boolean[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (i == j)
					p[i][j] = true;
				else
					p[i][j] = false;
			}
		}
		for (int i = 0; i < len - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				p[i][i + 1] = true;
				begin_pIndex = i;
				max_pLen = 2;
			}
		}

		for (int n = 3; n <= len; n++) {
			for (int i = 0; i < len - n + 1; i++) {
				int j = i + n - 1;
				if (s.charAt(i) == s.charAt(j) && p[i + 1][j - 1]) {
					p[i][j] = true;
					begin_pIndex = i;
					max_pLen = n;
				}
			}
		}
		return s.substring(begin_pIndex, begin_pIndex + max_pLen);
	}

	/**
	 * 解法二：从中间向两边展开
	 *
	 * @param s
	 * @return 根据回文子串的性质可知，任何一个回文字串都是对称的，区别在于： 长度为奇数时：关于中间的字符对称；长度为偶数时：关于空白字符对称。
	 *         故根据从中间向两边展开的思想遍历s的每个字符，然后从该字符向两边展开。
	 */
	public static String longestPalindrome2(String s) {
		String subString = s.charAt(0) + "";
		for (int i = 0; i < s.length() - 1; i++) {
			String sub = spreadToSide(s, i, i);
			if (sub.length() > subString.length()) {
				subString = sub;
			}
			String ss = spreadToSide(s, i, i + 1);
			if (ss.length() > subString.length()) {
				subString = ss;
			}
		}
		return subString;
	}

	public static String spreadToSide(String s, int left, int right) {
		while (left >= 0 && right < s.length()
				&& s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		return s.substring(left + 1, right);
	}

	/**
	 * 算法首先将输入字符串S， 转换成一个特殊字符串T，转换的原则就是将S的开头结尾以及每两个相邻的字符之间加入一个特殊的字符，例如#
	 * 例如: S = “abaaba”, T = “#a#b#a#a#b#a#”.
	 * 为了找到最长的回文字串，例如我们当前考虑以Ti为回文串中间的元素，如果要找到最长回文字串，我们要从当前的Ti扩展使得 Ti-d … Ti+d
	 * 组成最长回文字串. 这里d其实和 以Ti为中心的回文串长度是一样的. 进一步解释就是说，因为我们这里插入了 #
	 * 符号，对于一个长度为偶数的回文串，他应该是以
	 * #做为中心的，然后向两边扩，对于长度是奇数的回文串，它应该是以一个普通字符作为中心的。通过使用#，我们将无论是奇数还是偶数的回文串
	 * ，都变成了一个以Ti为中心，d为半径两个方向扩展的问题。并且d就是回文串的长度。 例如 #a#b#a#, P = 0103010,
	 * 对于b而言P的值是3，是最左边的#，也是延伸的最左边。这个值和当前的回文串是一致的。
	 * 如果我们求出所有的P值，那么显然我们要的回文串，就是以最大P值为中心的回文串。
	 *  T = # a # b # a # a # b # a #
	 *  P = 0 1 0 3 0 1 6 1 0 3 0 1 0 例如上面的例子，最长回文是 “abaaba”, P6 = 6.
	 * 根据观察发现，如果我们在一个位置例如
	 * abaaba的中间位置，用一个竖线分开，两侧的P值是对称的。当然这个性质不是在任何时候都会成立，接下来就是分析如何利用这个性质
	 * ，使得我们可以少算很多P的值。 下面的例子 S = “babcbabcbaccba” 存在更多的折叠回文字串。
	 *
	 * C表示当前的回文中心，L和R处的线表示以C为中心可以到达的最左和最右位置，如果知道这些，我们如何可以更好的计算C后面的P[i].
	 * 假设我们当前计算的是 i = 13, 根据对称性，我们知道对称的那个下标 i' = 9.
	 *
	 * 根据C对称的原则，我们很容易得到如下数据 P[ 12 ] = P[ 10 ] = 0, P[ 13 ] = P[ 9 ] = 1, P[ 14 ]
	 * = P[ 8 ] = 0).
	 *
	 * 当时当i = 15的时候，却只能得到回文 “a#b#c#b#a”, 长度是5， 而对称 i ' = 7 的长度是7.
	 *
	 * 如上图所示，如果以 i, i' 为中心，画出对称的区域如图，其中以i‘ = 7 对称的区域是 实心绿色 + 虚绿色 和
	 * 左侧，虚绿色表示当前的对称长度已经超过之前的对称中心C。而之前的P对称性质成立的原因是 i 右侧剩余的长度 R - i 正好比 以 i‘
	 * 为中心的回文小。 这个性质可以这样归纳，对于 i 而言，因为根据C对称的最右是R，所以i的右侧有 R - i 个元素是保证是 i' 左侧是对称的。
	 * 而对于 i' 而言他的P值，也就是回文串的长度，可能会比 R-i 要大。 如果大于 R - i, 对于i而言，我们只能暂时的先填写 P[i] =
	 * R - i, 然后依据回文的属性来扩充P[i] 的值； 如果P[i '] 小于R-i，那么说明在对称区间C内，i的回文串长度和i'
	 * 是一样长的。例如我们的例子中 i = 15, 因为R = 20，所以i右侧 在对称区间剩余的是 R - 15 = 5, 而 i’ = 7
	 * 的长度是7. 说明 i' 的回文长度已经超出对称区间。我们只能使得P[i] 赋值为5， 然后尝试扩充P[i].
	 * if P[ i' ] ≤ R – i,
	 *  then P[ i ] ← P[ i' ]
	 * else P[ i ] ≥R – i. (这里下一步操作是扩充 P[ i ]. 扩充P[i]
	 * 之后，我们还要做一件事情是更新 R 和 C， 如果当前对称中心的最右延伸大于R，我们就更新C和R。在迭代的过程中，我们试探i的时候，如果P[i']
	 * <= R - i, 那么只要做一件事情。 如果不成立我们对当前P[i] 做扩展，因为最大长度是n，扩展最多就做n次，所以最多做2*n。
	 * 所以最后算法复杂度是 O(n)
	 *
	 *
	 * 详细介绍见：http://blog.csdn.net/han_xiaoyang/article/details/11969497#t20
	 * @param s
	 * @return
	 */
	public static String longestPalindrome3(String s) {
		if (s.length() == 0)
			return "";
		String t = preProcess(s);
		// System.out.println(t);
		int C = 0, R = 0, p[] = new int[t.length()];
		for (int i = 0; i < t.length(); i++) {
			int i_mirror = 2 * C - i; // eqauls to i + i` = 2C;
			p[i] = R > i ? Math.min(R - i, p[i_mirror]) : 0;

			// attempt to expend palidrome centered at i
			while (i + p[i] + 1 < t.length() && i - p[i] - 1 >= 0
					&& t.charAt(i + p[i] + 1) == t.charAt(i - p[i] - 1)) {
				p[i]++;
			}

			// If palindrome centered at i expand past R,
			// adjust center based on expanded palindrome.
			if (i + p[i] > R) {
				C = i;
				R = i + p[i];
			}
		}

		int max_pLen = 0, center_index = 0;
		for (int i = 0; i < p.length; i++) {
			// System.out.println(p[i]);
			if (p[i] > max_pLen) {
				max_pLen = p[i];
				center_index = i;
			}

		}

		return s.substring((center_index - max_pLen) / 2,
				(center_index + max_pLen) / 2);
	}

	public static String preProcess(String s) {
		StringBuilder sb = new StringBuilder("#");
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i) + "#");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		int repeat = 100;

		System.out.println(longestPalindrome("abacd"));
		long t1 = System.currentTimeMillis();
		for (int i = 0; i < repeat; i++) {
			// System.out.println(longestPalindrome2("kgropitopqerutyrkgjoirwjglkmgvasdklgjioajvkljgpowitopjvmmnvklajsgfdjslkfsdbabababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa"));
			longestPalindrome2("kgropitopqerutyrkgjoirwjglkmgvasdklgjioajvkljgpowitopjvmmnvklajsgfdjslkfsdbabababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa");
		}
		long t2 = System.currentTimeMillis();
		for (int i = 0; i < repeat; i++) {
			// System.out.println(longestPalindrome3("sfsdfaddsfddsfdfdsfsdafdsgfdgbvhyghertioerjglkfgreiktngcnjkgbfhdgnlfkgjpoklsdamgrjgrwk;lgfmsdjgfrwjgklsmgkfjgoirejgkmbva;kgropitopqerutyrkgjoirwjglkmgvasdklgjioajvkljgpowitopjvmmnvklajsgfdjslkfsdbabababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa"));
			longestPalindrome3("kgropitopqerutyrkgjoirwjglkmgvasdklgjioajvkljgpowitopjvmmnvklajsgfdjslkfsdbabababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababa");
		}
		long t3 = System.currentTimeMillis();
		System.out.println("longestPalindrome2:" + (t2 - t1) + " longestPalindrome3:" + (t3 - t2));
	}
}
