package com.company;

import java.util.Iterator;

public abstract class AbstractList<E> implements IList<E> {
    @Override
    public String toString() {
        String temp = "";

        if (!isEmpty()) {
            for (E e : this) {
                temp = temp + ", " + e;
            }
        }

        return temp;
    }
    // ^ - bitowa różnica symetryczna
    @Override
    public int hashCode() {
        int hashCode = 0;
        for (E e : this)
            hashCode ^= e.hashCode();
        return hashCode;
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if(object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        return equals((IList<E>) object);
    }


    public boolean equals(IList<E> other) {
        boolean flag =  true;
        if (other == null || size() != other.size()) {
            return false;
        } else {
            Iterator<E> i = iterator();
            Iterator<E> j = other.iterator();

            while (i.hasNext() && j.hasNext()) {
                if(!i.next().equals(j.next())) {
                    flag = false;
                };
            }
            if (!flag) {
                return !i.hasNext() && !j.hasNext();
            } else {
                return false;
            }

        }
    }

}
