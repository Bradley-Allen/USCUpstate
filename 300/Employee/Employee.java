/**
 * Lab 4: Comparable (Employee.java)
 * @author Bradley Allen
 * Reads employees.txt and sorts the most
 * cost efficient employees in ascending order.
 */

import java.io.*;
import java.text.NumberFormat;
import java.util.*;

public class Employee implements Comparable<Employee> {
	/** Initialization */
	private String name;
	private double payrate;
	private int widgets;
	NumberFormat fmt = NumberFormat.getCurrencyInstance();
	
	/** Constructor for Employee */
	public Employee(String n, double pr, int w) {
		name = n;
		payrate = pr;
		widgets = w;
	}
	
	/** Main method, reads in file and creates Employee objects */
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(new File("employees.txt"));	
		ArrayList<Employee> employeeList = new ArrayList<>();
		while (scan.hasNext()) {
			String name = scan.next();
			double payrate = scan.nextDouble();
			int widgets = scan.nextInt();
			employeeList.add(new Employee(name, payrate, widgets));
		}
		Collections.sort(employeeList);
		for (Employee e: employeeList) {
			System.out.println(e);
		}
	}
	
	/** ToString method, handles printing */
	public String toString() {
		return (name + ":     \t" + fmt.format(payrate) + " for " + widgets + " widgets.");
	}
	
	/** CompareTo method, handles comparing */
	public int compareTo(Employee other) {
		return (int) ((1000*payrate/widgets)-(1000*other.payrate/other.widgets));
	}
}