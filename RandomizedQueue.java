import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private Node current;
    private int size;
    private Random random; // This is just good practice im pretty sure??

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        Node oldLast = new Node();
        if (isEmpty()) {
            first = oldLast;
            first.item = item;
            last = oldLast;
            last.item = item;
        } else {
            oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.prev = oldLast;
            oldLast.next = last;
        }
        System.out.println(last.item);
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }

        int index = random.nextInt(size);
        Node current = last;

        if (index == 0) {
            last = last.next;
            if (last != null) {
                last.prev = null;
            }
        } else {
            for (int i = 0; i < index; i++) {
                current = current.prev;
            }

            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                last = current.next;
            }

            if (current.next != null) {
                current.next.prev = current.prev;
            }
        }

        size--;
        return current.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        random = new Random();
        int index = random.nextInt(size);
        Node current = last;
        for (int i = 0; i < index; i++) {
            current = current.prev;
        }
        Item sample = current.item;
        return sample;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator() {
            Node current = first;

            @Override
            public boolean hasNext() { // Step 2
                return current != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Item value = current.item;
                    Node nextNode = current.next;
                    current = nextNode;
                    return value;
                }
            } // no point

            @Override
            public boolean hasPrevious() {
                return current != null;
            } // no point

            @Override
            public Object previous() {
                return 0;
            }

            @Override
            public int nextIndex() {
                return 0;
            }

            @Override
            public int previousIndex() {
                return 0;
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(Object o) {

            }

            @Override
            public void add(Object o) {

            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue ben = new RandomizedQueue();
        ben.enqueue("I");
        ben.enqueue("will");
        ben.enqueue("become");
        ben.enqueue("the");
        ben.enqueue("King");
        ben.enqueue("of");
        ben.enqueue("Hell.");

        System.out.println("This is my sample: " + ben.sample());
        System.out.println("This is my sample: " + ben.sample());

        System.out.println("This is my dequeue: " + ben.dequeue());
        System.out.println("This is my dequeue: " + ben.dequeue());
        System.out.println("This is my dequeue: " + ben.dequeue());

        Iterator iterator = ben.iterator();
        int i = 0;
        while (iterator.hasNext() && i < ben.size()) {
            System.out.println(iterator.next());
            i++;
        }

    }
}
