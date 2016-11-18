package inlamningsuppgift5;



import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeIterator<AnyType> {

	private NodeCounter next;

	public TreeIterator(NodeCounter root) {
		next = root;
		if (next == null)
			return;
		while (next.getLeft() != null)
			next = next.getLeft();
	}

	public boolean hasNext() {
		return next != null;
	}

	public NodeCounter next() {
		if (!hasNext())
			throw new NoSuchElementException();
		NodeCounter r = next;
		// if you can walk right, walk right, then fully left.
		// otherwise, walk up until you come from left.
		if (next.getRight() != null) {
			next = next.getRight();
			while (next.getLeft() != null)
				next = next.getLeft();
			return r;
		} else
			while (true) {
				if (next.getParent() == null) {
					next = null;
					return r;
				}
				if (next.getParent().getLeft() == next) {
					next = next.getParent();
					return r;
				}
				next = next.getParent();
			}
	}
}
