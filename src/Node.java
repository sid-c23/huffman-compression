public class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node leftChild;
    Node rightChild;

    public Node(char c, int f, Node l, Node r) {
        this.character = c;
        this.frequency = f;
        this.leftChild = l;
        this.rightChild = r;
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild == null;
    }

    @Override
    public int compareTo(Node o) {
        int compVal = Integer.compare(this.frequency, o.frequency);
        if (compVal != 0)
            return compVal;
        return Integer.compare(this.character, o.character);
    }
}
