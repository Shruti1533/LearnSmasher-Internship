import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PasswordStrengthChecker {

    public static int strength(String p) {
        int score = 0;

        // Check password length
        int n = p.length();
        if (n >= 10 && n <= 12) {
            score = 1;
        }

        // Check character types
        boolean hasUpper = false, hasLower = false, hasDigit = false;
        for (char b : p.toCharArray()) {
            if (Character.isUpperCase(b)) hasUpper = true;
            if (Character.isLowerCase(b)) hasLower = true;
            if (Character.isDigit(b)) hasDigit = true;
        }

        if (hasUpper && hasLower && hasDigit) {
            score += 3; // Increase score for complexity
        } else if ((hasUpper || hasLower) && hasDigit) {
            score += 2;
        }

        // Check if the password is common via API
        if (isCommonPassword(p)) {
            return 0; // Score 0 if it's a common password
        }

        return score;
    }

    public static boolean isCommonPassword(String password) {
        try {
            // Hash the password using SHA-1
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashString = bytesToHex(hash).toUpperCase();

            // Send a request to the Pwned Passwords API
            URL url = new URL("https://api.pwnedpasswords.com/range/" + hashString.substring(0, 5));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                // Check if the hash appears in the response
                if (line.startsWith(hashString.substring(5))) {
                    return true; // Password is common
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Password is not common
    }

    // Helper method to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the password to check its strength: ");
        String pass = sc.nextLine();

        int s = strength(pass);
        System.out.println("Password strength: " + s + "/10");
    }
}
