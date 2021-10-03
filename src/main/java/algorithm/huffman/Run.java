package algorithm.huffman;

import java.util.Objects;

public class Run implements Comparable<Run> {
    public byte symbol;
    public int runLength;
    public int freq = 1;
    private Run leftChild;
    private Run rightChild;

    public int codeword;
    public int codewordLen;

    public Run(Byte symbol, int runLength) {
        this.symbol = symbol;
        this.runLength = runLength;
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
        if(this.getFreq() != o.getFreq()) {
            return o.getFreq() - this.getFreq();
        } else {
            return  o.getSymbol() - this.getSymbol();
        }
    }
}