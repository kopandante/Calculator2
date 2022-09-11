import java.util.Scanner;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] chunks = input.split("\\s+");
        int l = chunks.length;

        // Если чисел с действиями между ними не два или три, вызывается ошибка
        if (l != 3 && l != 5) {
            throw new RuntimeException("Invalid input data");
        }

        // Функция на вырост, если вдруг понадобится обрабатывать больше трёх чисел.

        ArrayList<Integer> x = new ArrayList<>(); // Список чисел
        StringBuilder ops = new StringBuilder(); // Операторы действий
        for (int i = 0; i < chunks.length; i++) {
            if (i % 2 == 0) {
                x.add(Integer.parseInt(chunks[i]));
            } else {
                ops.append(chunks[i]);
            }
        }
        // если чисел всего два, в конец дописывается «плюс ноль», чтобы свич обработал это правильно
        if (ops.length() < 2) { x.add(0); ops.append("+"); }

        /* Задание не очень корректно сформулировано: в нём сказано, что числа могут принимать
        значения от 1 до 10, но в примерах используется отрицательное число. Поэтому я решил
        понимать это как «принимают значения от -10 до 10 включительно».

        Иначе должно быть так:
        if ((x.get(0) < 1 || x.get(0) > 10) || x.get(1) < 1 || x.get(1) > 10 || x.get(2) < 1 || x.get(2) > 10) {
            throw new NumberFormatException("Input data out of range");
        } */

        if (Math.abs(x.get(0)) > 10 || Math.abs(x.get(1)) > 10 || Math.abs(x.get(2)) > 10) {
            throw new NumberFormatException("Input data out of range");
        }

        /* Если бы переменных и/или действий с ними было больше, имело бы смысл выносить
        эту функцию, но сейчас это не имеет смысла. */

        int result = switch (ops.toString()) {
            case "++" -> x.get(0) + x.get(1) + x.get(2); case "+-" -> x.get(0) + x.get(1) - x.get(2);
            case "+*" -> x.get(0) + x.get(1) * x.get(2); case "+/" -> x.get(0) + x.get(1) / x.get(2);
            case "-+" -> x.get(0) - x.get(1) + x.get(2); case "--" -> x.get(0) - x.get(1) - x.get(2);
            case "-*" -> x.get(0) - x.get(1) * x.get(2); case "-/" -> x.get(0) - x.get(1) / x.get(2);
            case "*+" -> x.get(0) * x.get(1) + x.get(2); case "*-" -> x.get(0) * x.get(1) - x.get(2);
            case "**" -> x.get(0) * x.get(1) * x.get(2); case "*/" -> x.get(0) * x.get(1) / x.get(2);
            case "/+" -> x.get(0) / x.get(1) + x.get(2); case "/-" -> x.get(0) / x.get(1) - x.get(2);
            case "/*" -> x.get(0) / x.get(1) * x.get(2); case "//" -> x.get(0) / x.get(1) / x.get(2);
            default -> throw new RuntimeException("Invalid operators");
        };

        System.out.println(result);
    }
}