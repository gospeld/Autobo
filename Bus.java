import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;



public class Bus implements Runnable {
	static int TIME=0;
	static Vector<Station> path;
	int id;
	
	Station loc;
	
	public Bus() {
		
		this.path=new Vector<>();
		InitStation();
	    
	}
	
	public void InitStation() {
		for (int i = 0; i < Station.names.length; i++) {
			Station sta = new Station();
			path.add(sta);
			TIME+=sta.time_outside;
		}
		path.elementAt(0).pass=true;
	    path.elementAt(path.size()-1).pass=true;
	    //System.out.println(path.elementAt(path.size()-1)+","+path.elementAt(path.size()-1).pass);
	}
	

	@Override
	public void run() {
		
		busStart();
		
		
	}

	private void busStart() {
		loc=path.elementAt(0);
		while(loc!=path.elementAt(path.size()-1)) {
			System.out.println("bus go from "+loc.name+" to "+getNext().name+" driving for "+loc.time_outside+" minutes");
			try {
				Thread.sleep(loc.time_outside);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(getNext().pass==true) {
				try {
					System.out.println("the bus entered in the station named "+getNext());
					TIME+=loc.time_enter;
					Thread.sleep(loc.time_enter);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TIME-=loc.time_enter;
			}
			TIME-=loc.time_outside;
			System.out.println("fin: remain "+TIME+" minutes");
			loc.pass=false;
			loc=getNext();
		}
	}
	
	public Station  getNext(Station station ) {
		
		return path.get(path.indexOf(station)+1);
	}
	public Station  getNext() {
		return path.get(path.indexOf(loc)+1);
	}
	public static void OrderStation(String obj) {
		System.out.println("le choix est "+obj);
		int k = Bus.getIndex((String)obj);
		
		Station s=Bus.path.elementAt(k);
		System.out.println("a user ordered a bus at the station"+s);
		Bus.path.get(Bus.path.indexOf(s)).pass=true;
		System.out.println(Bus.path);
	}
	public static int getIndex(String s) {
		for (int i = 1; i < path.size(); i++) {
			if(path.get(i).name.equals(s)) {
				//System.out.println("["+path.get(i).name+"],["+s+"]");
				return i;
			}
		}
		System.err.println("not found");
		return 1;
	}
	
	public static int getTime(int station) {
		int sum=0;
		for (int i = 1; i < station; i++) {
			Station sta = path.elementAt(i);
			sum+=sta.time_outside;
			if(sta.pass) {
				sum+=sta.time_enter;
			}
		}
		return sum;
	}

	
	
	
}
