package staticLoadBalancingBlock;

public class pc_static_block {
    static int NUM_THREADS = 4;
    static int NUM_END = 200000;

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        Thread[] threads = new Thread[NUM_THREADS];
        int[] threadPrimeCounts = new int[NUM_THREADS]; // to store prime count of each thread

        long programStart = System.currentTimeMillis(); // time when program started

        int rangePerThread = NUM_END / NUM_THREADS; // divide the work into equal blocks

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;
            final int start = threadId * rangePerThread;
            final int end = (threadId == NUM_THREADS - 1) ? NUM_END : start + rangePerThread;

            threads[i] = new Thread(() -> {
                long threadStart = System.currentTimeMillis();

                BlockBalancer balancer = new BlockBalancer(start, end);
                int localCount = balancer.countPrimesInBlock();
                threadPrimeCounts[threadId] = localCount;

                long threadEnd = System.currentTimeMillis();
                System.out.println("Thread " + threadId + " took " + (threadEnd - threadStart) + "ms");
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join(); // wait for all threads to finish
        }

        long programEnd = System.currentTimeMillis();
        long totalTime = programEnd - programStart;

        // Add up all the prime counts from each thread
        int totalPrimes = 0;
        for (int count : threadPrimeCounts) {
            totalPrimes += count;
        }

        // Final Output
        System.out.println("Total time: " + totalTime + "ms  using static block load balancing");
        System.out.println("Performance: " + (1000.0 / totalTime) + "   using static block load balancing");
        System.out.println("Total prime numbers: " + totalPrimes + "   using static block load balancing");
    }
}
