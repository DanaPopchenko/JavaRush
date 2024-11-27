import java.util.List;
import java.util.Set;

public class CaesarCipher {

    public static String encrypt(String text, List<Character> upperCase, List<Character> lowerCase, int key) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (upperCase.contains(c)) {
                int index = (upperCase.indexOf(c) + key) % upperCase.size();
                result.append(upperCase.get(index));
            } else if (lowerCase.contains(c)) {
                int index = (lowerCase.indexOf(c) + key) % lowerCase.size();
                result.append(lowerCase.get(index));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public String decrypt(String text, List<Character> upperCase, List<Character> lowerCase, int key) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (upperCase.contains(c)) {
                int index = (upperCase.indexOf(c) - key + upperCase.size()) % upperCase.size();
                result.append(upperCase.get(index));
            } else if (lowerCase.contains(c)) {
                int index = (lowerCase.indexOf(c) - key + lowerCase.size()) % lowerCase.size();
                result.append(lowerCase.get(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    public static String bruteForceDecrypt(String text, List<Character> upperCase, List<Character> lowerCase, Set<String> dictionary) {
        int maxWordCount = 0;
        String bestMatch = text;

        for (int key = 1; key < 26; key++) {
            StringBuilder decrypted = new StringBuilder();

            for (char c : text.toCharArray()) {
                if (upperCase.contains(c)) {
                    int index = (upperCase.indexOf(c) - key + upperCase.size()) % upperCase.size();
                    decrypted.append(upperCase.get(index));
                } else if (lowerCase.contains(c)) {
                    int index = (lowerCase.indexOf(c) - key + lowerCase.size()) % lowerCase.size();
                    decrypted.append(lowerCase.get(index));
                } else {
                    decrypted.append(c);
                }
            }


            int wordCount = countDictionaryWords(decrypted.toString(), dictionary);
            if (wordCount > maxWordCount) {
                maxWordCount = wordCount;
                bestMatch = decrypted.toString();
            }
        }

        return bestMatch;
    }

    public static int countDictionaryWords(String text, Set<String> dictionary) {
        int count = 0;
        String[] words = text.split("\\W+");
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

}
