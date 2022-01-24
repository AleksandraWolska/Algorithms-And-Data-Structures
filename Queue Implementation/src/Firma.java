import java.util.ArrayList;
import java.util.Scanner;

public class Firma {

    OneWayLinkedListWithHeadAndTail<Magazyn> magazyny;

    Scanner dane = new Scanner(System.in);
    public Firma() {
        magazyny = new OneWayLinkedListWithHeadAndTail<Magazyn>();
        wczytajMagazyny();
        start();



    }

    void start(){
        System.out.println("WITAMY W FIRMIE!");
        System.out.println("1 - Sprawdź liczbę magazynów");
        System.out.println("2 - Oblicz przychody:");
        System.out.println("3 - Wyjdź z programu:");

        int wybor = getDaneInt();

        switch (wybor) {
            case 1:
                System.out.println("Twoja firma posiada " + magazyny.size() + " magazyny");
                start();
                break;
            case 2:
                obliczPrzychody();
                start();
                break;
            case 3:
                System.exit(0);
                break;

            default:
                System.out.println("Podaj inną liczbę!");
                start();
        }

    }



    double obliczPrzychody(){
        double tempPrzychody = 0;
        double tempJeden = 0;
            System.out.println("Przychody generowane:");

        for (Magazyn m : magazyny) {
            tempJeden = m.przychody();
            System.out.println("Magazyn " + m.getNumer() + ": " + tempJeden);
            tempPrzychody += tempJeden;
        }

        System.out.println("\nSUMA PRZYCHODÓW: " + tempPrzychody);
        return tempPrzychody;
    }




    void wczytajMagazyny(){

        //tworzę magazyny
        Magazyn magazyn1 = new Magazyn(1);
        Magazyn magazyn2 = new Magazyn(2);
        Magazyn magazyn3 = new Magazyn(3);


        //tworzę klientow
        Klient klient1 = new Klient("Klient1");
        klient1.zamowienia.enqueue(new Zamowienie("młotek", 3, 20));
        klient1.zamowienia.enqueue(new Zamowienie("wiertło", 2, 10));
        klient1.zamowienia.enqueue(new Zamowienie("śruba", 20, 0.5));

        Klient klient2 = new Klient("Klient2");
        klient2.zamowienia.enqueue(new Zamowienie("śruba", 10, 0.5));
        klient2.zamowienia.enqueue(new Zamowienie("wiertarka", 1, 150));
        klient2.zamowienia.enqueue(new Zamowienie("pędzel", 4, 5));

        Klient klient3 = new Klient("Klient3");
        klient3.zamowienia.enqueue(new Zamowienie("lakier", 2, 15));
        klient3.zamowienia.enqueue(new Zamowienie("cegła", 80, 7));
        klient3.zamowienia.enqueue(new Zamowienie("farba", 3 , 35));

        Klient klient4 = new Klient("Klient4");
        klient4.zamowienia.enqueue(new Zamowienie("wiertło", 10, 10));
        klient4.zamowienia.enqueue(new Zamowienie("wiertarka", 1, 190));
        klient4.zamowienia.enqueue(new Zamowienie("pędzel", 9, 4));

        Klient klient5 = new Klient("Klient5");
        klient5.zamowienia.enqueue(new Zamowienie("wiertło", 10, 10));
        klient5.zamowienia.enqueue(new Zamowienie("wiertarka", 1, 190));
        klient5.zamowienia.enqueue(new Zamowienie("pędzel", 9, 4));

        //dodaje zgloszenia do magazynow
        magazyn1.magazyn.enqueue(klient1);
        magazyn1.magazyn.enqueue(klient4);
        magazyn2.magazyn.enqueue(klient2);
        magazyn2.magazyn.enqueue(klient5);
        magazyn3.magazyn.enqueue(klient3);

        //dodaje magazyn do firmy
        magazyny.add(magazyn1);
        magazyny.add(magazyn2);
        magazyny.add(magazyn3);

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



}
