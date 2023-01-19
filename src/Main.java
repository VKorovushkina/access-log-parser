import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        int countY = 0;
        int countG=0;
        int countAll=0;
        int min=999;
        int max=0;
        final String regex = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"(.+?)\"";

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
                Pattern p = Pattern.compile(regex);
                //System.out.println("Apache log input line: " + line);
                countAll++;
                Matcher matcher = p.matcher(line);
                if (matcher.find()) {
                    /*System.out.println("IP Address: " + matcher.group(1));
                    System.out.println("UserName: " + matcher.group(3));
                    System.out.println("Date/Time: " + matcher.group(4));
                    System.out.println("Request: " + matcher.group(5));
                    System.out.println("Response: " + matcher.group(6));
                    System.out.println("Bytes Sent: " + matcher.group(7));
                    System.out.println("--------------------------------------------");
                    if (!matcher.group(8).equals("-"))
                        System.out.println("Referer: " + matcher.group(8));
                    System.out.println("User-Agent: " + matcher.group(9));*/

                    if (matcher.group(9).contains("(") && matcher.group(9).contains(")")) {
                        String helpString = matcher.group(9);
                        //System.out.println("ВЫВОД МОЕЙ СТРОКИ  " + helpString.substring(helpString.indexOf('(') + 1, helpString.indexOf(')')));
                        String[] parts = helpString.split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1];
                            if(fragment.contains("/")){
                                String help=fragment.substring(0, fragment.indexOf('/')).trim();
                                System.out.println(help);
                                if(help.equals("Googlebot"))
                                {
                                    countG++;
                                }
                                if(help.equals("YandexBot"))
                                    countY++;
                            }
                        }
                    }
                    if (line.length() > 1024) {
                        throw new RuntimeException("Длина строки больше 1024 символа");
                    }
                }
            }

                    // закомментировано в рамках задания 2
                /*int length = line.length();
                if (length>max)
                    max=length;
                if (length<min)
                    min=length;
                count++;*/
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println("countAll "+ countAll);
        System.out.println("countY "+ countY);
        System.out.println("countG "+ countG);
        int n1=countY*100/countAll;
        int n2=countG*100/countAll;
        System.out.println("Доля запросов от YandexBot: "+n1+"%");
        System.out.println("Доля запросов от GoogleBot: "+n2+"%");
        //System.out.println("Длина самой длинной строки:"+max);
        //System.out.println("Длина самой короткой строки:"+min);
    }
}

