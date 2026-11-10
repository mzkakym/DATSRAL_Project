public class CampusMapFinder {
    public static void main(String[] args) {
        PlaceStore store = new PlaceStore();
        PathFinder finder = new PathFinder(null, store);
        Menu menu = new Menu(store, finder);
        menu.run();
    }
}