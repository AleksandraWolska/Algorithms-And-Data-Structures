import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.start();

    }
    Scanner dane = new Scanner(System.in);

    void start() {


        System.out.println("Wpisz wyrażenie");
        String wyr = dane.nextLine();
        Palindrom pal = new Palindrom(wyr);
        Nawiasy naw = new Nawiasy(wyr);
        start();
    }

}
