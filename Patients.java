import java.sql.*;
import java.util.Scanner;

public class Patients {
    private Connection connection;

    public Patients(Connection connection) {
        this.connection = connection;
    }

    public void addpatient() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the patient name:");
        String patientname = scanner.nextLine();
        System.out.println("Enter the age of the patient:");
        int patientage = scanner.nextInt();
        System.out.println("Enter the patient Gender:");
        String patientgender = scanner.nextLine();
        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, patientname); // Seat 1 = Name
            preparedStatement.setInt(2, patientage); // Seat 2 = Age
            preparedStatement.setString(3, patientgender);// Seat 3 = Gender

            preparedStatement.executeUpdate();

            System.out.println("Success! Patient is now in the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewPatients() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery(); // executeQuery(): Used for asking questions
                                                                    // (Select). It returns a ResultSet (the data
                                                                    // itself).
                                                                    // executeUpdate(): Used for changing things
                                                                    // (Insert, Update, Delete). It returns a number
                                                                    // (how many rows changed).

            System.out.println("--------------------- PATIENT LIST -----------------------------");
            System.out.println("****************************************************************");
            System.out.println("|  Patient ID |             Name                | Age | Gender |");
            System.out.println("****************************************************************");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.println("ID: " + id + " | Name: " + name + " | Age: " + age + " | Gender: " + gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}