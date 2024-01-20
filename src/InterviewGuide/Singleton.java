package InterviewGuide;

// 静态内部类版本
public class Singleton {
    private Singleton() {
    }

    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        SingletonEnum.INSTANCE.doSomeTing();
        Singleton.getInstance();
    }
}


// 线程安全的双重检查锁
class Singleton1 {
    private Singleton1() {
    }

    private volatile static Singleton1 instance;
    private static Object object = new Object();

    public static Singleton1 getInstance() {
        if (instance == null) {
            synchronized (object) {
                instance = new Singleton1();
            }
        }
        return instance;
    }
}


// 简单懒汉式，性能比较低
class Singleton2{
    private Singleton2() {
    }

    private static Singleton2 instance;

    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

// 简单饿汉式，线程安全
class Singleton3{
    private Singleton3() {}
    private static Singleton3 instance = new Singleton3();

    public static Singleton3 getInstance() {
        return instance;
    }
}

// 枚举类型
enum SingletonEnum {
    INSTANCE;

    public void doSomeTing() {
        System.out.println("saa");
    }
}
