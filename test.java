import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*; 
public class test {

	public static void main(String[] args) throws IOException {
		
		Scanner scanner = new Scanner(new File("input.TXT"));
		int[] puz = new int [9]; 
		int[] puzgoal = new int [9]; 
		String s="";
		while(scanner.hasNextLine())
		{
			s+=scanner.nextLine();
		}	
		s=s.replaceAll("\\s", "");
		String ini = s.substring(0,9);
		String goal = s.substring(9, 18);
		for(int j=0;j<puz.length;j++) {
			if(ini.substring(j, j+1).equalsIgnoreCase("-")) {
				puz[j]=0;
			}else{
			puz[j]=Integer.parseInt(ini.substring(j, j+1));
			}
		}
		
		for(int j=0;j<puzgoal.length;j++) {
			if(goal.substring(j, j+1).equalsIgnoreCase("-")) {
				puzgoal[j]=0;
			}else{
				puzgoal[j]=Integer.parseInt(goal.substring(j, j+1));
			}
		}
		
		Node iniNode = new Node(puz);
		iniNode.PrintPiz();
		Search se = new Search();
		long start = System.currentTimeMillis();
		List<Node> solve = se.BFS(iniNode,puzgoal);
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		
		 try {
		      FileWriter myWriter = new FileWriter("output.txt");
		      myWriter.write(retS(puz));
		      myWriter.write(retS(puzgoal));
		     
		    
		      if(solve.size()>0) {
					Collections.reverse(solve);
					 myWriter.write("We found a solution \n");
					 myWriter.write("Execution time to find a solution in milliseconds: " + timeElapsed+" \n");
					 myWriter.write("size is "+solve.size());
					  myWriter.write("BFS \n");
					for(int i=0; i<solve.size();i++) 
						myWriter.write(solve.get(i).PrintPiz());
					
				}else 
					myWriter.write("We can't Solve");

		     
		      myWriter.close();
		     System.out.println("Successfully wrote to the file.");
		    } catch (IOException e1) {
		      System.out.println("An error occurred.");
		      e1.printStackTrace();
		    }

	      }
	static String retS(int[] p) {
		int t =0 ;
    	String s="";
    	for(int i= 0;i<3;i++) {
    		for(int j= 0;j<3;j++) {
    			if(p[t]==0) {
    				s+="-"+" ";
    				t++;
    			}else {
    			s+=p[t++]+" ";
    			}
    		}
    		s+="\n";
    	}
    	s+="\n";
    	return s;
		
	}

		
}
