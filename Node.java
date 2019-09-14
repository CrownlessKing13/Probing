public class Node {
		// reference to the next node in the chain, or null if there isn't one.
		Node next;
      
		// data carried by this node. could be of any type you need.
		String data;
 
		// Node constructor
                
		public Node(String dataValue) {
			next = null;
			data = dataValue;
         
		}
 
		
		
		// these methods should be self-explanatory
		public String getData() {
			return data;
		}
 
		
		public void setData(String dataValue) {
			data = dataValue;
		}
 
		public Node getNext() {
			return next;
		}
 
		public void setNext(Node nextValue) {
			next = nextValue;
		}
           
      public void printList() 
    { 
        Node n = next;
        System.out.println(this.data); 
        while (n != null) 
        { 
            System.out.println(n.data+" "); 
            n = n.next; 
        } 
    }     
 
	}
