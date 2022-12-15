import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BugDatabase {

	private LinkedList<Bugs> bugRecords;

	public BugDatabase() {
		bugRecords = new LinkedList<Bugs>();
	}
	
	public synchronized void addBug(String bugAppname, String bugDatefound, String bugPlatform,  String bugProbdesc, String bugStatus, String bugID, String bugEmpID) {
		Bugs temp = new Bugs(bugAppname, bugDatefound, bugPlatform,  bugProbdesc, bugStatus, bugID, bugEmpID);
		bugRecords.add(temp);
	}
	
	synchronized void WriteToFile() {
		try {
			FileWriter fWrite = new FileWriter("C:\\Users\\conor\\OneDrive\\Desktop\\Files\\OSFILES\\BugRecords.txt");
			for (int i = 0; i < bugRecords.size(); i++) {
				Bugs bu = bugRecords.get(i);
				fWrite.write(bu.bugAppname + "," + bu.bugDatefound + "," + bu.bugPlatform + "," + bu.bugProbdesc + "," + bu.bugStatus + ","+ bu.bugID + "," + bu.bugEmpID + "\n");
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
			FileReader fRead = new FileReader("C:\\Users\\conor\\OneDrive\\Desktop\\Files\\OSFILES\\BugRecords.txt");
			Scanner fReader = new Scanner(fRead);
			while (fReader.hasNextLine()) {
				String info = fReader.nextLine();
				String[] bugInfo = info.split(",");
				Bugs bu = new Bugs(bugInfo[0],bugInfo[1],bugInfo[2],bugInfo[3],bugInfo[4],bugInfo[5], bugInfo[6]);
				
				bugRecords.add(bu);
			}
			fRead.close();
			fReader.close();
		} catch (Exception e) {
			System.out.println("Oh dear, an error has occured");
		}
	}
	
	public synchronized String getBugs()
	{
		Iterator<Bugs> iterate = bugRecords.iterator();
		Bugs temp;
		String result="";
		while(iterate.hasNext())
		{
			//if(.equalsIgnoreCase(null)) {
				
				temp = iterate.next();
				result = result 
						+"\n-----Bug Details-----"
						+"\nBug Name: "+temp.getBugAppname()
						+"\nDate:"+temp.getBugDatefound()
						+"\nBug Platform: "+temp.getBugPlatform()
						+"\nBug Description: "+temp.bugProbdesc
						+"\nBug Status: "+temp.bugStatus
						+"\nBug ID: "+temp.bugID 
						+"\nAssigned Employee: "+temp.bugEmpID
						+"\n";
			//}
		}
			
		
		return result;
	}
	
	public LinkedList<Bugs> getBugRecords(){
		return bugRecords;
		
	}
	
}
