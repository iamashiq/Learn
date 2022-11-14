package beans;

public class Student {

	Integer id;
	Integer dep_id;
	String name;
	String email;
	String phone;
	String dob;
	String gender;

	public Student(Integer id, Integer dep_id, String name, String email, String phone, String dob, String gender) {
		super();
		this.id = id;
		this.dep_id = dep_id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDep_id() {
		return dep_id;
	}

	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
