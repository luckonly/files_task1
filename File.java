package files_task1;

public class File {

    String path;
    boolean isDirectory;

    public File(String path, boolean isDirectory) {
        this.path = path;
        this.isDirectory = isDirectory;
    }

    public String getPath() {
        return path;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

}
