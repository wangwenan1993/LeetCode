package iie.cloud.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangwenan
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class _3_LongestSubstring {
	public int lengthOfLongestSubstring(String s) {
		if(s == null || s.length() == 0) return 0;
		if(s.length() == 1) return 1;
		int maxLength = 1;
		int startIndex = 0;
		int equalsIndex = 0;
		boolean flag = false;
		StringBuilder sb = new StringBuilder();
		sb.append(s.charAt(0));
		for(int i = 1; i < s.length(); ) {
			for(int j = 0; j < sb.length(); j++) {
				if(sb.charAt(j) == s.charAt(i)) {
					flag = true;
					equalsIndex = j;
					break;
				}
			}
			if(flag) { //发生重复
				if(sb.length() > maxLength) {
					maxLength = sb.length();
					startIndex = i - sb.length();
				}
				sb.delete(0, equalsIndex + 1);
				flag = false;
			}
			else { //没有重复
				sb.append(s.charAt(i));
				System.out.println("-------");
				i++;
			}
			System.out.println("sb:" + sb.toString() + " maxlength:" + maxLength + " maxSubString:" + s.substring(startIndex, startIndex + maxLength));
		}
		if(sb.length() > maxLength) {
			maxLength = sb.length();
			startIndex = s.length() - 1 - maxLength;
		}
		return maxLength;
	}

	public static void main(String[] args) {
		_3_LongestSubstring longestSubstring = new _3_LongestSubstring();
		System.out.println(longestSubstring.lengthOfLongestSubstring("abcabcbb"));
	}


	/**
	 * @param s
	 * @return
	 * Approach #3 Sliding Window Optimized [Accepted]
	 * The above solution requires at most 2n steps. In fact,
	 * it could be optimized to require only n steps.
	 * Instead of using a set to tell if a character exists or not,
	 * we could define a mapping of the characters to its index. Then we can
	 * skip the characters immediately when we found a repeated character.
	 * The reason is that if s[j] have a duplicate in the range [i, j) with index j′,
	 * we don't need to increase i little by little. We can skip all the elements in the range [i, j']
	 * and let ii to be j' + 1 directly.
	 */
	public int lengthOfLongestSubstring2(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

	/*
	 * We also define a  mapping of the characters to their index. Note that there are 256 ASCII characters,
	 * so we define an array, charMap, of size 256 and initialize each of its elements to -1.
	 */
	public int lengthOfLongestSubstring3(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int i = 0, maxLen = 0;
		int[] charMap = new int[256];
		Arrays.fill(charMap, -1);

		for (int j = 0; j < s.length(); j++) {
			if (charMap[s.charAt(j)] >= i) {
				i = charMap[s.charAt(j)] + 1;
			}

			charMap[s.charAt(j)] = j;
			maxLen = Math.max(maxLen, j-i+1);
		}

		return maxLen;
	}
}
