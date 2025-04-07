package dynamicLoadBalancing;


import java.util.concurrent.atomic.AtomicInteger;

public class DynamicBalancer {
    // Atomic variables are thread-safe without synchronized blocks
    // this will avoid race and errors
    public AtomicInteger nextTaskStart = new AtomicInteger(0);
    public AtomicInteger primeCount = new AtomicInteger(0);
    private int numEnd;
    private int taskSize;

    public DynamicBalancer(int numEnd, int taskSize) {
        this.numEnd = numEnd;
        this.taskSize = taskSize;
    }

    // this function will be used by each thread
    public void processChunk() {
        int localCount = 0; // how many primes it found by itself.

        // Keep grabbing chunks until we hit the limit
        while (true) {
            int start = nextTaskStart.getAndAdd(taskSize); // safely get next chunk
            if (start >= numEnd) break;

            for (int j = start; j < start + taskSize && j < numEnd; j++) {
                if (isPrime(j)) localCount++;
            }
        }

        // Add local count to shared total safely
        primeCount.addAndGet(localCount);
    }

    // check if the number is Prime
    static boolean isPrime(int num) {
        if (num <= 1) return false; //theres no primeNumb less than 1
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}