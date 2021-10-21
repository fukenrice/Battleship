package battleship;

class Ship {
    public int hp;

    public Ship(int hp) {
        this.hp = hp;
    }

    public String toString() {
        return "*";
    }
}

class Carrier extends Ship {

    public Carrier(int hp) {
        super(hp);
    }
    public String toString() {
        return "Carrier";
    }
}

class Battleship extends Ship {

    public Battleship(int hp) {
        super(hp);
    }
    public String toString() {
        return "Battleship";
    }
}

class Cruiser extends Ship {

    public Cruiser(int hp) {
        super(hp);
    }
    public String toString() {
        return "Cruiser";
    }
}

class Destroyer extends Ship {

    public Destroyer(int hp) {
        super(hp);
    }
    public String toString() {
        return "Destroyer";
    }
}

class Submarine extends Ship {

    public Submarine(int hp) {
        super(hp);
    }
    public String toString() {
        return "Submarine";
    }
}
