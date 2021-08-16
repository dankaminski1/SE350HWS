package hw1;

//import junit.framework.Assert;
import junit.framework.TestCase;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Collection;


// TODO: complete the tests
public class InventoryTEST extends TestCase {
  public InventoryTEST(String name) {
    super(name);
  }

  InventorySet s = new InventorySet();
  final VideoObj v1 = new VideoObj( "A", 2000, "B" );
  final VideoObj v2 = new VideoObj( "B", 2000, "B" );

  public void testSize() {
		s.clear(); //Reset set
		assertEquals(0, s.size()); //Nothing added, should be 0
		
		s.addNumOwned(v1, 1);
		assertEquals(1, s.size()); //Only 1 record
		
		s.addNumOwned(v1, 15);
		assertEquals(1, s.size()); //Still 1 record
		
		s.addNumOwned(v2, 300);
		assertEquals(2, s.size()); //2 records exist
		
		s.addNumOwned(v2, -301);
		assertEquals(1, s.size()); //1 record exists because vid2 should be removed
		
		s.addNumOwned(v1, -300);
		assertEquals(0, s.size()); //0 records exist because vid 1 should be removed
		
		try { //Bad params
			s.addNumOwned(null, -1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		try {//No video but bad change i.e. no record is created
			s.addNumOwned(v1, 0);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
	}
  public void testAddNumOwned() {
	  s.clear(); //Reset set
		assertEquals(0, s.size()); //Empty hashmap
		
		s.addNumOwned(v1, 1);
		assertEquals(1, s.get(v1).numOwned); //Only 1 video added to vid1
		
		s.addNumOwned(v1, 500); //Adds 500
		assertEquals(501, s.get(v1).numOwned);
		
		s.addNumOwned(v1, -300); //Remove 300
		assertEquals(201, s.get(v1).numOwned);
		
		s.addNumOwned(v2, 5);
		assertEquals(5, s.get(v2).numOwned); //Add 5 to vid 2
		
		assertEquals(2, s.size()); //Should only be 2 videos in Inventory
		
		try { //Bad params
			s.addNumOwned(null, -1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		try {//No video but bad change i.e. no record is created
			s.addNumOwned(v1, 0);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		
	}

  public void testCheckOutCheckIn() {
    // TODO
	s.clear();
		
		try { //No videos in set
			s.checkIn(null);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		try { //No videos in set
			s.checkOut(null);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		s.addNumOwned(v1, 1);
		assertTrue(s.get(v1).numOut == 0 && s.get(v1).numRentals == 0); //New record therefore none out nor rental
		
		try { //Can't check in a video that's not out
			s.checkIn(v1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
		s.checkOut(v1);
		assertTrue(s.get(v1).numOut == 1 && s.get(v1).numRentals == 1); //1 video checked out therefore all should increment
		
		s.checkIn(v1);
		assertTrue(s.get(v1).numOut == 0 && s.get(v1).numRentals == 1); //Checked in therefore 0 are out and rentals EVER is still 1
		
		s.checkOut(v1); //Should now contain no available videos in inventory
		try { //Can't checkout a video when you don't have enough...or any!
			s.checkOut(v1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
	}


  public void testClear() {
    // TODO
		s.clear();
		assertEquals(0, s.size()); //Reset set
		
		//Add videos
		s.addNumOwned(v1, 2);
		s.addNumOwned(v2, 30);
		
		assertEquals(2, s.size()); //Should be 2 videos
		
		s.clear();
		assertEquals(0, s.size()); //Cleared therefore 0 videos
		
		try { //No videos should exist
			s.checkOut(v1);
			fail("Exception is thrown");
		} catch(IllegalArgumentException e) {}
		
	}

  public void testGet() {
    // TODO
	//Because a copy is supposedly returned then a new record is created each time
			//Therefore addresses should be different.
			//Since a .equals() was never created for the Record class, the default .equals() would just do a ==
			s.clear(); //No keys/values in set
			assertNull(s.get(v1));
			
			s.addNumOwned(v1, 1);
			assertNotNull(s.get(v1));
			
			Record record1 = s.get(v1);
			Record record2 = s.get(v1);
			assertFalse(record1.equals(record2));
			assertTrue(record1 != record2);
		}

  public void testToCollection() {
    // Be sure to test that changing records in the returned
    // collection does not change the original records in the
    // inventory.  ToCollection should return a COPY of the records,
    // not the records themselves.
    // TODO
		s.clear();
		
		assertTrue(s.toCollection() != s.toCollection()); //Addresses should be different if they were copies
		
		s.addNumOwned(v1, 1);
		Collection<Record> recordAL = s.toCollection(); //Both should contain 1 of vid1
		
		s.addNumOwned(v1, 15); //Inventory contains 16 of v 1, but the copy should still contain 1
		assertTrue(s.get(v1).numOwned != recordAL.iterator().next().numOwned); //Verify if above is true
		
		s.addNumOwned(v2, 2); //Adding another record to the inventory. The copy should still be just 1 record
		assertEquals(2, s.size());
		assertEquals(1, recordAL.size());
		
		s.clear(); //Nothing should be in inventory
		assertEquals(0, s.size());
		assertEquals(1, recordAL.size()); //But the copy should still contain 1 record
	}

}
