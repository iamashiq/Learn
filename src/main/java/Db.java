
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Login;
import beans.Student;


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

		}
		catch (Exception e) 
		{
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
				return new Login(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
			}

			return null;

		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return null;
	}

	public Student fetchStudent(String id) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id=? ");
			preparedStatement.setString(1, id);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return new Student(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
			}


		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return null;
	}
	
	public List<Student> fetchStudents(boolean all,Integer id) {
		try {
			ArrayList<Student> allUsers = new ArrayList<>();
			//if(all)
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				allUsers.add(new Student(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7)));
			}
			return allUsers;

		} catch (Exception e) { 
			return null;
		}
	}
	
	public boolean insertRecord(Login login,Student user) {
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO login(username,password,role) VALUES(?,?,?)");

			preparedStatement.setString(1, login.getUsername());
			preparedStatement.setString(2, login.getPassword());
			preparedStatement.setString(3, login.getRole());

			System.out.println(preparedStatement.toString());

			if ( preparedStatement.executeUpdate() != 1)
			{
				return false;
			}
			
			preparedStatement = connection.prepareStatement("SELECT id FROM login WHERE username=?");
			preparedStatement.setString(1, login.getUsername());
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next())
			{
				login.setId(rs.getInt(1));
				System.out.println("User added Id = "+login.getId());
			}
			

			preparedStatement = connection.prepareStatement("INSERT INTO student VALUES(?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, login.getId());
			preparedStatement.setInt(2, user.getDep_id());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setString(6, user.getDob());
			preparedStatement.setString(7, user.getGender());
			
			

			if ( preparedStatement.executeUpdate() == 1)
			{
				return true;
			}
			



		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
		}
		return false;
	}


}
