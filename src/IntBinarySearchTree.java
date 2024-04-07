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
        debug("Attempting to add value: " + x + "...");
        if (root == null) {
            debug("No root found! Adding root value: " + x);
            root = new Node(x);
        } else {
            add(x, root);

        } // end if

        debug("The height of the AVL Tree is now: " + getHeight());
        debug("");

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

            } // end if

            // recursively checks if the passed value is less than the current root value before adding it in
            if (x < currentNode.value) {
                if (currentNode.left == null) {
                    debug("Left child of value of " + currentNode.value + " is null, adding left child value: " + x);
                    currentNode.left = new Node(x);

                } else {
                    add(x, currentNode.left);
                    currentNode = currentNode.left;

                } // end if

            // else if the passed value is greater than the current root value
            } else if (x > currentNode.value) {
                if (currentNode.right == null) {
                    debug("Right child of " + currentNode.value + " is null, adding right child value: " + x);
                    currentNode.right = new Node(x);

                } else {
                    add(x, currentNode.right);
                    currentNode = currentNode.right;

                } // end if

            } else {
                System.out.println("Error adding value! Could not compare value " + x + " against " + currentNode.value);

            } // end if

            balanceTree(x, currentNode);
            return currentNode;

        } catch (Exception e) {
            // gracefully catches error and prints stack trace info for debugging
            e.printStackTrace();

        } // end try
        return null;

    } // end node

    /**
     * Checks the balance factor of this tree then balances it if necessary
     * @param x The value most recently inserted into the AVL Tree
     * @param parent The parent of the inserted value
     */
    public Node balanceTree(int x, Node parent) {
        int balanceFactor = getBalanceFactor();

        // returns early if the tree is currently balanced
        if (balanceFactor <= 1 && balanceFactor >= -1) {
            debug("This tree is balanced with a balance factor of: " + balanceFactor);
            return null;

        } // end if

        debug("WARNING: This tree is imbalanced! Attempting to balance...");

        // if the tree is has a left/left-right imbalance
        if (balanceFactor > 1) {
            System.out.println(" x = " + x);
            System.out.println(" parent.left value = " + parent.left.value);
            if (x <= parent.left.value) {
                debug("The tree has a left imbalance! Rotating right...");
                return rotateRight(parent);

            } else {
                debug("The tree has a left-right imbalance! Rotating left-right...");
                return rotateLeftRight(parent);

            } // end if

        } // end if

        // if the tree is right/right-left imbalanced
        else {
            if (x >= parent.right.value) {
                debug("The tree has a right imbalance! Rotating left...");
                return rotateLeft(parent);

            } else {
                debug("The tree has a right-left imbalance! Rotating right-left...");
                return rotateRightLeft(parent);

            } // end if

        } // end if

    } // end void

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
        if (parent == null) {
            System.out.println("Unable to perform left rotation, parent node was null!");
            return null;

        } // end if

        System.out.println("parent.right = " + parent.right);
        System.out.println("parent.right = " + parent.right.left);
        // creates a node to store the right child before it's overridden
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
        if (parent == null) {
            System.out.println("Unable to perform right rotation, parent node was null!");
            return null;

        } // end if

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
        parent.left = rotateLeft(parent.left);
        return rotateRight(parent);

    } // end node

    /**
     * Performs a right-left rotation of the passed SubTree
     * @param parent The parent node of the SubTree being right-left rotated
     * @return The Node that was previously the right child of the passed parent
     */
    public Node rotateRightLeft(Node parent) {
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
