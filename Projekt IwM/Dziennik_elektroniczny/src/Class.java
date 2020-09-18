import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Class implements Serializable {
private List<Student> students = new ArrayList<Student>();
private String nrKlasy;
private int nr2Klasy;
    int nrx = 1;
    public void addStudent(String imie,String nazwisko){
    Student student = new Student();
    student.setFullName(imie, nazwisko);
    student.setNrStudenta(nrx++);
    students.add(student);
}
public void setClass(String nrKlasy){
    this.nrKlasy = nrKlasy;
}
public void setNrClass(int nr2Klasy){
        this.nr2Klasy = nr2Klasy;
    }
public String getClassa(){
    return nrKlasy;
}
public int getNrClass(){
        return nr2Klasy;
}

    public void addStudents(){
        System.out.println("Ilu chcesz dodać uczniów? ");
    int registerSize = scannerInt();
    for (int i = 0; i < registerSize; i++){
       // addStudent();
    }
    }
    public void printAllStudents() {
    int x = 1;
    for (Student student : students){
        System.out.println("Nr. " + student.getNrStudenta() + " " + student.getFullName());
    }

    }
    public Student getStudent(int nr) {
        return students.get(nr);
    }
    public String printStudent(int nr){
        System.out.println("Student " + students.get(nr).getFullName());
        return "Student " + students.get(nr).getFullName();
    }

    public List getList(){
        return students;
    }

    public static String scannerString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Integer scannerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
