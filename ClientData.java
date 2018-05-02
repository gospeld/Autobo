import java.io.Serializable;

public class ClientData implements Serializable  {
	
	int id;
	String dest;
	public ClientData(int id,String str)
	{
		this.id = id;
		this.dest=str;
	}
}
