import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class ServerListenerThread extends Thread {

	private Socket socket;
	private ObjectInputStream ois;
	private HashMap<Integer, Socket> h;
	private ObjectOutputStream oos;

	public ServerListenerThread(Socket socket, HashMap<Integer, Socket> h) {
		this.h = h;
		this.socket = socket;
	}

	public void run() {
		try {
			

			while (true) 
			{
				ois = new ObjectInputStream(socket.getInputStream());
				Object obj = ois.readObject();

				ClientData dataFromClient = null;

				if (obj instanceof ClientData) {
				ClientData	cd= (ClientData) obj;
					h.put(new Integer(cd.id), socket);
					System.out.println("Server Received: " + cd.id);
					Bus.OrderStation((String)cd.dest);

				} else if (obj instanceof String) {
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		
		 try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject("Server Reply");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
