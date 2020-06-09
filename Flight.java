/**
 * 
 * @author lameric
 *
 */
public class Flight {
	private EricsLinkedList<Passenger> queue = new EricsLinkedList<Passenger>();
	private String origin;
	private String destination;
	public int flightID = hashCode();
	
	public Flight(String newOrigin, String newDestination) {
		origin = newOrigin;
		destination = newDestination;
	}
	public void add(Passenger passenger) {
		queue.add(passenger);
	}
	public void remove(Passenger passenger) {
		queue.remove(passenger);
	}
	public String getOrigin() {
		return origin;
	}
	public String getDestination() {
		return destination;
	}
	public int getID() {
		return flightID;
	}
	public int output(int index) {
	return queue.get(index).getID();
	}
	public int getSize() {
		return queue.size();
	}
	public int getWait() {
		return queue.size()-4;
	}
	
}
