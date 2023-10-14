package SehhaApp;
import java.util.ArrayList;import java.util.InputMismatchException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


/**
 * 
 * The admin application
 * @author Josh, Marcus, Rami, Muteb
 * created 5/3/2023
 * @version 5/19/2023
 */
public class SehhaAdminApp {
	/**
	 * ArrayList of patients
	 */
	private ArrayList<Patient> patients = new ArrayList<>(); 
	
	/**
	 * ArrayList of services
	 */
	private ArrayList<Service> services = new ArrayList<>(); 
	
	/**
	 * ArrayList of slots
	 */
	private ArrayList<Slot> slots = new ArrayList<>();
	
	/**
	 * creation of the scanner object
	 */
	Scanner scanner = new Scanner(System.in);
	
	/**
	 * creation of the menu object
	 */
	DisplayMenu menu = new DisplayMenu();

	//SETTERS AND GETTERS
	
	/**
	 * returns the patients list
	 * @return patients
	 */
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	
	/**
	 * sets the patients list of the SebhaAdminApp
	 * @param patients - an ArrayList of Patient objects
	 */
	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}
	
	/**
	 * returns the services list
	 * @return services
	 */
	public ArrayList<Service> getServices() {
		return services;
	}
	
	/**
	 * sets the services list of the SebhaAdminApp
	 * @param services - an ArrayList of Service objects
	 */
	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}
	
	/**
	 * returns the slots list
	 * @return slots
	 */
	public ArrayList<Slot> getSlots() {
		return slots;
	}
	
	/**
	 * sets the slots list of the SebhaAdminApp
	 * @param slots - an ArrayList of slots object
	 */
	public void setSlots(ArrayList<Slot> slots) {
		this.slots = slots;
	}

	//METHODS ARE SHOWN BELOW:

	/**
	 * checks if the new patient entered is a null object
	 * if it is a null object, returns a string stating that the patient is invalid
	 * checks if the new patient is already in the patients list
	 * if new patient is found in the patients list, returns a string stating that the patient already exists
	 * if not found, adds the new patient into the patients list and
	 * returns a string that says the new patient has been added successfully 
	 * @param newPatient - the patient to be added
	 * @return string
	 */
	public String addPatient(Patient newPatient) {
		if (newPatient == null) {
			return "Invalid patient. Please provide valid patient information.";
		}

		for (Patient p : patients) {
			if (p.getName().equals(newPatient.getName()) || p.getPid() == newPatient.getPid()) {
				return "The Patient ID "+ newPatient.getPid() + " already exists";
			}
		}

		patients.add(newPatient);
		return "Patient ID: " + newPatient.getPid() + " is added successfully.";
	}

	/**
	 * searches for wanted patient in the list
	 * if wanted patient is found in the list, the patient is removed from the list
	 * and returns a string that states the patient has been removed successfully
	 * if not found, returns a string that states the patient was not found within the database
	 * @param pid - the id of the wanted patient
	 * @return string
	 */
	public String deletePatient(long pid) {
		for(Patient p:patients) {
			if(p.getPid()==pid) {
				patients.remove(p);
				return "Patient ID " + pid + " removed successfully.";
			}
		}
		return "Patient ID " + pid + " is not found within the database.";
	}

	/**
	 * searches for the wanted patient in the list
	 * if the patient is found in the list, asks the user which data they want to modify, name or residency type
	 * receives the user input and calls the function which would allow them to
	 * modify the data they wanted to modify
	 * once the data of the patient has been updated, 
	 * returns a string which states the data of the patient has been updated successfully
	 * if patient was not found in the list, returns a string which states the patient was
	 * not found in the database.
	 * @param pid - the id of the wanted patient
	 * @return string
	 */
	public String modifyPatient(long pid) {
		boolean flag = false;

		for (Patient p : patients) {
			if (p.getPid() == pid) {
				flag = true;
				menu.displayModifyPatient(pid);
				System.out.print("Choose Data to be modified (0-2): ");
				int modifyPatientChoice = scanner.nextInt();
				scanner.nextLine();

				switch (modifyPatientChoice) {
				case 1:
					modifyPatientName(pid);
					break;
				case 2:
					modifyPatientResidencyType(pid);
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid choice, please try again.");
					break;
				}
				return "Patient ID " + pid + " updated successfully.";
			}
		}

		
		return "Patient ID " + pid + " was not found within the database.";	

	}
	
	/**
	 * asks the user to enter the new name of the patient
	 * sets the name of the patient as the new name entered
	 * @param pid - the id of the wanted patient
	 */
	public void modifyPatientName(long pid) {
		for (Patient p : patients) {
			if (p.getPid() == pid) {
				System.out.println("Patient ID: " + p.getPid());
				System.out.println("Old patient name: " + p.getName());
				String newPatientName = "";

				boolean nameValid = false;
				while (!nameValid) {
					System.out.print("Please enter the new patient name: ");
					newPatientName = scanner.nextLine();
					if (!newPatientName.matches(".*\\d.*") && !newPatientName.isEmpty()) {
						nameValid = true;
					} else if (newPatientName.isEmpty()) {
						System.out.println("Invalid input. The name should not be empty.");
					} else {
						System.out.println("Invalid input. The name should not contain numbers.");
					}
				}

				p.setName(newPatientName);
			}
		}
	}


	/**
	 * asks the user to enter the new residency type of the patient
	 * sets the residency type of the patient as new residency type chosen
	 * @param pid - the id of the wanted patient
	 */
	public void modifyPatientResidencyType(long pid) {
		for(Patient p: patients) {
			if(p.getPid()==pid) {
				System.out.println("Patient ID: " + p.getPid());
				System.out.println("Old patient residency type: " + p.getResidencyType());
				ResidencyType residencyType = null;
				while (residencyType == null) {
					System.out.print("Please enter the patient's new residency type (1 = Resident, 2 = Visitor): ");
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
				p.setResidencyType(residencyType);
			}
		}
	}

	/**
	 * searches for the wanted patient in the patient list
	 * if the patient is found in the list, returns the patient
	 * if the patient is not found in the list, returns a null object
	 * @param pid - id of the wanted patient
	 * @return patient object or null
	 */
	public Patient findPatient(long pid) {
		for(Patient p:patients) {
			if(p.getPid()==pid) {
				System.out.printf("%n| %-20s | %-20s | %-20s |%n", "Patient ID", "Patient Name", "Residency Type");
				return p;
			}
		}
		return null;
	}
	
	/**
	 * checks if the new service is null or not
	 * if null, returns a string that states that the service is invalid
	 * if not null, searches for the new service in the services list
	 * if found, returns a string that states the service already exists
	 * if not found, adds the new service into the services list and
	 * returns a string that states the service has been added successfully
	 * @param newService - the service to be added
	 * @return string
	 */
	public String addService(Service newService) {
		if (newService == null) {
			return "Invalid service. Please provide valid service information.";
		}
		for(Service s: services) {
			if(s.getTitle().equalsIgnoreCase(newService.getTitle())) {
				return "Service ID " + s.getId() + " already exists.";
			}
		}
		int latestId = 0;
		for (Service service : services) {
			latestId = service.getId();
		}

		newService.setId(latestId+1);
		services.add(newService);
		System.out.println("Service ID: "+ newService.getId());
		return "Service ID " + newService.getId() + " added successfully.";
	}

	/**
	 * searches for the wanted service in the services list
	 * if service is not found, returns a string that states the service was not found within the database
	 * if service is found, deletes all the slots related to the service, deletes the service and 
	 * returns a string that states the service and slots related to it are deleted successfully
	 * @param serviceID - the id of the wanted service
	 * @return string
	 */
	public String deleteService(int serviceID) {
		Service service=null;
		ArrayList<Slot> deletedSlots= new ArrayList<>();
		for(Service s:services) {
			if(s.getId()==serviceID) {
				service=s;}
		}


		if (service!=null) {
			for (Slot ss :slots) {
				if(ss.getAllocatedService().getId()==serviceID) {
					deletedSlots.add(ss);}
			}
			slots.removeAll(deletedSlots);
			services.remove(service);
			return "Service ID " + serviceID + " and all related slots are deleted successfully.";
		}


		return "Service ID " + serviceID + " is not found within the database";

	}

	/**
     * searches for the wanted service in the list
	 * if the service is found in the list, asks the user which data they want to modify, title or price
	 * receives the user input and calls the function which would allow them to
	 * modify the data they wanted to modify
	 * once the data of the service has been updated, 
	 * returns a string which states the data of the service has been updated successfully
	 * if service was not found in the list, returns a string which states the service was
	 * not found in the database.
	 * @param serviceID - the id of the wanted service
	 * @return string
	 */
	public String modifyService(int serviceID) {
		boolean flag = false;

		for (Service s : services) {
			if (s.getId() == serviceID) {
				flag = true;
				menu.displayModifyService(serviceID);
				System.out.print("Data to be modified (0-2): ");
				int modifyServiceChoice = scanner.nextInt();
				scanner.nextLine(); // Consume the newline character after the integer input

				switch (modifyServiceChoice) {
				case 1:
					modifyServiceTitle(serviceID);
					break;
				case 2:
					modifyServicePrice(serviceID);
					break;
				case 0:
					break;
				default:
					System.out.println("Invalid choice, please try again.");
					break;
				}

				return "Service ID " + serviceID + " updated successfully.";
			}
		}
		
		return "Service ID is not found within the database.";
	
	}

	/**
	 * asks the user to enter the new title of the service
	 * sets the title of the service to the new title
	 * @param serviceID - the id of the wanted service
	 */
	public void modifyServiceTitle(int serviceID) {
		for(Service s: services) {
			if(s.getId()==serviceID) {
				System.out.println("Service ID: " + s.getId());
				System.out.println("Old service type: " + s.getTitle());
				String serviceTitle = null;
				while (serviceTitle == null) {
					System.out.print("Please enter the new service type (1 = Procedure, 2 = Generic, 3 = Specialized, 4 = Operation): ");
					int serviceTitleChoice = scanner.nextInt();
					switch (serviceTitleChoice) {
					case 1:
						serviceTitle = "Procedure";
						break;
					case 2:
						serviceTitle = "Generic";
						break;
					case 3:
						serviceTitle = "Specialized";
						break;
					case 4:
						serviceTitle = "Operation";
						break;
					default:
						System.out.println("Invalid choice. Please enter a valid service type.");
						break;
					}
					scanner.nextLine(); // consume the newline character after the integer input
				}
				s.setTitle(serviceTitle);
			}
		}
	}

	/**
	 * asks the user to enter the new price of the service
	 * sets the price of the service to the new price
	 * @param serviceID - the id of the wanted service
	 */
	public void modifyServicePrice(int serviceID) {
		for (Service s : services) {
			if (s.getId() == serviceID) {
				System.out.println("Service ID: " + s.getId());
				System.out.println("Old service price per slot: " + s.getPricePerSlot());

				boolean isValidInput = false;
				int newPrice = 0;

				while (!isValidInput) {
					try {
						System.out.print("Please enter the new price per slot: ");
						newPrice = scanner.nextInt();
						isValidInput = true;
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Please enter a numerical value.");
						scanner.nextLine(); // Clear the input buffer
					}
				}
				s.setPricePerSlot(newPrice);
			}
		}
	}

	/**
	 * searches for the wanted service in the services list
	 * if the service is found in the list, returns the service
	 * if the service is not found in the list, returns a null object
	 * @param servTitle - the title of the wanted service
	 * @return service object or null
	 */
	public Service findService(String servTitle) {
		for(Service s:services) {
			if(s.getTitle().equalsIgnoreCase(servTitle)) {
				System.out.printf("%n| %-20s | %-20s | %-20s |%n", "Service ID", "Service Title", "Price");
				return s;
			}
		}
		return null;
	}

	/**
	 * searches for the wanted service in the services list
	 * if was not found in the list, returns a string that states the service does not exist
	 * if found in the list, searches for the slot on the wanted date and time
	 * if slot was found, checks if the maximum slots per day for the service has been reached
	 * if maximum slots per day has been reached, returns a string that states
	 * maximum slots per day of the service has been reached
	 * if maximum slots per day has not been reached, sets the allocated service of the slot as the wanted service
	 * and returns a string that states the service has been assigned to the wanted slot
	 * if the slot was not found, returns a string that states that there is no slot on the wanted date and time
	 * @param servTitle - the title of the wanted service
	 * @param date - the wanted date
	 * @param time - the wanted time
	 * @return string
	 */
	public String assignService(String servTitle, LocalDate date, LocalTime time) {
		Service wantedService=null;

		for (Service service : services) {
			if (service.getTitle().equalsIgnoreCase(servTitle)) 
				wantedService = service;
		}

		if (wantedService==null) {
			return String.format("The service %s does not exist", servTitle);
		}

		int maximumSlots = 0;
		switch (wantedService.getTitle().toLowerCase()) {
		case "procedure":
			maximumSlots = 15;
			break;
		case "generic":
			maximumSlots = 20;
			break;
		case "specialized":
			maximumSlots = 10;
			break;
		case "operation":
			maximumSlots = 5;
			break;
		}

		boolean slotExists = false;

		for (Slot slot :slots) {
			if (slot.getDate().equals(date) && slot.getTime().equals(time)) {
				slotExists = true;
			}
		}

		if (slotExists) {
			ArrayList<Slot> serviceSlots = new ArrayList<>();

			for (Slot slot : slots) {
				if (slot.getDate().equals(date) && slot.getAllocatedService().equals(wantedService)) {
					serviceSlots.add(slot);			
				}
			}

			if (serviceSlots.size()<maximumSlots) {
				for (Slot slot : slots) {
					if (slot.getDate().equals(date) && slot.getTime().equals(time)) {
						slot.setAllocatedService(wantedService);
						return "The service has been assigned to the wanted slot.";
					}
				}
			}
			return "Sorry, the maximum slots per day for this service has been reached.";
		}

		return "Sorry, the slot you were asking for does not exist.";
	}

	/**
	 * creates a new slot with no service and no patient
	 * sets the date and time of the new slot automatically
	 * sets the booked of the slot to false
	 * the new empty slot is added to the slots list
	 * returns a string that states the slot has been added successfully
	 * @return string
	 */
	public String addEmptySlot() {
		Slot slot = new Slot();
		slot.setAllocatedService(new Service());
		slot.setAllocatedPatient(new Patient());

		LocalDate latestDate = LocalDate.of(1,1,1);
		LocalTime latestTime = LocalTime.of(0, 0);
		if (slots.size()==0) {
			latestDate = LocalDate.now();
			latestDate = latestDate.plusDays(1);
			latestTime = LocalTime.of(7, 0);
		}
		else {
			int latestId=0;

			for (Slot s : slots) {
				latestId=s.getId();
				if (s.getDate().compareTo(latestDate)>=0) {
					latestDate = s.getDate();
					if (s.getTime().compareTo(LocalTime.of(20, 30))==0) {
						latestDate = latestDate.plusDays(1);
						latestTime = LocalTime.of(7, 0);
					}
					else if (s.getTime().compareTo(latestTime)>=0) {
						latestTime = s.getTime();
						latestTime = latestTime.plusMinutes(30);
					}
				}
			}
			int id = latestId+1;
			slot.setId(id);
		}

		slot.setBooked(false);
		slot.setDate(latestDate);
		slot.setTime(latestTime);
		slots.add(slot);
		return "The slot ID " + slot.getId() + " is added successfully.";
	}

	/**
	 * searches for the wanted service in the services list
	 * if wanted service is not found, returns a string that states there is no service with the that service id
	 * if wanted service is found, searches for slot on the wanted date and time, with the allocated service as the service
	 * if slot with the wanted service on the wanted date and time is found, this slot is deleted
	 * and returns a string that states the slot has been deleted successfully
	 * @param serviceID - the id of the wanted service
	 * @param date - the wanted date
	 * @param time - the wanted time
	 * @return string
	 */
	public String deleteSlot(int serviceID, LocalDate date, LocalTime time) {
		boolean serviceExists = false;
		for (Service service : services) {
			if (serviceID == service.getId()) {
				serviceExists = true;
				break; // No need to continue searching once the service is found
			}
		}
		if (!serviceExists) {
			return String.format("There is no service with the ID %d.", serviceID);
		}
		ArrayList<Slot> slotsToRemove = new ArrayList<>();
		for (Slot s : slots) {
			if (s.getAllocatedService().getId() == serviceID && s.getDate().equals(date) && s.getTime().equals(time)) {
				slotsToRemove.add(s);
			}
		}
		slots.removeAll(slotsToRemove);
		return "The slot for " + date + " " + time + " is deleted successfully.";
	}

	/**
	 * searches for the wanted patient in the patients list 
	 * if the wanted service is not found, returns a null object
	 * if wanted patient is found, find the slot on the wanted date and wanted time
	 * checks if the slot is booked or not
	 * if slot is booked returns a string stating that the slot has already been reserved
	 * if slot is not booked, sets the allocated patient as the wanted patient
	 * sets booked to true
	 * returns a string that the slot for the patient has been reserved
	 * returns an list of slots with the wanted service, on the wanted date, which are not booked
	 * @param pid - the patient of the wanted patient
	 * @param date - the wanted date
	 * @param time - the wanted time
	 * @return string
	 */
	public String reserveSlot(long pid, LocalDate date, LocalTime time) {
		Patient patient = new Patient();
		for (Patient p : patients) {
			if (p.getPid()==pid) {
				patient=p;
			}
		}
		if (patient.getName()==null) {
			return String.format("There is no patient associated with the entered ID %d.", pid);
		}
		for (Slot s : slots) {
			if (s.getDate().equals(date) && s.getTime().equals(time)) {
				if (s.isBooked()) {
					return "The slot has already been reserved";
				}
				else {
					s.setAllocatedPatient(patient);
					s.setBooked(true);
					return String.format("Slot for the patient %s has been reserved successfully.", patient.getName());
				}
			}

		}
		return String.format("No slot was made on the date %s and time %s.", date.toString(), time.toString());
	}

	/**
	 * prints out a list of all the patients in the system
	 * with their id, name, residency type (resident or visitor)
	 */
	public void showAllPatients() {
		if(!patients.isEmpty()) {
			System.out.println("\n---------------------------- All Patients ----------------------------");
			System.out.printf("| %-20s | %-20s | %-20s |%n", "Patient ID", "Patient Name", "Residency Type");
			System.out.println("----------------------------------------------------------------------");
			for(Patient p:patients) {
				System.out.print(p);
			}
			System.out.println("----------------------------------------------------------------------\n");
		} else {
			System.out.println("There are currently no patients within the database.");
		}
	}

	/**
	 * prints out a list of all the services in the system
	 * with their id, title, price, frequency (the number of times the service has been booked)
	 */
	public void showAllServices() {
		if (!services.isEmpty()) {
			System.out.println("\n-------------------------------------- All Services -----------------------------------------");
			System.out.printf("| %-20s | %-20s | %-20s | %-20s |%n", "Service ID", "Service Title", "Price", "Frequency");
			System.out.println("---------------------------------------------------------------------------------------------");

			for (Service s : services) {
				int frequency = 0;
				for (Slot ss : slots) {
					if (ss.getAllocatedService() != null && ss.getAllocatedService().getId() == s.getId() && ss.isBooked()) {
						frequency += 1;
					}
				}
				String service = String.format("| %-20d | %-20s | QAR %-16d | %-20d |%n", s.getId(), s.getTitle().toUpperCase(), s.getPricePerSlot(), frequency);
				System.out.print(service);
			}

			System.out.println("---------------------------------------------------------------------------------------------\n");
		} else {
			System.out.println("There are currently no services within the database.");
		}
	}

	
	/**
	 * prints out a list of all the slots in the system
	 * with their id, time, date, booking status, service, patient, 
	 */
	public void showAllSlots() {
		if(!slots.isEmpty()) {
			System.out.println("\n--------------------------------------------------------------- All Slots -----------------------------------------------------------------");
			System.out.printf("| %-20s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "Slot ID", "Time", "Date", "Booking Status", "Allocated Service", "Allocated Patient");
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
			for(Slot s:slots) {
				System.out.print(s);
			}
			System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------\n");
		} else {
			System.out.println("There are currently no slots within the database.");
		}
	}

	/**
	 * finds the slots on the wanted date
	 * @param date -  the wanted date
	 * @return list of slots on the wanted date
	 */
	public ArrayList<Slot> findSlotsByDate(LocalDate date) {
		ArrayList<Slot> slotDate = new ArrayList<>();
		for (Slot slot : slots) {
			if (slot.getDate().equals(date)) {
				slotDate.add(slot);
			}
		}
		return slotDate;
	}

	/**
	 * finds the slots which have not been booked (isBooked ==  false)
	 * returns a list of slots which have not been booked
	 * @return a list of available slots
	 */
	public ArrayList<Slot> getAvailableSlots(){
		ArrayList<Slot> availableSlots = new ArrayList<Slot>();
		for(Slot s:slots) {
			if(s.isBooked()==false) {
				availableSlots.add(s);
			}
		}
		return availableSlots;
	}

	/**
	 * searches for the wanted service in the services list 
	 * if the wanted service is not found, returns a null object
	 * if wanted service is found, find slots for the 
	 * wanted service, on the wanted date, which are not booked (isBooked == false)
	 * @param serviceID - the id of the wanted service
	 * @param date - the wanted date
	 * @return a list of available slots with the wanted service, on the wanted date
	 */
	public ArrayList<Slot> getAvailableSlotsPerServiceByDate(int serviceID, LocalDate date) {
		Service wantedService = null;
		for (Service service : services) {
			if (service.getId()==serviceID) {
				wantedService = service;
			}
		}
		
		if (wantedService==null) {
			return null;
		}
		
		ArrayList<Slot> availableSlots = new ArrayList<Slot>();
		for(Slot s:slots) {
			if(s.getAllocatedService().getTitle()!=null) {
				if (s.getAllocatedService().getTitle().equalsIgnoreCase(wantedService.getTitle()) && s.getDate().equals(date) && s.isBooked()==false) {
					availableSlots.add(s);
					}
			}
		}
		return availableSlots;
	}

	/**
	 * finds the slot with the wanted service, on the wanted date, on the wanted time
	 * checks if the slot is booked or not
	 * @param serviceTitle - the title of the wanted service
	 * @param date - the wanted date
	 * @param time -  the wanted time
	 * @return if slot is available or not
	 */
	public Boolean isSlotAvailable(String serviceTitle,LocalDate date,LocalTime time) {
		boolean isSlotAvailable = true;
		for (Slot s : this.slots) {
			if (s.getAllocatedService().getTitle()!=null)
				if (s.getAllocatedService().getTitle().equalsIgnoreCase(serviceTitle) && s.getDate().equals(date) && s.getTime().equals(time)) {
					if (s.isBooked()) {
						isSlotAvailable = false;
					}		
			}
			
		}return isSlotAvailable;
	}

	/**
	 * searches for the wanted service in the services list 
	 * if the wanted service is not found, returns a null object
	 * if wanted service is found, find slots for the 
	 * wanted service on the wanted date
	 * @param servTitle - the title of the service wanted
	 * @param date - the wanted date
	 * @return a list of slots with the wanted service, on the wanted date
	 */
	public ArrayList<Slot> getServiceSlotsStatus(String servTitle, LocalDate date) {
		Service wantedService = null;
		for (Service service : services) {
			if (service.getTitle().equalsIgnoreCase(servTitle)){
				wantedService = service;
			}
		}
		
		if (wantedService==null) {
			return null;
		}
		
		
		ArrayList<Slot> serviceSlot = new ArrayList<>();
		for (Slot s : slots) {
			if (s.getAllocatedService().getTitle()!=null) {
				if (s.getAllocatedService().getTitle().equalsIgnoreCase(wantedService.getTitle()) && s.getDate().equals(date)) {
					serviceSlot.add(s);
				}
			}
		}
		return serviceSlot;
	}
	
	/**
	 * checks if the service exists or not
	 * if service does not exist returns a null object
	 * if service does exist it returns a list of slots which has the slots for the service wanted
	 * @param servTitle - the title of the service wanted
	 * @return a list of slots which has the slots for the service wanted
	 */
	public ArrayList<Slot> getSlotsPerService(String servTitle) {
		boolean serviceExists = false;

		for (Service service : services) {
			if (service.getTitle().equalsIgnoreCase(servTitle)) {
				serviceExists = true;
				break;
			}
		}

		if (!serviceExists) {
			return null;
		}

		ArrayList<Slot> serviceSlot = new ArrayList<>();
		for (Slot slot : slots) {
			if (slot.getAllocatedService().getTitle()!=null) {
				if (slot.getAllocatedService().getTitle().equalsIgnoreCase(servTitle)) {
					serviceSlot.add(slot);
				}
			}
		}

		return serviceSlot;
	}

	/**
	 * receives the id of a given patient
	 * if wanted patient is not found, returns a string stating that the patient does not exist
	 * if wanted patient is found, prints out the invoice of the patient and returns the 
	 * total to be paid by the patient
	 * @param pid - the id of the wanted patient
	 * @return string
	 */
	public String printPatientsInvoice(long pid) {
		Patient wantedPatient = null;
		for (Patient patient : patients) {
			if (patient.getPid() == pid) {
				wantedPatient = patient;
				break; // No need to continue searching once the patient is found
			}
		}

		if (wantedPatient == null) {
			return String.format("%nThe patient with the entered ID %d does not exist.%n", pid);
		}

		// Print header
		System.out.println("-------------------------------------------------------------- Patient Invoice -------------------------------------------------------------");
		System.out.println("| Patient ID     | Name                     | Residency Type    | Time                | Date        | Allocated Service    | Price         |");
		double totalCost = 0;
		for (Slot slot : slots) {
			if (slot.getAllocatedPatient().getPid() == wantedPatient.getPid()) {
				String allocatedService = slot.getAllocatedService() != null && slot.getAllocatedService().getTitle() != null ? slot.getAllocatedService().getTitle() : "None";
				int priceOfSlot = slot.getAllocatedService() != null ? slot.getAllocatedService().getPricePerSlot() : 0;
				
				System.out.printf("| %-14d | %-24s | %-17s | %-19s | %-11s | %-20s | QAR %-9d |%n",
						wantedPatient.getPid(), wantedPatient.getName(), wantedPatient.getResidencyType(),
						slot.getTime(), slot.getDate(), allocatedService.toUpperCase(), priceOfSlot);
				if (slot.getAllocatedService() != null) {
					totalCost += (double) slot.getAllocatedService().getPricePerSlot();
				}
			}
		}

		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		return String.format("Invoice Cost: QAR %.2f%n", totalCost);
	}


	/**
	 * Writes the list of patients into the "Patients.dat" file
	 * Writes the list of services into the "Services.dat" file
	 * Writes the list of slots into the "Slots.dat" file
	 */
	//data management
	public void saveData() {
		try {
			ObjectOutputStream savePatientsFile = new ObjectOutputStream(new FileOutputStream("Patients.dat"));
			savePatientsFile.writeObject(patients);
			ObjectOutputStream saveServicesFile = new ObjectOutputStream(new FileOutputStream("Services.dat"));
			saveServicesFile.writeObject(services);
			ObjectOutputStream saveSlotsFile = new ObjectOutputStream(new FileOutputStream("Slots.dat"));
			saveSlotsFile.writeObject(slots);

			savePatientsFile.close();
			saveServicesFile.close();
			saveSlotsFile.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * calls the functions
	 * readPatientsFile()
	 * readServicesFile()
	 * readSlotsFile()
	 */
	public void readData() {
		try {
			readPatientsFile();
			readServicesFile();
			readSlotsFile();

		} catch (FileNotFoundException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * reads the data from the "Patients.dat" file and
	 * stores the array list object that was read in the
	 * patients field of the SebhaAdminApp
	 * @throws FileNotFoundException for opening the file
	 * @throws IOException for when reading the file
	 * @throws ClassNotFoundException for when the casting the object that has been read
	 */
	@SuppressWarnings("unchecked")
	public void readPatientsFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream readPatients = new ObjectInputStream(new FileInputStream("Patients.dat"));
		ArrayList<Patient> patientsInput = new ArrayList<Patient>();
		patientsInput=(ArrayList<Patient>) readPatients.readObject();
		for (Patient p:patientsInput)
			patients.add(p);
		readPatients.close();
	}

	/**
	 * reads the data from the "Services.dat" file and
	 * stores the array list object that was read in the 
	 * services field of the SebhaAdminApp
	 * @throws FileNotFoundException for opening the file
	 * @throws IOException for when reading the file
	 * @throws ClassNotFoundException for when the casting the object that has been read
	 */
	@SuppressWarnings("unchecked")
	public void readServicesFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream readServices = new ObjectInputStream(new FileInputStream("Services.dat"));
		ArrayList<Service> servicesInput = new ArrayList<Service>();
		servicesInput= (ArrayList<Service>) readServices.readObject();
		for(Service s:servicesInput) {
			services.add(s);
		}
		readServices.close();
	}
	/**
	 * reads the data from the "Slots.dat" file and 
	 * stores the array list object that was read in the 
	 * slots field of the SevhaAdminApp
	 * @throws FileNotFoundException for opening the file
	 * @throws IOException for when reading the file
	 * @throws ClassNotFoundException for when the casting the object that has been read
	 */
	@SuppressWarnings("unchecked")
	public void readSlotsFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream readSlots = new ObjectInputStream(new FileInputStream("Slots.dat"));
		ArrayList<Slot> slotsInput = new ArrayList<Slot>();
		slotsInput=(ArrayList<Slot>) readSlots.readObject();
		for (Slot s:slotsInput)
			slots.add(s);
		readSlots.close();
	}

}
