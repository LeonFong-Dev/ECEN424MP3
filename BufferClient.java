
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/** Karthik: Buffer characters and print only after a newline. */
public class BufferClient {
    public static void main(String[] args) {
        // The client needs the server IP address and port number.
        if (args.length != 2) {
            System.out.println("Usage: java BufferClient <server_ip> <server_port>");
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

            StringBuilder buffer = new StringBuilder();
            int character;

            // Save every character without printing it immediately.
            while ((character = input.read()) != -1) {
                buffer.append((char) character);

                // Show the entire message only after its final newline.
                if (character == '\n') {
                    System.out.print(buffer.toString());
                    System.out.flush();
                    return;
                }
            }

            System.err.println("Connection closed before a newline was received.");

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
