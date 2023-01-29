import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//package com.memorynotfound.useragent;


public class Main {
    public static void main(String[] args) throws IOException {
        int countY = 0;
        int countG = 0;
        int countAll = 0;
        final String regex = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"(.+?)\"";

        System.out.print("Введите путь к файлу: ");
        String path = new Scanner(System.in).nextLine();
        File file = new File(path);
        boolean fileExists = file.exists();
        boolean isDirectory = file.isDirectory();
        if ((!fileExists) || (isDirectory)) {
            System.out.println("Путь указан неверно, либо указан путь к папке");
            return;
        } else {
            System.out.println("Путь указан верно");
        }
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                Pattern p = Pattern.compile(regex);
                System.out.println("Apache log input line: " + line);
                countAll++;
                Matcher matcher = p.matcher(line);
                if (matcher.find()) {
                    System.out.println("IP Address: " + matcher.group(1));
                    System.out.println("UserName: " + matcher.group(3));
                    System.out.println("Date/Time: " + matcher.group(4));
                    System.out.println("Request: " + matcher.group(5));
                    System.out.println("Response: " + matcher.group(6));
                    System.out.println("Bytes Sent: " + matcher.group(7));
                    if (!matcher.group(8).equals("-"))
                        System.out.println("Referer: " + matcher.group(8));
                    System.out.println("User-Agent: " + matcher.group(9));
                    System.out.println("--------------------------------------------");

                    if (matcher.group(9).contains("(") && matcher.group(9).contains(")")) {
                        String helpString = matcher.group(9);
                        String[] parts = helpString.split(";");
                        if (parts.length >= 2) {
                            String fragment = parts[1];
                            if (fragment.contains("/")) {
                                String help = fragment.substring(0, fragment.indexOf('/')).trim();
                                System.out.println(help);
                                if (help.equals("Googlebot")) {
                                    countG++;
                                }
                                if (help.equals("YandexBot"))
                                    countY++;
                            }
                        }
                    }
                    if (line.length() > 1024) {
                        throw new RuntimeException("Длина строки больше 1024 символа");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("--------------------------------------");
        int n1 = countY * 100 / countAll;
        int n2 = countG * 100 / countAll;
        System.out.println("Доля запросов от YandexBot: " + n1 + "%");
        System.out.println("Доля запросов от GoogleBot: " + n2 + "%");
    }
}

