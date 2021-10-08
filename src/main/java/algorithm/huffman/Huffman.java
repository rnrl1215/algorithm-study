package algorithm.huffman;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class Huffman {
    private ArrayList<Run> runs = new ArrayList<>();
    private Run root = null;
    private MinHeap minHeap = new MinHeap();

    public Run getRoot() {
        return root;
    }

    public void setRoot(Run root) {
        this.root = root;
    }

    public void collectRuns(RandomAccessFile fin) throws IOException {
        int numberCounter = 1;
        byte preSymbol = fin.readByte();

        try {
            while (true)
            {
                byte symbol = fin.readByte();
                if (preSymbol == symbol) {
                    numberCounter++;
                } else {
                    boolean findFlag = false;
                    Run run = new Run(preSymbol, numberCounter);
                    for (int i = 0; i < runs.size(); i++) {
                        if (runs.get(i).equals(run)) {
                            findFlag = true;
                            runs.get(i).increaseFreq();
                            break;
                        }
                    }

                    if (!findFlag) {
                        runs.add(run);
                    }
                    preSymbol = symbol;
                    numberCounter = 1;
                }
            }
        }
        catch (EOFException ex)
        {
            System.out.println("Total number " + numberCounter);
            System.out.println("End of file reached!");
        }
    }

    public void createHuffmanTree() {
        for(int i = 0; i <runs.size(); i++) {
            minHeap.insert(runs.get(i));
        }
        
        while (minHeap.size() != 1) {
            Run childRun1 = minHeap.extractMin();
            if (childRun1 == null) {
                break;
            }
            Run childRun2 = minHeap.extractMin();
            if (childRun2 == null) {
                break;
            }

            Run parentRun =  createRunTree(childRun1, childRun2);
            minHeap.insert(parentRun);
        }

         root = minHeap.extractMin();
    }

    public Run createRunTree(Run run1, Run run2) {

        int num = -1;
        num = run1.getFreq() + run2.getFreq();
        Run parentRun  = new Run();
        parentRun.setFreq(num);

        if (run1.compareTo(run2) > 0) {
            parentRun.setLeftChild(run2);
            parentRun.setRightChild(run1);
        } else {
            parentRun.setLeftChild(run1);
            parentRun.setRightChild(run2);
        }

        return parentRun;
    }

    public void printRuns() {
        for(Run run : runs) {
            System.out.println("{ "+"Symbol: "+run.getSymbol() +
                    ", RunLength: " +run.getRunLength() +
                    ", Freq: "+run.getFreq() + " }");
        }
    }

    public void printHeapTree() {
        preOrder(root, 0);
    }

    public void preOrder(Run run, int depth) {
        if(run == null) {
            return;
        }
        for(int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
        System.out.println(run.getSymbol() + "," + run.getFreq());
        preOrder(run.getLeftChild(), depth+1);
        preOrder(run.getRightChild(), depth+1);
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        RandomAccessFile fin;
        try {
            fin = new RandomAccessFile("/home/skahn/work/sample.txt","r");
            huffman.collectRuns(fin);
            huffman.printRuns();
            System.out.println("=================Create Huffman===================");
            huffman.createHuffmanTree();
            huffman.preOrder(huffman.root, 0);

            int test  = 0;
            test += '1';
            test += '0';
            System.out.println(test);
            fin.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
