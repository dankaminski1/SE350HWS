package hw2;

public class MyLinked {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }

    int N;
    Node first;
    
    public MyLinked () {
        first = null;
        N = 0;
        assert checkInvariants ();
    }


    private boolean checkInvariants() {
        assert((N != 0) || (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                return false;
            }
            x = x.next;
        }
        assert(x == null);
        return true;
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }


 // delete the kth element
    public void delete (int k) {
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        // TODO 1.3.20
        if (first == null) {
        	// empty
        	return;
        }
        
        	if (first.next  == null){
        		first = null;
        		N--;
        		return;
        		// only one item in list
        	}
        	// first object of long list
        	if (k == 0){
        		first = first.next;
        		N--;
        		return;
        	}
        	Node curr = first; // postiioning 
        	int count = k;
        	while (count -1 > 0){
        		count--;
        		curr = curr.next;
        	}
        	//last time
        	if (k == (N-1)){
        		curr.next = null;
        		N--;
        		return;
        	}
        	
        	curr.next =curr.next.next;
        	N--;
        assert checkInvariants ();
    }

    // reverse the list "in place"... without creating any new nodes
    public void reverse () {
    	if (first == null){
    		// empty
    		return;
    	}
 
    	//if only one item in the list
    	if (first.next == null){
    		 return;
    	}
    	//backwards
    	Node prev = null;
    	Node curr = first;
    	Node next = null;
    	while (curr!=null){
    		next = curr.next;
    		curr.next = prev;
    		prev = curr;
    		curr = next;
    	}
    	first = prev;
        assert checkInvariants ();
    }

    // remove 
    public void remove (double item) {
    	Node temp = first;
    	Node prev = null;
    	while (temp != null)
    	{
    		if(temp.item ==item){
    			if (prev ==null){
    				first = temp.next;
    				temp = temp.next;
    				N--;
    			}
    			else
    			{
    				prev.next = temp.next;
    				temp = temp.next; 	
    				N--;
    			}
    		}
    		else
    		{
    			prev = temp;
    			temp = temp.next;
    		}
    	}    	
        assert checkInvariants ();
    }

// TODO: CONVERT THE FOLLOWING TO JUNIT TESTS
/**\
   
    private static void testDelete () {
        MyLinked b = new MyLinked ();
        b.add (1);
        print ("singleton", b);
        b.delete (0);
        print ("deleted", b);
        for (double i = 1; i < 13; i++) {
            b.add (i);
        }
        print ("bigger list", b);
        b.delete (0);
        print ("deleted at beginning", b);
        b.delete (10);
        print ("deleted at end", b);
        b.delete (4);
        print ("deleted in middle", b);
    }
    private static void testReverse () {
        MyLinked b = new MyLinked ();
        b.reverse ();
        print ("reverse empty", b);
        b.add (1);
        print ("singleton", b);
        b.reverse ();
        print ("reverse singleton", b);
        b.add (2);
        print ("two", b);
        b.reverse ();
        print ("reverse two", b);
        b.reverse ();
        print ("reverse again", b);
        for (double i = 3; i < 7; i++) {
            b.add (i);
            b.add (i);
        }
        print ("bigger list", b);
        b.reverse ();
        print ("reversed", b);
    }
    private static void testRemove () {
        MyLinked b = new MyLinked ();
        b.remove (4);
        print ("removed 4 from empty", b);
        b.add (1);
        b.remove (4);
        print ("removed 4 from singelton", b);
        b.remove (1);
        print ("removed 1 from singelton", b);
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
        }
        for (double i = 1; i < 5; i++) {
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
            b.add (i);
        }
        print ("longer list", b);
        b.remove (9);
        print ("removed all 9s", b); // does nothing
        b.remove (3);
        print ("removed all 3s", b);
        b.remove (1);
        print ("removed all 1s", b);
        b.remove (4);
        print ("removed all 4s", b);
        b.remove (2);
        print ("removed all 2s", b); // should be empty
    }*/

}