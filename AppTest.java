package SehhaApp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * 
 * Application Test
 * @author Josh, Marcus, Rami, Meteb
 * created 5/5/2023
 * @version 5/19/2023
 */
public class AppTest {
	/**
	 * use of default constructor
	 */
	
	/**
	 * created DisplayMenu object
	 */
	static DisplayMenu menu = new DisplayMenu();
	
	/**
	 * created Scanner object
	 */
	static Scanner scanner = new Scanner(System.in);
	
	/**
	 * created SebhaAdminApp object
	 */
	static SehhaAdminApp app = new SehhaAdminApp();

	/**
	 *
	 * Main method
	 * @param args for main method
	 */
	public static void main(String[] args) {
		app.readData();
		app.saveData();
		int choice = -1;

		do {
			menu.displayMenu();
			System.out.print("Enter a number (0-5): ");

			try {
				choice = scanner.nextInt();

				switch (choice) {
				case 1:
					patientFunctions();
					break;
				case 2:
					serviceFunctions();
					break;
				case 3:
					slotFunctions();
					break;
				case 4:
					dataListFunctions();
					break;
				case 5:
					otherInformationFunctions();
					break;
				case 0:
					System.out.println("\nThank you for using the Sehha Hospital Reception Management System! ");
					System.out.print("\nExiting now...");
					app.saveData();
					break;
				default:
					System.out.println("Invalid choice. Returning to main menu...");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer. Returning to main menu...");
				scanner.nextLine(); // Clear the invalid input from the scanner
				continue;
			}
		} while (choice != 0);
	}

	/**
	 * 
	 * provides the user with list of patient functions
	 * receives user input and calls the function chosen
	 */
	public static void patientFunctions() {
		boolean flag = true;
		while (flag) {
			menu.displayPatientFunctions();
			System.out.print("Enter a number (0-4): ");
			int patientChoice = scanner.nextInt();

			switch(patientChoice) {
			case 1:
				String addResult = app.addPatient(enterPatient());
				System.out.println(addResult);
				break;
			case 2:
				String deleteResult = app.deletePatient(enterPatientId());
				System.out.println(deleteResult);
				break;
			case 3:
				String modifyResult =app.modifyPatient(enterPatientId());
				System.out.println(modifyResult);
				break;
			case 4:
				Patient findResult = app.findPatient(enterPatientId());
				if(findResult!=null) {
					System.out.println(findResult);
				} else {
					System.out.println("The entered patient is not found within the database.");
				}
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		}
	}

	/**
	 * 
	 * provides the user with list of service functions
	 * receives user and runs the function chosen
	 */
	public static void serviceFunctions() {
		boolean flag = true;
		while (flag) {
			menu.displayServiceFunctions();
			System.out.print("Enter a number (0-5): ");
			int serviceChoice = scanner.nextInt();

			switch(serviceChoice) {
			case 1:
				String addResult = app.addService(enterService());
				System.out.println(addResult);
				break;
			case 2:
				String deleteResult = app.deleteService(enterServiceId());
				System.out.println(deleteResult);
				break;
			case 3:
				String modifyResult = app.modifyService(enterServiceId());
				System.out.println(modifyResult);
				break;
			case 4:
				Service findResult = app.findService(enterServiceTitle());
				if(findResult!=null) {
					System.out.println(findResult);
				} else {
					System.out.println("The entered service is not found within the database.");
				}
				break;
			case 5:
				String assignResult = app.assignService(enterServiceTitle(), enterDate(), enterTime());
				System.out.println(assignResult);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		}
	}

	/**
	 * 
	 * provides the user with list of slot functions
	 * receives the user input and runs the function chosen
	 */
	public static void slotFunctions() {
		boolean flag = true;
		while (flag) {
			menu.displaySlotFunctions();
			System.out.print("Enter a number (0-3): ");
			int slotChoice = scanner.nextInt();

			switch(slotChoice) {
			case 1:
				String emptySlotResult = app.addEmptySlot();
				System.out.println(emptySlotResult);
				break;
			case 2:
				String deleteResult = app.deleteSlot(enterServiceId(), enterDate(), enterTime());
				System.out.println(deleteResult);
				break;
			case 3:
				String reserveResult = app.reserveSlot(enterPatientId(), enterDate(), enterTime());
				System.out.println(reserveResult);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		}

	}
	
	/**
	 * 
	 * provides list of functions which would display either all the patients, all the services or all the slots
	 * receives the user input and runs the function chosen
	 */
	public static void dataListFunctions() {
		boolean flag = true;
		while (flag) {
			menu.displayDataList();
			System.out.print("Enter a number (0-3): ");
			int dataListChoice = scanner.nextInt();

			switch(dataListChoice) {
			case 1:
				app.showAllPatients();
				break;
			case 2:
				app.showAllServices();
				break;
			case 3:
				app.showAllSlots();
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		}
	}

	/**
	 * 
	 * provides list of functions which provides other information such as available slots, patient invoice etc.
	 * receives the user input and runs the function chosen
	 */
	public static void otherInformationFunctions() {
		boolean flag = true;
		while (flag) {
			menu.displayOtherInformation();
			System.out.print("Enter a number (0-7): ");
			int otherInfoChoice = scanner.nextInt();

			switch(otherInfoChoice) {
			case 1:
				LocalDate date = enterDate();
				ArrayList<Slot> slotsDate = app.findSlotsByDate(date);
				if (slotsDate.size()>0) {
					System.out.printf("\nSlots on the %s\n", date.toString());
					System.out.printf("%n| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Slot ID", "Time", "Date", "Booking Status", "Allocated Service", "Allocated Patient");
					for (Slot slot : slotsDate) {
						System.out.print(slot);
					}
				} else {
					System.out.printf("%nThere are no slots found on %s.%n", date);
				}
				break;
			case 2:
				ArrayList<Slot> availableSlots = app.getAvailableSlots();

				if (availableSlots.isEmpty() || availableSlots == null) {
					System.out.println("\nNo available slots at the moment.\n");
				} else {
					System.out.println("\nThe list of available slots are shown below:\n");
					System.out.printf("%n| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Slot ID", "Time", "Date", "Booking Status", "Allocated Service", "Allocated Patient");
					for (Slot slot : availableSlots) {
						System.out.print(slot);
					}
				}
				break;
			case 3:
				ArrayList<Slot> availableServiceSlots = app.getAvailableSlotsPerServiceByDate(enterServiceId(), enterDate());
				if (availableServiceSlots == null) {
					System.out.println("\nThe service does not exist.\n");
				}
				else {
					if (availableServiceSlots.size()>0) {
						System.out.println("\nThe list of available slots for the wanted service are shown below:\n");
						System.out.printf("%n| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Slot ID", "Time", "Date", "Booking Status", "Allocated Service", "Allocated Patient");
						for (Slot slot : availableServiceSlots) {
							System.out.print(slot);
						}
					}else {
						System.out.println("\nThere are no available slots for the specified service on the given date.\n");
					}
				}
				break;
			case 4:
				String serviceTitle1 = enterServiceTitle();
				LocalDate date3 = enterDate();
				LocalTime time = enterTime();
				boolean availabilityResult = app.isSlotAvailable(serviceTitle1, date3, time);
				String availability = (availabilityResult)? "\nThe slot for " + date3 + " at " + time + " for the specified service is available.\n": "\nThe slot for " + date3 + " at " + time + " for the specified service is not available.\n";
				System.out.println(availability);
				break;
			case 5:
				String wantedServiceTitle = enterServiceTitle();
				LocalDate wantedDate = enterDate();

				ArrayList<Slot> serviceSlotsDate = app.getServiceSlotsStatus(wantedServiceTitle, wantedDate);
				if (serviceSlotsDate==null) {
					System.out.printf("%nThe service %s does not exist.%n", wantedServiceTitle);
				}
				else {
					if (serviceSlotsDate.size()>0) {
						System.out.printf("%nThe list of slots for the service %s on the date %s are shown below:%n", wantedServiceTitle, wantedDate);
						System.out.printf("%n| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Slot ID", "Time", "Date", "Booking Status", "Allocated Service", "Allocated Patient");
						for (Slot slot : serviceSlotsDate) {
							System.out.print(slot);
						}
					} else {System.out.print("\nThere are no slots on the specified date.\n");}
				}
				break;
			case 6:
				String serviceTitle2 = enterServiceTitle();
				ArrayList<Slot> serviceSlots = app.getSlotsPerService(serviceTitle2);
				if (serviceSlots == null) {
					System.out.printf("%nThere is no service with the title %s.%n", serviceTitle2);
				}
				else {
					if (serviceSlots.size()>0) {
						System.out.printf("%nThe slots for the service %s is shown below: %n", serviceTitle2);
						System.out.printf("%n| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Slot ID", "Time", "Date", "Booking Status", "Allocated Service", "Allocated Patient");
						for (Slot slot : serviceSlots) {
							System.out.print(slot);
						}
					}
					else {
						System.out.println("\nThere are no slots for the specified service.\n");
					}
				}
				break;
			case 7:
				String invoiceResult = app.printPatientsInvoice(enterPatientId());
				System.out.println(invoiceResult);
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("Invalid choice, please try again.");
				break;
			}
		}
	}

	/**
	 * 
	 * asks the user to enter the name and id of the patient
	 * asks the user whether the patient is a resident or visitor
	 * creates a Patient object with the data the user has entered
	 * @return Patient object
	 */
	public static Patient enterPatient() {
		Scanner scanner = new Scanner(System.in);

		// Prompt the user to enter the patient's name
		String name = "";
		boolean nameValid = false;
		while (!nameValid) {
			System.out.print("Please enter the patient's name: ");
			name = scanner.nextLine();
			if (!name.matches(".*\\d.*") && !name.isEmpty()) { // Check if the name contains any digits and is not empty
				nameValid = true;
			} else if (name.isEmpty()) {
				System.out.println("Invalid input. The name should not be empty.");
			} else {
				System.out.println("Invalid input. The name should not contain numbers.");
			}
		}

		// Prompt the user to enter the patient's person ID
		long patientId = 0;
		boolean idValid = false;
		while (!idValid) {
			try {
				System.out.print("Please enter the patient's ID: ");
				patientId = scanner.nextLong();
				scanner.nextLine(); // consume the new line character after the long input

				if (patientId >= 1) {
					idValid = true;
				} else {
					System.out.println("Invalid input. The patient ID should be greater than or equal to 1.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a numerical value for the patient ID.");
				scanner.nextLine(); // consume the invalid input
			}
		}

		ResidencyType residencyType = null;
		while (residencyType == null) {
			System.out.print("Please enter the patient's residency type (1 = Resident, 2 = Visitor): ");
			int residencyTypeChoice = scanner.nextInt();
			if (residencyTypeChoice == 1) {
				residencyType = ResidencyType.RESIDENT;
			} else if (residencyTypeChoice == 2) {
				residencyType = ResidencyType.VISITOR;
			} else {
				System.out.println("Invalid choice. Please enter 1 for Resident or 2 for Visitor.");
			}
			scanner.nextLine(); // consume the newline character after the integer input
		}
		// Create a new Patient object with the entered attributes
		Patient patient = new Patient(patientId, name, residencyType);

		return patient;
	}

	/**
	 * 
	 * asks the user to enter the attributes of service such as the title and the price per slot
	 * creates the Service object with title and price per slot chosen by the user
	 * 
	 * @return Service object
	 */
	public static Service enterService() {
		Scanner scanner = new Scanner(System.in);

		String serviceTitle = null;
		while (serviceTitle == null) {
			System.out.print("Please enter the service type (1 = Procedure, 2 = Generic, 3 = Specialized, 4 = Operation): ");
			int serviceTitleChoice = scanner.nextInt();
			switch (serviceTitleChoice) {
			case 1:
				serviceTitle = "procedure";
				break;
			case 2:
				serviceTitle = "generic";
				break;
			case 3:
				serviceTitle = "specialized";
				break;
			case 4:
				serviceTitle = "operation";
				break;
			default:
				System.out.println("Invalid choice. Please enter a valid service type.");
				break;
			}
			scanner.nextLine();
		}

		boolean isValidInput = false;
		int pricePerSlot = 0;

		while (!isValidInput) {
			try {
				System.out.print("Please enter the price per slot: ");
				pricePerSlot = scanner.nextInt();
				if (pricePerSlot > 0) {
					isValidInput = true;
				} else {
					System.out.println("Invalid input. The price per slot should be greater than 0.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a numerical value.");
				scanner.nextLine();
			}
		}


		Service service = new Service(serviceTitle, pricePerSlot);

		return service;
	}

	/**
	 * 
	 * asks the user to enter the patient ID
	 * @return patientId
	 */
	public static long enterPatientId() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the patient ID: ");
		Long patientId = scanner.nextLong();
		return patientId;
	}

	/**
	 * 
	 * asks the user to enter the service ID
	 * @return serviceId
	 */
	public static int enterServiceId() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the service ID: ");
		int serviceId = scanner.nextInt();
		return serviceId;
	}

	/**
	 * 
	 * asks the user to enter the service title
	 * @return serviceTitle
	 */
	public static String enterServiceTitle() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the service title: ");
		String serviceTitle = scanner.nextLine();
		return serviceTitle;
	}

	/**
	 * 
	 * asks the user to enter the date
	 * @return date
	 */
	public static LocalDate enterDate() {
		LocalDate date;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please enter the year: ");
		int year = scanner.nextInt();
		if (year<2023) {
			while (year<2023) {
				System.out.print("Invalid input. Please enter the year: ");
				year = scanner.nextInt();
			}
		}	
		date = LocalDate.of(year, 1, 1);

		System.out.print("Please enter the month: ");
		int month = scanner.nextInt();
		if (month<0 || month>12) {
			while (month<0 || month>12) {
				System.out.print("Invalid input. Please enter the month: ");
				month = scanner.nextInt();
			}
		}
		date = LocalDate.of(year, month, 1);

		System.out.print("Please enter the day: ");
		int day = scanner.nextInt();

		if (month==2) {
			if (date.isLeapYear()==true) {
				if (day<0 || day>29) {
					while (day<0 || day>29) {
						System.out.print("Invalid input. Please enter the day: ");
						day = scanner.nextInt();
					}
				}
			}
			else {
				if (day<0 || day>28) {
					while (day<0 || day>28) {
						System.out.print("Invalid input. Please enter the day: ");
						day = scanner.nextInt();
					}
				}
			}
		}

		else if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
			if (day<0 || day>31 ) {
				while (day<0 || day>31) {
					System.out.print("Invalid input. Please enter the day: ");
					day = scanner.nextInt();
				}
			}
		}

		else {
			if (day<0 || day>30 ) {
				while (day<0 || day>30) {
					System.out.print("Invalid input. Please enter the day: ");
					day = scanner.nextInt();
				}
			}
		}
		date = LocalDate.of(year, month, day);

		return date;
	}




	/**
	 * 
	 * asks the user to enter the hours and minutes
	 * hours entered can only be between 7 and 20
	 * minutes entered can only be 0 or 30
	 * @return time
	 */
	public static LocalTime enterTime() {
		LocalTime time;
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please enter the hour (7-20): ");
		int hour = scanner.nextInt();
		if (hour<7 || hour >20) {
			while (hour<7 || hour>20) {
				System.out.print("Invalid input. Please enter the hour (7-20): ");
				hour = scanner.nextInt();
			}
		}
		time = LocalTime.of(hour, 0);

		System.out.print("Please enter the minutes (Either 0 or 30): ");
		int minutes = scanner.nextInt();
		if (minutes!=0 && minutes!=30) {
			while (minutes!=0 && minutes!=30) {
				System.out.print("Invalid input. Please enter the minutes (Either 0 or 30): ");
				minutes = scanner.nextInt();
			}
		}
		time = LocalTime.of(hour, minutes);

		return time;
	}
}
