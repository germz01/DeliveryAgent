package mainSource;

import utilities.*;
import graphSample.*;

public class Agent {
	private int toDeliver[];
	
	public void setToDeliver(int dimOfS, int packageRange) {
		this.toDeliver = Utils.givePackages(dimOfS, packageRange);
		System.out.println("\n"+this+"\n");
	}
	
	public void startSearch(Graph g, boolean degreeHeuristic) {
		FifoQueue possibleStarts = this.findStartPoints(g, degreeHeuristic);
		while(true) {
			Node start = possibleStarts.pop();
			if(start != null) {
				if(this.solveProblem(start, g) != 0){
					break;
				}
			}
			else {
				break;
			}
		}
	}
	
	private FifoQueue findStartPoints(Graph g, boolean degreeHeuristic) {
		FifoQueue tmp = new FifoQueue();
		for(Node n: g.getNodes()) {
			if(n.getContent() == this.toDeliver[0]) {
				tmp.push(n);;
			}
		}
		if(degreeHeuristic){
			System.out.println("Using degree-heuristic...");
			tmp.sortQueue();
		}
		return tmp;
	}
	
	private int solveProblem(Node start, Graph g) {
		System.out.println("\nPackage "+this.toDeliver[0]+" delivered at Node "+start.getName()+"!");
		int deliveringPackage = 1, toReturn = 0;
		boolean explored[] = new boolean[g.getNodes().length];
		explored[start.getName()] = true;
		FifoQueue frontier;
		LifoQueue path = new LifoQueue();
		frontier = this.setFrontier(this.toDeliver[deliveringPackage], start.getLinks(), explored);
		path.push(start);
		
		while(true) {
			Node state = frontier.pop();
			if(state == null && deliveringPackage == this.toDeliver.length) {
				System.out.println("\nALL PACKAGES DELIVERED!");
				toReturn = 1;
				break;
			}
			else if(state == null) {
				System.out.println("\nFAILURE AT NODE "+path.pop().getName()+"!");
				deliveringPackage = deliveringPackage - 1;
				if(path.getFirstElement() == null) {
					break;
				}
				else {
					frontier = this.setFrontier(this.toDeliver[deliveringPackage], path.getFirstElement().getLinks(), explored);
				}
			}
			else {
				System.out.println("\nPackage "+this.toDeliver[deliveringPackage]+" delivered at Node "+state.getName()+"!");
				path.push(state);
				if(deliveringPackage != this.toDeliver.length-1) {
					deliveringPackage = deliveringPackage + 1;
					frontier = this.setFrontier(this.toDeliver[deliveringPackage], state.getLinks(), explored);
				}
				else {
					deliveringPackage = deliveringPackage + 1;
					frontier.setNull(0);
				}
				explored[state.getName()] = true;
			}
		}
		return toReturn;
	}
	
	private FifoQueue setFrontier(int deliveringPackage, Node[] actions, boolean explored[]) {
		FifoQueue tmp = new FifoQueue();
		for(int i = 0;i < actions.length;i++) {
			if(actions[i] == null) {
				break;
			}
			if(actions[i].getContent() == deliveringPackage && explored[actions[i].getName()] == false) {
				tmp.push(actions[i]);
			}
		}
		return tmp;
	}
	
	public String toString() {
		String tmp = "Delivery Agent, packages: ";
		for(int pack: this.toDeliver) {
			tmp = tmp+pack+" ; ";
		}
		return tmp;
	}
	
	public Agent() {}
	
}