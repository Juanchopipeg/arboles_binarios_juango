public class Node<E> {

    /* fields */
    E value;      /* valor o dato del nodo */
    int key;      /* clave de ordenamiento */
    Node left;
    Node right;

    /* methods */
    public Node() { }

    public Node(E value) {
        this.value = value;
    }

    public Node(int key) {
        this.key = key;
    }

    public Node(int key, E value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() { return key; }
    public void setKey(int newKey) { key = newKey; }

    public E getValue() { return value; }
    public void setValue(E value) { this.value = value; }

    public Node getLeft() { return this.left; }
    public Node getRight() { return this.right; }

    // CORRECCIÓN: se asigna el parámetro, no el atributo a sí mismo
    public void setLeft(Node newLeft) { this.left = newLeft; }
    public void setRight(Node newRight) { this.right = newRight; }
}