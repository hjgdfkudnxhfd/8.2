package org.example.Modules;

import com.drew.metadata.exif.ExifSubIFDDirectory;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

import javax.imageio.ImageIO;

@Component
public class ImageFileModule implements FileModule{
    String basePath = "C:\\Users\\val_4\\source\\repos\\JavaLaba8.2\\Test_files";

    @Override
    public boolean isCurrentFileFormatWorks(String path) {
        return path.endsWith(".jpg");
    }

    @Override
    public void getDesc() {
        System.out.println("1) Вывод размера изображения");
        System.out.println("2) Вывод информации EXIF");
        System.out.println("3) Вывод даты сьёмки из информации EXIF");
    }

    @Override
    public void method1(String path) {
        File file = new File(basePath+"\\"+path);
        try {
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            System.out.println("Ширина: " + width + " пикселей");
            System.out.println("Высота: " + height + " пикселей");
        } catch (IOException e) {
            System.err.println("Ошибка при чтении изображения: " + e.getMessage());
        }
    }

    @Override
    public void method2(String path) {
        File file = new File(basePath+"\\"+path);
        try {
            //Используется библиотека ImageMetadataReader для чтения метаданных EXIF из файла изображения
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            //Получение директории EXIF
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                for (Tag tag : directory.getTags()) {
                    System.out.println(tag.getTagName() + " : " + tag.getDescription());
                }
            }else {
                System.out.println("В файле нет EXIF информации");
            }
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void method3(String path) {
        File file = new File(basePath+"\\"+path);
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(file);

            // Получаем директорию EXIF
            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);

            if (directory != null) {
                Date date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                if (date != null) {
                    System.out.println("Дата съёмки: " + date);
                } else {
                    System.out.println("Дата съёмки не найдена в метаданных Exif.");
                }
            }else {
                System.out.println("В данном файле нет EXIF информации");
            }
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
    }
}