import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;
/**
 * This project is a travel agency's flight reservation system. Customers can call the travel agency to book 
 * their flights, and the travel agent will use this system to record the reservation.
 * 
 * @author lameric 05/15/2020
 */
public class Main {
	static Scanner sc;
	static boolean exit = false;	
	static ArrayList<Passenger> list = new ArrayList<Passenger>();
	static Flight flight[] = new Flight[6];
	static String[] origin = new String [6];
	static String[] destination = new String [6];
	static Map<Integer, Flight> flightMap  = new TreeMap<>();
	static Map<Integer, Passenger> passengerMap = new TreeMap<>();
	/** 
	 * This method prints out the menu options and returns the user's selection
	 * @return returns the user's selection
	 */
	public static int menu() {
		System.out.println("MENU");
		System.out.println("0. Create a new passenger");
		System.out.println("1. Edit information for an existing passenger");
		System.out.println("2. Display all passenger information based on name");
		System.out.println("3. Display all passenger information based on number of segments flown");
		System.out.println("4. Book a reservation");
		System.out.println("5. Cancel a reservation");
		System.out.println("6. Display all flights with flight details");
		System.out.print("Menu Choice: ");
		int selection = sc.nextInt();
		return selection;
	}
	/**
	 * This method creates a passenger by prompting the user to enter a name and email
	 */
	public static void createPassenger() {
		System.out.print("Enter name of passenger: ");
		String name = sc.next();
		System.out.print("Enter passenger email: ");
		String email = sc.next();
		list.add(new Passenger(name, email, 0)); // Adds new passenger to list
		passengerMap.put(list.get(list.size()-1).getID(), list.get(list.size()-1)); // Adds new passenger to map
		System.out.print("Press 0 to exit. Press 1 to add another account: ");
		int restart = sc.nextInt();
		if(restart == 1) {
			createPassenger();
		}
	}
	/**
	 * This method edits an account by prompting the user to select an account from the arrayList. 
	 */
	public static void editPassenger() {
		for(int i=0; i < list.size(); i++) { // Prints out all passenger information
			System.out.println("Name: " + list.get(i).getName() + " Passenger ID: " + list.get(i).getID() +
					" Email: " + list.get(i).getEmail() + " Trips Taken:" + list.get(i).getTrips());
		}
		System.out.print("Enter Passenger ID of account to edit: ");
		int delete = sc.nextInt();
		System.out.print("Enter name of passenger: ");
		String name = sc.next();
		list.get(delete).changeName(name);
		System.out.print("Enter passenger email: ");
		String email = sc.next();
		list.get(delete).changeEmail(email);
		System.out.print("Enter trips taken: ");
		int tripsTaken = sc.nextInt();
		passengerMap.get(delete).changeTrips(tripsTaken);
	}
	/**
	 * This method prints all passengers by alphabetical order
	 */
	public static void alphaPassenger() {
		List<Passenger> sortedList = list.stream() // Sorts the list by name then trips.
                .sorted(Comparator.comparing(Passenger::getName).thenComparing(Passenger::getTrips))
                .collect(Collectors.toList());
		for(int i=0; i < sortedList.size(); i++) {
			System.out.println("Name: " + sortedList.get(i).getName() + " Passenger ID: " + sortedList.get(i).getID() +
					" Email: " + sortedList.get(i).getEmail() + " Trips Taken:" + sortedList.get(i).getTrips());
		}
	}
	/**
	 * This method prints all passengers by trips taken
	 */
	public static void priorityPassenger() {
		List<Passenger> sortedList = list.stream() // Sorts the list by trips then name.
                .sorted(Comparator.comparing(Passenger::getTrips).thenComparing(Passenger::getName))
                .collect(Collectors.toList());
		for(int i=0; i < sortedList.size(); i++) {
			System.out.println("Name: " + sortedList.get(i).getName() + " Passenger ID: " + sortedList.get(i).getID() +
					" Email: " + sortedList.get(i).getEmail() + " Trips Taken: " + sortedList.get(i).getTrips());
		}
	}
	/**
	 * This method makes a reservation
	 */
	public static void makeReservation() {
		for (int i = 0; i < 6; i++) { // Prints out all flight information
			System.out.println("Flight Number: " +  flight[i].getID() + " From: " + flight[i].getOrigin() + " To: "  + flight[i].getDestination()
					+ " Current Reservations: " + flight[i].getSize());
		}
		System.out.print("Enter Flight number: ");
		int flightNumber = sc.nextInt();
		alphaPassenger(); // Prints all passengers in alphabetical order
		System.out.print("Enter Passenger ID:  ");
		int passengerID = sc.nextInt();
		if (flightMap.get(flightNumber).getSize()>4) {
			System.out.println("This flight is currently fully booked! You are number " + flightMap.get(flightNumber).getWait() + " on the waitlist." );
		}
		flightMap.get(flightNumber).add(passengerMap.get(passengerID));
	}
	/**
	 * This method cancels a reservation
	 */
	public static void cancelReservation() {
		for (int i = 0; i < 6; i++) { // Prints out all flight information
			System.out.println("Flight Number: " +  flight[i].getID() + " From: " + flight[i].getOrigin() + " To: "  + flight[i].getDestination()
					+ " Current Reservations: " + flight[i].getSize());
		}
		System.out.print("Enter Flight number: "); 
		int flightNumber = sc.nextInt(); 
		for(int i = 0; i < flightMap.get(flightNumber).getSize(); i++) { // Prints out Passenger List and Waitlist
			if (i<5) {
				System.out.println("Passenger"+ Integer.sum(i,1) + ". " + flightMap.get(flightNumber).output(i));
			}
			else System.out.println("Waitlist"+ Integer.sum(i,-4) + ". " + flightMap.get(flightNumber).output(i));
		}
		System.out.print("Enter Passenger ID to Remove:  "); 
		int passengerID = sc.nextInt();
		flightMap.get(flightNumber).remove(passengerMap.get(passengerID));
	}
	public static void printFlights() {
		for (int i = 0; i < 6; i++) { // Prints out all flight information
			System.out.println("Flight Number: " +  flight[i].getID() + " From: " + flight[i].getOrigin() + " To: "  + flight[i].getDestination()
					+ " Current Reservations: " + flight[i].getSize());
		}
		System.out.print("Enter flight number to see details. Enter 0 to return to menu: ");
		int flightNumber = sc.nextInt();
		if (flightNumber == 0) {
			menu();
		}
		for(int i = 0; i < flightMap.get(flightNumber).getSize(); i++) { // Prints out Passenger List and Waitlist
			if (i<5) {
				System.out.println("Passenger"+ Integer.sum(i,1) + ". " + flightMap.get(flightNumber).output(i));
			}
			else System.out.println("Waitlist"+ Integer.sum(i,-4) + ". " + flightMap.get(flightNumber).output(i));
		}
	}
	/**
	 * This main opens a scanner that will be used for the duration of the program. For each output of menu, the
	 * respective method is called until the method is complete, then menu will be called again and the process will
	 * continue until the program is closed. 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner flightScanner = new Scanner(new FileReader("flights.txt")); // Scans in flight data from text file
		for (int i = 0; i < 6; i++) {
			origin[i] = flightScanner.next();
			destination[i] = flightScanner.next();
			flight[i] = new Flight(origin[i], destination[i]);
			flightMap.put(flight[i].getID(), flight[i]);
		}
		flightScanner.close();
		sc = new Scanner(System.in);
		do {
			switch (menu()) {
			case 0: createPassenger();
			break;
			case 1: editPassenger();
			break;
			case 2: alphaPassenger();
			break;
			case 3: priorityPassenger();
			break;
			case 4: makeReservation();
			break;
			case 5: cancelReservation();
			break;
			case 6: printFlights();
			break;
			}
			
		} while (!exit);
		sc.close();
	}
}
