import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Klient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;


        socket = new Socket(host.getHostName(), 9876);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());



        while(true) {
            try {
                String pyt1 = (String) ois.readObject();                   // 1 dodanie klasy , 2 wypisanie klas , 3 wybor klasy
                System.out.println(pyt1);
                int x = scannerInt();                                                 // wybieram
                oos.writeUnshared(x);
                oos.flush();
                switch(x){
                    case 1:                                                   // 1 dodanie klasy
                        String pyt2 = (String) ois.readObject();                // zapytanie o nazwe klasy
                        System.out.println(pyt2);
                        String nazwa = scannerString();
                        oos.writeUnshared(nazwa);
                        oos.flush();

                        System.out.println("klasa " + nazwa);
                        break;
                    case 2:                                                              // 2 wypisanie klas

                        List<Class> listaklas = (List<Class>) ois.readUnshared();
                        int nr = 1;
                        int limit = listaklas.size();
                        for (int it = 0; it < limit; it++) {
                            System.out.println("nr." + nr + " " +  listaklas.get(it).getClassa());
                            nr++;
                        }
                        break;
                    case 3:                                                                           //3 wybor klasy
                        String message4 = (String) ois.readObject();  //ktora klasa
                        System.out.println(message4);
                        int wybor = scannerInt();                    //wybor klasy
                        oos.writeUnshared(wybor);
                        oos.flush();
                        boolean znak = true;
                        try {
                            while (znak) {
                                String message5 = (String) ois.readObject();   // co zrobic 1- dodanie ucznia 2 wypisanie uczniow 3 wybor ucznia 4 wroc
                                System.out.println(message5);
                                int wybor2 = scannerInt();
                                oos.writeUnshared(wybor2);
                                oos.flush();
                                switch (wybor2) {
                                    case 1:                                                        // 1- dodanie ucznia
                                        String message6 = (String) ois.readObject();               // podaj imie nazwisko
                                        System.out.println(message6);
                                        String wybor3 = scannerString();                           // imie
                                        oos.writeUnshared(wybor3);
                                        oos.flush();
                                        String wybor4 = scannerString();                           // nazwisko
                                        oos.writeUnshared(wybor4);
                                        oos.flush();
                                        break;
                                    case 2:                                                     //2 wypisanie uczniow
                                        List<Student> listastudentow = (List<Student>) ois.readUnshared();
                                        int nrs = 1;
                                        for (int it = 0; it < listastudentow.size(); it++) {
                                            System.out.println("nr." + nrs + " " + listastudentow.get(it).getFullName());
                                            nrs++;
                                        }
                                        break;
                                    case 3:                                           //3 wybor ucznia
                                        String message7 = (String) ois.readObject();  //ktory uczen
                                        System.out.println(message7);
                                        int wybor5 = scannerInt();                    //wybor ucznia
                                        oos.writeUnshared(wybor5);
                                        oos.flush();
                                        boolean znak3 = true;
                                        try {
                                            while (znak3) {
                                                String uczenCzynnosci = (String) ois.readObject();  //1 - dodaj ocenę, 2 - wystaw zachowanie, 3 - wyświetl oceny ucznia, 4 - wróć
                                                System.out.println(uczenCzynnosci);
                                                int wybor6 = scannerInt();
                                                oos.writeUnshared(wybor6);
                                                oos.flush();
                                                switch (wybor6) {
                                                    case 1:                                              //dodawanie oceny
                                                        System.out.println((String) ois.readObject());   // z ktorego przedmiotu
                                                        oos.writeUnshared(scannerInt());                 // wybrany przedmiot
                                                        oos.flush();
                                                        System.out.println((String) ois.readObject());   // podaj ocene
                                                        int ocena;
                                                        do{ ocena = scannerInt();}while(ocena>5);
                                                        oos.writeUnshared(ocena);                        // ta ocena
                                                        oos.flush();
                                                        System.out.println((String) ois.readObject());   // podaj opis
                                                        oos.writeUnshared(scannerString());              // opis
                                                        oos.flush();
                                                        break;
                                                    case 2:                                              // zachowanie
                                                        System.out.println((String) ois.readObject());   // wybierz zachowanie
                                                        oos.writeUnshared(scannerInt());                 // wybrane zachowanie
                                                        oos.flush();
                                                        System.out.println((String) ois.readUnshared());
                                                        break;
                                                    case 3:                                              // wyswietlanie ocen
                                                        System.out.println((String) ois.readObject());   // z ktorego przedmiotu
                                                        oos.writeUnshared(scannerInt());                 // wybrany przedmiot
                                                        oos.flush();
                                                        System.out.println((String) ois.readUnshared());   // oceny ucznia

                                                        ArrayList<String> oceny = (ArrayList<String>) ois.readUnshared();
                                                        for (int i = 0; i < oceny.size(); i++) {
                                                            System.out.println(oceny.get(i));   // oceny
                                                        }
                                                        break;
                                                    case 4:
                                                        znak3 = false;
                                                        break;
                                                    default:
                                                        System.out.println("Błąd. Wybierz jeszcze raz.");
                                                        break;
                                                }
                                            }
                                        }catch (InputMismatchException | IOException e) {
                                            e.printStackTrace();
                                            break;

                                        }
                                        break;
                                    case 4:
                                        znak = false;
                                        break;
                                    default:
                                        System.out.println("Błąd. Wybierz jeszcze raz.");
                                        break;
                                }
                            }
                        } catch (InputMismatchException | IOException | ClassNotFoundException e) {
                            System.out.println("Błąd. Wybierz jeszcze raz.");
                            scannerInt();
                            break;
                        }


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
}