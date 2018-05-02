
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class Server  extends Thread{
	static Bus bus = new Bus();
	static HashMap<Integer, Socket> h =  new HashMap<Integer,Socket>();
	static Socket socket = null ;
	static ObjectInputStream ois = null;
	static ObjectOutputStream oos = null;

	public  void run(){
		System.out.println("Server start");
		try {
			ServerSocket server = new ServerSocket(4444);
			while(true)
			{
				socket = server.accept();
				ServerListenerThread l= new ServerListenerThread(socket, h);
				l.start();



			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void exitServer() {
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