package dynamicLoadBalancing;

import dynamicLoadBalancing.DynamicBalancer;

public class pc_dynamic {
    // the reason of naming things like NUM_END and.. is to have the same variable as the pc_serial.java :DDDD
    static int NUM_END = 200000; // last number
    static int NUM_THREADS = 4;
    static int TASK_SIZE = 10; //we can set it in any size ,but we do 10 cause that is suggested in the project info

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        Thread[] threads = new Thread[NUM_THREADS]; //Create an array of threads (based on how many you want)
        long programStart = System.currentTimeMillis(); // Start time will be recorded

        // initialize the dynamic balancer helper class
        DynamicBalancer balancer = new DynamicBalancer(NUM_END, TASK_SIZE);

        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadIndex = i;

            threads[i] = new Thread(() -> {
                long threadStart = System.currentTimeMillis(); //record time for each Thread

                balancer.processChunk(); // let the thread do its work using the helper class

                long threadEnd = System.currentTimeMillis();
                System.out.println("Thread " + threadIndex + " took " + (threadEnd - threadStart) + "ms");
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        long programEnd = System.currentTimeMillis();
        long totalTime = programEnd - programStart; //runtime calculation

        // Final output
        System.out.println("Total time: " + totalTime + "ms  using dynamic load balancing");
        System.out.println("Performance: " + (1000.0 / totalTime) + "   using dynamic load balancing");
        System.out.println("Total prime numbers: " + balancer.primeCount.get() + "   using dynamic load balancing");
    }
}