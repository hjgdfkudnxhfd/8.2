package org.example.Modules;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class TxtFileModule implements FileModule{
    String basePath = "C:\\Users\\val_4\\source\\repos\\JavaLaba8.2\\Test_files";
    @Override
    public boolean isCurrentFileFormatWorks(String path) {
        return path.endsWith(".txt");
    }

    @Override
    public void getDesc() {
        System.out.println("1) Вывод количества строк в файле");
        System.out.println("2) Вывод частоты вхождения каждого символа");
        System.out.println("3) Вывод количества символов в файле");
    }

    @Override
    public void method1(String path) {
        File file = new File(basePath+"\\"+path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Считываем строки из файла
            long count = reader.lines().count();
            System.out.println("Количество строк: " + count);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void method2(String path) {
        File file = new File(basePath+"\\"+path);
        Map<Character, Integer> mapLetterToCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char letter : line.toCharArray()){
                    mapLetterToCount.put(letter,mapLetterToCount.getOrDefault(letter,0)+1);
                }
            }

            for (Map.Entry<Character, Integer> entry : mapLetterToCount.entrySet()){
                System.out.printf("Символ '%c' встречается - %d раз%n", entry.getKey(), entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    @Override
    public void method3(String path) {
        File file = new File(basePath+"\\"+path);
        Map<Character, Integer> mapLetterToCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            long count = 0;
            while (reader.read() != -1) {
                count++;
            }
            System.out.printf("В файле - %d символов%n", count);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}