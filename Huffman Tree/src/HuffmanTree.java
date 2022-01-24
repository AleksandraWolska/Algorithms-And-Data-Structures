
import java.util.*;

public class HuffmanTree {
    private Node root;


    public HuffmanTree(Map<Character, Integer> czestosci) {

        PriorityQueue<Node> litery = new PriorityQueue<Node>(czestosci.size(), new NodeComparator());


        for (char ch : czestosci.keySet()) {       //dodaję do kolejki
            Node newNode = new Node();
            newNode.litera = ch;
            newNode.czestosc = czestosci.get(ch);
            litery.add(newNode);
        }

        while (litery.size() > 1) {         //tworzę drzewo znaków

            Node newNode = new Node();
            newNode.left = litery.poll();
            newNode.right = litery.poll();
            newNode.czestosc = newNode.left.czestosc + newNode.right.czestosc;     //wyzsze nody maja sume czestosci dzieci

            litery.add(newNode);    //dodajemy kawałek drzewa na koniec kolejki, przy "poll" zostanie posortowane
        }

        root = litery.remove();
    }



    public String rozkoduj(String input) {
        System.out.println("\nRozkodowanie ciągu:");
        String result = "";
        Node n = root;
        for (int i = 0; i < input.length(); i++) {

            char ch = input.charAt(i);
            if (ch == '0') {            // 0-left; 1-right
                n = n.left;             //poruszamy sie w dol drzewa do momentu natrafienia na lisc
            } else {
                n = n.right;
            }
            if (n.left == null) //n jest lisciem
            {
                result = result + n.litera;
                n = root;
                //System.out.println((i+1) + ": " + result);
            }
        }
        return result;
    }



    public Map<Character, String> getZakodowanaMapa() {
        System.out.println("\nIlości i kody znaków:");
        Map<Character, String> map = new HashMap<Character, String>();

        if (root != null) {
            root.nadajKod(map, "");         //ta operacja wywoluje sie rekurencyjnie, lewo -0, prawo -1
        }

        System.out.println(map.toString());
        return map;
    }


    public String getZakodowanyString(Map<Character, String> map, String str){

        char[] arr = str.toCharArray();
        String wynik = "";
        for (char c : arr){
            wynik = wynik + map.get(c);
        }

        return wynik;

    }

    public static int iloscDzieci(Node root) {
        if (null == root)
            return 0;

        int nLeftSubtree = iloscDzieci(root.left);
        int nRightSubtree = iloscDzieci(root.right);
        return nLeftSubtree + nRightSubtree + 1;
    }




    class Node implements Comparable<Node> {
        public char litera;
        public int czestosc;
        public Node left;
        public Node right;

        public int compareTo(Node other) {
            return czestosc - other.czestosc;
        }


        public void nadajKod(Map<Character, String> map, String prefix) {

            if (litera!=0 && litera != ' '){
            System.out.println(String.format("%-6s - %-3s - %-6s",  litera, czestosc, prefix));
            } else if (litera == ' ') {
                System.out.println(String.format("spacja - %-3s - %-6s", czestosc, prefix));
            }


            if (left == null)
            {
                map.put(litera, prefix);
            } else {
                left.nadajKod(map, prefix + "0");
                right.nadajKod(map, prefix + "1");
            }

        }

        public String toString() {
            if (litera == ' ') {
                String spacja = "spacja";
                return String.format("\n%7s - %-3s", spacja,  czestosc);
            }
            return String.format("\n%7s - %-3s", litera, czestosc);

        }

        public int getCzestosc() {
            return czestosc;
        }
    }

    private class NodeComparator implements Comparator<Node>{

        public int compare(Node n1, Node n2) {
            if (n1.czestosc < n2.czestosc)
                return -1;
            else if (n1.czestosc > n2.czestosc)
                return 1;
            else {
                if (iloscDzieci(n1) < iloscDzieci(n2))
                    return -1;
                else if (iloscDzieci(n1) > iloscDzieci(n2))
                    return 1;
                else return 0;
            }
        }
    }
}