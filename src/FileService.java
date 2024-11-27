import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    public String readFile(String filePath){
        StringBuilder text = new StringBuilder();
        try{
            Files.lines(Paths.get(filePath)).forEach(line -> text.append(line).append("\n"));
        } catch (IOException e){
            System.err.println("Error reading file: " + e.getMessage());
        }
        return text.toString();
    }

    public void writeFile(String filePath, String text) {
        try {
            Files.write(Paths.get(filePath), text.getBytes());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public String generateOutputFileName(String inputFile, String command) {
        String outputFileName = inputFile;
        if (command.equals("ENCRYPT")) {
            outputFileName = inputFile.replace(".txt", "[ENCRYPTED].txt");
        } else if (command.equals("DECRYPT")) {
            outputFileName = inputFile.replace(".txt", "[DECRYPTED].txt");
        }
        return outputFileName;
    }
}

