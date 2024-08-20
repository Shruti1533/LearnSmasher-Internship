import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtils {

    public static byte[] readImage(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }

    public static void saveToFile(byte[] data, String outputPath) throws IOException {
        Path path = Paths.get(outputPath);
        Files.write(path, data);
    }
}
