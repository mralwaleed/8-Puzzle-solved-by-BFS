import java.util.*; 
public class Node {
	int [] puzzle = new int[9];
	Node pirent ;
	List<Node> chlidern = new ArrayList<Node>();
	int x=0 ;
	int col=3;
	
	public Node(int[] p){
		
		for(int i=0;i<p.length;i++) {
			puzzle[i]=p[i];
			
		}
	}
	 
	
	boolean isGaol(int[] gol) {
		boolean isgol=true ;
		int[] theGol ;
		theGol =gol;
		for(int i= 0;i<puzzle.length;i++) {
			if(theGol[i] != puzzle[i]) {
				isgol=false ;}
		}
		
		return isgol;
	}
	void copyarr(int[] a,int[] b) {
		for(int i= 0;i<b.length;i++) {
			b[i]=a[i];
		}
	}
	void MoveToRight(int[] p ,int i) {
		if(i%col <col-1) {
			int[] pa = new int[9]; 
			copyarr(p , pa);
			int temp = pa[i+1];
			pa[i+1]=pa[i];
			pa[i] = temp;
			Node child = new Node(pa);
			chlidern.add(child);
		     child.pirent=this;
		}
		
	}
    void MoveToLift(int[] p ,int i) {
    	if(i%col>0) {
    		int[] pa = new int[9]; 
			copyarr(p , pa);
			int temp = pa[i-1];
			pa[i-1]=pa[i];
			pa[i] = temp;
			Node child = new Node(pa);
			chlidern.add(child);
			child.pirent=this;
    	}
		
	}
    void MoveToUp(int[] p ,int i) {
		if (i - col >=0) {
			int[] pa = new int[9]; 
			copyarr(p , pa);
			int temp = pa[i-3];
			pa[i-3]=pa[i];
			pa[i] = temp;
			Node child = new Node(pa);
		   chlidern.add(child);
			child.pirent=this;
			
		}
	}
    void MoveToDown(int[] p ,int i) {
    	if (i+col < puzzle.length) {
    		int[] pa = new int[9]; 
			copyarr(p , pa);
			int temp = pa[i+3];
			pa[i+3]=pa[i];
			pa[i] = temp;
			Node child = new Node(pa);
			chlidern.add(child);
			child.pirent=this;
    	}
    }
    
    String PrintPiz() {
    	int t =0 ;
    	String s="";
    	for(int i= 0;i<col;i++) {
    		for(int j= 0;j<col;j++) {
    			if(puzzle[t]==0){
    				s+="- ";
    				t++;
    			}else {
    			s+=puzzle[t++]+" ";
    			}
    		}
    		s+="\n";
    	}
    	s+="\n";
    	return s;
    }

	boolean isSamePuz(int[] p) {
		boolean isSame=true ;
		for(int i=0 ;i<puzzle.length;i++) {
			if(puzzle[i]!=p[i]) {
				isSame =false;
			}
			
		}
		return isSame;
	}
	void expandMove() {
		for(int i=0;i<puzzle.length;i++) {
			if(puzzle[i]==0) 
				x=i;
		}
		MoveToRight(puzzle,x);
		MoveToLift(puzzle,x);
		MoveToUp(puzzle,x);
		MoveToDown(puzzle,x);
	}
	
	
	
}
 

