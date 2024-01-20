package InterviewGuide;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MyThreadPool {
    //线程池中默认线程的个数为5
    private static int WORK_NUM = 5;
    //队列默认任务为100
    private static int TASK_COUNT = 100;

    //工作线程数组
    private WorkThread[] workThreads;
    //等待队列
    private final BlockingQueue<Runnable> taskQueue;
    //用户在构造这个线程池时，希望启动的线程数
    private final int workerNum;

    /**
     * 内部类，工作线程类
     */
    private class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable runnable = null;
            try {
                while (! isInterrupted()) {  //如果没有被标记中断，则从对阻塞队列中取出任务执行
                    runnable = taskQueue.take();  //take：阻塞接口的移除方法
                    if (runnable != null) {
                        System.out.println("Thread ID:" + getId() + " ready exec:" + runnable.toString());
                        runnable.run();
                    }
                    runnable = null;  //help gc
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //线程停止工作
        public void stopWork() {
            interrupt();
        }
    }

    //构造方法：创建具有默认线程个数的线程池
    public MyThreadPool() {
        this(WORK_NUM,TASK_COUNT);
    }

    //创建线程池,workNum为线程池中工作线程的个数
    public MyThreadPool(int workerNum, int taskCount) {
        if (workerNum <= 0) {
            workerNum = WORK_NUM;
        }
        if (taskCount <= 0) {
            taskCount = TASK_COUNT;
        }
        this.workerNum = workerNum;
        taskQueue = new ArrayBlockingQueue<>(taskCount);
        workThreads = new WorkThread[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workThreads[i] = new WorkThread();  //new一个工作线程
            workThreads[i].start();  //启动工作线程
        }
        Runtime.getRuntime().availableProcessors();
    }
    //执行任务，其实就是把任务加入任务队列，什么时候执行由线程池管理器决定
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);   //put:阻塞接口的插入
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    //销毁线程池,该方法保证所有任务都完成的情况下才销毁所有线程，否则等待任务完成再销毁
    public void destory() {
        //工作线程停止工作，且置为null
        System.out.println("ready close thread...");
        for (int i = 0; i < workerNum; i++) {
            workThreads[i].stopWork();
            workThreads[i] = null; //help gc
        }
        taskQueue.clear();  //清空等待队列

    }
    //覆盖toString方法，返回线程信息：工作线程个数和已完成任务个数
    @Override
    public String toString() {
        return "WorkThread number:" + workerNum + " ==分割线== wait task number:" + taskQueue.size();
    }
}
