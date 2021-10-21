package battleship;

import java.util.Random;

class Field {
    private final int xSize;
    private final int ySize;
    private final int carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum;
    public Ship[][] field;
    private final Random rd = new Random();
    public int shipsAmount;
    public String[][] userField;

    /**
     * Creates a field object.
     */
    public Field(int xSize, int ySize, int carriersNum, int battleshipsNum, int cruisersNum, int destroyersNum, int submarinesNum) {
        this.carriersNum = carriersNum;
        this.battleshipsNum = battleshipsNum;
        this.cruisersNum = cruisersNum;
        this.destroyersNum = destroyersNum;
        this.submarinesNum = submarinesNum;
        this.xSize = xSize;
        this.ySize = ySize;
        field = new Ship[xSize][ySize];
        userField = new String[xSize][ySize];
        shipsAmount = carriersNum + battleshipsNum + cruisersNum + destroyersNum + submarinesNum;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                userField[i][j] = ". ";
            }
        }
    }

    /**
     * Method to attack cell at x y.
     * @param x x cord of an attacked cell.
     * @param y y cord of an attacked cell.
     */
    public void Attack(int x, int y) {
        if (field[x][y] == null) {
            userField[x][y] = "o ";
        } else {
            userField[x][y] = "* ";
            field[x][y].hp--;
            if (field[x][y].hp == 0) {
                System.out.println("You just have sunk a " + field[x][y]);
                shipsAmount--;
            }
        }
    }

    /**
     * Method for setting ships in the field.
     * If program is unable to set any ship it throws corresponding exception;
     */
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

    /**
     * Method for setting defined ship.
     * @param size ship length.
     * @return ship is set.
     */
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
        int attemps = 10000000;
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
                } else {
                    for (int i = xPos; i < xPos + size; i++) {
                        field[i][yPos] = ship;
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

    /**
     * Method, checking the area around potential ship.
     * @param xPos x cord of a ship.
     * @param yPos y cord of a ship.
     * @param size ships' size.
     * @param vertical direction of the ship.
     * @return ship can be placed.
     */
    public boolean canSetShip(int xPos, int yPos, int size, boolean vertical) {
        int minX = Math.max(0, xPos - 1);
        int minY = Math.max(0, yPos - 1);
        int maxX = Math.min(xSize - 1, xPos + 1 + (vertical ? 0 : size));
        int maxY = Math.min(ySize - 1, yPos + 1 + (vertical ? size : 0));

        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (field[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the field.
     * @return string which represents the field.
     */
    public String toString() {
        String res = "";
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                res += userField[i][j] + " ";
            }
            res += "\n";
        }
        return res;
    }

}
