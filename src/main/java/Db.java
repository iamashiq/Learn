
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Admin;
import beans.Allocation;
import beans.Login;
import beans.StudentMark;
import beans.Student;
import beans.Teacher;
import beans.TeacherMark;

public class Db {

	public static Db instance;

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
				return new Login(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
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
					"SELECT mark.score,subject.*  FROM mark INNER JOIN course on course.course_id  = mark.course_id  INNER JOIN subject ON subject.sub_id = course.sub_id WHERE mark.student_id = ?");
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

	public List<Allocation> fetachAllocations(Integer teacherId) {

		List<Allocation> allocations = new ArrayList<>();
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
				allocations.add(new Allocation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5),
						rs.getString(6)));
			}
			return allocations;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchStudentsByClassId(Integer classId) {
		try {
			Map<Integer, String> students = new HashMap<>();
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

	public boolean insertMark(TeacherMark mark) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO mark(student_id,course_id,score) VALUES(?,?,?)");
			preparedStatement.setInt(1, mark.studentId());
			preparedStatement.setInt(2, mark.courseId());
			preparedStatement.setInt(3, mark.score());

			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() != 1) {
				return false;
			} else {
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			return false;
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
			Map<Integer, String> classes = new HashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT class_id,class_name FROM class;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				classes.put(rs.getInt(1), rs.getString(2));
			}
			return classes;

		} catch (Exception e) {
			return null;
		}
	}

	public Map<Integer, String> fetchDepartments() {
		try {
			Map<Integer, String> departments = new HashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT dep_id,dep_name FROM department;");

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
					.prepareStatement("INSERT INTO login(username,password,role_id) VALUES(?,?,?)");

			preparedStatement.setString(1, login.username());
			preparedStatement.setString(2, login.password());
			preparedStatement.setInt(3, Integer.parseInt(login.role()));

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

	public boolean insertAdmin(Admin admin) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admin VALUES(?,?,?,?)");

			preparedStatement.setInt(1, admin.adminId());
			preparedStatement.setString(2, admin.name());
			preparedStatement.setString(3, admin.email());
			preparedStatement.setString(4, admin.phone());
			System.out.println(preparedStatement.toString());

			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}

	public boolean insertStudent(Student student) {
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
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}

	public boolean insertTeacher(Teacher teacher) {
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
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}

	public Map<Integer, String> fetchTeachers() {
		try {
			Map<Integer, String> teachers = new HashMap<>();
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

	public Map<Integer, String> fetchCourses() {
		try {
			Map<Integer, String> courses = new HashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT course.course_id,class.class_name,subject.sub_name FROM course INNER JOIN class ON class.class_id = course.class_id INNER JOIN subject ON subject.sub_id = course.sub_id;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				courses.put(rs.getInt(1), rs.getString(2) + " - " + rs.getString(3));
			}
			return courses;

		} catch (Exception e) {
			return null;
		}
	}

	public boolean insertAllocation(Integer teacherId, Integer courseId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO allocation(teacher_id, course_id) VALUES(?,?)");

			preparedStatement.setInt(1, teacherId);
			preparedStatement.setInt(2, courseId);

			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}

	public Map<Integer, String> fetchSubjects() {
		try {
			Map<Integer, String> subjects = new HashMap<>();
			// if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT sub_id,sub_name FROM subject;");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				subjects.put(rs.getInt(1), rs.getString(2));
			}
			return subjects;

		} catch (Exception e) {
			return null;
		}
	}

	public boolean insertCourse(Integer classId, Integer subjectId) {
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO course(class_id, sub_id) VALUES(?,?)");

			preparedStatement.setInt(1, classId);
			preparedStatement.setInt(2, subjectId);

			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}

	public boolean insertEntity(String entity, String name, Integer departmentId, Integer total) {
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
				return true;
			}

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}

}
