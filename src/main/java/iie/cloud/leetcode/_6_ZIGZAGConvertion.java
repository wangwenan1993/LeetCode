package iie.cloud.leetcode;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string text, int nRows);
 convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR
 * @author wangwenan
 *
 */
public class _6_ZIGZAGConvertion {
	public static String convert(String s, int numRows) {
		if(s.length() == 0 || s.length() <= numRows || numRows == 1) return s;
		int numPerV = 2 * numRows -2;    //得到每个"V"字形中字符的个数
		int num_of_v = s.length() / numPerV + 1;  //算出一共有多少个"V"字形（包括不完整的）
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < num_of_v; j++) {
				int index = numPerV * j + i;
				if(index < s.length()) {
					sb.append(s.charAt(index));
					if(i > 0 && i < numRows - 1 && numPerV * (j + 1) - i < s.length()) {
						sb.append(s.charAt(numPerV * (j + 1) - i));
					}
				}
			}
		}
		return sb.toString();
	}

	public String convert1(String s, int numRows) {
		if(numRows<=1)return s;
		StringBuilder[] sb=new StringBuilder[numRows];
		for(int i=0;i<sb.length;i++){
			sb[i]=new StringBuilder("");   //init every sb element **important step!!!!
		}
		int incre=1;
		int index=0;
		for(int i=0;i<s.length();i++){
			sb[index].append(s.charAt(i));
			if(index==0){incre=1;}
			if(index==numRows-1){incre=-1;}
			index+=incre;
		}
		String re="";
		for(int i=0;i<sb.length;i++){
			re+=sb[i];
		}
		return re.toString();
	}

	public static void main(String[] args) {
		System.out.println(convert("PAYPALISHIRING", 3));
	}
}
