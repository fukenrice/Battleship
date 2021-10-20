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
    private int xSize;
    private int ySize;
    private int carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum;
    public Ship[][] field;
    private Random rd = new Random();

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

    public boolean SetShips() {
        for (int i = 0; i < 10000; i++) {
            int totalShipsNum = carriersNum + battleshipsNum + cruisersNum + destroyersNum + submarinesNum;


            for (int j = 0; i < carriersNum; i++) {
                boolean vertical = rd.nextBoolean();
                if (vertical) {
                    int xPos = rd.nextInt(xSize);
                    int yPos = rd.nextInt(ySize - 5);
                }
                else {
                    int xPos = rd.nextInt(xSize - 5);
                    int yPos = rd.nextInt(ySize);
                }


            }


            if (totalShipsNum == 0) {
                break;
            }
            if (i == 9999) {
                return false;
            }
        }
        return true; // TODO: переделать.
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



