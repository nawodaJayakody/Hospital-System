import java.util.Scanner;
import java.sql.*;

public class hospitalSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "7572";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ← needs its own catch
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);

            Patients patient = new Patients(connection);
            Doctors doctor = new Doctors(connection);

            while (true) {
                System.out.println("--- HOSPITAL MANAGEMENT SYSTEM ---");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                if (choice == 1) {
                    patient.addpatient();
                } else if (choice == 2) {
                    patient.viewPatients();
                } else if (choice == 3) {
                    doctor.viewDoctors();
                } else if (choice == 4) {
                    System.out.println("Thank you for using the system!");
                    break;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (ClassNotFoundException e) {
            // JAR is missing or not linked properly in VS Code
            System.out.println("MySQL Driver JAR not found! Check your lib folder.");
            e.printStackTrace();

        } catch (SQLException e) {
            // Driver found but DB connection failed (wrong password, MySQL not running,
            // etc.)
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
    }
}