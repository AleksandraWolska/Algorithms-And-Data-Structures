import java.util.Scanner;

public class Test {

    String tempStr;
    String tempOnp;
    ExpressionTree tree;
    Scanner dane;
    String[] tabONP;

    public Test() {
        dane = new Scanner(System.in);

        System.out.println("Podaj wyrażenie w notacji infiksowej");
        tempStr = dane.nextLine();
        ONP onp = new ONP();
        tempOnp = onp.konwertujNaOnp(tempStr);
        System.out.println("\nCiąg przekazany do konstruktu drzewa: " + tempOnp + "\n");
        tabONP = tempOnp.split(" ");
        tree = new ExpressionTree();

        Node root = tree.constructTree(tabONP);
        System.out.println("Wyrażenie z nawiasami (inorder):");
        tree.printInorder(root);
        double wynik = tree.oblicz(root);
        if (Double.isInfinite(wynik) ) {
            System.out.println(" = UWAGA, DZIELENIE PRZEZ ZERO!");
        } else {
        System.out.println(" = "+ wynik);
        }


        System.out.println("\nWyrazenie w postaci ONP (postorder):");
        tree.printPostorder(root);

        System.out.println("\n\nLiczba liści: " + tree.leafCounter);
        System.out.println("Liczba węzłów: " + tree.nodeCounter);
        System.out.println("Wysokość drzewa: " + (tree.wysokosc(root) -1));

        System.out.println("\nPrzejście drzewa wszerz (levelorder)");
        tree.printLevelOrder(root);
        System.out.println("\n");

    }





}
