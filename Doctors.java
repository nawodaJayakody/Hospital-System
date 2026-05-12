import java.sql.*;
import java.util.Scanner;

public class Doctors {
    private Connection connection;

    public Doctors(Connection connection) {
        this.connection = connection;
    }

    public void adddoctors() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the doctors name:");
        String doctorname = scanner.nextLine();
        System.out.println("Enter the doctor's specialization:");
        String doctorspecialization = scanner.nextLine();
        String query = "INSERT INTO doctors(name, specialization) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, doctorname);// Seat 1 = Name

            preparedStatement.setString(2, doctorspecialization); // Seat 2 = specialization

            preparedStatement.executeUpdate();

            System.out.println("Success! Doctor is now in the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewDoctors() {
        String query = "SELECT * FROM doctors";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("------------------------- DOCTORS LIST -------------------------");
            System.out.println("****************************************************************");
            System.out.println("|  Doctors ID |             Name           |    Specialization  |");
            System.out.println("****************************************************************");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.println("ID: " + id + " | Name: " + name + " | Specialization: " + specialization + "|" );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
