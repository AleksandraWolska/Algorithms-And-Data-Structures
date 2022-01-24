import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ExpressionTree {

    public static int leafCounter = 0;
    public static int nodeCounter = 0;


    Node constructTree(String postfix[]) {           //drzewo za pomocą stosu, napierw wrzucam liście
        leafCounter = 0;
        nodeCounter = 0;
        Stack<Node> st = new Stack<Node>();
        Node t, t1, t2;

        for (int i = 0; i < postfix.length; i++) {          //iteracja przez ciąg wynikowy

            if (!isOperator(postfix[i])) {          //jesli to liczba, stwórz z niej Node i połóż na stos
                t = new Node(postfix[i]);
                st.push(t);

            } else {
                t = new Node(postfix[i]);           //jeśli to operator,stworz z niego node

                t1 = st.pop();                      //i dwie gorne liczby ze stosu zrób jego dziecmi
                t2 = st.pop();
                t.right = t1;
                t.left = t2;

                st.push(t);                 //dodaj to podwyrażenie do stosu
            }
        }
        t = st.peek();          //element który został będzie "rootem" i go zwracamy
        st.pop();

        return t;
    }


    public static double oblicz(Node root) {
        if (root == null) {
            return 0;
        }

        if (czyLisc(root)) {                    //jesli jest lisciem (liczbą)
            return Integer.valueOf(root.val);
        }

        double x = oblicz(root.left);       //rekursywnie obliczamy lewe i prawe poddrzewo
        double y = oblicz(root.right);

        return obliczPodwyrazenie(root.val, x, y);      //dla kazdego wywolania obliczamy podwyrazenie  w zal od operatora
    }

    public static double obliczPodwyrazenie(String op, double x, double y) {
        if (op.equalsIgnoreCase("+")) { return x + y; }
        if (op.equalsIgnoreCase("-")) { return x - y; }
        if (op.equalsIgnoreCase("*")) { return x * y; }
        if (op.equalsIgnoreCase("/")) { return x / y; }
        if (op.equalsIgnoreCase("%")) { return x % y; }

        return 0;
    }

    public static boolean czyLisc(Node node) {
        return node.left == null && node.right == null;
    }


    void printPostorder(Node node)
    {
        if (node == null)
            return;

        nodeCounter++;
        if (czyLisc(node)) {
            leafCounter++;
        }

        printPostorder(node.left);          //rekurencja na prawym
        printPostorder(node.right);          //rekurencja na lewym
        System.out.print(node.val + " ");
    }


    void printInorder(Node node)
    {
        if (node == null){
            return;
        }

        if(!czyLisc(node)){
            System.out.print("( ");
        }


        printInorder(node.left);            //rekurencja na lewym
        System.out.print(node.val + " ");      //wydrukuj dane od lewego
        printInorder(node.right);               //rekurencja na prawym
        if(!czyLisc(node)){
            System.out.print(") ");
        }

    }

    void printLevelOrder(Node root)
    {
        Queue<Node> kolejka;
        kolejka = new LinkedList<Node>();
        kolejka.add(root);
        while (!kolejka.isEmpty())            //powtarzamy dopoki nie wyczyscimy kolejki
        {
            Node tempNode = kolejka.poll();       //poll usuwa obecną głowę kolejki i zapisuje w temp
            System.out.print(tempNode.val + " ");

            if (tempNode.left != null) {        //jeśli istnieje, dodaj do kolejki lewe dziecko
                kolejka.add(tempNode.left);
            }

            if (tempNode.right != null) {           //jeśli istnieje, dodaj do kolejki prawe dziecko
                kolejka.add(tempNode.right);
            }
        }
    }










    public boolean isOperator(String c) {
        return c.equalsIgnoreCase("+")
                || c.equalsIgnoreCase("-")
                || c.equalsIgnoreCase("*")
                || c.equalsIgnoreCase("%")
                || c.equalsIgnoreCase("/");
    }



    int wysokosc(Node node)
    {
        if (node == null)
            return 0;
        else
        {

            int leftWysokosc = wysokosc(node.left);
            int rightWysokosc = wysokosc(node.right);


            if (leftWysokosc > rightWysokosc)
                return (leftWysokosc + 1);
            else
                return (rightWysokosc + 1);
        }
    }


}
