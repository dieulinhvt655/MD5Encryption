import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Scanner object for reading input from keyboard
        Scanner sc = new Scanner(System.in);

        // AccountManager object to manage accounts
        AccountManager manager = new AccountManager();

        // Main loop to display menu continuously
        while (true) {
            try {
                // Display menu options
                System.out.println("\n===== MENU =====");
                System.out.println("1. Add account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose option: ");

                // Read user's menu choice
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    // Option 1: Add new account
                    case 1:
                        try {
                            String username;

                            // Validate username: not empty and not duplicated
                            while (true) {
                                System.out.print("Username: ");
                                username = sc.nextLine();

                                if (username == null || username.trim().isEmpty()) {
                                    System.out.println("Username cannot be empty");
                                    continue;
                                }

                                if (manager.isUsernameExist(username)) {
                                    System.out.println("Username already exists");
                                } else {
                                    break; // Valid username
                                }
                            }

                            // Input password (must not be empty)
                            String password = Util.InputNotEmpty("Password cannot be empty");

                            // Input user's full name
                            System.out.print("Name: ");
                            String name = sc.nextLine();

                            // Input and validate phone number
                            String phone = Util.InputPhone();

                            // Input and validate email
                            String email = Util.InputEmail();

                            // Input address
                            System.out.print("Address: ");
                            String address = sc.nextLine();

                            // Input and validate date of birth
                            String dob = Util.InputDate();

                            // Add account to system
                            int id = manager.addAccount(username, password, name, phone, email, address, dob);

                            // Display success message
                            System.out.println("Add account success. ID = " + id);

                        } catch (Exception e) {
                            // Display error message if something goes wrong
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    // Option 2: Login
                    case 2:
                        // Input login credentials
                        System.out.print("Username: ");
                        String u = sc.nextLine();

                        System.out.print("Password: ");
                        String p = sc.nextLine();

                        // Perform login
                        if (!manager.login(u, p)) {
                            System.out.println("Login failed!");
                        }
                        break;

                    // Option 3: Exit program
                    case 3:
                        System.out.println("Exit program.");
                        System.exit(0);
                        break;

                    default:
                        throw new Exception("Invalid option!");
                }
            } catch (Exception e) {
                // Invalid menu option
                System.out.println("Invalid option!");
            }
        }
    }
}
