import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        // Leave empty
    }

    // is the deque empty?
    public boolean isEmpty() {
        // can change if reference node
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        // Creating the linked list
        Node oldFirst = new Node();
        if (isEmpty()){
            first = oldFirst;
            last = oldFirst;
        } else {
            oldFirst = first;
            first = new Node();
            first.next = oldFirst;
            oldFirst.prev = first;

        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // Creating the linked list
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        oldLast.next = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Item removed = first.item;
        first = first.next;
        first.prev = null;
        size--;
        return removed;
    }

    // remove and return the item from the back
    public Item removeLast() {
        // Irrelevant to Deque 😴
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
            Item removed = last.item;
            last = last.prev;
            size--;
            return removed;
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

        Node current = first;

        return new ListIterator() {
            @Override
            public boolean hasNext() { // Step 2
                return current != null;
            }

            @Override
            public Object next() {
                if (!hasNext()){
                    return new NoSuchElementException();
                } else {
                    Item value = current.item;
                    last = current;
                    Node nextNode = current.next;
                    // Do I need to use prev here?
                    current.item = nextNode.item;
                    current.next = nextNode.next;
                    return value;
                }
            }

            @Override
            public boolean hasPrevious() {
                return current != null;
            }

            @Override
            public Object previous() { // Can't work backwards since it's a singely linked list, but im pretty sure if I find the node before current then its gucci
                if (!hasPrevious()){
                    return new NoSuchElementException();
                } else {
                    Item value = current.item;


                }
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


    }

}