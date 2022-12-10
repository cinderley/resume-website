
public class Player {
	String level;
	String name;
	int time;
	
	public Player(String l, String n, int t) {
		level = l;
		name = n;	
		time = t;	
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return level + "   " + time + " seconds   " + name;
	}
}
