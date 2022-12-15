
public class Bugs {

	String bugAppname;
	String bugDatefound;
	String bugPlatform;
	String bugProbdesc;
	String bugStatus;
	String bugEmpID;
	String bugID;
	
	public Bugs(String bugAppname, String bugDatefound, String bugPlatform,  String bugProbdesc, String bugStatus, String bugID, String bugEmpID) {
		
		
		this.bugAppname = bugAppname;
		this.bugDatefound = bugDatefound;
		this.bugPlatform = bugPlatform;
		this.bugProbdesc = bugProbdesc;
		this.bugStatus = bugStatus;
		this.bugID = bugID;
		this.bugEmpID = bugEmpID;
		
	}

	public String getBugAppname() {
		return bugAppname;
	}

	public void setBugAppname(String bugAppname) {
		this.bugAppname = bugAppname;
	}

	public String getBugDatefound() {
		return bugDatefound;
	}

	public void setBugDatefound(String bugDatefound) {
		this.bugDatefound = bugDatefound;
	}

	public String getBugPlatform() {
		return bugPlatform;
	}

	public void setBugPlatform(String bugPlatform) {
		this.bugPlatform = bugPlatform;
	}

	public String getBugProbdesc() {
		return bugProbdesc;
	}

	public void setBugProbdesc(String bugProbdesc) {
		this.bugProbdesc = bugProbdesc;
	}

	public String getBugStatus() {
		return bugStatus;
	}

	public void setBugStatus(String bugStatus) {
		this.bugStatus = bugStatus;
	}

	public String getbugID() {
		return bugID;
	}

	public void setbugID(String bugID) {
		this.bugID = bugID;
	}
	
	
	public String getBugEmpID() {
		return bugEmpID;
	}

	public void setBugEmpID(String bugEmpID) {
		this.bugEmpID = bugEmpID;
	}

}
