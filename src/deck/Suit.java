package deck;

public enum Suit {
    //Enum's Objects
    SPADES("Spades", 1), HEARTS("Hearts", 2), DIAMONDS("Diamonds", 3), CLUBS("Clubs", 4);
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
