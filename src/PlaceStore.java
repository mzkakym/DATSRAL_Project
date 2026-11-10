import java.util.ArrayList;

public class PlaceStore {
    private final ArrayList<Place> places = new ArrayList<>();

    public boolean add(Place place) {
        if (find(place.getName()) != null) {
            return false;
        }
        places.add(place);
        return true;
    }

    public Place find(String name) {
        for (Place p : places) {
            if (p.getName().equalsIgnoreCase(name.trim())) {
                return p;
            }
        }
        return null;
    }

    public int indexOf(String name) {
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getName().equalsIgnoreCase(name.trim())) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Place> sortedCopy() {
        ArrayList<Place> copy = new ArrayList<>(places);
        for (int i = 1; i < copy.size(); i++) {
            Place key = copy.get(i);
            int j = i - 1;
            while (j >= 0 && copy.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                copy.set(j + 1, copy.get(j));
                j--;
            }
            copy.set(j + 1, key);
        }
        return copy;
    }

    public Place get(int index) {
        return places.get(index);
    }

    public int size() {
        return places.size();
    }

    public boolean isEmpty() {
        return places.isEmpty();
    }

    public void printAll() {
        for (int i = 0; i < places.size(); i++) {
            System.out.println((i + 1) + ". " + places.get(i));
        }
    }
}

