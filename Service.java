package SehhaApp;
import java.io.Serializable;

/**
 * The service class
 * @author Josh, Marcus, Rami. Meteb
 * created 5/3/2023
 * @version 5/19/2023
 */
public class Service implements Serializable{
	private static final long serialVersionUID = -6893611531990681789L;
	/**
	 * the title of the service
	 */
	private String title;
	
	/**
	 * the id of the service
	 */
	private int id;
	
	/**
	 * next available id for the service
	 */
	private static int nextId = 1; 
	
	/**
	 * the price per slot of the service
	 */
	private int pricePerSlot;
	
	/**
	 * non-parameterized constructor of Service class
	 */
	public Service() {}
	
	/**
	 * instantiates the title, price per slot of the service and the service id
	 * @param title - title of the service
	 * @param pricePerSlot - price per slot of the service
	 */
	public Service(String title, int pricePerSlot) {
		setTitle(title);
		setPricePerSlot(pricePerSlot);
		setId(++nextId);
	}

	/**
	 * get the title of the service
	 * @return the title of the service
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * set the title of the service
	 * @param title -  the title of the service
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * get the id of the service
	 * @return the id of the service
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * set the id of the service
	 * @param id - the id of the service
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * get the price per slot of the service
	 * @return price per slot of the service
	 */
	public int getPricePerSlot() {
		return pricePerSlot;
	}
	
	/**
	 * set the price per slot of the service
	 * @param pricePerSlot - price per slot of the service
	 */
	public void setPricePerSlot(int pricePerSlot) {
		this.pricePerSlot = pricePerSlot;
	}
	
	/**
	 * returns the details of the service such as the id, title and price per slot
	 * @return the details of the service
	 */
	@Override
	public String toString() {
		return String.format("| %-20d | %-20s | QAR %-16d |", id, title.toUpperCase(), pricePerSlot);
	}
	
}
