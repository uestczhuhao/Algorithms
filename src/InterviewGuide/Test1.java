package InterviewGuide;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

    public static void main(String[] args) throws Exception{
        MyThreadPool myThreadPool = new MyThreadPool(3, 3);
        myThreadPool.execute(new MyTask("A"));
        myThreadPool.execute(new MyTask("B"));
        myThreadPool.execute(new MyTask("C"));
        myThreadPool.execute(new MyTask("D"));
        myThreadPool.execute(new MyTask("E"));

        System.out.println(myThreadPool);
        Thread.sleep(10000);
        myThreadPool.destory(); //所有线程都执行完成后，再destory
        System.out.println(myThreadPool);

    }
}
