import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CuckooHash {
    HashEntry[] table1;
    HashEntry[] table2;
    int TABLE_SIZE = 100;

    public CuckooHash() {
        allocateTables(TABLE_SIZE);
    }

    public CuckooHash(ArrayList<String> dictionary) {
        int tableSize = nextPrime(dictionary.size());
        TABLE_SIZE = tableSize;
        allocateTables(tableSize);

        for (String word : dictionary) {
            insert(word);
        }
    }

    private void insert(String word) {
        System.out.println(word + " tried to insert");
        int location = hash1(word);
        if (table1[location].word.equals("")) {
            table1[location].setWord(word);
        }

        else {
            String bootOut = table1[location].word;
            table1[location].setWord(word);

            int location2 = hash2(bootOut);
            if (table2[location2].word.equals("")) {
                table2[location2].setWord(bootOut);
            }

            else {
                String bootOut2 = table2[location2].word;
                table2[location2].setWord(bootOut);

                if (!findNextEmtpy(bootOut2, hash1(bootOut2), table1, 1)) {
                    if (!findNextEmtpy(bootOut2, hash2(bootOut2), table2, 1)) {
                        resizeTables(TABLE_SIZE * 2);
                        insert(bootOut2);
                    }
                }
            }
        }

    }

    // Hash used for table 1
    private int hash1(String word) {
        char[] ch = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            ch[i] = word.charAt(i);
        }

        int hashVal = 0;
        for (char c : ch) {
            hashVal += (int) c;
        }

        hashVal = hashVal % table1.length;
        if( hashVal < 0 )
            hashVal += table1.length;

        return hashVal;
    }

    // Hash used for table 2
    private int hash2(String word) {
        char[] ch = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            ch[i] = word.charAt(i);
        }

        int hashVal = 0;
        for (char c : ch) {
            hashVal += (int) c;
        }

        hashVal = (hashVal / 11) % table1.length;
        if( hashVal < 0 )
            hashVal += table1.length;

        return hashVal;
    }

    private int wrapper(int location, int depth) {
        int num = location + depth;
        if  (num > TABLE_SIZE) {
            num = num - TABLE_SIZE;
        }

        return num;
    }

    private boolean findNextEmtpy(String word, int location, HashEntry[] table, int depth) {
        if (table[location].word.equals("")) {
            table[location].word = word;
            return true;
        }
        else {
            if (table.length >= (table.length / 2) + (table.length / 10)) {
                return false;
            }
        }

        int nextLocation = wrapper(location, depth);
        return findNextEmtpy(word, nextLocation, table, depth * depth);
    }

    private void allocateTables(int tableSize) {
        allocateTable1(nextPrime(tableSize));
        allocateTable2(nextPrime(nextPrime(tableSize)));
    }

    private void allocateTable1(int tableSize) {
        HashEntry entry = new HashEntry("");
        table1 = new HashEntry[tableSize];

        Arrays.fill(table1, entry);
    }

    private void allocateTable2(int tableSize) {
        HashEntry entry = new HashEntry("");
        table2 = new HashEntry[tableSize];

        Arrays.fill(table2, entry);
    }

    private void resizeTables(int tableSize) {
        allocateTables(tableSize);
    }

    private static int nextPrime( int n )
    {
        if( n % 2 == 0 )
            n++;

        for( ; !isPrime( n ); n += 2 )
            ;

        return n;
    }

    private static boolean isPrime( int n )
    {
        if( n == 2 || n == 3 )
            return true;

        if( n == 1 || n % 2 == 0 )
            return false;

        for( int i = 3; i * i <= n; i += 2 )
            if( n % i == 0 )
                return false;

        return true;
    }

    public void print() {
        System.out.println("TABLE 1");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.println(table1[i].word);
        }

        System.out.println("TABLE 2");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.println(table2[i].word);
        }
    }
}
