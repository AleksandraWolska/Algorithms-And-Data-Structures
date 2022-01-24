package com.company;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedListWithHead <E> extends AbstractList<E>{


    private class Element{

        private E value;
        private Element next;

        Element(E data){
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

    public OneWayLinkedListWithHead(){}


    public int count() {
        Iterator<E> iter = this.iterator();
        int counter = 0;
        while (iter.hasNext()) {
            counter++;
            iter.next();
        }
        return counter;
    }





    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void clear() {
        head = null;
    }

    /** zwraca referencję na Element, wewnętrzną klasę */
    private Element getElement(int index){
        Element actElem = head;

        while(index > 0 && actElem != null){
            index--;
            actElem = actElem.getNext();
        }
        return actElem;
    }

    @Override
    public boolean add(E e) {
        Element newElem = new Element(e);
        if (head == null){
            head = newElem;
            return true;
        }
        Element tail = head;
        while(tail.getNext() != null)
            tail = tail.getNext();
        tail.setNext(newElem);
        return true;
    }


    @Override           //wstawianie w jakies miejsce dane indeksem
    public boolean add(int index, E data) {
        if (index < 0) return false;
        Element newElem = new Element(data);
        if (index == 0) {           //na poczatku listy
            newElem.setNext(head);
            head = newElem;
            return true;
        }

        Element actElem = getElement(index - 1);            //biorę element przed i robię powiązania
        if (actElem == null)
            return false;
        newElem.setNext(actElem.getNext());
        actElem.setNext(newElem);
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


    @Override
    public E remove(int index) {

        if (head == null)
            return null;

        if (index == 0){
            E retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }

        Element actElem = getElement(index - 1);        //element przed usuwanym
        if (actElem == null || actElem.getNext() == null)
            return null;
        E retValue = actElem.getNext().getValue();          //następne usuwanego przypisuje elementowi usuwanemu
        actElem.setNext(actElem.getNext().getNext());
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

        Element actElem = head;
        while (actElem.getNext() != null && !actElem.getNext().getValue().equals(value)) {
            actElem = actElem.getNext();
        }

        if (actElem.getNext() == null) {
            return false;
        } else {
            actElem.setNext(actElem.getNext().getNext());
            return true;
        }

    }

    @Override
    public int size() {
        int pos=0;
        Element actElem=head;
        while(actElem!=null)
        {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;
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

        OneWayLinkedListWithHead<E> innerList = new OneWayLinkedListWithHead<>();
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
