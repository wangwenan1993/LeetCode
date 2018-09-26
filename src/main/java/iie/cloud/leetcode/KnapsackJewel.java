package iie.cloud.leetcode;


import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * @author wangwenan 问题： 话说有一哥们去森林里玩发现了一堆宝石，他数了数，一共有n个。但他身上能装宝石的就只有一个背包，背包的容量为C。
 *         这哥们把n个宝石排成一排并编上号： 0,1,2,…,n-1。第i个宝石对应的体积和价值分别为V[i]和W[i]。
 *         排好后这哥们开始思考：背包总共也就只能装下体积为C的东西，那我要装下哪些宝石才能让我获得最大的利益呢？ 思路：
 *         很显然该题目可以使用动态规划（DP）来解决，那么我们首先就得找到问题的状态和状态转移方程
 *
 *         比如n=3。然后设背包容量C=10，三个宝石的体积为5，4，3，对应的价值为20，10，12:
 *
 *         我们发现，n=3时，放入背包的是0号和2号宝石；当n=2时，我们放入的是0号宝石。这并不是一个偶然，
 *         没错，这就是传说中的“全局最优解包含局部最优解”（n=2是n=3情况的一个局部子问题）。
 *         让我们形式化地表示一下它们，定义d(i,j)为前i个宝石装到剩余体积为j的背包里能达到的最大价值。 那么上面两句话即为：d(2,
 *         7)和d(3, 10)。这样看着真是爽多了，而这两个看着很爽的符号就是我们要找的状态了。
 *         即状态d(i,j)表示前i个宝石装到剩余体积为j的背包里能达到的最大价值
 *         。上面那么多的文字，用一句话概括就是：根据子问题定义状态！你找到子问题，状态也就浮出水面了
 *
 *         那么回到例子，d(2, 7)和d(3, 10)是怎么转移的？来，我们来说说2号宝石（记住宝石编号是从0开始的）。 从d(2,
 *         7)到d(3, 10)就隔了这个2号宝石。它有两种情况，装或者不装入背包。
 *         如果装入，在面对前2个宝石时，背包就只剩下体积7来装它们，而相应的要加上2号宝石的价值12， d(3, 10)=d(2,
 *         10-3)+12=d(2, 7)+12； 如果不装入，体积仍为10，价值自然不变了， d(3, 10)=d(2, 10)。记住，d(3,
 *         10)表示的是前3个宝石装入到剩余体积为10 的背包里能达到的最大价值， 既然是最大价值，就有d(3, 10)=max{ d(2,
 *         10), d(2, 7)+12 }。好了，这条方程描述了状态d(i, j)的一些关系，
 *         没错，它就是状态转移方程了。把它形式化一下：d(i, j)=max{ d(i-1, j), d(i-1,j-V[i-1]) +
 *         W[i-1] }。 注意讨论前i个宝石装入背包的时候，其实是在考查第i-1个宝石装不装入背包（因为宝石是从0开始编号的）。
 */
public class KnapsackJewel {

	Scanner in = new Scanner(System.in);

	@Test
	public void knapsack() {
		int num = 0, total_volume = 0;
		String s = in.nextLine().trim();
		num = Integer.parseInt(s.split(" ")[0]);
		total_volume = Integer.parseInt(s.split(" ")[1]);
		int volume[] = new int[num + 1];
		int weight[] = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			s = in.nextLine().trim();
			volume[i] = Integer.parseInt(s.split(" ")[0]);
			weight[i] = Integer.parseInt(s.split(" ")[1]);
		}
		int d[][] = new int[num + 1][total_volume + 1];
		for (int i = 0; i <= num; i++) {
			for (int j = 0; j <= total_volume; j++) {
				d[i][j] = 0;
			}
		}
		for (int i = 1; i <= num; i++) {
			for (int j = 1; j <= total_volume; j++) {
				d[i][j] = d[i - 1][j];
				if (j >= volume[i]
						&& d[i - 1][j - volume[i]] + weight[i] > d[i][j]) {
					d[i][j] = d[i - 1][j - volume[i]] + weight[i];
				}
			}
		}
		System.out.println(d[num][total_volume]);
		int x[] = new int[num + 1], j = total_volume;
		for (int i = num; i > 0; i--) {
			if (d[i][j] > d[i - 1][j]) {
				x[i] = 1;
				j -= volume[i];
			}
		}
		for (int i = 1; i <= num; i++) {
			System.out.print(x[i] + " ");
		}
		System.out.println();
	}

	/**
	 * 状态转移方程为：d(i, j)=max{ d(i-1, j), d(i-1, j-V)+W }， 也就是在计算d(i,
	 * j)时我们用到了d(i-1,j)和d(i-1, j-V)的值。如果我们只用一个一维数组d(0)～d(C)来保存状态值可以么？
	 * 将i方向的维数去掉，我们可以将原来二维数组表示为一维数据：d(i-1, j-V)变为d(j-V)， d(i-1, j)变为d(j)。
	 * 当我们要计算d(i, j)时，只需要比较d(j)和d(j-V)+W的大小，用较大的数更新d(j)即可。等等，如果我要计算d(i, j+1)，
	 * 而它恰好要用到d(i-1, j)的值，那么问题就出来了，因为你刚刚才把它更新为d(i, j)了。那么，怎么办呢？
	 * 按照j递减的顺序即可避免这种问题。比如，你计算完d(i, j)，接下来要计算的是d(i,j-1)， 而它的状态转移方程为d(i,
	 * j-1)=max{ d(i-1, j-1), d(i-1, j-1-V)+W }，它不会再用到d(i-1,j)的值！
	 * 所以，即使该位置的值被更新了也无所谓。
	 */
	@Test
	public void knapsack_optimization() {
		int num = 0, total_volume = 0;
		String s = in.nextLine().trim();
		num = Integer.parseInt(s.split(" ")[0]);
		total_volume = Integer.parseInt(s.split(" ")[1]);
		int volume[] = new int[num + 1];
		int weight[] = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			s = in.nextLine().trim();
			volume[i] = Integer.parseInt(s.split(" ")[0]);
			weight[i] = Integer.parseInt(s.split(" ")[1]);
		}
		int d[] = new int[total_volume + 1];
		for (int j = 0; j <= total_volume; j++) {
			d[j] = 0;
		}
		for (int i = 1; i <= num; i++) {
			for (int j = total_volume; j >= 1; j--) {
				if (j >= volume[i]
						&& d[j - volume[i]] + weight[i] > d[j]) {
					d[j] = d[j - volume[i]] + weight[i];
				}
			}
		}
		System.out.println(d[total_volume]);
	}
}
