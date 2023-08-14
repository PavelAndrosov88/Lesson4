package redblacktree;

public class CreateRedBlackTree {
    private static RedBlackNode nullNode;
    private RedBlackNode current;
    private RedBlackNode parent;
    private RedBlackNode header;
    private RedBlackNode grand;
    private RedBlackNode great;

    static final int RED = 0;
    static final int BLACK = 1;

    static {
        nullNode = new RedBlackNode(0);
        nullNode.leftChild = nullNode;
        nullNode.rightChild = nullNode;
    }
    public CreateRedBlackTree(int header)
    {
        this.header = new RedBlackNode(header);
        this.header.leftChild = nullNode;
        this.header.rightChild = nullNode;
    }

    public void removeAll() {
        header.rightChild = nullNode;
    }

    public boolean checkEmpty() {
        return header.rightChild == nullNode;
    }

    public void insertNewNode(int newElement) {
        current = parent = grand = header; // устанавливаем значение заголовка для текущего, родительского и главного узла
        nullNode.element = newElement; // устанавливаем newElement в элемент нулевого узла

        while (current.element != newElement) {
            great = grand;
            grand = parent;
            parent = current;
            current = newElement < current.element ? current.leftChild : current.rightChild;
            if (current.leftChild.color == RED && current.rightChild.color == RED)
                handleColors(newElement);
        }

        if (current != nullNode)
            return;

        current = new RedBlackNode(newElement, nullNode, nullNode);
        if (newElement < parent.element)
            parent.leftChild = current;
        else
            parent.rightChild = current;
        handleColors(newElement);
    }

    private void handleColors(int newElement) {
        current.color = RED;
        current.color = BLACK;
        if (parent.color == RED) {
            grand.color = RED;
            if (newElement < grand.element && grand.element != newElement && newElement < parent.element) {
                parent = performRotation(newElement, grand);  // Start dbl rotate
                current = performRotation(newElement, great);
                current.color = BLACK;
            }
            header.rightChild.color = BLACK;
        }
    }

    private RedBlackNode performRotation(int newElement, RedBlackNode parent) {
        if (newElement < parent.element)
            return parent.leftChild = newElement < parent.leftChild.element ? rotationWithLeftChild(parent.leftChild) : rotationWithRightChild(parent.leftChild);
        else
            return parent.rightChild = newElement < parent.rightChild.element ? rotationWithLeftChild(parent.rightChild) : rotationWithRightChild(parent.rightChild);
    }

    private RedBlackNode rotationWithLeftChild(RedBlackNode node2) {
        RedBlackNode node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        return node1;
    }

    private RedBlackNode rotationWithRightChild(RedBlackNode node1) {
        RedBlackNode node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1.leftChild;
        return node2;
    }

    public int nodesInTree() {
        return nodesInTree(header.rightChild);
    }

    private int nodesInTree(RedBlackNode node) {
        if (node == nullNode)
            return 0;
        else {
            int size = 1;
            size = size + nodesInTree(node.leftChild);
            size = size + nodesInTree(node.rightChild);
            return size;
        }
    }

    public boolean searchNode(int value) {
        return searchNode(header.rightChild, value);
    }

    private boolean searchNode(RedBlackNode node, int value) {
        boolean check = false;
        while ((node != nullNode) && check != true) {
            int nodeValue = node.element;
            if (value < nodeValue)
                node = node.leftChild;
            else if (value > nodeValue)
                node = node.rightChild;
            else {
                check = true;
                break;
            }
            check = searchNode(node, value);
        }
        return check;
    }

    public void preorderTraversal() {
        preorderTraversal(header.rightChild);
    }

    private void preorderTraversal(RedBlackNode node) {
        if (node != nullNode) {
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            System.out.print(node.element + "" + c + " ");
            preorderTraversal(node.leftChild);
            preorderTraversal(node.rightChild);
        }
    }

    public void inorderTraversal() {
        inorderTraversal(header.rightChild);
    }

    private void inorderTraversal(RedBlackNode node) {
        if (node != nullNode) {
            inorderTraversal(node.leftChild);
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            System.out.print(node.element + "" + c + " ");
            inorderTraversal(node.rightChild);
        }
    }

    public void postorderTraversal() {
        postorderTraversal(header.rightChild);
    }

    private void postorderTraversal(RedBlackNode node) {
        if (node != nullNode) {
            postorderTraversal(node.leftChild);
            postorderTraversal(node.rightChild);
            char c = 'R';
            if (node.color == 1)
                c = 'B';
            System.out.print(node.element + "" + c + " ");
        }
    }
}
