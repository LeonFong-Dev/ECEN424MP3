
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/** Karthik: Print each received character immediately. */
public class NaiveClient {
    public static void main(String[] args) {
        // The client needs the server IP address and port number.
        if (args.length != 2) {
            System.out.println("Usage: java NaiveClient <server_ip> <server_port>");
            return;
        }

        String serverIp = args[0];
        int serverPort;

        // Make sure the port is a valid number.
        try {
            serverPort = Integer.parseInt(args[1]);
            if (serverPort < 1 || serverPort > 65535) {
                System.out.println("Error: server_port must be between 1 and 65535.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: server_port must be a valid integer.");
            return;
        }

        // Connect using TCP and close the socket and reader automatically.
        try (Socket socket = new Socket(serverIp, serverPort);
             InputStreamReader input = new InputStreamReader(
                     socket.getInputStream(), StandardCharsets.UTF_8)) {

            int character;

            // Read and display one character at a time as it arrives.
            while ((character = input.read()) != -1) {
                System.out.print((char) character);
                System.out.flush(); // Do not wait for more characters to print.

                // The newline means the server finished its last send.
                if (character == '\n') {
                    return;
                }
            }

            System.err.println("Connection closed before a newline was received.");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
