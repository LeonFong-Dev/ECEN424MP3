import java.io.IOException;
import java.net.Socket;

/** Karthik: Buffer characters and print only after a newline. */
public class BufferClient {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java BufferClient <server_ip> <server_port>");
            return;
        }

        // TODO (Karthik): Parse server IP and port.
        // TODO (Karthik): Open a Socket to the server.
        // TODO (Karthik): Collect incoming characters in a StringBuilder.
        // TODO (Karthik): Print the full collected message ONLY once newline arrives.
        // TODO (Karthik): Terminate after printing the newline.
        // TODO (Karthik): Close resources; handle connection errors.
    }
}
