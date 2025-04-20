package staticLoadBalancingBlock;

public class pc_static_block {

    // Just your friendly launcher 🚀
    public static void main(String[] args) throws InterruptedException {
        int NUM_THREADS = 4;         // Default number of threads (can be overridden via args)
        int NUM_END = 200000;        // Count primes up to this number

        // Allow user to override settings via command line
        if (args.length == 2) {
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_END = Integer.parseInt(args[1]);
        }
        // And now we call the general static block logic to do the real work
        BlockBalancer.run(NUM_THREADS, NUM_END);
    }
}
