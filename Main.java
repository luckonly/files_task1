package files_task1;

import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        List<NewFile> filesList = getFilesList();

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

    private static List<NewFile> getFilesList() {

        List<NewFile> filesList = new ArrayList<>();

        sb.append("Список файлов: ");

        String pathGames = "/users/at/netology-ru/IdeaProjects/out/Games";
        NewFile fileDirGames = new NewFile(pathGames, true);
        filesList.add(fileDirGames);

        String pathSrc = "/users/at/netology-ru/IdeaProjects/out/Games/src";
        NewFile fileDirSrc = new NewFile(pathSrc, true);
        filesList.add(fileDirSrc);

        String pathMain = "/users/at/netology-ru/IdeaProjects/out/Games/src/Main";
        NewFile fileDirMain = new NewFile(pathMain, true);
        filesList.add(fileDirMain);

        String pathMainJava = "/users/at/netology-ru/IdeaProjects/out/Games/src/Main/Main.java";
        NewFile fileMainJava = new NewFile(pathMainJava, false);
        filesList.add(fileMainJava);

        String pathUtilsJava = "/users/at/netology-ru/IdeaProjects/out/Games/src/Main/Utils.java";
        NewFile fileUtilsJava = new NewFile(pathUtilsJava, false);
        filesList.add(fileUtilsJava);

        String pathTest = "/users/at/netology-ru/IdeaProjects/out/Games/src/Test";
        NewFile fileDirTest = new NewFile(pathTest, true);
        filesList.add(fileDirTest);

        String pathRes = "/users/at/netology-ru/IdeaProjects/out/Games/res";
        NewFile fileDirRes = new NewFile(pathRes, true);
        filesList.add(fileDirRes);

        String pathDirSavegames = "/users/at/netology-ru/IdeaProjects/out/Games/savegames";
        NewFile fileDirSavegames = new NewFile(pathDirSavegames, true);
        filesList.add(fileDirSavegames);

        String pathDirTemp = "/users/at/netology-ru/IdeaProjects/out/Games/temp";
        NewFile fileDirTemp = new NewFile(pathDirTemp, true);
        filesList.add(fileDirTemp);

        String pathDirDrawables = "/users/at/netology-ru/IdeaProjects/out/Games/res/drawables";
        NewFile fileDirDrawables = new NewFile(pathDirDrawables, true);
        filesList.add(fileDirDrawables);

        String pathVectors = "/users/at/netology-ru/IdeaProjects/out/Games/res/vectors";
        NewFile fileDirVectors = new NewFile(pathVectors, true);
        filesList.add(fileDirVectors);

        String pathIcons = "/users/at/netology-ru/IdeaProjects/out/Games/res/icons";
        NewFile fileDirIcons = new NewFile(pathIcons, true);
        filesList.add(fileDirIcons);

        String pathFileTemp = "/users/at/netology-ru/IdeaProjects/out/Games/temp/temp.txt";
        NewFile fileTemp = new NewFile(pathFileTemp, false);
        filesList.add(fileTemp);

        return filesList;

    }

    private static void makeNewFiles(List<NewFile> fileList) {
        for (int i = 0; i < fileList.size(); i++) {
            makeNewFile(fileList.get(i).getPath(), fileList.get(i).isDirectory());
        }
    }

    private static boolean makeNewFile(String path, boolean isDirectory) {

        File pathFile = new File(path);
        String[] fileNames = path.split("/");
        int count = fileNames.length;
        var fileName = fileNames[count-1];

        boolean filesExists = checkFileExists(pathFile, isDirectory);

        if (!filesExists) {
            if (isDirectory) {
                if (!pathFile.mkdir()) {
                    sb.append("\n\tПри создании каталога " + fileName + " произошла ошибка");
                    return false;
                } else {
                    sb.append("\n\tСоздание каталога " + fileName + " прошло успешно");
                    return true;
                }
            } else {
                try {
                    if (pathFile.createNewFile()) {
                        sb.append("\n\tСоздание файла " + fileName + " прошло успешно");
                        return true;
                    } else {
                        sb.append("\n\tПри создании файла " + fileName + " произошла ошибка");
                        return false;
                    }
                } catch (IOException ex) {
                    sb.append("\n\tПри создании файла " + fileName + " произошла ошибка, текст ошибки: " + ex.toString());
                    return false;
                }
            }
        } else {
            sb.append("\n\t" + (isDirectory ? "Каталог " : "Файл ") + fileName + " был создан ранее");
            return true;
        }
    }

    private static boolean checkFileExists(File pathFile, boolean isDirectory) {

        if (pathFile.exists()) {

            if (isDirectory && pathFile.isDirectory()) {
                return true;
            } else if (!isDirectory && pathFile.isFile()) {
                return true;
            } else return false;

        } else return false;

    }


}
