import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerThread {
	public static final int SERVICE_PORT = 6543;
	// Max size of packet, large enough for almost any client
	public static final int BUFSIZE = 4096;

	public String sentence_from_client;
    public String sentence_to_client;
	private ServerSocket socket;
	public EchoServerThread(){
		try {
			socket = new ServerSocket(SERVICE_PORT);
			System.out.println("Server active on port " + socket.getLocalPort());
		} catch (IOException e) {
			System.err.print("Unable to bind port");
		}
	}
	
	public void serviceClients() {
		while (true) {
            
			try {
				Socket connectionSocket = socket.accept();
				System.out.println("New client connected");

	            new ServerThread(connectionSocket).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }

	}
	public static void main(String[] args) {
		EchoServerThread server = new EchoServerThread();
		server.serviceClients();
	}
}
