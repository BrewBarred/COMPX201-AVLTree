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
        root = add(x, root);

    } // end node

    /**
     * Inserts a new node to this Binary Search Tree by recursively searching
     * for the first empty root on the correct side of the Tree
     * @param x The value to add to this tree
     * @param currentRoot The current root being compared against this value
     * @return The node that
     */
    public Node add(int x, Node currentRoot) {
        try {
            // adds our value to the current root if it's empty
            if (currentRoot == null) {
                currentRoot = new Node(x);
                return currentRoot;

            } // end if

            // recursively checks if the passed value is less than the current root value before adding it in
            if (x < currentRoot.value) {
                return add(x, currentRoot.left);
            // else if the passed value is greater than the current root value

            } else if (x > currentRoot.value) {
                return add(x, currentRoot.right);

            } else {
                throw new UnsupportedOperationException("Error adding value! Could not compare value " + x + " against " + currentRoot.value);

            } // end if

        } catch (Exception e) {
            // gracefully catches error and prints stack trace info for debugging
            e.printStackTrace();
            return null;

        } // end try

    } // end node

    public void print(Node currentNode) {
            // print in order??
        System.out.println(currentNode);
            if (currentNode != null) {
                print(currentNode.left); // Print left subtree
                System.out.print(currentNode.value + " "); // Print current node
                print(currentNode.right); // Print right subtree

            } // end if

    } // end void

    public void print() {
        // test print fix
        print(root);
    }

} // end class
