package deck;

public enum Suit {
    //Enum's Objects
    SPADES("Picas", 1), HEARTS("Corazones", 2), DIAMONDS("Diamantes", 3), CLUBS("Tréboles", 4);
    //Attributes
    private String name;
    private int id;
    //Constructors
    Suit(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    //Getters
    public String getName() { return name; }
    public int getId() { return id; }
}
