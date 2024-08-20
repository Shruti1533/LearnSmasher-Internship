import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class hash {

    private static final int OUTPUT_SIZE = 32;  // Setting the desired output size 
    public static byte[] hashData(String data) {
        try {

            // Creating a SHA-256 MessageDigest instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Updating the digest with the input data
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            // Truncating or adding the hash value as needed to achieve the desired output size
            byte[] truncatedHash = new byte[OUTPUT_SIZE];
            System.arraycopy(hash, 0, truncatedHash, 0, OUTPUT_SIZE);

            return truncatedHash;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();  // Handling the exception
            return null;
        }
    }

    public static void main(String[] args) {
        
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the data to be hashed: ");
        String s=sc.nextLine();

        // Generate the hash value
        byte[] hashValue = hashData(s);

        // Print the input data and hash value
        System.out.println("Input Data: " + s);
        System.out.print("Hash Value: ");
        for (byte b : hashValue) {
            System.out.printf("%02x", b);
        }
    }
}

