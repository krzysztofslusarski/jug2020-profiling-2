package pl.ks.profiling.demo.rsa;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

class JitVsInterpreterExample {
    private static RsaClass rsaClass = new RsaClass();

    public static void main(String[] args) throws Exception {
        long a;
        long b;

        a = optimizeA("MethodA() first run", rsaClass,1);
        b = optimizeB("MethodB() first run", 1);
        displayADivB(a, b);
        System.out.println("");
        Thread.sleep(1000);
        a = optimizeA("MethodA() next 10 run", rsaClass,10);
        b = optimizeB("MethodB() next 10 run", 10);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() next 100 run", rsaClass,100);
        b = optimizeB("MethodB() next 100 run", 100);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() next 1k run", rsaClass,1_000);
        b = optimizeB("MethodB() next 1k run", 1_000);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() next 10k run", rsaClass,10_000);
        b = optimizeB("MethodB() next 10k run", 10_000);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() next 10k run", rsaClass,10_000);
        b = optimizeB("MethodB() next 10k run", 10_000);
        displayADivB(a, b);
        System.out.println("");
        Thread.sleep(1000);
        a = optimizeA("MethodA() JIT? run", rsaClass,15_000);
        b = optimizeB("MethodB() JIT? run", 15_000);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() JIT? run", rsaClass,15_000);
        b = optimizeB("MethodB() JIT? run", 15_000);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() JIT? run", rsaClass,15_000);
        b = optimizeB("MethodB() JIT? run", 15_000);
        displayADivB(a, b);
        System.out.println("");
        a = optimizeA("MethodA() JIT? run", rsaClass,15_000);
        b = optimizeB("MethodB() JIT? run", 15_000);
        displayADivB(a, b);
    }

    private static void displayADivB(long a, long b) {
        BigDecimal result = new BigDecimal(a).divide(new BigDecimal(b), 2, RoundingMode.HALF_EVEN);
        System.out.println("A / B == " + result.toString());
    }

    private static long optimizeA(String message, RsaClass rsaClass, int runs) {
        long startTime = System.nanoTime();

        for (int i = 0; i < runs; i++) {
            methodA(rsaClass);
        }

        long average = (System.nanoTime() - startTime) / runs;
        System.out.println(message + ", average: " + average);
        return average;
    }

    private static long optimizeB(String message, int runs) {
        long startTime = System.nanoTime();

        for (int i = 0; i < runs; i++) {
            methodB();
        }

        long average = (System.nanoTime() - startTime) / runs;
        System.out.println(message + ", average: " + average);
        return average;
    }

    private static byte[] methodA(RsaClass rsaClass) {
        return rsaClass.cypher();
    }

    private static int methodB() {
        int ret = 0;
        for (int i = 0; i < 40_000; i++) {
            ret += RandomUtils.nextInt();
        }
        return ret;
    }
}
