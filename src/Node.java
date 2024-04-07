public class Node {

    /**
     * The root value of this node
     */
    int value;
    /**
     * The node object attached to the left side of this node
     */
    Node left;
    /**
     * The node object attached to the right side of this node
     */
    Node right;

    /**
     * Constructs a node using the passed root value
     * @param rootValue The parent value for this node
     */
    public Node (int rootValue) {
        value = rootValue;
        left = null;
        right = null;

    } // end constructor

} // end class
