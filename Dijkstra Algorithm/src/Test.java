import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {

    List<List<Vertex>> listaSasiedztwa;
    int rozmiar;
    Graf graf;

    public Test (){
         rozmiar = 5;
         stworzListeSasiedztwa();
         start();

    }


    void stworzListeSasiedztwa(){
        listaSasiedztwa = new ArrayList<List<Vertex> >();   //lista sąsiedztwa
        for (int i = 0; i <rozmiar; i++) {
            List<Vertex> item = new ArrayList<Vertex>();
            listaSasiedztwa.add(item);
        }

        listaSasiedztwa.get(0).add(new Vertex(1, 10));
        listaSasiedztwa.get(0).add(new Vertex(3, 30));
        listaSasiedztwa.get(0).add(new Vertex(4, 100));
        listaSasiedztwa.get(1).add(new Vertex(2, 50));
        listaSasiedztwa.get(2).add(new Vertex(4, 10));
        listaSasiedztwa.get(3).add(new Vertex(2, 20));
        listaSasiedztwa.get(3).add(new Vertex(4, 60));
    }


    void listaToString(List<List<Vertex>> lista){
        for (List<Vertex> w : lista) {
            System.out.print("\n"+ getMiasto(lista.indexOf(w))+":   ");
            for (Vertex v : w) {
                System.out.print( getMiasto(v.miasto) +" ("+ getOdleglosc(v.koszt) + "), ");
            }
        }

    }


    String getMiasto (int numer){
        String str;
        switch (numer){
            case 0:
                str = "Wrocław";
                break;
            case 1:
                str = "Oława  ";
                break;
            case 2:
                str = "Brzeg  ";
                break;
            case 3:
                str = "Nysa   ";
                break;
            case 4:
                str = "Opole  ";
                break;
            default:
                str = "nie ma takiego miasta";
        }
        return str;
    }


    String getOdleglosc(int numer){
        String str;
        if (numer == Integer.MAX_VALUE) {
            str = "BRAK DROGI";
        } else {
            str = numer + "";
        }
      return str;
    }


    void start() {
        System.out.println("\n\nMENU");
        System.out.println("1 - Wyświetl listę sąsiedztwa");
        System.out.println("2 - Znajdź najkrótsze drogi z wybranego miasta");
        System.out.println("3 - Wyjdź z programu");
        int wybor  = getDaneInt(1, 3);

        switch (wybor) {
            case 1:
                listaToString(listaSasiedztwa);
                start();
                break;

            case 2:
                graf = new Graf(rozmiar);
                znajdzDroge();
                start();
                break;

            case 3:
                System.exit(0);
                break;
            default:
                System.out.println(wybor);
                start();


        }
    }


    void znajdzDroge() {
        System.out.println("Z jakiego miasta startujemy?");
        System.out.println("0 - Wrocław\n" +
                "1 - Oława\n" +
                "2 - Brzeg\n" +
                "3 - Nysa\n" +
                "4 - Opole");

        int start = getDaneInt(0, 4);

        graf.dijkstra(listaSasiedztwa, start);

        System.out.println("\nNajkrótsze ścieżki dla miasta " + getMiasto(start));
        System.out.println("\nStart\t\t" + "Docelowe " + "\tOdleglosc");
        System.out.println("--------    ---------   ---------");

        for (int i = 0; i < graf.tabDystans.length; i++){
            System.out.println(getMiasto(start) + " \t " + getMiasto(i) + " \t "  + getOdleglosc(graf.tabDystans[i]));
        }

        }




    Scanner dane = new Scanner(System.in);

    public int getDaneInt(int min, int max) {
        int liczba = 0;
        try {
            liczba = Integer.parseInt(dane.next());
        } catch (NumberFormatException e) {
            System.err.println("Wprowadź liczbę!");
            liczba = getDaneInt(min, max);
        }
        try {
            if (!(liczba >= min && liczba <= max)) {
                throw new Exception("Wpisz poprawną liczbę!");
            }
        } catch (Exception e) {
            System.err.println("Wprowadź poprawną liczbę!");
            liczba = getDaneInt(min, max);
        }
        return liczba;
    }





}
