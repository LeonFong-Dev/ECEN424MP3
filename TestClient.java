import java.net.Socket;
import java.io.InputStream;

public class TestClient {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 5000);
        InputStream input = socket.getInputStream();

        int data;
        while ((data = input.read()) != -1) {
            System.out.print((char) data);
        }

        socket.close();
    }
}