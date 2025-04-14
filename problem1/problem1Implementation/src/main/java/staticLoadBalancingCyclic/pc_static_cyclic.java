package staticLoadBalancingCyclic;

public class pc_static_cyclic {

    // This is just the entry point – like the remote control 📺
    public static void main(String[] args) throws InterruptedException {
        int NUM_THREADS = 4;         // Default number of threads
        int NUM_END = 200000;        // Count up to this number

        // Allow user to override settings via command line
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }

        // Call the general method that runs the static cyclic load balancing logic
        CyclicBalancer.run(NUM_THREADS, NUM_END);
    }
}
