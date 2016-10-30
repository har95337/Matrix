//-----------------------------------------------------------------------------
//  MatrixTest.java
//  A test client for the Matrix ADT
//  Harpreet Singh
//  hsingh18@ucsc.edu
//-----------------------------------------------------------------------------

public class MatrixTest{
   public static void main(String[] args){
      int i, j, n=100000;
      Matrix A = new Matrix(n);
      Matrix B = new Matrix(n);

      A.changeEntry(1,1,1); B.changeEntry(1,1,1);
      A.changeEntry(1,2,2); B.changeEntry(1,2,0);
      A.changeEntry(1,3,3); B.changeEntry(1,3,1);
      A.changeEntry(2,1,4); B.changeEntry(2,1,0);
      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
      A.changeEntry(2,3,6); B.changeEntry(2,3,0);
      A.changeEntry(3,1,7); B.changeEntry(3,1,1);
      A.changeEntry(3,2,8); B.changeEntry(3,2,1);
      A.changeEntry(3,3,9); B.changeEntry(3,3,1);

      System.out.println(A.getNNZ());
      System.out.println(A);

      System.out.println(B.getNNZ());
      System.out.println(B);

      A.changeEntry(1,1,2); B.changeEntry(1,1,2);
      A.changeEntry(1,2,3); B.changeEntry(1,2,0);
      A.changeEntry(1,3,4); B.changeEntry(1,3,2);
      A.changeEntry(2,1,6); B.changeEntry(2,1,0);
      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
      A.changeEntry(2,3,2); B.changeEntry(2,3,0);
      A.changeEntry(3,1,1); B.changeEntry(3,1,1);
      A.changeEntry(3,2,3); B.changeEntry(3,2,1);
      A.changeEntry(3,3,7); B.changeEntry(3,3,1);
      System.out.println("Checking Replacement!");
      System.out.println(A.getNNZ());
      System.out.println(A);

      System.out.println(B.getNNZ());
      System.out.println(B);
      System.out.println("Checking ScalarMult");
      Matrix C = A.scalarMult(1.5);
      System.out.println(C.getNNZ());
      System.out.println(C);

      System.out.println("Checking Add on self!");
      Matrix D = A.add(A);
      System.out.println(D.getNNZ());
      System.out.println(D);

      System.out.println("Checking Add on not self!");
      D = A.add(B);
      System.out.println(D.getNNZ());
      System.out.println(D);

      System.out.println("Checking Sub on self!");
      Matrix E = A.sub(A);
      System.out.println(E.getNNZ());
      System.out.println(E);

      System.out.println("Checking Sub on not self!");
      E = A.sub(B);
      System.out.println(E.getNNZ());
      System.out.println(E);

      System.out.println("Checking Transpose!");
      Matrix F = B.transpose();
      System.out.println(F.getNNZ());
      System.out.println(F);

      System.out.println("Checking Mult!");
      Matrix G = B.mult(B);
      System.out.println(G.getNNZ());
      System.out.println(G);

      System.out.println("Checking Copy and Equals");
      Matrix H = A.copy();
      System.out.println(H.getNNZ());
      System.out.println(H);
      System.out.println(A.equals(H));
      System.out.println(A.equals(B));
      System.out.println(A.equals(A));

      System.out.println("Checking makeZero!");
      A.makeZero();
      System.out.println(A.getNNZ());
      System.out.println(A);
   }
}
