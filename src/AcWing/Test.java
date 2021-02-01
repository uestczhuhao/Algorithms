package AcWing;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


//@NotEmpty
public class Test {
    @NotNull
    public static void main(String[] args) throws Exception {


        A a = new A();
        Thread1 t1 = new Thread1(a);
        Thread2 t2 = new Thread2(a);
        t1.start();
        t2.start();
//        A.test1();
//        a.test2();
    }
}

class Thread1 extends Thread {
    private A a;
    public Thread1(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.test1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Thread2 extends Thread {
    private A a;
    public Thread2(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class A {
    static synchronized void test1() throws Exception {
        System.out.println("class lock");
        while (true) {
            Thread.sleep(1000);
        }
    }

    synchronized void test2() throws Exception {
        System.out.println("object lock");
        Thread.sleep(1000);
    }
}
