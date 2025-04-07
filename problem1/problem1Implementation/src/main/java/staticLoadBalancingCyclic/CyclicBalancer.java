package staticLoadBalancingCyclic;

public class CyclicBalancer {
    private int numStart;
    private int numEnd;
    private int taskSize;

    public CyclicBalancer(int numStart, int numEnd, int taskSize) {
        this.numStart = numStart;
        this.numEnd = numEnd;
        this.taskSize = taskSize;
    }

    // Each thread will call this method with its thread ID and total number of threads
    public int countPrimesForThread(int threadId, int numThreads) {
        int localCount = 0;

        // Each thread takes turns grabbing task-size blocks in a cyclic pattern
        for (int i = numStart + threadId * taskSize; i < numEnd; i += numThreads * taskSize) {
            for (int j = i; j < i + taskSize && j < numEnd; j++) {
                if (isPrime(j)) {
                    localCount++;
                }
            }
        }

        return localCount;
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
