import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T> {

    T [] stack;
    private int maxSize;
    private int size;


    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = (T[]) new Object[maxSize];

    }


    void reverseStack() {
        T[] reversedStack = (T[]) new Object[maxSize];
        int counter = 0;
        for (int i = stack.length - 1; i >= 0; i--) {
            if (stack[i] != null) {
                reversedStack[counter] = stack[i];
                counter++;
            }
        }
        stack = reversedStack;
    }



    @Override
    public void push(T value) {
        if (isFull()) {
            System.err.println("Przepełnienie stosu!");
        } else {
            stack[size++] = value;
        }
    }

    @Override
    public boolean isFull() {
        return size == stack.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            return stack[--size];
        }
    }

    @Override
    public T top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        else {
            return stack[size -1];
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }


    String showStack() {
        //System.out.println();
        int n = size;
        String result = "";
        for (int i = 1; i <= n; i++ ) {
            result = result  + "    " + stack[n - i];
        }
        return result + "\n";
    }
}