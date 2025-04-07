package staticLoadBalancingCyclic;

public class pc_static_cyclic {
    static int NUM_THREADS = 4;
    static int NUM_END = 200000;
    static int TASK_SIZE = 10; // recommended task size is 10

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        Thread[] threads = new Thread[NUM_THREADS];
        int[] threadPrimeCounts = new int[NUM_THREADS]; // each thread will store its prime count here

        long programStart = System.currentTimeMillis(); // record when the program started

        CyclicBalancer balancer = new CyclicBalancer(0, NUM_END, TASK_SIZE);

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadId = i;

            threads[i] = new Thread(() -> {
                long threadStart = System.currentTimeMillis();

                // Each thread does its share of cyclic work
                int localCount = balancer.countPrimesForThread(threadId, NUM_THREADS);
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

        // Sum up total primes
        int totalPrimes = 0;
        for (int count : threadPrimeCounts) {
            totalPrimes += count;
        }

        // Final output
        System.out.println("Total time: " + totalTime + "ms  using static cyclic load balancing");
        System.out.println("Performance: " + (1000.0 / totalTime) + "   using static cyclic load balancing");
        System.out.println("Total prime numbers: " + totalPrimes + "   using static cyclic load balancing");
    }
}
