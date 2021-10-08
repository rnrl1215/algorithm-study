package algorithm.huffman;

import java.util.ArrayList;
import java.util.Collections;

public class MinHeap {
    ArrayList<Run> list  = new ArrayList<>();

    public void minHeapify(int idx, int size)
    {
        int leftChild = idx * 2 + 1;
        int rightChild = idx * 2 + 2;
        int updateIndex = idx;

        if (leftChild < size && list.get(updateIndex).getFreq() > list.get(leftChild).getFreq()) {
            updateIndex = leftChild;
        }


        if (rightChild < size && list.get(updateIndex).getFreq() > list.get(rightChild).getFreq()) {
            updateIndex = rightChild;
        }

        if (idx != updateIndex) {
            Collections.swap(list, idx, updateIndex);
            minHeapify(updateIndex, size);
        }
    }

    public void insert(Run run) {
        list.add(run);
        int i = list.size()-1;
        while (i > 0 && list.get(i/2).getFreq() > list.get(i).getFreq()){
            Collections.swap(list, i/2, i);
            i = i/2;
        }
    }

    public void delete(int Run) {
        list.remove(list.size());
    }

    public Run extractMin() {
        if (list.size() > 0) {
            Run run = list.get(0);
            list.remove(0);
            for(int  i = list.size()/2-1; i >= 0; i--) {
                minHeapify(i, list.size());
            }
            return run;
        } else {
            return null;
        }
    }

    public int size() {
        return list.size();
    }
}
