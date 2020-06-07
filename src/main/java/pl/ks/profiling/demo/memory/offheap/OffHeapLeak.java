package pl.ks.profiling.demo.memory.offheap;

import static pl.ks.profiling.demo.memory.offheap.OffHeapAllocator.allocate;

class OffHeapLeak {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            allocate();
            System.out.println("Allocated: " + ++i);
            Thread.sleep(10);
        }
    }
}
