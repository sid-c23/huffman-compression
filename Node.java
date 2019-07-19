public class Node implements Comparable<Node> {
  char value;
  int frequency;
  Node leftChild;
  Node rightChild;

  public Node(char v, int f, Node left, Node right) {
    this.value = v;
    this.frequency = f
    this.leftChild = left;
    this.rightChild = right;
  }

  @Override // Check if this works
  public int CompareTo(Node other) {
    int comp = Integer.compare(this.frequency, other.frequency); 
    if (comp != 0)
      return comp;
    return this.value.compareTo(other.value);
   }
}
