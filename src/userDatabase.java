
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class userDatabase {

	private LinkedList<Employees> empRecords;

	public userDatabase() {
		empRecords = new LinkedList<Employees>();
	}

	public synchronized void addEmp(String id, String name, String email, String department) {
		Employees temp = new Employees(id, name, email, department);
		empRecords.add(temp);
	}

	synchronized void WriteToFile() {
		try {
			FileWriter fWrite = new FileWriter("C:\\Users\\conor\\OneDrive\\Desktop\\Files\\OSFILES\\EmpRecords.txt");
			for (int i = 0; i < empRecords.size(); i++) {
				Employees emp = empRecords.get(i);
				fWrite.write(emp.id + "," + emp.name + "," + emp.email + "," + emp.department + "\n");
			}
			fWrite.close();
			System.out.println("Sucessfully saved to file");
		} catch (Exception e) {
			System.out.println("Well that didn't save sucessfully");
			e.printStackTrace();
		}
	}

	synchronized void ReadFromFile() {
		try {
			FileReader fRead = new FileReader("C:\\Users\\conor\\OneDrive\\Desktop\\Files\\OSFILES\\EmpRecords.txt");
			Scanner fReader = new Scanner(fRead);
			while (fReader.hasNextLine()) {
				String info = fReader.nextLine();
				String[] employeesInfo = info.split(",");
				Employees emp = new Employees(employeesInfo[0],employeesInfo[1],employeesInfo[2],employeesInfo[3]);
				
				empRecords.add(emp);
			}
			fRead.close();
			fReader.close();
		} catch (Exception e) {
			System.out.println("Oh dear, an error has occured");
		}
	}
	
	public LinkedList<Employees> getEmployees(){
		return empRecords;
		
	}

}
