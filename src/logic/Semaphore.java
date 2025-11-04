package logic;

public class Semaphore {
    
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void waitSemaphore() {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        value--;
    }
    
    public synchronized void signal() {
        value++;    
        notify();
    }

    public synchronized int availablePermits() {
        return value;
    }
}

