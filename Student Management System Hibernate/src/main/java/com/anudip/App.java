package com.anudip;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

 
public class App 
{
    public static void main( String[] args )
    {
    	SessionFactory factory = new Configuration().configure().buildSessionFactory();
		StudentDAO dao = new StudentDAO(factory);
		Session session = factory.openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		System.out.println("**Welcome to Student management System**\n");
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter your choice:");
			System.out.println("1. Add Student\n" + "2. Show all Students\n" + "3. Show Student based on id\n"
					+ "4. Update the Student\n" + "5. Delete the Student\n" + "6. Exit from App");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				Student stu = new Student();
				System.out.println("Enter ID:");
				String id = sc.next();
				System.out.println("Enter First name:");
				String fname = sc.next();
				System.out.println("Enter Last name:");
				String lname = sc.next();
				System.out.print("Enter date of birth (yyyy-mm-dd): ");
				String dateInput = sc.next();
				// Date sqlDate = parseDate(dateInput);
				System.out.println("Enter gender:");
				String gender = sc.next();
				System.out.println("Enter email:");
				String email = sc.next();
				System.out.println("Enter phone number:");
				String phone = sc.next();
				System.out.println("Enter Marks:");
				int marks = sc.nextInt();
				stu.setsId(id);
				stu.setfName(fname);
				stu.setlName(lname);
				stu.setDob(Date.valueOf(dateInput));
				stu.setGender(gender);
				stu.setEmail(email);
				stu.setPhone(phone);
				stu.setMarks(marks);
				dao.insertEntity(stu);
				break;
			case 2:

				List<Student> records = dao.fetchAllRecords();

				// Print or use the records as needed
				for (Student record : records) {
					System.out.println(record);
				}
				break;
			case 3:
				System.out.println("Enter id to show the details of the student:");
				String stuid=sc.next();
				Student entity = dao.getById(stuid); 
				if (entity != null) {
					System.out.println("Entity found: " + entity);
				} else {
					System.out.println("No entity found with sId S101");
				}
				break;
			case 4:
				System.out.println("Enter id to update the details of the student:");
				String stuid1 = sc.next();
				System.out.println("Enter the updated marks of the student:");
				int marksnew=sc.nextInt();
				dao.updateMarksById(stuid1, marksnew);
				break;
			case 5:
				System.out.println("Enter id to delete the details of the student:");
				String id1 = sc.next();
				dao.deleteById(id1);
				break;
			case 6:
				System.out.println("Thank you to use the App...");
				System.exit(0);
			default:
				System.out.println("Enter valid choice!");
			}

		} while (true);
    }
}
