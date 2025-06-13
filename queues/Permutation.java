import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]); // k number of prints
        RandomizedQueue<String> randomQueue = new RandomizedQueue<>();

        // reads random sequence of k strings
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            randomQueue.enqueue(item);
        }
        // prints that random sequence
        for (int i = 0; i < k; i++) {
            StdOut.println(randomQueue.dequeue());


        }
    }
}

