package algorithm.huffman;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;


public class Huffman {
    private ArrayList<Run> runs = new ArrayList<>();
    private Run root = null;
    private MinHeap minHeap = new MinHeap();

    // 기존 저장했던 run을 간편하게 조회하기 위한 배열
    // 기존에서는 run 들이 tree 로 되어 있어서 그냥 쓰기에는 불편하다.
    private Run []chars = new Run[256];
    private int charsSize = 0;


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

    private void assignCodewords(Run p, int codeword, int length) {
        if(p.getLeftChild() == null && p.getLeftChild() == null) {
            p.setCodeword(codeword);
            p.setCodewordLen(length);
        } else {
            assignCodewords(p.getLeftChild(), ((codeword << 1) | 0), length+1);
            assignCodewords(p.getRightChild(), ((codeword << 1) | 1), length+1);
        }
    }

    private void insertToArray(Run p) {
        boolean isExistRun = false;
        int index = -1;

        for(int i = 0; i < charsSize; i++) {
            if (chars[i].getSymbol() == p.getSymbol()) {
                isExistRun = true;
                index = i;
                break;
            }
        }

        if (isExistRun) {
            Run run = chars[index];
            while (true) {
                Run preRun = run;
                run = run.getRightRun();
                if(run == null) {
                    preRun.setRightRun(p);
                    break;
                }
            }
        } else {
            chars[charsSize++] = p;
        }
    }
    
    private void storeRunsIntoArray(Run p) {
        if (p.getLeftChild() == null && p.getRightChild() == null) {
            insertToArray(p);
        } else {
            storeRunsIntoArray(p.getLeftChild());
            storeRunsIntoArray(p.getRightChild());
        }
    }

    public void compressFile(RandomAccessFile fin) {
        try {
            collectRuns(fin);
            printRuns();
            System.out.println("=================Create Huffman===================");
            createHuffmanTree();
            preOrder(root, 0);
            assignCodewords(root, 0,0);
            storeRunsIntoArray(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        RandomAccessFile fin;
        try {
            fin = new RandomAccessFile("/home/skahn/work/sample.txt","r");
            huffman.compressFile(fin);
            fin.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
