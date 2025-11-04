package logic;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

    class Pump extends Thread {
    private int id;
    private Queue<Car> carQueue;
    private Semaphore mutex;
    private Semaphore availableAreas;
    private Semaphore waitingCars;
    private Semaphore availablePumps;
    private volatile boolean running = true;
    public Pump(int id, Queue<Car> carQueue, Semaphore mutex, Semaphore availableAreas, Semaphore waitingCars, Semaphore availablePumps) {
        this.id = id;
        this.carQueue = carQueue;
        this.mutex = mutex;
        this.availableAreas = availableAreas;
        this.waitingCars = waitingCars;
        this.availablePumps = availablePumps;
    }

    public void stopPump() {
        running = false;
        this.interrupt();
    }
    
    public void run() {
        while (running) {
            try {
                waitingCars.waitSemaphore();
                availablePumps.waitSemaphore();
                mutex.waitSemaphore();

                Car car = carQueue.poll();

                if (car == null) {
                    mutex.signal();
                    availablePumps.signal();
                    continue;
                }

                System.out.println("Pump " + id + ": C" + car.getCarId() + " Occupied");
                System.out.println("Pump " + id + ": C" + car.getCarId() + " login");
                System.out.println("Pump " + id + ": C" + car.getCarId() + " begins service at Bay " + id);
                
                mutex.signal();
                availableAreas.signal();
                int serviceTime = (int)(Math.random() * 3000 + 2000);
                Thread.sleep(serviceTime);

                System.out.println("Pump " + id + ": C" + car.getCarId() + " finishes service");
                System.out.println("Pump " + id + ": Bay " + id + " is now free");
                availablePumps.signal();
                
            } catch (InterruptedException e) {
                if (!running) break;
            }
        }
    }
}

