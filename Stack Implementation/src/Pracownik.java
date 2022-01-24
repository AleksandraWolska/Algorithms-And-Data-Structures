package com.company;

import java.io.Serializable;
import java.util.Date;

public class Pracownik implements Serializable {
    long PESEL;
    String nazwisko;
    String imie;
    String dataUrodzenia;
    String stanowisko;
    double pensja;
    int staz;
    double premia;

    public Pracownik(long PESEL, String nazwisko, String imie, String dataUrodzenia, String stanowisko, double pensja, int staz) {
        this.PESEL = PESEL;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.dataUrodzenia = dataUrodzenia;
        this.stanowisko = stanowisko;
        this.pensja = pensja;
        this.staz = staz;
    }


    public String toString () {
        return String.format("%12d    %-12s %-12s %12s    %-12s  %6f  %4d    %4f", PESEL, nazwisko, imie, dataUrodzenia, stanowisko, pensja, staz, getPremia());

    }

    String przedstaw() {
        return imie + " " + nazwisko;
    }

    public int compare(Pracownik inny) {
        if (PESEL > inny.getPESEL()) {
            return 1;
        } else if (PESEL == inny.getPESEL()) {
            return 0;
        } else {
            return -1;
        }
    }



    public long getPESEL() {
        return PESEL;
    }

    public void setPESEL(long PESEL) {
        this.PESEL = PESEL;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(String dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public double getPensja() {
        return pensja;
    }

    public void setPensja(double pensja) {
        this.pensja = pensja;
    }

    public int getStaz() {
        return staz;
    }

    public void setStaz(int staz) {
        this.staz = staz;
    }

    public double getPremia() {
        double tempPremia = 0;
        if( staz >= 20) {
            tempPremia = 0.2 * pensja;
        } else if (staz < 20 && staz >= 10) {
            tempPremia = 0.1 * pensja;
        }
        return tempPremia;
    }

    public void setPremia(double premia) {
        this.premia = premia;
    }
}
