public class Message extends Event {
    public String message;
    public int srcAddress;
    public int destAddress;

    Host destHost;
    int distance;

    public Message(String type, int srcAddress, int destAddress) {
        this.destAddress = destAddress;
        this.srcAddress = srcAddress;
        this.message = type;
    }

    @Override
    public void setInsertionTime(int currentTime) {
        this.insertionTime = currentTime;
        this.arrivalTime = currentTime + this.distance;

    }

    @Override
    public void cancel() {
        System.out.println("Error: Message cancelled");
    }

    @Override
    public void handle() {
        this.destHost.receive(this);
    }

    public String getMessage() {
        return this.message;
    }

    public int getSrcAddress() {
        return this.srcAddress;
    }

    public int getDestAddress() {
        return this.destAddress;
    }

    // Switches the source and the destination host addresses,
    // so you can send to a neighbor of a neighbor of the main host
    public void setNextHop(Host destHost, int distance) {
        this.destHost = destHost;
        this.distance = distance;
    }
}
