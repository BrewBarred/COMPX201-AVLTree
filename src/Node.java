public class Node {

    /**
     * The root value of this node
     */
    int value;
    /**
     * The node object attached to the left side of this node
     */
    Node left = null;
    /**
     * The node object attached to the right side of this node
     */
    Node right = null;

    /**
     * Constructs a node using the passed root value
     * @param rootValue The parent value for this node
     */
    public Node (int rootValue) {
        int value = rootValue;

    } // end constructor

    /**
     * Constructs a node using the passed root value and left-child value
     * @param rootValue The parent value for this node
     * @param leftChild The left-child value for this node
     */
    public Node (int rootValue, Node leftChild) {
        int value = rootValue;
        left = leftChild;

    } // end constructor

    /**
     * Constructs a node using the passed root value, left-child value and right-child value
     * @param rootValue The parent value for this node
     * @param leftChild The left-child value for this node
     * @param rightChild The right-child value for this node
     */
    public Node (int rootValue, Node leftChild, Node rightChild) {
        int value = rootValue;
        left = leftChild;
        right = rightChild;

    } // end constructor

} // end class
