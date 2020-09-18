import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {
    private String name;      // imie
    private String surname;   // nazwisko
    Behaviour behaviour;      // zachowanie
    private int nrStudenta;
    Subject subject;          // przedmiot
    private int grade;        // ocena


    public void setFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void setNrStudenta(int nrStudenta) {
        this.nrStudenta = nrStudenta;
    }

    public int getNrStudenta() {
        return nrStudenta;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }



    private static Map<Subject, ArrayList> ocena = new HashMap<>();
    private static ArrayList<Integer> listGradeMAT = new ArrayList<Integer>();
    private static ArrayList<Integer> listGradeBIO = new ArrayList<Integer>();
    private static ArrayList<Integer> listGradeFIZ = new ArrayList<Integer>();
    private static ArrayList<Integer> listGradeJPO = new ArrayList<Integer>();
    private static ArrayList<Integer> listGradeJANG = new ArrayList<Integer>();
    private static ArrayList<Integer> listGradeCHE = new ArrayList<Integer>();
    public void setListGrades(){

        switch(getSubject()){
            case MATEMATYKA -> {
                listGradeMAT.add(getGrade());
                ocena.put(subject,listGradeMAT);
                break;
            }
            case BIOLOGIA -> {
                listGradeBIO.add(getGrade());
                ocena.put(subject,listGradeBIO);
                break;
            }
            case FIZYKA -> {
                listGradeFIZ.add(getGrade());
                ocena.put(subject,listGradeFIZ);
                break;
            }
            case JPOLSKI-> {
                listGradeJPO.add(getGrade());
                ocena.put(subject,listGradeJPO);
                break;
            }
            case JANGIELSKI -> {
                listGradeJANG.add(getGrade());
                ocena.put(subject,listGradeJANG);
                break;
            }
            case CHEMIA -> {
                listGradeCHE.add(getGrade());
                ocena.put(subject,listGradeCHE);
                break;
            }

        }
    }
    public Map<Subject, ArrayList> getListGrades(){
        return ocena;
    }
}
