import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue; // the randomized queue
    private int size; // the size of queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resizes the array, taken from COS 226 website
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Unable to add null");
        }
        // doubles size of the array if it reaches max capacity
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[size++] = item;
    }


    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item to dequeue");
        }
        int randomIndex = StdRandom.uniformInt(size);
        Item item = queue[randomIndex];
        queue[randomIndex] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        // resize array by 1/2 if size reaches 1/4 of capacity
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item to dequeue");
        }
        return queue[StdRandom.uniformInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();

    }

    private class RandomQueueIterator implements Iterator<Item> {
        private Item[] randomQueue; // queue of random items
        private int items; // items in queue

        // copies queue and shuffles it
        public RandomQueueIterator() {
            items = 0;
            randomQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomQueue[i] = queue[i];
            }
            StdRandom.shuffle(randomQueue);
        }

        // checks if number of items is less than size of queue.
        public boolean hasNext() {
            return items < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Empty");
            }
            return randomQueue[items++];

        }
    }


    // unit testing (optional)
    public static void main(String[] args) {

    }

}
