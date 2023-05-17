/**
 * UnderflowException.java
 * @author Bradley Allen
 * Exception used by BinarySearchTree.java and AvlTree.java (by Weiss)
 */
public class UnderflowException extends Exception {
	public UnderflowException(String errorMessage) {
		super(errorMessage);
	}
	public UnderflowException() {
		super();
	}
}
