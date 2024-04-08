public class IntBinarySearchTree {

    /**
     * Enable to display debug messages to console while testing
     */
    boolean debug = true;
    /**
     * The root node of this tree (i.e., the top-most value in the tree diagram)
     */
    Node root;

    /**
     * Constructs a new Binary Search Tree object
     */
    public IntBinarySearchTree() {
        root = null;

    } // end constructor

    /**
     * Searches each node of this Binary Search Tree to check if it contains the passed value
     * @param x The value to find in this Binary Search Tree
     * @return True if the passed value is found in this Binary Search Tree, else returns false
     */
    public boolean hasValue(int x) {
        return hasValue(x, root);

    } // end boolean

    /**
     * Searches each branch linked to the passed parent node to check if it contains the passed value
     * @param x The value to find in this Binary Search Tree
     * @param currentNode The current node being searched
     * @return True if the passed node contains the passed value, else returns false
     */
    public boolean hasValue(int x, Node currentNode) {
        try {
            // if the currentNode is null, the value cannot be contained within this tree
            if (currentNode == null) {
                return false;

            } else if (currentNode.value == x) {
                return true;

            } // end if

            // if the passed value is smaller than the parent value
            if (x < currentNode.value)
                return hasValue(x, currentNode.left);
                // else if the passed value is larger than the parent value
            else
                return hasValue(x, currentNode.right);

        } catch (Exception e) {
            // gracefully catches error and prints stack trace info for debugging
            e.printStackTrace();
            return false;

        } // end try

    } // end boolean

    /**
     * Inserts a new node to this Binary Search Tree
     * @param x The value to insert into this Binary Search Tree
     */
    public void add(int x) {
        debug("Attempting to add value " + x + " to the tree...");
        root = add(x, root);
        debug("The height of the AVL Tree is now: " + getHeight());
        print();

    } // end node

    /**
     * Inserts a new node to this Binary Search Tree by recursively searching
     * for the first empty root on the correct side of the Tree
     * @param x The value to add to this tree
     * @param currentNode The current root being compared against this value
     * @return The node that
     */
    public Node add(int x, Node currentNode) {
        try {
            if (currentNode == null) {
                debug("Parent is null, adding parent: " + x);
                currentNode = new Node(x);

            // if the passed value is less than the current node value, finds a spare slot in the left SubTree for insertion
            } else if (x < currentNode.value) {
                currentNode.left = add(x, currentNode.left);

            // else if the passed value is greater than the current node value, finds a spare slot in the right SubTree for insertion
            } else {
                currentNode.right = add(x, currentNode.right);

            } // end if

            return balanceTree(x, currentNode);

        } catch (Exception e) {
            // gracefully catches error and prints stack trace info for debugging
            e.printStackTrace();
            return null;

        } // end try

    } // end node

    /**
     * Checks the balance factor of this tree then balances it if necessary by performing the necessary rotations on it
     * @param x The value most recently inserted into the AVL Tree
     * @param parent The parent of the inserted value
     */
    public Node balanceTree(int x, Node parent) {
        int balanceFactor = getBalanceFactor();

        // if there is a left-imbalance
        if (balanceFactor > 1) {
            // rotates right if there's a left imbalance
            if (x < parent.left.value) {
                System.out.println("The tree has a left-imbalance!");
                return rotateRight(parent);
            // rotates left-right if there's a left-right imbalance
            } else if (x > parent.left.value) {
                System.out.println("The tree has a left-Right-imbalance!");
                return rotateLeftRight(parent);

            } // end if

        // else if there is a right-imbalance
        } else if (balanceFactor < -1) {
            // rotates left if there's a right imbalance
            if (x > parent.right.value) {
                System.out.println("The tree has a right-imbalance");
                return rotateLeft(parent);
            // rotates right-left if there is a right-left-imbalance
            } else if (x < parent.right.value) {
                System.out.println("The tree has a right-left-imbalance");
                return rotateRightLeft(parent);

            } // end if

        } else {
            System.out.println("The tree is balanced with a balance factor of " + balanceFactor);

        } // end if

        return parent;

    } // end Node

    public Node remove(Node currentNode){
 // Leaf node: simply remove node from tree.
 // Single Parent: node has only one child, replace the node with its child.
 // Two Children: node has two subtrees, but which node to replace with? Typically the left-most node of the right subtree
        return currentNode;
    } // end node

    /**
     * Gets the balance factor of this tree
     * @return An integer value denoting the BalanceFactor of this tree
     */
    public int getBalanceFactor() {
        return getBalanceFactor(root);

    } // end int

    /**
     * Gets the balance factor of the passed tree
     * @param root The root node of the Tree being balance checked
     * @return An integer value denoting the BalanceFactor of the passed tree
     */
    public int getBalanceFactor(Node root) {
        if (root == null)
            return 0;
        return getHeight(root.left) - getHeight(root.right);

    } // end int

    /**
     * Gets the height of this tree
     * @return An integer value denoting the height of this tree
     */
    public int getHeight() {
        return getHeight(root);

    } // end int

    /**
     * Gets the height of the passed SubTree
     * @param subTree The root of the SubTree currently being measured
     * @return An integer value denoting the height of the passed SubTree
     */
    public int getHeight(Node subTree) {
        if (subTree == null)
            return -1;
        // returns 1 plus the greater value of the height of the LST or RST
        return Math.max(getHeight(subTree.left), getHeight(subTree.right)) + 1;

    } // end int

    /**
     * Performs a left rotation on the passed SubTree
     * @param parent The parent node of the SubTree being rotated
     * @return The Node that was previously the right child of the passed parent
     */
    public Node rotateLeft(Node parent) {
        System.out.println("Rotating left...");
        Node child = parent.right;
        parent.right = child.left;
        child.left = parent;
        return child;

    } // end node

    /**
     * Performs a right rotation on the passed SubTree
     * @param parent The parent node of the SubTree being rotated
     * @return The Node that was previously the left child of the passed parent
     */
    public Node rotateRight(Node parent) {
        System.out.println("Rotating right...");
        Node child = parent.left;
        parent.left = child.right;
        child.right = parent;
        return child;

    } // end node

    /**
     * Performs a left-right rotation of the passed SubTree
     * @param parent The parent node of the SubTree being left-right rotated
     * @return The Node that was previously the left child of the passed parent
     */
    public Node rotateLeftRight(Node parent) {
        System.out.println("Rotating left-right...");
        parent.left = rotateLeft(parent.left);
        return rotateRight(parent);

    } // end node

    /**
     * Performs a right-left rotation of the passed SubTree
     * @param parent The parent node of the SubTree being right-left rotated
     * @return The Node that was previously the right child of the passed parent
     */
    public Node rotateRightLeft(Node parent) {
        System.out.println("Rotating right-left...");
        parent.right = rotateRight(parent.right);
        return rotateLeft(parent);

    } // end node

    /**
     * Removes the passed value from this binary search tree
     * @param x The value to remove from this BST
     */
    public void remove(int x) {
// Three cases for deletion:
//• Leaf node: simply remove node from tree.
        if (root.value == x) {

        }
//• Single Parent: node has only one child, replace the node with its child.
//• Two Children: node has two subtrees, but which node to replace with?

    } // end void

    /**
     Prints this binary search tree in order from the lowest -> the highest value
     @param currentNode The node to process/print
     */
    private void printInOrder(Node currentNode) {
        if (currentNode != null) {
            // recursively works through left subtree (left child -> root -> right child)
            printInOrder(currentNode.left);
            // prints the current node value
            System.out.print(currentNode.value + " ");
            // recursively works through right subtree (left child -> root -> right child)
            printInOrder(currentNode.right); // Print right subtree

        } // end if

    } // end node

    /**
     * Prints this whole tree to the console in order from the smallest to the largest value
     */
    public void print() {
        System.out.println("Printing BST in order...");
        printInOrder(root);
        System.out.println("");
        StrBSTPrinter.printNode(root);

    } // end if

    /**
     * If debug mode is enabled, allows debug messages to be printed to the console
     * @param debugMessage The debug message to be printed
     */
    public void debug(String debugMessage) {
        if (debug)
            System.out.println(debugMessage);

    } // end void

} // end class
