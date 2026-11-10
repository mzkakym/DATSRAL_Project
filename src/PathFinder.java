import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// BFS pathfinding to find shortest path
public class PathFinder {
    private Graph graph;
    private final PlaceStore store;

    public PathFinder(Graph graph, PlaceStore store) {
        this.graph = graph;
        this.store = store;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    // Finds the shortest path between two places using BFS.
    public ArrayList<Place> findPath(Place start, Place end) {
        if (graph == null || start == null || end == null) {
            return null;
        }

        int startIdx = store.indexOf(start.getName());
        int endIdx = store.indexOf(end.getName());

        if (startIdx == -1 || endIdx == -1) {
            return null;
        }

        if (startIdx == endIdx) {
            ArrayList<Place> single = new ArrayList<>();
            single.add(start);
            return single;
        }

        int n = store.size();
        boolean[] visited = new boolean[n];
        int[] cameFrom = new int[n];
        Arrays.fill(cameFrom, -1);

        MyQueue<Integer> queue = new MyQueue<>(n);
        queue.offer(startIdx);
        visited[startIdx] = true;

        boolean found = false;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == endIdx) {
                found = true;
                break;
            }

            for (int neighbor : graph.getNeighbors(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    cameFrom[neighbor] = current;
                    queue.offer(neighbor);
                }
            }
        }

        if (!found) {
            return null;
        }

        return buildPath(cameFrom, startIdx, endIdx);
    }

    // Builds the path from the cameFrom array
    private ArrayList<Place> buildPath(int[] cameFrom, int startIndex, int endIndex) {
        ArrayList<Place> path = new ArrayList<>();
        int curr = endIndex;

        while (curr != -1) {
            path.add(store.get(curr));
            if (curr == startIndex) {
                break;
            }
            curr = cameFrom[curr];
        }

        Collections.reverse(path);
        return path;
    }
}
