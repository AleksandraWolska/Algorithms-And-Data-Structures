import java.util.*;

public class SortingAlgorithms <T> {

    private final Comparator<T> comparator;
    public SortingAlgorithms(Comparator<T> comparator) {
        this.comparator = comparator;
    }


    public List<T> insertSort(List<T> list) {

        for (int i = 1; i < list.size(); ++i) {             //dla kazdego elementu w liście
            T current = list.get(i);                          //pobieramy wartość obecnego
            T temp;
            int j;                                             // po pętli dostajemy indekst w który mamy wstawić
            //porownujemy current z (j-1) i szukamy najmniejszego indeksu, od którego jest wiekszy
            //pętla zostaje przerwana w momencie gd poprzedni element będzie mniejszy
            for (j = i; j > 0 && (comparator.compare(current, temp = list.get(j - 1)) < 0); --j){
                list.set(j, temp);
            }
            list.set(j, current);
        }
        return list;
    }




    public List<T> bubbleSort(List<T> list) {
        int size = list.size();
        for (int currentIter = 1; currentIter < size; ++currentIter) {
            for (int left = 0; left < (size - currentIter); ++left) {
                int right = left + 1;
                if (comparator.compare(list.get(left), list.get(right)) > 0) {
                    swap(list, left, right);
                }
            }
        }
        return list;
    }
    private void swap(List<T> list, int left, int right) {
        T temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }

    public List<T> selectSort(List<T> list) {
        int size = list.size();

        for (int slot = 0; slot < size - 1; ++slot) {                   //zaczynam od ind. 0

            int smallest = slot; // pozycja wartości minimalnej
            for (int check = slot + 1; check < size; ++check) {
                if (comparator.compare(list.get(check), list.get(smallest)) < 0){
                    smallest = check;
                }
            }

            swap(list, smallest, slot);
        }
        return list;
    }




    Random random = new Random();

    public List<T> quickSort(List<T> list) {
        quicksort(list, 0, list.size());
        return list;
    }


    private void quicksort(List<T> list, int startIndex, int endIndex) {

        if (endIndex - startIndex > 1) {
            int partition = partition(list, startIndex, endIndex);
            quicksort(list, startIndex, partition );
            quicksort(list, partition + 1, endIndex);}}

    private int partition(List<T> list, int nFrom, int nTo) {
                                                            //jako element dzielący bierzemy losowy
        int rnd = nFrom + random.nextInt(nTo - nFrom);
        swap(list, nFrom, rnd);

        T value = list.get(nFrom);
        int idxBigger = nFrom + 1, idxLower = nTo - 1;

        do {
            while (idxBigger <= idxLower && comparator.compare(list.get(idxBigger),value) <= 0)
                idxBigger++;
            while (comparator.compare(list.get(idxLower), value) > 0)
                idxLower--;
            if (idxBigger < idxLower)
                swap (list, idxBigger, idxLower);
        } while (idxBigger < idxLower);


        swap(list,idxLower,nFrom);

        return idxLower;
    }




    public List<T> heapSort(List<T> list){
        heapsort(list, list.size());
        return list;}

    private void heapsort(List<T> heap, int n) {
        heapAdjustment(heap, n);
        for(int i = n-1; i > 0; i--){
            swap(heap, i, 0);
            sink(heap, 0, i);
        }
    }


    public void sink(List<T> heap,int idx, int n){
        int idxOfBigger = 2 * (idx + 1);
        if (idxOfBigger < n){
            if (idxOfBigger + 1 < n &&
                    comparator.compare(heap.get(idxOfBigger), heap.get(idxOfBigger+1)) < 0)
                idxOfBigger++;
            if(comparator.compare(heap.get(idx), heap.get(idxOfBigger)) < 0){
                swap(heap, idx, idxOfBigger);
                sink(heap, idxOfBigger, n);
            }
        }
    }
    void heapAdjustment(List<T> heap,int n) {
        for(int i= (n-1) / 2; i >= 0; i--)
            sink(heap, i, n);
    }



    public List<T> mergeSort(List<T> list) {
        return mergesort(list, 0, list.size() - 1);
    }


    private List<T> mergesort(List<T> list, int startIndex, int endIndex) {

        if (startIndex == endIndex) {
            List<T> result = (List<T>) (new ArrayList<Object>());
            result.add(list.get(startIndex));
            return result;
        }

        int splitIndex = startIndex + (endIndex - startIndex) / 2;
        return merge(mergesort(list, startIndex, splitIndex),
                mergesort(list, splitIndex + 1, endIndex));
    }

    @SuppressWarnings("unchecked")
    private List<T> merge(List<T> left, List<T> right) {

        List<T> result = (List<T>) new ArrayList<Object>();
        Iterator<T> l = left.iterator();
        Iterator<T> r = right.iterator();
        T elemL = null, elemR = null;

        boolean contL, contR;
        if (contL = l.hasNext()) elemL = l.next();
        if (contR = r.hasNext()) elemR = r.next();

        while (contL && contR) {

            if (comparator.compare(elemL, elemR) <= 0) {
                result.add(elemL);
                if (contL = l.hasNext()) elemL = l.next();
                else result.add(elemR);
            } //już odczytany element drugiej listy do wyniku
            else {
                result.add(elemR);
                if (contR = r.hasNext()) elemR = r.next();
                else result.add(elemL);
            } //już odczytany element pierwszej listy do wyniku
        }
        while (l.hasNext()) result.add(l.next());
        while (r.hasNext()) result.add(r.next());
        return result;
    }








}
