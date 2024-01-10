package interview;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatePrinting {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int count = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (count <= 100) {
                lock.lock();
                try {
                    if (count % 2 == 1) {
                        System.out.println(count);
                        count++;
                        condition.signal();
                    } else {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (count <= 100) {
                lock.lock();
                try {
                    if (count % 2 == 0) {
                        System.out.println(count);
                        count++;
                        condition.signal();
                    } else {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
