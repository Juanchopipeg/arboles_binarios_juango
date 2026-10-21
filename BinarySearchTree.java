import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void insertNode(Node newNode) {
        root = insertRecursive(newNode, root);
    }

    public Node insertRecursive(Node nodeToInsert, Node rootNode) {
        if (rootNode == null)
            return nodeToInsert;

        if (nodeToInsert.getKey() < rootNode.getKey())
            rootNode.setLeft(insertRecursive(nodeToInsert, rootNode.getLeft()));
        else // mayor o igual -> derecha
            rootNode.setRight(insertRecursive(nodeToInsert, rootNode.getRight()));
        return rootNode;
    }

    public boolean searchNodeRecursive(int key, Node rootNode) {
        if (rootNode == null)
            return false;
        if (rootNode.getKey() == key)
            return true;
        if (rootNode.getKey() > key)
            return searchNodeRecursive(key, rootNode.getLeft());
        return searchNodeRecursive(key, rootNode.getRight());
    }

    public Node buscarNodo(int key, Node rootNode) {
        if (rootNode == null)
            return null;
        if (rootNode.getKey() == key)
            return rootNode;
        if (rootNode.getKey() > key)
            return buscarNodo(key, rootNode.getLeft());
        return buscarNodo(key, rootNode.getRight());
    }

    public void InOrder(Node root) {
        if (root == null)
            return;
        InOrder(root.getLeft());
        System.out.println("->" + root.getKey());
        InOrder(root.getRight());
    }

    public void preOrder(Node root) {
        if (root == null)
            return;
        System.out.println("->" + root.getKey());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public void posOrder(Node root) {
        if (root == null)
            return;
        posOrder(root.getLeft());
        posOrder(root.getRight());
        System.out.println("->" + root.getKey());
    }


    // Parte A.2: in-orden => llaves de menor a mayor
    public ArrayList<Integer> getKeyList() {
        ArrayList<Integer> keyList = new ArrayList<>();
        llenarClaves(root, keyList);
        return keyList;
    }

    private void llenarClaves(Node n, ArrayList<Integer> lista) {
        if (n == null)
            return;
        llenarClaves(n.getLeft(), lista);
        lista.add(n.getKey());
        llenarClaves(n.getRight(), lista);
    }

    // R1: in-orden
    public ArrayList<Cliente> clientesAscendente() {
        ArrayList<Cliente> lista = new ArrayList<>();
        llenarClientes(root, lista);
        return lista;
    }

    private void llenarClientes(Node n, ArrayList<Cliente> lista) {
        if (n == null)
            return;
        llenarClientes(n.getLeft(), lista);
        lista.add((Cliente) n.getValue());
        llenarClientes(n.getRight(), lista);
    }

    // R2: in-orden inverso (D-N-I), se detiene al reunir n clientes
    public ArrayList<Cliente> mayoresN(int n) {
        ArrayList<Cliente> lista = new ArrayList<>();
        mayoresRec(root, n, lista);
        return lista;
    }

    private void mayoresRec(Node nodo, int n, ArrayList<Cliente> lista) {
        if (nodo == null || lista.size() >= n)
            return;
        mayoresRec(nodo.getRight(), n, lista);
        if (lista.size() < n)   // la derecha pudo completar la lista
            lista.add((Cliente) nodo.getValue());
        mayoresRec(nodo.getLeft(), n, lista);
    }

    // R3: in-orden con poda
    public ArrayList<Cliente> clientesEnRango(int min, int max) {
        ArrayList<Cliente> lista = new ArrayList<>();
        rangoRec(root, min, max, lista);
        return lista;
    }

    private void rangoRec(Node nodo, int min, int max, ArrayList<Cliente> lista) {
        if (nodo == null)
            return;
        int k = nodo.getKey();
        if (min < k)                      // a la izquierda hay claves menores que k
            rangoRec(nodo.getLeft(), min, max, lista);
        if (k >= min && k <= max)
            lista.add((Cliente) nodo.getValue());
        if (k <= max)                     // "<=" porque los duplicados van a la derecha
            rangoRec(nodo.getRight(), min, max, lista);
    }

    // R5: el mínimo está en el nodo más a la izquierda (solo se sigue un camino)
    public Cliente minimo() {
        if (root == null)
            return null;
        Node n = root;
        while (n.getLeft() != null)
            n = n.getLeft();
        return (Cliente) n.getValue();
    }

    // R5: el máximo está en el nodo más a la derecha
    public Cliente maximo() {
        if (root == null)
            return null;
        Node n = root;
        while (n.getRight() != null)
            n = n.getRight();
        return (Cliente) n.getValue();
    }

    // Parte D: vacío = -1, un nodo = 0
    public int altura(Node rootNode) {
        if (rootNode == null)
            return -1;
        return 1 + Math.max(altura(rootNode.getLeft()), altura(rootNode.getRight()));
    }
}