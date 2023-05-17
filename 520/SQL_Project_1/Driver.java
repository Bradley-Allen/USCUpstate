import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class Driver {


    public static void main(String[] args) throws SQLException, IOException
	{
    	//connect to database server	
                try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver"); //Load the Oracle JDBC driver
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Could not load the driver");
		}
		try //connect to oracle and validate the user and passwd
		{
			String url="jdbc:oracle:thin:@mcs.uscupstate.edu:1521:xe";
			Connection conn = DriverManager.getConnection(url,"c##testb","t123456");//connect oracle

		    //print menu
			Scanner scan = new Scanner(System.in);
			SQLCommands SQL = new SQLCommands();
			int command;
			String input;
	        boolean keepGoing = true;
	        while (keepGoing == true)
	        {
	            printmenu();
	            input = scan.nextLine();
	            command = Integer.parseInt(input);
				
				switch(command)
				{
					case 1: SQL.add_course(conn, scan); break;
					case 2: SQL.delete_course(conn, scan); break;
					case 3: SQL.add_student(conn, scan); break;
					case 4: SQL.delete_student(conn, scan); break;
					case 5: SQL.register_course(conn, scan); break;
					case 6: SQL.check_registration(conn, scan); break;
					case 7: SQL.upload_grades(conn, scan); break;
					case 8: 
						System.out.println("The Session has been ended, Thank you!");
	               	    keepGoing = false;
	               	    conn.close(); //close database connection   
						break;
					case 9: SQL.show_courses(conn); break;
					case 10: SQL.show_registered(conn); break;
					case 11: SQL.show_students(conn); break;
				}    		            			
		    }
		}
		catch (SQLException ex)
		{
			System.out.println("An error occurred when connecting to the database server.");
			ex.printStackTrace();
		}				
  }
    public static void printmenu()
    {
    		System.out.println();
    		System.out.println("*********************************************************************");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("***              Welcome to Online Registration System            ***");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("*********************************************************************");
	      	System.out.println("1. Add a course");
	      	System.out.println("2. Delete a course");
	      	System.out.println("3. Add a student");
	      	System.out.println("4. Delete a student");
	      	System.out.println("5. Register a course");
	      	System.out.println("6. Check student registration");
	      	System.out.println("7. Upload grades");
	      	System.out.println("8. Quit ");
	      	// Debug functions
	      	// System.out.println("9. Show all courses");
	      	// System.out.println("10. Show all registered");
	      	// System.out.println("11. Show all students");
	      	// ---------------
	      	System.out.println();
	      	
	      	System.out.println("Please choose an option");
    }
}


