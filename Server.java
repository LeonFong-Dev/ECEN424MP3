import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Leon: Server implementation.
 * Usage: java Server <server_port> <max_clients>
 */
public class Server {

    public static void main(String[] args) {
        //Check if there are two command-line arguments provided
        if (args.length != 2) {
            System.out.println("Usage: java Server <server_port> <max_clients>");
            return;
        }

        int serverPort;
        int maxClients;
        //Validate and parse the server port
        try {
            serverPort = Integer.parseInt(args[0]);
            if (serverPort < 1 || serverPort > 65535) {
                System.out.println("Error: server_port must be a positive integer between 1 and 65535.");
                return;
            }
            maxClients = Integer.parseInt(args[1]);
            if (maxClients <= 0) {
                System.out.println("Error: max_clients must be a positive integer.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: server_port and max_clients must be valid integers.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter a String:");
            String str = scanner.nextLine();
            System.out.println("Enter a positive integer:");
            int n = 0;
            if(scanner.hasNextInt()){
                n = scanner.nextInt();
            }
            

            if (n <= 0) {
                System.out.println("Error: The integer must be positive.");
                return;
            }
            
            AtomicInteger activeClients = new AtomicInteger(0);

            // Create a ServerSocket bound to serverPort.
            try(ServerSocket server = new ServerSocket(serverPort)){
                //Wait for the client to connect
                System.out.println("Server listening on port " + serverPort);
                
                while(true){

                    Socket client = server.accept();
                    System.out.println("Client Connected!");

                    // Check maximum number of active clients
                    if(activeClients.get() >= maxClients){
                        System.out.println("Server full. Rejecting client.");
                        client.close();
                        continue;
                    }

                    //Increase active client count
                    activeClients.incrementAndGet();

                    //Create a handler for the connected client
                    ClientHandler handler = new ClientHandler(client,str,n);


                    // Start a separate worker thread
                    Thread worker = new Thread(() -> {
                        try {
                            handler.run();
                        } finally {
                            // Decrease count when client finishes
                            activeClients.decrementAndGet();
                        }
                    });

                    worker.start();
                }
                
            }
            catch (IOException e) {
                System.out.println("Server error: " + e.getMessage());
                return;
            }
        }
    }
}
