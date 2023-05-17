import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class SQLCommands {
	
	/**
	 * Add a course, given course code and title
	 */
	public void add_course(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		System.out.println("Add a course");
		System.out.println("Please input course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		System.out.println("Please input course title: ");
		String courseTitle = keyboard.nextLine().trim();
		String query = "SELECT code FROM Course WHERE code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			System.out.println("Course already exists");
			return;
		}
		query = "INSERT INTO Course (code, title) VALUES ('" + courseCode + "', '" + courseTitle + "')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("A new course is added.");
	}
	
	/**
	 * Delete a course and remove all registered students, given course code
	 */
	public void delete_course(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		System.out.println("Delete a course");
		System.out.println("Please input course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		String query = "SELECT code FROM Course WHERE code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			try {
				query = "DELETE FROM Registered WHERE code = '" + courseCode + "'";
				st.executeUpdate(query);
				query = "DELETE FROM Course WHERE code ='" + courseCode + "'";
				st.executeUpdate(query);
				System.out.println("The course has been deleted.");
			} catch (SQLException e) {
				System.out.println("Message: " + e.getMessage());
			}
		}
		else {
			System.out.println("The course given does not exist.");
		}
		rs.close();
		st.close();
		return;
	}
	
	/**
	 * Add a student, given SSN, student name, address, and major
	 */
	public void add_student(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		System.out.println("Add a student");
		System.out.println("Please input student SSN: ");
		int ssn;
		try {
			ssn = Integer.parseInt(keyboard.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("SSN was not an integer.");
			return;
		}
		String query = "SELECT ssn FROM Student WHERE ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			System.out.println("Student already exists");
			return;
		}
		System.out.println("Please input student name: ");
		String name = keyboard.nextLine().trim();
		if (name.matches("[0-9]+")) {
			System.out.println("Student name cannot be numeric.");
			return;
		}
		if (name.length() > 50) {
			System.out.println("Student name cannot more than 50 characters.");
			return;
		}
		System.out.println("Please input student address: ");
		String address = keyboard.nextLine().trim();
		if (address.length() > 100) {
			System.out.println("Student address cannot more than 100 characters.");
			return;
		}
		System.out.println("Please input student major: ");
		String major = keyboard.nextLine().trim();
		if (major.matches("[0-9]+")) {
			System.out.println("Student major cannot be numeric.");
			return;
		}
		if (major.length() > 10) {
			System.out.println("Student major cannot more than 10 characters.");
			return;
		}
		query = "INSERT INTO Student (ssn, name, address, major) VALUES ('" + ssn + "', '" + name + "', '" + address + "', '" + major + "')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
		
		}
		rs.close();
		st.close();
		System.out.println("A new student is added.");
	}
	
	/**
	 * Delete a student and all the student's registered courses, given SSN
	 */
	public void delete_student(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		System.out.println("Delete a student");
		System.out.println("Please input student SSN: ");
		int ssn;
		try {
			ssn = Integer.parseInt(keyboard.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("SSN was not an integer.");
			return;
		}
		String query = "SELECT ssn FROM Student WHERE ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (rs.next()) {
			try {
				query = "DELETE FROM Registered WHERE ssn = '" + ssn + "'";
				st.executeUpdate(query);
				query = "DELETE FROM Student WHERE ssn = '" + ssn + "'";
				st.executeUpdate(query);
				System.out.println("Student successfully deleted.");
			} catch (SQLException e) {
				System.out.println("Message: " + e.getMessage());
			}
			return;
		}
		else {
			System.out.println("Student does not exist.");
		}
		rs.close();
		st.close();
	}
	
	/**
	 * Register a course for a student given SSN, course code, year, and semester
	 */
	public void register_course(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		System.out.println("Register a course");
		// Input SSN and checks
		System.out.println("Please input student SSN: ");
		int ssn;
		try {
			ssn = Integer.parseInt(keyboard.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("SSN must be an integer.");
			// SSN was not an integer
			return;
		}
		String query = "SELECT ssn FROM Student WHERE ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Student does not exist.");
			return;
		}
		
		// Input course code and checks
		System.out.println("Please input course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		query = "SELECT code FROM Course WHERE code = '" + courseCode + "'";
		rs = st.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Course does not exist.");
			return;
		}
		
		// Input year and semester and checks
		System.out.println("Please input year: ");
		int courseYear;
		try {
			courseYear = Integer.parseInt(keyboard.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Course year was not valid.");
			// Course year was not a year
			return;
		}
		System.out.println("Please input semester: ");
		String courseSemester = keyboard.nextLine().trim();
		if (courseSemester.matches("[0-9]+")) {
			System.out.println("Semester was not valid.");
			return;
		}
		courseSemester = courseSemester.substring(0,1).toUpperCase() + courseSemester.substring(1).toLowerCase();
		
		query = "SELECT ssn FROM Registered WHERE ssn = '" + ssn + "' AND code = '" + courseCode + "' AND year = '" + courseYear + "' AND semester = '" + courseSemester + "'";
		rs = st.executeQuery(query);
		if (rs.next()) {
			System.out.println("Student already registered for that course.");
			// Student SSN was registered for course already
			return;
		}
		query = "INSERT INTO Registered (ssn, code, year, semester) VALUES ('" + ssn + "', '" + courseCode + "', '" + courseYear + "', '" + courseSemester + "')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
			// Error while inserting into registered
			return;
		}
		rs.close();
		st.close();
		System.out.println("Student successfully registered for course.");
	}
	
	/**
	 * Prints all courses (course code, year, and semester) a student is registered to, given a SSN
	 */
	public void check_registration(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		System.out.println("Check student registration");
		System.out.println("Please input student SSN: ");
		int ssn;
		try {
			ssn = Integer.parseInt(keyboard.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("SSN must be an integer.");
			// SSN was not an integer
			return;
		}
		String query = "SELECT ssn FROM Student WHERE ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Student does not exist.");
			return;
		}
		query = "SELECT code, year, semester FROM Registered WHERE ssn = '" + ssn + "'";
		rs = st.executeQuery(query);
		while (rs.next()) {
			String code = rs.getString("code");
			String year = rs.getString("year");
			String semester = rs.getString("semester");
			System.out.println("Code: " + code + "\tYear: " + year + "\tSemester: " + semester);
		}
		rs.close();
		st.close();
	}
	
	/**
	 * Uploads grades to Registered table for the given course, year, and semester
	 */
	public void upload_grades(Connection conn, Scanner keyboard) throws SQLException, IOException {
		Statement st = conn.createStatement();
		Statement st2 = conn.createStatement();
		System.out.println("Upload grades");
		
		// Input course code and checks
		System.out.println("Please input course code: ");
		String courseCode = keyboard.nextLine().toUpperCase().trim();
		String query = "SELECT code FROM Course WHERE code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		if (!rs.next()) {
			System.out.println("Course does not exist.");
			return;
		}
				
		// Input year and semester and checks
		System.out.println("Please input year: ");
		int courseYear;
		try {
			courseYear = Integer.parseInt(keyboard.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Course year was not valid.");
			// Course year was not a year
			return;
		}
		System.out.println("Please input semester: ");
		String courseSemester = keyboard.nextLine().toUpperCase().trim();
		if (courseSemester.matches("[0-9]+")) {
			System.out.println("Semester was not valid.");
			return;
		}
		courseSemester = courseSemester.substring(0,1).toUpperCase() + courseSemester.substring(1).toLowerCase();
		query = "SELECT ssn FROM Registered WHERE code = '" + courseCode + "' AND year = '" + courseYear + "' AND semester = '" + courseSemester + "'";
		rs = st.executeQuery(query);
		boolean gradesEntered = false;
		while (rs.next()) {
			String ssn = rs.getString("ssn");
			System.out.println("SSN: " + ssn);
			System.out.print("Enter grade: ");
			String grade = keyboard.nextLine().toUpperCase().trim();
			try {
				String query2 = "UPDATE Registered SET grade = '" + grade + "' WHERE ssn = '" + ssn + "' AND code = '" + courseCode + "' AND year = '" + courseYear + "' AND semester = '" + courseSemester + "'";
				st2.executeUpdate(query2);
			} catch (SQLException e) {
				System.out.println("Message: " + e.getMessage());
				return;
			}
			gradesEntered = true;
		}
		rs.close();
		st.close();
		st2.close();
		if (gradesEntered) {
			System.out.println("Grades have been uploaded.");
		} else {
			System.out.println("Course has no registered students.");
		}
		
	}
	
	/**
	 * Debug function, prints all content from Registered table
	 */
	public void show_registered(Connection conn) throws SQLException, IOException {
		try {
			Statement st = conn.createStatement();
			String query = "SELECT * FROM Registered";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				 String ssn = rs.getString("ssn");
				 String code = rs.getString("code");
				 String year = rs.getString("year");
				 String semester = rs.getString("semester");
				 String grade = rs.getString("grade");
			     System.out.println("SSN: " + ssn + "\tCode: " + code + "\tYear: " + year + "\tSemester: " + semester + "\tGrade: " + grade);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
		
		}
	}
	
	/**
	 * Debug function, prints all content from Student table
	 */
	public void show_students(Connection conn) throws SQLException, IOException {
		try {
			Statement st = conn.createStatement();
			String query = "SELECT * FROM Student";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String ssn = rs.getString("ssn");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String major = rs.getString("major");
				System.out.println("SSN: " + ssn + "\tName: " + name + "\tAddress: " + address + "\tMajor: " + major);
			}
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
		}
	}
	
	/**
	 * Debug function, prints all content from Course table
	 */
	public void show_courses(Connection conn) throws SQLException, IOException {
		try {
			Statement st = conn.createStatement();
			String query = "select * from Course";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) 
			{
				 String code = rs.getString("code");
				 String title = rs.getString("title");
			     System.out.println("Code: " + code + "\tTitle: " + title);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			System.out.println("Message: " + e.getMessage());
		}
	}	
}

