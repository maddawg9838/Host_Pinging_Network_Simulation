/*
Node class which stores the data in the node and the references
to the next node and the previous node for the doubly linked list
 */
public class Node {
    private final Event data;
    public Node prev;
    public Node next;

    public Node(Event e) {
        this.data = e;
        this.prev = null;
        this.next = null;
    }

    public Event getData() {
        return this.data;
    }
}