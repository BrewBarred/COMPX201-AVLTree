public class Main {
    public static void main(String[] args) {
        System.out.println("\nCommencing Binary Search Tree Testy\n");

        // creates a new BST object
        IntBinarySearchTree bst = new IntBinarySearchTree();
        StrBSTPrinter printer = new StrBSTPrinter();

        bst.add(50);
        bst.add(25);
        bst.add(75);
        bst.add(30);
        bst.add(100);
        bst.add(90);
        bst.add(65);
        bst.add(10);
        bst.add(3);
        bst.add(27);

        System.out.println();
        bst.print();

        System.out.println();
        printer.printNode(bst.root);

        System.out.println();
        System.out.println("BST has value 27: " + bst.hasValue(27));
        System.out.println("BST has value 0: " + bst.hasValue(0));
        System.out.println();


    } // end main

} // end class