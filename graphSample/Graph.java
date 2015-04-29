package graphSample;

import utilities.Utils;

public class Graph {
	
	private int dimOfP;
	private Node nodes[];
	
	public Graph(int dimOfP, int packageRange) {
		this.dimOfP = dimOfP;
		this.nodes = new Node[dimOfP];
		for(int i = 0;i != dimOfP;i++) {
			this.nodes[i] = new Node(dimOfP, packageRange);
		}
		this.makeConnections();
		System.out.println(this);
	}
	
	private void makeConnections() {
		for(int i = 0;i < this.nodes.length;i++) {
			for(int j = 0;j < this.nodes.length;j++) {
				if(i != j && Utils.randInt(0, 1) == 1) {
					if(this.nodes[i].getName() < this.nodes[j].getName()) {
						this.nodes[i].setCanLink(j);
						this.nodes[j].setCanLink(i);
					}
				}
			}
			for(int j = 0;j < this.dimOfP;j++) {
				if(this.nodes[i].getCanLink(j) && !(this.nodes[i].isLinked(this.nodes[j]))) {
					this.nodes[i].addLink(this.nodes[j]);
					this.nodes[j].addLink(this.nodes[i]);
				}
			}
		}
	}
	
	public Node[] getNodes() {
		return nodes;
	}

	public String toString() {
		String tmp = "";
		for(Node n: this.nodes) {
			tmp = tmp + n+"\n";
		}
		return tmp;
	}
	
}