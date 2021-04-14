import java.util.*; 

public class Search {
	
	
	List<Node> BFS(Node root,int [] gol){
		List<Node> PathToSolve = new ArrayList<Node>();
		List<Node> OpenList = new ArrayList<Node>();
		List<Node> CloseList = new ArrayList<Node>();
		OpenList.add(root);
		
		boolean goalFound = false ;
	
		while(!OpenList.isEmpty() && !goalFound) {
			
			Node currrentNode = OpenList.get(0);
			CloseList.add(currrentNode);
			
			OpenList.remove(0);
			currrentNode.expandMove();
			for(int i=0 ; i< currrentNode.chlidern.size();i++) {
				Node currrentChi = currrentNode.chlidern.get(i);
			
				if(currrentChi.isGaol(gol)) {
				//	System.out.println("Goal is found ");
					goalFound =true ;
					Pathtest(PathToSolve,currrentChi); 
					
				}
				if(!contains(OpenList,currrentChi)||!contains(CloseList,currrentChi)) {
					OpenList.add(currrentChi);
				}
	
			}
		}
		
		return PathToSolve;
	}
	public  boolean contains(List<Node> lis ,Node co) {
		boolean cout = false;
		for(int i=0; i<lis.size();i++) {
		 if(lis.get(i).isSamePuz(co.puzzle)) { 

			 cout = true ;}
		}
		return  cout;
	}
	void Pathtest(List<Node> path ,Node n) {
	
		Node current = n;
		path.add(current);
		while(current.pirent != null) {
			current = current.pirent;
			path.add(current);
		}
		
	}

}
