package algorithm.maxheap;

import java.util.ArrayList;
import java.util.Collections;

public class MaxHeap {

    ArrayList<Integer> heapList = new ArrayList<>();

    public void maxHeapify(ArrayList<Integer> list , int idx, int size)
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


    public void insert(int key) {
        heapList.add(key);
        int i = heapList.size()-1;
        while (i > 0 && heapList.get(i/2) < heapList.get(i)){
            Collections.swap(heapList, i/2, i);
            i = i/2;
        }
    }

    public void delete(int key) {
        heapList.remove(heapList.size());
    }

    public void setTestData() {
        heapList.add(3);
        heapList.add(22);
        heapList.add(4);
        heapList.add(55);
        heapList.add(7);
        heapList.add(100);
        heapList.add(11);
        heapList.add(313);
        heapList.add(1);
    }

    public void runMaxHeap() {
        for(int  i = heapList.size()/2-1; i >= 0; i--) {
            maxHeapify(heapList, i, heapList.size());
        }
    }

    public void printData() {
        ArrayList<Integer> copyOfHeap = (ArrayList<Integer>) heapList.clone();
        int range = copyOfHeap.size()-1;
        for (int i = copyOfHeap.size() - 1; i > 0; i--) {
            Collections.swap(copyOfHeap, 0, i);
            maxHeapify(copyOfHeap, 0, range--);
        }

        for(int i = 0; i < copyOfHeap.size(); i++){
            System.out.println(copyOfHeap.get(i));
        }
    }
}
