package iie.cloud.leetcode;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Reverse digits of an integer.

 Example1: x = 123, return 321
 Example2: x = -123, return -321
 * @author wangwenan
 * 需要考虑数据溢出
 */
public class _7_ReverseInteger {
	public static int reverse(int x) {
		if(x == 0) return x;
		if(x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) return 0;
		long temp = x;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(true) {
			if(temp != 0) {
				list.add((int)Math.abs(temp % 10));
				temp /=10;
			}
			else {
				break;
			}
		}
		temp = 0;
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()) {
			temp = (temp + iter.next()) * 10;
		}
		if(x < 0) temp = -temp/10;
		else temp = temp / 10;
		System.out.println(temp);
		if(temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) return 0;
		return (int) temp;
	}

	public static int reverse1(int x) {
		if(x == 0) return x;
		if(x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) return 0;
		int temp = x;
		long result = 0;
		while(true) {
			if(temp != 0) {
				System.out.println(Math.abs(temp % 10));
				result = (result + Math.abs(temp % 10)) * 10;
				temp /=10;
			}
			else {
				break;
			}
		}
		if(x < 0) result = -result/10;
		else result = result / 10;
		System.out.println(result);
		if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
		return (int) result;
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE + " " + Integer.MIN_VALUE);
		System.out.println(reverse1(-2147483412));
	}
}
