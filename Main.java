import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Cliente[] clientes = {
            new Cliente("1001", 34, "Ana Torres",     850000f),
            new Cliente("1002", 28, "Luis Prado",     1200000f),
            new Cliente("1003", 45, "Marta Ríos",     430000f),
            new Cliente("1004", 28, "Camilo Vélez",   990000f),   // misma edad que Luis
            new Cliente("1005", 52, "Sofía Mejía",    2100000f),
            new Cliente("1006", 31, "Julián Ortiz",   670000f),
            new Cliente("1007", 23, "Valentina Cruz", 310000f),
            new Cliente("1008", 39, "Andrés Lozano",  1750000f),
            new Cliente("1009", 34, "Paola Gil",      540000f)    // misma edad que Ana
        };

        BinarySearchTree arbolCompras = new BinarySearchTree();
        BinarySearchTree arbolEdad = new BinarySearchTree();

        for (Cliente c : clientes) {
            // TODO 1
            arbolCompras.insertNode(new Node<Cliente>((int) c.getTotal_compras(), c));
            // TODO 2 (nodo propio para cada árbol)
            arbolEdad.insertNode(new Node<Cliente>(c.getEdad(), c));
        }

        System.out.println("Clientes registrados: " + clientes.length);
        System.out.println("Nodos en arbolCompras: " + arbolCompras.getKeyList().size());
        System.out.println("Nodos en arbolEdad: " + arbolEdad.getKeyList().size());
        // ¿Coinciden? Si no, vea la Parte C.

        // R1: listado ascendente por compras
        System.out.println("\n== R1: clientes por compras (ascendente) ==");
        for (Cliente c : arbolCompras.clientesAscendente())
            System.out.println(c);

        // R2: tres mayores compradores
        System.out.println("\n== R2: Top 3 compradores ==");
        for (Cliente c : arbolCompras.mayoresN(3))
            System.out.println(c);

        // R3: clientes entre 25 y 35 años
        System.out.println("\n== R3: clientes entre 25 y 35 años ==");
        for (Cliente c : arbolEdad.clientesEnRango(25, 35))
            System.out.println(c);

        // R4: búsqueda exacta por total de compras
        System.out.println("\n== R4: búsqueda por total de compras ==");
        int buscado = 990000;
        Node encontrado = arbolCompras.buscarNodo(buscado, arbolCompras.getRoot());
        if (encontrado != null)
            System.out.println((Cliente) encontrado.getValue());
        else
            System.out.println("No existe un cliente con compras = " + buscado);

        // R5: extremos por edad
        System.out.println("\n== R5: más joven y mayor ==");
        System.out.println("Más joven: " + arbolEdad.minimo());
        System.out.println("Mayor: " + arbolEdad.maximo());

        // Parte D: forma del árbol y altura
        System.out.println("\n== Parte D: altura según orden de inserción ==");
        int[] ordenado = {10, 20, 30, 40, 50, 60};
        int[] balanceado = {40, 20, 60, 10, 30, 50};

        BinarySearchTree t1 = new BinarySearchTree();
        for (int k : ordenado)
            t1.insertNode(new Node(k));
        System.out.println("Orden 10,20,30,40,50,60 -> altura " + t1.altura(t1.getRoot()));

        BinarySearchTree t2 = new BinarySearchTree();
        for (int k : balanceado)
            t2.insertNode(new Node(k));
        System.out.println("Orden 40,20,60,10,30,50 -> altura " + t2.altura(t2.getRoot()));
    }
}