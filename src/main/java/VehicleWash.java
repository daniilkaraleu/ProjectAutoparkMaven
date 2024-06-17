import Classes.CollectionManager;
import Classes.Vehicle;

import static java.lang.Thread.sleep;

public class VehicleWash {
    private static final int SLEEP = 1000;
    CollectionManager collectionManager = new CollectionManager();
    public void startWashing() {
        VehiclesQueue queue = new VehiclesQueue();

        createQueue(queue);

        while (queue.size() != 0) {
            washVehicle(queue);

            try {
                sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void createQueue(VehiclesQueue queue) {
        for (Vehicle vehicle : collectionManager.getVehicleCollection().getList()) {
            queue.enqueue(vehicle);
        }
    }

    private void washVehicle(VehiclesQueue queue) {
        System.out.println(queue.dequeue().getModel() + " is Washing...");
    }
}

class VehiclesQueue {
    Vehicle[] vehicles = new Vehicle[20];
    int counter = 0;

    public void enqueue(Vehicle vehicle) {
        vehicles[counter] = vehicle;
        counter++;
    }

    public Vehicle dequeue() {
        Vehicle result = vehicles[0];

        for (int i = 0; i <= counter - 1; i++) {
            vehicles[i] = vehicles[i + 1];
        }
        counter--;

        return result;
    }

    public int size() {
        return counter;
    }

    public Vehicle peek() {
        return vehicles[1];
    }
}