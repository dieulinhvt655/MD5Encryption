import java.util.ArrayList;

public class AccountManager {

    // Store all user accounts
    private ArrayList<Account> accounts = new ArrayList<>();

    // Used to automatically generate unique ID for each account
    private int autoId = 1;

    // Add a new account with validation and MD5 encryption
    public int addAccount(String username, String password, String name,
                          String phone, String email, String address, String dob) throws Exception {

        // Validate username: must not be empty
        if (username == null || username.trim().isEmpty())
            throw new Exception("Username cannot be empty");

        // Ensure username is unique
        if (isUsernameExist(username))
            throw new Exception("Username already exists");

        // Validate password
        if (password == null || password.trim().isEmpty())
            throw new Exception("Password cannot be empty");

        // Validate phone number: only digits, length 10–11
        if (!phone.matches("\\d{10,11}"))
            throw new Exception("Phone number must be 10 or 11 digits");

        // Validate email format using utility method
        if (!Util.isValidEmail(email))
            throw new Exception("Invalid email format");

        // Validate date of birth format (dd/MM/yyyy)
        if (!Util.isValidDate(dob))
            throw new Exception("Invalid DOB format (dd/MM/yyyy)");

        // Encrypt password using MD5 before storing
        String md5Password = Util.md5Encrypt(password);

        // Create new Account object with encrypted password
        Account acc = new Account(autoId, username, md5Password,
                name, phone, email, address, dob);

        // Save account to the list
        accounts.add(acc);

        // Return current ID, then increment for the next account
        return autoId++;
    }

    // Login function: authenticate user with MD5 password
    public Boolean login(String username, String password) {

        // Encrypt input password to compare with stored MD5 password
        String md5Password = Util.md5Encrypt(password);

        // Check username and encrypted password against stored accounts
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)
                    && acc.getPassword().equals(md5Password)) {

                // Login successful
                System.out.println("Hello " + username);
                return true;
            }
        }

        // Login failed
        return false;
    }

    // Check if a username already exists
    public boolean isUsernameExist(String username) {
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

