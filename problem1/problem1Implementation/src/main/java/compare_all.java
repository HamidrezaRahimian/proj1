
import dynamicLoadBalancing.pc_dynamic;
import staticLoadBalancingBlock.pc_static_block;
import staticLoadBalancingCyclic.pc_static_cyclic;

public class compare_all {
    public static void main(String[] args) throws Exception {
        int threads = 4;
        int end = 200000;

        System.out.println("============ Running Dynamic Load Balancing ============");
        pc_dynamic.main(new String[]{String.valueOf(threads), String.valueOf(end)});

        System.out.println("\n============ Running Static Block Load Balancing ============");
        pc_static_block.main(new String[]{String.valueOf(threads), String.valueOf(end)});

        System.out.println("\n============ Running Static Cyclic Load Balancing ============");
        pc_static_cyclic.main(new String[]{String.valueOf(threads), String.valueOf(end)});
    }
}
