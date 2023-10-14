package SehhaApp;

/**
 * the display menu of the application
 * @author Josh, Marcus, Rami, Meteb
 * created 5/3/2023
 * @version 5/19/2023
 *
 */
public class DisplayMenu {
	
	private final String ANSI_BOLD = "\u001B[1m";
	private final String ANSI_RESET = "\u001B[0m";
	
	/**
	 * displays the main menu of the application
	 */
	public void displayMenu() {
	    System.out.println("============================================================================================");
	    System.out.println(ANSI_BOLD + "  	Sehha Hospital Reception Management System" + ANSI_RESET);
	    System.out.println("    	[1] Patient Functions");
	    System.out.println("    	[2] Service Functions");
	    System.out.println("    	[3] Slot Functions");
	    System.out.println("    	[4] Data List");
	    System.out.println("    	[5] Other Information");
	    System.out.println("");
	    System.out.println("    	[0] Exit");
	    System.out.println("============================================================================================\n");
	}
	
	/**
	 * displays the menu for the patient functions of the application
	 */
	public void displayPatientFunctions() {
	    System.out.println("============================================================================================");
	    System.out.println(ANSI_BOLD + "  	Sehha Hospital Reception Management System: Patient Functions" + ANSI_RESET);
	    System.out.println("    	[1] Add a patient");
	    System.out.println("    	[2] Delete a patient");
	    System.out.println("    	[3] Modify patient information");
	    System.out.println("    	[4] Find a patient");
	    System.out.println("");
	    System.out.println("      	[0] Back");
	    System.out.println("============================================================================================\n");
	}
	
	/**
	 * displays the menu for the service functions of the application
	 */
	public void displayServiceFunctions() {
	    System.out.println("============================================================================================");
	    System.out.println(ANSI_BOLD + "  	Sehha Hospital Reception Management System: Service Functions" + ANSI_RESET);
	    System.out.println("    	[1] Add a service");
	    System.out.println("    	[2] Delete a service");
	    System.out.println("    	[3] Modify a service");
	    System.out.println("    	[4] Find a service");
	    System.out.println("    	[5] Assign a service to a slot");
	    System.out.println("");
	    System.out.println("      	[0] Back");
	    System.out.println("============================================================================================\n");
	}
	
	/**
	 * displays the menu for the slot functions of the application
	 */
	public void displaySlotFunctions() {
	    System.out.println("============================================================================================");
	    System.out.println(ANSI_BOLD + "  	Sehha Hospital Reception Management System: Slot Functions" + ANSI_RESET);
	    System.out.println("    	[1] Add an empty slot");
	    System.out.println("    	[2] Delete a slot");
	    System.out.println("    	[3] Reserve a slot");
	    System.out.println("");
	    System.out.println("    	[0] Back");
	    System.out.println("============================================================================================\n");
	}
	
	/**
	 * displays the menu for the data list functions of the application
	 */
	public void displayDataList() {
	    System.out.println("============================================================================================");
	    System.out.println(ANSI_BOLD + "  	Sehha Hospital Reception Management System: Data List" + ANSI_RESET);
	    System.out.println("    	[1] Show all patients");
	    System.out.println("    	[2] Show all services");
	    System.out.println("    	[3] Show all slots");
	    System.out.println("");
	    System.out.println("    	[0] Back");
	    System.out.println("============================================================================================\n");
	}
	
   /**
    * displays the menu for the rest of the functions of the application
    */
	public void displayOtherInformation() {
        System.out.println("============================================================================================");
        System.out.println(ANSI_BOLD + "  	Sehha Hospital Reception Management System: Other Information" + ANSI_RESET);
        System.out.println("    	[1] Find slots by date");
        System.out.println("    	[2] Get available slots");
        System.out.println("    	[3] Get available slots per service by date");
        System.out.println("    	[4] Slot availability");
        System.out.println("    	[5] Get service slots status");
        System.out.println("    	[6] Get slots per service");
        System.out.println("        [7] Print the invoice of a patient");
        System.out.println("");
        System.out.println("    	[0] Back");
        System.out.println("============================================================================================\n");
    }
    
    /**
     * displays the menu when modifying the patient
     * @param pid - id of the patient being modified
     */
	public void displayModifyPatient(long pid) {
    	System.out.println("============================================================================================");
        System.out.println(ANSI_BOLD + "  	Patient Functions: Modify Patient Details for Patient ID " + pid + ANSI_RESET);
        System.out.println("    	[1] Modify patient name");
        System.out.println("    	[2] Modify patient residency type");
        System.out.println("");
        System.out.println("    	[0] Back");
        System.out.println("============================================================================================\n");
    }
    
    /**
     * displays the menu when modifying the service
     * @param serviceID - id of the service being modified
     */
	public void displayModifyService(int serviceID) {
    	System.out.println("============================================================================================");
        System.out.println(ANSI_BOLD + "  	Service Functions: Modify Service Details for Service ID " + serviceID + ANSI_RESET);
        System.out.println("    	[1] Modify service title");
        System.out.println("    	[2] Modify price per slot");
        System.out.println("");
        System.out.println("    	[0] Back");
        System.out.println("============================================================================================\n");
    }


}
