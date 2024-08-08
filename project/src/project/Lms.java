package project;
import java.sql.*;
import java.util.Scanner;

public class Lms {
	
	    public static final String URL = "jdbc:mysql://localhost:3306/library";
	    public static final String USER = "root";
	    public static final String PASSWORD = "Swathika@2003";
	    public static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            System.out.println("Connected to the database.");

	            int choice;
	            do {
	                System.out.println("\nLibrary Management System operations:");
	                System.out.println("1. Add Book");
	                System.out.println("2. Add Member");
	                System.out.println("3. Add Transaction");
	                System.out.println("4. Update Book");
	             
	                System.out.print("Enter your choice: ");
	                choice = scanner.nextInt();

	                switch (choice) {
	                    case 1:
	                        addBook(connection);
	                        break;
	                    case 2:
	                        addMember(connection);
	                        break;
	                    case 3:
	                        addTransaction(connection);
	                        break;
	                    case 4:
	                        updateBook(connection);
	                        break;
	                 
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            } while (choice != 4);
	        } catch (SQLException e) {
	            System.out.println("SQL Exception: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            scanner.close();
	        }
	    }

	    public static void addBook(Connection connection) throws SQLException {
	        System.out.println("Enter title: ");
	        scanner.nextLine(); 
	        String title = scanner.nextLine();
	        System.out.println("Enter author: ");
	        String author = scanner.nextLine();
	        System.out.println("Enter ISBN: ");
	        String isbn = scanner.nextLine();
	        System.out.println("Enter quantity: ");
	        int quantity = scanner.nextInt();

	        String sql = "INSERT INTO book (title, author, isbn, quantity) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, title);
	            statement.setString(2, author);
	            statement.setString(3, isbn);
	            statement.setInt(4, quantity);
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("A new book was inserted successfully.");
	            }
	        }
	    }

	    public static void addMember(Connection connection) throws SQLException {
	        System.out.println("Enter name: ");
	        scanner.nextLine(); 
	        String name = scanner.nextLine();
	        System.out.println("Enter email: ");
	        String email = scanner.nextLine();

	        String sql = "INSERT INTO member (name, email) VALUES (?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setString(1, name);
	            statement.setString(2, email);
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("A new member was inserted successfully.");
	            }
	        }
	    }

	    public static void addTransaction(Connection connection) throws SQLException {
	        System.out.println("Enter book ID: ");
	        int bookId = scanner.nextInt();
	        System.out.println("Enter member ID: ");
	        int memberId = scanner.nextInt();
	        scanner.nextLine(); 
	        System.out.println("Enter date borrowed (YYYY-MM-DD): ");
	        String dateBorrowed = scanner.nextLine();

	        String sql = "INSERT INTO transaction (book_id, member_id, date_borrowed) VALUES (?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, bookId);
	            statement.setInt(2, memberId);
	            statement.setString(3, dateBorrowed);
	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("A new transaction was inserted successfully.");
	            }
	        }
	    }

	    public static void updateBook(Connection connection) throws SQLException {
	        System.out.println("Enter book ID: ");
	        int bookId = scanner.nextInt();
	        System.out.println("Enter new quantity: ");
	        int quantity = scanner.nextInt();

	        String sql = "UPDATE book SET quantity = ? WHERE id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, quantity);
	            statement.setInt(2, bookId);
	            int rowsUpdated = statement.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Book quantity updated successfully.");
	            } else {
	                System.out.println("No book found with the specified ID.");
	            }
	        }
	    }

	}

