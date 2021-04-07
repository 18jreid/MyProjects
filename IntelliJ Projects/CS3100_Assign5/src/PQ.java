import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Iterator;

public class PQ {
    public static void main(String[] args) {

        PriorityQueue<Student> pq = new PriorityQueue<Student>(1, new ComparePQ());

        Student student1 = new Student("Jake","Lauritsen",1);
        Student student2 = new Student("Shubham","S",2);
        Student student3 = new Student("Brandon","H",3);
        Student student4 = new Student("Brandon","S",4);
        Student student5 = new Student("Christian","L",5);
        Student student6 = new Student("Christian","L",6);
        pq.add(student1);
        pq.add(student2);
        pq.add(student3);
        pq.add(student4);
        pq.add(student5);
        pq.add(student6);
        Iterator<Student> pqIterator = pq.iterator();
        while (pqIterator.hasNext()) {
            System.out.println(pqIterator.next().firstName+" "+pqIterator.next().lastName+", "+pqIterator.next().aNumber);
            pqIterator.remove();
        }
     }
}

class ComparePQ implements Comparator<Student> {
    
    public int compare(Student s1, Student s2) {
                System.out.println("Comparing");
                System.out.printf(s1.firstName+" "+s1.lastName+", "+s1.aNumber +" and "+s2.firstName+" "+s2.lastName+", "+s2.aNumber);
                int a = s2.firstName.compareTo(s1.firstName);
                int b = s2.lastName.compareTo(s1.lastName);
                int c = s2.aNumber - s1.aNumber;
                if(a == 0 && b ==0){
                    System.out.println("Equal first name and last name");
                    return c;
                }
                else if (a==0 && b!=0){
                    System.out.println("Equal first name and different last name");
                    return b;
                }
                 System.out.println("different first name");
                return a;
                    
    }
}
