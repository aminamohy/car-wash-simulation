package logic;
import GUI.*;
import java.util.Queue;

class Pump extends Thread {
    private int id;
    private Queue<Car> carQueue;
    private Semaphore mutex;
    private Semaphore availableAreas;
    private Semaphore waitingCars;
    private Semaphore availablePumps;
    private SimulationView view;
    private volatile boolean running = true;

    public Pump(int id, Queue<Car> carQueue, Semaphore mutex,
                Semaphore availableAreas, Semaphore waitingCars,
                Semaphore availablePumps, SimulationView view) {
        this.id = id;
        this.carQueue = carQueue;
        this.mutex = mutex;
        this.availableAreas = availableAreas;
        this.waitingCars = waitingCars;
        this.availablePumps = availablePumps;
        this.view = view;
    }

    public void stopPump() {
        running = false;
        interrupt();
    }

    
    public void run() {
        while (running) {
            try {
                waitingCars.waitSemaphore(); 
                availablePumps.waitSemaphore(); 

                mutex.waitSemaphore();
                Car car = carQueue.poll();

                if (car != null) {
                    view.removeCarFromQueue("C" + car.getCarId());
                    System.out.println("Pump " + id + ": C" + car.getCarId() + " begins service.");
                    view.setPumpBusy(id, "C" + car.getCarId());
                }
                mutex.signal();
                availableAreas.signal();

                
                Thread.sleep((int) (Math.random() * 3000 + 2000));

                System.out.println("Pump " + id + ": C" + car.getCarId() + " finished.");
                view.setPumpFree(id);
                availablePumps.signal();

            } catch (InterruptedException e) {
                if (!running) break;
            }
        }
    }
}
