public class Place {
    private final String name;

    public Place(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Place name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
