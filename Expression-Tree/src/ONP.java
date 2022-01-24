import java.util.Stack;

class ONP {

    public String konwertujNaOnp(String str) {

        if (str == null)
            return null;

        String strWynikowy = "";
        int len = str.length();

        Stack<Character> operator = new Stack<Character>(); //stos operatorów
        Stack<String> onp = new Stack<String>();            //stos gotowego wyrazenia


        operator.push('#');             //użyjemy zamiast isEmpty

        for (int i = 0; i < len;) {                     //dla każdego znaku

            while (i < len && str.charAt(i) == ' ')     //oprocz spacji
                i++;
            if (i == len)
                break;

            if (isNum(str.charAt(i))) {                     //jesli jest cyfrą
                String num = "";
                while (i < len && isNum(str.charAt(i))){    //pobieram kolejne cyfry do liczby
                    num += str.charAt(i++);
                }
                onp.push(num);                              //wrzucam cal liczbe

            } else if (isOperator(str.charAt(i))) {         //jesli jest operatorem
                char op = str.charAt(i);
                switch (op) {

                    case '(':
                        operator.push(op);          //wrzucam nawias - pocztek podwyrazenia
                        break;
                    case ')':
                        while (operator.peek() != '(') {
                            onp.push(Character.toString(operator.pop()));       //dorzucam do stosu onp liczby i operatory
                        }
                        operator.pop();             //usuwam nawias
                        break;

                    case '+':
                    case '-':
                        if (operator.peek() == '(')
                            operator.push(op);
                        else {
                            while (operator.peek() != '#' && operator.peek() != '('){
                                onp.push(Character.toString(operator.pop()));
                            }
                            operator.push(op);
                        }
                        break;

                    case '*':
                    case '%':
                    case '/':
                        if (operator.peek() == '(')
                            operator.push(op);
                        else {
                            while (operator.peek() != '#' && operator.peek() != '+' &&
                                    operator.peek() != '-' && operator.peek() != '('){
                                onp.push(Character.toString(operator.pop()));                       //wrzucam co jest na stosie - + i minus działa jako nawias (kolejnosc dzialan)
                            }
                            operator.push(op);
                        }
                        break;
                }
                i++;
            }
        }


        while (operator.peek() != '#')                 //dodaję co zostało z operatorów
            onp.push(Character.toString(operator.pop()));

        while (!onp.isEmpty())
            strWynikowy = strWynikowy.length() == 0 ? onp.pop() + strWynikowy : onp.pop() + " " + strWynikowy;      //w odwrotnej kolejnosci umieszczam w wyniku
        return strWynikowy;
    }

    public boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '%' || c == '/' || c == '(' || c == ')';
    }

    public boolean isNum(char c) {
        return c - '0' >= 0 && c - '0' <= 9;        //rzutuje na int i patrzy czy cyfra
    }
}