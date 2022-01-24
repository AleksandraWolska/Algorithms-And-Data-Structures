import java.lang.reflect.Array;

public class Palindrom {

    String wyrazenie;



    public Palindrom(String wyrazenie) {
        this.wyrazenie = wyrazenie;
        sprawdzPalindrom(wyrazenie);

    }


    void sprawdzPalindrom(String wyrazenie) {

        ArrayStack<Character> normalny = new ArrayStack<Character>(wyrazenie.length());
        ArrayStack<Character> odwrocony = new ArrayStack<Character>(wyrazenie.length());


        for (int ii = 0; ii < wyrazenie.length(); ii++) {
            normalny.push(wyrazenie.charAt(ii));
            odwrocony.push(wyrazenie.charAt(ii));
        }

        System.out.println("Przed odwróceniem");
        System.out.println(odwrocony.showStack());

        odwrocony.reverseStack();

        System.out.println("Po odwróceniu");
        System.out.println(odwrocony.showStack());


        boolean flag = true;


        while(!normalny.isEmpty()) {
            if (!normalny.pop().equals(odwrocony.pop())){                   //jesli któraś iteracja się nie zgadza
                flag = false;
                break;
            }
        }


        if (flag == true && normalny.isEmpty() && odwrocony.isEmpty()) {        //jeśli oba stosy są puste
            System.out.println("To jest palindrom!");
        } else {
            System.out.println("To nie jest palindrom!");
        }
    }
}
