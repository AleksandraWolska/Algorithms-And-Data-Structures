import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedListWithHeadAndTail<E> extends AbstractList<E>{


    private class Element{

        private E value;

        private Element next;

        Element(E data) {
            this.value = data;}

        public E getValue() {
            return value;}

        public void setValue(E value) {
            this.value = value;}

        public Element getNext() {
            return next;}

        public void setNext(Element next) {
            this.next = next;}
    }

    Element head=null;
    Element tail = null;


    public OneWayLinkedListWithHeadAndTail(){ }


    public int count() {
        Iterator<E> iter = this.iterator();
        int counter = 0;
        while (iter.hasNext()) {
            counter++;
            iter.next();
        }
        return counter;
    }

    void wyswietl() {
        for (E e : this) {
            System.out.println(e.toString());
        }
    }


    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }


    private Element getElement(int index){

        Element actElem = head;

        while(index > 0 && actElem != null){
            index--;
            actElem = actElem.getNext();
        }
        return actElem;
    }

    @Override
    public boolean add(E e) {                       //enqueue
        Element newElem = new Element(e);
        if (head == null){
            head = newElem;
            tail = newElem;
            return true;
        }
        else {
            tail.setNext(newElem);
            tail = newElem;
        }
        return true;
    }


    @Override
    public boolean add(int index, E data) {
        if (index < 0) return false;
        Element newElem = new Element(data);

        if (index == 0) {                               //na poczatku listy
            newElem.setNext(head);
            head = newElem;
            return true;

        } else if (index == size()) {                    //działa jak zwykle add
            tail.setNext(newElem);
            tail = newElem;
        } else {
            Element actElem = getElement(index - 1);
            if (actElem == null)
                return false;
            newElem.setNext(actElem.getNext());
            actElem.setNext(newElem);

        }
        return true;

    }



    @Override
    public int indexOf(E data) {
        int pos = 0;
        Element actElem = head;

        while(actElem != null) {
            if(actElem.getValue().equals(data)) {
                return pos;
            }
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }


    @Override
    public boolean contains(E data) {
        return indexOf(data) >= 0;
    }

    @Override
    public E get(int index) {
        Element actElem = getElement(index);
        return actElem == null ? null : actElem.getValue();
    }

    @Override               //zwraca element przed nadpisaniem
    public E set (int index, E data) {
        Element actElem = getElement(index);
        if (actElem == null)
            return null;
        E elemData = actElem.getValue();
        actElem.setValue(data);
        return elemData;
    }

    public E removeDequeue(){               //zawsze usuwam indeks 0
        if (head == null) {             //jesli jest pusta
            return null;
        }
            if (head == tail) {          //jesli jest jeden element
                tail = null;
            }
            E retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }


    @Override
    public E remove(int index) {

        if (head == null) {             //jesli jest pusta
            return null;
        }

        if (index == 0){
            if(head == tail) {          //jesli jest jeden element
                tail = null;
            }
            E retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }

        Element prevElem = getElement(index - 1);        //bierzemy element przed usuwanym
        if (prevElem == null || prevElem.getNext() == null)
            return null;

        E retValue = prevElem.getNext().getValue();          //wartość zwrócona będzie ta kolejna
        if (prevElem.getNext().equals(tail)) {              //jeśli kolejny to ogon, to podkładamy go jako
            tail = prevElem;
        }

        prevElem.setNext(prevElem.getNext().getNext());
        return retValue;
    }





    @Override
    public boolean remove(E value) {

        if(head == null)
            return false;

        if (head.getValue().equals(value)){    //jesli usuwam z glowy
            head = head.getNext();
            return true;
        }


        Element prevElem = head;
                                                                        //szukamy elementu bezpośrednio przed tym, którry chcemy usunąć
        while (prevElem.getNext() != null && !prevElem.getNext().getValue().equals(value)) {
            prevElem = prevElem.getNext();
        }

        if (prevElem.getNext() == null) {
            return false;
        } else {
            if (prevElem.getNext().equals(tail)) {
                tail = prevElem;
            }
            prevElem.setNext(prevElem.getNext().getNext());
            return true;
        }

    }

    @Override
    public int size() {

        if(tail == null) {
            return 0;
        } else {
            return indexOf(tail.value)+ 1;
        }

    }



    private class InnerIterator implements Iterator<E> {
        Element actElem;

        public InnerIterator() {
            actElem = head;
        }

        @Override
        public boolean hasNext() {
            return actElem != null;
        }

        @Override
        public E next() {
            E value = actElem.getValue();
            actElem = actElem.getNext();
            return value;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    private class InnerListIterator implements Iterator<E> {
        Element actElem;

        public InnerListIterator() {
            actElem = head;
        }

        @Override
        public boolean hasNext() {
            return actElem != null;
        }

        @Override
        public E next() {
            E value = actElem.getValue();
            actElem = actElem.getNext();
            return value;
        }
    }


    @Override
    public ListIterator<E> listIterator() {

        OneWayLinkedListWithHeadAndTail<E> innerList = new OneWayLinkedListWithHeadAndTail<>();
        return new ListIterator<E>() {
            int cursor = -1;
            boolean wasNextCalled = false;
            boolean wasPreviousCalled = false;
            int size = size();

            @Override
            public boolean hasNext() {
                return cursor < size - 1;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    wasNextCalled = true;
                    wasPreviousCalled = false;
                    return get(++cursor);
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public boolean hasPrevious() {
                return cursor >= 0;
            }

            @Override
            public E previous() {
                if (hasPrevious()) {
                    wasPreviousCalled = true;
                    wasNextCalled = false;
                    return get(cursor--);
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public int nextIndex() {
                if (hasNext())
                    return cursor + 1;
                else return size;
            }

            @Override
            public int previousIndex() {
                if (hasPrevious())
                    return cursor;
                else return -1;
            }

            @Override
            public void remove() {
                if (wasNextCalled) {
                    wasNextCalled = false;
                    innerList.remove(cursor);
                    cursor--;
                } else if (wasPreviousCalled) {
                    wasPreviousCalled = false;
                    innerList.remove(cursor+1);
                } else {
                    throw new IllegalStateException();
                }
                size--;
            }

            @Override
            public void set(E e) {
                if (wasNextCalled) {
                    wasNextCalled = false;
                    innerList.set(cursor, e);
                } else if (wasPreviousCalled) {
                    wasPreviousCalled = false;
                    innerList.set(cursor+1, e);
                } else {
                    throw new IllegalStateException();
                }
            }

            @Override
            public void add(E e) {
                if (!hasPrevious())
                    innerList.add(0, e);

                wasNextCalled = false;
                wasPreviousCalled = false;
                innerList.add(cursor+1, e);
                cursor++;
                size++;
            }
        };
    }


}
