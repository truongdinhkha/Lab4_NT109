import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;
	 
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
    public String sentence_from_client;
    public String sentence_to_client;
    public void run() {
        try {
        	do {
        	BufferedReader inFromClient =
	                new BufferedReader(new
	                    InputStreamReader(socket.getInputStream())); 
			PrintWriter outToClient =
	                new PrintWriter(socket.getOutputStream(),true);
			sentence_from_client = inFromClient.readLine();
			System.out.println("ID Thread: "+ getId() +", From client: "+ sentence_from_client);
            sentence_to_client = sentence_from_client +" (Server accepted!)" + '\n';
            outToClient.println(sentence_to_client); 
			} while (!sentence_from_client.equals("bye"));
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
