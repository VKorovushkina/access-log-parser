import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 0;
        for (; ; ) {
            System.out.print("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();
            if ((!fileExists) || (isDirectory)) {
                System.out.println("Путь указан неверно, либо указан путь к папке");
                continue;
            }
            else{
                count++;
                System.out.println("Путь указан верно\nЭто файл номер " + count);
            }
        }
    }
}
