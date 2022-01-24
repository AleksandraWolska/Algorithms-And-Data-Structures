package com.company;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Baza {

    TwoWayCycledListWithSentinel<Pracownik> domyslne;
    public static TwoWayCycledListWithSentinel<Pracownik> lista;

    Scanner dane = new Scanner(System.in);

    public Baza() {



        start();
    }


    public void start() {
        System.out.println("\n_________ MENU __________");
        System.out.println("1 - Utwórz nową bazę danych");
        System.out.println("2 - Odczytaj bazę z pliku");
        System.out.println("3 - Wyświetl wszystkie rekordy");
        System.out.println("4 - Wyswietl dane jednego pracownika");
        System.out.println("5 - Dopisz nowego pracownika");
        System.out.println("6 - Usuń pracownika z bazy");
        System.out.println("7 - Aktualizuj dane pracownika");
        System.out.println("8 - Oblicz średnią pensję w firmie");
        System.out.println("9 - Oblicz ilu pracowników zarabia poniżej średniej");
        System.out.println("10 - Zapisz bazę do pliku");
        System.out.println("11 - Parking");

        int wybor = getDaneInt();

        switch (wybor) {
            case 1:
                utworzBaze();
                break;
            case 2:
                odczytajZPliku();
                break;
            case 3:
                wyswietlRekordyPracownikow();
                break;
            case 4:
                wyswietlDanePracownika();
                break;
            case 5:
                dopiszDoBazy();
                break;
            case 6:
                usunZBazy();
                break;
            case 7:
                aktualizujDane();
                break;
            case 8:
                System.out.println("Srednia penja bez premii: " + obliczSredniaPensjeBezPremii());;
                System.out.println("Srednia penja z premią: " + obliczSredniaPensjeZPremia());;

                start();
                break;
            case 9:
                iluPonizejSredniej();
                break;
            case 10:
                zapiszDoPliku(lista);
                break;
            case 11:
                new Parking();
                break;


            default:
                System.out.println("Podaj inną liczbę!");
                start();
        }
    }

    void zapiszDoPliku(TwoWayCycledListWithSentinel<Pracownik> list) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src\\pracownicy.ser"))) {
            for (Pracownik p : list ) {
                output.writeObject(p);
            }
            System.out.println("Zapisano do pliku");
            start();
        } catch (IOException e) {
            System.err.println("Coś nie tak! \n" + e.getStackTrace());
            start();
        }

    }



    void odczytajZPliku() {

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("src\\pracownicy.ser"))) {
            lista = new TwoWayCycledListWithSentinel<Pracownik>();

            while(true) {
                Pracownik p = (Pracownik) input.readObject();

                insertToList(p);
                System.out.println(p.przedstaw());
            }

        } catch (IOException e) {


        } catch (ClassNotFoundException e) {
            System.err.println("Nie znalezniono klasy");
        }
        start();
    }

    void utworzBaze() {
        domyslne = new TwoWayCycledListWithSentinel<Pracownik>();

        domyslne.add(new Pracownik(222, "Szreder", "Maria", "04.11.1980", "architekt", 5000, 15));
        domyslne.add(new Pracownik(444, "Janik", "Tomasz", "04.11.1984", "chemik", 5050, 30));
        domyslne.add(new Pracownik(333, "Kwolek", "Adam", "04.11.1996", "elektronik", 4000, 5));
        domyslne.add(new Pracownik(111, "Logisz", "Tomasz", "04.11.1998", "informatyk", 3000, 12));
        domyslne.add(new Pracownik(666, "Mateusz", "Zielonka", "04.11.1998", "mechanik", 7000, 15));
        domyslne.add(new Pracownik(555, "Kasia", "Zlotek", "04.11.2000", "artystka", 4000, 19));
        zapiszDoPliku(domyslne);
    }

    void wyswietlRekordyPracownikow() {
        for (Pracownik p : lista) {
            System.out.println(p.toString());
        }
        start();
    }

    void wyswietlDanePracownika() {
        System.out.println("Podaj pesel");
        long tempPesel = getDaneLong();

        for (Pracownik p : lista ) {
            if (p.getPESEL() == tempPesel) {
                System.out.println(p.toString());
                break;
            }
        }
        start();

    }

    void usunZBazy() {
        System.out.println("Podaj pesel");
        long tempPesel = getDaneLong();
        int tempIndeks = 0;
        boolean done = false;

        for (Pracownik p : lista ) {
            if (p.getPESEL() == tempPesel) {
               tempIndeks = lista.indexOf(p);
               done = true;
               break;
            }
        }
        if (done) {
            System.out.println("Usunięto pracownika " + lista.get(tempIndeks).przedstaw());
            lista.remove(tempIndeks);
        } else {
            System.err.println("Nie ma takiego pracownika!");
        }


        start();

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

    public long getDaneLong() {
        long liczba = 0;
        try {
            liczba = Long.parseLong(dane.next());
        } catch (NumberFormatException e) {
            System.err.println("Wprowadź liczbę!");
        }

        return liczba;
    }

    public String getDaneTekst() {
        String str = "";
        str = dane.next();
        try {
            if (!str.chars().allMatch(Character::isLetter)) {
                throw new Exception("Niepoprawna wartość!");
            }
        } catch (Exception e) {
            System.err.println(e);
            getDaneTekst();
        }
        return str;
    }
    public double getDaneDouble() {
        double liczba = 0;
        try {
            liczba = Double.parseDouble(dane.next());
        } catch (NumberFormatException e) {
            System.err.println("Wprowadź liczbę!");
            getDaneDouble();
        }
        return liczba;
    }


    void dopiszDoBazy() {
            long tempPesel;
            String tempImie;
            String tempNazwisko;

            double tempEtat;
            double tempPensja;
            String tempStanowisko;
            String tempDataUrodzenia;
            int tempStaz;

            System.out.println("Wprowadź pesel");
            tempPesel = getDaneLong();
            System.out.println("Wprowadź imię");
            tempImie = getDaneTekst();
            System.out.println("Wprowadź nazwisko");
            tempNazwisko = getDaneTekst();

            System.out.println("Wprowadź datę urodzenia");
            tempDataUrodzenia = dane.next();
            System.out.println("Wprowadź stanowisko");
            tempStanowisko = getDaneTekst();
            System.out.println("Wprowadź Pensję");
            tempPensja = getDaneDouble();
            System.out.println("Wprowadź staż");
            tempStaz = getDaneInt();

            insertToList(new Pracownik(tempPesel, tempNazwisko, tempImie, tempDataUrodzenia, tempStanowisko, tempPensja, tempStaz));


            start();


    }

    void insertToList(Pracownik p) {
        boolean done = false;
        if (lista.size() == 0) {
            lista.add(p);
            done = true;
        } else {
           for (Pracownik pr : lista) {
               if ((pr.getPESEL() > p.getPESEL()) && !done) {
                   lista.add(lista.indexOf(pr), p);
                   done = true;
                   break;
               }
           }
        }
        if(!done) {
            lista.add(p);
        }
    }

    void aktualizujDane () {
        System.out.println("Podaj pesel pracownika");
        long tempPesel = getDaneLong();
        int tempIndeks = 0;
        boolean done = false;

        for (Pracownik p : lista ) {
            if (p.getPESEL() == tempPesel) {
                tempIndeks = lista.indexOf(p);
                done = true;
                break;
            }
        }
        if (done) {
            aktualizuj(lista.get(tempIndeks));
            System.out.println("Znaleziono");
        } else {
            System.err.println("Nie ma takiego pracownika!");
            start();
        }
    }

    void aktualizuj(Pracownik p) {
        System.out.println("Jakie dane chcesz zaktualizowac?");
        System.out.println("1 - Nazwisko");
        System.out.println("2 - Imie");
        System.out.println("3 - Data urodzenia");
        System.out.println("4 - Stanowisko");
        System.out.println("5 - Pensja");
        System.out.println("6 - Staż");


        int wybor = getDaneInt();

        switch (wybor) {
            case 1:
                System.out.println("Wprowadź imie");
                p.setImie(getDaneTekst());
                break;
            case 2:
                System.out.println("Wprowadź nazwisko");
                p.setNazwisko(getDaneTekst());
                break;
            case 3:
                System.out.println("Wprowadź datę orodzenia (DD.MM.YYYY)");
                p.setDataUrodzenia(dane.next());
                break;
            case 4:
                System.out.println("Wprowadź stanowisko");
                p.setStanowisko(getDaneTekst());
                break;
            case 5:
                System.out.println("Wprowadź pensję");
                p.setPensja(getDaneDouble());
                break;
            case 6:
                System.out.println("Wprowadź staż");
                p.setStaz(getDaneInt());
                break;

            default:
                System.out.println("Podaj inną liczbę!");
                aktualizuj(p);
        }
        System.out.println("Zaktualizowano!");
           start();
    }


    double obliczSredniaPensjeBezPremii() {
        double sumaBezPremii = 0;
        for (Pracownik p : lista) {
            sumaBezPremii += p.getPensja();
        }
        return sumaBezPremii / lista.size();

    }

    double obliczSredniaPensjeZPremia() {
        double sumaZPremia = 0;
        for (Pracownik p : lista) {
            sumaZPremia += p.getPensja() + p.getPremia();
        }
        return sumaZPremia / lista.size();

    }

    void iluPonizejSredniej() {
        int counter = 0;
        double srednia = obliczSredniaPensjeBezPremii();
        for (Pracownik p : lista) {
            if (p.getPensja() < srednia) {
                counter++;
            }
        }
        System.out.println("Mniej niż wynosi średnia firmy zarabia " + counter + " pracownikow");
        start();
    }




}
