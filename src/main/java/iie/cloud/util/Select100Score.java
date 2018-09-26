package iie.cloud.util;

import java.util.*;

public class Select100Score {
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        Map<Integer, Integer> o_score_map = new HashMap<Integer, Integer>();
        int n = in.nextInt();
        for(int i = 0; i < n; i++) {
            o_score_map.put(i+1, in.nextInt());
        }
        List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<Map.Entry<Integer, Integer>>(o_score_map.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        List<Map.Entry<Integer, Integer>> new_infoIds = new ArrayList<Map.Entry<Integer, Integer>>();

        Collections.reverse(infoIds);
        int total = 0;
        for(int i = 0; i < infoIds.size(); i++) {
            total += infoIds.get(i).getValue();
            if(total == 100) {
                new_infoIds.add(infoIds.get(i));
                break;
            } else if (total < 100) {
                new_infoIds.add(infoIds.get(i));
            }
        }

        Collections.reverse(new_infoIds);
        System.out.println(new_infoIds.size());
        for (Map.Entry<Integer, Integer> m: new_infoIds) {
            System.out.println(m.getKey());
        }
    }
}