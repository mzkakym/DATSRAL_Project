import java.util.ArrayList;

// Represents a graph of places and their connections (roads).
public class Graph {
    private final PlaceStore store;
    private final ArrayList<ArrayList<Integer>> adjacency;

    public Graph(PlaceStore store) {
        this.store = store;
        this.adjacency = new ArrayList<>();
        for (int i = 0; i < store.size(); i++) {
            this.adjacency.add(new ArrayList<>());
        }
    }

    // Adds an edge (road) between two places in the graph.
    public boolean addEdge(String from, String to) {
        int u = store.indexOf(from);
        int v = store.indexOf(to);

        if (u == -1 || v == -1 || u == v) {
            return false;
        }

        // Add connection both ways if not already present
        if (!adjacency.get(u).contains(v)) {
            adjacency.get(u).add(v);
        }
        if (!adjacency.get(v).contains(u)) {
            adjacency.get(v).add(u);
        }
        return true;
    }
    // Returns the neighbors (connected places) of a given place index.
    public ArrayList<Integer> getNeighbors(int index) {
        if (index < 0 || index >= adjacency.size()) {
            return new ArrayList<>();
        }
        return adjacency.get(index);
    }

    public int size() {
        return adjacency.size();
    }


    // Prints adjacency list
    public void printGraph() {
        for (int i = 0; i < adjacency.size(); i++) {
            System.out.print(store.get(i).getName() + " -> ");
            ArrayList<Integer> neighbors = adjacency.get(i);
            for (int j = 0; j < neighbors.size(); j++) {
                System.out.print(store.get(neighbors.get(j)).getName());
                if (j < neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}