package algorithm.maxheap;

import java.util.ArrayList;
import java.util.Collections;

public class MaxHeap {
    public static void maxHeapify(ArrayList<Integer> list, int idx, int size)
    {
        int leftChild = idx * 2 + 1;
        int rightChild = idx * 2 + 2;
        int updateIndex = idx;

        if (leftChild < size && list.get(updateIndex) < list.get(leftChild)) {
            updateIndex = leftChild;
        }

        if (rightChild < size && list.get(updateIndex) < list.get(rightChild)) {
            updateIndex = rightChild;
        }

        if (idx != updateIndex) {
            Collections.swap(list, idx, updateIndex);
            maxHeapify(list, updateIndex, size);
        }
    }


    public static void insert(ArrayList<Integer> list, int key) {
        list.add(key);
        int i = list.size()-1;
        while (i > 0 && list.get(i/2) < list.get(i)){
            Collections.swap(list, i/2, i);
            i = i/2;
        }
    }

    public static void delete(ArrayList<Integer> list, int key) {
        list.remove(list.size());
    }
}
