package staticLoadBalancingBlock;

public class BlockBalancer {

    // Our main logic to count prime numbers using Block Static Load Balancing
    public static void run(int NUM_THREADS, int NUM_END) throws InterruptedException {
        int primeCount = 0;

        Thread[] threads = new Thread[NUM_THREADS];   // army of worker threads
        int[] localCounts = new int[NUM_THREADS];     // to collect how many primes each thread found

        long programStart = System.currentTimeMillis(); // stopwatch start 🚀

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                long threadStart = System.currentTimeMillis();
                int local = 0;

                int blockSize = NUM_END / NUM_THREADS;
                int start = threadIndex * blockSize;
                if (start < 2) start = 2;
                int end = (threadIndex == NUM_THREADS - 1) ? NUM_END : start + blockSize;

                for (int j = start; j < end; j++) {
                    if (isPrime(j)) local++;
                }

                localCounts[threadIndex] = local;
                long threadEnd = System.currentTimeMillis();
              //  System.out.println("Thread " + threadIndex + " took " + (threadEnd - threadStart) + "ms");
            });

            threads[i].start();
        //  System.out.println("Thread " + i + " started");
            // go go go little thread!
        }

        // Wait for all threads to finish their prime-counting business
        for (Thread t : threads) t.join();
      //  System.out.println("Threads stopped");

        // Gather all the local prime counts from each thread
        for (int count : localCounts) {
            primeCount += count;
        }

        long programEnd = System.currentTimeMillis(); // stopwatch stop 🛑
        long totalTime = programEnd - programStart;

        // Print the juicy results 🧃
        System.out.println("Total time: \u001B[31m" + totalTime + "\u001B[0m ms  using static block load balancing");
      //  System.out.println("Performance: " + (1000.0 / totalTime) + "   using static block load balancing");
      // System.out.println("Total prime numbers: " + primeCount + "   using static block load balancing");
    }

    // The classic "is it prime?" function.
    static boolean isPrime(int num) {
        int i;
        if (num <= 1) return false;

        for (i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
