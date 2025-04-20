import java.io.File;
import java.util.Scanner;

// Thread class for multiplying a block of matrix rows
public class MatrixMulThread extends Thread {
    int threadId, startRow, endRow;
    int[][] a, b, result;
    long executionTime;

    public MatrixMulThread(int threadId, int startRow, int endRow, int[][] a, int[][] b, int[][] result) {
        this.threadId = threadId;
        this.startRow = startRow;
        this.endRow = endRow;
        this.a = a;
        this.b = b;
        this.result = result;
    }

    public void run() { //we define run here so in other one we basically dont need any start
        long start = System.currentTimeMillis();
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        executionTime = System.currentTimeMillis() - start;
    }
}
