package com.company;


import java.util.Scanner;

import static com.company.Baza.lista;

public class Parking {

    ListStack<Pracownik> stos;
    ListStack<Pracownik> stosOczekiwanie;
    Scanner dane = new Scanner(System.in);
    OneWayLinkedListWithHead<Pracownik> obecni = new OneWayLinkedListWithHead<Pracownik>();

    public Parking() {
        stos = new ListStack<Pracownik>();
        stosOczekiwanie = new ListStack<Pracownik>();
        startParking();
    }

    void startParking() {
        System.out.println("Podaj pesel");
        long tempPESEL = getDaneLong();
        System.out.println("Co chcesz zrobić?");
        System.out.println("1 - Wjedź");
        System.out.println("2 - Wyjedź");


        int wybor = getDaneInt();

        switch (wybor) {
            case 1:
                wjedz(tempPESEL);
                break;
            case 2:
                wyjedz(tempPESEL);
                break;

            default:
                System.out.println("Podaj inną liczbę!");
                startParking();
        }
    }


    void wjedz(long tempPesel) {


        for (Pracownik p : lista ) {
            if (p.getPESEL() == tempPesel) {
                stos.push(p);
                obecni.add(p);
                System.out.println(p.przedstaw() + " wjechał na parking");
                break;
            }
        }
        startParking();
    }



    void wyjedz(long tempPesel) {


        for (Pracownik p : lista) {
            if (p.getPESEL() == tempPesel) {

                if (obecni.contains(p)) {
                    while (p != stos.top()) {
                        System.out.println(stos.top().przedstaw() + " proszony o wyjazd");
                        stosOczekiwanie.push(stos.pop());
                    }
                    obecni.remove(stos.top());
                    System.out.println(stos.pop().przedstaw() + " wyjechał z parkingu");


                    while (!stosOczekiwanie.isEmpty()) {
                        System.out.println(stosOczekiwanie.top().przedstaw() + " może wjechać z powrotem");
                        stos.push(stosOczekiwanie.pop());
                    }

                }



            }
        }
        startParking();
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


}
