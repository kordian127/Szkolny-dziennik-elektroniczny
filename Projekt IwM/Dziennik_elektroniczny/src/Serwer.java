import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.*;
import java.io.IOException;
import java.net.Socket;


public class Serwer implements Serializable {

    private static ServerSocket server;
    private static int port = 9876;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        server = new ServerSocket(port);

        System.out.println("Waiting for the client request");

        Socket socket = server.accept();
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


        School szkola = new School();
        while (true) {
            try {
                String pyt1 = "1 - dodanie klasy, 2 - wypisanie wszystkich klas, 3 - wybór klasy";
                oos.writeObject(pyt1);
                oos.flush();
                System.out.println(pyt1);
                int wybor1 = (int) ois.readUnshared();
                System.out.println(wybor1);
                switch (wybor1) {
                    case 1:
                        String pyt2 = "Podaj nazwę klasy";
                        oos.writeObject(pyt2);
                        oos.flush();

                        System.out.println(pyt2);
                        szkola.addClass((String) ois.readUnshared());
                        break;
                    case 2:
                        oos.writeUnshared(szkola.getList());          //wysyla liste
                        oos.flush();
                        szkola.printAllClass();                     // wypisuje w serwerze
                        break;
                    case 3:
                        String pyt3 = "Którą klasę wybrać z listy?";
                        System.out.println(pyt3);
                        oos.writeObject(pyt3);
                        oos.flush();
                        int x = wyborKlasy((int) ois.readUnshared());
                        uczen(szkola, x, oos, ois);
                        break;
                    default:
                        System.out.println("Błąd. Wybierz jeszcze raz.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Błąd. Wybierz jeszcze raz.");
                scannerString();
            }
        }
    }

    public static String scannerString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Integer scannerInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static int wyborKlasy(int x) {
        x = x - 1;
        return x;
    }

    static int wyborUcznia(int x3) {
        System.out.println("Którego ucznia wybierasz?");
        x3 = x3 - 1;
        return x3;
    }

    static void zachowanie(School szkola, int x, int x3, ObjectOutputStream oos, ObjectInputStream ois) throws IOException, InputMismatchException, ClassNotFoundException {

        szkola.getClass(x).printStudent(x3);
        oos.writeUnshared("Zachowanie ucznia: \n" +
                "1.Wzorowe, 2.Bardzo dobre, 3.Dobre, 4.Dostateczne, 5.Dopuszczające, 6.Nieodpowiednie");
        oos.flush();
        System.out.println("Zachowanie ucznia: \n" +
                "1.Wzorowe, 2.Bardzo dobre, 3.Dobre, 4.Dostateczne, 5.Dopuszczające, 6.Nieodpowiednie");
        switch ((Integer) ois.readUnshared()) {
            case 1:
                szkola.getClass(x).getStudent(x3).setBehaviour(Behaviour.WZOROWE);
                break;
            case 2:
                szkola.getClass(x).getStudent(x3).setBehaviour(Behaviour.BDOBRE);
                break;
            case 3:
                szkola.getClass(x).getStudent(x3).setBehaviour(Behaviour.DOBRE);
                break;
            case 4:
                szkola.getClass(x).getStudent(x3).setBehaviour(Behaviour.DOSTATECZNE);
                break;
            case 5:
                szkola.getClass(x).getStudent(x3).setBehaviour(Behaviour.DOPUSZCZAJACE);
                break;
            case 6:
                szkola.getClass(x).getStudent(x3).setBehaviour(Behaviour.NIEODPOWIEDNIE);
                break;
            default:
                oos.writeUnshared("Błąd. Wybierz jeszcze raz.");
                oos.flush();
                System.out.println("Błąd. Wybierz jeszcze raz.");
                break;
        }
        oos.writeUnshared("Zachowanie ucznia: " + szkola.getClass(x).getStudent(x3).getBehaviour());
        oos.flush();
        System.out.println("Zachowanie ucznia: " + szkola.getClass(x).getStudent(x3).getBehaviour());
    }

    static void uczen(School szkola, int x, ObjectOutputStream oos, ObjectInputStream ois) {

        boolean znak = true;
        try {
            while (znak) {
                String wiad1 = "Klasa: " + szkola.printClass(x) + "\nCo chcesz zrobic? \n 1 - dodaj ucznia do klasy, 2 - wypisanie wszystkich uczniów,  3 - wybierz ucznia, 4 - wróć";
                oos.writeObject(wiad1);
                oos.flush();
                System.out.println(wiad1);
                int wybor2 = (int) ois.readUnshared();
                System.out.println(wybor2);
                switch (wybor2) {
                    case 1:
                        String wiad2 = "Podaj imię i nazwisko ucznia";
                        System.out.println(wiad2);
                        oos.writeObject(wiad2);
                        oos.flush();
                        szkola.getClass(x).addStudent((String) ois.readUnshared(),(String) ois.readUnshared());
                        break;
                    case 2:
                        szkola.getClass(x).printAllStudents();
                        oos.writeUnshared(szkola.getClass(x).getList());
                        oos.flush();
                        break;
                    case 3:
                        oos.writeObject("Którego ucznia wybierasz?");
                        oos.flush();
                        int x3 = wyborUcznia((int) ois.readUnshared());
                        boolean znakWyjsc = true;
                        try {
                            while (znakWyjsc) {
                                oos.writeObject("1 - dodaj ocenę, 2 - wystaw zachowanie, 3 - wyświetl oceny ucznia, 4 - wróć");
                                oos.flush();
                                System.out.println("1 - dodaj ocenę, 2 - wystaw zachowanie, 3 - wyświetl oceny ucznia, 4 - wróć");
                                int wybor = (int) ois.readUnshared();
                                System.out.println(wybor);
                                switch (wybor) {
                                    case 1:
                                        wyborSubject(szkola, x, x3, ois, oos);
                                        addGrade(szkola.getClass(x).getStudent(x3), szkola.getClass(x), szkola.getClass(x).getStudent(x3).getSubject(), szkola, x, x3, ois, oos);
                                        break;
                                    case 2:
                                        zachowanie(szkola, x, x3, oos, ois);
                                        break;
                                    case 3:
                                        wyborSubject(szkola, x, x3, ois, oos);
                                        oos.writeObject("Oceny ucznia: ");
                                        oos.flush();
                                        System.out.println("Oceny ucznia: ");
                                        ArrayList<String> listGrade = new ArrayList<>();
                                        for (int i = 0; i < listaOcen.size(); i++) {
                                            if (listaOcen.get(i).toString().contains(szkola.getClass(x).getStudent(x3).getFullName()) && listaOcen.get(i).toString().contains(szkola.getClass(x).getStudent(x3).getSubject().toString())) {
                                                System.out.println(listaOcen.get(i).toString());
                                                listGrade.add(listaOcen.get(i).toString());
                                            }
                                        }
                                        oos.writeUnshared(listGrade);
                                        oos.flush();
                                        break;
                                    case 4:
                                        znakWyjsc = false;
                                        break;
                                    default:
                                        System.out.println("Błąd. Wybierz jeszcze raz.");
                                        break;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Błąd. Wybierz jeszcze raz.");
                            scannerString();
                            break;
                        }
                        break;
                    case 4:
                        znak = false;
                        break;
                    default:
                        System.out.println("wybierz jeszcze raz");
                        break;
                }
            }
        } catch (InputMismatchException | IOException | ClassNotFoundException e) {
            System.out.println("Błąd. Wybierz jeszcze raz.nie wiem co sie dzieje tutaj");
        }
    }
    static void wyborSubject(School szkola, int x, int x3, ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        szkola.getClass(x).printStudent(x3);
        String wiad = "Wybierz przedmiot: \n" +
                "1.Matematyka, 2.Język polski, 3.Język angielski, 4.Fizyka, 5.Biologia, 6.Chemia";
        System.out.println(wiad);
        oos.writeUnshared(wiad);
        oos.flush();
        switch ((Integer) ois.readUnshared()) {
            case 1:
                szkola.getClass(x).getStudent(x3).setSubject(Subject.MATEMATYKA);
                break;
            case 2:
                szkola.getClass(x).getStudent(x3).setSubject(Subject.JPOLSKI);
                break;
            case 3:
                szkola.getClass(x).getStudent(x3).setSubject(Subject.JANGIELSKI);
                break;
            case 4:
                szkola.getClass(x).getStudent(x3).setSubject(Subject.FIZYKA);
                break;
            case 5:
                szkola.getClass(x).getStudent(x3).setSubject(Subject.BIOLOGIA);
                break;
            case 6:
                szkola.getClass(x).getStudent(x3).setSubject(Subject.CHEMIA);
                break;
            default:
                oos.writeUnshared("wybierz jeszcze raz");
                oos.flush();
                System.out.println("wybierz jeszcze raz");
        }
    }
    static ArrayList<ocena> listaOcen = new ArrayList<>();

    public static void addGrade(Student student, Class klasa, Subject przedmiot, School szkola, int x, int x3, ObjectInputStream ois, ObjectOutputStream oos) throws IOException, ClassNotFoundException {
        System.out.println("Jaką ocenę wystawić?");
        oos.writeUnshared("Jaką ocenę wystawić?");
        oos.flush();
        int y = (Integer) ois.readUnshared();
        System.out.println("Opis: ");
        oos.writeObject("Opis: ");
        String y2 = (String) ois.readUnshared();
        ocena nowaocena = new ocena(y, student, klasa, przedmiot, y2, szkola, x, x3);
        nowaocena.toString();
        listaOcen.add(nowaocena);
    }
}