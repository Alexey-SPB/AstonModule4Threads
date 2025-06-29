
public class LivelockExample {
        private static final Object res1 = new Object();
        private static final Object res2 = new Object();

        public static void main(String[] args) {
            Thread thread1 = new Thread(() -> {
                while (true) {
                    try {
                        synchronized (res1) {
                            System.out.println("Первый поток получил данные от ресурса 1");
                            Thread.sleep(50);
                            synchronized (res2) {
                                System.out.println("Первый поток получил данные от ресурса 2");
                            }
                        }
                        //break;
                    } catch (InterruptedException e) {
                        System.out.println("Первый поток получил оба ресурса");
                    }
                }
            });

            Thread thread2 = new Thread(() -> {
                while (true) {
                    try {
                        synchronized (res2) {
                            System.out.println("Второй поток получил данные от ресурса 2");
                            Thread.sleep(10);
                            synchronized (res1) {
                                System.out.println("Второй поток получил данные от ресурса 1");
                            }
                        }
                        //break;
                    } catch (InterruptedException e) {
                        System.out.println("Второй поток получил оба ресурса");
                    }
                }
            });

            thread1.start();
            thread2.start();
        }
    }


