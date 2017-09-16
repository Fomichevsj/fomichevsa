public class TestJThread {
    public static void main(String[] args) {

        System.out.println("Главный поток начал работу...");
        JThread myThread = new JThread();
        new Thread(myThread,"MyThread").start();
        System.out.println("Главный поток продолжает работу");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myThread.disable();


        System.out.println("Главный поток завершил работу...");
    }
}
