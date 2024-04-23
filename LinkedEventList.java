public class LinkedEventList implements FutureEventList {

    private Node head; // Head of the linked list
    private int size = 0; // Numbers of events in the linked list

    private int simulationTime = 0; // To track simulation time

    // Removes the first event in the linked list
    @Override
    public Event removeFirst() {

        // If list is empty
        if (head == null) {
            return null;
        }

        // Updates simulation time
        simulationTime = head.getData().getArrivalTime();
        Event temp = head.getData();

        // Checks head.next before setting the rest of the nodes
        // inorder to set the list properly after removing the first node
        if (head.next == null) {
            head = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }

        // Decreases the size and returns the temp
        this.size--;
        return temp;

    }

    // Removes a specific event from the linked list
    @Override
    public boolean remove(Event e) {
        // If the list empty
        if (size == 0) {
            return false;
        }

        // Traverses the list linearly to find the node to remove
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.getData().equals(e)) {

                // Checks the rest of the list before setting the rest of the nodes
                // in order to set the list properly after removing a specific node
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                else {
                    head = currentNode.next;
                }

                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }

                // Decreases size and returns true
                this.size--;
                return true;
            }

            currentNode = currentNode.next;
        }

        return false;
    }


    // Inserts an event into the linked list in a sorted order based on the arrival time
    @Override
    public void insert(Event e) {
        Node tempNode = new Node(e);
        e.setInsertionTime(simulationTime);

        // Inserting node at the beginning of the list
        if (head == null || tempNode.getData().getArrivalTime() < head.getData().getArrivalTime()) {
            if (head == null) {
                head = tempNode;
            }
            else {
                Node switchNode = head;
                head = tempNode;
                head.next = switchNode;
                switchNode.prev = head;
            }
        }
        // Traversing the list in order to maintain correct order based on arrival time
        else {
            Node currentNode = head;
            while (currentNode != null) {
                if (currentNode.getData().getArrivalTime() > tempNode.getData().getArrivalTime()) {
                    if (currentNode.prev != null) {
                        // Inserting the tempNode between currentNode.prev and currentNode
                        currentNode.prev.next = tempNode;
                        tempNode.prev = currentNode.prev;
                    }

                    tempNode.next = currentNode;
                    currentNode.prev = tempNode;
                    break;
                }
                if (currentNode.next == null) {
                    // Inserting tempNode at the end of the list
                    currentNode.next = tempNode;
                    tempNode.prev = currentNode;
                    break;
                }
                currentNode = currentNode.next;
            }
        }
        this.size++;
    }

    // Returns the size of the linked list
    // (number of events in the linked list)
    @Override
    public int size() {
        return this.size;
    }

    // Returns the capacity of the linked list, which is also the size
    @Override
    public int capacity() {
        return this.size;
    }

    // Returns the simulation time based
    // on the arrival time of the last event
    @Override
    public int getSimulationTime() {
        return simulationTime;
    }
}