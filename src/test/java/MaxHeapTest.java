import algorithm.maxheap.MaxHeap;

import java.util.ArrayList;
import java.util.Collections;

import static algorithm.maxheap.MaxHeap.maxHeapify;

public class MaxHeapTest {
    public static void main(String[] args) {

        MaxHeap maxHeap = new MaxHeap();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(22);
        list.add(4);
        list.add(55);
        list.add(7);
        list.add(100);
        list.add(11);
        list.add(313);
        list.add(1);


        for(int  i = list.size()/2-1; i >= 0; i--) {
            maxHeapify(list, i, list.size());
        }

        maxHeap.insert(list, 120);

        int range = list.size()-1;
        for (int i = list.size() - 1; i > 0; i--) {
            Collections.swap(list, 0, i);
            maxHeapify(list, 0, range--);
        }

        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}
