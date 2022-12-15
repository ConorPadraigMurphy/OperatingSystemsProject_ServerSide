import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

//Reminder, code must stopped with red square before starting the program again

public class ServerThreadProj extends Thread {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;

	private String empID;
	private String empName;
	private String empEmail;
	private String empDept;

	private String logID;
	private String logEmail;
	private String searchID;
	
	
	private String bugAppname;
	private String bugDatefound;
	private String bugPlatform;
	private String bugProbdesc;
	private String bugStatus;
	private String bugID;
	private String bugEmpID;

	userDatabase rec;
	BugDatabase bug;

	public ServerThreadProj(Socket s, userDatabase r, BugDatabase b) {
		socket = s;
		rec = r;
		bug = b;

		rec.ReadFromFile();
		bug.ReadFromFile();
	}

	public void run() {
		// 3. get Input and Output streams
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			do {
				// Register an Employee
				sendMessage("----- Main Menu -----\n" + "1. Add an Employee\n" + "2. Login to Bug Tracker");
				message = (String) in.readObject();

				if (message.equalsIgnoreCase("1")) {
					// Registers employee information
					sendMessage("Employee ID: ");
					empID = (String) in.readObject();

					sendMessage("Employee Name: ");
					empName = (String) in.readObject();

					sendMessage("Employee Email: ");
					empEmail = (String) in.readObject();

					sendMessage("Employee Department: ");
					empDept = (String) in.readObject();

					// Adds employee to your list
					rec.addEmp(empID, empName, empEmail, empDept);
					rec.WriteToFile();

				}

				if (message.equalsIgnoreCase("2")) {

					sendMessage("Employee ID: ");
					logID = (String) in.readObject();

					sendMessage("Employee Email: ");
					logEmail = (String) in.readObject();
					boolean log = false;
					LinkedList<Employees> empRecords = rec.getEmployees();
					
					for (int i = 0; i< empRecords.size(); i++) {
						Employees emp = empRecords.get(i);
						
						
						if (emp.id.equalsIgnoreCase(logID)) {
							if (emp.email.equalsIgnoreCase(logEmail)) {
								log = true;
							}else
								sendMessage("incorrect user login details");
						}
					
					}
					if(log == true) {
						sendMessage("Sucessful Login");
						while(true) {
							
							sendMessage("----- Bug Menu -----\n" + "1. Add a Bug Report\n"
									+ "2. Assign the bug to a Employee\n" + "3. View bugs not assigned to Employees\n"
									+ "4. Update bug status\n" + "5. Exit the system");
							//1.0
							message = (String) in.readObject();

							if (message.equalsIgnoreCase("1")) {
								// Registers employee information
								//1.1
								regBug();
							}

							else if (message.equalsIgnoreCase("2")) {
								//sendMessage("Assign a bug");
								assignBug();
									
								
								
							} else if (message.equalsIgnoreCase("3")) {
							//Lists all bugs where empgugid = null
									sendMessage(bug.getBugs());
									
							} else if (message.equalsIgnoreCase("4")) {
								//sendMessage("Update bug status");
								updateStatus();	
								
							} else if (message.equalsIgnoreCase("5")) {
								sendMessage("You exited the system");
								System.exit(1);
							}
						}
					}
	
				}
				
				sendMessage("\n1.Back to main menu" + "\n2.Exit Application");
				message = (String) in.readObject();
				if (message.equals("2")) {
					sendMessage("You exited the system");
					System.exit(1);
				}

			} while (message.equalsIgnoreCase("1"));

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void updateStatus() throws IOException, ClassNotFoundException {
		sendMessage("What is the ID of the bug you would like to update the status of?\n"+"Bug ID: ");
		searchID=(String) in.readObject();
		
		sendMessage("What is the current status of the bug?\n"+"Bug Status: ");
		bugStatus = (String) in.readObject();
		
		int searchBugID = 0;
		
		LinkedList<Bugs> bugRecords = bug.getBugRecords();
		for (int i = 0;i<bugRecords.size(); i++) {
			Bugs bl = bugRecords.get(i);
			
			if(bl.getbugID().equals(searchID)) {
				searchBugID = 1;
				bl.setBugStatus(bugStatus);
			}
		}
		if (searchBugID == 0) {
			sendMessage("Unfortuantley the Bug ID you have entered does not exists, or you have entered it incorrectly\n"+"Please try again");
		}
		bug.WriteToFile();
	}

	private void assignBug() throws IOException, ClassNotFoundException {
		sendMessage("What is the ID of the bug you would like to assign a Employee to?\n"+"Bug ID: ");
		searchID=(String) in.readObject();
		
		sendMessage("What is the ID of the employee you would like to assign to the bug?\n"+"Employee ID: ");
		bugEmpID = (String) in.readObject();
		
		int searchBugID = 0;
		
		LinkedList<Bugs> bugRecords = bug.getBugRecords();
		for (int i = 0;i<bugRecords.size(); i++) {
			Bugs bl = bugRecords.get(i);
			
			if(bl.getbugID().equals(searchID)) {
				searchBugID = 1;
				bl.setBugEmpID(bugEmpID);
			}
		}
		if (searchBugID == 0) {
			sendMessage("Unfortuantley the Bug ID you have entered does not exists, or you have entered it incorrectly\n"+"Please try again");
		}
		bug.WriteToFile();
	}

	private void regBug() throws IOException, ClassNotFoundException {
		sendMessage("App Name: ");
		bugAppname = (String) in.readObject();

		sendMessage("Date Found: ");
		bugDatefound = (String) in.readObject();

		sendMessage("Platform: ");
		bugPlatform = (String) in.readObject();

		sendMessage("Bug Description: ");
		bugProbdesc = (String) in.readObject();

		sendMessage("Status: ");
		bugStatus = (String) in.readObject();

		sendMessage("Bug ID: ");
		bugID = (String) in.readObject();
		
		// Adds bug information to a list
		bug.addBug(bugAppname, bugDatefound, bugPlatform, bugProbdesc, bugStatus, bugID, bugEmpID);
		bug.WriteToFile();
	}

	
	public void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
