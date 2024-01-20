package InterviewGuide;
import java.util.Random;

public class MyTask implements Runnable {

    private String name;
    public String getName() {
        return name;
    }

    private Random random = new Random();

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(random.nextInt(1000) + 2000);
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getId() + "sleep InterruptedException:" +
                Thread.currentThread().isInterrupted());
        }
        System.out.println("task--- " + name + "---done!");
    }
}
