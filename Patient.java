package SehhaApp;
import java.io.Serializable;

/**
 * The patient class
 * @author Josh, Marcus, Rami, Meteb
 *created 5/3/2023
 *@version 5/19/2023
 */
public class Patient implements Serializable{
	private static final long serialVersionUID = -1730326740703257522L;
	/**
	 * id of the patient
	 */
	private long pid;
	/**
	 * name of the patient
	 */
	private String name;
	/**
	 * residency type of the patient
	 */
	private ResidencyType residencyType;
	
	/**
	 * non-parameterized constructor of Patient class
	 */
	public Patient() {}
	
	/**
	 * instantiates the id, name and residency type of the patient
	 * @param pid - the id of the patient
	 * @param name -  the name of the patient
	 * @param residencyType - the residency type of the patient
	 */
	public Patient(long pid, String name, ResidencyType residencyType) {
		setPid(pid);
		setName(name);
		setResidencyType(residencyType);
	}
	
	/**
	 * get the id of the patient
	 * @return the id of the patient
	 */
	public long getPid() {
		return pid;
	}
	/**
	 * sets the id of the patient
	 * @param pid - the id of the patent
	 */
	public void setPid(long pid) {
		this.pid = pid;
	}
	
	/**
	 * get the name of the patient
	 * @return the name of the patient
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the name of the patient
	 * @param name - the name of the patient
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * get the residency type of the patient
	 * @return the residency type of the patient
	 */
	public ResidencyType getResidencyType() {
		return residencyType;
	}
	
	/**
	 * sets the residency type of the patient
	 * @param residencyType - the residency type of the patient
	 */
	public void setResidencyType(ResidencyType residencyType) {
		this.residencyType = residencyType;
	}
	
	/**
	 * returns the details of the patient such as their id, name and residency type
	 * @return the details of the patient
	 */
	@Override
	public String toString() {
		return String.format("| %-20d | %-20s | %-20s |%n", pid, name, residencyType.equals(ResidencyType.RESIDENT) ? "RESIDENT" : "VISITOR");
	}
}
