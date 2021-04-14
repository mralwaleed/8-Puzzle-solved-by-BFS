import java.util.*;
import java.io.*;
import java.lang.*;
import javax.swing.*;

public class puzzle {
	
	static int NUM = 0;
	static int N = 0;
	static int g = 0;
	
	static boolean FLAG;
	static String[] startStrs;
	static String[] endStrs;
	static int[] start_1d;
	static int[] end_1d;
	static int[] current_1d;
	static ArrayList<int[]> open = new ArrayList<int[]>();
	
	public static void main(String [] args) {
		
		//puzzleChoose();
		
		start_1d = new int[9];
		end_1d = new int[9];
		current_1d = new int[9];
		current_1d = start_1d;
		
		enterState(startStrs, start_1d, "START STATE");
		
		enterState(endStrs, end_1d, "END STATE");
		
		ArrayList<int[]> closed = new ArrayList<int[]>();
		addToClosed(closed, start_1d);
		int[] holder = new int[NUM];
		
		while(matchCheck(current_1d, end_1d) == false) {
			int movePosition = 0;
			int lowestF = 100;
			int currentF = 0;
			moveCheck(current_1d);
			for(int i = 0; i < open.size(); i++) {
				boolean check = isInClosed(open.get(i), closed);
				if(check == false) {
					currentF = hTotal(open.get(i));
					currentF += g;
					matrixPrint(open.get(i));
					System.out.println("F = " + currentF);
					if(i == 0 || currentF < lowestF){
						lowestF = currentF;
						movePosition = i;
					}
				}
			}
			
			current_1d = arrayCopy(open.get(movePosition), current_1d);
			addToClosed(closed, open.get(movePosition));
			System.out.println("Chosen array:");
			matrixPrint(open.get(movePosition));
			open.clear();
			Scanner in = new Scanner(System.in);
			in.nextLine();
		}
		System.out.println(closed.size());

		System.out.println("STEPS:");
		for(int i = 0; i < closed.size(); i++) {
			System.out.println("Move " + i + ":");
			matrixPrint(closed.get(i));
		}
	}
	
	public static void addToClosed(ArrayList<int[]> closed, int[] a) {
		int[] holder = new int[NUM];
		closed.add(arrayCopy(a, holder));
	}
	
	public static boolean isInClosed(int[] current, ArrayList<int[]> closed) { // TRUE if is in closed list, FALSE if not
		for(int i = 0; i < closed.size(); i++) {
			if((matchCheck(current, closed.get(i))) == true) {
				return true;
			}
		}
		return false;
	}

	public static void matrixPrint(int[] states)
	{
		for (int j = 0; j < states.length; j++)
		{
			if (states[j] != 0)
				System.out.print(states[j] + " ");
			else
				System.out.print("* ");
				
			if((j+1)%N == 0) {System.out.println();}
		}
		
		System.out.println();
	}
	
	public static int find(int[] states, int target)
	{
		int found = -1;
		for(int p = 0; p < states.length; p++)
		{
			found = states[p];
			if(found == target) return p;
		}
		return 0;
	}
	
	public static int[] swap(int[] statesAA, int first, int second)
	{

		int[] copy = new int[NUM];
		copy = arrayCopy(statesAA, copy);
		int posF = 0;
		int tempInt = 0;
		int posS = 0;

		tempInt = copy[first];
		copy[first] = copy[second];
		copy[second] = tempInt;
		return copy;
	}
	
	public static int[] arrayCopy(int[] a, int[] b) {
		for(int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		return b;
	}
	
	public static void moveCheck(int[] states)
	{
		int letter = 0;
		int n = find(states, 0);
		int[] temp = new int[NUM];
		int[] copy = new int[NUM];
		
		copy = arrayCopy(states, copy);
	if (matchCheck(current_1d,end_1d))
	{
		System.out.println("End state reached, no viable moves remaining");
	}
	else
	{	
		if (n-1 >= 0 && (n-1)%N != 2)
		{
			open.add(swap(copy,n,n-1));
		}

		if (n+1 < states.length)
		{
			if ((n+1)%N != 0)
			{
			open.add(swap(copy,n,n+1));
			}
		}
		if (n-N >= 0)
		{
			open.add(swap(copy,n,n-N));
		}
		if (n+N < states.length)
		{
			open.add(swap(copy,n,n+N));
		}
	}
	}
	
	
	public static boolean matchCheck(int[] current, int[] end)
	{
		for(int i = 0; i < current.length; i++)
		{
			if(current[i] != end[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public static int h(int[] states,int p)
	{
		int tempC = find(states,p);
		int tempE = find(end_1d,p);
		int hNum = 0;
		if((tempC/N)-(tempE/N) < 0)
		{
			hNum = ((tempC/N)-(tempE/N))*(-1);
		}
		else
		{
			hNum = (tempC/N)-(tempE/N);
		}
		if((tempC%N)-(tempE%N) < 0) {
		
			hNum += ((tempC%N)-(tempE%N))*(-1);
		}
		else 
		{
			hNum += (tempC%N)-(tempE%N);
		}
		return hNum;
	}
	
	public static int hTotal(int[] states)
	{
		int hTotal = 0;
		for(int o = 0; o < states.length; o++)
		{
			if (states[o] != 0)
			{
				hTotal += h(states,states[o]);
			}
		}
		return hTotal;
	}
	
	public static String letterChoose(int i)
	{
		switch(i)
		{
			case 0:
				return "a)";
			case 1:
				return "b)";
			case 2:
				return "c)";
			case 3:
				return "d)";
			default:
				return "How did you break this?";	
		}
	}
	
	public static void puzzleChoose() {
		
		boolean flag = true;
		int puzzle = 0;
		while (flag) {
			
			String str = JOptionPane.showInputDialog("Please enter 8 or 15 to choose a puzzle: ");
			
			if (!str.equals("")) {
				if (str.equals("8")) {
					NUM = 9;
					N = 3;
					flag = false;
				}
				else if (str.equals("15")) {
					NUM = 16;
					N = 4;
					flag = false;
				}
				else
					JOptionPane.showMessageDialog(null, "Please enter 8 or 15!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(null, "Please enter something!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void enterState(String[] stateStrs, int[] states, String mark) {
		
		FLAG = true;
		int[] temp;
		
		while (FLAG) {
			
			String str = JOptionPane.showInputDialog("Please enter integers as the " + mark + ": ");
			
			if (!str.equals("")) {
				
				stateStrs = str.split(" ");
				
				if (checkIfIntegers(stateStrs)) {
					
					temp = new int[stateStrs.length];
					
					for (int i = 0; i < temp.length; i++)
						temp[i] = Integer.parseInt(stateStrs[i]);
				
					if (temp.length != NUM) {
						JOptionPane.showMessageDialog(null, "Number of Integers dismatch\nPlease Retry!", "Error", JOptionPane.ERROR_MESSAGE);
						temp = null;
					}
					else if (!isUnique(temp)) {
						JOptionPane.showMessageDialog(null, "Duplicate Numbers Exist\nPlease Retry!", "Error", JOptionPane.ERROR_MESSAGE);
						temp = null;
					}
					else if (!isInRange(temp)) {
						JOptionPane.showMessageDialog(null, "Out of Range\nPlease Retry!", "Error", JOptionPane.ERROR_MESSAGE);
						temp = null;
					}
					else {
						for (int i = 0; i < states.length; i++)
							states[i] = temp[i];
						FLAG = false;
					}
				}
				
				else
					JOptionPane.showMessageDialog(null, "Integers Required\nPlease Retry!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			else
				JOptionPane.showMessageDialog(null, "Please enter something!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static boolean isUnique(int[] states) {
		
		for (int i = 0; i < states.length - 1; i++) {
			
			for (int j = i + 1; j < states.length; j++) {
				
				if (states[i] == states[j])
					return false;
			}
		}
		
		return true;
	}
	
	public static boolean isInRange(int[] states) {
		
		for (int i = 0; i < states.length; i++) {
			
			if (states[i] > NUM - 1)
				return false;
		}
		
		return true;
	}
	
	public static boolean checkIfIntegers(String[] s) {
		
		for (int i = 0; i < s.length; i++)
			
			if (!isInteger(s[i]))
				return false;
		
		return true;
	}
	
	public static boolean isInteger(String s) {
		
		boolean isValidInteger = false;
		
		try {
			Integer.parseInt(s);
			isValidInteger = true;
		} catch (NumberFormatException ex) {}
 
      return isValidInteger;
   }
}