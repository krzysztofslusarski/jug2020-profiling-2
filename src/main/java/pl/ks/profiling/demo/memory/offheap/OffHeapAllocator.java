package pl.ks.profiling.demo.memory.offheap;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

class OffHeapAllocator {
    private static List<ByteBuffer> memoryLeak = new ArrayList<>();

    public static void allocate() {
        memoryLeak.add(ByteBuffer.allocateDirect(1 * 1024 * 1024));
    }
}
