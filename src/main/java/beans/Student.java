package beans;

public class Student {
	
	Integer id;
	String name;
	String phone;
	Integer age;
	Integer gender;
	Float cgpa;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Float getCgpa() {
		return cgpa;
	}

	public void setCgpa(Float cgpa) {
		this.cgpa = cgpa;
	}

	public Student(Integer id, String name, String phone, Integer age, Integer gender, Float cgpa) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.age = age;
		this.gender = gender;
		this.cgpa = cgpa;
	}
	
	
	
	
}
