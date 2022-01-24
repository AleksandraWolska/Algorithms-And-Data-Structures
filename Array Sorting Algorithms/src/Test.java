import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

public class Test {

    public Test (int n) {

        Random rand = new Random();


        int[] lista = new int[n];
        int[] listaInsert = new int[n];
        int[] listaBubble = new int[n];
        int[] listaSelect = new int[n];
        int[] listaQuick = new int[n];
        int[] listaHeap = new int[n];
        int[] listaMerge = new int[n];


        for (int i = 0; i < n; i++) {
            lista[i] = rand.nextInt((n -1)+1);
        }


        for (int i = 0; i < lista.length; i++) {
            listaInsert[i] = lista[i];
            listaBubble[i] = lista[i];
            listaSelect[i] = lista[i];
            listaQuick[i] = lista[i];
            listaHeap[i] = lista[i];
            listaMerge[i] = lista[i];
        }


        SortingAlgorithms algorithms = new SortingAlgorithms();



        long start = 0;
        long end = 0;

        System.out.println("DANE LOSOWE\n");
        start = System.currentTimeMillis();
        algorithms.insertSort(listaInsert);
        end = System.currentTimeMillis();
        System.out.println("Insert sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.bubbleSort(listaBubble);
        end = System.currentTimeMillis();
        System.out.println("Bubble sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.selectSort(listaSelect);
        end = System.currentTimeMillis();
        System.out.println("Select sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.quickSort(listaQuick);
        end = System.currentTimeMillis();
        System.out.println("Quick sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.heapSort(listaHeap);
        end = System.currentTimeMillis();
        System.out.println("Heap sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.mergeSort(listaMerge);
        end = System.currentTimeMillis();
        System.out.println("Merge sort: " + (end - start));



        Arrays.fill(listaInsert, 0);
        Arrays.fill(listaBubble, 0);
        Arrays.fill(listaSelect, 0);
        Arrays.fill(listaQuick, 0);
        Arrays.fill(listaHeap, 0);
        Arrays.fill(listaMerge, 0);


        Arrays.sort(lista);



        for (int i = 0; i < lista.length; i++) {
            listaInsert[i] = lista[i];
            listaBubble[i] = lista[i];
            listaSelect[i] = lista[i];
            listaQuick[i] = lista[i];
            listaHeap[i] = lista[i];
            listaMerge[i] = lista[i];
        }


        System.out.println("\nDANE POSORTOWANE ROSNĄCO\n");
        start = System.currentTimeMillis();
        algorithms.insertSort(listaInsert);
        end = System.currentTimeMillis();
        System.out.println("Insert sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.bubbleSort(listaBubble);
        end = System.currentTimeMillis();
        System.out.println("Bubble sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.selectSort(listaSelect);
        end = System.currentTimeMillis();
        System.out.println("Select sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.quickSort(listaQuick);
        end = System.currentTimeMillis();
        System.out.println("Quick sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.heapSort(listaHeap);
        end = System.currentTimeMillis();
        System.out.println("Heap sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.mergeSort(listaMerge);
        end = System.currentTimeMillis();
        System.out.println("Merge sort: " + (end - start));



        //zamykamy w Integer, sortujemy i mapujemy do prymitywnych
        lista = IntStream.of(lista).boxed().sorted(Comparator.reverseOrder()).mapToInt(i -> i).toArray();


        Arrays.fill(listaInsert, 0);
        Arrays.fill(listaBubble, 0);
        Arrays.fill(listaSelect, 0);
        Arrays.fill(listaQuick, 0);
        Arrays.fill(listaHeap, 0);
        Arrays.fill(listaMerge, 0);




        for (int i = 0; i < lista.length; i++) {
            listaInsert[i] = lista[i];
            listaBubble[i] = lista[i];
            listaSelect[i] = lista[i];
            listaQuick[i] = lista[i];
            listaHeap[i] = lista[i];
            listaMerge[i] = lista[i];
        }


        System.out.println("\nDANE POSORTOWANE MALEJĄCO\n");
        start = System.currentTimeMillis();
        algorithms.insertSort(listaInsert);
        end = System.currentTimeMillis();
        System.out.println("Insert sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.bubbleSort(listaBubble);
        end = System.currentTimeMillis();
        System.out.println("Bubble sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.selectSort(listaSelect);
        end = System.currentTimeMillis();
        System.out.println("Select sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.quickSort(listaQuick);
        end = System.currentTimeMillis();
        System.out.println("Quick sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.heapSort(listaHeap);
        end = System.currentTimeMillis();
        System.out.println("Heap sort: " + (end - start));

        start = System.currentTimeMillis();
        algorithms.mergeSort(listaMerge);
        end = System.currentTimeMillis();
        System.out.println("Merge sort: " + (end - start));


    }




}