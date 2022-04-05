package saint;
import java.util.Objects;

// Узлы
class Node <T extends Comparable<T>>{

    // Возможные цвета узлов
    enum Color {
        RED,
        BLACK
    }

    T data;
    Color color;
    Node<T> parent;
    Node<T> leftChild;
    Node<T> rightChild;

    public Node(T data, Color color) {
        this.data = data;
        this.color = color;
    }

    public Node (Node<T> other, Color color)  {
        this.data = other.data;
        this.color = color;
        if (other.leftChild != null) {
            this.leftChild = new Node<>(other.leftChild, other.leftChild.color);
        }
        if (other.rightChild != null) {
            this.rightChild = new Node<>(other.rightChild, other.rightChild.color);
        }
    }

    // Сравнение узлов, их потомков, потомков потомков, потомков потомков потомков...
    public static boolean compareNodes(Node<?> a, Node<?> b) {
        if (a == null && b == null) {
            return true;
        }
        if (a != null && b == null) {
            return false;
        }
        if (a == null) {
            return false;
        }

        if (!Objects.equals(a.data, b.data)) {
            return false;
        } else {
            return compareNodes(a.leftChild, b.leftChild) && compareNodes(a.rightChild, b.rightChild);
        }
    }

    public Node<T> getLeftChild() {
        return leftChild;
    }

    public Node<T> getRightChild() {
        return rightChild;
    }

    @Override
    public String toString() {
        return "[" + data + ", " + ((color == Color.RED) ? "RED" : "BLACK")  +"]";
    }
}