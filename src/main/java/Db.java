import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import beans.Admin;
import beans.Allocation;
import beans.Status;
import beans.TeacherAllocation;
import beans.Login;
import beans.StudentMark;
import beans.Student;
import beans.Teacher;
import beans.TeacherMark;


public class Db {

	private static Db instance;

	String url = "jdbc:mysql://localhost:3306/test";
	String dbusername = "root";
	String dbpassword = "12345678";
	Connection connection;

	private Db() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, dbusername, dbpassword);

		} catch (Exception e) {
			System.out.println("\n Error : " + e.getMessage());
		}

	}

	public static Db getInstance() {

		if (instance == null) {
			instance = new Db();
		}
		return instance;
	}

	public Login fetchLogin(String username, String password) {

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM login WHERE username=? AND password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getString(6));
			}

			return null;

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return null;
	}

	// Student Portal

	public Student fetchStudent(String id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT student.*,class.class_name,department.dep_name  FROM student join class on class.class_id  = student.class_id JOIN department on department.dep_id = class.dep_id WHERE student_id = ?;");
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return new Student(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return null;
	}

	public List<StudentMark> fetchMarksByStudent(Integer id) {
		try {
			ArrayList<StudentMark> marks = new ArrayList<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT mark.score,subject.*  FROM mark INNER JOIN course on course.course_id  = mark.course_id  INNER JOIN subject ON subject.sub_id = course.sub_id WHERE mark.student_id = ?;");
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				marks.add(new StudentMark(rs.getInt(2), rs.getString(3), rs.getInt(1), rs.getInt(4)));
			}
			return marks;

		} catch (Exception e) {
			return null;
		}
	}

	// Teacher Portal

	public Teacher fetchTeacher(Integer id) {

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT teacher.*,department.dep_name  FROM teacher INNER JOIN department on department.dep_id = teacher.dep_id WHERE teacher_id = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				return new Teacher(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
			}
			return null;

		} catch (Exception e) {
			return null;
		}
	}

	public List<TeacherAllocation> fetachAllocations(Integer teacherId) {

		List<TeacherAllocation> allocations = new ArrayList<>();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection.prepareStatement(
					"SELECT allocation.alloc_id ,course.course_id,class.class_id ,class.class_name ,subject.sub_id,subject.sub_name FROM allocation\n"
							+ "INNER JOIN course ON course.course_id = allocation.course_id \n"
							+ "INNER JOIN class ON class.class_id = course.class_id \n"
							+ "INNER JOIN subject ON subject.sub_id = course.sub_id \n" + "WHERE teacher_id = ?;");
			preparedStatement.setInt(1, teacherId);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				allocations.add(new TeacherAllocation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getInt(5), rs.getString(6)));
			}
			return allocations;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchStudentsByClassId(Integer classId) {
		try {
			Map<Integer, String> students = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT student_id,name FROM student WHERE class_id = ?;");
			preparedStatement.setInt(1, classId);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				students.put(rs.getInt(1), rs.getString(2));
			}
			return students;

		} catch (Exception e) {
			return null;
		}
	}
	

	public Map<Integer, String> fetchFilteredStudents(Integer classId,Integer courseId) {
		try {
			Map<Integer, String> students = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT student_id,name,email FROM student WHERE class_id = ? AND student_id NOT IN (SELECT mark.student_id FROM mark INNER JOIN course ON course.course_id = mark.course_id INNER JOIN subject ON subject.sub_id = course.sub_id WHERE course.course_id = ?);");
			preparedStatement.setInt(1, classId);
			preparedStatement.setInt(2, courseId);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				students.put(rs.getInt(1), rs.getString(2));
			}
			return students;

		} catch (Exception e) {
			return null;
		}
	}
	


	public Status insertMark(TeacherMark mark) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO mark(student_id,course_id,score) VALUES(?,?,?)");
			preparedStatement.setInt(1, mark.studentId());
			preparedStatement.setInt(2, mark.courseId());
			preparedStatement.setInt(3, mark.score());

			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() == 1) {
				return new Status(true, "");
			} else {
				return new Status(false, "Something went wrong");
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
	}

	public Admin fetchAdmin(int adminId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM admin WHERE admin_id = ?;");
			preparedStatement.setInt(1, adminId);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return null;
	}

	public Map<Integer, String> fetchClasses() {
		try {
			Map<Integer, String> classes = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT class_id,class_name FROM class ORDER BY class_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				classes.put(rs.getInt(1), rs.getString(2));
			}
			return classes;

		} catch (Exception e) {
			return null;
		}
	}
	
	public Map<Integer, String> fetchClasseswithDepartments() {
		try {
			Map<Integer, String> classes = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT class_id,class_name,department.dep_name FROM class INNER JOIN department ON department.dep_id = class.dep_id ORDER BY class.class_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				classes.put(rs.getInt(1), rs.getString(2)+","+rs.getString(3));
			}
			return classes;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchDepartments() {
		try {
			Map<Integer, String> departments = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT dep_id,dep_name FROM department ORDER BY dep_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				departments.put(rs.getInt(1), rs.getString(2));
			}
			return departments;

		} catch (Exception e) {
			return null;
		}
	}

	public Integer insertLogin(Login login) {
		try {
			Integer loginId = -1;
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO login(username,password,role_id,phone,email) VALUES(?,?,?,?,?)");

			preparedStatement.setString(1, login.username());
			preparedStatement.setString(2, login.password());
			preparedStatement.setInt(3, login.roleId());
			preparedStatement.setString(4, login.phone());
			preparedStatement.setString(5, login.email());

			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() != 1) {
				return -1;
			}

			preparedStatement = connection.prepareStatement("SELECT user_id FROM login WHERE username=?");
			preparedStatement.setString(1, login.username());

			System.out.println(preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				loginId = rs.getInt(1);
				return loginId;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return -1;
	}

	public Status insertAdmin(Admin admin) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admin VALUES(?,?,?,?)");

			preparedStatement.setInt(1, admin.adminId());
			preparedStatement.setString(2, admin.name());
			preparedStatement.setString(3, admin.email());
			preparedStatement.setString(4, admin.phone());
			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() > 0) {
				return new Status(true, "");
			} else {
				throw new SQLException();
			}

		} catch (SQLException e) {

			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM login WHERE user_id  = ?;");
				preparedStatement.setInt(1, admin.adminId());

				preparedStatement.executeUpdate();

			} catch (SQLException e1) {
				System.out.println("\n rollback login : " + e.getMessage());
				e1.printStackTrace();
			}

			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
	}

	public Status insertStudent(Student student) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?)");

			preparedStatement.setInt(1, student.studentId());
			preparedStatement.setInt(2, student.classId());
			preparedStatement.setString(3, student.name());
			preparedStatement.setString(4, student.email());
			preparedStatement.setString(5, student.phone());
			preparedStatement.setString(6, student.dob());
			preparedStatement.setString(7, student.gender());
			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() > 0) {
				return new Status(true, "");
			} else {
				throw new SQLException();
			}

		} catch (SQLException e) {

			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM login WHERE user_id  = ?;");
				preparedStatement.setInt(1, student.studentId());

				preparedStatement.executeUpdate();

			} catch (SQLException e1) {
				System.out.println("\n rollback login : " + e.getMessage());
				e1.printStackTrace();
			}

			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
	}

	public Status insertTeacher(Teacher teacher) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO teacher VALUES(?,?,?,?,?,?,?,?)");

			preparedStatement.setInt(1, teacher.teacherId());
			preparedStatement.setInt(2, teacher.departmentId());
			preparedStatement.setString(3, teacher.name());
			preparedStatement.setString(4, teacher.email());
			preparedStatement.setString(5, teacher.phone());
			preparedStatement.setString(6, teacher.joinDate());
			preparedStatement.setString(7, teacher.gender());
			preparedStatement.setString(8, teacher.qualification());
			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() > 0) {
				return new Status(true, "");
			} else {
				throw new SQLException();
			}

		} catch (SQLException e) {

			try {
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM login WHERE user_id  = ?;");
				preparedStatement.setInt(1, teacher.teacherId());

				preparedStatement.executeUpdate();

			} catch (SQLException e1) {
				System.out.println("\n rollback login : " + e.getMessage());
				e1.printStackTrace();
			}

			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
	}

	public Map<Integer, String> fetchTeachers() {
		try {
			Map<Integer, String> teachers = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT teacher.teacher_id,teacher.name,department.dep_name FROM teacher INNER JOIN department ON department.dep_id = teacher.dep_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				teachers.put(rs.getInt(1), rs.getString(2) + " - " + rs.getString(3));
			}
			return teachers;

		} catch (Exception e) {
			return null;
		}
	}

	// For Allocation based of dep_id
	public Map<String, String> fetchTeachersWithDepartmentId() {
		try {
			Map<String, String> teachers = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT teacher.teacher_id,department.dep_id,teacher.name,department.dep_name FROM teacher INNER JOIN department ON department.dep_id = teacher.dep_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				teachers.put(rs.getInt(1) + "," + rs.getInt(2), rs.getString(3) + " - " + rs.getString(4));
			}
			return teachers;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchStudents() {
		try {
			Map<Integer, String> students = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT student.student_id,student.name,class.class_name,department.dep_name FROM student INNER JOIN class ON class.class_id = student.class_id  INNER JOIN department ON department.dep_id = class.dep_id ORDER BY student.student_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				students.put(rs.getInt(1), rs.getString(2) + "," + rs.getString(3) + "," + rs.getString(4));
			}
			return students;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchAdmins() {
		try {
			Map<Integer, String> admins = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT admin_id,name FROM admin ORDER BY admin_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				admins.put(rs.getInt(1), rs.getString(2));
			}
			return admins;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchCourses() {
		try {
			Map<Integer, String> courses = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT course.course_id,class.class_name,subject.sub_name FROM course INNER JOIN class ON class.class_id = course.class_id INNER JOIN subject ON subject.sub_id = course.sub_id ORDER BY course.course_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				courses.put(rs.getInt(1), rs.getString(2) + " - " + rs.getString(3));
			}
			return courses;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchFilteredCourses(int departmentId,int teacherId) {
		try {
			Map<Integer, String> courses = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT course.course_id,class.class_name,subject.sub_name FROM course\n"
					+ "INNER JOIN class ON class.class_id = course.class_id\n"
					+ "INNER JOIN subject ON subject.sub_id = course.sub_id\n"
					+ "INNER JOIN department ON department.dep_id = class.dep_id\n"
					+ "WHERE department.dep_id = ? AND course.course_id NOT IN (SELECT allocation.course_id FROM allocation WHERE allocation.teacher_id = ?)");
			preparedStatement.setInt(1, departmentId);
			preparedStatement.setInt(2, teacherId);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				courses.put(rs.getInt(1), rs.getString(2) + " - " + rs.getString(3));
			}
			return courses;

		} catch (Exception e) {
			return null;
		}
	}

	public Status insertAllocation(Integer teacherId, Integer courseId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO allocation(teacher_id, course_id) VALUES(?,?)");

			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, courseId);

			if (preparedStatement.executeUpdate() > 0) {
				return new Status(true, "");
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
		return new Status(false, "Something went wrong");
	}

	public Map<Integer, String> fetchSubjects() {
		try {
			Map<Integer, String> subjects = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT sub_id,sub_name FROM subject ORDER BY sub_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				subjects.put(rs.getInt(1), rs.getString(2));
			}
			return subjects;

		} catch (Exception e) {
			return null;
		}
	}
	

	public Map<Integer, String> fetchFilteredSubjects(Integer classId) {
		try {
			Map<Integer, String> subjects = new LinkedHashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT sub_id,sub_name FROM subject WHERE sub_id NOT IN (SELECT sub_id FROM course WHERE class_id = ?);");
			preparedStatement.setInt(1, classId);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				subjects.put(rs.getInt(1), rs.getString(2));
			}
			return subjects;

		} catch (Exception e) {
			return null;
		}
	}

	public Status insertCourse(Integer classId, Integer subjectId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO course(class_id, sub_id) VALUES(?,?)");

			preparedStatement.setInt(1, classId);
			preparedStatement.setInt(2, subjectId);

			if (preparedStatement.executeUpdate() > 0) {
				return new Status(true, "");
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
		return new Status(false, "Something went wrong");
	}

	public Status insertEntity(String entity, String name, Integer departmentId, Integer total) {
		try {
			PreparedStatement preparedStatement;

			System.out.println(entity + " in DB");

			if (entity.equals("Class")) {

				preparedStatement = connection.prepareStatement("INSERT INTO class(class_name,dep_id) VALUES(?,?)");

				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, departmentId);

			} else if (entity.equals("Subject")) {
				preparedStatement = connection.prepareStatement("INSERT INTO subject(sub_name,total) VALUES(?,?)");

				preparedStatement.setString(1, name);
				preparedStatement.setInt(2, total);
			} else {
				preparedStatement = connection.prepareStatement("INSERT INTO department(dep_name) VALUES(?)");

				preparedStatement.setString(1, name);

			}

			if (preparedStatement.executeUpdate() > 0) {
				return new Status(true, "");
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			return new Status(false, e.getMessage());
		}
		return new Status(false, "Something went wrong");
	}

	public List<Allocation> fetachAllAllocations() {

		List<Allocation> allocations = new ArrayList<>();
		PreparedStatement preparedStatement;

		try {
			preparedStatement = connection.prepareStatement(
					"SELECT allocation.alloc_id ,course.course_id,teacher.teacher_id,teacher.name,department.dep_name,class.class_id ,class.class_name ,subject.sub_id,subject.sub_name FROM allocation\n"
							+ "INNER JOIN teacher ON teacher.teacher_id = allocation.teacher_id\n"
							+ "INNER JOIN department ON teacher.dep_id = department.dep_id \n"
							+ "INNER JOIN course ON course.course_id = allocation.course_id \n"
							+ "INNER JOIN class ON class.class_id = course.class_id \n"
							+ "INNER JOIN subject ON subject.sub_id = course.sub_id\n"
							+ "ORDER BY allocation.alloc_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				allocations.add(new Allocation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getString(9)));
			}
			return allocations;

		} catch (Exception e) {
			return null;
		}
	}

	public String getMD5Hash(String password) {
		try {
			MessageDigest m= MessageDigest.getInstance("MD5");
			m.reset();
			m.update(password.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}

}
