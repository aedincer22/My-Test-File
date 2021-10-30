/* TCSS 342 - Summer 2021
 * Assignment 1 - Evolved Names
 * Abdullah Enes
 */

import java.util.Iterator;

/**
 * A singly linked implementation.
 */

public class MyLinkedList <Type extends Comparable<Type>>  {

    /** node in front of the list**/
    private Node<Type> first;
    /**node in the end of the list**/
    private Node<Type> last;
    /**size**/
    private int size;


    /**
     * Implements list nodes for a singly-linked list.
     *
     * @param <Char> The data type for this list node.
     */
    private static class Node<Char> {
        /** The element stored in this Node. */
        private Char element;
        /** The reference to the next Node. */
        private Node<Char> next;

        private Node(Char element) {
            this(element,null);
        }

        /** Constructs an linkedList **/
        private Node(Char element, Node<Char> next) {
            this.element = element;
            this.next = next;
        }
    }
    /**
     * Constructs an empty linked list.
     */
    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }
    /**
     * Constructs a linked list with the given element.
     *
     * @param element The element to store in this list.
     */
    public MyLinkedList(Type element) {
        first = new Node<>(element);
        last = first;
        size = 1;
    }

    /**
     * Adds an element at the end of this list.
     *
     * @param element The element to add at the end of this list.
     */
    public void add(Type element) {
        if (size == 0) {
            first = new Node<>(element, first);
            last = first;
            size += 1;
        } else {

            last.next = new Node<>(element);
            last = last.next;
            size += 1;
        }
    }

    /**
     * Adds the given element at the given index in this list, shifting any element
     * at this index and any subsequent elements to the right.
     *
     * @param element The item to insert.
     * @param index The index to insert item at.
     * @throws IndexOutOfBoundsException if index < 0 or index > size.
     */

    public void add(int index, Type element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 0) {
            first = new Node<>(element, first);
            last = first;
            size += 1;
        } else if (index == 0) {
            Node<Type> temp = new Node<>(element, first);
            first = temp;
            size += 1;
        } else if (index == size) {
              add(element);
        } else {
            Node<Type> curr = first;
            for(int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            Node<Type> temp = new Node<Type>(element, curr.next);
            curr.next = temp;
            size += 1;
        }
    }
    /**
     * Removes and returns the element at the given index, shifting any subsequent
     * elements to the left.
     *
     * @param index The index of the element to remove.
     * @return The element removed at index.
     * @throws IndexOutOfBoundsException if index < 0 || index >= size.
     */
    public Type remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Type removed;
        if (index == 0) {
            removed = first.element;
            if (size == 0) {
                first = null;
                last = null;
            } else {
                first = first.next;
            }
        } else if (index == size - 1) {
            Node<Type> curr = first;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            removed = curr.next.element;
            last = curr;
        }
        else {
            Node<Type> curr = first;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            removed = curr.next.element;
            curr.next = curr.next.next;
        }
        size -= 1;
        return removed;
    }
    /**
     * Returns element in temp.
     *
     * @return The elements in temp node.
     */
    public Type get(int index) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<Type> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.element;
    }

    /**
     * sets a given value into a given index.
     */
    public void set(int index, Type value) {
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        Node<Type> curr = first;
        //Node<Char> setNode = new Node<>(value);
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.element = value;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return The number of elements in this list.
     */
    public int size() {
        return size;
    }

    /** we did this as a practice in Seminar. I used my solution I made in that class
     * Performs a sort on this list.
     */
    public void sort() {
        if (!isEmpty()) {
            for (int i = 0; i < size() -  1; i++) {
                Node<Type> curr = first;
                boolean swapped = false;
                for (int j = 0; j < size() - 1 - i; j++) {
                    int cmp = curr.element.compareTo(curr.next.element);
                    if (cmp > 0) {
                        Type temp = curr.element;
                        curr.element = curr.next.element;
                        curr.next.element = temp;
                        swapped = true;
                    }
                    curr = curr.next;
                }
                if (!swapped) {
                    break;
                }
            }
        }
    }

    /**
     * Checks if this list is empty.
     *
     * @return true if this list is empty and false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Displays MyLinkedList
     *
     * @return to MyLinkedList.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        Iterator<Type> it = new MyLinkedListIterator();
        result.append("[");
        if (it.hasNext()) {
            result.append(it.next());
        }
        while(it.hasNext()) {
            result.append(", " + it.next());
        }
        result.append("]");
        return result.toString();
    }


    public Iterator<Type> iterator() {
        return new MyLinkedListIterator();
    }

    public class MyLinkedListIterator implements Iterator<Type> {
        int currIndex = 0;

        @Override
        public boolean hasNext() {
            return currIndex < size;
        }

        @Override
        public Type next() {
            Node<Type> curr = first;
            for (int i = 0; i < currIndex; i++) {
                curr = curr.next;
            }
            currIndex++;
            return curr.element;
        }
    }

}
