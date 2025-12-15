import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Util {
    private static final Scanner sc = new Scanner(System.in);

    // Input a non-empty string (used mainly for password)
    // Keep asking until the input is not empty
    public static String InputNotEmpty(String error) {
        System.out.print("Password: ");
        String text = sc.nextLine();

        // Validate: input must not be null or empty
        if (text == null || text.trim().isEmpty()) {
            System.out.println(error);
            // Recursive call to re-enter data
            return Util.InputNotEmpty(error);
        }
        return text;
    }

    // Input and validate phone number (10–11 digits)
    public static String InputPhone() {
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        // Phone number must contain only digits and have length 10 or 11
        if (!phone.matches("\\d{10,11}")) {
            System.out.println("Phone number must be 10 or 11 digits");
            // Ask user to re-enter phone number
            return Util.InputPhone();
        }
        return phone;
    }

    // Input and validate date of birth (dd/MM/yyyy)
    public static String InputDate() {
        System.out.print("DOB (dd/MM/yyyy): ");
        String dob = sc.nextLine();

        // Check date format and logical validity
        if (!isValidDate(dob)) {
            System.out.println("Invalid DOB format (dd/MM/yyyy)");
            // Ask user to re-enter DOB
            return Util.InputDate();
        }
        return dob;
    }

    // Input and validate email address
    public static String InputEmail() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        // Validate email format using regex
        if (!Util.isValidEmail(email)) {
            System.out.println("Invalid email format");
            // Ask user to re-enter email
            return Util.InputEmail();
        }
        return email;
    }

    // Check if an email is valid using Regular Expression
    public static boolean isValidEmail(String email) {
        // Basic email regex pattern
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    // Validate date string with strict format dd/MM/yyyy
    public static boolean isValidDate(String date) {
        try {
            // Create formatter with expected date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            // Disable lenient parsing
            sdf.setLenient(false);

            // Try parsing the date
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            // Parsing failed → invalid date
            return false;
        }
    }

    // Encrypt a string using MD5 hashing algorithm
    public static String md5Encrypt(String input) {
        try {
            // Create MD5 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Generate hash bytes from input string
            byte[] bytes = md.digest(input.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            // Return null if encryption fails
            return null;
        }
    }
}
