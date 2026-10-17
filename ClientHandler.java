
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Handles communication with one connected client.
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private final String str;
    private final int n;
    private final Runnable onFinished;

    // Constructor without a completion callback
    public ClientHandler(Socket socket, String message, int repetitions) {
        this(socket, message, repetitions, null);
    }

    // Constructor with a completion callback
    public ClientHandler(Socket socket, String message,
                         int repetitions, Runnable onFinished) {
        this.socket = socket;
        this.str = message;
        this.n = repetitions;
        this.onFinished = onFinished;
    }

    @Override
    public void run() {
        try (Socket client = socket) {
            OutputStream output = client.getOutputStream();

            // Send the message N times
            for (int i = 0; i < n; i++) {
                String data = str;

                // Only the final transmission includes a newline
                if (i == n - 1) {
                    data += "\n";
                }

                // Convert the string to bytes and send
                byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
                output.write(bytes);
                output.flush();

                // Wait 1 second only between transmissions
                if (i < n - 1) {
                    Thread.sleep(1000);
                }
            }

            System.out.println("Finished serving client: "
                    + client.getRemoteSocketAddress());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Client handler interrupted.");

        } catch (IOException e) {
            System.err.println("Client communication failed: "
                    + e.getMessage());

        } finally {
            // Tell the server this client is no longer active
            if (onFinished != null) {
                onFinished.run();
            }
        }
    }
}
