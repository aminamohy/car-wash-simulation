// package logic;
// import GUI.*;
// import java.util.LinkedList;
// import java.util.Queue;
// public class ServiceStation {
  
//     public Queue<Car> carQueue;
//     public int size;
//     public Semaphore mutex;
//     public Semaphore availableAreas;
//     public Semaphore waitingCars;
//     public Semaphore availablePumps; 
//     private final int numPumps;
//     private Pump[] pumps;
//     private SimulationView view;

//     public ServiceStation(int numPumps, int queueSize,SimulationView view) {
//         if (numPumps < 1 || numPumps > 10) {
//             System.out.println("Error: Number of pumps must be between 1 and 10. Using default: 3");
//             numPumps = 3;
//         }
//         if (queueSize < 1 || queueSize > 10) {
//             System.out.println("Error: Queue size must be between 1 and 10. Using default: 5");
//             queueSize = 5;
//         }
//         this.view=view;

//         this.numPumps = numPumps;
//         size = queueSize;
//         carQueue = new LinkedList<>();
//         mutex = new Semaphore(1);
//         availableAreas = new Semaphore(queueSize);
//         waitingCars = new Semaphore(0);
//         availablePumps = new Semaphore(numPumps);
//         pumps = new Pump[numPumps];
        
//         System.out.println("Service Station initialized with " + numPumps + " pumps and queue size " + queueSize);
//     }

//     public ServiceStation(int numPumps) {
//         this.numPumps = numPumps;
//     }

//     public void startSimulation(int numCars) {
//         System.out.println("Simulation starting...");
        

//         for (int i = 0; i < numPumps; i++) {
//             pumps[i] = new Pump(i + 1, carQueue, mutex, availableAreas, waitingCars, availablePumps,view);
//             pumps[i].start();
//         }
        

//         int carId = 1;
//         for (int i = 0; i < numCars; i++) {
//             Car car = new Car(carId++, carQueue, availableAreas, waitingCars, mutex,view);
//             car.start();
//             try {
//                 Thread.sleep(1000);

//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//             }
//         }

        
//         waitForCompletion(numCars);


//         stopAllPumps();

//         System.out.println(" All cars processed; simulation ends");
//     }


//     private void waitForCompletion(int totalCars) {
//         while (true) {
//             try {
//                 Thread.sleep(1000);
//                 mutex.waitSemaphore();
//                 boolean queueEmpty = carQueue.isEmpty();
//                 mutex.signal();

//                 boolean allPumpsAvailable = (availablePumps.availablePermits() == numPumps);

//                 if (queueEmpty && allPumpsAvailable) {
//                     break;
//                 }
//             } catch (InterruptedException e) {
//                 Thread.currentThread().interrupt();
//                 break;
//             }
//         }
//     }


//     private void stopAllPumps() {
//         for (Pump pump : pumps) {
//             pump.stopPump();
//         }
//     }
// }


package logic;

import GUI.*;
import java.util.*;

public class ServiceStation {
    public Queue<Car> carQueue;
    public Semaphore mutex;
    public Semaphore availableAreas;
    public Semaphore waitingCars;
    public Semaphore availablePumps;
    private Pump[] pumps;
    private SimulationView view;

    public ServiceStation(int numPumps, int queueSize, SimulationView view) {
        this.view = view;
        carQueue = new LinkedList<>();

        mutex = new Semaphore(1);
        availableAreas = new Semaphore(queueSize);
        waitingCars = new Semaphore(0);
        availablePumps = new Semaphore(numPumps);

        pumps = new Pump[numPumps];
        for (int i = 0; i < numPumps; i++) {
            pumps[i] = new Pump(i + 1, carQueue, mutex, availableAreas, waitingCars, availablePumps, view);
        }
    }

    public void startSimulation(int numCars) {
        for (Pump pump : pumps) pump.start();

        for (int i = 1; i <= numCars; i++) {
            Car car = new Car(i, carQueue, mutex, availableAreas, waitingCars, view);
            car.start();
            try {
                Thread.sleep(1000 + (int)(Math.random() * 1500));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
