import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {


        if (args.length == 0) {
            CLI cli = new CLI();
            args = cli.getArgsFromCLI();
        }

        if (args.length != 3) {
            System.err.println("Usage: java -jar myApp.jar <command> <filePath> <key>");
            System.exit(1);
        }

        String command = args[0];
        String filePath = args[1];
        int key = Integer.parseInt(args[2]);

        FileService fileService = new FileService();
        CaesarCipher cipher = new CaesarCipher();

        List<Character> upperCase = new ArrayList<>();
        List<Character> lowerCase = new ArrayList<>();

        for (char c = 'A'; c <= 'Z'; c++) upperCase.add(c);
        for (char c = 'a'; c <= 'z'; c++) lowerCase.add(c);

        Set<String> dictionary = loadEnglishDictionary();

        String text = fileService.readFile(filePath);
        String result = "";

        switch (command.toUpperCase()){
            case "ENCRYPT":
                result  = cipher.encrypt(text, upperCase, lowerCase, key);
                break;
            case "DECRYPT":
                result = cipher.decrypt(text, upperCase, lowerCase, key);
                break;
            case "BRUTE_FORCE":
                result = cipher.bruteForceDecrypt(text, upperCase, lowerCase, dictionary);
                break;
            default:
                System.err.println("Invalid command.");
                System.exit(1);
        }

        String outputFileName = fileService.generateOutputFileName(filePath, command);
        fileService.writeFile(outputFileName, result);
        System.out.println("Output saved to: " + outputFileName);

    }
    
    public static Set<String> loadEnglishDictionary() {
        Set<String> dictionary = new HashSet<>();
        try (Scanner scanner = new Scanner(new File("src/words_alpha.txt"))) {
            while (scanner.hasNext()) {
                dictionary.add(scanner.next().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Error loading dictionary: " + e.getMessage());
        }
        return dictionary;
    }

}
