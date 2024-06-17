import Classes.Vehicle;

public class VehicleGarage {
    private static final int GARAGE_SIZE = 3;
    private VehicleStack stack = new VehicleStack();

    public void pushVehicle(Vehicle vehicle) {
        if (stack.size() < GARAGE_SIZE) {
            stack.push(vehicle);
            System.out.println(vehicle.getModel() + " is on repair");
        } else {
            System.out.println("Garage is full!");
        }

    }

    public void removeVehicle() {

        System.out.println(stack.pop() + " was removed!");
    }
}

class VehicleStack {
    static class Node {
        private Node previous;
        private final Vehicle data;

        Node(Vehicle data) {
            this.data = data;
        }
    }

    private Node lastNode;
    private Node firstNode;
    private int sizeOfStack;

    public void push(Vehicle data) {
        Node tempNode;
        Node node = new Node(data);

        if (firstNode == null) {
            firstNode = node;
        } else {
            tempNode = firstNode;
            firstNode = node;
            firstNode.previous = tempNode;
        }
        sizeOfStack++;
    }

    public Vehicle peek() throws IllegalArgumentException {
        if (sizeOfStack == 0) {
            throw new IllegalArgumentException("No cars in the Garage!");
        }
        return firstNode.data;
    }

    public Vehicle pop() throws IllegalArgumentException {
        if (sizeOfStack == 0) {
            throw new IllegalArgumentException("No cars in the Garage!");
        }

        Vehicle vehicle = firstNode.data;

        firstNode = firstNode.previous;

        sizeOfStack--;
        return vehicle;
    }

    public int size() {
        return sizeOfStack;
    }
}