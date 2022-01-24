public class Zamowienie {

    String nazwaTowaru;
    int ilosc;
    double cenaJednostkowa;

    public Zamowienie(String nazwaTowaru, int ilosc, double cenaJednostkowa) {
        this.nazwaTowaru = nazwaTowaru;
        this.ilosc = ilosc;
        this.cenaJednostkowa = cenaJednostkowa;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public double getCenaJednostkowa() {
        return cenaJednostkowa;
    }

    public void setCenaJednostkowa(double cenaJednostkowa) {
        this.cenaJednostkowa = cenaJednostkowa;
    }
}
