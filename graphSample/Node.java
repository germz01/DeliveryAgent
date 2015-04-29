package graphSample;

import utilities.Utils;

public class Node {
	
	private static int name = 0;
	private int nodeName, content;
	private Node links[] = new Node[1];
	private boolean canLink[];
	
	public Node(int dimOfP, int packageRange) {
		this.nodeName = name;
		this.content = Utils.givePackages(1, packageRange)[0];
		this.canLink = new boolean[dimOfP];
		name = name + 1;
	}
	
	public int getName() {
		return this.nodeName;
	}
	
	public void setCanLink(int index) {
		this.canLink[index] = true;
	}
	
	public boolean getCanLink(int index) {
		return this.canLink[index];
	}
	
	public boolean isLinked(Node n) {
		boolean tmp = false;
		if(this.links[0] == null || n.links[0] == null) {
			tmp = false;
		}
		else {
			for(Node nodes: n.links) {
				if(nodes != null && this.nodeName == nodes.nodeName) {
					tmp = true;
					break;
				}
			}
		}
		return tmp;
	}
	
	public void addLink(Node n) {
		int tmp = Utils.firstIndexFree(this.links);
		if(tmp == this.links.length) {
			Node newLinks[] = new Node[this.links.length + 1];
			for(int i = 0;i < this.links.length;i++) {
				newLinks[i] = this.links[i];
			}
			newLinks[tmp] = n;
			this.links = newLinks;
		}
		else {
			this.links[tmp] = n;
		}
	}
	
	public Node[] getLinks() {
		return this.links;
	}
	
	public int getLinksNumber() {
		return this.links.length;
	}

	public int getContent() {
		return this.content;
	}
	
	public static void flushNameCounter() {
		name = 0;
	}

	public String toString() {
		String tmp = "Node "+this.nodeName+", content "+this.content+", linked to ";
		if(this.links[0] != null) {
			for(int i = 0;i < this.links.length;i++) {
				if(this.links[i] != null) {
					tmp = tmp+this.links[i].nodeName+" ; ";
				}
			}
		}
		return tmp;
	}
	
}
