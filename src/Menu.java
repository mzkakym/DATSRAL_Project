import java.util.Scanner;

public class Menu {
    private final PlaceStore store;
    private final PathFinder finder;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(PlaceStore store, PathFinder finder) {
        this.store = store;
        this.finder = finder;
    }

    public void run() {
        int choice;
        do {
            printMenu();
            System.out.print("Choose an option: ");
            String input = scanner.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice.");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    // load campus_maptxt
                    break;
                case 2:
                    listPlaces();
                    break;
                case 3:
                    showPath();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please choose 1, 2, 3, or 4.");
            }
        } while (choice != 4);
    }

    private void printMenu() {
        System.out.println("\n=== Campus Map Finder===");
        System.out.println("1. Load the map file.");
        System.out.println("2. Show all places in A-to-Z order.");
        System.out.println("3. Find a path between 2 places.");
        System.out.println("4. Quit");
    }

    private void listPlaces() {
        if (store.isEmpty()){
            System.out.println("No places loaded");
            return;
        }
        store.printSorted()
    }

    private void searchPlace() {
        throw new UnsupportedOperationException();
    }

    private void showPath() {
        if (store.isEmpty()) {
            System.out.println("Please load a map first.");
            return;
        }

        Place start = askForPlace("Enter starting place: ");
        if (start == null) return;

        Place end = askForPlace("Enter ending place: ");
        if (end == null) return;

        ArrayList<Place> path = finder.findPath(start, end);

        if (path == null || path.isEmpty()) {
            System.out.println("No path found.");
            return;
        }

        System.out.println("Path:");
        for (Place p : path) {
            System.out.print(p.getName() + " ");
        }
        System.out.println();
        System.out.println("Number of stops: " + (path.size() - 1));

    }

    private Place askForPlace(String prompt) {
        System.out.print(prompt);
        String name = scanner.nextLine().trim();

        Place place = store.find(name);
        if (place == null) {
            System.out.println("Place not found: " + name);
            return null;
        }
        return place;
    }
}
