
public class Employees {

	String id;
	String name;
	String email;
	String department;

	public Employees(String id, String name, String email, String department) {
		super();
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
