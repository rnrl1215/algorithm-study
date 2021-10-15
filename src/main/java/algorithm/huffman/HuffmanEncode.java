package algorithm.huffman;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class HuffmanEncode {
    private ArrayList<Run> runs = new ArrayList<>();
    private Run root = null;
    private MinHeap minHeap = new MinHeap();

    // 기존 저장했던 run을 간편하게 조회하기 위한 배열
    // 기존에서는 run 들이 tree 로 되어 있어서 그냥 쓰기에는 불편하다.
    private Run []chars = new Run[256];
    private int charsSize = 0;

    private byte[] byteArray = new byte[8];
    private int index = 0;


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
                            runs.get(i).increaseFreq();
                            findFlag = true;
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

    public void printRuns() {
        for(Run run : runs) {
            System.out.println("{ "+"Symbol: "+run.getSymbol() +
                    ", RunLength: " +run.getRunLength() +
                    ", Freq: "+run.getFreq() + " " +
                    ", codeword"+ run.getCodeword()+"}");
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
        System.out.println(run.getSymbol() + ","+ run.getRunLength() +" "+ run.getCodeword());


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

    public Run findRun(byte symbol, int length) {
        Run  findRun = null;
        for (int i = 0; i < charsSize; i++) {
            Run run = chars[i];
            if (run.getSymbol() == symbol) {
                findRun = run;
                break;
            }
        }

        Run tmpRun = findRun;
        while (tmpRun != null) {
            if(length == tmpRun.getRunLength()) {
                findRun = tmpRun;
                break;
            } else {
                tmpRun = tmpRun.getRightRun();
            }
        }
        return findRun;
    }

    private void outputFrequencies(RandomAccessFile fIn,
                                   RandomAccessFile fOut) throws IOException {
        fOut.writeInt(runs.size());
        fOut.writeLong(fIn.getFilePointer());

        for(int i = 0 ; i < runs.size(); i++) {
            Run r = runs.get(i);
            fOut.write(r.getSymbol());
            fOut.writeInt(r.getRunLength());
            fOut.writeInt(r.getFreq());
        }
    }

    private void encode(RandomAccessFile fIn,
                                   RandomAccessFile fOut) throws IOException {

        int numberCounter = 1;
        byte preSymbol = fIn.readByte();
        String tmpString = "";

        int codeWord = 0;
        try {
            while (true)
            {
                byte symbol = fIn.readByte();
                if (preSymbol == symbol) {
                    numberCounter++;
                } else {
                    Run run = findRun(preSymbol, numberCounter);
                    writeEncodeCode(run, fOut);
                    preSymbol = symbol;
                    numberCounter = 1;
                }
            }
        }
        catch (EOFException ex)
        {
            System.out.println("end file");
        }

        if (index < 7 && index != 0) {
            for(int i = index; i < 8; i++) {
                byteArray[i] = 0;
            }
            fOut.write(byteArray);
        }
    }


    public void writeEncodeCode(Run run, RandomAccessFile fOut) throws IOException {
        int bits = 8;
        int mask = 1;
        int symbol  = run.getCodeword();
        int runLength = run.getCodewordLen();
        mask <<= bits -1;

        for (int i = 0; i < 8-runLength; i++) {
            symbol = symbol << 1;
        }

        for (int i = 0; i < runLength; i++) {
            if ((symbol & mask) != 0) {
                byteArray[index] = 1;
            } else {
                byteArray[index] = 0;
            }
            index++;
            symbol = symbol << 1;
            if (index == 8) {
                index = 0;
                fOut.write(byteArray);
            }
        }
    }


    private void compressFile(String inFileName, RandomAccessFile fin) throws IOException
    {
        Arrays.fill(byteArray, (byte)-1);
        String outFileName = new String(inFileName+".z");
        RandomAccessFile fOut = new RandomAccessFile(outFileName,"rw");
        collectRuns(fin); // run 을 만든다
        outputFrequencies(fin, fOut); // 런을 저장한다.
        createHuffmanTree(); // 허프만 트리를 만든다.
        assignCodewords(root, 0,0); // 만들어진 트리에 코드워드를 부여한다.
        preOrder(root, 0);

        storeRunsIntoArray(root); //런을 배열로 저장한다. -> 트리를 매번 읽기에는 불편하다.
        fin.seek(0); // 포인터를 파일 맨앞으로 이동
        encode(fin,fOut);
    }


    private void readFile(String filePath) throws IOException {
        RandomAccessFile fIn = new RandomAccessFile(filePath,"r");
        int dataIndex = fIn.readInt();
        Long dataSize = fIn.readLong();

        for (int j = 0; j < dataIndex; j++) {
            byte symbol = (byte) fIn.read();
            int runLength = fIn.readInt();
            int freq =  fIn.readInt();

            Run r = new Run(symbol, runLength, freq);
            runs.add(r);
        }

        int numberCounter = 1;
        String tmpString = "";
        try {
            while (true)
            {
                byte codeword = fIn.readByte();
                System.out.print(codeword + " ");
            }
        }
        catch (EOFException ex)
        {
            System.out.println();
            System.out.println("end file");
        }

    }

    public static void main(String[] args) {
        HuffmanEncode huffman = new HuffmanEncode();
        RandomAccessFile fin;
        try {
            String fileName = "/home/skahn/work/sample.txt";
            fin = new RandomAccessFile(fileName,"r");
            huffman.compressFile(fileName, fin);
            huffman.readFile(fileName+".z");
            fin.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
