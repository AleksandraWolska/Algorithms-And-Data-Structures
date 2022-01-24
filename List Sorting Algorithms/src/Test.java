import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Test {

    public Test (int n) {

        Random rand = new Random();

        Comparator comp = new NaturalComparator();

        ArrayList<Integer> lista = new ArrayList<Integer>();

        ArrayList<Integer> listaInsert = new ArrayList<Integer>();
        ArrayList<Integer> listaBubble = new ArrayList<Integer>();
        ArrayList<Integer> listaSelect = new ArrayList<Integer>();
        ArrayList<Integer> listaQuick = new ArrayList<Integer>();
        ArrayList<Integer> listaHeap = new ArrayList<Integer>();
        ArrayList<Integer> listaMerge = new ArrayList<Integer>();




        for (int i = 0; i < n; i++) {
            lista.add(rand.nextInt((n -1)+1));
        }


        for (Integer i : lista) {
            listaInsert.add(i);
            listaBubble.add(i);
            listaSelect.add(i);
            listaQuick.add(i);
            listaHeap.add(i);
            listaMerge.add(i);
        }


        SortingAlgorithms<Integer> algorithms = new SortingAlgorithms<Integer>(comp);



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



            listaInsert.clear();
            listaBubble.clear();
            listaSelect.clear();
            listaQuick.clear();
            listaHeap.clear();
            listaMerge.clear();


        Collections.sort(lista);



        for (Integer i : lista) {
            listaInsert.add(i);
            listaBubble.add(i);
            listaSelect.add(i);
            listaQuick.add(i);
            listaHeap.add(i);
            listaMerge.add(i);
        }


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



        Collections.sort(lista, Collections.reverseOrder());


            listaInsert.clear();
            listaBubble.clear();
            listaSelect.clear();
            listaQuick.clear();
            listaHeap.clear();
            listaMerge.clear();




        for (Integer i : lista) {
            listaInsert.add(i);
            listaBubble.add(i);
            listaSelect.add(i);
            listaQuick.add(i);
            listaHeap.add(i);
            listaMerge.add(i);
        }


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


    }




}
