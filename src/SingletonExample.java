public class SingletonExample {
    private static SingletonExample instance;
    private final Object lock = new Object();
    private SingletonExample() {
    }
    public static synchronized SingletonExample getInstance() {
       //synchronized (instance) {
           if (instance == null) {
               System.out.println("Singleton Instance created");
               instance = new SingletonExample();
           } else {
               System.out.println("Singleton Instance returned");
           }
           return instance;
       //}
    }
}
