import java.util.Comparator;

/**
 * Created by pavan on 28/11/16.
 */
public class Node implements Comparable<Node> {
    Node leftChild;
    Node rightChild;
    char leafChar = '\n';
    int frequency;
    Node(){

    }

    Node(char s,int f) {
        this.frequency = f;
        this.leafChar = s;
    }

    public void addLeftChild(Node node){
        this.leftChild = node;
    }
    public void addRightChild(Node node){
        this.rightChild = node;
    }

    public void setCharAndItsFreq(int frequency, char a){
        this.frequency = frequency;
        this.leafChar = a;
    }

    @Override
    public int compareTo(Node o) {
        return this.frequency < o.frequency?-1 : this.frequency == o.frequency? 0 : 1;
    }

    @Override
    public String toString() {
        return String.valueOf(this.leafChar);
    }
}
