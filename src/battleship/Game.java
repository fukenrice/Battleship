package battleship;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Scanner in = new Scanner(System.in);
    private static int xSize;
    private static int ySize;
    private static int carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum;
    private static int xShot, yShot;

    /**
     * Method for parsing integers.
     * @param value value.
     * @return value is integer.
     */
    static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method for validating coordinates of shot.
     * @return data is correct.
     */
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

    /**
     * Method for getting number of carriers on the field.
     * @param message info-message.
     * @return data is correct.
     */
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

    /**
     * Method for getting number of battleships on the field.
     * @param message info-message.
     * @return data is correct.
     */
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

    /**
     * Method for getting number of cruisers on the field.
     * @param message info-message.
     * @return data is correct.
     */
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

    /**
     * Method for getting number of submarines on the field.
     * @param message info-message.
     * @return data is correct.
     */
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

    /**
     * Method for getting number of destroyers on the field.
     * @param message info-message.
     * @return data is correct.
     */
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

    /**
     * Method for getting field configurations from user.
     * @return config is ok.
     */
    static boolean getData() {
        System.out.println("Вы запустили игру 'Морсокой бой', вы можете ввести характеристики поля и кораблей вручную или сыграть по обычным правилам (поле 10 на 10, 4 однопалубных, 3 двухпалубных, 2 трехпалубных и 1 четырехпалубный)\n default/input: ");
        String data = in.nextLine();
        while (!data.equals("default") && !data.equals("input")) {
            System.out.println("Пожалуйста, введите default или input: ");
            data = in.nextLine();
        }
        switch (data) {
            case "default" -> {
                xSize = 10;
                ySize = 10;
                carriersNum = 0;
                battleshipsNum = 1;
                cruisersNum = 2;
                destroyersNum = 3;
                submarinesNum = 4;
                return true;
            }
            case "input" -> {
                System.out.println("Введите размер поля в формате 'x y'(минимальный размер - 6 на 6): ");
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
        }
        return true;
    }

    public static void main(String[] args) {
        Field field = null;
        while (!getData()) {
            field = new Field(xSize, ySize, carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum);
        }

        while (true) {
            try {
                // Пробуем несколько раз расположить корабли, если не выйдет, сообщаем пользователю.
                for (int i = 0; i < 1000; i++) {
                    try {
                        field = new Field(xSize, ySize, carriersNum, battleshipsNum, cruisersNum, destroyersNum, submarinesNum);
                        field.SetShips();
                    }
                    catch (Exception e) {
                        if (i == 999) {
                            throw e;
                        }
                    }

                }
                break;
            }
            catch (Exception e) {
                System.out.println(e.getMessage() + "\nПожелуйста, введите данные заново.");
                while (!getData()) {

                }
            }
        }

// Можно раскомментировать для дебагга.
//        for (int i = 0; i < xSize; i++) {
//            for (int j = 0; j < ySize; j++) {
//                if (field.field[i][j] == null) {
//                    System.out.print(". ");
//                } else {
//                    System.out.print("* ");
//                }
//            }
//            System.out.println();
//        }

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
