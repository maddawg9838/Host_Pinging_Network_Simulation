# Network Traffic Simulator

## Overview
This Java program simulates a simple network environment that manages single-hop (neighbor-to-neighbor) network traffic. It utilizes a doubly-linked list to handle future events and allows hosts to send and receive messages in a star topology. The program reads parameters from a configuration file (simulation.txt) to set up hosts, neighbors, and network events, enabling the simulation of network message exchanges.

## Features
- **Linked Event List**: Implements a linked-list version of the FutureEventList using a doubly-linked list for efficient event management.
- **Network Message Events**: Simulates sending and receiving network messages, including ping requests and responses.
- **Custom Exception Handling**: Includes a custom exception class for managing event-related exceptions.
- **Dynamic Host Management**: Capable of adding and managing neighbors dynamically based on the input configuration.
- **Simulation Control**: The simulation processes events in the order they are scheduled, accurately mimicking real-world network interactions.

## Prerequisites
- Java Development Kit (JDK)
- Basic understanding of linked lists and exception handling in java

## How to Run
1. Ensure you have a file named simulation.txt in the project directory with the correct format.
2. Compile and run the Main class to initiate the network simulation.
3. Follow the prompts and view the simulation results as the network traffic is processed.

## File Format
The simulation.txt file should contain:
- The first line: The initial host address.
- Subsequent lines: Pairs of neighbor addresses and distances until a -1 is encountered.
- The remaining lines should contain source address, destination address, interval time, and total duration for sending pings.

## Code Overview
FutureEventList
- Implemented as LinkedEventList, a doubly-linked list with nodes.
- Uses linear search for both insert and remove operations.
- Provides a method to return the current size of the list.

Event Handling
- The Event class is now abstract, with a new Message class representing network messages.
- The Message class includes methods to retrieve message details, such as source and destination addresses.
- Hosts communicate through a custom sendPings method, which schedules ping requests.

Host Management
- A base abstract class Host is provided, and a child class SimpleHost implements its methods.
- The simulation initiates with a host that reads from simulation.txt to configure neighbors and events.

## Author
Madison Humphries
