import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSystem {
    // 1. Обмежена місткість парковки (Semaphore контролює кількість вільних місць)
    private static final Semaphore parkingSpots = new Semaphore(5);
    private static final ReentrantLock logLock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            new Thread(new Car(i)).start();
        }
    }

    static class Car implements Runnable {
        private final int carId;

        public Car(int carId) {
            this.carId = carId;
        }

        @Override
        public void run() {
            try {
                log("прибув на парковку.");

                // Очікування вільного місця (Semaphore)
                parkingSpots.acquire();

                log("заїхав на парковку.");

                // Імітація перебування на парковці
                Thread.sleep((long) (Math.random() * 5000));

                log("виїхав з парковки.");

                // Звільнення місця
                parkingSpots.release();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void log(String message) {
            logLock.lock();
            try {
                System.out.println("Авто #" + carId + " " + message);
            } finally {
                logLock.unlock();
            }
        }
    }
}