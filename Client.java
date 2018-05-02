import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

class Client extends Thread {
	InetAddress host = null;
	Socket socket = null;
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	private String str;
  static int index = 1;
  ClientWindow cw;
	//ClientData data = new ClientData(2); 

	public void run() {
		try {

			host = InetAddress.getLocalHost();
			socket = new Socket(host.getHostName(), 4444);

		//	oos = new ObjectOutputStream(socket.getOutputStream());
		//	oos.writeObject(data);
			
			ois = new ObjectInputStream(socket.getInputStream());
			String message = (String) ois.readObject();
			System.out.println("Client Received: " + message);
			if(message.equals("Ok"));
				//ClientWindow.end(str);
		} catch (Exception e) {
		}
	}

	public void SendStopInStationToServer(String s)
	{
		try {
			str=s;
			oos = new ObjectOutputStream(socket.getOutputStream());
			ClientData cd = new ClientData(index++, s);
			oos.writeObject(cd);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connectToServer()
	{
		try {
			host = InetAddress.getLocalHost();
			socket = new Socket(host.getHostName(), 4444);
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(index++);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeClient() {
		try {
			ois.close();
			oos.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}