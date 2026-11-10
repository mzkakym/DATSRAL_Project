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
        throw new UnsupportedOperationException();
    }

    private void printMenu() {
        throw new UnsupportedOperationException();
    }

    private void listPlaces() {
        throw new UnsupportedOperationException();
    }

    private void searchPlace() {
        throw new UnsupportedOperationException();
    }

    private void showPath() {
        throw new UnsupportedOperationException();
    }

    private Place askForPlace(String prompt) {
        throw new UnsupportedOperationException();
    }
}