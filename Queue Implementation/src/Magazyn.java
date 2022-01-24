import java.sql.SQLOutput;
import java.util.Scanner;

public class Magazyn {

    Queue<Klient> magazyn;
    Scanner dane = new Scanner(System.in);
    int numer;



    //konstruktor do zad. 1
    public Magazyn() {
        magazyn = new Queue<Klient>();
        start();
    }



    //konstruktor do zad 2
    public Magazyn(int numer) {
        this.numer = numer;
        magazyn = new Queue<Klient>();
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }



    void wczytajKlientow(){
        Klient klient1 = new Klient("Klient1");
        klient1.zamowienia.enqueue(new Zamowienie("młotek", 3, 20));
        klient1.zamowienia.enqueue(new Zamowienie("wiertło", 2, 10));
        klient1.zamowienia.enqueue(new Zamowienie("śruba", 20, 0.5));
        magazyn.enqueue(klient1);

        Klient klient2 = new Klient("Klient2");
        klient2.zamowienia.enqueue(new Zamowienie("śruba", 10, 0.5));
        klient2.zamowienia.enqueue(new Zamowienie("wiertarka", 1, 150));
        klient2.zamowienia.enqueue(new Zamowienie("pędzel", 4, 5));
        magazyn.enqueue(klient2);

        Klient klient3 = new Klient("Klient3");
        klient3.zamowienia.enqueue(new Zamowienie("lakier", 2, 15));
        klient3.zamowienia.enqueue(new Zamowienie("cegła", 80, 7));
        klient3.zamowienia.enqueue(new Zamowienie("farba", 3 , 35));
        magazyn.enqueue(klient3);

        Klient klient4 = new Klient("Klient4");
        klient4.zamowienia.enqueue(new Zamowienie("wiertło", 10, 10));
        klient4.zamowienia.enqueue(new Zamowienie("wiertarka", 1, 190));
        klient4.zamowienia.enqueue(new Zamowienie("pędzel", 9, 4));
        magazyn.enqueue(klient4);

        start();
    }






    void start() {
        System.out.println("WITAMY W MAGAZYNIE");
        System.out.println("Oczekuje " + magazyn.size() + " klientów");
        System.out.println("0 - Wczytaj zgłoszenia klientów (domyślne)");
        System.out.println("1 - Realizuj wszystkie zgłoszenie");
        System.out.println("2 - Realizuj następne zgłoszenie");
        System.out.println("3 - Wyjdź z programu");

        int wybor = getDaneInt();

        switch (wybor) {
            case 0:
                wczytajKlientow();
                break;
            case 1:
                realizujWszytkieZgloszenia();
                start();
                break;
            case 2:
                realizujNastepneZgloszenie();
                break;
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("Podaj inną liczbę!");
                start();
        }
    }

    public int getDaneInt() {
        int liczba = 0;
        try {
            liczba = Integer.parseInt(dane.next());
        } catch (NumberFormatException e) {
            System.err.println("Wprowadź liczbę!");
        }

        return liczba;
    }


    double realizujWszytkieZgloszenia(){
        double tempSumaKwot = 0;
        Klient tempKlient;
        while (!magazyn.isEmpty()) {
            tempKlient = magazyn.dequeue();
            System.out.println("\nZlecenie zrealizowane: " + tempKlient.getNazwaKlienta());
            System.out.println("Kwota do zapłaty: " + tempKlient.sumaZamowien());
            tempSumaKwot += tempKlient.sumaZamowien();
        }

        System.out.println("\nSuma kwot: " + tempSumaKwot);
    return tempSumaKwot;
    }



    void realizujNastepneZgloszenie() {

        if(!magazyn.isEmpty()) {
            Klient tempKlient = magazyn.dequeue();
            System.out.println("\nZlecenie zrealizowane: " + tempKlient.getNazwaKlienta());
            System.out.println("Kwota do zapłaty: " + tempKlient.sumaZamowien());
        } else {
            System.out.println("Brak nowych zamówień");
        }
        start();
    }



//zadanie FIRMA
    double przychody() {
        double tempSumaKwot = 0;
        Klient tempKlient;
        while (!magazyn.isEmpty()) {
            tempKlient = magazyn.dequeue();

            tempSumaKwot += tempKlient.sumaZamowien();
        }
        return tempSumaKwot;
    }





}
