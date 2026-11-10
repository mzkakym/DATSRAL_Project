import java.util.ArrayList;

public class PathFinder {
    private final Graph graph;
    private final PlaceStore store;

    public PathFinder(Graph graph, PlaceStore store) {
        this.graph = graph;
        this.store = store;
    }

    public ArrayList<Place> findPath(Place start, Place end) {
        throw new UnsupportedOperationException();
    }

    private ArrayList<Place> buildPath(int[] cameFrom, int startIndex, int endIndex) {
        throw new UnsupportedOperationException();
    }
}
