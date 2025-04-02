
class RunnableThreadDemo implements Runnable {
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getId() + "-" + Thread.currentThread().getName() + " is running");
            //System.out.println("Runnable run() called");
        } catch (Exception exception) {
            System.out.println("Caught Exception::" + exception.getLocalizedMessage());
        }
    }
}

class ExtendableThreadDemo extends Thread {
    public void run() {
        try {
            //System.out.println("ExtendableThread  called");
            System.out.println("Thread " + Thread.currentThread().getId() + " Name::" + Thread.currentThread().getName() + " is running");
        } catch (Exception exception) {
            System.out.println("Caught Exception::" + exception.getLocalizedMessage());
        }
    }
}
class Counter {
    private int value;

    public void increment(){

        ++value;
    }

    public int get(){
        return value;
    }
}

class CounterDemo{

    public void callCounterThread() throws InterruptedException{
        Counter counter = new Counter();

        Runnable incrementTask = () -> {
            synchronized (this) {
                for (int i = 0; i < 100_000; i++) {
                    counter.increment();
                }
            }
        };

        Thread t1 = new Thread(incrementTask,"T1");
        Thread t2 = new Thread(incrementTask,"T2");

        long start = System.nanoTime();
        t1.start();

        t2.start();

        t1.join(); // wait here till T1 completes
        t2.join(); // wait here till T2 completes.
        long end =  System.nanoTime();

        String timeTaken = String.format("%.2f", (end - start)/1000_000.0);
        System.out.println("Final Counter Value: " + counter.get() + " Time Taken: " + timeTaken + " millis");
    }

}


public class MultiThread {


    public static void main(String[] args) throws InterruptedException {
        int n = 8;
        System.out.println("Calling Runnable run() Thread");
        for (int i = 0; i < n; i++) {
            RunnableThreadDemo runnableThreadDemo = new RunnableThreadDemo();
            Thread runnableThread = new Thread(runnableThreadDemo,"My Constructor passed RunnableThread");
            //runnableThreadDemo.run();
            runnableThread.start();
        }
        //System.exit(0);
        System.out.println("Calling ExtendableThread");
        for (int i = 0; i < n; i++) {
            ExtendableThreadDemo extendableThreadDemo = new ExtendableThreadDemo();
            extendableThreadDemo.start();
        }
        System.out.println("Calling CounterThread Demo");
        new CounterDemo().callCounterThread();

        System.out.println("Calling TableThread Demo");
        new  TableThreadDemo().callTableThreads();


    }
}

class Table {
     synchronized public static void printTable(int n) {

       // synchronized (this) {

        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("Exception " + e.getLocalizedMessage() + " occured");
            }
        }
    //}

    }
}

class  TableThreadDemo {
    final Table table = new Table();

    public void callTableThreads(){
        Thread thread1 = new Thread(){
            public void run() {
                Table.printTable(5);
            }
        };

        Thread thread2 = new Thread(){
            public void run() {
                Table.printTable(100);
            }
        };

        thread1.start();
        thread2.start();
    }
}

