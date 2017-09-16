import static java.lang.Thread.sleep;

/**
 * Class implements wait beautifier
 * out dots in console while program makes requests

 */
public class WaitIndicator implements Runnable {
    private volatile Boolean isActive = true;
    @Override
    public void run() {
        int i = 1;
        while (isActive) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            System.out.print(".");
        }
    }

        public void setStop() {
            this.isActive = false;
    }

}
