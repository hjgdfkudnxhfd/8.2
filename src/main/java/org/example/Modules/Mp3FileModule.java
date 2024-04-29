package org.example.Modules;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class Mp3FileModule implements FileModule{
    String basePath = "C:\\Users\\val_4\\source\\repos\\JavaLaba8.2\\Test_files";
    @Override
    public boolean isCurrentFileFormatWorks(String path) {
        return path.endsWith(".mp3");
    }

    @Override
    public void getDesc() {
        System.out.println("1) Вывод названия трека из тегов");
        System.out.println("2) Вывод длительности в секундах");
        System.out.println("3) Вывод исполнителя трека из тегов");
    }

    @Override
    public void method1(String path) {
        File file = new File(basePath+"\\"+path);
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            String title = audioFile.getTag().getFirst(FieldKey.TITLE);;
            System.out.println("Название трека: " + title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void method2(String path) {
        File file = new File(basePath+"\\"+path);
        try {
            AudioFile audioFile = AudioFileIO.read(file);

            int durationSeconds = audioFile.getAudioHeader().getTrackLength();

            System.out.println("Длительность трека в секундах: " + durationSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void method3(String path) {
        File file = new File(basePath+"\\"+path);
        try {
            AudioFile audioFile = AudioFileIO.read(file);
            String artist = audioFile.getTag().getFirst(FieldKey.ARTIST);;

            System.out.println("Исполнитель: " + artist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}