import java.io.File;
import java.util.Scanner;

// Main class
class pc_MatrixMulti {
    static final int THREADS =5;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); // i added file as input in debugging configuration
        int[][] a = readMatrix(sc);
        int[][] b = readMatrix(sc);
        int[][] result = new int[a.length][b[0].length];

        MatrixMulThread[] threads = new MatrixMulThread[THREADS];
        int blockSize = a.length / THREADS; //deviding task to blocks

        long startAll = System.currentTimeMillis();

        for (int i = 0; i < THREADS; i++) {
            int start = i * blockSize;
            int end = (i == THREADS - 1) ? a.length : start + blockSize;
            threads[i] = new MatrixMulThread(i, start, end, a, b, result);
            threads[i].start();
        }

        for (MatrixMulThread t : threads) t.join();

        long endAll = System.currentTimeMillis();

        System.out.println("Matrix[" + result.length + "][" + result[0].length + "]");
        System.out.println("Sum = " + getSum(result));
        for (MatrixMulThread t : threads) {
            System.out.println("Thread " + t.threadId + ": " + t.executionTime + " ms");
        }
        System.out.println("Total time: " + (endAll - startAll) + " ms");
    }

    static int[][] readMatrix(Scanner sc) { //typical read matrix method
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] mat = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = sc.nextInt();
        return mat;
    }

    static int getSum(int[][] mat) { //function for summung up the result
        int sum = 0;
        for (int[] row : mat)
            for (int val : row)
                sum += val;
        return sum;
    }
}
