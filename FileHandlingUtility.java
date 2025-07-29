import java.io.*;
import java.util.Scanner;

public class FileHandlingUtility {

    public static void main(String[] args) {
        String fileName = "example.txt"; // Name of the file to be handled
        Scanner scanner = new Scanner(System.in); // Scanner for user input

        while (true) {
            // Display menu options
            System.out.println("\nFile Handling Menu:");
            System.out.println("1. Create & Write to File");
            System.out.println("2. Read File Content");
            System.out.println("3. Append to File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt(); // Get user choice
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    writeFile(fileName, false, scanner); // Create & write
                    break;
                case 2:
                    readFile(fileName); // Read file content
                    break;
                case 3:
                    writeFile(fileName, true, scanner); // Append to file
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    scanner.close(); // Close scanner
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice! Try again."); // Handle invalid input
            }
        }
    }

    // Method to write/create a file
    private static void writeFile(String fileName, boolean append, Scanner scanner) {
        System.out.println("Enter text to write (type 'END' in a new line to finish):");
        StringBuilder content = new StringBuilder(); // To store user input
        String line;

        // Read user input until 'END' is typed
        while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
            content.append(line).append("\n"); // Append line to content
        }

        // Write content to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, append))) {
            writer.write(content.toString()); // Write the content
            System.out.println((append ? "Appended to" : "Created") + " file successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    // Method to read a file
    private static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("\nFile content:");
            String line;
            // Read and print each line of the file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Create the file first.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
