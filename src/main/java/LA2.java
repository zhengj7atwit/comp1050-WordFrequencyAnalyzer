import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class LA2 {

    public static String cleanWord(String token) {
        // TODO 1: Convert token to lowercase.
        String lower = token.toLowerCase();
        // TODO 2: Remove all characters that are not English letters a-z.
        StringBuilder cleaned = new StringBuilder();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c >= 'a' && c <= 'z') {
                cleaned.append(c);
            }
        }
        // TODO 3: Return the cleaned word. Punctuation-only tokens return "".
        return cleaned.toString();
    }

    public static int findWord(String word, ArrayList<String> words) {
        // TODO 4: Search the list with a loop.
        // TODO 5: Return the matching index when word is found.
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(word)) {
                return i;
            }
        }
        // TODO 6: Return -1 if the word is not in the list.
        return -1;
    }

    public static String buildReport(String filename) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();

        // TODO 7: Open filename with a Scanner.
        Scanner fileScanner = new Scanner(new File(filename));

        // TODO 8: Read every whitespace-separated token.
        while (fileScanner.hasNext()) {
            String word = cleanWord(fileScanner.next());
            // TODO 9: Clean each token and skip empty cleaned words.
            if (word.isEmpty()) {
                continue;
            }
            // TODO 10: Use findWord to determine whether the word is already stored.
            int index = findWord(word, words);

            // TODO 11: Add a new word/count or increment the existing count.
            if (index == -1) {
                words.add(word);
                counts.add(1);
            } else {
                counts.set(index, counts.get(index) + 1);
            }
        }

        // TODO 12: Close the file Scanner.
        fileScanner.close();

        StringBuilder report = new StringBuilder();
        report.append("Word Frequency Report\n");
        report.append("----------------------\n");

        // TODO 13: Append each word and count in first-appearance order.
        // Required format: word: count
        for (int i = 0; i < words.size(); i++) {
            report.append(words.get(i)).append(": ").append(counts.get(i)).append("\n");
        }

        return report.toString().trim();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the input filename: ");
        String filename = input.nextLine();

        try {
            String report = buildReport(filename);
            // TODO 14: Print report to the console.
            System.out.println(report);

            // TODO 15: Write the same report to output.txt using PrintWriter.
            PrintWriter writer = new PrintWriter("output.txt");

            // TODO 16: Close the PrintWriter.
            writer.close();

            // TODO 17: Print "Report written to output.txt".
            System.out.println("Report written to output.txt");

        } catch (FileNotFoundException exception) {
            // TODO 18: Print the required missing-file error message.
            System.out.println("Error: File not found.");
        }
    }
}
