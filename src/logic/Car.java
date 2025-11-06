package logic;
import GUI.*;
import java.util.Queue;

public class Car extends Thread {
    private int id;
    private Queue<Car> queue;
    private Semaphore mutex;
    private Semaphore availableAreas;
    private Semaphore waitingCars;
    private SimulationView view;

    public Car(int id, Queue<Car> queue, Semaphore mutex, Semaphore availableAreas, Semaphore waitingCars, SimulationView view) {
        this.id = id;
        this.queue = queue;
        this.mutex = mutex;
        this.availableAreas = availableAreas;
        this.waitingCars = waitingCars;
        this.view = view;
    }

    public int getCarId() {
        return id;
    }

    public void run() {
        try {
            System.out.println("C" + id + " arrived.");

            availableAreas.waitSemaphore();
            mutex.waitSemaphore();

            queue.add(this);
            view.addCarToQueue("C" + id);
            System.out.println("C" + id + " entered the waiting queue.");

            mutex.signal();
            waitingCars.signal(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
