package redblacktree;

public class RedBlackNode {
    RedBlackNode leftChild, rightChild;
    int element;
    int color;

    public RedBlackNode(int element){
        this(element, null,null);
    }
    public RedBlackNode(int element, RedBlackNode leftChild, RedBlackNode rightChild)
    {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        color = 1;
    }
}
