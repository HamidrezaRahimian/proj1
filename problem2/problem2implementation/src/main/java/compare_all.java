import java.io.File;
import java.util.Scanner;

public class compare_all {
    private static final int[] THREAD_COUNTS = {1, 2, 4, 6, 8, 10, 12, 14, 16, 32};

    public static void main(String[] args) throws Exception {
        // Load matrix only once
        Scanner sc = new Scanner(System.in);
        int[][] a = readMatrix(sc);
        int[][] b = readMatrix(sc);

        System.out.println("[THREAD COUNT PERFORMANCE COMPARISON]\n");

        for (int THREADS : THREAD_COUNTS) {
            int[][] result = new int[a.length][b[0].length];
            MatrixMulThread[] threads = new MatrixMulThread[THREADS];
            int blockSize = a.length / THREADS;

            long startAll = System.currentTimeMillis();

            for (int i = 0; i < THREADS; i++) {
                int start = i * blockSize;
                int end = (i == THREADS - 1) ? a.length : start + blockSize;
                threads[i] = new MatrixMulThread(i, start, end, a, b, result);
                threads[i].start();
            }

            for (MatrixMulThread t : threads) t.join();

            long endAll = System.currentTimeMillis();
            long totalTime = endAll - startAll;

            System.out.printf("Threads: %2d => Time: %4d ms\n", THREADS, totalTime);
        }
    }

    // COPY your same readMatrix method
    static int[][] readMatrix(Scanner sc) {
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        int[][] mat = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = sc.nextInt();
        return mat;
    }
}
