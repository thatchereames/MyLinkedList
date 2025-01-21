import static org.junit.Assert.*;

import org.junit.*;

public class MyLinkedListCustomTester {

	private MyLinkedList<Integer> emptyIntList;
	private MyLinkedList<Integer> threeIntList;
	private MyLinkedList<String> threeStringList;

	private static final int[] INTEGER_ARRAY = {5, 4, 3, 2, 1};
	private static final String[] STRING_ARRAY = {"five", "four", "three", 	
		"two", "one"};
	//private static final boolean[] BOOLEAN_ARRAY = {true, false, true, false, 
	//	true};
	
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
		emptyIntList = new MyLinkedList<Integer>();
		threeIntList = new MyLinkedList<Integer>();
		threeStringList = new MyLinkedList<String>();
	}

	private void populateLinkedLists() {
        MyLinkedList<String>.Node stringNode0 =  
			this.threeStringList.new Node(this.STRING_ARRAY[0]);
        MyLinkedList<String>.Node stringNode1 =  
            this.threeStringList.new Node(this.STRING_ARRAY[1]);
        MyLinkedList<String>.Node stringNode2 =  
            this.threeStringList.new Node(this.STRING_ARRAY[2]);

        this.threeStringList.head.next = stringNode0;
        stringNode0.prev = this.threeStringList.head;
        stringNode0.next = stringNode1;
        stringNode1.prev = stringNode0;
        stringNode1.next = stringNode2;
        stringNode2.prev = stringNode1;
        stringNode2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = stringNode2;
        this.threeStringList.size = 3;

		MyLinkedList<Integer>.Node intNode0 =  
			this.threeIntList.new Node(this.INTEGER_ARRAY[0]);
        MyLinkedList<Integer>.Node intNode1 =  
            this.threeIntList.new Node(this.INTEGER_ARRAY[1]);
        MyLinkedList<Integer>.Node intNode2 =  
            this.threeIntList.new Node(this.INTEGER_ARRAY[2]);

        this.threeIntList.head.next = intNode0;
        intNode0.prev = this.threeIntList.head;
        intNode0.next = intNode1;
        intNode1.prev = intNode0;
        intNode1.next = intNode2;
        intNode2.prev = intNode1;
        intNode2.next = this.threeIntList.tail;
        this.threeIntList.tail.prev = intNode2;
        this.threeIntList.size = 3;
    }

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		populateLinkedLists();
		assertThrows("Should throw an IndexOutOfBoundsException"
			, IndexOutOfBoundsException.class, 
			() -> {emptyIntList.add(1,INTEGER_ARRAY[1]);});
		emptyIntList.add(0,INTEGER_ARRAY[0]);
		assertEquals("Added node should be accessible from head", 
			Integer.valueOf(INTEGER_ARRAY[0]), 
			this.emptyIntList.head.next.data);
		assertEquals("Added node should be accessible from tail", 
			Integer.valueOf(INTEGER_ARRAY[0]), 
			this.emptyIntList.tail.prev.data);
		assertEquals("Size of MyLinkedList should be updated", 1,
			this.emptyIntList.size);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		/*
		populateLinkedLists();
		threeIntList.add(0,INTEGER_ARRAY[3]);
		assertEquals("Head should point to item add at index 0", 
			Integer.valueOf(INTEGER_ARRAY[3]), threeIntList.head.next.data);
		assertEquals("The inserted object should point to the next Node", 
			Integer.valueOf(INTEGER_ARRAY[0]), 
			threeIntList.head.next.next.data);
		assertEquals("The object after the inserted should point back to it", 
			Integer.valueOf(INTEGER_ARRAY[3]), 
			threeIntList.head.next.next.prev.data);
		*/
		populateLinkedLists();
		threeIntList.add(INTEGER_ARRAY[3]);
		assertEquals(Integer.valueOf(INTEGER_ARRAY[3]) , 
			threeIntList.tail.prev.data);
		assertEquals(Integer.valueOf(INTEGER_ARRAY[3]) , 
			threeIntList.head.next.next.next.next.data);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		populateLinkedLists();
		threeStringList.add(1 , STRING_ARRAY[3]);
		assertEquals(STRING_ARRAY[3], threeStringList.head.next.next.data);
		assertEquals(STRING_ARRAY[1], threeStringList.head.next.next.next.data);
		assertSame(threeStringList.head.next.next , 
			threeStringList.tail.prev.prev.prev);
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		assertThrows(IndexOutOfBoundsException.class , 
			() -> {emptyIntList.remove(0);});
		assertThrows(IndexOutOfBoundsException.class, 
			() -> {emptyIntList.remove(3);});
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		populateLinkedLists();
		Integer threeIntListOne = threeIntList.remove(1);
		assertEquals(Integer.valueOf(INTEGER_ARRAY[1]) , threeIntListOne);
		assertEquals(Integer.valueOf(INTEGER_ARRAY[2]) , 
			threeIntList.head.next.next.data);
		assertEquals(Integer.valueOf(INTEGER_ARRAY[2]), 
			threeIntList.tail.prev.data);
		assertThrows(IndexOutOfBoundsException.class , 
			() -> {threeIntList.remove(4);});
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		populateLinkedLists();
		assertThrows(IndexOutOfBoundsException.class , 
			() -> {threeStringList.set(3,STRING_ARRAY[3]);});
		assertThrows(IndexOutOfBoundsException.class , 
			() -> {emptyIntList.set(0,INTEGER_ARRAY[5]);});
	}

	/**
	 * Aims to test the contains(E data, int start, int end) method.
	 * Data argument exists in the list but outside the given range. 
	 */
	@Test
	public void testCustomContainsExistsOutOfRange() {
		populateLinkedLists();
		assertEquals(false , 
			threeIntList.contains(Integer.valueOf(INTEGER_ARRAY[2]), 0, 1));
	}

	/**
	 * Aims to test the indexOfElement(E data) method.
	 * Data argument does not exist in the list.
	 */
	@Test
	public void testCustomIndexOfElementDoesNotExist() {
		populateLinkedLists();
		assertEquals(false , threeStringList.contains("one", 0 , 3));
	}
	//end testing

}

