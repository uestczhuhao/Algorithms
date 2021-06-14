package leetcode.week.competition;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mizhu
 * @date 2021/6/13 23:53
 */
public class Demo {

    /**
     * 使用计数器，两个线程分别在计数器为奇数/偶数时打印，
     * 使用AtomicBoolean更简单，这里使用AtomicInteger
     * 是考虑可拓展为N个线程交替打印
     */
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);
        new PrinterA("线程 A", counter).start();
        new PrinterB("线程 B", counter).start();
    }

    private static class PrinterA extends Thread {
        private final AtomicInteger counter;

        public PrinterA(String name, AtomicInteger counter) {
            super(name);
            this.counter = counter;
        }

        @Override
        public void run() {
            for (char i = 'A'; i <= 'Z'; ) {
                if (counter.get() % 2 == 0) {
                    System.out.println(getName() + " 打印字母: " + i++);
                    counter.incrementAndGet();
                }
            }
        }
    }

    private static class PrinterB extends Thread {
        private final AtomicInteger counter;

        public PrinterB(String name, AtomicInteger counter) {
            super(name);
            this.counter = counter;
        }

        @Override
        public void run() {
            for (char i = 'A'; i <= 'Z'; ) {
                if (counter.get() % 2 != 0) {
                    System.out.println(getName() + " 打印字母: " + i++);
                    counter.incrementAndGet();
                }
            }
        }
    }
}
