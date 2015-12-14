package nl.imacbest.subtitle;

import java.io.File;

/**
 * Created by Thomas on 24-11-2015.
 */
public class VideoFile {

    private File file;
    private String path;
    private String absolutePath;

    public VideoFile(String path, String absolutePath) {
        this.path = path;
        this.absolutePath = absolutePath;
    }

    public VideoFile(File file) {
        this.path = file.getPath();
        this.absolutePath = file.getAbsolutePath();
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
