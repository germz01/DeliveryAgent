package mainSource;

import java.util.Scanner;
import graphSample.*;

public class Application {
	
	public static void main(String args[]) {
		int dimOfP = 0, dimOfS = 0, range = 0;
		char retry = 'Y', sameGraph = 'N', useHeuristic = 'N';
		boolean heuristic = false;
		Agent a = new Agent();
		Scanner s = new Scanner(System.in);
		Graph g = null;
		
		System.out.println("DELIVERY AGENT SIMULATION PROGRAM\n");
		
		while(retry == 'Y') {			
			if(sameGraph == 'N') {
				System.out.println("Insert the dimension of P: ");
				dimOfP = s.nextInt();
				validateDimOfP(dimOfP);
				System.out.println("\nInsert the dimension of S: ");
				dimOfS = s.nextInt();
				validateDimOfS(dimOfP, dimOfS);
				System.out.println("\nInsert the maximum package's content value: ");
				range = s.nextInt();
				validateRange(range);
				
				a.setToDeliver(dimOfS, range);
				
				Node.flushNameCounter();
				g = new Graph(dimOfP, range);
				System.out.println("\nDo you want to use degree-heuristic?(Y/N)");
				useHeuristic = s.next(".").charAt(0);
				if(useHeuristic == 'Y') {
					heuristic = true;
				}
				else{
					heuristic = false;
				}
				a.startSearch(g, heuristic);
			}
			else {
				a.setToDeliver(dimOfS, range);
				System.out.println(g);
				System.out.println("\nDo you want to use degree-heuristic?(Y/N)");
				useHeuristic = s.next(".").charAt(0);
				if(useHeuristic == 'Y') {
					heuristic = true;
				}
				else{
					heuristic = false;
				}
				a.startSearch(g, heuristic);
			}
			
			System.out.println("\nRetry?(Y/N)");
			retry = s.next(".").charAt(0);
			if(retry == 'Y') {
				System.out.println("\nSame Graph?(Y/N)");
				sameGraph = s.next(".").charAt(0);
				System.out.println();
			}
		}
		s.close();
	}
	
	private static void validateDimOfP(int dimOfP) {
		if(dimOfP <= 0) {
			throw new IllegalArgumentException("ERROR!!! THE GRAPH'S DIMENSION MUST BE GREATER THAN 0!!!");
		}
	}
	
	private static void validateDimOfS(int dimOfP, int dimOfS) {
		if(dimOfS > dimOfP) {
			throw new IllegalArgumentException("ERROR!!! THE NUMBER OF PACKAGES CAN'T BE BIGGER THAN THE GRAPH'S NODES!!!");
		}
	}
	
	private static void validateRange(int range) {
		if(range <= 0) {
			throw new IllegalArgumentException("ERROR!!! THE RANGE MUST BE GREATER THAN 0!!!");
		}
	}
	
	private Application() {}
	
}