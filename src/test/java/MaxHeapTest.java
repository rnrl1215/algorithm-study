import algorithm.maxheap.MaxHeap;


public class MaxHeapTest {
    public static void main(String[] args) {

        MaxHeap maxHeap = new MaxHeap();

        maxHeap.setTestData();
        maxHeap.insert(120);
        maxHeap.runMaxHeap();
        maxHeap.insert(240);
        maxHeap.printData();
    }
}
