import java.util.Scanner;

public class CLI {

    public String[] getArgsFromCLI() {
        Scanner scanner = new Scanner(System.in);
        String[] args = new String[3];

        System.out.println("Enter command (ENCRYPT/DECRYPT/BRUTE_FORCE):");
        args[0] = scanner.nextLine();

        System.out.println("Enter file path:");
        args[1] = scanner.nextLine();

        System.out.println("Enter key:");
        args[2] = scanner.nextLine();

        return args;
    }
}

