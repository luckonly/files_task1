package files_task1;

import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();
    public static String path = "/users/at/netology-ru/IdeaProjects/out/";

    public static void main(String[] args) {

        List<File> filesList = getFilesList();

        makeNewFiles(filesList);
        System.out.println(sb.toString());
        saveLogFile();

    }

    private static void saveLogFile() {

        try {
            String pathLog = "/users/at/netology-ru/IdeaProjects/out/Games/temp/temp.txt";
            FileWriter logFile = new FileWriter(pathLog);
            logFile.write(sb.toString());
            logFile.flush();
            logFile.close();
            System.out.println("Логи успешно записаны в temp.txt");
        } catch (IOException x) {
            System.out.println("Произошла ошибка при записи логов в temp.txt по причине: " + x.toString());
        }

    }

    private static List<File> getFilesList() {

        List<File> filesList = new ArrayList<>();

        filesList.add(new File(path + "/Games", true));
        filesList.add(new File(path + "/Games/src", true));
        filesList.add(new File(path + "/Games/src/Main", true));
        filesList.add(new File(path + "/Games/src/Main/Main.java", false));
        filesList.add(new File(path + "/Games/src/Main/Utils.java", false));
        filesList.add(new File(path + "/Games/src/Test", true));
        filesList.add(new File(path + "/Games/res", true));
        filesList.add(new File(path + "/Games/savegames", true));
        filesList.add(new File(path + "/Games/temp", true));
        filesList.add(new File(path + "/Games/res/drawables", true));
        filesList.add(new File(path + "/Games/res/vectors", true));
        filesList.add(new File(path + "/Games/res/icons", true));
        filesList.add(new File(path + "/Games/temp/temp.txt", false));

        return filesList;
    }

    private static void makeNewFiles(List<File> file) {
        for (int i = 0; i < file.size(); i++) {
            makeNewFile(file.get(i).getPath(), file.get(i).isDirectory());
        }
    }

    private static boolean makeNewFile(String path, boolean isDirectory) {

        java.io.File pathFile = new java.io.File(path);
        String[] fileNames = path.split("/");
        int count = fileNames.length;
        var fileName = fileNames[count-1];

        boolean filesExists = checkIfFileExists(pathFile, isDirectory);

        if (!filesExists) {
            if (isDirectory) {
                if (!pathFile.mkdir()) {
                    sb.append("При создании каталога " + fileName + " произошла ошибка\n");
                    return false;
                } else {
                    sb.append("Создание каталога " + fileName + " прошло успешно\n");
                    return true;
                }
            } else {
                try {
                    if (pathFile.createNewFile()) {
                        sb.append("Создание файла " + fileName + " прошло успешно\n");
                        return true;
                    } else {
                        sb.append("При создании файла " + fileName + " произошла ошибка\n");
                        return false;
                    }
                } catch (IOException ex) {
                    sb.append("При создании файла " + fileName + " произошла ошибка, текст ошибки: " + ex.toString() + "\n");
                    return false;
                }
            }
        } else {
            sb.append((isDirectory ? "Каталог " : "Файл ") + fileName + " был создан ранее\n");
            return true;
        }
    }

    private static boolean checkIfFileExists(java.io.File pathFile, boolean isDirectory) {

        if (pathFile.exists()) {

            if (isDirectory && pathFile.isDirectory()) {
                return true;
            } else if (!isDirectory && pathFile.isFile()) {
                return true;
            } else return false;

        } else return false;

    }

}
