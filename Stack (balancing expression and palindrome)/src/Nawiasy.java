public class Nawiasy {


    String wyrazenie;

    public Nawiasy(String wyrazenie) {
        this.wyrazenie = wyrazenie;
        boolean flag = nawiasyZrownowazone(wyrazenie);

        if (flag) {
            System.out.println("Wyrażenie " + wyrazenie + " jest zrównoważone");
        } else {
            System.out.println("Wyrażenie " + wyrazenie + " nie jest zrównoważone");
        }
    }



    boolean nawiasyZrownowazone(String wyrazenie) {
        ArrayStack<Character> nawiasy = new ArrayStack<Character>(10);

        for (int i = 0; i < wyrazenie.length(); i++) {

            if (nawiasOtwierajacy(wyrazenie.charAt(i))) {
                nawiasy.push(wyrazenie.charAt(i));

            } else if (nawiasZamykajacy(wyrazenie.charAt(i))) {

                if ((wyrazenie.charAt(i) == ']' && nawiasy.top().equals('[')) ||
                        (wyrazenie.charAt(i) == '}' && nawiasy.top().equals('{')) ||
                        (wyrazenie.charAt(i) == ')' && nawiasy.top().equals('('))) {
                    nawiasy.pop();
                } else {
                    break;
                }
            }
        }

        if (nawiasy.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }



    boolean nawiasOtwierajacy(char ch) {
        boolean flag = false;

        if (ch == '{' || ch == '[' || ch == '(') {
            flag = true;
        }
        return flag;
    }


    boolean nawiasZamykajacy(char ch) {
        boolean flag = false;

        if (ch == '}' || ch == ']' || ch == ')') {
            flag = true;
        }
        return flag;
    }

}