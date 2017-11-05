import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient{
    private String host;
    private int port;
    private DataOutputStream outToServer;
    private
    Socket clientSocket;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            clientSocket = new Socket(TCPClient.this.host, TCPClient.this.port);
            outToServer = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            outToServer.writeBytes(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
