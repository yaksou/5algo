package inlamningsuppgift5;


import java.util.*;

public class TreeSetCounter<AnyType> {

	private NodeCounter root;
	private int count;
	private String maxWord;
	private int[] balance = new int[2];

	public TreeSetCounter(Object o) {
		root = new NodeCounter(o, null, null);
		count = 0;
		maxWord = "";
	}

	public TreeSetCounter() {
		root = new NodeCounter(null, null, null);
		count = 0;
		maxWord = "";
	}

	public void addWord(String a) {
		NodeCounter temp = root;
		if (temp.getElement() == null) {
			root = new NodeCounter(a, null, null);
		} else {
			while (true) {
				if (temp.compareTo(a) > 0) {
					if (temp.getLeft() != null) {
						temp = temp.getLeft();
					} else {
						temp.setLeft(new NodeCounter(a, null, null));
						temp.getLeft().setParent(temp);
						checkBalance();
						break;
					}
				} else if (temp.compareTo(a) < 0) {
					if (temp.getRight() != null) {
						temp = temp.getRight();
					} else {
						temp.setRight(new NodeCounter(a, null, null));
						temp.getRight().setParent(temp);
						checkBalance();
						break;
					}
				} else {
					temp.incrementCount();
					if (temp.getCount() > count) {
						count = temp.getCount();
						maxWord = temp.getElement().toString();
					}
					break;
				}
			}
		}
	}

	public void makeEmpty() {
		root = new NodeCounter(null, null, null);
	}

	public String getMaxFrekv() {
		if (count == 0) {
			maxWord = "Alla ord förrekommer 1 gång";
		}
		return maxWord;
	}

	public void checkBalance() {
		balance[0] = 0;
		balance[1] = 0;
		checkDepth(root);
		// System.out.println("Left: " + balance[0] + ", Right: " + balance[1]);
		if (balance[0] >= balance[1] + 2) {
			getLeftBalance();
		}
		if (balance[1] >= balance[0] + 2) {
			getRightBalance();
		}
		checkDepth(root);
		// System.out.println("Left: " + balance[0] + ", Right: " + balance[1]);
		// System.out.println("");

	}

	public int checkDepth(NodeCounter node) {

		if (node == null) {

			return 0;
		} else {

			/* compute the depth of each subtree */
			int lDepth = checkDepth(node.getLeft());
			int rDepth = checkDepth(node.getRight());
			balance[0] = lDepth;
			balance[1] = rDepth;

			/* use the larger one */
			if (lDepth > rDepth) {
				return (lDepth + 1);
			} else {
				return (rDepth + 1);
			}
		}
	}

	public void getLeftBalance() {

		NodeCounter tempLeftRight = root.getLeft().getRight();
		NodeCounter tempOldRoot = root;
		root = root.getLeft();
		root.setRight(tempOldRoot);
		tempOldRoot.setLeft(tempLeftRight);
		root.getRight().setParent(root);
		root.setParent(null);

		if (tempLeftRight != null) {
			tempLeftRight.setParent(tempOldRoot);
		}
	}

	public void getRightBalance() {
		NodeCounter tempRightLeft = root.getRight().getLeft();
		NodeCounter tempOldRoot = root;
		root = root.getRight();
		root.setLeft(tempOldRoot);
		tempOldRoot.setRight(tempRightLeft);
		root.setParent(null);
		root.getLeft().setParent(root);

		if (tempRightLeft != null) {
			tempRightLeft.setParent(tempOldRoot);
		}
	}

	// public void setUpPrint(){
	// print(root);
	// }

	// public void print(NodeCounter node){
	// if(node.getLeft()!=null){
	// print(node.getLeft());
	// }
	// System.out.println(node.getElement().toString() + " " + node.getCount());
	//
	//
	// if(node.getRight()!=null){
	// print(node.getRight());
	// }
	// }

	public TreeIterator<AnyType> Iterator() {
		return new TreeIterator<AnyType>(root);
	}

	public int[] getTreeBalance() {
		return balance;
	}

}
