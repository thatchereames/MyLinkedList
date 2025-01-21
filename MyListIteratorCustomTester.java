import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;

public class MyListIteratorCustomTester {

    private MyLinkedList<String> listLen1, listLen2;
    private MyLinkedList<String>.MyListIterator listLen1Iter, listLen2Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList<String>();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList<String>();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        listLen1Iter.next();
        assertThrows(NoSuchElementException.class, 
            () -> {listLen1Iter.next();});
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class , 
            () -> {listLen1Iter.previous();});
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        assertThrows(NullPointerException.class , 
            () -> {listLen1Iter.add(null);});
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        assertThrows(IllegalStateException.class, 
            () -> {listLen2Iter.set("New String");});
        listLen2Iter.next();
        listLen2Iter.add("New");
        assertThrows(IllegalStateException.class, 
            () -> {listLen2Iter.set("New New");});
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        assertThrows(IllegalStateException.class, 
            () -> {listLen2Iter.remove();});
        listLen2Iter.next();
        listLen2Iter.remove();
        assertThrows(IllegalStateException.class, 
            () -> {listLen2Iter.remove();});
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.next();
        assertEquals(false, listLen1Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals(false, listLen1Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals(-1, listLen2Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen1Iter.next();
        assertEquals(1, listLen1Iter.nextIndex());
    }
}
