package battleship;

import java.time.Year;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static void main(String[] args) {
        Carrier a = new Carrier(5);
        System.out.println(a.hp);

    }
}


class Field {
    private final int xSize;
    private final int ySize;
    private final int carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum;
    public Ship[][] field;
    private final Random rd = new Random();

    public Field(int xSize, int ySize, int carriersNum, int battleshipsNum, int cruisersNum, int destroyersNum, int submarinesNum) {
        this.carriersNum = carriersNum;
        this.battleshipsNum = battleshipsNum;
        this.cruisersNum = cruisersNum;
        this.destroyersNum = destroyersNum;
        this.submarinesNum = submarinesNum;
        this.xSize = xSize;
        this.ySize = ySize;
        field = new Ship[xSize][ySize];
    }

    public void SetShips() {
        for (int i = 0; i < carriersNum; i++) {
            if (!SetShip(5)) {
                throw new IllegalArgumentException("Не удалось сгенерировать заданное количество авианосцев");
            }
        }
        for (int i = 0; i < battleshipsNum; i++) {
            if (!SetShip(4)) {
                throw new IllegalArgumentException("Не удалось сгенерировать заданное количество линкоров");
            }
        }
        for (int i = 0; i < cruisersNum; i++) {
            if (!SetShip(3)) {
                throw new IllegalArgumentException("Не удалось сгенерировать заданное количество крейсеров");
            }
        }
        for (int i = 0; i < destroyersNum; i++) {
            if (!SetShip(2)) {
                throw new IllegalArgumentException("Не удалось сгенерировать заданное количество уничтожетелей(What?)");
            }
        }
        for (int i = 0; i < submarinesNum; i++) {
            if (!SetShip(1)) {
                throw new IllegalArgumentException("Не удалось сгенерировать заданное количество подлодок");
            }
        }
    }

    boolean SetShip(int size) {
        Ship ship;
        switch (size) {
            case 5:
                ship = new Carrier(5);
                break;
            case 4:
                ship = new Battleship(4);
                break;
            case 3:
                ship = new Cruiser(3);
                break;
            case 2:
                ship = new Destroyer(2);
                break;
            case 1:
                ship = new Submarine(1);
                break;
            default:
                ship = new Ship(0);
        }
        int attemps = 10000;
        for (int k = 0; k < attemps; k++) {
            boolean vertical = rd.nextBoolean();
            int xPos, yPos;
            if (vertical) {
                xPos = rd.nextInt(xSize);
                yPos = rd.nextInt(ySize - size);
            } else {
                xPos = rd.nextInt(xSize - size);
                yPos = rd.nextInt(ySize);
            }
            if (canSetShip(xPos, yPos, size, vertical)) {
                if (vertical) {
                    for (int l = yPos; l < yPos + size; l++) {
                        field[xPos][l] = ship;
                    }
                }
                break;
            }
            if (k == attemps - 1) {
                return false;
            }
        }
        return true;
    }


    public boolean canSetShip(int xPos, int yPos, int size, boolean vertical) {
        int minX = Math.max(0, xPos - 1);
        int minY = Math.max(0, yPos - 1);
        int maxX = Math.min(xSize, xPos + 1 + (vertical ? 0 : size));
        int maxY = Math.min(ySize, yPos + 1 + (vertical ? size : 0));

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (field[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

}

class Ship {
    public int hp;

    public Ship(int hp) {
        this.hp = hp;
    }
}

class Carrier extends Ship {

    public Carrier(int hp) {
        super(hp);
    }
}

class Battleship extends Ship {

    public Battleship(int hp) {
        super(hp);
    }
}

class Cruiser extends Ship {

    public Cruiser(int hp) {
        super(hp);
    }
}

class Destroyer extends Ship {

    public Destroyer(int hp) {
        super(hp);
    }
}

class Submarine extends Ship {

    public Submarine(int hp) {
        super(hp);
    }
}



