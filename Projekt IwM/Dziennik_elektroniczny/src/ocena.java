import java.io.Serializable;

public class ocena implements Serializable {

    int ocena;
    String opis;
    Student student;
    Class klasa;
    Subject przedmiot;
    public ocena(int ocena, Student student, Class klasa, Subject przedmiot, String opis, School szkola, int x, int x3){
        this.student=szkola.getClass(x).getStudent(x3);
        this.klasa=szkola.getClass(x);
        this.ocena=ocena;
        this.opis=opis;
        this.przedmiot=szkola.getClass(x).getStudent(x3).getSubject();
    }
    public String toString(){
        return "\nOcena: " + ocena + "\nPrzedmiot: " + przedmiot + "\nOpis: " + opis + "\nUczeń: " + student.getFullName() + "\nKlasa: " + klasa.getClassa();
    }
}
