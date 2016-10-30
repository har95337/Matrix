//-----------------------------------------------------------------------------
// Harpreet Singh
// 1423136
// hsingh18@ucsc.edu
// Matrix ADT in Java
//-----------------------------------------------------------------------------

class Matrix{
	
    private static class Entry{
      // Fields
    int column;
    double value;
      // Constructor
    Entry(int column, double value) {
          this.value = value; 
          this.column = column; 
      }

      // toString():  overrides Object's toString() method
    public String toString() { 
         return  ("(" + column + ", " + value + ")"); 
      }

      // equals(): overrides Object's equals() method
    public boolean equals(Object x){
         boolean eq = false;
         Entry E;
         if(x instanceof Entry){
            E = (Entry) x;
            eq = (this.value==E.value && this.column == E.column);
         }
         return eq;
      }
   }
   
    List [] row;
    int size;
    Matrix(int n){
	   if(n < 1){
		   throw new RuntimeException(
		   "Matrix called with 0 or negative nxn count");
	   }
	   row = new List[n + 1];
	   for(int i = 1; i <= n; i++){
		   row[i] = new List();
	   }
	   size = n;
   }
   // Acess functions
    int getSize(){
	   return size;
    }
   
    int getNNZ(){
	   int NNZ = 0;
	   List A = new List();
	   for(int i = 1; i <= getSize(); i++){
		    A = row[i];
		    NNZ = NNZ + A.length();
		}
		return NNZ;
	}
	
    public boolean equals(Object x){
         Matrix M;
         if(x instanceof Matrix){
            M = (Matrix) x;
			if(this.getSize() != M.getSize()){
				return false;
			}
			for(int i = 1; i <= getSize(); i++){
				 if(!(this.row[i].equals(M.row[i]))){
					return false;
				  }
				}
			}
		return true;
	}
    void makeZero(){
		for(int i = 1; i <= getSize(); i++){
		 row[i] = new List();
		} 
    }
    
	//DONE
    Matrix copy(){
		Matrix newMatrix = new Matrix(getSize());
		for(int i = 1; i <= getSize(); i++){
			this.row[i].moveFront();
			while(this.row[i].index() > -1){
				Entry E = (Entry)this.row[i].get();
				newMatrix.changeEntry(i,E.column,E.value);
				this.row[i].moveNext();
			}
		}
		return newMatrix;
	}
	
	
    void changeEntry(int i, int j, double x){
		if(i < 1 || i > getSize()){
			throw new RuntimeException("Incorrect argument in function change entry: i");
		}
		if(j < 1 || j > getSize()){
			throw new RuntimeException("Incorrect argument in function change entry: j");
		}
		
		List A = new List();
		A = row[i];
		A.moveFront();
		
		if(x == 0.0){
		    while(A.index() > -1 && ((Entry)A.get()).column < j){
					A.moveNext();
			}
			if(A.index() > -1){
				return;
			}
			if(A.index() == -1){
				return;
			}
			Entry E = (Entry)A.get();
			if(E.column == j){
				if(E.column == 0){
					A.deleteFront();
					return;
				}else if(E.column == getSize() - 1){
					A.deleteBack();
					return;
				}else{
			    A.delete();
				return;
				}
			}
		}
		A.moveFront();
		if(x != 0.0){
		    while(A.index() > -1 && ((Entry)A.get()).column < j){
					A.moveNext();
			}
			if(A.index() == -1){
				Entry T = new Entry(j,x);
				A.append(T); 
				return;
			}
			Entry E = (Entry)A.get();
			if(E.column == j){
			    E.value = x;
				return;
			} else{
				Entry Z = new Entry(j,x);
				A.insertBefore(Z);
				return;
			}
		}
	}
	
 	Matrix scalarMult(double x){
		Matrix scalarMatrix = new Matrix(this.getSize());
		for(int i = 1; i <= getSize(); i++){
			this.row[i].moveFront();
			while(this.row[i].index() > -1){
				Entry E = (Entry)this.row[i].get();
				scalarMatrix.changeEntry(i,E.column,(x * E.value));
				row[i].moveNext();
			}
		}
		return scalarMatrix;
	} 
	
	Matrix add(Matrix M){
		if(M.getSize() != this.getSize()){
			throw new RuntimeException(
			"Incorrect sizes for add function in Matrix!");
		}
		Matrix temp = new Matrix(getSize());
		if(this.equals(M)){
			temp = this.copy();
			return temp.scalarMult(2);
		}else{
			for(int i = 1; i <= getSize(); i++){
			   temp.row[i] = addHelper(row[i],M.row[i]);
			}
		}
		return temp;
	}
	
	Matrix sub(Matrix M){
		if(M.getSize() != this.getSize()){
			throw new RuntimeException(
			"Incorrect sizes for subtract function in Matrix!");
		}
		Matrix temp = new Matrix(getSize());
		if(this.equals(M)){
			return temp;
		}else{
			Matrix negativeM = M.scalarMult(-1);
			for(int i = 1; i <= getSize(); i++){
			   temp.row[i] = addHelper(row[i],negativeM.row[i]);
			}
		}
		return temp;
	}
	
	Matrix mult(Matrix M){
		if(M.getSize() != this.getSize()){
			throw new RuntimeException(
			"Incorrect sizes for mult function in Matrix!");
		}
		Matrix A = new Matrix(getSize());
		Matrix B = M.transpose();
		for(int i = 1; i <= getSize(); i++){
			if(this.row[i].length() == 0)continue;
			  for(int j = 1; j <= getSize(); j++){
			  	  if(B.row[j].length() == 0) continue;
				  A.changeEntry(i,j,dot(row[i], B.row[j]));
			  }
		}
		return A;
	}
	
	Matrix transpose(){
		Matrix transMatrix = new Matrix(getSize());
		List A = new List();
		for(int i = 1; i <= getSize(); i++){
			A = row[i];
			A.moveFront();
			while(A.index() != -1){
				transMatrix.changeEntry(((Entry)row[i].get()).column, i, ((Entry)row[i].get()).value);
				row[i].moveNext();
			}
		}
		return transMatrix;
	}
	public String toString(){
		List A = new List();
		String output = "";
		for(int i = 1; i <= getSize() ; i++){
			A = row[i];
			if(A.length() > 0){
				output +=  (i + ": " + row[i]+ " " + "\n");
			}
		}
		return output;
	}
		
	private static double dot(List P, List Q){
		double mult = 0.0;
		P.moveFront();
		Q.moveFront();
		while(P.index() != -1 && Q.index() != -1){
			Entry x = (Entry)P.get();
			Entry y = (Entry)Q.get();
			if(x.column > y.column){
				Q.moveNext();
			}else if(x.column < y.column){
				P.moveNext();
			}else if(x.column == y.column){
				mult = mult + (x.value * y.value);
				P.moveNext();
				Q.moveNext();
			}
		}
		return mult;
	}
	private static List addHelper(List P, List Q){
		List A = new List();
		if(P.isEmpty() && !Q.isEmpty()){
			return Q;
		}else if(!P.isEmpty() && Q.isEmpty()){
			return P;
		}else{
	    P.moveFront();
		Q.moveFront();
		while(P.index() != -1 && Q.index() != -1){
			Entry x = (Entry)P.get();
			Entry y = (Entry)Q.get();
			if(x.column > y.column){
				A.append(new Entry(y.column, y.value));
				Q.moveNext();
			}else if(x.column < y.column){
				A.append(new Entry(x.column,x.value));
				P.moveNext();
			}else if(x.column == y.column){
				if(x.value + y.value != 0){
					A.append(new Entry(x.column, x.value + y.value));
				}
				P.moveNext();
				Q.moveNext();
			}
		 }
		 if(P.index() == -1 && Q.index() != -1){
			 while(Q.index() != -1){
				 Entry y = (Entry)Q.get();
				 A.append(new Entry(y.column, y.value));
				 Q.moveNext();
			 }
		 }
		 if(P.index() != -1 && Q.index() == -1){
			 while(P.index() != -1){
				 Entry x = (Entry)P.get();
			     A.append(new Entry(x.column, x.value));
				 P.moveNext();
			 }
		 }
	  }
	  return A;
  }
}