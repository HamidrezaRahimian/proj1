package dynamicLoadBalancing;

public class pc_dynamic {
    public static void main(String[] args) throws InterruptedException {
        int NUM_THREADS = 4;
        int NUM_END = 200000;

        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        DynamicBalancer.run(NUM_THREADS, NUM_END); // Call the general method
    }
}
