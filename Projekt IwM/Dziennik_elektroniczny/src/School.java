import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


class School implements Serializable {
    private List<Class> klasy = new ArrayList<Class>();
    int x = 1;
    int nrx = 1;

    public void addClass(String nazwa) {
        Class klasa = new Class();
        klasa.setClass(nazwa);
        klasa.setNrClass(nrx++);
        klasy.add(klasa);
        printClass(klasy.size()-1);
    }

    public void addKlasy() {
        int registerSize = scannerInt();
        for (int i = 0; i < registerSize; i++) {
            addClass(scannerString());
        }
    }

    public void printLastClass(){
        printClass(klasy.size()-1);
    }
    public void printAllClass() {
        for (Class klasa : klasy) {
            System.out.println("nr." + klasa.getNrClass() + " " + klasa.getClassa());
        }
    }

    public String printClass(int nr) {
        System.out.println("klasa " + klasy.get(nr).getClassa());
        return "klasa " + klasy.get(nr).getClassa();
    }

    public Class getClass(int nr) {
        return klasy.get(nr);
    }

    public List getList() {
        return klasy;
    }

    public static String scannerString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Integer scannerInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}