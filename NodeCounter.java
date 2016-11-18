package inlamningsuppgift5;

public class NodeCounter implements Comparable {

	private Object element;
	private int count;
	private NodeCounter left;
	private NodeCounter right;
	private NodeCounter parent;

	public NodeCounter(Object o, NodeCounter lt, NodeCounter rt) {
		element = o;
		count = 1;
		left = lt;
		right = rt;
	}

	public NodeCounter() {
		this.element = null;
		// this.count = 0;
		this.left = null;
		this.right = null;
	}

	public Object getElement() {
		return this.element;
	}

	public int getCount() {
		return this.count;
	}

	public NodeCounter getLeft() {
		return this.left;
	}

	public NodeCounter getRight() {
		return this.right;
	}

	public NodeCounter getParent() {
		return parent;
	}

	public void setParent(NodeCounter node) {
		parent = node;
	}

	public void setLeft(NodeCounter nyNod) {
		left = nyNod;
	}

	public void setRight(NodeCounter nyNod) {
		right = nyNod;
	}

	public void incrementCount() {
		count++;
	}

	@Override
	public int compareTo(Object o) {
		return this.element.toString().compareTo(o.toString());
	}

}
