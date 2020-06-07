package pl.ks.profiling.demo.memory;

import java.util.ArrayList;
import java.util.List;

class ThreadMemory {
    private static int doIt(int i) throws InterruptedException {
        if (i == 4000) {
            synchronized (ThreadMemory.class) {
                System.out.println("Waiting");
                ThreadMemory.class.wait();
                System.out.println("Waking up");
            }
            return i;
        }
        return i + doIt(i + 1);
    }

    public static void main(String[] args) throws Exception {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            threads.add(new Thread(() -> {
                try {
                    doIt(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }

        System.in.read();

        synchronized (ThreadMemory.class) {
            ThreadMemory.class.notifyAll();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        threads.clear();
        System.out.println("GC");
        System.gc();
        System.in.read();
    }
}