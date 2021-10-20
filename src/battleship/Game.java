package battleship;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Scanner in = new Scanner(System.in);
    private static int xSize;
    private static int ySize;
    private static int carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum;
    private static int xShot, yShot;

    static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean getShotCoords() {
        String s = in.nextLine();
        var a = s.split(" ");
        while (a.length != 2 || !tryParseInt(a[0]) || !tryParseInt(a[1])) {
            System.out.println("Введите ровно 2 числа - координаты выстрела, пожалуйта, повторите попытку: ");
            s = in.nextLine();
            a = s.split(" ");
        }
        xShot = Integer.parseInt(a[0]);
        yShot = Integer.parseInt(a[1]);
        if (xShot < 1 || xShot > xSize || yShot < 0 || yShot > ySize) {
            System.out.println("Введены неверные координаты выстрела, повторите попытку: ");
            return false;
        }
        return true;
    }

    static boolean getCarrierNum(String message) {
        System.out.println(message);
        String data = in.nextLine();
        while (!tryParseInt(data)) {
            System.out.println("Вы ввели не число, повторите попытку");
            data = in.nextLine();
        }
        carriersNum = Integer.parseInt(data);
        if (carriersNum < 0) {
            System.out.println("Вы ввели некорректное число, повторите попытку");
            return false;
        }
        return true;
    }

    static boolean getBattleshipNum(String message) {
        System.out.println(message);
        String data = in.nextLine();
        while (!tryParseInt(data)) {
            System.out.println("Вы ввели не число, повторите попытку");
            data = in.nextLine();
        }
        battleshipsNum = Integer.parseInt(data);
        if (battleshipsNum < 0) {
            System.out.println("Вы ввели некорректное число, повторите попытку");
            return false;
        }
        return true;
    }

    static boolean getCruiserNum(String message) {
        System.out.println(message);
        String data = in.nextLine();
        while (!tryParseInt(data)) {
            System.out.println("Вы ввели не число, повторите попытку");
            data = in.nextLine();
        }
        cruisersNum = Integer.parseInt(data);
        if (cruisersNum < 0) {
            System.out.println("Вы ввели некорректное число, повторите попытку");
            return false;
        }
        return true;
    }

    static boolean getSubmarinesNum(String message) {
        System.out.println(message);
        String data = in.nextLine();
        while (!tryParseInt(data)) {
            System.out.println("Вы ввели не число, повторите попытку");
            data = in.nextLine();
        }
        submarinesNum = Integer.parseInt(data);
        if (submarinesNum < 0) {
            System.out.println("Вы ввели некорректное число, повторите попытку");
            return false;
        }
        return true;
    }

    static boolean getDestroyersNum(String message) {
        System.out.println(message);
        String data = in.nextLine();
        while (!tryParseInt(data)) {
            System.out.println("Вы ввели не число, повторите попытку");
            data = in.nextLine();
        }
        destroyersNum = Integer.parseInt(data);
        if (destroyersNum < 0) {
            System.out.println("Вы ввели некорректное число, повторите попытку");
            return false;
        }
        return true;
    }

    static boolean getData() {
        System.out.println("Вы запустили игру 'Морсокой бой', вы можете ввести характеристики поля и кораблей вручную или сыграть по обычным правилам (поле 10 на 10, 4 однопалубных, 3 двухпалубных, 2 трехпалубных и 1 четырехпалубный)\n default/input: ");
        String data = in.nextLine();
        while (!data.equals("default") && !data.equals("input")) {
            System.out.println("Пожалуйста, введите default или input: ");
            data = in.nextLine();
        }
        switch (data) {
            case "default":
                xSize = 10;
                ySize = 10;
                carriersNum = 0;
                battleshipsNum = 1;
                cruisersNum = 2;
                destroyersNum = 3;
                submarinesNum = 4;
                return true;
            case "input":
                System.out.println("Введите размер поля в формате 'x y': ");
                data = in.nextLine();
                var a = data.split(" ");
                while (a.length != 2 || !tryParseInt(a[0]) || !tryParseInt(a[1])) {
                    System.out.println("Введите размер поля в формате 'x y': ");
                    data = in.nextLine();
                    a = data.split(" ");
                }
                xSize = Integer.parseInt(a[0]);
                ySize = Integer.parseInt(a[1]);
                if (xSize < 6 || ySize < 6 || xSize > 30 || ySize > 30) {
                    System.out.println("Вы ввели некорректные параметры размеров поля, они должны быть в промежутке от 6 до 30\n Придется начать ввод заново(");
                    return false;
                }

                while (!getCarrierNum("Введите количество пятипалубных кораблей(не меньше нуля): ")) {

                }
                while (!getBattleshipNum("Введите количество четырехпалубных кораблей(не меньше нуля): ")) {

                }

                while (!getCruiserNum("Введите количество трехпалубных кораблей(не меньше нуля): ")) {

                }

                while (!getDestroyersNum("Введите количество двухпалубных кораблей(не меньше нуля): ")) {

                }

                while (!getSubmarinesNum("Введите количество однопалубных кораблей(не меньше нуля): ")) {

                }
        }
        return true;
    }

    public static void main(String[] args) {

        while (!getData()) {

        }

        Field field = new Field(xSize, ySize, carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum);

        while (true) {
            try {
                field.SetShips();
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "\nПожелуйста, введите данные заново.");
                while (!getData()) {

                }
            }
        }


        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (field.field[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

        System.out.println("Оружие готово, можете начинать огонь, вводите координаты выстрела в формате x y начиная с 1 до максимальной размерности.");

        int shotsCnt = 0;
        do {
            while (!getShotCoords());
            xShot--;
            yShot--;
            field.Attack(xShot, yShot);
            System.out.println(field.toString());
            shotsCnt++;
        } while (field.shipsAmount != 0);

        System.out.println("Игра окончена вы сделали " + shotsCnt + " выстрелов для победы.");

    }
}


class Field {
    private final int xSize;
    private final int ySize;
    private final int carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum;
    public Ship[][] field;
    private final Random rd = new Random();
    public int shipsAmount;
    public String[][] userField;

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
