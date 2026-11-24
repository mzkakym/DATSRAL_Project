import java.util.ArrayList;
import java.util.Collections;

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

        if (!adjacency.get(u).contains(v)) {
            adjacency.get(u).add(v);
            Collections.sort(adjacency.get(u));
        }
        if (!adjacency.get(v).contains(u)) {
            adjacency.get(v).add(u);
            Collections.sort(adjacency.get(v));
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
        int edgeCount = 0;
        for (int i = 0; i < adjacency.size(); i++) {
            System.out.print(store.get(i).getName() + " -> ");
            ArrayList<Integer> neighbors = adjacency.get(i);
            edgeCount += neighbors.size();
            for (int j = 0; j < neighbors.size(); j++) {
                System.out.print(store.get(neighbors.get(j)).getName());
                if (j < neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        System.out.println("Total roads: " + (edgeCount / 2));
    }
    // Prints every undirected edge once, sorted.
    public void printEdges() {
        ArrayList<String> edges = new ArrayList<>();
        for (int u = 0; u < adjacency.size(); u++) {
            for (int v : adjacency.get(u)) {
                if (u < v) {
                    edges.add(store.get(u).getName() + " -- " + store.get(v).getName());
                }
            }
        }
        Collections.sort(edges);
        System.out.println("Edges (" + edges.size() + "):");
        for (String e : edges) {
            System.out.println("  " + e);
        }
    }

    // Prints a BFS-layered ASCII view rooted at the given place.
    // Each level lists the places reachable at that distance, with the
    // parent(s) that discovered them, so cycles are visible as "(also: X)".
    public void printLayeredView(String rootName) {
        int root = store.indexOf(rootName);
        if (root == -1) {
            System.out.println("Place not found: " + rootName);
            return;
        }

        int n = adjacency.size();
        int[] dist = new int[n];
        ArrayList<ArrayList<Integer>> parents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dist[i] = -1;
            parents.add(new ArrayList<>());
        }

        MyQueue<Integer> queue = new MyQueue<>(n);
        dist[root] = 0;
        queue.offer(root);

        int maxDist = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int nb : adjacency.get(cur)) {
                if (dist[nb] == -1) {
                    dist[nb] = dist[cur] + 1;
                    parents.get(nb).add(cur);
                    queue.offer(nb);
                    if (dist[nb] > maxDist) maxDist = dist[nb];
                } else if (dist[nb] == dist[cur] + 1) {
                    parents.get(nb).add(cur); // same-level parent = cycle edge
                }
            }
        }

        System.out.println("Layered view rooted at: " + store.get(root).getName());
        System.out.println("==================================================");
        for (int level = 0; level <= maxDist; level++) {
            System.out.println("Level " + level + ":");
            for (int i = 0; i < n; i++) {
                if (dist[i] != level) continue;

                StringBuilder sb = new StringBuilder();
                sb.append("  ").append(store.get(i).getName());

                if (level == 0) {
                    sb.append("  (root)");
                } else {
                    sb.append("  <- ");
                    ArrayList<Integer> ps = parents.get(i);
                    for (int k = 0; k < ps.size(); k++) {
                        sb.append(store.get(ps.get(k)).getName());
                        if (k < ps.size() - 1) sb.append(", ");
                    }
                    if (ps.size() > 1) {
                        sb.append("  (cycle)");
                    }
                }
                System.out.println(sb);
            }
        }

        // Lists anything unreachable
        boolean anyUnreachable = false;
        for (int i = 0; i < n; i++) {
            if (dist[i] == -1) {
                if (!anyUnreachable) {
                    System.out.println("Unreachable from root:");
                    anyUnreachable = true;
                }
                System.out.println("  " + store.get(i).getName());
            }
        }
        if (!anyUnreachable) {
            System.out.println("(all places reachable)");
        }
    }
    // Prints adjacency, edge list, and a layered view for convenience.
    public void visualize(String rootName) {
        System.out.println("\n=== Adjacency list ===");
        printGraph();
        System.out.println("\n=== Edge list ===");
        printEdges();
        System.out.println("\n=== Layered view ===");
        printLayeredView(rootName);
    }
}