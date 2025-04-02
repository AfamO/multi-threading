import java.io.*;

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Launching IncrementerThread");
        Incrementer incrementer = new Incrementer();
        Runnable incrementerRunnable = incrementer::increment;

        Thread t1 = new Thread(incrementerRunnable,"T1");
        Thread t2 = new Thread(incrementerRunnable, "T2");

        t1.start();
        t2.start();
        //wait for t1 and t2 to finish
        t1.join();
        t2.join();


        //System.out.println("Final value of x=="+this.getX());

        System.out.println("Launching checkAndActDemo");
        CheckAndAct checkAndAct = new CheckAndAct();
        Runnable checkAndActRunnable = () ->{
            checkAndAct.checkAndAct(10);
        };

        t1 = new Thread(checkAndActRunnable,"T1");
        t2 = new Thread(checkAndActRunnable, "T2");

        t1.start();
        t2.start();
        //wait for xi and x2 to finish
        t1.join();
        t2.join();

        //System.out.println("Final value of x=="+this.getX());

        System.out.println("Launching ThreadCounterDemo");
        MyCounter myCounter = new MyCounter();
        ThreadCounterDemo t11= new ThreadCounterDemo(myCounter);
        ThreadCounterDemo t22 = new ThreadCounterDemo(myCounter);

        t11.start();
        t22.start();
        t11.join();
        t22.join();

        String s1 ="SHINCHAN";
        String s2= "NOHARAAA";
        int result = commonChild(s1, s2);

        System.out.println("commonchild Result=="+result);

        s1 ="AA";
        s2= "BB";

        result = commonChild(s1, s2);
        System.out.println("common child Result=="+result);

        s1 ="HARRY";
        s2= "SALLY";

        result = commonChild(s1, s2);
        System.out.println("common child Result=="+result);
        SingletonExample.getInstance();
        SingletonExample.getInstance();
        DeadLockTest.runTheThreads();

    }

    public static int commonChild(String a, String b){
        int[][] C = new int[a.length()+1][b.length()+1];

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    C[i+1][j+1] = C[i][j] + 1;
                    System.out.println("At i=="+i+" and j=="+j+" C =="+(C[i][j] + 1));
                } else {
                    C[i+1][j+1] = Math.max(C[i+1][j], C[i][j+1]);
                    //System.out.println("Max between "+C[i+1][j]+" and "+C[i][j+1]+"=="+(Math.max(C[i+1][j], C[i][j+1])));
                }
            }
        }
        return C[a.length()][b.length()];
    }

}


class ThreadCounterDemo extends Thread {
    private MyCounter myCounter;

    public ThreadCounterDemo(MyCounter myCounter){
        this.myCounter = myCounter;
    }

    @Override
    public void run(){
        MyCounter.doCount();
    }
}

class MyCounter {

    public synchronized static void  doCount(){
        for (int i = 0; i < 3; i++){
            System.out.println(Thread.currentThread().getName()+" -> "+i);
        }
    }


}

class Incrementer{

    public void increment(){
        int x = 0;
        //synchronized (x) {
            x = 100;

            for (int i = 0; i < 10; i++) {
                x = x + 1;
                System.out.println(Thread.currentThread().getName()+"  x=="+x);
            }
       // }

    }

    public void  startAllThreads() throws InterruptedException {


    }
}
class CheckAndAct{

    public void checkAndAct(int x){
        x= 100;
        if (x == 100) {
            x = x * 2;
        }
        System.out.println(Thread.currentThread().getName()+"  x=="+x);

    }
}
