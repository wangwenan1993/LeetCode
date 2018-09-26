package iie.cloud.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestChain {
	
	public List<Integer> longest = new ArrayList<Integer>();
	
	public int max = 5;
	public List<Integer> calFactorOrMultipler(int number) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= max; i++) {
			if((i % number == 0 || number % i == 0) && i != number) {
				list.add(i);
			}
		}
		return list;
	}
	
	public List<Integer> calLongestChain(List<Integer> list) {
		if(list != null && list.size() > 0) {
			List<Integer> factorOrMultiplers = calFactorOrMultipler(list.get(list.size() -1 ));
			List<Integer> temp = new ArrayList<Integer>();
			for(int i = 0; i < factorOrMultiplers.size(); i++) {
				if(list.contains(factorOrMultiplers.get(i))) {
					temp.add(factorOrMultiplers.get(i));
				}
			}
			factorOrMultiplers.removeAll(temp);
			if(factorOrMultiplers.size() == 0) {
				for(int i = 0; i < list.size(); i++) {
					System.out.print(list.get(i) + " ");
				}
				System.out.println();
				if(list.size() > longest.size()) {
					longest = list;
				}
				list = null;
				return list;
			} else {
				for(int i = 0; i < factorOrMultiplers.size(); i++) {
					List<Integer> list_bak = new ArrayList<Integer>(list);
					list_bak.add(factorOrMultiplers.get(i));
					calLongestChain(list_bak);
				}
			}
		} else {
			for(int i = 1; i <= max; i++) {
				List<Integer> temp = list;
				temp.add(i);
				calLongestChain(temp);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		Long begin = System.currentTimeMillis();
		LongestChain longestChain = new LongestChain();
		longestChain.calLongestChain(new ArrayList<Integer>());
		System.out.println("##################longest list##################");
		for(int i = 0; i < longestChain.longest.size(); i++) {
			System.out.print(longestChain.longest.get(i) + " ");
		}
		long end = System.currentTimeMillis();
		System.out.println("total speed: " + (end - begin) + "ms");
	}
}
