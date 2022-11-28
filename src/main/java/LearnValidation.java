import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.validator.EmailValidator;
import org.apache.commons.*;

import beans.Status;

public class LearnValidation {

	private static LearnValidation instance;

	private LearnValidation() {

	}

	public static LearnValidation getInstance() {

		if (instance == null) {
			instance = new LearnValidation();
		}
		return instance;
	}

	public Status vaildateMail(String email) {
		if (EmailValidator.getInstance().isValid(email)) {
			return new Status(true, "");

		} else {
			return new Status(false, "Email format is not valid");
		}

	}

	public Status vaildateInt(String number) {
		
		if (number.matches("[0-9]*"))
		{
			return new Status(true, "");
		}
		else
		{
			return new Status(false, "Number format is not valid, Check for Special characters !");
		}
	}
	
	public Status vaildatePhone(String number) {
		
		if (number.matches("[0-9]*") && number.length() <= 10)
		{
			return new Status(true, "");
		}
		else
		{
			return new Status(false, "Phone format is not valid, Check for Special characters !");
		}
	}
	
	public Status vaildateCleanText(String string) {
		
		if (string.matches("[a-zA-Z]*"))
		{
			return new Status(true, "");
		}
		else
		{
			return new Status(false, "Text format is not valid, Check for Special characters !");
		}
		

	}
	
	
	public Status vaildatePassword(String password) {
		
		if ( password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") )
		{
			return new Status(true, "");
		}
		else
		{
			return new Status(false, "Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character");
		}

	}

	public Status isBeforeToday(String date) {
		Date enteredDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			enteredDate = sdf.parse(date);
		} catch (Exception ex) 
		{}

		Date currentDate = new Date();
		if (enteredDate.before(currentDate)) {
			return new Status(true, "");
		} else {
			return new Status(false, "Date of Birth should be before today!");
		}

	}
	
	
	
}
