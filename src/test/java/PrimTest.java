import algorithm.prim.Prim;

public class PrimTest {
    public static void main(String[] args) {
        Prim prim = new Prim(9);
        prim.addEdge(1,2,4);
        prim.addEdge(1,8,8);

        prim.addEdge(2,3,8);
        prim.addEdge(2,8,11);

        prim.addEdge(3,4,7);
        prim.addEdge(3,9,2);
        prim.addEdge(3,6,4);

        prim.addEdge(4,5,9);
        prim.addEdge(4,6,14);

        prim.addEdge(5,6,10);


        prim.addEdge(6,7,2);


        prim.addEdge(7,8,1);
        prim.addEdge(7,9,6);

        prim.addEdge(8,9,7);

        prim.runPrim(1);
        prim.printNode();
    }
}
