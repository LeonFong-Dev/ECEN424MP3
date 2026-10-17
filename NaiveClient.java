import java.io.IOException;
import java.net.Socket;

/** Karthik: Print each received character immediately. */
public class NaiveClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java NaiveClient <server_ip> <server_port>");
            return;
        }

        // TODO (Karthik): Parse server IP and port.
        // TODO (Karthik): Open a Socket to the server.
        // TODO (Karthik): Read incoming characters one by one.
        // TODO (Karthik): Print each character immediately (do not wait for a newline).
        // TODO (Karthik): Stop AFTER receiving and printing the newline.
        // TODO (Karthik): Close resources; handle connection errors.
    }
}
