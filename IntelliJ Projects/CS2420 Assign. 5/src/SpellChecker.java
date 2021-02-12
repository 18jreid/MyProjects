import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
    public static void main(String[]  args) throws FileNotFoundException {
        ArrayList<String> dictionary = new ArrayList<>();

        final FileInputStream dictionaryInputStream = new FileInputStream("dictionary.txt");
        Scanner dictionaryScanner = new Scanner(dictionaryInputStream);

        while (dictionaryScanner.hasNextLine()) {
            dictionary.add(dictionaryScanner.nextLine());
        }

        ArrayList<String> testDictionary = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            testDictionary.add(dictionary.get(i));
        }

        CuckooHash cuckooHash = new CuckooHash(testDictionary);

        cuckooHash.print();
    }
}
