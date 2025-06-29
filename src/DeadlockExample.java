public class DeadlockExample {
    static final Object A = new Object();
    static final Object B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("Поток 1 ожидает Объект В");
                }
                synchronized (B) {
                    System.out.println("Поток 1 получил объект А и объект B");
                }
            }
        });
        ;
        Thread t2 = new Thread(() -> {
            synchronized (B) {
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                System.out.println("Поток 2 ожидает Объект А");
                synchronized (A) {
                    System.out.println("Поток 2 получил объект В и объект А");
                }
            }
        });

        t1.start();
        t2.start();
    }
}


