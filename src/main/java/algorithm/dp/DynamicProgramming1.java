package algorithm.dp;

import java.util.Arrays;

public class DynamicProgramming1 {

    public static int f[] = new int[256];
    public static int count = 0;

    public static int fibo(int n) {
        count++;
        if (n<=1) {
            return 1;
        }
        return fibo(n-1)+fibo(n-2);
    }

    public static int fiboMemo(int n) {
        count++;
        if (n <= 1) {
            return f[n];
        }

        if (f[n] != -1) {
            return f[n];
        }


        f[n] = fiboMemo(n-1) +fiboMemo(n-2);
        return f[n];
    }

    public static int fiboDP(int n) {
        for(int i = 2; i<=n; i++) {
            count++;
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        Arrays.fill(f,-1);
        f[0] = f[1] = 1;

        int result = fiboMemo(20);
        System.out.println("fibo number: " + result);
        System.out.println("Call Count: " + count);
    }
}
