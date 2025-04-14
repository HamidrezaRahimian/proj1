import dynamicLoadBalancing.pc_dynamic;
import staticLoadBalancingBlock.pc_static_block;
import staticLoadBalancingCyclic.pc_static_cyclic;

public class compare_all {
    private static final int NUM_END = 200000;
    private static final int[] THREAD_COUNTS = {1, 2, 4, 6, 8, 10, 12, 14, 16, 32};

    public static void main(String[] args) throws Exception {
        // Custom number range if provided
        int end = args.length > 0 ? Integer.parseInt(args[0]) : NUM_END;

        // Header row for thread counts
        System.out.print("Thread count\t");
        for (int threads : THREAD_COUNTS) {
            System.out.print(threads + "\t");
        }
        System.out.println();

        // Run Static Block balancing for all thread counts
        System.out.print("Static (Block)\t");
        for (int threads : THREAD_COUNTS) {
            long startTime = System.currentTimeMillis();
            pc_static_block.main(new String[]{String.valueOf(threads), String.valueOf(end)});
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.print(elapsed + "\t");
        }
        System.out.println();

        // Run Static Cyclic balancing for all thread counts
        System.out.print("Static (cyclic) task size:10 numbers\t");
        for (int threads : THREAD_COUNTS) {
            long startTime = System.currentTimeMillis();
            pc_static_cyclic.main(new String[]{String.valueOf(threads), String.valueOf(end)});
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.print(elapsed + "\t");
        }
        System.out.println();

        // Run Dynamic balancing for all thread counts
        System.out.print("Dynamic task size:10 numbers\t");
        for (int threads : THREAD_COUNTS) {
            long startTime = System.currentTimeMillis();
            pc_dynamic.main(new String[]{String.valueOf(threads), String.valueOf(end)});
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.print(elapsed + "\t");
        }
        System.out.println();
    }
}