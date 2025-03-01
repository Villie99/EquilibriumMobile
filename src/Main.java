import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter nested structure:");
        String input = scanner.nextLine();
        scanner.close();

        // Start parsing from the first level (level 1)
        parseStructure(input, 1, 0);
    }

    // Function to parse the structure
    private static int parseStructure(String input, int level, int index) {
        while (index < input.length()) {
            char current = input.charAt(index);

            if (current == '[') {
                // If we encounter '[', it means we go one level deeper
                index = parseStructure(input, level + 1, index + 1);
            } else if (Character.isDigit(current)) {
                // Parse the integer
                int num = 0;
                while (index < input.length() && Character.isDigit(input.charAt(index))) {
                    num = num * 10 + (input.charAt(index) - '0');
                    index++;
                }
                System.out.println("Level " + level + ": " + num);
            } else if (current == ']') {
                // If we encounter ']', it means we go one level up
                return index + 1;
            } else {
                // For commas or other characters, just move to the next character
                index++;
            }
        }
        return index;
    }
}
