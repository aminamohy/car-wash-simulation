package logic;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Car extends Thread  {

    private int id;
    private Queue<Car> queue;
    private Semaphore empty, full, mutex;

    public Car(int id, Queue<Car> queue, Semaphore empty, Semaphore full, Semaphore mutex) {
        this.id = id;
        this.queue = queue;
        this.empty = empty;
        this.full = full;
        this.mutex = mutex;
    }

    public int getCarId() {
        return id;
    }

    public void run(){
        try {
            System.out.println(" C" + id + " arrived");
            
            empty.waitSemaphore(); 
            mutex.waitSemaphore();
            
            queue.add(this);
            if (queue.size() == 1) {
                System.out.println("C" + id + " entered queue");
            } else {
                System.out.println("C" + id + " arrived and waiting");
            }
            
            mutex.signal();
            full.signal();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

