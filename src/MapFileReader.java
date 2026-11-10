import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapFileReader {
    public static Graph load(String filename, PlaceStore store) throws IOException {
        ArrayList<String[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    throw new IOException("Line " + lineNumber + ": expected two places like 'Library Cafeteria'");
                }
                if (parts[0].equalsIgnoreCase(parts[1])) {
                    throw new IOException("Line " + lineNumber + ": a road cannot connect a place to itself");
                }
                rows.add(parts);
            }
        }

        for (String[] row : rows) {
            store.add(new Place(row[0]));
            store.add(new Place(row[1]));
        }

        Graph graph = new Graph(store);
        for (String[] row : rows) {
            graph.addEdge(row[0], row[1]);
        }
        return graph;
    }
}
