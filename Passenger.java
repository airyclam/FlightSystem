/**
 * This class represents a passenger with a name, email, and trips taken. 
 * @author lameric
 *
 */
public class Passenger {
	private String name;
	private String email;
	private int tripCount;
	public int passengerID = hashCode();
	
	public Passenger() {
  		this.tripCount = 0;
	}
	/**
	 * @param newName Name of passenger
	 * @param newEmail Email of passenger
	 * @param tripsTaken Trips taken by passenger
	 */
	public Passenger(String newName, String newEmail, int tripsTaken) {
		name = newName;
		email = newEmail;
		tripCount = tripsTaken;
	}
	public String getName() {
		return name;
	}
	public void changeName(String newName) {
		name = newName;
	}
	public String getEmail() {
		return email;
	}
	public void changeEmail(String newEmail) {
		email = newEmail;
	}
	public int getTrips() {
		return tripCount;
	}
	public void changeTrips(int tripsTaken) {
		tripCount = tripsTaken;
	}
	public int getID() {
		return passengerID;
	}
}
