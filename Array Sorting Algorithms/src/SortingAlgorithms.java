import java.util.Random;

public class SortingAlgorithms {


    public SortingAlgorithms() {
    }


    private void swap(int[] list, int left, int right) {
        int temp = list[left];
        list[left] = list[right];
        list[right] =  temp;
    }


    public int[] insertSort(int[] list) {

        for (int i = 1; i < list.length; ++i) {             //dla kazdego elementu w liście
            int current = list[i];                          //pobieramy wartość obecnego
            int temp;
            int j;                                             // po pętli dostajemy indekst w który mamy wstawić
                                                                //porownujemy current z (j-1) i szukamy najmniejszego indeksu, od którego jest wiekszy
                                                                //pętla zostaje przerwana w momencie gd poprzedni element będzie mniejszy
            for (j = i; j > 0 && (current - (temp = list[j - 1]) < 0); --j){
                list[j] =  temp;
            }
            list[j] = current;
        }
        return list;
    }




    public int[] bubbleSort(int[] list) {
        int size = list.length;
        for (int currentIter = 1; currentIter < size; ++currentIter) {
            for (int left = 0; left < (size - currentIter); ++left) {
                int right = left + 1;
                if (list[left] - list[right] > 0) {
                    swap(list, left, right);
                }
            }
        }
        return list;
    }


    public int[] selectSort(int[] list) {
        int size = list.length;

        for (int slot = 0; slot < size - 1; ++slot) {                   //zaczynam od ind. 0

            int smallest = slot;                                        // pozycja wartości minimalnej
            for (int check = slot + 1; check < size; ++check) {
                if (list[check] - list[smallest] < 0){
                    smallest = check;
                }
            }

            swap(list, smallest, slot);
        }
        return list;
    }


//====================================================================

    Random random = new Random();

    public int[] quickSort(int[] list) {
        quicksort(list, 0, list.length);
        return list;
    }


    private void quicksort(int[] list, int startIndex, int endIndex) {


        if (endIndex - startIndex > 1) {                                //jesli ma wiecej niz 1 element
            int partition = partition(list, startIndex, endIndex);         //dzielimy na dwie
            quicksort(list, startIndex, partition );
            quicksort(list, partition + 1, endIndex);}
    }


    private int partition(int[] list, int nFrom, int nTo) {             //zwraca nam pozycję pivota
        //jako element dzielący bierzemy losowy
        int rnd = nFrom + random.nextInt(nTo - nFrom);              //wybieramy randomowy element wokół którego sortujemy
        swap(list, nFrom, rnd);                                             //przerzucam na pocztaek

        int value = list[nFrom];
        int idxBigger = nFrom + 1;                      //licznik idzie od początku
        int idxLower = nTo - 1;                         //licznik idzie od końca

        do {                                                                    //rób dopóki indeksy sie nie miną
            while (idxBigger <= idxLower && list[idxBigger] - value <= 0)       //oraz dopóki wartosci są nadal mniejsze
                idxBigger++;                                                        //nadal szukamy indeksu do zamiany, dwie pętle muszą się zatrzymać
            while (list[idxLower] - value > 0)
                idxLower--;
            if (idxBigger < idxLower)                                            //o ile się nie minęły, wykonujemy zamianę
                swap (list, idxBigger, idxLower);
        } while (idxBigger < idxLower);

        swap(list, idxLower, nFrom);                                            //wstawiamy na miejsce spowrotem

        return idxLower;                                                    //zwracamy pozycję pivota
    }


//======================================================================

    public int[] heapSort(int[] list){
        heapsort(list, list.length);
        return list;}

    private void heapsort(int[] heap, int dlugosc) {
        heapAdjustment(heap, dlugosc);
        for(int i = dlugosc-1; i > 0; i--){                 //tutaj zaczynamy sortowanie ostatnie, czyli zabieramy z góry drzewa najwieksze wartosci
            swap(heap, i, 0);                           //wymieniamy je z tymi na dole
            sink(heap, 0, i);                              //i znowu ustawiamy kopiec tak, by znowu na górze pojawila sie obecna najwieksza wartosc
        }
    }


    public void sink(int[] heap,int idx, int n){
        int idxOfBigger = 2 * idx + 1;                                    //szukamy indeksu potomka,
        if (idxOfBigger < n){                                               //jeśli istnieje potomek (pierwsze prawe dziecko)
            if (idxOfBigger + 1 < n &&                                          //oraz drugie lewe dziecko
                    heap[idxOfBigger] - heap[idxOfBigger+1] < 0) {              //szukam większego dziecka do porównania z rodzicem
                idxOfBigger++;
            }
            if(heap[idx] - heap[idxOfBigger] < 0){
                swap(heap, idx, idxOfBigger);                               //jeśli jest potomek większy to zamieniamy z rodzicem (swap_
                sink(heap, idxOfBigger, n);                                    //i wykonujemy tą funkce w górę drzewa, az gałąź bedzie poprawna
            }                                                                   //sprawdzamy tez czy przy przerzucaniu nie zaburzyliśmy kolejności
        }
    }
    void heapAdjustment(int[] heap, int dlugosc) {                          //potomkowie indeksu i: 2*i+1, 2*i+2
        for(int i = (dlugosc -1) / 2; i >= 0; i--)                             //rodzice: (i-1)/2
            sink(heap, i, dlugosc);                                            //sink "wstawia" w dobre miejsce kopca
    }


//=====================================================================

    public int[] mergeSort(int[] list) {
        return mergesort(list, 0, list.length - 1);
    }

    int[] mergesort(int arr[], int lewy, int prawy)   //liczniki na poczatku i końcu tablicy
    {
        if (lewy < prawy) {
                                                            // znajdz srodek tablicy
            int srodek = lewy + (prawy - lewy)/2;

                                                            //znowu sortuj połówki, rekurencyjne wywolanie
            mergesort(arr, lewy, srodek);
            mergesort(arr, srodek + 1, prawy);

                                                                // scal tablice
            merge(arr, lewy, srodek, prawy);
        }
        return arr;
    }

    void merge(int arr[], int lewy, int srodek, int prawy)
    {
                                                                //znajdujemy rozmiary nowych tablic
        int n1 = srodek - lewy + 1;
        int n2 = prawy - srodek;

        int L[] = new int[n1];
        int R[] = new int[n2];

                                                                //kopiuj do tymczasowych tablic
        for (int i = 0; i < n1; ++i)
            L[i] = arr[lewy + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[srodek + 1 + j];

                                                      //indeksy dla podtablic
        int i = 0, j = 0;

                                              // początowy indeks połączonych tablic
        int k = lewy;
                                                 //dopoki indeksy nie przekroczą rozmiarów tablic
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {                  //wstaw ten większy do "nowej" tablicy
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

                                                            //skopiuj pozostałe elementy jesli takie są z tablicy lewej
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
                                                            //skopiuj pozostałe elementy jesli takie są z tablicy prawej
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }






}
