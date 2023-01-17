import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int count = 0;
        int min=999;
        int max=0;
        System.out.print("Введите путь к файлу: ");
        String path = new Scanner(System.in).nextLine();
        File file = new File(path);
        boolean fileExists = file.exists();
        boolean isDirectory = file.isDirectory();
        if ((!fileExists) || (isDirectory)) {
            System.out.println("Путь указан неверно, либо указан путь к папке");
            return;
        }
        else{
            System.out.println("Путь указан верно");
        }
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length()>1024){
                    throw new RuntimeException("Длина строки больше 1024 символа");
                }
                int length = line.length();
                if (length>max)
                    max=length;
                if (length<min)
                    min=length;
                count++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Количество строк в файле:"+count);
        System.out.println("Длина самой длинной строки:"+max);
        System.out.println("Длина самой короткой строки:"+min);
    }
}

