import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.util.Base64;

public class ImageEncryption {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 128;

    public static void main(String[] args) {
        try {
            // Generate a random key and initialization vector (IV)
            SecretKey secretKey = generateSecretKey();
            IvParameterSpec iv = generateIV();

            // Encrypt the image
            encryptImage("E:\\LearnSmasher Internship\\sample.jpg", "E:\\LearnSmasher Internship\\image.enc", secretKey, iv);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    private static IvParameterSpec generateIV() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static void encryptImage(String inputImagePath, String outputImagePath, SecretKey secretKey, IvParameterSpec iv)
            throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        // Read the image into bytes
        byte[] inputBytes = ImageUtils.readImage(inputImagePath);

        // Encrypt the image
        byte[] encryptedBytes = cipher.doFinal(inputBytes);


        // Before saving the encrypted bytes to a file, create the directory if it doesn't exist
Path outputDir = Paths.get(outputImagePath).getParent();
if (outputDir != null && !Files.exists(outputDir)) {
    Files.createDirectories(outputDir);
}


        // Save the encrypted bytes to a file
        ImageUtils.saveToFile(encryptedBytes, outputImagePath);
    }
}
