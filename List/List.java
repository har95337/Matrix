//-----------------------------------------------------------------------------
// Harpreet Singh
// 1423136
// hsingh18@ucsc.edu
// List ADT in Java
//-----------------------------------------------------------------------------

class List{

   private class Node{
      // Fields
      Object data;
      Node next;
      Node prev;
      // Constructor
      Node(Object data) {
          this.data = data; 
          next = null; 
          prev = null;
      }

      // toString():  overrides Object's toString() method
      public String toString() { 
         return String.valueOf(data); 
      }

      // equals(): overrides Object's equals() method
      public boolean equals(Object x){
         boolean eq = false;
         Node N;
         if(x instanceof Node){
            N = (Node) x;
            eq = (this.data.equals(N.data));
         }
         return eq;
      }
   }

   // Fields
   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int index;
   // Constructor
   List() { 
      front = back = cursor = null; 
      length = 0; 
      index = -1;
   }


   // Access Functions --------------------------------------------------------
   // isEmpty()
   // Returns true if this Queue is empty, false otherwise.
   
   boolean isEmpty() { 
      return length == 0; 
   }

   // getLength()
   // Returns length of this Queue.
   int length() { 
      return length; 
   }

   // getFront() 
   // Returns front element.
   // Pre: !this.isEmpty()
   Object front(){
      if( this.isEmpty() ){
         throw new RuntimeException(
            "List Error: front() called on empty List");
      }
      return front.data;
   }

   Object back(){
      if( this.isEmpty() ){
         throw new RuntimeException(
            "List Error: back() called on empty List");
      }
      return back.data;
   }

   int index(){
       if(cursor != null){
           //System.out.println(cursor.data);
           return index;
       }
       return -1;
   }


   Object get(){
     if( this.isEmpty() ){
         throw new RuntimeException(
            "List Error: get() called on empty List");
      }
      if(index < 0){
          throw new RuntimeException(
              "List Error: get() called on undefined index in List");
      }
      return cursor.data;
   }

    void clear(){
        front = back = cursor = null;
        length = 0;
        index = -1;
    }

    void moveFront(){
        cursor = front;
        index = 0;
    }

    void moveBack(){
        cursor = back;
        index = length - 1;
    }

    void movePrev(){
      if((cursor != null) && (index >= 0)){
            cursor = cursor.prev;
            //System.out.println(index);
            index--;
        }
  }

   void moveNext(){
        if((cursor != null) && (index >= 0)){
            cursor = cursor.next;
            index++;
        }
    }

    void prepend(Object data){
        Node N = new Node(data);
        N.prev = null;
        N.next = front;
        if(this.isEmpty()){
            back = front = N;
        }else{
            front.prev = N;
        }
        front = N;
        length++;
    }

    void append(Object data){
        Node N = new Node(data);
        N.prev = back;
        N.next = null;
        if(this.isEmpty()){
           back = front = N;
        }else{
            back.next = N;
        }
        back = N;
        length++;
    }

    void insertBefore(Object data){
        Node N = new Node(data);
        if(length <= 0 || index == -1){
            throw new RuntimeException(
                "Cannot insert before cursor on empty list");
        }
        if(cursor == front){
            prepend(data);
			length--;
        }else if(index > 0){
            N.prev = cursor.prev;
            N.next = cursor;
            cursor.prev.next = N;
            cursor.prev = N;
        }
        length++;
    }

     void insertAfter(Object data){
        Node N = new Node(data);
        N.prev = cursor;
        N.next = cursor.next;
        if(length <= 0 || index < 0){
            throw new RuntimeException(
                "Cannot insert before cursor on empty list");
        }
        if(cursor == back){
            append(data);
			length--;
        }else if(cursor != back){
            N.next = cursor.next;
            N.prev = cursor;
            cursor.next.prev = N;
            cursor.next = N;
        }
        length++;
    }

    void deleteFront(){
      if( isEmpty()){
        throw new RuntimeException(
          "Cannot deleteFront from empty list");
      }
      front = front.next;
      front.prev = null;
      length--;
    }
     void deleteBack(){
      if( isEmpty() ){
        throw new RuntimeException(
          "Cannot deleteBack from empty list");
      }
      back = back.prev;
      back.next = null;
      length--;
    }
    void delete(){
      if(index <= 0 || isEmpty()){
        throw new RuntimeException(
          "Cannot delete from an invalid list!");
      }
      if(cursor == front){
        deleteFront();
		return;
      }
      if(cursor == back){
        deleteBack();
		return;
      }
      cursor.prev.next = cursor.next;
      cursor.next.prev = cursor.prev;
      cursor = null;
	  index = -1;
      length--;
    }
   // Other Functions ---------------------------------------------------------

   // toString()
   // Overides Object's toString() method.
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = front;
      while(N!=null){
         sb.append(N.toString());
         sb.append(" ");
         N = N.next;
      }
      return new String(sb);
   }

   // equals()
   // Overrides Object's equals() method.  Returns true if x is a List storing
   // the same integer sequence as this Queue, false otherwise.
   public boolean equals(Object x){
      boolean eq  = false;
      List Q;
      Node N, M;
      // System.out.println("In Equals in List.");
      if(x instanceof List){
         Q = (List)x;
         N = this.front;
         M = Q.front;
         // System.out.println(this.length);
         // System.out.println(Q.length);
         eq = (this.length==Q.length);
         while( eq && N!=null ){
            eq = N.equals(M);
            // System.out.println(N);
            // System.out.println(M);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }


   // copy()
   // Returns a new Queue identical to this Queue.
   List copy(){
      List L = new List();
      Node N = this.front;
      while( N != null ){
         L.append(N.data);
         N = N.next;
      }
      return L;
   }

}