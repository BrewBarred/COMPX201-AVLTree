public class IntBinarySearchTree {

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
            if (x < currentNode.value) {
                return hasValue(x, currentNode.left);
                // else if the passed value is larger than the parent value

            } else if (x > currentNode.value) {
                return hasValue(x, currentNode.right);

            } else {
                throw new UnsupportedOperationException("Error finding value! Unable to check value " + x + " against " + currentNode.value);

            } // end if

        } catch (Exception e) {
            // gracefully catches error and prints stack trace info for debugging
            e.printStackTrace();
            return false;

        } // end try

    } // end boolean

    /**
     * Inserts a new node to this Binary Search Tree
     * @param x The value to insert into this Binary Search Tree
     * @return The node that was manipulated to insert the passed value (Used to update the tree)
     */
    public void add(int x) {
        System.out.println("Attempting to add value: " + x + "...");
        if (root == null) {
            System.out.println("No root found! Adding root value: " + x);
            root = new Node(x);
        } else {
            add(x, root);

        } // end if

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
                System.out.println("Parent is null, adding parent: " + x);
                currentNode = new Node(x);
                return currentNode;

            } // end if

            // recursively checks if the passed value is less than the current root value before adding it in
            if (x < currentNode.value) {
                if (currentNode.left == null) {
                    System.out.println("Left child of value of " + currentNode.value + " is null, adding left child value: " + x);
                    currentNode.left = new Node(x);

                } else {
                    add(x, currentNode.left);
                    return currentNode.left;

                } // end if

            // else if the passed value is greater than the current root value
            } else if (x > currentNode.value) {
                if (currentNode.right == null) {
                    System.out.println("Right child of " + currentNode.value + " is null, adding right child value: " + x);
                    currentNode.right = new Node(x);

                } else {
                    add(x, currentNode.right);
                    return currentNode.right;

                } // end if

            } else {
                throw new UnsupportedOperationException("Error adding value! Could not compare value " + x + " against " + currentNode.value);

            } // end if

        } catch (Exception e) {
            // gracefully catches error and prints stack trace info for debugging
            e.printStackTrace();

        } // end try
        return null;

    } // end node


    /**
     * Removes the passed value from this binary search tree
     * @param x The value to remove from this BST
     */
    public void remove(int x) {


    } // end void

    /**
     Prints this binary search tree in order from the lowest -> highest value
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

    public void print() {
        System.out.println("Printing BST in order...");
        // test print fix
        printInOrder(root);

    } // end if

} // end class
