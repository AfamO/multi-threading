public class DeadLockTest {
    private static Object lockThread1 = new Object();
    private static Object lockThread2 = new Object();

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lockThread1) {
                System.out.println("Thread1 - Holding lock_thread1");
                try {
                    Thread.sleep(30);
                }   catch (InterruptedException exception) {
                }
                System.out.println("Thread1 - Waiting for lock_thread2");
                synchronized (lockThread2) {
                    System.out.println("Thread 1 - Holding lock 1 & 2");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lockThread1) {
                System.out.println("Thread2 - Holding lock_thread1");
                try {
                    Thread.sleep(30);
                }   catch (InterruptedException exception) {
                }
                System.out.println("Thread2- Waiting for lock_thread1");
                synchronized (lockThread2) {
                    System.out.println("Thread2- Holding lock 1 and 2");
                }
            }
        }
    }

   public static void runTheThreads() {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        System.out.println("The thread's state is ::"+thread1.getState());
        thread1.start();
        System.out.println("The thread's state is::"+thread1.getState());
        thread1.start();
        thread2.start();
    }
}
