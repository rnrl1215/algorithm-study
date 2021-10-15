package algorithm.huffman;

import java.util.Objects;

public class Run implements Comparable<Run> {
    public byte symbol;
    public int runLength;
    public int freq;

    private Run leftChild;
    private Run rightChild;
    private Run rightRun;

    private int codeword;
    private int codewordLen;

    public Run(Byte symbol, int runLength) {
        this.symbol = symbol;
        this.runLength = runLength;
        this.freq = 1;
    }

    public Run(Byte symbol, int runLength, int freq) {
        this.symbol = symbol;
        this.runLength = runLength;
        this.freq = freq;
    }


    public Run() {
    }

    public void setLeftChild(Run leftChild) {
        this.leftChild = leftChild;
    }
    public void setRightChild(Run rightChild) {
        this.rightChild = rightChild;
    }
    public byte getSymbol() {
        return symbol;
    }
    public void setSymbol(byte symbol) {
        this.symbol = symbol;
    }
    public int getRunLength() {
        return runLength;
    }
    public void setRunLength(int runLength) {
        this.runLength = runLength;
    }
    public int getFreq() {
        return freq;
    }
    public void setFreq(int freq) {
        this.freq = freq;
    }
    public Run getLeftChild() {
        return leftChild;
    }
    public Run getRightChild() {
        return rightChild;
    }

    public void increaseFreq() {
        this.freq = this.freq+1;
    }

    public int getCodeword() {
        return codeword;
    }

    public void setCodeword(int codeword) {
        this.codeword = codeword;
    }

    public int getCodewordLen() {
        return codewordLen;
    }

    public void setCodewordLen(int codewordLen) {
        this.codewordLen = codewordLen;
    }

    public Run getRightRun() {
        return rightRun;
    }

    public void setRightRun(Run rightRun) {
        this.rightRun = rightRun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return symbol == run.symbol && runLength == run.runLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, runLength);
    }

    @Override
    public int compareTo(Run o) {
        if (o.getFreq() == this.getFreq()) {
            return this.getSymbol() - o.getSymbol();
        } else {
            return o.getFreq() - this.getFreq();
        }
    }
}
