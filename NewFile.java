package files_task1;

public class NewFile {

    String path;
    boolean isDirectory;

    public NewFile(String path, boolean isDirectory) {
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
