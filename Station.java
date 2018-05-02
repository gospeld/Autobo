import java.util.Observable;

public class Station extends Observable {
	
	static String[] names= {"station_1","station_2","sation_3","station_4","station_5","station_6","station_7","station_8"};
	static int[] times_out= {10,9,7,6,7,8,6,0};
	static int[] times_enter= {0,4,6,5,4,4,5,7};
	static int index = 0;

	String name;
	int time_enter,time_outside , time_until;
	
	boolean pass=false;
	TabTime tab;
			
	public Station() {
		this.time_until=getPredictionTime();
		this.name=names[index];
		this.time_outside=times_out[index];
		this.time_enter=times_enter[index++];
	}	
	
	private int getPredictionTime() {
		int sum=0;
		for (int i = 0; i < index-1; i++) {
			sum+=times_out[i];
		}
		return sum;
	}
	public static int getIndex(String s) {
		for (int i = 0; i < names.length; i++) {
			if(names[i]==s) {
				return i;
			}
		}
		System.err.println("not found");
		return 1;
	}

	@Override
	public String toString() {
		if(pass==true) {
			return"{"+name+" , passing }";
		}
		else
		return "{"+name+" ,not passing }";
	}

}
