package staticLoadBalancingBlock;

public class BlockBalancer {
    private int numStart;
    private int numEnd;

    public BlockBalancer(int numStart, int numEnd) {
        this.numStart = numStart;
        this.numEnd = numEnd;
    }

    // This method returns how many prime numbers are between start and end
    public int countPrimesInBlock() {
        int count = 0;

        for (int i = numStart; i < numEnd; i++) {
            if (isPrime(i)) count++;
        }

        return count;
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
