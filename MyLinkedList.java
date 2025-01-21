/*
 * Name: Thatcher Eames
 * Email: theames@ucsd.edu
 * PID: A17284279
 * Used: 
 * 
 * This file creates a doubley linked list data structure. 
 */

import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    /**
     * A no-arg constructor that creates a MyLinkedList with no elements in it.
     */
    public MyLinkedList() {
        size = 0;
        head = new Node(null);
        tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    /**
     * Returns the number of elements in the MyLinkedList array.
     * @return An int representing the number of elements.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * This function returns the element (of type E) stored in MyListArray at 
     * the given index.
     * @param index An int representing the desired index
     * @return The element of type E at the index 
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = getNth(index);
        E data = currNode.getElement();
        return data;
    }

    /**
     * Adds a new element to the MyLinkedList at the specified index.
     * @param index Index of the MyLinkedList where the element will be added.
     * @param data The new element that will be added to MyLinkedList
     */
    @Override
    public void add(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        } else if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(data);
        if (size == 0) {
            head.setNext(newNode);
            tail.setPrev(newNode);
            newNode.setPrev(head);
            newNode.setNext(tail);
        } else if (index == size){
            tail.prev.setNext(newNode);
            newNode.setPrev(tail.prev);
            newNode.setNext(tail);
            tail.setPrev(newNode);
        } else {
            Node currNode = getNth(index);
            Node prevNode = currNode.getPrev();
            newNode.setPrev(prevNode);
            currNode.setPrev(newNode);
            newNode.setNext(currNode);
            prevNode.setNext(newNode);
        }
        size++;
    }

    /**
     * Adds a new element to the end of the MyLinkedList. 
     * @param data The object of any type that is added to the end of the
     * MyLinkedList
     * @return A boolean representing if the add was successful
     */
    @Override
    public boolean add(E data) {
        if(data == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(data);
        //Node currNode = new Node(null);
        if (size == 0) {
            head.setNext(newNode);
            tail.setPrev(newNode);
            newNode.setPrev(head);
            newNode.setNext(tail);
        } else {
            Node currNode = getNth(size - 1);
            currNode.setNext(newNode);
            newNode.setPrev(currNode);
            newNode.setNext(tail);
            tail.setPrev(newNode);
        }
        size++;
        return true; 
    }

    /**
     * Sets the element at a given index in MyLinkedList to a new data value and 
     * returns the element that was replaced
     * @param index An int representing the index of the element to be replaced.
     * @param data The data that the element will be set to.
     * @return The data in the MyLinkedList that was written over.
     */
    @Override
    public E set(int index, E data) {
        if (data == null) {
            throw new NullPointerException();
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        //Creates a new node with a pointer to the node in MyLinkedList @ index
        Node currNode = getNth(index);
        //Creates a new element E that has the same value as the Node @ index
        E currData = currNode.getElement();
        currNode.setElement(data);
        return currData;
    }

    /**
     * Removes the element in MyLinkedList at the specified index
     * @param index An int that represents the index of the element
     * @return The value of type E of the element that was removed
     */
    @Override
    public E remove(int index) {
        //Throws the exception if the index is out of bounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = getNth(index);
        E currData = currNode.getElement();
        currNode.getNext().setPrev(currNode.getPrev());
        currNode.getPrev().setNext(currNode.getNext());
        size--;
        return currData;
    }

    /**
     * Removes all the elements from the MyLinkedList
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    /**
     * Checks if the MyLinkedList is empty
     * @return Boolean representing if the list is empty
     */
    @Override
    public boolean isEmpty() {
        if (size != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * A helper method that returns a pointer to the Node at the provided index.
     * @param index An int that is the index of the returned item
     * @return The node at the given index
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode = head;
        for (int i = 0 ; i <= index ; i++) {
            currNode = currNode.getNext();
        }
        return currNode;  
    }
    
    /**
     * Determines if the MyLinkedList contains the given element between the 
     * specified indicies: start (inclusive) and end (exclusive).
     * @param data The element that is checked for inclusion in the MyLinkedList
     * @param start The int representing the index where the search starts 
     *              (inclusive)
     * @param end The int representing the index where the search ends 
     *            (exclusive)
     * @return Boolean representing if the element is within the bounds in the 
     *         MyLinkedList
     */
    public boolean contains(E data, int start, int end) {
        if (start < 0 || end > size ) {
            throw new IndexOutOfBoundsException();
        }
        Node currNode;
        if (start > end) {
            for (int i = start ; i < size ; i++) {
                currNode = getNth(i);
                if (data.equals(currNode.getElement())) {
                    return true;
                }
            }
            for (int i = 0 ; i < end ; i++) {
                currNode = getNth(i);
                if (data.equals(currNode.getElement())) {
                    return true;
                }
            }
            return false;
        } else {
            for (int i = start ; i < end ; i++) {
                currNode = getNth(i);
                if (data.equals(currNode.getElement())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    /**
     * Returns the index at which the specified element is stored within the 
     * MyLinkedList. Returns a -1 if the item is not within the list.
     * @param data The element that is searched for in the MyLinkedList
     * @return The index at which the element is stored
     */
    public int indexOfElement(E data) {
        Node currNode;
        for (int i = 0 ; i < size ; i++) {
            currNode = getNth(i);
            if (data.equals(currNode.getElement())) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 
     */
    protected class MyListIterator implements ListIterator<E> {
        
        Node left;
        Node right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;
        /**
         * No arg constructor with 
         */
        public MyListIterator() {
            left = head;
            right = head.getNext();
            idx = 0;
            forward = true;
            canRemoveOrSet = false; 
        }

        public boolean hasNext() {
            if(right.getElement() == null) {
                return false;
            } else {
                return true;
            }
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node nextNode = right;
            right = right.getNext();
            left = left.getNext();
            forward = true;
            idx++;
            canRemoveOrSet = true;
            return nextNode.getElement();
        }

        public boolean hasPrevious() {
            if (left.getElement() == null) {
                return false;
            } else {
                return true;
            }
        }

        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            Node prevNode = left;
            right = right.getPrev();
            left = left.getPrev();
            forward = false;
            idx--;
            canRemoveOrSet = true;
            return prevNode.getElement();
        }

        public int nextIndex() {
            return idx;
        }

        public int previousIndex() {
            return idx - 1;
        }

        public void add(E element) {
            if(element == null) {
                throw new NullPointerException();
            }
            Node newNode = new Node(element);
            left.setNext(newNode);
            right.setPrev(newNode);
            newNode.setPrev(left);
            newNode.setNext(right);
            left = newNode;
            idx++;
            canRemoveOrSet = false;
        }

        public void set(E element) {
            if(!canRemoveOrSet) {
                throw new IllegalStateException();
            } else if (element == null) {
                throw new NullPointerException();
            }
            if(forward) {
                left.setElement(element);
            } else {
                right.setElement(element);
            }
        }

        public void remove() {
            if(!canRemoveOrSet) {
                throw new IllegalStateException();
            }
            if(forward) {
                left.getPrev().setNext(right);
                right.setPrev(left.getPrev());
                left = left.getPrev();
                idx--;
            } else {
                right.getNext().setPrev(left);
                left.setNext(right.getNext());
                right = right.getNext();
            }
            canRemoveOrSet = false;
        }
    }

}

