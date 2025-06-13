import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first; // first item in list
    private Node<Item> last; // last item in list
    private int size; // size of deque

    private static class Node<Item> {

        private Item item; // item in the queue
        private Node<Item> next; // next item in node
        private Node<Item> previous; // previous item in node
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item is null");
        }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        }
        else {
            oldFirst.previous = first;
        }
        size++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.previous = oldLast;
        if (isEmpty()) {


            first = last;
        }
        else oldLast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item to remove");
        }

        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        }
        else {
            first.previous = null;
        }

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item to remove");
        }
        Item item = last.item;
        last = last.previous;
        size--;
        if (isEmpty()) {
            first = null;
        }
        else {
            last.next = null;
        }
        return item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> node = first;

            public boolean hasNext() {
                return node != null;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Item item = node.item;
                node = node.next;
                return item;
            }

        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        // creates deque of integers and runs unit tests
        Deque<Integer> deque = new Deque<>();
        for (int i = 1; i < 11; i++) {
            deque.addFirst(i);
        }
        for (int i = 1; i < 11; i++) {
            int lastRemoved = deque.removeLast();
            if (lastRemoved != i) {
                StdOut.println("error");
            }
            else {
                StdOut.println(lastRemoved);
            }
        }
        for (int i = 10; i > 0; i--) {
            deque.addLast(i);
        }
        for (int i = 10; i > 0; i--) {
            int lastRemoved = deque.removeFirst();
            if (lastRemoved != i) {
                StdOut.println("error");
            }
            else {
                StdOut.println(lastRemoved);
            }

        }
        // utilizes iterator and for each loop to print numbers
        for (int i = 1; i < 11; i++) {
            deque.addFirst(i);
        }
        StdOut.println(deque.size());
        StdOut.println(deque.isEmpty());
        Iterator<Integer> iterator = deque.iterator();
        for (Integer i : deque) {
            StdOut.println("Item: " + i);
            if (iterator.hasNext()) {
                StdOut.println("next Item: " + iterator.next());
            }
        }


    }
}

