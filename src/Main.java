import java.util.Scanner;

public class Main {
    static int num1;
    static int num2;
    static String operator;
    static int result;
    static String decision;
    static Scanner sc = new Scanner(System.in);

    public static String calc(String input) throws Exception {
        Convert convert = new Convert();


        //Scanner sc = new Scanner(System.in);
        // input = sc.nextLine(); // считываем выражение как строку

        String[] data = input.split(" "); // создаем строковый массив с  разделенителем по пробелу

        if (data.length > 3) {

            try {
                throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор");

            }
        }

        operator = data[1];// оператор
        if (convert.isRoman(data[0]) == convert.isRoman(data[2])) { // Определяем числа в одном формате или нет - оба римские или оба арабские

            boolean isRoman = convert.isRoman(data[0]);

            if (isRoman) { // если числа римские конвертируем их в арабские
                num1 = convert.romToArab(data[0]);
                num2 = convert.romToArab(data[2]);
                if (getCalc(num1, num2, operator) > 0) {
                    decision = convert.arabToRom(getCalc(num1, num2, operator));
                    return decision;
                } else {
                    throw new Exception("в римской системе нет отрицательных чисел");
                }
            }
            // если числа арабские, конвертируем их из строки в число
            num1 = Integer.parseInt(data[0]);
            num2 = Integer.parseInt(data[2]);
            if (num1 <= 10 && num2 <= 10 && num1 > 0 && num2 > 0) {
                decision = "" + getCalc(num1, num2, operator);
                return decision;

            } else {
                throw new Exception("Числа не соответсвуют заданному диапазону");
            }
        } else {
            throw new Exception("Ошибка ввода данных");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Введите математическое выражение.");
        String input = sc.nextLine();
        System.out.println(calc(input));

    }

    public static <InputMismatchException> int getCalc(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                try {
                    result = num1 / num2;

                } catch (ArithmeticException e) {

                    System.out.println("Делить на ноль нельзя");

                    break;
                }
                break;


            default:
                throw new IllegalArgumentException("Введен неизвестный оператор");
        }
        return result;

    }
}
