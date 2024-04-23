public class SimpleHost extends Host{
    private int previousCurrentTime;
    private int intervalTimerID;
    private int durationTimerID;
    private int destAddr;
    private int interval;

    @Override
    protected void receive(Message msg) {
        // If the message is type REQUEST, this method outputs and creates a RESPONSE type Message
        if("REQUEST".equals(msg.getMessage())) {
            System.out.println("[" + getCurrentTime() + "ts] Host " + msg.getDestAddress() + ": Ping request from host " + msg.getSrcAddress());

            Message response = new Message("RESPONSE", msg.getDestAddress(), msg.getSrcAddress());
            sendToNeighbor(response);

        }
        // If the message is type RESPONSE, this method outputs and calculates the rtt based on the currentTime
        else if("RESPONSE".equals(msg.getMessage())) {
            int rtt;
            rtt = getCurrentTime() - previousCurrentTime;
            System.out.println("[" + getCurrentTime() + "ts] Host " + msg.getDestAddress() + ": Ping response from host " + msg.getSrcAddress() + " (RTT = " + rtt + "ts)");
        }
    }

    @Override
    protected void timerExpired(int eventId) {
        // If interval timer expires, then create new interval timer and REQUEST message, and output
        if (intervalTimerID == eventId) {
            this.intervalTimerID = newTimer(interval);
            this.previousCurrentTime = getCurrentTime();

            Message request = new Message("REQUEST", getHostAddress(), destAddr);
            sendToNeighbor(request);

            System.out.println("[" + getCurrentTime() + "ts] Host " + getHostAddress() + ": Sent ping to host " + destAddr);
        }
        // If duration timer expires, then cancel the interval timer and output
        else if (durationTimerID == eventId) {
            System.out.println("[" + getCurrentTime() + "ts] Host " + getHostAddress() + ": Stopped sending pings");
            cancelTimer(intervalTimerID);
        }
    }

    // Method for canceling any timers
    @Override
    protected void timerCancelled(int eventId) {
        cancelTimer(eventId);
    }

    // Create the two initial timers for each line read in the file and sets the interval and destAddr
    public void sendPings(int destAddr, int interval, int duration) {
        this.interval = interval;
        this.destAddr = destAddr;

        this.intervalTimerID = newTimer(interval);
        this.durationTimerID = newTimer(duration);
    }
}
