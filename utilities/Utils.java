package utilities;

import java.util.Random;
import graphSample.*;

public class Utils {
	
	public static int[] givePackages(int dimOfS, int packageRange) {
		int tmpArray[] = new int[dimOfS];
		for(int i = 0;i < dimOfS;i++) {
			tmpArray[i] = randInt(1, packageRange);
		}
		return tmpArray;
	}
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
		
	public static int firstIndexFree(Node stack[]) {
		int tmp = 0;
		for(int i = 0;i < stack.length;i++) {
			if(stack[i] == null) {
				break;
			}
			tmp = tmp + 1;
			if(tmp == stack.length) {
				break;
			}
		}
		return tmp;
	}
	
	private Utils() {}
	
}
