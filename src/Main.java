import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        Integer firstNumber = Integer.valueOf(new Scanner(System.in).nextLine());
        System.out.println("Введите втоорое число:");
        Integer secondNumber = Integer.valueOf(new Scanner(System.in).nextLine());

        System.out.println("Сумма чисел: " + (firstNumber+secondNumber));
        System.out.println("Разность чисел: " + (firstNumber-secondNumber));
        System.out.println("Произведение чисел: " + firstNumber*secondNumber);
        System.out.println("Частное чисел: " + (double)firstNumber/secondNumber);
    }
}
