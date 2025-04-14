package dynamicLoadBalancing;

import java.util.concurrent.atomic.AtomicInteger;

public class DynamicBalancer {
    public static void run(int NUM_THREADS, int NUM_END) throws InterruptedException {
        int TASK_SIZE = 10;

        AtomicInteger nextTaskStart = new AtomicInteger(0);
        AtomicInteger primeCount = new AtomicInteger(0);

        Thread[] threads = new Thread[NUM_THREADS];
        long programStart = System.currentTimeMillis();

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                long threadStart = System.currentTimeMillis();
                int localCount = 0;

                while (true) {
                    int start = nextTaskStart.getAndAdd(TASK_SIZE);
                    if (start >= NUM_END) break;

                    for (int j = start; j < start + TASK_SIZE && j < NUM_END; j++) {
                        if (isPrime(j)) localCount++;
                    }
                }

                primeCount.addAndGet(localCount);
                long threadEnd = System.currentTimeMillis();
         //       System.out.println("Thread " + threadIndex + " took " + (threadEnd - threadStart) + "ms");
            });

            threads[i].start();
        }

        for (Thread t : threads) t.join();

        long programEnd = System.currentTimeMillis();
        long totalTime = programEnd - programStart;

        System.out.println("Total time: \u001B[32m" + totalTime + "\u001B[0m ms  using dynamic load balancing");
     //   System.out.println("Performance: " + (1000.0 / totalTime) + "   using dynamic load balancing");
     //   System.out.println("Total prime numbers: " + primeCount.get() + "   using dynamic load balancing");
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
