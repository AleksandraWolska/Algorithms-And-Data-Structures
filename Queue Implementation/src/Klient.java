public class Klient {

    String nazwaKlienta;
    Queue<Zamowienie> zamowienia;

    public Klient(String nazwaKlienta) {
        this.nazwaKlienta = nazwaKlienta;
        zamowienia = new Queue<Zamowienie>();
    }



    double sumaZamowien() {
        double tempSuma = 0;
        Zamowienie tempZam;
        while (!zamowienia.isEmpty()) {
            tempZam = zamowienia.dequeue();
            tempSuma += tempZam.getCenaJednostkowa() * tempZam.getIlosc();
        }
        return tempSuma;
    }



    public String getNazwaKlienta() {
        return nazwaKlienta;
    }

    public void setNazwaKlienta(String nazwaKlienta) {
        this.nazwaKlienta = nazwaKlienta;
    }

    public Queue<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    public void setZamowienia(Queue<Zamowienie> zamowienia) {
        this.zamowienia = zamowienia;
    }
}
