import java.util.*;


class Graf {
    int tabDystans[];
    Set<Integer> odwiedzone;
    PriorityQueue<Vertex> kolejka;
    int liczbaWierzcholkow;
    List<List<Vertex> > graf;

    public Graf(int liczbaWierzcholkow) {
        this.liczbaWierzcholkow = liczbaWierzcholkow;
        tabDystans = new int[liczbaWierzcholkow];
        odwiedzone = new HashSet<Integer>();
        kolejka = new PriorityQueue<Vertex>(liczbaWierzcholkow, new Vertex());
    }



Random rand = new Random();



    public void dijkstra(List<List<Vertex> > graf, int start)
    {
        this.graf = graf;

        for (int i = 0; i < liczbaWierzcholkow; i++){       //ustawiam dystanse do wierzcholkow na wart. poczatkowa
            tabDystans[i] = Integer.MAX_VALUE;
        }

        kolejka.add(new Vertex(start, 0));      //dodaje pierwszy (source)
        tabDystans[start] = 0;                       //ustawiam dystans na 0

        while (odwiedzone.size() != liczbaWierzcholkow) {       //dopoki wszystkie nie zostaly odwiedzone

            if (!kolejka.isEmpty()) {
                int u = kolejka.remove().miasto;        //z najmniejszym dystansem
                odwiedzone.add(u);                      //dodaj do odwiedzonych
                ogarnijSasiadow(u);                     //ogarnianie sąsiadow obecnego wierzchołka
            } else {
                odwiedzone.add(rand.nextInt(1000));     //w ramach realizacji warunku
            }

        }
    }



    private void ogarnijSasiadow(int u)   {

        int nowyDystans = -1;

        for (int i = 0; i < graf.get(u).size(); i++) {      //wszystkie sąsiadujące wierzchołki z listy
            Vertex v = graf.get(u).get(i);

            if (!odwiedzone.contains(v.miasto)) {           //jesli nie zostal odwiedzony

                nowyDystans = tabDystans[u] + v.koszt;

                if (nowyDystans < tabDystans[v.miasto]) {     //porównuje dystanse, ew. update kosztu
                    tabDystans[v.miasto] = nowyDystans;
                }

                kolejka.add(new Vertex(v.miasto, tabDystans[v.miasto]));    //dodaj obecny wierzchołek do kolejki
            }
        }

    }


    void DFS(int ilosc) {
        boolean odwiedzeni[] = new boolean[ilosc];
        przejdzDeep(0, odwiedzeni);
    }

    void przejdzDeep(int ilosc, boolean odwiedzeni[])
    {
        odwiedzeni[ilosc] = true;
        System.out.print(ilosc + " ");

        Iterator<Vertex> i = graf.get(ilosc).listIterator();    //rekurencja dla wszytskich sąsiadow danego wierzcholka
        while (i.hasNext())
        {
            int n = i.next().miasto;
            if (!odwiedzeni[n])
                przejdzDeep(n, odwiedzeni);
        }
    }






}