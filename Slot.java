package SehhaApp;
import java.time.LocalTime;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The slot class
 * @author Josh, Marcus, Rami, Meteb
 *created 5/3/2023
 *@version 5/19/2023
 */
public class Slot implements Serializable {
	private static final long serialVersionUID = 5296044219594567647L;
    
	/**
	 * next available id for the slot
	 */
	private static int nextId = 1; 
    /**
     * id of the slot
     */
    private int id; // Unique ID for each slot
    /**
     * time of the slot
     */
    private LocalTime time;
    /**
     * date of the slot
     */
    private LocalDate date;
    /**
     * booking status of the slot
     */
    private boolean isBooked;
    /**
     * service allocated in the slot
     */
    private Service allocatedService;
    /**
     * patient allocated in the slot
     */
    private Patient allocatedPatient;

    /**
     * non-parameterized constructor of the slot class
     * id of the slot is auto generated
     */
    public Slot() {
        this.id = nextId++;
    }

    /**
     * instantiates the time, date, booking status, allocated service and allocated patient of the slot
     * @param time - time of the slot
     * @param date - date of the slot
     * @param isBooked - booking status of the slot
     * @param allocatedService - service allocated in the slot
     * @param allocatedPatient - patient allocated in the slot
     */
    public Slot(LocalTime time, LocalDate date, boolean isBooked, Service allocatedService, Patient allocatedPatient) {
        this();
        setTime(time);
        setDate(date);
        setBooked(isBooked);
        setAllocatedService(allocatedService);
        setAllocatedPatient(allocatedPatient);
    }

    /**
     * get the id of the slot
     * @return the id of the slot
     */
    public int getId() {
        return id;
    }

    /**
     * set the id of the slot
     * @param id - the id of the slot
     */
    public void setId(int id) {
        this.id = id;
    }
	
    /**
	 * get the time of the slot
	 * @return time of the slot
	 */
    public LocalTime getTime() {
		return time;
	}
	
    /**
	 * set the time of the slot
	 * @param time - time of the slot
	 */
    public void setTime(LocalTime time) {
		this.time = time;
	}
	
    /**
     * get the date of the slot
     * @return the date of the slot
     */
    public LocalDate getDate() {
		return date;
	}
	
    /**
     * set the date of the slot
     * @param date - the date of the slot
     */
    public void setDate(LocalDate date) {
		this.date = date;
	}
	
    /**
     * get the booking status of the slot
     * @return booking status of the slot
     */
    public boolean isBooked() {
		return isBooked;
	}
	
    /**
     * set the booking status of the slot
     * @param isBooked - booking status of the slot
     */
    public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	
    /**
     * get the service allocated in the slot
     * @return the service allocated in the slot
     */
    public Service getAllocatedService() {
		return allocatedService;
	}
	
    /**
     * set the service allocated in the slot
     * @param allocatedService - the service allocated in the slot
     */
    public void setAllocatedService(Service allocatedService) {
		this.allocatedService = allocatedService;
	}
	
    /**
     * get the patient allocated in the slot
     * @return the patient allocated in the slot
     */
    public Patient getAllocatedPatient() {
		return allocatedPatient;
	}
	
    /**
     * set the patient allocated in the slot
     * @param allocatedPatient - the patient allocated in the slot
     */
    public void setAllocatedPatient(Patient allocatedPatient) {
		this.allocatedPatient = allocatedPatient;
	}
	
	/**
	 * returns a string of the details of the slot such as the slot id, time, date, booking status, allocated service and allocated patient
	 * @return details of the slot
	 */
	@Override
	public String toString() {
	    String allocatedService = getAllocatedService() != null && getAllocatedService().getTitle() != null? getAllocatedService().getTitle().toUpperCase() : "None";
	    String allocatedPatient = getAllocatedPatient() != null && getAllocatedPatient().getName() != null ? getAllocatedPatient().getName() : "None";
	    
	    return String.format("| %-20d | %-20s | %-20s | %-20s | %-20s | %-20s |%n", getId(), getTime(), getDate(), isBooked() ? "Booked" : "Unoccupied", allocatedService, allocatedPatient);
	}

}
