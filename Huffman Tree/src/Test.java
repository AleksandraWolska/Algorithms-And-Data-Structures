import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test {


    Map<Character, Integer> mapa;


    public Test() {

        //stworzenie mapy z pliku
        mapa = start();

        //dbudowanie na jej podstawie drzewa
        HuffmanTree tree = new HuffmanTree(mapa);

        //pobranie stringu binarnego za pomocą kodowania wcześniej ustalounego kodu
        String wynik = tree.getZakodowanyString(tree.getZakodowanaMapa(), tekst);
        System.out.println("\nZakodowany tekst:");
        System.out.println(wynik);
        System.out.println(tree.rozkoduj(wynik));

    }



    String tekst;
    StringBuilder strB = new StringBuilder();


     public Map <Character, Integer> start() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("src\\plik.txt"));
        ) {
            while (br.ready()) {
                strB.append(br.readLine());
            }
        } catch (IOException e) {
            System.err.println("Nie udało sie wczytac pliku!");
        }
        tekst = strB.toString();
        mapa = stworzMapeIlosciLiter(tekst);
        return mapa;

    }



    static HashMap<Character, Integer> stworzMapeIlosciLiter(String input)
    {

        HashMap<Character, Integer> iloscLiterMapa = new HashMap<Character, Integer>();
        char[] tablica = input.toCharArray();

        for (char c : tablica) {

            if (iloscLiterMapa.containsKey(c)) {
                iloscLiterMapa.put(c, iloscLiterMapa.get(c) + 1);     //jesli jest w mapie to count++
            } else {
                iloscLiterMapa.put(c, 1);                //jesli nie jest to count = 1
            }
        }
        return iloscLiterMapa;
        }

    }

