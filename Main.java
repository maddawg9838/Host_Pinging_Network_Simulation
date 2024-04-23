import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Open a scanner that is able to read the text file
        FileInputStream fileByteStream = new FileInputStream("simulation.txt");
        Scanner scnr = new Scanner(fileByteStream);

        LinkedEventList doublyList = new LinkedEventList();

        int srcAddress, destAddress, hostAddress, neighborDistance, intervalTime, totalDuration;

        SimpleHost[] addresses = new SimpleHost[10];
        boolean flag = false;

        // Reading contents of the text file
        hostAddress = scnr.nextInt();
        SimpleHost firstHost = new SimpleHost();
        firstHost.setHostParameters(hostAddress, doublyList);

        addresses[hostAddress] = firstHost;

        // Adding the neighbors of the original host based on the data from the file
        while(!flag) {
            int neighborAddress = scnr.nextInt();
            if (neighborAddress == -1) {
                flag = true;
            }
            else {
                // Sets the parameters of the neighbors and adds the
                // neighbor and firstHost as neighbors to each other
                SimpleHost neighbor = new SimpleHost();
                neighbor.setHostParameters(neighborAddress, doublyList);

                neighborDistance = scnr.nextInt();
                addresses[neighborAddress] = neighbor;
                addresses[hostAddress].addNeighbor(neighbor, neighborDistance);
                addresses[neighborAddress].addNeighbor(firstHost, neighborDistance);
            }
        }

        while(scnr.hasNextInt()) {
            // Reads each line in the rest of the file in order to boostrap the simulation
            srcAddress = scnr.nextInt();
            destAddress = scnr.nextInt();
            intervalTime = scnr.nextInt();
            totalDuration = scnr.nextInt();

            // Uses method sendPings to create the initial timers to bootstrap simulation
            addresses[srcAddress].sendPings(destAddress, intervalTime, totalDuration);
        }

        // Handles all the events in the LinkedEventList
        while (doublyList.size() > 0) {
            doublyList.removeFirst().handle();
        }
    }
}
