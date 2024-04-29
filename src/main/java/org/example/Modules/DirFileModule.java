package org.example.Modules;

import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class DirFileModule implements FileModule {

    String basePath = "C:\\Users\\val_4\\source\\repos\\JavaLaba8.2\\Test_files";
    @Override
    public boolean isCurrentFileFormatWorks(String path) {
        File fileSystemElement = new File(basePath + "\\" + path);
        return fileSystemElement.isDirectory();
    }

    @Override
    public void getDesc() {
        System.out.println("1) Вывод списка файлов в каталоге");
        System.out.println("2) Вывод суммарного размера всех файлов в каталоге");
        System.out.println("3) Вывод количества файлов и папок в каталоге");
    }

    @Override
    public void method1(String path) {
        File directory = new File(basePath + "\\" + path);

        // Получаем список файлов в каталоге
        File[] files = directory.listFiles();

        // Выводим имена файлов
        if (files != null) {
            for (File file : files) {
                if(file.isFile()){
                    System.out.println(file.getName());
                }
            }
        }
    }

    @Override
    public void method2(String path) {
        File directory = new File(basePath + "\\" + path);
        // Получаем список файлов в каталоге
        File[] files = directory.listFiles();

        long size = 0;
        // Выводим имена файлов
        if (files != null) {
            for (File file : files) {
                if(file.isFile()){
                    size+=file.length();
                }
            }
        }
        System.out.printf("%d байт",size);
    }

    @Override
    public void method3(String path) {
        File directory = new File(basePath + "\\" + path);


        // Получаем список файлов в каталоге
        File[] files = directory.listFiles();
        int fileCount = 0;
        int dirCount = 0;
        // Выводим имена файлов
        if (files != null) {
            for (File file : files) {
                if(file.isFile()){
                    fileCount++;
                }else{
                    dirCount++;
                }
            }
        }
        System.out.printf("%d папок%n",dirCount); //позволяет выводить переменные а не только текст
        System.out.printf("%d файлов%n",fileCount);
    }
}