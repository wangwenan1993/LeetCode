package iie.cloud.util;

import javafx.scene.transform.Transform;

import java.util.List;

public class PrintUtil {

    public static void main(String[] args) {
        int[] array = {1, 2};
        printIntArray(array);
    }

    public static void printIntArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void printIntList(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }
}
