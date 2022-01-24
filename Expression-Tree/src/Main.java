import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args)  throws IOException {
       Test test = new Test();
        Scanner dane = new Scanner(System.in);

        while (true){
            System.out.println("Jeszcze raz? 1- tak, 2 - nie");
            int wybor = dane.nextInt();

            switch (wybor) {
                case 1:
                    test = new Test();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Zła wartość");

            }
        }

    }



    }

