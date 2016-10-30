//-----------------------------------------------------------------------------
//  ListTest.java
//  A test client for the List ADT. Use this to test your list module. The
//  correct output is given below.
//  Harpreet Singh
//  hsingh18@ucsc.edu
//-----------------------------------------------------------------------------

public class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();
      List D = new List();
      for(int i=1; i<=20; i++){
         A.append(i);
         B.prepend(i);
      }
      for(double j = 0.0; j <= 20.0; j += 4.0){
         D.append(j);
      }
      System.out.println(A);
      System.out.println(B);
      System.out.println(D);
      for(A.moveFront(); A.index()>=0; A.moveNext()){
         System.out.print(A.get()+" ");
      }
      System.out.println();
      for(B.moveBack(); B.index()>=0; B.movePrev()){
         System.out.print(B.get()+" ");
      }
      System.out.println();
      
      List C = A.copy();
      List E = D.copy();
      System.out.println(A.equals(B));
      System.out.println(B.equals(C));
      System.out.println(C.equals(A));
      System.out.println(D.equals(A));
      System.out.println(D.equals(E));
      System.out.println(E.equals(D));
      A.moveFront();
      for(int i=0; i<5; i++) A.moveNext(); // at index 5
      A.insertBefore(-1);                  // at index 6
      for(int i=0; i<9; i++) A.moveNext(); // at index 15
      A.insertAfter(-2);
      for(int i=0; i<5; i++) A.movePrev(); // at index 10
      A.delete();
      A.deleteFront();
      A.deleteBack();
      D.deleteFront();
      D.deleteBack();
      System.out.println(A.index());
      System.out.println(A);
      System.out.println(A.length());
      A.clear();
      System.out.println(A.length());
      System.out.println(D.index());
      System.out.println(D);
      System.out.println(D.length());
      D.clear();
      System.out.println(D.length());
   }
}

// Output of this program:
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
// false
// false
// true
// 1 2 3 4 5 -1 6 7 8 9 11 12 13 14 15 -2 16 17 18 19 20
// 21
// 0

