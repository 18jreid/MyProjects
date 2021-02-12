import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // Grabs Salaries.txt and assigns it to an array, then inserts each salary into myDynamicMedian class
        ArrayList<Integer> salariesArrayList = new ArrayList<>();
        FileInputStream fc = new FileInputStream("Salaries.txt");
        Scanner sc = new Scanner(fc);

        while (sc.hasNextLine()) {
            if (sc.hasNextInt()) {
                salariesArrayList.add(sc.nextInt());
            }
            sc.nextLine();
        }

        DynamicMedian<Integer> myDynamicMedian = new DynamicMedian<>();
        myDynamicMedian.setPrintCount(25);
        for (Integer salary : salariesArrayList) {
            myDynamicMedian.insert(salary);
        }

        // Prints final Median Salary
        DecimalFormat format = new DecimalFormat("$###,###,###");
        System.out.println("The final Median Salary is: " + format.format(myDynamicMedian.currMedian));
    }
}
