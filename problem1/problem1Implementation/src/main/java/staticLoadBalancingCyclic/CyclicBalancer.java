package staticLoadBalancingCyclic;

public class CyclicBalancer {

    // This method handles the magic of Cyclic Load Balancing 🔄
    public static void run(int NUM_THREADS, int NUM_END) throws InterruptedException {
        int primeCount = 0;

        Thread[] threads = new Thread[NUM_THREADS];   // our thread army 🧙‍♂️
        int[] localCounts = new int[NUM_THREADS];     // each thread gets its own scoreboard

        long programStart = System.currentTimeMillis(); // timer ON ⏱️

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                long threadStart = System.currentTimeMillis(); // timer for this thread
                int local = 0;

                // Here’s the CYCLIC trick: thread i works on i, i+NUM_THREADS, i+2*NUM_THREADS, etc.
                for (int j = threadIndex; j < NUM_END; j += NUM_THREADS) {
                    if (isPrime(j)) local++;
                }

                localCounts[threadIndex] = local;

                long threadEnd = System.currentTimeMillis();
             //   System.out.println("Thread " + threadIndex + " took " + (threadEnd - threadStart) + "ms");
            });

            threads[i].start(); // thread goes brrrr 🌀
        }

        // Wait for all threads to finish counting their primes
        for (Thread t : threads) t.join();

        // Add up all local prime counts
        for (int count : localCounts) {
            primeCount += count;
        }

        long programEnd = System.currentTimeMillis(); // timer OFF 🛑
        long totalTime = programEnd - programStart;

        // Tada! 🎉 Print the results
        System.out.println("Total time: \u001B[35m" + totalTime + "\u001B[0m ms  using static cyclic load balancing");
   //     System.out.println("Performance: " + (1000.0 / totalTime) + "   using static cyclic load balancing");
   //     System.out.println("Total prime numbers: " + primeCount + "   using static cyclic load balancing");
    }

    // This function checks if a number is a prime number (simple & classic)
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
