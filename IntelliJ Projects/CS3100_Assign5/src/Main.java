import java.util.*;
import java.lang.*;

class Main{
    public static void main(String[]args){
        Student student1 = new Student("Jake","Lauritsen",1);
        Student student2 = new Student("Shubham","S",2);
        Student student3 = new Student("Brandon","H",3);
        Student student4 = new Student("Brandon","S",4);
        Student student5 = new Student("Christian","L",5);
        Student student6 = new Student("Christian","L",6);

        List<Student> studentList = Arrays.asList(new Student[]{student1,student2,student3,student4,student5,student6});
        
        Collections.shuffle(studentList);
        
        System.out.println("Shuffled student list\n");
        for(Student student:studentList)
            System.out.println(student.firstName+" "+student.lastName+", "+student.aNumber);

        Collections.sort(studentList, new Comparator<Student>() {
           public int compare(Student s1, Student s2) {
                    int a = s2.firstName.compareTo(s1.firstName);
                    int b = s2.lastName.compareTo(s1.lastName);
                    int c = s2.aNumber - s1.aNumber;
                    if(a == 0 && b ==0)
                        return c;
                    else if (a==0 && b!=0)
                        return b;
                    return a;
                        
                }
        });
        
        System.out.println("\nSorted student list");
        for(Student student:studentList)
            System.out.println(student.firstName+" "+student.lastName+", "+student.aNumber);

        
  
    }
}