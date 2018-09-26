package iie.cloud.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteTheSame {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        List<Integer> o_list = new ArrayList<Integer>();
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            int a = in.nextInt();
            if (o_list.size() >= 10) {
                break;
            } else if(!o_list.contains(a)) {
                o_list.add(a);
            }
        }

        System.out.println(o_list.size());
        for(int x: o_list) {
            System.out.println(x);
        }
    }
}
