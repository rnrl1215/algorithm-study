package algorithm.huffman;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class HuffmanDecode {

    private ArrayList<Run> runs = new ArrayList<>();
    private Run root = null;
    private MinHeap minHeap = new MinHeap();
    private Long totalWordSize = 0L;

    public Run getRoot() {
        return root;
    }

    public void setRoot(Run root) {
        this.root = root;
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

        root = minHeap.getMinRun();
    }

    public Run createRunTree(Run run1, Run run2) {
        int num = -1;
        num = run1.getFreq() + run2.getFreq();
        Run parentRun = new Run((byte)0, 0, num);
        if (run1.compareTo(run2) > 0) {
            parentRun.setLeftChild(run1);
            parentRun.setRightChild(run2);
        } else {
            parentRun.setLeftChild(run2);
            parentRun.setRightChild(run1);
        }
        return parentRun;
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
        System.out.println(run.getSymbol() + ","+ run.getRunLength() +","+run.getCodeword());

        preOrder(run.getLeftChild(), depth+1);
        preOrder(run.getRightChild(), depth+1);
    }

    private void assignCodewords(Run p, int codeword, int length) {
        if(p.getLeftChild() == null && p.getLeftChild() == null) {
            p.setCodeword(codeword);
            p.setCodewordLen(length);
        } else {
            assignCodewords(p.getLeftChild(), (codeword << 1 | 0), length+1);
            assignCodewords(p.getRightChild(), (codeword << 1 | 1), length+1);
        }
    }

    public void decompress(RandomAccessFile fin, RandomAccessFile fout) throws IOException  {
        runs.clear();
        minHeap.clear();

        inputFrequencies(fin);
        createHuffmanTree(); // 허프만 트리를 만든다.
        assignCodewords(root, 0,0); // 만들어진 트리에 코드워드를 부여한다.
        preOrder(root, 0);
        decode(fin, fout);

        fin.close();
        fout.close();
    }


    private void inputFrequencies(RandomAccessFile fIn)
            throws IOException
    {
        int dataIndex = fIn.readInt();
        totalWordSize = fIn.readLong();

        for (int j = 0; j < dataIndex; j++) {
            byte symbol = (byte) fIn.read();
            int runLength = fIn.readInt();
            int freq =  fIn.readInt();

            Run r = new Run(symbol, runLength, freq);
            runs.add(r);
        }
    }


    private boolean decode(RandomAccessFile fIn, RandomAccessFile fOut) throws IOException {
        int numberCounter = 1;
        String tmpString = "";
        int readCount = 0;
        Run rootRun = root;
        String symbol = "";

        try {
            while (true)
            {

                if(rootRun.getRightChild() == null && rootRun.getLeftChild() == null) {
                    for(int i = 0; i < rootRun.getRunLength(); i++) {
                        symbol += (char)rootRun.getSymbol();
                        readCount++;
                    }
                    rootRun = root;
                }

                byte codeword = fIn.readByte();

                if(codeword != 0) {
                    rootRun = rootRun.getRightChild();
                } else {
                    rootRun = rootRun.getLeftChild();
                }

                if (readCount == totalWordSize) {
                    break;
                }
            }
        }
        catch (EOFException ex)
        {
            System.out.println("Total number " + numberCounter);
            System.out.println("End of file reached!");
        }
        fOut.writeChars(symbol);
        System.out.println("Decoding: "+symbol);
        return true;
    }


    public static void main(String[] args) {
        HuffmanDecode huffman = new HuffmanDecode();
        RandomAccessFile fin;
        RandomAccessFile fout;

        try {
            String fileName = "/home/skahn/work/sample.txt.z";
            fin = new RandomAccessFile(fileName,"r");
            fout = new RandomAccessFile(fileName+".dec","rw");
            huffman.decompress(fin, fout);
            fin.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
